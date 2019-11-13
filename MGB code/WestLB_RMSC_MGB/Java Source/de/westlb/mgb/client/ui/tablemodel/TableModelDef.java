/*
 * Created on Aug 26, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.client.ui.tablemodel;


/**
 * @author d055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface TableModelDef {
    
    public static final String ELEMENT_TABLE = "table";
    public static final String ATTRIBUTE_PROPERTY_PROVIDER_CLASS = "property-provider-class";
    
    public static final String ELEMENT_KEY_COLUMN = "key-column";

    public static final String ELEMENT_COLUMN = "column";
    public static final String ATTRIBUTE_PROPERTY_NAME = "property-name";
    public static final String ATTRIBUTE_HEADER_LABEL = "header-label";
    public static final String ATTRIBUTE_COLUMN_TYPE = "column-type";
    public static final String ATTRIBUTE_USE_PROPERTY_PROVIDER = "use-property-provider";

    public static final String ELEMENT_DATE_FORMAT = "date-format";
    public static final String ATTRIBUTE_TYPE = "type";

    public static final String ELEMENT_NUMBER_FORMAT = "number-format";
    public static final String ATTRIBUTE_FRACTION_MIN_LENGTH = "fraction-min-length";
    public static final String ATTRIBUTE_FRACTION_MAX_LENGTH = "fraction-max-length";
    public static final String ATTRIBUTE_GROUPING_LENGTH = "grouping-length";
 
    public static final String ELEMENT_CHOICE_LIST = "choice-list";
    public static final String ATTRIBUTE_LIST_CLASS= "list-class";
             
    public static final String ELEMENT_GENERIC_FORMAT = "generic-format";
    public static final String ATTRIBUTE_FORMAT_CLASS= "format-class";
}
