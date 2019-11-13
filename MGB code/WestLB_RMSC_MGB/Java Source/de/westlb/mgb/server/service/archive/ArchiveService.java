package de.westlb.mgb.server.service.archive;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;

import de.westlb.mgb.MgbException;
import de.westlb.mgb.client.server.TradeService;
import de.westlb.mgb.model.impl.JobImpl;
import de.westlb.mgb.model.impl.ReportImageImpl;
import de.westlb.mgb.model.impl.TradeHistEntryImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.StoreSingleton;
import de.westlb.mgb.persistence.Transaction;

/**
 * Contains the service methods for the archiving of jobs.
 * 
 * @author boernema
 *
 */
public class ArchiveService {
	private static Logger logger = Logger.getLogger(ArchiveService.class);
	
	/**
	 * Writes the data of an job to an archive file.
	 * 
	 * @param session
	 * @param job
	 * @param outputStream
	 * @throws IOException
	 * @throws PersistenceException
	 */
	public void writeArchive(Session session, JobImpl job, OutputStream outputStream) throws IOException, PersistenceException {
        Mapping  mapping = new Mapping(getClass().getClassLoader());

        try {
			mapping.loadMapping(getClass().getResource("/archive_mapping.xml" ));
	        Marshaller marshaller = new Marshaller(new OutputStreamWriter(outputStream));
	        marshaller.setMapping(mapping);
			marshaller.marshal(job);
		} catch (MarshalException e) {
			logger.error(e);
			throw new RuntimeException("Archiving failed with unexpected Exception");
		} catch (ValidationException e) {
			logger.error(e);
			throw new RuntimeException("Archiving failed with unexpected Exception");
		} catch (MappingException e) {
			logger.error(e);
			throw new RuntimeException("Archiving failed with unexpected Exception");
		} catch (IOException e) {
			logger.error(e);
			throw e;
		}
	}
	
	/**
	 * Executes the archive process for MGB jobs hat are older than a specific
	 * number of dates. The time to run for the method might be limited to
	 * a specific number of minutes.
	 * 
	 * @param session
	 * @param daysToKeep 
	 * @param maxExecTimeInMinutes
	 * @param outputDirPath
	 * @throws Exception
	 */
	public void archiveJobs(int daysToKeep, int maxExecTimeInMinutes, 
			String outputDirPath, boolean delete) throws MgbException {
	    Session session = null;
		long startTimeInMillis = System.currentTimeMillis();
		Calendar expiredDate = Calendar.getInstance();
		expiredDate.add(Calendar.DATE, -Math.abs(daysToKeep));
 		List<Long> jobList;
		try {
		    session = StoreSingleton.getUniqueInstance().openSession();
			jobList = findJobIdsDueToArchive(session, expiredDate);
		} catch (PersistenceException e) {
			logger.error(e);
			throw (e);
		} finally {
		    if (session != null) {
                try {
                    session.close();
                } catch (PersistenceException e) {
                }
            }
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("Starting to archive " + jobList.size() + " jobs that are older than "+expiredDate.getTime());
		}
		
		Iterator<Long> it = jobList.iterator();
		boolean stop = false;
		while (it.hasNext() && !stop) {
		    try {
			Long jobId = it.next();
			session = StoreSingleton.getUniqueInstance().openSession();
			JobImpl job = (JobImpl)session.select(JobImpl.class, jobId);
			File jobFile = new File(outputDirPath + File.separator + jobId + ".xml");
            logger.debug("Starting to archive job to " + jobFile.getAbsolutePath());
			FileOutputStream jobStream = null;
			BufferedOutputStream bufferedJobStream = null;
			try {
				jobStream = new FileOutputStream(jobFile);
				bufferedJobStream = new BufferedOutputStream(jobStream);
				writeArchive(session, job, bufferedJobStream);
	            logger.debug("Finished to archive job to " + jobFile.getAbsolutePath());
			} catch (PersistenceException e) {
				logger.error("PersistenceException while archiving job " + jobId, e);
				break;
            } catch (FileNotFoundException e) {
                logger.error("Can not open archive file " + jobFile.getPath(), e);
                break;
			} catch (IOException e) {
				logger.error("Can not write archive file " + jobFile.getPath(), e);
				break;
			} finally {
			    try {
			        if (bufferedJobStream != null) {
			            bufferedJobStream.close();
			        }
			        if (jobStream != null) {
			            jobStream.close();
			        }
			    } catch (IOException e) {
			        logger.error(e);
			    }
			}
			
			
            FileOutputStream attachmentStream = null;
            File attachmentFile = null;
			try {
			    List<ReportImageImpl> attachmentList = findAttachmentsForJob(session, job);
			    for (Iterator<ReportImageImpl> iterator = attachmentList.iterator(); iterator.hasNext();) {
                    ReportImageImpl reportImage = iterator.next();
                    attachmentFile = new File(outputDirPath + File.separator + reportImage.getUniqueFileName());
                    logger.debug("Starting to archive attachment to " + attachmentFile.getAbsolutePath());
                    attachmentStream = new FileOutputStream(attachmentFile);
                    Blob blob = reportImage.getImage();
                    if (blob != null) {
                        attachmentStream.write(blob.getBytes(1, (int) blob.length()));
                    } else {
                        logger.warn("Wrote an empty attachment " + attachmentFile.getAbsolutePath());
                    }
                    logger.debug("Finshed to archive attachment to " + attachmentFile.getAbsolutePath());
			    }
            } catch (PersistenceException e) {
                logger.error("PersistenceException while archiving the attachments of job " + jobId, e);
                break;
            } catch (FileNotFoundException e) {
                logger.error("Can not open attachment file " + attachmentFile.getPath(), e);
                break;
            } catch (IOException e) {
                logger.error("Can not write attachment file " + attachmentFile.getPath(), e);
                break;
            } catch (SQLException e) {
                logger.error("Can not get BLOB data from ReportImageImpl", e);
                break;
            } finally {
                try {
                    if (attachmentStream != null) {
                        attachmentStream.close();
                    }
                } catch (IOException e) {
                    logger.error(e);
                }
            }
			
			if (delete) {
			    try {
			        logger.debug("Deleting job " + jobId);
			        Transaction t = session.beginTransaction();
			        new TradeService().deleteAllTradesOfJob(session, job);
			        job.setArchived(true);
			        job.setArchiveFile(jobFile.getName());
			        session.save(job);
			        t.commit();
			    } catch (PersistenceException pe) {
                    logger.debug("Error while deleting job " + jobId, pe);
			    }
			}

			stop = (System.currentTimeMillis() - startTimeInMillis ) / (1000*60) > maxExecTimeInMinutes;
			
		    } finally {
		        if (session != null) {
	                try {
	                    session.close();
	                } catch (PersistenceException e) {
	                }
	            }
		    }
		}
		
		if (stop) {
			logger.info("Archiving of MGB jobs stopped after " + maxExecTimeInMinutes + " minutes");
		} else {
			logger.info("Archiving job MGB jobs finished!");
		}
		
	}
	
	/**
	 * Finds the list of job ids that are older than a specific 
	 * date and are due to be archived.
	 * 
	 * @param session
	 * @param date
	 * @return
	 * @throws PersistenceException
	 */
    private List<Long> findJobIdsDueToArchive(Session session, Calendar date) throws PersistenceException {
		Query query = session.createQuery("select job.id from "+JobImpl.class.getName()+" job where job.stopBusinessTime < :date and job.archived='N' order by job.stopBusinessTime asc");
		query.setParameter("date", date);
		return query.list();
	}

	/**
     * Finds the list of attachmants for a specific job 
     * 
     * @param session
     * @param job
     * @return
     * @throws PersistenceException
     */
    private List<ReportImageImpl> findAttachmentsForJob(Session session, JobImpl job) throws PersistenceException {
        Query query = session.createQuery("select hist.reportImage from "+TradeHistEntryImpl.class.getName()+" hist where hist.trade.job = :job");
        query.setParameter("job", job);
        return query.list();
    }

}
