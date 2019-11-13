/*
 * Created on 27.08.2009
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.model.impl;

import java.lang.reflect.Field;


public class LoadSummitAmendImpl extends DataLoadImpl {

    private String tradeId;
    private String tradeType;
    private String productType;
    private String bookId;
    private int tradeVersionOld;
    private int tradeVersionNew;
    private String fieldModified;
    private String fieldValueOld;
    private String fieldValueNew;
    private String fieldValueChange;

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

    public String getTradeId() {
        return tradeId;
    }
    
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }
    
    public String getTradeType() {
        return tradeType;
    }
    
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
    
    public String getProductType() {
        return productType;
    }
    
    public void setProductType(String productType) {
        this.productType = productType;
    }
    
    public String getBookId() {
        return bookId;
    }
    
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    
    public int getTradeVersionOld() {
        return tradeVersionOld;
    }
    
    public void setTradeVersionOld(int tradeVersionOld) {
        this.tradeVersionOld = tradeVersionOld;
    }
    
    public int getTradeVersionNew() {
        return tradeVersionNew;
    }
    
    public void setTradeVersionNew(int tradeVersionNew) {
        this.tradeVersionNew = tradeVersionNew;
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

}
