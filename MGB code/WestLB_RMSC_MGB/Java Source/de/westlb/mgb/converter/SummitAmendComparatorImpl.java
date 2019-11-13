/*
 * Created on 31.08.2009
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import de.westlb.mgb.model.impl.MandantImpl;
import de.westlb.mgb.model.impl.MgbConfigurationImpl;
import de.westlb.mgb.model.impl.SummitAmendImpl;
import de.westlb.mgb.model.impl.finder.MgbConfigurationSearchParams;
import de.westlb.mgb.model.impl.finder.MgbFinderImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.util.DateTimeUtils;


public class SummitAmendComparatorImpl {

    private static Logger logger = Logger.getLogger(SummitAmendComparatorImpl.class);

    private static final String AMEND_PREFIX = "AMEND";
    private static final String AMEND_FIELD_LIST = AMEND_PREFIX+"_FIELD_LIST";
    private static final String TYPE_DATE = "DATE";
    private static final String TYPE_NUMBER_RELATIVE = "NUMBER_REL";
    private static final String TYPE_NUMBER_ABSOLUTE = "NUMBER_ABS";
    private static final String DELIMITER = ",";

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    Set<String> fieldComparisonExact = new HashSet<String>();
    Map<String, Double> fieldComparisonAbsoluteNumeric = new HashMap<String, Double>();
    Map<String, Double> fieldComparisonRelativeNumeric = new HashMap<String, Double>();
    Map<String, Long> fieldComparisonDays = new HashMap<String, Long>();
    
    public SummitAmendComparatorImpl(Session sess, MandantImpl mandant) throws PersistenceException {
        loadAmendComperatorConfig(sess, mandant);
    }
    
    private void loadAmendComperatorConfig(Session sess, MandantImpl mandant) throws PersistenceException {
        MgbFinderImpl finder = new MgbFinderImpl(sess);
        MgbConfigurationSearchParams params = new MgbConfigurationSearchParams(mandant);
        params.setKey(AMEND_PREFIX+"%");
        Collection<MgbConfigurationImpl> col = finder.findMgbConfigurations(params);
        for (Iterator<MgbConfigurationImpl> amendConfigurations = col.iterator(); amendConfigurations.hasNext();) {
            MgbConfigurationImpl amendConfiguration = amendConfigurations.next();
            if (AMEND_FIELD_LIST.equals(amendConfiguration.getKey())) { 
                if (amendConfiguration.getValue() != null) {
                    fieldComparisonExact.addAll(Arrays.asList(StringUtils.split(amendConfiguration.getValue(), DELIMITER)));
                }
            } else {
                String field = StringUtils.split(amendConfiguration.getKey(), DELIMITER)[1];
                String type = StringUtils.split(amendConfiguration.getKey(), DELIMITER)[2];
                if (TYPE_DATE.equals(type)){
                    fieldComparisonDays.put(field, new Long(amendConfiguration.getValue()));
                } else if (TYPE_NUMBER_ABSOLUTE.equals(type)){
                    fieldComparisonAbsoluteNumeric.put(field, new Double(amendConfiguration.getValue()));
                } else if (TYPE_NUMBER_RELATIVE.equals(type)){
                    fieldComparisonRelativeNumeric.put(field, new Double(amendConfiguration.getValue()));
                } else {
                    throw new RuntimeException("Amendment configuration does not match the rules: "+amendConfiguration.getKey());
                }
            }
        }
    }
    
    public boolean hasRelevantAmendments(SummitAmendImpl amend) throws ConverterException {
        try {
            String field = amend.getFieldModified();
            if (fieldComparisonExact.contains(field)) {
                // field are always different
                return true;
            } else if (fieldComparisonDays.containsKey(field)) {
                long maxDiff = fieldComparisonDays.get(field).longValue();
                logger.debug(field+": maxDaysDifference="+maxDiff+", oldValue="+amend.getFieldValueOld()+", newValue="+amend.getFieldValueNew());
                if (amend.getFieldValueOld() == null || amend.getFieldValueNew() == null) {
                    return true;
                }
                Calendar oldValue = Calendar.getInstance(); 
                oldValue.setLenient(false);
                oldValue.setTime(dateFormat.parse(amend.getFieldValueOld()));
                Calendar newValue = Calendar.getInstance(); 
                newValue.setLenient(false);
                newValue.setTime(dateFormat.parse(amend.getFieldValueNew()));
                long l = Math.abs(DateTimeUtils.differenceInDays(oldValue, newValue));
                return maxDiff < l;
            } else if (fieldComparisonAbsoluteNumeric.containsKey(field)) {
                double maxDiff = fieldComparisonAbsoluteNumeric.get(field).doubleValue();
                logger.debug(field+": maxAbsDifference="+maxDiff+", oldValue="+amend.getFieldValueOld()+", newValue="+amend.getFieldValueNew());
                if (amend.getFieldValueOld() == null || amend.getFieldValueNew() == null) {
                    return true;
                }
                double oldValue = (new Double(amend.getFieldValueOld())).doubleValue();
                double newValue = (new Double(amend.getFieldValueNew())).doubleValue();
                double d = Math.abs(oldValue - newValue);
                return maxDiff < d;
            } else if (fieldComparisonRelativeNumeric.containsKey(field)) {
                double maxDiff = fieldComparisonRelativeNumeric.get(field).doubleValue();
                logger.debug(field+": maxRelDifference="+maxDiff+", oldValue="+amend.getFieldValueOld()+", newValue="+amend.getFieldValueNew());
                if (amend.getFieldValueOld() == null || amend.getFieldValueNew() == null) {
                    return true;
                }
                double oldValue = (new Double(amend.getFieldValueOld())).doubleValue();
                double newValue = (new Double(amend.getFieldValueNew())).doubleValue();
                double d = Math.abs((oldValue - newValue)/oldValue*100);
                return maxDiff < d;
            } else {
                logger.debug(field+": not relevant");
                return false;
            }
        } catch (NumberFormatException nfe) {
            throw new ConverterException("Error processing the amend record "+amend.getLongId(), nfe);
        } catch (ParseException pe) {
            throw new ConverterException("Error processing the amend record "+amend.getLongId(), pe);
        }
    }
}
