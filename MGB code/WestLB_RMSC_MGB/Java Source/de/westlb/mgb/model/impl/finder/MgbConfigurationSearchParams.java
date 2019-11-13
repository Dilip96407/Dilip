/*
 * Copyright (c) 2003, WestLB Systems
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.model.impl.finder;

import de.westlb.mgb.model.impl.MandantImpl;

/**
 * DOCUMENT ME!
 *
 * @author M. Boerner
 */
public class MgbConfigurationSearchParams extends SearchParams {
    private String key;

    /**
     * 
     */
    public MgbConfigurationSearchParams() {
        super();
    }

    /**
     * @param mandant
     */
    public MgbConfigurationSearchParams(MandantImpl mandant) {
        super(mandant);
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public String getKey() {
        return key;
    }

    /**
     * DOCUMENT ME!
     *
     * @param string
     */
    public void setKey(String string) {
        key = string;
    }
}