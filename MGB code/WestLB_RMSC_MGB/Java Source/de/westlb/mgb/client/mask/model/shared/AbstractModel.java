package de.westlb.mgb.client.mask.model.shared;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import de.westlb.mgb.client.application.ApplicationDefinitions;
import de.westlb.mgb.client.ui.util.CurrencyFormat;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb_systems.xaf.ui.components.text.BigDecimalFormat;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author M. Boerner
 *
 * Abstract superclass of mgb views containing common
 * behavior.
 */
public abstract class AbstractModel extends Model {

    static Logger logger = Logger.getLogger(AbstractModel.class.getName());

    public static final CurrencyFormat format3GMin0Max10 = new CurrencyFormat(0, 10, true, 3);
	public static final CurrencyFormat format3G0D = new CurrencyFormat(0, 0, true, 3);
	
	protected static final DateFormat timeFormat = new DateFormat(DateFormat.TIME_FORMAT);
	protected static final DateFormat timelongFormat = new DateFormat(DateFormat.TIME_FORMAT_LONG);
	protected static final DateFormat dateFormat = new DateFormat(DateFormat.DATE_FORMAT);

	protected static final DateFormat datetimeformat = new DateFormat(DateFormat.DATETIME_FORMAT);

	/**
	 * Constructor for AbstractView.
	 */
	public AbstractModel() {
		super();
	}
	
	protected GregorianCalendar getDateProperty(String propertyName) throws ParseException {
		Object value = getProperty(propertyName);
		if (value == null || value instanceof Calendar) {
			return (GregorianCalendar)value;
		} 
		
		GregorianCalendar retCode = null;
		if (value instanceof String ) {
			retCode = (GregorianCalendar)dateFormat.parseObject((String)value);
		}

		return retCode;		
	}

	protected Double getDoubleProperty(String propertyName, CurrencyFormat format) throws ParseException {
		Object value = getProperty(propertyName);
		if (value == null) {
		    return null;
		}
		if (value instanceof Double) {
			return (Double)value;
		} 
		
		Double retCode = null;
		if (value instanceof BigDecimal) {
	        retCode = new Double(((BigDecimal)value).doubleValue());		    
		} else {
	        BigDecimal big = (BigDecimal)format.parseObject(value.toString());
	        retCode = new Double(big.doubleValue());
		}
        return retCode;
    }
    
    protected void setDoubleProperty(String propertyName, double value, CurrencyFormat format) {
		BigDecimal big = new BigDecimal(value);
		setProperty(propertyName, format.format(big));
    }

	protected double getDoubleProperty(String propertyName, double defaultValue) throws ParseException {
		return getDoubleProperty(propertyName, defaultValue, format3GMin0Max10);
	}

    protected double getDoubleProperty(String propertyName, double defaultValue, CurrencyFormat format) throws ParseException {
		Double object = getDoubleProperty(propertyName, format);
		if (object == null) {
			return defaultValue;
		}
        return object.doubleValue();
    }

	protected Integer getIntegerProperty(String propertyName) throws ParseException {
		Object value = getProperty(propertyName);
		if (value == null) {
		    return null;
		}
		if (value instanceof Integer) {
			return (Integer)value;
		} 
		
		BigDecimalFormat format = new BigDecimalFormat();
		Integer retCode = null;
		if (value instanceof BigDecimal) {
	        retCode = Integer.valueOf(((BigDecimal)value).intValue());		    
		} else {
	        BigDecimal big = (BigDecimal)format.parseObject(value.toString());
	        retCode = Integer.valueOf(big.intValue());
		}
        return retCode;
    }

    protected int getIntProperty(String propertyName, int defaultValue) throws ParseException {
		Integer object = getIntegerProperty(propertyName);
		if (object == null) {
			return defaultValue;
		}
        return object.intValue();
    }
    
	/**
	 * Returnes the prefix for resource strings
	 */
	public String getResourceBase() {
		String className = getClass().getName();
		String name = className.substring(className.lastIndexOf(".") + 1);
		if (name.endsWith("View")) {
			name = name.substring(0, name.length()-4);
		} else if (name.endsWith("Dialog")) {
			name = name.substring(0, name.length()-6);
		} else if (name.endsWith("Model")) {
			name = name.substring(0, name.length()-5);
		}
		
		return name + "_";		
	}
	
	/**
	 * Returnes the name of the class without package prefix as
	 * name for the resource file.
	 */
	@Override
    public String getResourceName() {
		String resourceName = getClass().getName();
		int i = resourceName.lastIndexOf(".");
		if (i > 0) {
			resourceName = resourceName.substring(i+1);
		}
		
		if (resourceName.endsWith("Model")) {
			resourceName = resourceName.substring(0, resourceName.length()-5);
		}
		
		return ApplicationDefinitions.LABEL_PATH + resourceName;
	}

	protected void handleRemoteException(Exception e) {
	    logger.error(e);
		setError(DATABASE_ERROR, e.getMessage(), e);
	}
	
	/**
	 * Standard methods to copy properties from the data object to
	 * this. The function can be used for properties with
	 * standard data types (int, float, long, String and Date.
	 */
	protected void propagateProperties(String[] properties, Object dao)  {
		for (int i=0; i < properties.length; i++) {
			try {
				Object value = PropertyUtils.getProperty(dao, properties[i]); 
				if (value instanceof Double) {
					setDoubleProperty(properties[i], ((Double)value).doubleValue(), format3GMin0Max10);
				} else {
					setProperty(properties[i], value);					
				}      	
            } catch (NoSuchMethodException e) {
            	Debug.log(e);
				e.printStackTrace();
            } catch (InvocationTargetException e) {
            	Debug.log(e);
				e.printStackTrace();
			} catch (IllegalAccessException e) {
            	Debug.log(e);
				e.printStackTrace();
			}
		}			
	}
	
	/**
	 * Reads the content of the file into a byte array
	 */
	protected byte[] readFile(String path) throws FileNotFoundException, IOException {
	    byte[] content = null;
		if (path != null) {
	        File file = new File(path);
	        FileInputStream inputStream = null;
	        try {
	            inputStream = new FileInputStream(path);
	            content = new byte[(int) file.length()];
	            inputStream.read(content);
	        } catch (FileNotFoundException e) {
	        	setErrorDetails(e);
	        	throw e;        	
	        } catch (IOException e) {
	        	setErrorDetails(e);
	        	throw e;        	
	        } finally {
	            if (inputStream != null) {
	                try {
	                    inputStream.close();
	                } catch (IOException io){
	                }
	            }
	        }
		}
		
		return content;
	}

    /**
     * @see de.westlb_systems.xaf.ui.model.Model#reload()
     */
    @Override
    public void reload() {
        super.reload();
        setBusinessObject(getBusinessObject());
    }
    
}
