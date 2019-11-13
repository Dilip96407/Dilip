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
public class RequestMappingLoadImpl extends DataLoadImpl {

    private Long id;
    private String instrument;
    private String requestString;
    private double conversionFactor;

    /**
     */
    @Override
    public Serializable getId() {
        return id;
    }
    
    public Long getLongId()
    {
        return id;
    }
    
    public void setLongId(Long id)
    {
        this.id=id;
    }

    @Override
    public void setNullId() {
        id = null;
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

    public void setConversionFactor(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
}