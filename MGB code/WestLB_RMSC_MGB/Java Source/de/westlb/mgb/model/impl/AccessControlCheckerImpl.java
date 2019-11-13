package de.westlb.mgb.model.impl;

import java.io.Serializable;
import java.security.AccessControlException;
import java.util.Arrays;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.server.MgbServiceImpl;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class AccessControlCheckerImpl implements Serializable {

    private static final long serialVersionUID = 2060994658031581759L;

    static Logger logger = Logger.getLogger(AccessControlCheckerImpl.class);

	private AccessControlImpl[] accessControls = null;

	private MgbServiceImpl mgbService = null;

	public AccessControlCheckerImpl(MgbServiceImpl mgbService) {
		this.mgbService = mgbService;
	}

	private void init() throws AccessControlException {
		if (mgbService != null) {
			if (accessControls == null || accessControls.length == 0) {
				try {
					accessControls = mgbService.findAllAccessControls().toArray(new AccessControlImpl[0]);
				} catch (Exception e) {
					logger.error("AccessControl could not been set.", e);
					throw new AccessControlException("AccessControl could not been set.(" + e.getMessage() + ")");
				}
			}
		} else {
			logger.warn("The MGB service has not been set.");
		}
	}

	public boolean checkAccess(String functionName, RoleImpl[] roles) throws AccessControlException {
		if ("login".equals(functionName)) {
			return true;
		}
		init();

		if (roles == null) {
			logger.debug("No roles to check the access of function " + functionName);			
			return false;
		}
		logger.debug("Checking access to " + functionName + " of the roles " + Arrays.asList(roles).toString());
		if (accessControls == null) {
			throw new AccessControlException("No access control data was loaded.");
		}
		for (int i = 0; i < roles.length; i++) {
			AccessControlImpl anAccessControl = new AccessControlImpl(functionName, roles[i]);
			for (int j = 0; j < accessControls.length; j++) {
				if (accessControls[j].equals(anAccessControl)) {
					return true;
				}
			}
		}
		return false;
	}

}
