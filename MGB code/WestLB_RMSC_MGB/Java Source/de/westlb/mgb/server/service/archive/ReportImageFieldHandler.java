package de.westlb.mgb.server.service.archive;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.exolab.castor.mapping.ConfigurableFieldHandler;
import org.exolab.castor.mapping.FieldHandler;
import org.exolab.castor.mapping.ValidityException;

import de.westlb.mgb.model.impl.ReportImageImpl;

public class ReportImageFieldHandler implements FieldHandler, ConfigurableFieldHandler {
	private static Logger logger = Logger.getLogger(ReportImageFieldHandler.class);

	public final static String PATH = "path";
	private String path = "";

    @Override
    public void setConfiguration(Properties config) throws ValidityException {
        String configPath = config.getProperty(PATH);
        path = configPath;
        logger.debug("Configured "+PATH+" to "+path);
        if (configPath == null) {
            throw new ValidityException("Required parameter '"+PATH+"' is missing for ReportImageFieldHandler.");
        }
    }


	
	  /**
     * Returns the value of the field from the object.
     *
     * @param object The object
     * @return The value of the field
     * @throws IllegalStateException The Java object has changed and
     *  is no longer supported by this handler, or the handler is not
     *  compatible with the Java object
     */
    @Override
    public Object getValue( Object object ) throws IllegalStateException {
        if (object instanceof ReportImageImpl) {
            ReportImageImpl root = (ReportImageImpl)object;
            Object value = path+root.getUniqueFileName();
            logger.debug("Path is "+value);
            return value;
        }
        throw new IllegalStateException("This handler only supports the object ReportImageImpl");
    }

    /**
     * Sets the value of the field on the object.
     *
     * @param object The object
     * @param value The new value
     * @throws IllegalStateException The Java object has changed and
     *  is no longer supported by this handler, or the handler is not
     *  compatible with the Java object
     * @throws IllegalArgumentException The value passed is not of
     *  a supported type
     */
    @Override
    public void setValue( Object object, Object value )
        throws IllegalStateException, IllegalArgumentException
    {
        throw new IllegalArgumentException("Not implemented to create a ReportImageImpl from xml-mapping data");
    }
     
    /**
     * Creates a new instance of the object described by this field.
     *
     * @param parent The object for which the field is created
     * @return A new instance of the field's value
     * @throws IllegalStateException This field is a simple type and
     *  cannot be instantiated
     */
    @Override
    public Object newInstance( Object parent )
        throws IllegalStateException
    {
        //-- Since it's marked as a string...just return null,
        //-- it's not needed.
        return null;
    }

    /**
     * Sets the value of the field to a default value.
     *
     * Reference fields are set to null, primitive fields are set to
     * their default value, collection fields are emptied of all
     * elements.
     *
     * @param object The object
     * @throws IllegalStateException The Java object has changed and
     *  is no longer supported by this handler, or the handler is not
     *  compatible with the Java object
     */
    @Override
    public void resetValue( Object object )
        throws IllegalStateException, IllegalArgumentException
    {
        ((ReportImageImpl)object).setImage(null);
        ((ReportImageImpl)object).setName(null);
        ((ReportImageImpl)object).setNullId();
    }

    /**
     * @deprecated No longer supported
     */
    @Override
    public void checkValidity( Object object )
        throws ValidityException, IllegalStateException
    {
        // do nothing
    }

 }
