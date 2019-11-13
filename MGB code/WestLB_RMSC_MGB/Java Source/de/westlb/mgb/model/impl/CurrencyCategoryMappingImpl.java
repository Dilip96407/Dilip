/*
 * Created on Jun 15, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.model.impl;

/**
 * @author d055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class CurrencyCategoryMappingImpl extends EntityImpl {
	
/**
     * 
     */
    private static final long serialVersionUID = 3181947980311573774L;
private String currency;
private CurrencyCategoryImpl category;

/**
 * @return Returns the category.
 */
public CurrencyCategoryImpl getCategory() {
	return category;
}
/**
 * @param category The category to set.
 */
public void setCategory(CurrencyCategoryImpl category) {
	this.category = category;
}

/**
 * @return Returns the currency.
 */
public String getCurrency() {
	return currency;
}
/**
 * @param currency The currency to set.
 */
public void setCurrency(String currency) {
	this.currency = currency;
}

	/**
	 * @see de.westlb.mgb.model.impl.EntityImpl#getMandant()
	 */
	@Override
    public MandantImpl getMandant() {
		return getCategory().getMandant();
	}
	
	/**
	 * @see de.westlb.mgb.model.impl.EntityImpl#setMandant(de.westlb.mgb.model.impl.MandantImpl)
	 */
	@Override
    public void setMandant(MandantImpl mandant) {
		getCategory().setMandant(mandant);
	}
}
