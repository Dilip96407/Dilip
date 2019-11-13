/*
 * Created on Dec 15, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.ui.util;

import java.util.Locale;

import junit.framework.TestCase;

/**
 * @author WSY4148
 *
 */
public class CurrencyFormatTest extends TestCase {

    /**
     * Constructor for CurrencyFormatTest.
     * @param arg0
     */
    public CurrencyFormatTest(String arg0) {
        super(arg0);
    }
    
    public void testFormatGerman() {
    	Locale.setDefault(Locale.GERMAN);
    	CurrencyFormat currencyFormat = new CurrencyFormat();
    	assertEquals("3,0000", currencyFormat.format(new Float(3f)));
		assertEquals("123.456.792,0000", currencyFormat.format(new Float(123456789f)));
    }

	public void testFormatEnglish() {
		Locale.setDefault(Locale.ENGLISH);
		CurrencyFormat currencyFormat = new CurrencyFormat();
		assertEquals("3.0000", currencyFormat.format(new Float(3f)));
		assertEquals("123,456,792.0000", currencyFormat.format(new Float(123456789f)));
	}

}
