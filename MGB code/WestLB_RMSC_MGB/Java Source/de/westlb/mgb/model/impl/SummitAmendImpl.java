/*
 * Created on 27.08.2009
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.model.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Calendar;


public class SummitAmendImpl extends EntityImpl{

    private static final long serialVersionUID = -6481882744886581751L;

    public final static String FIELD_NPV = "NPV";
    
    private Long longId;
    private String tradeIdOld;
    private String tradeIdNew;
    private String fieldModified;
    private String fieldValueOld;
    private String fieldValueNew;
    private String fieldValueChange;
    private String tradeId;
    private int tradeVersionOld;
    private int tradeVersionNew;

    public SummitAmendImpl() {
        super();
    }

    public SummitAmendImpl(LoadSummitAmendImpl loadData) {
        super();
        convert(loadData);
    }

    public void convert(LoadSummitAmendImpl loadData) {
        setTradeId(loadData.getTradeId());
        setTradeVersionOld(loadData.getTradeVersionOld());
        setTradeVersionNew(loadData.getTradeVersionNew());
        int i = loadData.getFieldModified().lastIndexOf('('); 
        if (i > 0) { 
            setFieldModified(loadData.getFieldModified().substring(0,i).trim().toUpperCase().replace(' ', '_'));
        } else {
            setFieldModified(loadData.getFieldModified().toUpperCase().replace(' ', '_'));            
        }
        setFieldValueOld(loadData.getFieldValueOld());
        setFieldValueNew(loadData.getFieldValueNew());
        setFieldValueChange(loadData.getFieldValueChange());
        setCreationDate(Calendar.getInstance());
    }

    @Override
    public Serializable getId() {
        return longId;
    }

    @Override
    public void setNullId() {
        setTradeIdOld(null);
    }

    @Override
    public String toString() {
        Field[] fields = this.getClass().getDeclaredFields();
        StringBuffer s = new StringBuffer(this.getClass().getName() + ": ");
        for (int i = 0; i < fields.length; i++) {
            try {
                s.append(
                    "["
                        + fields[i].getName()
                        + ": "
                        + fields[i].get(this)
                        + "] ");
            } catch (IllegalAccessException e) {
            }
        }
        return s.toString();
    }

    @Override
    public Long getLongId() {
        return longId;
    }
    
    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public String getAmendedTradeIdNew() {
        return getTradeId()+"_"+getTradeVersionNew();
    }
    
    public String getAmendedTradeIdOld() {
        return getTradeId()+"_"+getTradeVersionOld();
    }

    public String getTradeIdOld() {
        return tradeIdOld;
    }

    
    public void setTradeIdOld(String tradeIdOld) {
        this.tradeIdOld = tradeIdOld;
    }

    
    public String getTradeIdNew() {
        return tradeIdNew;
    }

    
    public void setTradeIdNew(String tradeIdNew) {
        this.tradeIdNew = tradeIdNew;
    }

    
    public String getFieldModified() {
        return fieldModified;
    }

    
    public void setFieldModified(String fieldModified) {
        this.fieldModified = fieldModified;
    }

    
    public String getFieldValueOld() {
        return fieldValueOld;
    }

    
    public void setFieldValueOld(String fieldValueOld) {
        this.fieldValueOld = fieldValueOld;
    }

    
    public String getFieldValueNew() {
        return fieldValueNew;
    }

    
    public void setFieldValueNew(String fieldValueNew) {
        this.fieldValueNew = fieldValueNew;
    }
 
	public String getFieldValueChange() {
		return fieldValueChange;
	}

	public void setFieldValueChange(String fieldValueChange) {
		this.fieldValueChange = fieldValueChange;
	}

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeVersionOld(int tradeVersionOld) {
        this.tradeVersionOld = tradeVersionOld;
    }

    public int getTradeVersionOld() {
        return tradeVersionOld;
    }

    public void setTradeVersionNew(int tradeVersionNew) {
        this.tradeVersionNew = tradeVersionNew;
    }

    public int getTradeVersionNew() {
        return tradeVersionNew;
    }
}
