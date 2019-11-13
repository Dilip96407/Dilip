package de.westlb.mgb.model.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import de.westlb.mgb.model.Entity;
import de.westlb.mgb.model.definition.DualControlJobOperationDef;
import de.westlb.mgb.model.definition.DualControlJobProcessorDef;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.StoreSingleton;
import de.westlb.mgb.persistence.Transaction;
import de.westlb.mgb.util.DualControlJobEntityAccessorFilter;
import de.westlb.mgb.util.ObjectComparator;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DualControlJobProcessorImpl implements DualControlJobProcessorDef {

	static Logger logger = Logger.getLogger(DualControlJobProcessorImpl.class);

	public void delete(Session sess, Object o, EmployeeImpl employee) throws PersistenceException {
		save(sess, o, employee, DualControlJobOperationDef.DELETE);
	}

	public void update(Session sess, Object o, EmployeeImpl employee) throws PersistenceException {
		save(sess, o, employee, DualControlJobOperationDef.UPDATE);
	}

	public void insert(Session sess, Object o, EmployeeImpl employee) throws PersistenceException {
		save(sess, o, employee, DualControlJobOperationDef.CREATE);
	}

	private void save(Session sess, Object o, EmployeeImpl employee, String operationType) throws PersistenceException {
		DualControlJobImpl dualControlJob = null;
		try {
			Transaction t = sess.beginTransaction();
			dualControlJob = (DualControlJobImpl) sess.create(DualControlJobImpl.class);
			dualControlJob.setDescription(o.getClass().getName());
			dualControlJob.setEntityTypeName(o.getClass().getName());
			if ( !DualControlJobOperationDef.CREATE.equals(operationType) ) {
				dualControlJob.setEntity((Entity) o);
			}
			dualControlJob.setState(OPEN);
			dualControlJob.setOperation(operationType);
			dualControlJob.setRequestedBy(employee);
			dualControlJob.setPersistentObject(sess.getHibernateSession().getLobHelper().createBlob(objectToBytes(o)));
			t.commit();
			logger.debug("Saved the " + operationType + "-task " + dualControlJob.getId() + " to change a " + o.getClass().getName() + ".");
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.CREATE_OBJ, dualControlJob, e);
		}
	}

	public void commit(Session sess, DualControlJobImpl dualControlJob, EmployeeImpl employee) throws PersistenceException {
		if (dualControlJob.getCommitedBy() != null || dualControlJob.getRequestedBy().equals(employee)) {
			throw new PersistenceException(
				"Only jobs that have not been commited jet and that are requested by a different employee than '" + employee + "' can be commited.");
		}
		try {
			Transaction t = sess.beginTransaction();
			Object o = streamToObject(dualControlJob.getPersistentObject().getBinaryStream());
			if (o instanceof Entity) {
				ObjectComparator comperator = new ObjectComparator();
				comperator.setFilter(DualControlJobEntityAccessorFilter.getInstance());
				
				Entity entity = (Entity) o;
				if (DualControlJobOperationDef.DELETE.equals(dualControlJob.getOperation())) {
					sess.evict(dualControlJob.getEntity());
					dualControlJob.setEntity(null);
					sess.save(dualControlJob);
					saveChanges(sess, dualControlJob, comperator.getAccessorList(entity), entity, null);
					sess.delete(entity);
				} else if (DualControlJobOperationDef.UPDATE.equals(dualControlJob.getOperation())) {
					sess.evict(dualControlJob.getEntity());
					Entity old = sess.select(entity.getClass(), entity.getId());
					saveChanges(sess, dualControlJob, comperator.getChangedFieldsAccessorList(entity, old), old, entity);
					sess.evict(old);
					sess.update(entity);
				} else {
					if (entity.getId() instanceof Long) {
						// To insert the given Object as a new entry, the hibernate-identifier is set to null,
						// but only if the id is a Long. Any more complex identifier is assumed to be set correctly by the user/application
						entity.setNullId();
					}
					saveChanges(sess, dualControlJob, comperator.getAccessorList(entity), null, entity);
					sess.save(entity);
				}
				dualControlJob.setCommitedBy(employee);
				dualControlJob.setState(EXECUTED);
				sess.update(dualControlJob);
				logger.debug("Commited the " + dualControlJob.getOperation() + "-task " + dualControlJob.getId() + " to change a " + o.getClass().getName() + ".");
			} else {
				throw new PersistenceException(PersistenceException.SAVE_OBJ, o);
			}
			t.commit();
		} catch (PersistenceException pe) {
			// save the error state
			Session session = StoreSingleton.getUniqueInstance().openSession(employee.getMandant(), employee.getNtId());
			try {
				Transaction tt = session.beginTransaction();
				session.refresh(dualControlJob);
				dualControlJob.setState("ERROR: " + pe.getMessage());
				session.update(dualControlJob);
				tt.commit();
			} catch (PersistenceException pe1) {
				logger.error(pe1);
			} finally {
				session.close();
			}
			throw new PersistenceException(PersistenceException.CREATE_OBJ, dualControlJob, pe);
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.CREATE_OBJ, dualControlJob, e);
		}
	}

	private void saveChanges(Session sess, DualControlJobImpl dualControlJob, Method[] methods, Object oldObject, Object newObject) throws PersistenceException {
		for (int i = 0; i < methods.length; i++) {
			ChangeRevisionLogImpl change = (ChangeRevisionLogImpl)sess.create(ChangeRevisionLogImpl.class);
			change.setFieldName(methods[i].getName());
			change.setDualControlJob(dualControlJob);
			try {
			if (newObject != null) {
				Object retValNew = methods[i].invoke(newObject);
				if (retValNew != null) {
					change.setFieldValueNew(retValNew.toString().substring(0,Math.min(ChangeRevisionLogImpl.FIELD_VALUE_MAX_LENGTH,retValNew.toString().length())));
				}
			}
			if (oldObject != null) {
				Object retValOld = methods[i].invoke(oldObject);
				if ( retValOld != null ) {
					change.setFieldValueOld(retValOld.toString().substring(0,Math.min(ChangeRevisionLogImpl.FIELD_VALUE_MAX_LENGTH,retValOld.toString().length())));
				}
			}
			} catch(InvocationTargetException ite) {
				throw new PersistenceException(PersistenceException.CREATE_OBJ, dualControlJob, ite);
			} catch(IllegalAccessException iae) {
				throw new PersistenceException(PersistenceException.CREATE_OBJ, dualControlJob, iae);
			}				
			sess.save(change);
		}	
	}
	private byte[] objectToBytes(Object object) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(baos);
		os.writeObject(object);
		return baos.toByteArray();
	}

	@SuppressWarnings("unused")
    private Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream is = new ObjectInputStream(bais);
		return is.readObject();
	}

	public static Object streamToObject(InputStream is) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(is);
		return ois.readObject();
	}

}
