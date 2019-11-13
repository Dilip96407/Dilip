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
public class RatingCategoryMappingImpl extends EntityImpl {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 8511921944856676583L;
private String rating;
private RatingCategoryImpl category;

/**
 * @return Returns the category.
 */
public RatingCategoryImpl getCategory() {
	return category;
}
/**
 * @param category The category to set.
 */
public void setCategory(RatingCategoryImpl category) {
	this.category = category;
}

/**
 * @return Returns the rating.
 */
public String getRating() {
	return rating;
}
/**
 * @param rating The rating to set.
 */
public void setRating(String rating) {
	this.rating = rating;
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
