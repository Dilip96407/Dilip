/*
 * Created on 07.12.2007
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.struts_client.action.worksheet;

import org.apache.poi.ss.usermodel.Workbook;

import de.westlb.mgb.model.definition.MandantDef;

public class TradeSheetBuilderFactory {

    public static TradeSheetBuilder getInstance(Workbook workbook, String name, String productClass) {
    	
        if (MandantDef.PRODUCT_BOND.equals(productClass)) {
            return new BondTradeSheetBuilder(workbook, name);
        } else if (MandantDef.PRODUCT_DERIVATIVE.equals(productClass)) {
            return new DerivativeTradeSheetBuilder(workbook, name);
        } else if (MandantDef.PRODUCT_EQUITY.equals(productClass)) {
            return new EquityTradeSheetBuilder(workbook, name);
        } else if (MandantDef.PRODUCT_MONEYMARKET.equals(productClass)) {
            return new MoneyMarketTradeSheetBuilder(workbook, name);
        } else if (MandantDef.PRODUCT_REPO.equals(productClass)) {
            return new RepoTradeSheetBuilder(workbook, name);
        } else {
            return new GeneralTradeSheetBuilder(workbook, name);
        }
    }
}
