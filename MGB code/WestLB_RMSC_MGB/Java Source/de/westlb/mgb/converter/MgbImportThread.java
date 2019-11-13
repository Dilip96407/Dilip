package de.westlb.mgb.converter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import de.westlb.mgb.model.definition.ImportPropertiesDef;
import de.westlb.mgb.model.definition.JobStateDef;
import de.westlb.mgb.model.impl.JobImpl;
import de.westlb.mgb.model.impl.SourceSystemImpl;
import de.westlb.mgb.model.impl.finder.JobSearchParams;
import de.westlb.mgb.model.impl.finder.MgbFinderImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.StoreSingleton;
import de.westlb.mgb.util.DateTimeUtils;
/**
 * @author D055625
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class MgbImportThread extends Thread implements ImportPropertiesDef, Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = -2125590270791529878L;
    private static final Logger log = Logger.getLogger(MgbImportThread.class);
	public static long POLLING_INTERVAL = 30 * 1000; // 30 sec
	private static long FILE_CHANGE_CHECK_INTERVAL = 10 * 1000; // 10 sec
	
	private static final int lastLineArrayNumberOfRecordsPos = 1;
	private static final int lastLineArrayStartBuissnesTimePos = 2;
	private static final int lastLineArrayStopBuissnesTimePos = 3;
	private static final int lastLineArraySystemDatePos = 4;
	
	private MgbImportThreadStatus status = new MgbImportThreadStatus();
	/**
	 * Method extractHostFromDBUrl. The url may look like:
	 * hibernate.connection.url=jdbc:oracle:oci8:@(description=(address=(host=wlb07077)(protocol=tcp)(port=1521))(connect_data=(sid=RMMPTST1)))
	 * or like:
	 * hibernate.connection.url=jdbc:oracle:thin:@dusu0093:1521:RMMGBPRD
	 * 
	 * @param url
	 * @return String
	 * @throws Exception
	 */
	private String extractHostFromDBUrl(String url) throws Exception {
		int start = url.indexOf("sid=") + 4;
		if (start > 3) {
			int stop = url.indexOf(")", start);
			if (stop > -1) {
				return url.substring(start, stop);
			}
		} else {
			log.warn("Try to extract sid from short url");
			start = url.lastIndexOf(":") + 1;
			if (start > 0) {
				return url.substring(start, url.length());
			}
		}
		throw new Exception("Unable to extract the SID from the URL " + url);
	}
	/**
	 * Constructor for MgbImportThread.
	 */
	public MgbImportThread(Properties importProperties,
			Properties hibernateProperties, String sourceSystemPrefix)
			throws Exception {
		super(sourceSystemPrefix);
		setDaemon(true);
		status.setDbUser(getParameterFromProperties(hibernateProperties, HIBERNATE_USER));
		status.setDbPassword(getParameterFromProperties(hibernateProperties, HIBERNATE_PASSWORD));
		String url = getParameterFromProperties(hibernateProperties, HIBERNATE_URL);
		status.setDbHost(extractHostFromDBUrl(url));
		status.setSqlLoaderPathName(getParameterFromProperties(importProperties, IMPORT_SQL_LOADER_PATH));
		status.setLoaderCtlDir(getParameterFromProperties(importProperties, IMPORT_CTL_DIR));
		status.setWorkingDir(getParameterFromProperties(importProperties, IMPORT_WORK_DIR));
		status.setArchiveDir(getParameterFromProperties(importProperties, IMPORT_ARCHIVE_DIR));
		status.setImportDir(getParameterFromProperties(importProperties, IMPORT_IMPORT_DIR));

		Session sess = StoreSingleton.getUniqueInstance().openBatchSession();
		log.info("Receiving inport configuration.");
		SourceSystemImpl  sourceSystem = (SourceSystemImpl) sess.select(SourceSystemImpl.class, sourceSystemPrefix);
		sess.close();
		status.setSourceSystem(sourceSystem);

		status.setStartTime(new GregorianCalendar());
		status.setStatus(MgbImportThreadStatus.INITIALIZED);
		status.debug();
	}
	
	/**
	 * Method getParameterFromProperties.
	 * 
	 * @param properties
	 * @param parameter
	 * @return String
	 * @throws Exception
	 */
	private String getParameterFromProperties(Properties properties,
			String parameter) throws Exception {
		return getParameterFromProperties(properties, parameter, null);
	}
	/**
	 * Method getParameterFromProperties.
	 * 
	 * @param properties
	 * @param parameter
	 * @param defaultValue
	 * @return String
	 * @throws Exception
	 */
	private String getParameterFromProperties(Properties properties,
			String parameter, String defaultValue) throws Exception {
		String propertiesvalue = properties
				.getProperty(parameter, defaultValue);
		log.debug("Param " + parameter + " = " + propertiesvalue);
		if (propertiesvalue == null) {
			String msg = "could not resolve parameter " + parameter;
			log.fatal(msg);
			throw new Exception(msg);
		}
		return propertiesvalue;
	}
	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
    public void run() {
		log.debug("Started thread " + getName());
		FilenameFilter[] files = new FilenameFilter[status.getImportFiles().length];
		File importDir = new File(status.getImportDir());
		for (int i = 0; i < files.length; i++) {
		    String fn = status.getImportFiles()[i] + "." + status.getImportFileExtension();
	        files[i] = new MessageFormatFilenameFilter(fn);
        }

		for (int i = 0; i < files.length; i++) {
	        log.debug("waiting for file " + files[i] + " in " + status.getImportDir());            
        }
		while (!isStopRequested()) {
			try {
				status.setStatus(MgbImportThreadStatus.WAITING);
				sleep(POLLING_INTERVAL);
				for (int i = 0; i < files.length; i++) {
				    long size = -1;
				    FilenameFilter ffilter = files[i];
				    log.trace("checking for file " + ffilter);
				    File[] matchingFiles = importDir.listFiles(ffilter);
				    if (matchingFiles.length>0) {
				        /* Easy way to circumvent files with permanent errors --
				         * pick a random one: */
				        File file = matchingFiles[(int)(Math.random() * matchingFiles.length)];
		                status.setStatus(MgbImportThreadStatus.RECEIVING);
				        log.debug("found file " + file.getName());
				        long newSize = file.length();
				        long fileAge = 0; 
				        while (newSize > size || (newSize == 0 && fileAge < status.getMaxFileAge())) {
				            log.debug("still receiving file " + file.getName()+ ", size="+newSize);
				            size = newSize;
				            sleep(FILE_CHANGE_CHECK_INTERVAL);
				            newSize = file.length();
				            fileAge = System.currentTimeMillis() - file.lastModified(); 
				            if (isStopRequested()) {
				                throw new InterruptedException("This thread will stop receiving file "+file.getName());
				            }
				        }
				        log.debug("received file " + file.getName()+ ", size="+newSize);
			            status.setStatus(MgbImportThreadStatus.PROCESSING);
				        if (newSize == 0) {
	                        processEmptyFile(file);
				        } else {
				            processFile(file);
				        }
                        status.setLastImportTime(new GregorianCalendar());
				    }
				}
			} catch (InterruptedException ie) {
				log.debug("Interupted thread " + getName());
			}
		}
		log.debug("Stopped thread " + getName());
		status.setStatus(MgbImportThreadStatus.STOPPED);
	}

	private Calendar extractCob(File file) {
        SourceSystemImpl sourceSystem = status.getSourceSystem();
        String fileName = file.getName();
        String cobPattern = sourceSystem.getCobFormat();
        int offset = sourceSystem.getOffset();
        Calendar tmpCal = new GregorianCalendar();
        tmpCal.add(Calendar.DATE, 0-offset);
        Calendar cob = DateTimeUtils.lastBusinessCal(tmpCal);
        if (cobPattern != null) {
            int cobPos = sourceSystem.getCobIndex();
            String cobString = fileName.substring(cobPos, cobPos+cobPattern.length());
			log.info("set cob "+cobString+" for "+ fileName);			        
			try {
    			cob = parseCalendar(cobString, cobPattern);
    		} catch (ParseException e) {
    			log.error("could not parse cob "+cobString+" for "+ fileName);			        
    		}
        }
        return cob;
	}
	/**
	 * Method processFile.
	 * 
	 * @param inputFile
	 */
	private void processFile(File inputFile) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String cmd = status.getSqlLoaderPathName();
		String userid = status.getDbUser() + "/" + status.getDbPassword() + "@" + status.getDbHost() + ".world";
		String sourceSystemName = status.getSourceSystemName();
		String controlfileName = status.getLoaderCtlDir() + File.separatorChar + "import_" + sourceSystemName + ".ctl";
		String datafileName = status.getWorkingDir() + File.separatorChar + sourceSystemName + ".dat";
		String logfileName = status.getWorkingDir() + File.separatorChar + sourceSystemName + ".log";
		String badfileName = status.getWorkingDir() + File.separatorChar + sourceSystemName + ".bad";
        String archivefileName = status.getSourceSystemName() + "." +format.format(new Date());
		BufferedReader br = null;
		MgbConverter converter = null;
		boolean datafileOk = true;  /* Whether we could successfully place the
		    file in work/ */
		try {
			StringBuffer stateMessage = new StringBuffer();
            String lastLineDateFormat = status.getLastLineDateFormat();
            String lastLineFormat = MessageFormat.format(status.getLastLineFormat(),lastLineDateFormat);
            log.trace("Last line expected in format "+lastLineFormat);
			String[] lastLineArray = getLastLineArray(inputFile);
			int numberOfTheoraticalRecords = 0;
			if (lastLineArray != null && lastLineArray.length > 0) {
				try {
					numberOfTheoraticalRecords = Integer.parseInt(lastLineArray[lastLineArrayNumberOfRecordsPos].trim());
				} catch (Exception e) {
					log.error(e.getMessage());
					log.error("Invalid last line in file " + inputFile.getName() + ": must be " + lastLineFormat);
					stateMessage.append("Error in meta data (" + e.getMessage() + "). ");
				}
			} else {
				log.error("Invalid last line in file " + inputFile.getName() + ": must be " + lastLineFormat);
				stateMessage.append("Error in meta data (Format: " + lastLineFormat + "). ");
			}
			Calendar startTime = null;
			Calendar stopTime = null;
			Calendar systemTime = null;
			if (lastLineArray != null && lastLineArray.length > lastLineArraySystemDatePos) {
				try {
					startTime = parseCalendar(lastLineArray[lastLineArrayStartBuissnesTimePos],lastLineDateFormat);
					stopTime = parseCalendar(lastLineArray[lastLineArrayStopBuissnesTimePos],lastLineDateFormat);
					systemTime = parseCalendar(lastLineArray[lastLineArraySystemDatePos],lastLineDateFormat);
					if (systemTime == null) {
						systemTime = new GregorianCalendar();
					}
				} catch (Exception e) {
					log.error(e.getMessage());
					log.error("Invalid last line in file " + inputFile.getName() + ": must be " + lastLineFormat);
					stateMessage.append("Error in meta data (" + e.getMessage() + "). ");
				}
			}
			
            Calendar cob = extractCob(inputFile); 

            /* Before creating a job instance or even moving the file to work/,
			 * check if dependencies are met. */
			if(!checkCobDepends(cob,numberOfTheoraticalRecords))
			{
			    log.info("Not processing file "+inputFile.getName()+" because COB dependencies are not met");
			    return;
			}
			
			/* -- from here on, we're doing some real modifications in the filesystem -- */
			
			File dataFile = new File(datafileName);
			if(dataFile.exists() || !inputFile.renameTo(dataFile)) {
			    datafileOk=false;
			    throw new IOException("Unable to move "+inputFile.getName()+" to "+datafileName);
			}
			int numberOfRealRecords = getLineCountWithoutComments(datafileName, true);
			if (Math.abs(numberOfTheoraticalRecords - numberOfRealRecords) > 0) {
				log.error("Theoratical and real number of records are different in file " + inputFile.getName() + " ("
								+ numberOfTheoraticalRecords
								+ " != "
								+ numberOfRealRecords + ")");
				stateMessage.append("Theoratical and real number of records are different ("
								+ numberOfTheoraticalRecords
								+ " != "
								+ numberOfRealRecords + "). ");
			}
			
            /* -- from here on, we're doing some real modifications in the database -- */
			
            converter = MgbConverterFactory.getMgbConverter(status.getSourceSystem());
            long jobId = converter.createJob();
            
			converter.setBusinessStatistic(numberOfRealRecords, cob, startTime, stopTime, systemTime);
			if (Math.abs(numberOfTheoraticalRecords - numberOfRealRecords) <= status.getMaxCheckDifferences()) {
				String[] cmdArray = {cmd, "userid=" + userid /*keep this in pos 1*/,
						"control=" + controlfileName, "data=" + datafileName,
						"log=" + logfileName, "bad=" + badfileName};
				/* Remove password from logged string (/...@)... kinda hackish though: */
                log.debug("running: "+cmdArray[0]+" "+cmdArray[1].replaceAll(
                        "\\/[^\\@]*\\@","/XXX@")+" "+StringUtils.join(
                                Arrays.copyOfRange(cmdArray,2,cmdArray.length)," "));
				GregorianCalendar loadStartTime = new GregorianCalendar();
				ProcessBuilder pb = new ProcessBuilder(cmdArray);
				pb.redirectErrorStream(true);
				Process p = pb.start();
				// JRE would block because subprocess writes to stdout. So read
				// all output from stdout.
				br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line = null;
				while ((line = br.readLine()) != null) {
					log.debug("SQLLOAD: "+line);
				}
				p.waitFor();
				int exitValue = p.exitValue();
				int rejectedRecords = getLineCountWithoutComments(badfileName,
						false);
				// Fixes a bug in the SQL-Loader. The loader is not able to skip
				// the trailer record that starts with a '#'.
				// That record is allways rejected. So the return value needs to
				// be patched if only lines with a starting '#' are rejected.
				if (exitValue == 2 && rejectedRecords == 0) {
					exitValue = 0;
				}
				log.debug(cmd + " finished with exitCode: " + exitValue);
				String msg = "";
				/* These exit codes are only correct for UNIX */
				switch (exitValue) {
					case 0 :
						msg = "'All rows loaded successfully'";
						break;
					case 1 :
						msg = "'Command line/syntax errors' or 'Oracle errors fatal to SQL*Loader'";
						break;
					case 2 :
						msg = "All/some (" + rejectedRecords
								+ ") rows rejected/discarded' (max = "
								+ status.getMaxLoadErrors() + ")";
						break;
					case 3 :
						msg = "'Operating system errors (such as file open/close and malloc)'";
						break;
					default :
						break;
				}
				log.debug(cmd + ": " + msg);
				GregorianCalendar loadStopTime = new GregorianCalendar();
				converter.setLoaderStatistic(loadStartTime, loadStopTime,
						rejectedRecords);
				// ToDO Temporary accept some errors
				if (exitValue == 0
						|| (exitValue == 2 && rejectedRecords < status
								.getMaxLoadErrors())) {
					log.debug("converting " + status.getSourceSystemName());
					converter.processData();
					if (jobId > -1) {
					    archivefileName = archivefileName + "_" + jobId;
					}
				} else {
					log.error("Error while loading data from file "+ inputFile.getName() + "(" + msg
							+ "). The converter will not run.");
					stateMessage.append("Error while loading data (" + msg
							+ "). ");
					converter.setStatus(JobStateDef.JOB_ERROR_STATUS+": "+stateMessage.toString());
				}
			} else {
				log.error("Difference between theoratical and real number of records is too high in file "+ inputFile.getName() + " (|"
								+ numberOfTheoraticalRecords
								+ "-"
								+ numberOfRealRecords
								+ "| > "
								+ status.getMaxCheckDifferences()
								+ "). The loader/converter will not run.");
				stateMessage
						.append("Difference between theoratical and real number of records is too high (|"
								+ numberOfTheoraticalRecords
								+ "-"
								+ numberOfRealRecords
								+ "| > "
								+ status.getMaxCheckDifferences()
								+ "). The loader/converter will not run.");
				converter.setStatus(JobStateDef.JOB_ERROR_STATUS+": "+stateMessage.toString());
			}
		} catch (IOException e) {
		    log.error("Error while importing data",e);
        } catch (InterruptedException e) {
            log.error("Error while importing data",e);
        } catch (ConverterException e) {
            log.error("Error while importing data",e);
        } finally {
		    if (br != null) {
		        try {
		            br.close();
		        } catch (Exception e) {
		            log.error(e);
		        }
		    }
            if (converter != null) {
                converter.setArchiveFileName(archivefileName);
                converter.setImportFileName(inputFile.getName());
            }
            archivefileName = status.getArchiveDir() + File.separatorChar + archivefileName;
			File datafile = new File(datafileName);
			if (datafileOk&&datafile.exists()) {
				if (!datafile.renameTo(new File(archivefileName	+ ".dat"))) {
				    log.error("Unable to move file "+datafile.getName()+" to "+ archivefileName + ".dat");
				}
			}
			File logfile = new File(logfileName);
			if (logfile.exists()) {
			    if (!logfile.renameTo(new File(archivefileName + ".log"))) {
                    log.error("Unable to move file "+logfile.getName()+" to "+ archivefileName + ".log");			        
			    }
			}
			File badfile = new File(badfileName);
			if (badfile.exists()) {
			    if (!badfile.renameTo(new File(archivefileName + ".bad"))) {
                    log.error("Unable to move file "+logfile.getName()+" to "+ archivefileName + ".bad");                  
			    }
			}
		}
	}

	/**
	 * Method processEmptyFile.
	 * 
	 * @param inputFile
	 */
	private void processEmptyFile(File inputFile) {
        String inputFileName = inputFile.getName();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String sourceSystemName = status.getSourceSystemName();
		String logfileName = status.getWorkingDir() + File.separatorChar + sourceSystemName + ".log";
        String archivefileName = status.getSourceSystemName() + "." +format.format(new Date());
		try {
			Calendar cob = extractCob(inputFile);
			MgbConverter converter = MgbConverterFactory.getMgbConverter(status.getSourceSystem());
	        long jobId = converter.createJob();
			GregorianCalendar loadStartTime = new GregorianCalendar();
			GregorianCalendar loadStopTime = new GregorianCalendar();
			converter.setLoaderStatistic(loadStartTime, loadStopTime,0);
			converter.processData();
	        converter.setBusinessStatistic(0, cob, cob,cob, new GregorianCalendar());
			if (jobId > -1) {
				archivefileName = archivefileName + "_" + jobId;
			}
			if (converter != null) {
				converter.setArchiveFileName(archivefileName);
				converter.setImportFileName(inputFile.getName());
			}
			archivefileName = status.getArchiveDir() + File.separatorChar + archivefileName;
				if (!inputFile.renameTo(new File(archivefileName	+ ".dat"))) {
					log.error("Unable to move file "+inputFile.getName()+" to "+ archivefileName + ".dat");
				}
			File logfile = new File(logfileName);
			if (logfile.exists()) {
				if (!logfile.renameTo(new File(archivefileName + ".log"))) {
					log.error("Unable to move file "+logfile.getName()+" to "+ archivefileName + ".log");			        
				}
			}			
		} catch (Exception e) {
            log.error("could not process empty file "+inputFileName+": "+ e.getMessage());                  
		    if (!inputFile.renameTo(new File(archivefileName + ".bad"))) {
                log.error("Unable to move file "+inputFileName+" to "+ archivefileName + ".bad");                  
		    }
		}
	}

	/**
	 * Method getLineCountWithoutComments.
	 * 
	 * @param fileName
	 * @param hasHeaderRecord
	 * @return int
	 */
	private int getLineCountWithoutComments(String fileName,
			boolean hasHeaderRecord) {
		int rejectedRecords = 0;
		File file = new File(fileName);
		if (file.exists()) {
			BufferedReader f = null;
			String line;
			try {
				f = new BufferedReader(new FileReader(file));
				while ((line = f.readLine()) != null) {
					if (!line.startsWith("#")) {
						rejectedRecords++;
					}
				}
			} catch (IOException e) {
				log.error("Error while reading file " + fileName, e);
			} finally {
			    if (f != null) {
			        try {
                        f.close();
                    } catch (IOException e) {
                        log.error(e);
                    }
			    }
			}
		} else {
		    log.warn("No such file "+fileName);
		}
		if (hasHeaderRecord && rejectedRecords > 0) {
			rejectedRecords--;
		}
		return rejectedRecords;
	}
	/**
	 * Method changeDelimitterString.
	 * 
	 * @param file
	 * @param oldDelim
	 * @param newDelim
	 */
	@SuppressWarnings("unused")
    private void changeDelimitterString(File file, char oldDelim, char newDelim) {
        log.debug("Change delimitter from "+oldDelim+" to "+newDelim+" in file " + file.getName());
		FileReader fr = null;
		FileWriter fw = null;
		int c;
		try {
            File input = new File(file.getName() + ".in");
            if (!file.renameTo(input)) {
                throw new IOException("Unable to move "+file.getName()+" to "+input.getName());
            }
            fr = new FileReader(input);
            
			File output = new File(file.getName() + ".out");
			fw = new FileWriter(output);
			while ((c = fr.read()) != -1) {
				if (oldDelim == c) {
					c = newDelim;
				}
				fw.write(c);
			}
			fr.close();
			fw.close();
			if (!output.renameTo(file)) {
	             throw new IOException("Unable to move "+output.getName()+" to "+file.getName());
			}
			if (!input.delete()) {
                throw new IOException("Unable to delete "+input.getName());
			}
		} catch (IOException e) {
			log.error("Error while reading file " + file.getName(), e);
		} finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                log.error(e);
            }
		}
	}
	/**
	 * Method getLastLineArray.
	 * 
	 * @param file
	 * @return String[]
	 */
	private String[] getLastLineArray(File file) {
		BufferedReader f = null;
		String line;
		String[] result = null;
		try {
			f = new BufferedReader(new FileReader(file));
			while ((line = f.readLine()) != null) {
				if (line.startsWith("#")) {
                    if (line.indexOf(',') > 0 && line.indexOf(';') > 0) {
                        result = line.substring(1).split("[,;]");
                    } else 
					if (line.indexOf(',') > 0) {
						result = line.substring(1).split(",");
					} else if (line.indexOf(';') > 0) {
						result = line.substring(1).split(";");
					} else {
						result = line.substring(1).split("[\t\n\f\r]");
					}
				}
			}
			f.close();
		} catch (IOException e) {
			log.error("Error while reading file " + file.getName(), e);
		} finally {
		    try {
		        if (f != null) {
		            f.close();
		        }
		    } catch (IOException e) {
		        log.error(e);
		    }
		}
		return result;
	}
	
	/**
     * Checks whether all dependencies to process the COB
     * <code>stopBusinessDate</code> are met. This means checking whether a job
     * for the same COB and the source system code
     * {@link MgbImportThreadStatus#getSourceSystemCobDepends()} that is not failed
     * or running exists. If no such dependency is configured, just returns
     * <code>true</code>.
     * 
     * @return true If all dependencies are fulfilled and the file can be processed;
     * false otherwise.
     */
	protected boolean checkCobDepends(Calendar cob,int numberOfRecords)
	{
	    Session sess = null;
	    try
	    {
    	    if(status.getSourceSystemCobDepends()!=null)
    	    {
    	        String sourceSystemCobDepends = status.getSourceSystemCobDepends().trim();
    	        if(sourceSystemCobDepends.length()>0)
    	        {
                    /* We do depend on another source system for the COB, so
                     * now be strict about stopBusinessDate -- if we have data */
                    if(cob==null)
                    {
                        log.error("File has no valid cob -- cannot process");
                        return false;
                    }
    	            sess = StoreSingleton.getUniqueInstance().openSession();
    	            MgbFinderImpl finder = new MgbFinderImpl(sess);
    	            Collection<SourceSystemImpl> ssyslist = finder.findSourceSystems(null);
    	            SourceSystemImpl ssys=null;
    	            for(SourceSystemImpl s : ssyslist) if(s.getCode().equals(sourceSystemCobDepends)) ssys=s;
    	            if(ssys==null)
    	            {
    	                log.warn("COB data depends on unknown source system "+sourceSystemCobDepends+
    	                        ", ignoring");
    	                return true;
    	            }
    	            JobSearchParams params = new JobSearchParams(null,
    	                    ssys,cob,
    	                    new String[]{JobStateDef.JOB_OK_STATUS,
    	                    JobStateDef.JOB_CLOSED_STATUS,
    	                    JobStateDef.JOB_SPK_CLOSED_STATUS}, false); 
    	            Collection<JobImpl> oldJobs = finder.findJobs(params);
    	            log.debug("COB depends check has found "+oldJobs.size()+" matching job(s)");
    	            return oldJobs.size()>0;
    	        }
    	    }
    	    return true;
	    }
	    catch(PersistenceException e0)
	    {
	        log.error("Cannot check for COB depends "+e0,e0);
	        return false;
        }
        finally
        {
            try
            {
                if(sess != null)
                {
                    sess.close();
                }
            }
            catch(PersistenceException he)
            {
            }
        }
	}
		
	/**
	 * Method isStopRequested.
	 * 
	 * @return boolean
	 */
	public boolean isStopRequested() {
		return status.isStopRequested();
	}
	/**
	 * Method setStopRequested.
	 * 
	 * @param b
	 */
	public void setStopRequested(boolean stopFlag) {
		status.setStopRequested(stopFlag);
		if (stopFlag) {
			status.setStatus(MgbImportThreadStatus.STOP_REQUESTED);
		}
	}
	/**
	 * Method parseCalendar parses a datestring.
	 * 
	 * @param dateString
	 * @return Calendar
	 * @throws ParseException
	 */
	private Calendar parseCalendar(String dateString,String dateFormat) throws ParseException {
		if (dateString != null && dateString.length() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            sdf.set2DigitYearStart((new GregorianCalendar(1950,01,01)).getTime());
            sdf.setLenient(false);
		    GregorianCalendar cal = new GregorianCalendar();
		    cal.setTime(sdf.parse(dateString.trim()));
            return cal;
		}
        return null;
	}
	/**
	 * Returns the config.
	 * 
	 * @return MgbImportThreadStatus
	 */
	public MgbImportThreadStatus getStatus() {
		return status;
	}
}