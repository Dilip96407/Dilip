/*
 * Created on Dec 5, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.ui.util;

/**
 * @author WSY4148
 *
 */
public class MmkInstrumentCode {
	private String currencyCode1;
	private String currencyCode2;
	private String maturityCode;
	private boolean generatedCrossCurrency = false;

    /**
     * @return
     */
    public String getCurrencyCode1() {
        return currencyCode1;
    }

    /**
     * @return
     */
    public String getCurrencyCode2() {
        return currencyCode2;
    }

    /**
     * @return
     */
    public String getMaturityCode() {
        return maturityCode;
    }

    /**
     * @param string
     */
    public void setCurrencyCode1(String string) {
        currencyCode1 = string;
    }

    /**
     * @param string
     */
    public void setCurrencyCode2(String string) {
        currencyCode2 = string;
    }

    /**
     * @param string
     */
    public void setMaturityCode(String string) {
        maturityCode = string;
    }

    /**
     * @return
     */
    public boolean isGeneratedCrossCurrency() {
        return generatedCrossCurrency;
    }

    /**
     * @param b
     */
    public void setGeneratedCrossCurrency(boolean b) {
        generatedCrossCurrency = b;
    }

}
