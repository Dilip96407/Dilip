package de.westlb.mgb.util;

import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.TestCase;

/**
 * @author wsy4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class NativeFileReceiverTest extends TestCase {

    /**
     * Constructor for NativeFileReceiverTest.
     */
    public NativeFileReceiverTest() {
        super();
    }

    /**
     * Constructor for NativeFileReceiverTest.
     * @param arg0
     */
    public NativeFileReceiverTest(String arg0) {
        super(arg0);
    }
    
    public void testReceiveFiles() throws MalformedURLException {
		URL srcUrl = new URL("http","localhost",8080,"");
		NativeFileReceiver.getInstance().receiveFiles(srcUrl, "native", false);    	
    }

    public void testReceiveFilesOwnThread() throws MalformedURLException {
		URL srcUrl = new URL("http","localhost",8080,"");
		NativeFileReceiver.getInstance().receiveFiles(srcUrl, "native", true);    	
    }

}
