/*
 * Created on Jul 8, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.mail.parser;

import java.util.HashMap;

import junit.framework.TestCase;

/**
 * @author WSY4148
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ParserTest extends TestCase {

	/**
	 * 
	 */
	public ParserTest() {
		super();
	}

	/**
	 * @param arg0
	 */
	public ParserTest(String arg0) {
		super(arg0);
	}
	
	public void testVariableReplacement1() throws Exception {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("test", "ok");
		String result = Parser.parseTemplate("[var:test]", hashMap);
		assertEquals("ok", result);
	}
		
	public void testVariableReplacement2() throws Exception {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("firstName", "Fritz");
		hashMap.put("lastName", "Mueller");
		String result = Parser.parseTemplate("Hello Mr. [var:muempel][var:firstName] [var:lastName],", hashMap);
		assertEquals("Hello Mr. Fritz Mueller,", result);
	}

	public void testConditionStatement1() throws Exception {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("flag", Boolean.TRUE);
		String result = Parser.parseTemplate("[if:flag]ok[endif]", hashMap);
		assertEquals("ok", result);
	}

	public void testConditionStatement2() throws Exception {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("flag1", Boolean.TRUE);
		hashMap.put("flag2", Boolean.FALSE);
		hashMap.put("test1", "ok1");
		hashMap.put("test2", "ok2");
		
		String result = Parser.parseTemplate("[if:flag1]super [var:test1][endif][if:flag2] mist [var:test2][endif]", hashMap);
		assertEquals("super ok1", result);
	}
	
}
