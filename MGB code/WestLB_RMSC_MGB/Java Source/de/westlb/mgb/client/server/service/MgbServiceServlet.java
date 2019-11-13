/*
 * Created on Nov 3, 2006
 *
 */
package de.westlb.mgb.client.server.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.server.Mgb;

/**
 * @author boernema
 */
public class MgbServiceServlet extends HttpServlet {
	private static final long serialVersionUID = -3200214766202411975L;
	private static Logger logger = Logger.getLogger(MgbServiceServlet.class);
	private transient MethodCache methodCache;
	
	/* (non-Javadoc)
	 * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
	 */
	@Override
    public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
        logger.info("initilised");
	}

	private MethodCache getMethodCache() {
	    if (methodCache != null) {
	        logger.info("initilised methodCache");
	        methodCache = new MethodCache();
	        methodCache.put(MgbServiceImplProxy.getProxy(true).getClass().getMethods());	        
	    }
	    return methodCache;
	}
	
	private static class MethodCache {
        private HashMap<String, Method> cache = new HashMap<String, Method>();
		public void put(Method[] methods) {
			for (int i = 0; i < methods.length; i++) {
				put(methods[i]);
			}
		}
		public void put(Method method) {
			cache.put(method.getName() + "/" + method.getParameterTypes().length, method);
		}
		public Method get(String name, int nParams) {
			return cache.get(name + "/" + nParams);
		}
		@SuppressWarnings("unused")
        public void clear() {
			cache.clear();
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private MethodCall readMsgCall(InputStream inputStream) throws ServletException {
		MethodCall returnMsg = null;
		ObjectInputStream input = null;
		logger.info("Start Method readMsgCall");
		try {
			input = new ObjectInputStream(inputStream);
			returnMsg = (MethodCall)input.readObject();
		} catch (Exception e) {
			logger.error(e);
			throw new ServletException("Exception while reading method call from client", e);
		} finally {			
			if (input != null) {
				try {
					input.close();
				} catch (IOException e2) {
				}
			}
		}
		logger.info("Finish Method readMsgCall");
		return returnMsg;
	}

	private void writeMsgReturn(MethodReturn msg, OutputStream outputStream) throws ServletException {
		ObjectOutputStream output = null;
		logger.info("Start Method writeMsgReturn");
		try {
			output = new ObjectOutputStream(outputStream);
			output.writeObject(msg);
		} catch (Exception e) {
			logger.error(e);
			throw new ServletException("Exception while sending method return to client");
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e2) {
				}
				output = null;
			}
			if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e2) {
                }			    
			}
		}
		logger.info("Finish Method writeMsgReturn");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MethodCall methodCallMessage = readMsgCall(request.getInputStream());
		MethodReturn methodReturnMessage = callMessage(request, methodCallMessage);
        writeMsgReturn(methodReturnMessage, response.getOutputStream());
//        OutputStream outputStream = response.getOutputStream();
//        writeMsgReturn(methodReturnMessage, outputStream);
//		try {
//			outputStream.flush( );
//			outputStream.close();
//		} catch (IOException e) {
//			logger.error(e);
//		}
	}
	
	private Mgb retrieveService(HttpServletRequest request) throws RemoteException {
		Mgb service = (Mgb)request.getSession().getAttribute("mgb_service");
		if (service == null) {
			service = MgbServiceImplProxy.getProxy(false);
			request.getSession().setAttribute("mgb_service", service);
		}
		return service;
	}
	
	private MethodReturn callMessage(HttpServletRequest request, MethodCall methodCall) throws RemoteException {
		Exception exception = null;
		Object retObject = null;
		logger.info("Start Method callMethod <" + methodCall.getName() + ">" );
		try {
			Object service = retrieveService(request);
			if (service == null) {
				exception = new RemoteException("Service is not available. Login-Mechanism failed!");
			} else {
				Method method = getMethodCache().get(
						methodCall.getName(), 
						methodCall.getArgs().length);
				if (method == null) {
					throw new ServiceException("Method <" + methodCall.getName() + "> not implemented by service");
				}
				retObject = method.invoke(service, methodCall.getArgs());
			}
		} catch (IllegalArgumentException e) {
			logger.error(e);
			exception =  new RemoteException("Client passed illegal arguments for service method");
		} catch (IllegalAccessException e) {
			logger.error(e);
			exception = new RemoteException("Illegal access for service method");
		} catch (InvocationTargetException e) {
			logger.error(e);
			exception = new RemoteException("InvocationTargetException in service method");
		} catch (ServiceException e) {
		    logger.error(e);
			exception = e;
        }
		
		if (retObject != null && !(retObject instanceof Serializable)) {
			exception = new RemoteException("Object rtd by service method doesn't implement Serializable. Class=<" + retObject.getClass() + ">");
		} 
		logger.info("Finish Method callMethod <" + methodCall.getName() + ">" );
		MethodReturn methodReturn = new MethodReturn();
		methodReturn.setException(exception);
		methodReturn.setObject(retObject);
		
		return methodReturn;
	}
}
