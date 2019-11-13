package de.westlb.mgb.client.reports;

import dori.jasper.engine.JasperPrint;

/**
 * @author wsy4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Report extends JasperPrint {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6359312440975450558L;
	private JasperPrint jasperPrint = null;
	
	Report(JasperPrint jasperPrint) {
		this.jasperPrint = jasperPrint;
	}
	
    /**
     * Returns the jasperPrint.
     * @return JasperPrint
     */
    JasperPrint getJasperPrint() {
        return jasperPrint;
    }

}
