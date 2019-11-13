package de.westlb.mgb.bloomberg.model;

import junit.framework.TestCase;


public class BloombergRequesterTest extends TestCase {
	public void test1() throws Exception {
		String newStr = new BloombergRequester().addSourceToRequestString("DE11 GR ISIN", "xyz");
		assertEquals(newStr, "DE11@xyz GR ISIN");
	}

	public void test2() throws Exception {
		String newStr = new BloombergRequester().addSourceToRequestString("DE11", "xyz");
		assertEquals(newStr, "DE11");
	}	
	
	public void test3() throws Exception {
		String newStr = new BloombergRequester().addSourceToRequestString("DE11@abc GR ISIN", "xyz");
		assertEquals(newStr, "DE11@xyz GR ISIN");
	}
}
