/*
 * Created on Mar 2, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.westlb.mgb.client.server.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.server.Mgb;

/**
 * @author wsy4148
 *
 * Stub class for the MGB service. Delegates all Method calls to
 * the remote MGB service servlet. It is assumed that the URL
 * to the servlet is provided by the system parameter 'MgbClientService.url.
 * If this system parameter does not exist it takes the default
 * URL 'http://localhost:5080/mgb/MgbService' which is valid in the development
 * environment only.
 * 
 */
public class MgbClientService implements InvocationHandler {
	private static final String DEFAULT_SERVICE_URL = "http://localhost:5080/mgb/MgbService";
	public static final String SERVICE_URL = "MgbClientService.url";
	
	private static Object[] NO_ARGS = new Object[0];
	private static Mgb instance;
	private static Logger logger = Logger.getLogger(MgbClientService.class);
	private boolean initialized = false;
	private URL url; 
    private URLConnection connection = null;
	
	public MgbClientService() {
	}
	
	private void initizalize() throws RemoteException {
		String urlStr = System.getProperty(SERVICE_URL);
		System.err.println("Servlet-URL from Environment = <" + urlStr + ">");
		if (urlStr == null) {
			urlStr = DEFAULT_SERVICE_URL;
		}

		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			throw new RemoteException("Invalid URL <" + urlStr + "> for MGB Service");
		}
		
	}
	
	/**
	 * @see java.lang.reflect.InvocationHandler#invoke(Object, Method, Object[])
	 */
	@Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		synchronized(this) {
			String methodName = method.getName() + "()";
			long start = System.currentTimeMillis();
			logger.debug("Start calling " + methodName);
			MethodReturn rtd = executeRemote(method, args);
			String duration = " (took " + (System.currentTimeMillis() - start) + " milliSec)";
			logger.debug("Stop  calling " + methodName+ duration);
			
			if (rtd.getException() != null) {
				throw rtd.getException();
			}
			
			return rtd.getObject();
		}
	}
	
	private MethodReturn executeRemote(Method method, Object args[]) throws RemoteException {
		if (!initialized) {
			initizalize();
			initialized = true;
		}
		
	    try {
	        connection = url.openConnection();
	    } catch (IOException e) {
	        logger.error(e);
	        throw new RemoteException("Can not open connection to service",e);
	    }
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setDefaultUseCaches(false);
		
        try {
            connection.connect();
        } catch (IOException e) {
            logger.error(e);
            throw new RemoteException("Can not connect connection to service",e);
        }
        
		OutputStream outputStream = null;
        MethodCall msg = createMethodCall(method, args);
		try {
			outputStream = connection.getOutputStream();
	        writeMsgCall(msg, outputStream);
		} catch (IOException e) {
			throw new RemoteException("IOException while writing object from service",e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e2) {
                }
            }
		}
		

		InputStream inputStream = null;
		try {
			inputStream = connection.getInputStream();
	        return readMsg(inputStream);
		} catch (IOException e) {
			throw new RemoteException("IOException while reading object from service",e);
		} finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                }
            }
		}
		
	}

	private MethodReturn readMsg(InputStream inputStream) throws RemoteException {
		MethodReturn returnMsg = null;
		ObjectInputStream input = null;
		logger.debug("Start calling readMsg");
			try {
				input = new ObjectInputStream(inputStream);
				returnMsg = (MethodReturn)input.readObject();
			} catch (ClassNotFoundException e2) {
				throw new RemoteException("Class of object returned from service on client side not found");
			} catch (IOException e1) {
				logger.error(e1);
				throw new RemoteException("IOException while reading object from service",e1);
			} finally {			
				if (input != null) {
					try {
						input.close();
					} catch (IOException e2) {
					}
				}
		}
	    logger.debug("Finish calling readMsg");
		return returnMsg;
	}

	private void writeMsgCall(MethodCall msg, OutputStream outputStream) throws RemoteException {
		ObjectOutputStream output = null;
		logger.debug("Start calling writeMsgCall");
		try {
			output = new ObjectOutputStream(outputStream);
			output.writeObject(msg);
		} catch (IOException e1) {
			logger.error(e1);
			throw new RemoteException("IOException while sending method call to service",e1);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e2) {
				}
				output = null;
			}
		}
		logger.debug("Stop calling writeMsgCall");
	}
	
	private MethodCall createMethodCall(Method method, Object args[]) {
		MethodCall methodCall = new MethodCall();
		methodCall.setName(method.getName());
		if (args == null) {
			args = NO_ARGS;
		}
		methodCall.setArgs(args);
		return methodCall;
	}
	
	public static Mgb getInstance() {
	    synchronized (MgbClientService.class) {
	        if (instance == null) {
	            InvocationHandler handler = new MgbClientService();
	            Class<?>[] interfaces = new Class[] { Mgb.class };
	            ClassLoader classloader = handler.getClass().getClassLoader();
	            Object proxy = Proxy.newProxyInstance(classloader, interfaces, handler);
	            instance = (Mgb) proxy;
	        }
	    }
	    return instance;
	}
}
