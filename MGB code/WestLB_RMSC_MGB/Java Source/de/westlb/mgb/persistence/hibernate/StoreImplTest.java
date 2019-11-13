package de.westlb.mgb.persistence.hibernate;

import java.util.List;
import java.util.Properties;

import junit.framework.TestCase;
import de.westlb.mgb.model.impl.EmployeeImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.Transaction;

/**
 * Hibernate store implementation test.
 * 
 * @author Manfred Boerner
 */
public class StoreImplTest extends TestCase {
	private StoreImpl store;

	/**
	 * Constructor for StoreImplTest.
	 * @param name
	 */
	public StoreImplTest(String name) {
		super(name);
	}

	/**
	 * @see TestCase#setUp()
	 */
	@Override
    protected void setUp() throws Exception {
		super.setUp();
		Properties properties = new Properties();
		properties.setProperty("mgb.store.classname", "de.westlb.mgb.persistence.hibernate.StoreImpl");
		properties.setProperty("mgb.hibernate.mappingfiles", "/hibernate_mappings.xml");
		properties.setProperty("mgb.hibernate.properties", "/hibernate.properties");
		store = new StoreImpl(properties);
	}

	/**
	 * @see TestCase#tearDown()
	 */
	@Override
    protected void tearDown() throws Exception {
		super.tearDown();
	}
	
    public void test_Select() throws PersistenceException {
		Session session = store.openSession();
		@SuppressWarnings("unused")
        List<EmployeeImpl> employees = (List<EmployeeImpl>)session.select(EmployeeImpl.class);
		Transaction trx = session.beginTransaction();
		trx.commit();
		session.close();
	}

}
