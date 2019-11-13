package de.westlb.mgb.struts_client.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import de.westlb.mgb.struts_client.MessageKeys;



	/**
	 * Simply forward to the value of a given request parameter.
	 * 
	 * @author boerner
	 */
	public class MgbRelayAction extends MgbAction {
		/**
		 * default request parameter name
		 */
		public final String DEFAULT_PARAMETER_NAME = "forward";
		public final String DEFAULT_PARAMETER_VALUE = "default";

		/**
		 * Get the relay parameter name.
		 * 
		 * @param mapping
		 * @param request
		 * @return
		 */
		protected String getParameterName(ActionMapping mapping, HttpServletRequest request) {
			// get parameter name from mapping (or use default parameter name)
			String parameter = mapping.getParameter();
			if (parameter == null || parameter.length() == 0)
				parameter = DEFAULT_PARAMETER_NAME;
			return parameter;
		}

		/**
		 * Forward to the value of a given request parameter.
		 * As the parameter's name the action mapping parameter is taken.
		 * If the mapping has no parameter set, "forward" is taken.
		 * The last returned action forward is returned again, if the request
		 * does not contain the forward parameter.
		 */
		@Override
        public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

			String parameter = getParameterName(mapping, request);

			// get forward name
			String name = request.getParameter(parameter);

			ActionForward forward = null;
			if (name != null) {
				// get forward from mapping
				forward =  mapping.findForward(name);
			}

			// Parameter name or forward not defined
			if (forward == null) {
				// Search for an default forward in mapping
				forward =  mapping.findForward(DEFAULT_PARAMETER_VALUE);
				if (forward == null) {
					ActionMessage error = new ActionMessage(MessageKeys.ERROR_UNKNOWN_FORWARD, name);
					addError(request, error);
					return mapping.findForward(FORWARD_FAILURE);
				}
			}

			return forward;
		}
	}

