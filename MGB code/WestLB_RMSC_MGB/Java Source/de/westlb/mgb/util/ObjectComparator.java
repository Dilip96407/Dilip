package de.westlb.mgb.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.log4j.Logger;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ObjectComparator {

	static Logger logger = Logger.getLogger(ObjectComparator.class);
	private MethodFilter filter = null;

	public Method[] getAccessorList(Object o1) {
		ArrayList<Method> list = new ArrayList<Method>();
		Method[] methods = o1.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (isAccessorMethod(methods[i]) && isMethodAccepted(o1, methods[i].getName())) {
				list.add(methods[i]);
			}
		}
		return list.toArray(new Method[0]);
	}

	private boolean isAccessorMethod(Method method) {
		return method.getName().startsWith("get") && method.getParameterTypes().length == 0;
	}
	
	private boolean isMethodAccepted(Object object, String methodName) {
		return filter == null ? true : filter.accept(object, methodName);
	}

	private boolean isObjectTranslated(Object object, Method method) {
		return filter == null || method == null ? false : filter.translate(object, method.getName());
	}

	public Method[] getChangedFieldsAccessorList(Object o1, Object o2)
		throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		ArrayList<Method> list = new ArrayList<Method>();
		if (!o1.getClass().equals(o2.getClass())) {
			throw new IllegalArgumentException(
				"Arguments must be instances from the same class (" + o1.getClass().getName() + ", " + o2.getClass().getName() + ")");
		}
		Method[] methods = o1.getClass().getMethods();
		logger.debug("ObjectComparison of: " + o1.getClass().getName());
		for (int i = 0; i < methods.length; i++) {
			if (isAccessorMethod(methods[i])) {
				if (!isMethodAccepted(o1, methods[i].getName())) {
					continue;
				}
				Object returnObject1 = translate(methods[i].invoke(o1), isObjectTranslated(o1, methods[i]));
				Object returnObject2 = translate(methods[i].invoke(o2), isObjectTranslated(o1, methods[i]));
				if ((returnObject1 != null && !returnObject1.equals(returnObject2)) || (returnObject2 != null && !returnObject2.equals(returnObject1))) {
					list.add(methods[i]);
					logger.debug("ObjectComparison returned different values by " + methods[i].getName() + "(): " + returnObject1 + ", " + returnObject2);
				}
			}
		}
		return list.toArray(new Method[0]);
	}

	public void showChangedFields(Object o1, Object o2) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (!o1.getClass().equals(o2.getClass())) {
			throw new IllegalArgumentException(
				"Arguments must be instances from the same class (" + o1.getClass().getName() + ", " + o2.getClass().getName() + ")");
		}
		Method[] methods = o1.getClass().getMethods();
		logger.debug("ObjectComparison of: " + o1.getClass().getName());
		for (int i = 0; i < methods.length; i++) {
			if (!isMethodAccepted(o1, methods[i].getName())) {
				continue;
			}
			if (methods[i].getName().startsWith("get")) {
				Object returnObject1 = translate(methods[i].invoke(o1), isObjectTranslated(o1, methods[i]));
				Object returnObject2 = translate(methods[i].invoke(o2), isObjectTranslated(o2, methods[i]));
				if ((returnObject1 != null && !returnObject1.equals(returnObject2)) || (returnObject2 != null && !returnObject2.equals(returnObject1))) {
					logger.debug("ObjectComparison returned different values by " + methods[i].getName() + "(): " + returnObject1 + ", " + returnObject2);
				}
			}
		}
	}

    /**
     * @return
     */
    public MethodFilter getFilter() {
        return filter;
    }

    /**
     * @param filter
     */
    public void setFilter(MethodFilter filter) {
        this.filter = filter;
    }

    private Object translate(Object o, boolean doTranslation) {
    	if (o != null && o instanceof Calendar && doTranslation) {
    		int i = ((Calendar)o).get(Calendar.YEAR)*1000 + ((Calendar)o).get(Calendar.DAY_OF_YEAR);
    		logger.debug("Comparing the date ("+i+") but not the Calendar (" + ((Calendar)o).getTime()+")");
    		return Integer.valueOf(i);
    	}
        return o;
    }
}
