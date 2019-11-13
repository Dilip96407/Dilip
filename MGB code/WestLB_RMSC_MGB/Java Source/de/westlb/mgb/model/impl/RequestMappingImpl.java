/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.model.impl;

import java.io.Serializable;


/**
 * @author Persistent class representing a Mapping of instrument to a bloomberg request ticker.
 */
public class RequestMappingImpl extends EntityImpl {

    private static final long serialVersionUID = 5219023579639467954L;

    private String instrument;
    private String requestString;
    private double conversionFactor;

 
    public void convert(RequestMappingLoadImpl loadData) {
        setInstrument(loadData.getInstrument());
        setRequestString(loadData.getRequestString());
        setConversionFactor(loadData.getConversionFactor());
    }

    /**
     */
    @Override
    public Serializable getId() {
        return instrument;
    }

    public void setRequestString(String requestString) {
        this.requestString = requestString;
    }

    public String getRequestString() {
        return requestString;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getInstrument() {
        return instrument;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
    
    public void setConversionFactor(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }
}