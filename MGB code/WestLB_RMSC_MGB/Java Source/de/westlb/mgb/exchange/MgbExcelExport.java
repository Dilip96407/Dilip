package de.westlb.mgb.exchange;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.text.SimpleDateFormat;

public class MgbExcelExport {

	public static void main(String[] args) {
		
		
		
		
		try {
			String userId = "d021535";
			String outputfilename = "PAG_daily_<YYYYMMDD>.xls";
			//String outputfilename = "PAG_weekly_<YYYYMMDD>.xls";
			String outputdir = "c:\\tmp";
			String reportLocation="PORTIGON";
			String client =  "PAG";
			boolean manCheckOnly = true;
			//boolean manCheckOnly = false;
			boolean useCreationDate = true;
			//int daiscount = 300;
			int daiscount = 300;
			

			
			Object timestamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
			outputfilename=outputfilename.replaceAll("<YYYYMMDD>", timestamp.toString());
			//String outfilename= StringUtils.join(new Object[] {outputdir, outputfilename}, File.separator);
			
			MgbExcelExchange exch = new MgbExcelExchange(userId,"DVG");
			exch.extractTrades(manCheckOnly, useCreationDate, daiscount, outputdir, outputfilename, reportLocation, client);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
//		Session sess;
//		try {
//			//sess = StoreSingleton.getUniqueInstance().openBatchSession();
//			Calendar from = Calendar.getInstance();
//			from.add(Calendar.DAY_OF_MONTH, -360);
//			
//			MgbExcelExchange exporter = new MgbExcelExchange();
//			Workbook workBook = exporter.createWorkBookForClient(from, Calendar.getInstance(), true, false, true, "PORTIGON", "PAG");
//			//workBook.
//			
//			workBook.write(new FileOutputStream(new File("C:\\tmp\\mgb_test.xls")));
//			//sess.close();
//		//} catch (PersistenceException e) {
//		//	// TODO Auto-generated catch block
//		//	e.printStackTrace();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}

}