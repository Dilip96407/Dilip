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
public class TradeTypeCategoryMappingImpl extends EntityImpl {
	
/**
	 * 
	 */
	private static final long serialVersionUID = -7705005323134586668L;
private String tradeType;
private TradeTypeCategoryImpl category;

/**
 * @return Returns the category.
 */
public TradeTypeCategoryImpl getCategory() {
	return category;
}
/**
 * @param category The category to set.
 */
public void setCategory(TradeTypeCategoryImpl category) {
	this.category = category;
}
/**
 * @return Returns the tradeType.
 */
public String getTradeType() {
	return tradeType;
}
/**
 * @param tradeType The tradeType to set.
 */
public void setTradeType(String tradeType) {
	this.tradeType = tradeType;
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
