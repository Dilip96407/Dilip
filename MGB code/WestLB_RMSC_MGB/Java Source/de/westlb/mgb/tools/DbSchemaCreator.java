package de.westlb.mgb.tools;

import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.hibernate.StoreImpl;


public class DbSchemaCreator {
	public void createSchema() throws PersistenceException {
		StoreImpl store = new StoreImpl("server.properties");
		store.exportSchema(true);
	}
	
	public static void main(String args[]) throws Exception {
		new DbSchemaCreator().createSchema();
	}
}
