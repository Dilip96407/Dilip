package de.westlb.mgb.client.server;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.TestCase;
import de.westlb.mgb.client.server.vo.CheckStateVo;
import de.westlb.mgb.client.server.vo.DataSelectionVo;
import de.westlb.mgb.client.server.vo.PriceVo;
import de.westlb.mgb.client.server.vo.RequestVo;
import de.westlb.mgb.client.server.vo.StornoGroupVo;
import de.westlb.mgb.client.server.vo.TradeSearchParamsVo;
import de.westlb.mgb.client.server.vo.TradeSearchResultEntryVo;
import de.westlb.mgb.model.definition.MarketDataSourceDef;
import de.westlb.mgb.model.definition.PriceDef;
import de.westlb.mgb.model.impl.MandantImpl;

/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MgbSoapBindingImplTest extends TestCase {

	/**
	 * Constructor for MgbSoapBindingImplTest.
	 */
	public MgbSoapBindingImplTest() {
		super();
	}

	/**
	 * Constructor for MgbSoapBindingImplTest.
	 * @param arg0
	 */
	public MgbSoapBindingImplTest(String arg0) {
		super(arg0);
	}

	public void testLogin() throws RemoteException {
		Mgb mgb = MgbSoapBindingProxyImpl.getProxy();
		mgb.login("wsy4148", null, null);
	}


	public void testFindTrades() throws RemoteException {
		Mgb mgb = MgbSoapBindingProxyImpl.getProxy();
		mgb.login("wsy4148", null, null);
		DataSelectionVo data = mgb.getDataSelection();

		TradeSearchParamsVo param = new TradeSearchParamsVo();
		Long[] jobIds = new Long[data.getSelectedJobs().length];
		for (int i=0;i<jobIds.length; i++) {
			jobIds[i] = (data.getSelectedJobs()[i]).getJobId();
		}
		param.setJobIds(jobIds);
		param.setMandantCode(data.getMandantCode());
		//			param.setFromDate(data.getFromDate());
		//			param.setToDate(data.getToDate()); 
		TradeSearchResultEntryVo[] trades = mgb.findTrades(param);

		assertTrue("No data for yesterday??", trades != null && trades.length > 0);
	}

	public void testCheckState() throws RemoteException {
		Mgb mgb = MgbSoapBindingProxyImpl.getProxy();
		mgb.login("wsy4148", null, null);
		DataSelectionVo data = mgb.getDataSelection();

		TradeSearchParamsVo param = new TradeSearchParamsVo();
		Long[] jobIds = new Long[data.getSelectedJobs().length];
		for (int i=0;i<jobIds.length; i++) {
			jobIds[i] = (data.getSelectedJobs()[i]).getJobId();
		}
		param.setJobIds(jobIds);
		param.setMandantCode(data.getMandantCode());
		@SuppressWarnings("unused")
        TradeSearchResultEntryVo[] trades = mgb.findTrades(param);

		CheckStateVo state = mgb.getCheckState();

		assertTrue("No data for yesterday??", state != null && state.getNoTradesToCheck() > 0);
	}

	//   public void testImageSave() throws RemoteException {
	//    	Mgb mgb = new MgbSoapBindingImpl();
	//    	mgb.login("wsy4148", null);
	//    	DataSelectionVo data = mgb.getDataSelection();
	//		
	//		byte[] testbytes = "jens".getBytes();
	//
	//		TradeSearchParamsVo param = new TradeSearchParamsVo();
	//		param.setJobIds(data.getJobIds());
	//		param.setMandantCode(data.getMandantCode());
	//		TradeSearchResultEntryVo[] trades = mgb.findTrades(param);
	//
	//		assertTrue("No data for yesterday??", trades != null && trades.length > 0);
	//		
	//		TradeHistoryEntryVo hist = new TradeHistoryEntryVo();
	//		hist.setStateCode(ManualStateImpl.MAN_OK);
	//		hist.setType(TradeHistEntryImpl.MANUAL);
	//		hist.setInternalTradeId(trades[0].getId());
	//		hist.setAttachmentName("tt");
	//
	//		mgb.saveTradeState(hist, testbytes);
	//
	//   }

	//   public void testImageGet() throws RemoteException {
	//    	Mgb mgb = new MgbSoapBindingImpl();
	//    	mgb.login("wsy4148", null);
	//    	DataSelectionVo data = mgb.getDataSelection();
	//		
	//		byte[] testbytes = "jens".getBytes();
	//
	//		TradeSearchParamsVo param = new TradeSearchParamsVo();
	//		param.setJobIds(data.getJobIds());
	//		param.setMandantCode(data.getMandantCode());
	//		TradeSearchResultEntryVo[] trades = mgb.findTrades(param);
	//
	//		assertTrue("No data for yesterday??", trades != null && trades.length > 0);
	//		
	//		TradeHistoryEntryVo[] tradeHist = mgb.getHistory(trades[0].getId());
	//		Long id = tradeHist[0].getAttachmentId();
	//		long size = mgb.startReadAttachment(id);
	//		byte[] result = mgb.readAttachment(id, 0, size);
	//		assertTrue("Different bytes.", result != null && Arrays.equals(result, testbytes));
	//    }

	public void testPrice() throws RemoteException {
		Mgb mgb = MgbSoapBindingProxyImpl.getProxy();
		mgb.login("wsy4148", null, null);
		@SuppressWarnings("unused")
        DataSelectionVo data = mgb.getDataSelection();

		PriceVo price = new PriceVo();
		price.setMinPrice(1213.4f);
		price.setMaxPrice(1213.4f);
		price.setPriceType(PriceDef.SINGLE);
		price.setPriceDate(new GregorianCalendar());
		price.setRequestId(Long.valueOf(2));

		RequestVo request = new RequestVo();
		request.setJobId(Long.valueOf(1));
		request.setPriceSource(MarketDataSourceDef.BLOOMBERG);
		request.setRequestId(Long.valueOf(2));

		mgb.savePrice(request, price);

		PriceVo result = mgb.getPrice(request);

		assertTrue("Different dates.", result != null && result.getPriceDate().equals(price.getPriceDate()));
	}

	public void testStornoGroup() throws RemoteException {
		Mgb mgb = MgbSoapBindingProxyImpl.getProxy();
		mgb.login("wsy4148", null, null);
		int[] ia = { 30, 45, 48, 51 };

		for (int j = 0; j < ia.length; j++) {
			StornoGroupVo[] data = mgb.getStornoGroup(Long.valueOf(ia[j]));
			for (int i = 0; i < data.length; i++) {
				System.out.println(
					data[i].getFieldName()
						+ ": "
						+ data[i].getFieldValue()
						+ "("
						+ data[i].getTradeId()
						+ ") "
						+ data[i].getReferenceValue()
						+ " ("
						+ data[i].getReferenceTradeId()
						+ ")");
			}
		}

		assertTrue("Different dates.", true);
	}

	public void testDeleteJob() throws RemoteException {
		Mgb mgb = MgbSoapBindingProxyImpl.getProxy();
		mgb.login("wsy4148", null, null);
		mgb.deleteJob(Long.valueOf(1));
		

		assertTrue("Different dates.", true);
	}

}
