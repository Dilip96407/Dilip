/*
 * Created on 20.03.2008
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.model.impl.visitor;

import de.westlb.mgb.model.impl.HistoricPriceImpl;
import de.westlb.mgb.model.impl.IntervalPriceImpl;


public class PriceVisitorAdapter implements PriceVisitor {

    @Override
    public void visit(IntervalPriceImpl price) {
    }

    @Override
    public void visit(HistoricPriceImpl price) {
    }

}
