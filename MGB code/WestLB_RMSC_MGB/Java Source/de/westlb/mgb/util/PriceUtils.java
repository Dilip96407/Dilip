/*
 * Created on 07.07.2009
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.util;


public class PriceUtils {

    public static double calculatePriceDifference(double tradePrice, double minPrice, double maxPrice, boolean isIntervalPrice) { 
        if (isIntervalPrice) {
            return Math.max(0.0,Math.max(tradePrice - maxPrice, minPrice - tradePrice));
        }
        return tradePrice - minPrice;
    }

    public static double calculateMidPrice(double minPrice, double maxPrice) {
        if (minPrice == 0d) {
            return maxPrice;
        }
        if (maxPrice == 0d) {
            return minPrice;
        }
        return (minPrice+maxPrice)/2d;
    }

}
