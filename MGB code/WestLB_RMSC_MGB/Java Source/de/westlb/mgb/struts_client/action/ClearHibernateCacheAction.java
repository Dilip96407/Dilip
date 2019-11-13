package de.westlb.mgb.struts_client.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.StoreSingleton;



	/**
	 * 
	 * 
	 * @author boerner
	 */
	public class ClearHibernateCacheAction extends MgbAction {
		/**

		 */
		@Override
        public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws PersistenceException {
			StoreSingleton.getUniqueInstance().clearCache();
			return mapping.findForward(FORWARD_SUCCESS);
		}
	}

