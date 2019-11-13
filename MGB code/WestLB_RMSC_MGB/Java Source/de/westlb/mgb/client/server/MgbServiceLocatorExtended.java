package de.westlb.mgb.client.server;

/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MgbServiceLocatorExtended extends MgbServiceLocator {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4206855850584387608L;
	// Use to get a proxy class for Mgb
    private String mgbAdress = null;

	public MgbServiceLocatorExtended(String mgbAdress) {
		setMgbAdress(mgbAdress);
	}
	
    /**
     * Returns the mgbAdress.
     * @return String
     */
    public String getMgbAdress() {
    	if (mgbAdress == null) {
    		return super.getMgbAddress();
    	}
        return mgbAdress;
    }

    /**
     * Sets the mgbAdress.
     * @param mgbAdress The mgbAdress to set
     */
    public void setMgbAdress(String mgbAdress) {
        this.mgbAdress = mgbAdress;
    }

}
