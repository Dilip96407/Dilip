package de.westlb.mgb.exchange;

import java.io.File;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class MgbExcelImport {

	public static void main(String[] args) {
		String inputdir = "C:\\tmp";
		String inputfilename = "PAG_daily_<YYYYMMDD>.xls";
		String userId  ="d021535";
		
		Object datesuffix = new SimpleDateFormat("yyyyMMdd").format(new Date());
		inputfilename=inputfilename.replaceAll("<YYYYMMDD>", datesuffix.toString());
		
		String infilename= StringUtils.join(new Object[] {inputdir, inputfilename}, File.separator);
		Path path = Paths.get(infilename);

		if (Files.exists(path)) {
			
		
			try {
				

				
				MgbPagCommentLoader loader = new MgbPagCommentLoader(userId,"PORTIGON","PAG");
				MgbPagExcelReader poiReader = new MgbPagExcelReader(loader);
				poiReader.read(infilename);
				
				if (poiReader.getErrorCount() == 0) {
			        File oldfile =new File(infilename);
			        Object timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			        String archivefilename= StringUtils.join(new Object[] {inputdir, "archive", inputfilename}, File.separator);
			        File newfile =new File(archivefilename+ "_" + timestamp.toString());
	
			        if(oldfile.renameTo(newfile)){
			            System.out.println("File renamed");
			        }else{
			            System.out.println("The file can't be renamed");
			        }
				} else {
					System.out.println(poiReader.getResultMessage());
					System.out.println(poiReader.getErrorDetails());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	
	

}
