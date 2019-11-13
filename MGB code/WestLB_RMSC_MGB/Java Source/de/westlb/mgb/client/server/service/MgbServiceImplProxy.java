/**
 * Secure proxy class for the MgbService implementation. The proxy checks
 * if the currently logged in user is authorized to use the called method.
 * If not it throws an AccessException.
 */

package de.westlb.mgb.client.server.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.AccessException;
import java.util.Iterator;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.MgbServiceImpl;
import de.westlb.mgb.model.impl.AccessControlCheckerImpl;
import de.westlb.mgb.model.impl.EmployeeImpl;
import de.westlb.mgb.model.impl.RoleImpl;
import de.westlb.mgb.model.impl.UserRoleImpl;

public class MgbServiceImplProxy implements InvocationHandler {

    static Logger logger = Logger.getLogger(MgbServiceImplProxy.class);

    private final MgbServiceImpl delegate;

    private AccessControlCheckerImpl accessControlChecker;

    private MgbServiceImplProxy(boolean checkAccessControl) {
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
        if ("isBusy".equals(method.getName())) { return method.invoke(delegate, args); }
        try {
            delegate.openSession();
            String userName = null;

            String methodName = method.getName() + "()";
            if (accessControlChecker != null) {
                EmployeeImpl user = delegate.getUser();
                RoleImpl[] roles = null;
                if (user != null && user.getRoles() != null) {
                    userName = user.getNtId();
                    roles = new RoleImpl[user.getRoles().size()];
                    int i = 0;
                    Iterator<UserRoleImpl> iter = user.getRoles().iterator();
                    while (iter.hasNext()) {
                        roles[i] = iter.next().getType();
                        i++;
                    }
                }

                if (!accessControlChecker.checkAccess(method.getName(), roles)) {
                    logger.error("Access denied to " + methodName + " for " + userName);
                    throw new AccessException("Access denied to " + methodName + " for " + userName);
                }
                logger.debug("Access allowed to " + methodName + " for " + userName);
            }

            long start = System.currentTimeMillis();
            logger.debug("Start calling " + methodName);
            Object result = method.invoke(delegate, args);
            String duration = " (took " + (System.currentTimeMillis() - start) + " milliSec)";
            logger.debug("Stop  calling " + methodName + duration);
            logger.info("Called " + methodName + " for " + userName + duration);
            return result;
        } catch (InvocationTargetException itE) {
            if (itE.getTargetException() instanceof InvocationTargetException) {
                logger.error("Error calling " + method.getName() + "()", ((InvocationTargetException) itE
                        .getTargetException()).getTargetException());
            } else {
                logger.error("Error calling " + method.getName() + "()", itE.getTargetException());
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
        InvocationHandler handler = new MgbServiceImplProxy(checkAccessControl);
        Class<?> interfaces[] = new Class[] { Mgb.class};
        ClassLoader classloader = handler.getClass().getClassLoader();
        Object proxy = Proxy.newProxyInstance(classloader, interfaces, handler);
        return (Mgb) proxy;
    }

}