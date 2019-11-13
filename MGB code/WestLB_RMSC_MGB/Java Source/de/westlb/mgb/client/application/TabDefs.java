package de.westlb.mgb.client.application;

/**
 * @author M. Boerner
 *
 * The interface which contains the definition for the views
 * containted in a multi view in the MGB systems (tabbed view).
 */

public interface TabDefs {
   public static final String[][] TRADE = new String[][]{
	  {	
	  	"MGB_TRADE_COMMON",
	  	ContentID.MGB_TRADE_OVERVIEW,    
	  	ContentID.MGB_TRADE_RECLAMATION, 
	  	ContentID.MGB_TRADE_HISTORY,
	  	ContentID.MGB_TRADE_PARAMETER, 
	  },
   };
   
   public static final String[][] STORNO_TRADE = new String[][]{
	  {	
	  	"MGB_TRADE_COMMON",
	  	ContentID.MGB_TRADE_OVERVIEW,
	  	ContentID.MGB_TRADE_STORNO,	  	    
	  	ContentID.MGB_TRADE_RECLAMATION, 
	  	ContentID.MGB_TRADE_HISTORY,
	  	ContentID.MGB_TRADE_PARAMETER, 
	  },
   };
   
   
}
