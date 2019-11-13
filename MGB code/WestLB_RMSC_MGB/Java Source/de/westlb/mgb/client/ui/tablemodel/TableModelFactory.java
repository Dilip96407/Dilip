/*
 * Created on Aug 26, 2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.client.ui.tablemodel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.text.Format;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import de.westlb.mgb.client.ui.selection_list.AbstractSelectionList;
import de.westlb.mgb.client.ui.util.CurrencyFormat;
import de.westlb.mgb.client.ui.util.DateFormat;

/**
 * @author d055625
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class TableModelFactory implements TableModelDef {

    static Logger logger = Logger.getLogger(TableModelFactory.class);

    private static String tableModelPath = TableModelFactory.class.getPackage().getName().replaceAll("\\.","/") + "/";

    private TableModelFactory() {
    }
    
    /**
     * @param xmlFilename This file is supposed to be located in the same directory that this classfile. It is
     * searched by the classloader in the classpath. So a file name like 'MyTable' is looked up using the path
     * 'de/westlb/mgb/client/ui/tablemodel/MyTable.xml'
     * If the name contains any directory separators, it is left unchanged.
     * If the extention '.xml' is obmitted, it is appended.
     * @param voObjects the fields of the bean array is referenced in the xml-file.
     * @return a TableModel
     * @throws RemoteException
     */
    public static TableModel createTableModel(String xmlFilename, Object[] voObjects) throws RemoteException {
        try {
            Document doc = parse(xmlFilename);

            Element root = doc.getRootElement();
            int numberOfColumns = root.elements(ELEMENT_COLUMN).size();
            Format[] formatArray = new Format[numberOfColumns];
            String[] headerLabelArray = new String[numberOfColumns];
            String[] propertyNameArray = new String[numberOfColumns];
            int[] columnTypeArray = new int[numberOfColumns];
            Arrays.fill(columnTypeArray, 0);
            AbstractSelectionList[] selectionListArray = new AbstractSelectionList[numberOfColumns];
            AbstractPropertyProvider propertyProvider = null;
            boolean[] usePropertiyProvider = new boolean[numberOfColumns];;
            if (root.attribute(ATTRIBUTE_PROPERTY_PROVIDER_CLASS) != null) {
                propertyProvider = (AbstractPropertyProvider) Class.forName(root.attributeValue(ATTRIBUTE_PROPERTY_PROVIDER_CLASS)).newInstance();
            }
 
            String keyPropertyName = null;
            if (root.element(ELEMENT_KEY_COLUMN) != null) {
                keyPropertyName = root.element(ELEMENT_KEY_COLUMN).attributeValue(ATTRIBUTE_PROPERTY_NAME);
            }
            
            int i = 0;
            Iterator<Element> elementIterator = root.elementIterator(ELEMENT_COLUMN);
            while (elementIterator.hasNext()) {
                Element element = elementIterator.next();
                headerLabelArray[i] = element.attributeValue(ATTRIBUTE_HEADER_LABEL);
                propertyNameArray[i] = element.attributeValue(ATTRIBUTE_PROPERTY_NAME);
                columnTypeArray[i] = mapColumnType(element.attributeValue(ATTRIBUTE_COLUMN_TYPE));
                usePropertiyProvider[i] = "true".equals(element.attributeValue(ATTRIBUTE_USE_PROPERTY_PROVIDER));
                if (element.element(ELEMENT_DATE_FORMAT) != null) {
                    formatArray[i] = dateFormatFromElement(element);
                } else if (element.element(ELEMENT_NUMBER_FORMAT) != null) {
                    formatArray[i] = numberFormatFromElement(element);
                } else if (element.element(ELEMENT_GENERIC_FORMAT) != null) {
                    formatArray[i] = genericFormatFromElement(element);
                } else if (element.element(ELEMENT_CHOICE_LIST) != null) {
                    selectionListArray[i] = getAbstractSelectionListFromElement(element);
                }
                i++;
            }

            TableModel model = new TableModel();
            model.setHeaderLabels(headerLabelArray);
            model.setPropertyNames(propertyNameArray);
            model.setKeyPropertyName(keyPropertyName);
            model.setColumnFormats(formatArray);
            model.setColumnTypes(columnTypeArray);
            model.setSelectionLists(selectionListArray);
            model.setPropertyProvider(propertyProvider, usePropertiyProvider);
            model.setData(voObjects);
            return model;
        } catch (IOException e) {
            throw new RemoteException("Error while creating a table model.", e);
        } catch (InstantiationException e) {
            throw new RemoteException("Error while creating a table model.", e);
        } catch (IllegalAccessException e) {
            throw new RemoteException("Error while creating a table model.", e);
        } catch (ClassNotFoundException e) {
            throw new RemoteException("Error while creating a table model.", e);
        } catch (DocumentException e) {
            throw new RemoteException("Error while creating a table model.", e);
        } catch (SAXException e) {
            throw new RemoteException("Error while creating a table model.", e);
        }
    }

    private static AbstractSelectionList getAbstractSelectionListFromElement(Element element)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (AbstractSelectionList) Class.forName(
                element.element(ELEMENT_CHOICE_LIST).attributeValue(ATTRIBUTE_LIST_CLASS)).newInstance();
    }

    private static Format dateFormatFromElement(Element element) {
        return new DateFormat(element.element(ELEMENT_DATE_FORMAT).attributeValue(ATTRIBUTE_TYPE));
    }

    private static Format genericFormatFromElement(Element element) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        return (Format) Class.forName(element.element(ELEMENT_GENERIC_FORMAT).attributeValue(ATTRIBUTE_FORMAT_CLASS))
                .newInstance();
    }

    private static Format numberFormatFromElement(Element element) {
        Element e = element.element(ELEMENT_NUMBER_FORMAT);
        CurrencyFormat f = new CurrencyFormat();
        f.setMinimumFractionDigits((new Integer(e.attributeValue(ATTRIBUTE_FRACTION_MIN_LENGTH))).intValue());
        f.setMaximumFractionDigits((new Integer(e.attributeValue(ATTRIBUTE_FRACTION_MAX_LENGTH))).intValue());
        int groupingLength = (new Integer(e.attributeValue(ATTRIBUTE_GROUPING_LENGTH))).intValue();
        f.setGroupingUsed(groupingLength > 0);
        f.setGroupingSize(groupingLength);
        return f;
    }

    private static int mapColumnType(String columnType) {
        if ("default".equals(columnType) || columnType == null) {
            return TableModel.DEFAULT;
        } else if ("boolean".equals(columnType)) {
            return TableModel.BOOLEAN;
        } else if ("number".equals(columnType)) {
            return TableModel.NUMBER;
        } else if ("sicon".equals(columnType)) {
            return TableModel.SICON;
        } else if ("icon".equals(columnType)) {
            return TableModel.ICON;
        } else if ("currency".equals(columnType)) {
            return TableModel.CURRENCY;
        } else if ("date".equals(columnType)) {
            return TableModel.DATE;
        } else if ("date_time".equals(columnType)) {
            return TableModel.DATE_TIME;
        } else if ("float".equals(columnType)) {
            return TableModel.FLOAT;
        } else if ("choice".equals(columnType)) {
            return TableModel.CHOICE;
        } else {
            throw new IllegalArgumentException("Undefined columnType '" + columnType + "'");
        }
    }

    /**
     * @param xmlFilename
     * @return
     * @throws DocumentException
     * @throws MalformedURLException
     * @throws FileNotFoundException
     */
    private static Document parse(String xmlFilename) throws DocumentException, MalformedURLException,
            FileNotFoundException, SAXException {
        SAXReader reader = new SAXReader();
        reader.setIncludeExternalDTDDeclarations( false );
        reader.setEntityResolver( new EntityResolver() {
            @Override
            public InputSource resolveEntity( String publicID, String systemID ) {
                String dtd = "TableModel.dtd";
//                logger.debug("Looking up entity "+ publicID + " " + systemID);
                if( systemID.indexOf( dtd ) != -1 ) {
                    logger.debug( "Loading "+dtd+" from system resource.");
                    try {
//                        InputStream is;
//                        is = TableModelFactory.class.getResourceAsStream(dtd);
//                        if (is != null) {
//                            logger.info( "Loaded 4");
//                            return new InputSource( new InputStreamReader(is,"UTF-8") );
//                        }
//
//                        is = TableModelFactory.class.getResourceAsStream(tableModelPath+dtd);
//                        if (is != null) {
//                            logger.info( "Loaded 1");
//                            return new InputSource( new InputStreamReader(is,"UTF-8") );
//                        }
//    
//                        is = ClassLoader.getSystemResourceAsStream(tableModelPath+dtd);
//                        if (is != null) {
//                            logger.info( "Loaded 2");
//                            return new InputSource( new InputStreamReader(is,"UTF-8") );
//                        }
//                        
//                        is = ClassLoader.getSystemResourceAsStream(dtd);
//                        if (is != null) {
//                            logger.info( "Loaded 3");
//                            return new InputSource( new InputStreamReader(is,"UTF-8") );
//                        }
//                        return null;
                        
                        return new InputSource( new InputStreamReader(TableModelFactory.class.getResourceAsStream(dtd ),"UTF-8") );
                    } catch (Exception e) {
                        logger.error( "Error while reading " + dtd , e);
                    }
                }
                throw new RuntimeException("Unknown entity " + systemID);
            }
        }
        );
        
        if (xmlFilename.indexOf('/') < 0) {
            xmlFilename = tableModelPath + xmlFilename;
            logger.debug("Expanding file with default package name: " + xmlFilename);
        }
        if (!xmlFilename.endsWith(".xml")) {
            xmlFilename = xmlFilename + ".xml";
        }
        
        Document document = reader.read(TableModelFactory.class.getClassLoader().getResourceAsStream(xmlFilename));
        return document;
    }

}