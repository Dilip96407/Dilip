/**
 * MgbSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package de.westlb.mgb.client.server;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.AccessException;
import java.util.Iterator;

import org.apache.log4j.Logger;

import de.westlb.mgb.model.impl.AccessControlCheckerImpl;
import de.westlb.mgb.model.impl.EmployeeImpl;
import de.westlb.mgb.model.impl.RoleImpl;
import de.westlb.mgb.model.impl.UserRoleImpl;

public class MgbSoapBindingProxyImpl implements InvocationHandler, Serializable {

    private static final long serialVersionUID = 4542898123187061795L;

    static Logger logger = Logger.getLogger(MgbSoapBindingProxyImpl.class);

	private final MgbServiceImpl delegate;

	private AccessControlCheckerImpl accessControlChecker;

	private MgbSoapBindingProxyImpl(boolean checkAccessControl) {
		this.delegate = new MgbServiceImpl();
		if (checkAccessControl) {
			this.accessControlChecker = new AccessControlCheckerImpl(delegate);
		}
	}

	/**
	 * @see java.lang.reflect.InvocationHandler#invoke(Object, Method, Object[])
	 */
	@Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if ("isBusy".equals(method.getName())) {
			return method.invoke(delegate, args);
		}
        EmployeeImpl user = delegate.getUser();
        String userName = user == null ? null : user.getNtId();        
        String methodName = method.getName() + "()";
        if ("keepAlive".equals(method.getName())) {
            logger.debug("Calling directly " + methodName + " for "+userName);
            return method.invoke(delegate, args);
        }
		try {
			delegate.openSession();
			
			if (accessControlChecker != null) {
				RoleImpl[] roles = null;
				if (user != null && user.getRoles() != null) {
					roles = new RoleImpl[user.getRoles().size()];
					int i = 0;
					Iterator<UserRoleImpl> iter = user.getRoles().iterator();
					while (iter.hasNext()) {
						roles[i] = iter.next().getType();
						i++;
					}
				}
							
				if ( !accessControlChecker.checkAccess(method.getName(), roles) ) {
					logger.error("Access denied to "+methodName+ " for "+userName);
					throw new AccessException("Access denied to "+methodName+ " for "+userName);
				}
                logger.debug("Access allowed to "+methodName+ " for "+userName);
			}
			
			long start = System.currentTimeMillis();
			logger.debug("Start calling " + methodName);
			Object result = method.invoke(delegate, args);
			String duration = " (took " + (System.currentTimeMillis() - start) + " milliSec)";
			logger.debug("Stop  calling " + methodName+ duration);
			logger.info("Called " + methodName + " for "+userName+duration);
			return result;
		} catch (InvocationTargetException itE) {
			if (itE.getTargetException() instanceof InvocationTargetException) {
				logger.error("Error calling "+methodName,((InvocationTargetException)itE.getTargetException()).getTargetException());
			} else {
				logger.error("Error calling "+methodName,itE.getTargetException());				
			}
			throw (itE.getTargetException());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			delegate.closeSession();
		}

	}
	
	public static Mgb getProxy() {
		return getProxy(true);
	}
	
	public static Mgb getProxy(boolean checkAccessControl) {
		InvocationHandler handler = new MgbSoapBindingProxyImpl(checkAccessControl);
		Class<?> interfaces[] = new Class[] { Mgb.class };
		ClassLoader classloader = handler.getClass().getClassLoader();
		Object proxy = Proxy.newProxyInstance(classloader, interfaces, handler);

		return (Mgb) proxy;
	}

}