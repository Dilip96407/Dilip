/*
 * Created on 11.07.2011
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.util;

import java.util.ArrayList;


public class MgbSystemUtils {
    private final static String USER_NAME = "user.name";
    private final static String JAVA_VERSION = "java.version";
    private final static String JAVAWEBSTART_VERSION = "javawebstart.version";

    public static String getSystemProperties() {
        ArrayList<String> properties = new ArrayList<String>();
        properties.add(USER_NAME+"="+System.getProperty(USER_NAME));
        properties.add(JAVA_VERSION+"="+System.getProperty(JAVA_VERSION));
        properties.add(JAVAWEBSTART_VERSION+"="+System.getProperty(JAVAWEBSTART_VERSION));        
        return properties.toString();
    }
}
