package de.westlb.mgb.client.reports;

import java.io.FileOutputStream;

import junit.framework.TestCase;
import de.westlb.mgb.client.mask.view.shared.FileHandler;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.TradeSearchParamsVo;
import de.westlb_systems.xaf.util.PropertyFactory;

/**
 * @author wsy4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ReportManagerTest extends TestCase {

    /**
     * Constructor for ReportFactoryTest.
     */
    public ReportManagerTest() {
        super();
    }

    /**
     * Constructor for ReportFactoryTest.
     * @param arg0
     */
    public ReportManagerTest(String arg0) {
        super(arg0);
    }

    /**
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
		PropertyFactory.put("mgb.svc_provider", "Faked");
        MgbServiceFactory.getService().login("d055625", null, null);
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    /*
     * Tests the create of report of late trades
     */
    public void atestCreateReport_TradesLate() throws Exception {
    	TradeSearchParamsVo searchParams = new TradeSearchParamsVo();
		searchParams.setIsManualCheckRequired(Boolean.TRUE);
		Object[] beanArray = MgbServiceFactory.getService().findTrades(searchParams);
    	Report report = ReportManager.getInstance().createReport(ReportManager.REPORT_TRADES_RECLAMATION_LATE, beanArray);
        byte[] export = ReportManager.getInstance().exportReport(report);        
		new FileHandler(null).viewFile(export, "test1.pdf");        
    }
    
        /*
     * Tests the create of report of late trades
     */
    public void testCreateReport_TraderReclamation() throws Exception {
    	TradeSearchParamsVo searchParams = new TradeSearchParamsVo();
		searchParams.setIsManualCheckRequired(Boolean.TRUE);
		Object[] beanArray = MgbServiceFactory.getService().findEmployeesByReclamationRequired();
    	Report report = ReportManager.getInstance().createReport(ReportManager.REPORT_TRADER_RECLAMATION, beanArray);
        byte[] export = ReportManager.getInstance().exportReport(report);        
        FileOutputStream outputStream = new FileOutputStream("c:/tmp/zztest2.pdf");
        outputStream.write(export);
        outputStream.close();
		new FileHandler(null).viewFile(export, "test2.pdf");        
    }

}
