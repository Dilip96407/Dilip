package de.westlb.mgb.model.impl;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class AccessControlImpl extends EntityImpl {

	/**
     * 
     */
    private static final long serialVersionUID = -9108044950115659048L;
    private String function;
	private RoleImpl role;

	/**
	 * Constructor for AccessControlImpl.
	 */
	public AccessControlImpl(String function, RoleImpl role) {
		this.function = function;
		this.role = role;
	}

	public AccessControlImpl() {
	}

	/**
	 * Returns the function.
	 * @return String
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * Returns the role.
	 * @return RoleImpl
	 */
	public RoleImpl getRole() {
		return role;
	}

	/**
	 * Sets the function.
	 * @param function The function to set
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	/**
	 * Sets the role.
	 * @param role The role to set
	 */
	public void setRole(RoleImpl role) {
		this.role = role;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AccessControlImpl))
            return false;
        AccessControlImpl vo = (AccessControlImpl)o;
        return vo.role.getCode().equals(role.getCode()) && vo.function.equals(function);
    }

    @Override
    public int hashCode() {
        return role.getCode().hashCode() + function.hashCode();
    }

}
