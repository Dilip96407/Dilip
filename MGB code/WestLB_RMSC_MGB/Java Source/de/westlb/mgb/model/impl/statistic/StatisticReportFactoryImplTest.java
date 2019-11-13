/*
 * Created on Apr 5, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.model.impl.statistic;

import java.util.Properties;

import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.StoreSingleton;
import junit.framework.TestCase;

/**
 * @author WSY4148
 *
 */
public class StatisticReportFactoryImplTest extends TestCase {

	private Session session = null;
	
    /**
     * 
     */
    public StatisticReportFactoryImplTest() {
        super();
    }

    /**
     * @param arg0
     */
    public StatisticReportFactoryImplTest(String arg0) {
        super(arg0);
    }
    
    /* (Kein Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {    	
    	
    	Properties properties = new Properties();
    	properties.put("hibernate.dialect", "net.sf.hibernate.dialect.OracleDialect");
		properties.put("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver");
		properties.put("hibernate.connection.url", "jdbc:oracle:oci8:@(description=(address=(host=wlb07077)(protocol=tcp)(port=1521))(connect_data=(sid=RMMPTST1)))");
		properties.put("hibernate.connection.username", "MGB_DEV");
		properties.put("hibernate.connection.password", "MGB_DEV");
		properties.put("mgb.hibernate.mappingfiles", "hibernate_mappings.properties");
		
		session = StoreSingleton.getUniqueInstance().openSession();
    }

    /* (Kein Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
		session.close();
    }

}
