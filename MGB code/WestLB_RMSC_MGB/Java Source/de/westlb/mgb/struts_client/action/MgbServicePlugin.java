/*
 * Created on Jan 19, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.struts_client.action;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import de.westlb_systems.xaf.util.PropertyFactory;

/**
 * @author WSY4148
 *
 */
public class MgbServicePlugin implements PlugIn {

    /* (Kein Javadoc)
     * @see org.apache.struts.action.PlugIn#destroy()
     */
    @Override
    public void destroy() {
    }

    /* (Kein Javadoc)
     * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet, org.apache.struts.config.ModuleConfig)
     */
    @Override
    public void init(ActionServlet actionServlet, ModuleConfig moduleConfig) throws ServletException {
    	PropertyFactory.load("struts_client");
    }

}
