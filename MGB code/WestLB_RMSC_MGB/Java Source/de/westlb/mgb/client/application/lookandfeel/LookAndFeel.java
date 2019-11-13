package de.westlb.mgb.client.application.lookandfeel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.UIDefaults;
import javax.swing.border.Border;

import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;

import de.westlb.mgb.client.ui.util.CurrencyFormat;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb_systems.xaf.ui.misc.IconKatalog;

/**
 * Created on 09.01.2003
 *
 * ListLookAndFeel.java
 *
 * Copyright (c) 2002, WestLB Systems
 *
 * All rights reserved
 * This information contained herin may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 *
 * Description:
 * ActivityListLookAndFeel
 *
 *@version <Insert version>
 *@author WSY4148
 *
 *
 */
public class LookAndFeel extends WindowsClassicLookAndFeel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1973737749046606926L;

	/** DOCUMENT ME! */

	/** DOCUMENT ME! */
	private static final Color WESTLB_DUNKEL_BLAU = new Color(  52,  51, 153);
	private static final Color WESTLB_HELL_BLAU = new Color(203, 203, 229);

	private static final Color WHITE = Color.white;
	private static final Color BLACK = Color.black;
	private static final Color YELLOW = Color.yellow;
	private static final Color GREEN = new Color(12, 128, 128);
	private static final Color LIGHT_BLUE_FOR_SELECTION = new Color(230,230,255);
	private static final Color LIGHT_YELLOW = new Color(255, 255, 204);
	private static final Color BORDER_GREY = new Color(128, 128, 128);

	private static final Color TRADE = GREEN;
	private static final Color ADMIN = new Color( 52,  52, 201);
	private static final Color RED  = new Color(255,   7,   7);

	private static final Color RED2      = new Color( 12, 128, 128);

	private static final Color ATTENTION_FIELD_COLOR = new Color(198, 172, 185);
	private static final Color EMPHASIZED_FIELD_COLOR = new Color(225, 235, 224);

	private final static Font WESTLB_FONT      	= new Font("Arial Narrow", Font.PLAIN, 11);
	private final static Font WESTLB_FONT_STRONG = new Font("Arial Narrow", Font.BOLD,  11);

	static final CurrencyFormat stdCurrencyFormat = new CurrencyFormat();
	static {
		stdCurrencyFormat.setMinimumFractionDigits(4);
		stdCurrencyFormat.setMaximumFractionDigits(10);
		stdCurrencyFormat.setGroupingUsed(true);
		stdCurrencyFormat.setGroupingSize(3);
	}

	static final CurrencyFormat stdIntegerFormat = new CurrencyFormat();
	static {
		stdIntegerFormat.setMinimumFractionDigits(0);
		stdIntegerFormat.setMaximumFractionDigits(0);
		stdIntegerFormat.setGroupingUsed(true);
		stdIntegerFormat.setGroupingSize(3);        
	}

	static DateFormat stdTimeFormat = new DateFormat(DateFormat.TIME_FORMAT_LONG); 
	static DateFormat stdDateFormat = new DateFormat(DateFormat.DATE_FORMAT); 


	/**f
	 * @see javax.swing.LookAndFeel#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Look and Feel for Application MGB";
	}

	/**
	 * @see javax.swing.LookAndFeel#getID()
	 */
	@Override
	public String getID() {
		return "MGB_V1.0";
	}

	/**
	 * @see javax.swing.LookAndFeel#getName()
	 */
	@Override
	public String getName() {
		return "L&F MgbTool";
	}


	/**
	 * Fill Defaults Table
	 */ 
	@Override
	protected void initComponentDefaults(UIDefaults table) {
		Class<WindowsClassicLookAndFeel> c = WindowsClassicLookAndFeel.class;
		super.initComponentDefaults(table);

		IconKatalog iconKatalog = IconKatalog.getInstance();

		Color menuBackground = WESTLB_DUNKEL_BLAU;
		Color menuForeground = WHITE;
		Color menuSelectedBackground = menuBackground;
		Color menuSelectedForeground = YELLOW;

		Border emptyBorder = BorderFactory.createEmptyBorder(2 ,2 ,2 ,2);
		Color origControlColor = (Color)table.get("control");

		table.put("MenuBar.border",             emptyBorder);
		table.put("MenuBar.foreground",         menuForeground);
		table.put("MenuBar.background",         menuBackground);
		table.put("Menu.foreground",            menuForeground);
		table.put("Menu.background",            menuBackground);
		table.put("Menu.pressedForeground",     menuSelectedForeground);
		table.put("Menu.pressedBackground",     menuSelectedBackground);

		table.put("MenuItem.foreground",        menuForeground);
		table.put("MenuItem.background",        menuBackground);
		table.put("MenuItem.pressedForeground", menuSelectedForeground);
		table.put("MenuItem.pressedBackground", menuSelectedBackground);

		table.put("SMenu.foreground",        	menuForeground);
		table.put("SMenu.background",        	menuBackground);
		table.put("SMenuItem.foreground",       menuForeground);
		table.put("SMenuItem.background",       menuBackground);

		table.put("S3Bar.foreground"             , WHITE);
		table.put("S3Bar.background"             , WESTLB_DUNKEL_BLAU);
		table.put("S3Bar.selectedForeground"     , YELLOW);
		table.put("S3Bar.itemIcon"               , iconKatalog.getIcon("FOLDERBAR_ITEM"));
		table.put("S3Bar.selectedItemIcon"       , iconKatalog.getIcon("FOLDERBAR_ITEM_AKTIV"));
		table.put("S3Bar.folderIcon"             , null);
		table.put("S3Bar.selectedFolderIcon"     , null);
		table.put("S3Bar.folderFont"             , new Font( WESTLB_FONT.getName(), WESTLB_FONT.getStyle(), WESTLB_FONT.getSize() + 2));

		table.put("ToolBar.foreground"           , BLACK);
		table.put("ToolBar.background"           , WHITE);

		// Multiviews	
		table.put("MultiViewUI"                  , "de.westlb_systems.xaf.ui.view.ButtonTabNewUI");
		table.put("ButtonTab.menuBackground"     , WESTLB_HELL_BLAU);
		table.put("ButtonTab.menuForeground"     , WHITE);
		table.put("ButtonTab.subMenuBackground"  , WESTLB_DUNKEL_BLAU);
		table.put("ButtonTab.selectedForeground" , YELLOW);
		table.put("ButtonTab.menuFont"           , WESTLB_FONT_STRONG);
		table.put("ButtonTab.headerFont"         , new Font( WESTLB_FONT.getName(), Font.BOLD, 12));
		// table.put("ButtonTab.menuBorderColor"    , WHITE);

		table.put("ComboBox.selectionBackground" , WESTLB_DUNKEL_BLAU);
		table.put("ComboBox.selectionForeground" , Color.WHITE);

		// Tabelpanel
		table.put("TablePanelUI"                 , "de.westlb_systems.xaf.ui.view.TablePanelOldUI");
		table.put("TablePanel.headerMarker"      , WESTLB_HELL_BLAU);
		table.put("TablePanel.headerForeground"  , BORDER_GREY);
		table.put("TablePanel.headerBackground"  , WESTLB_HELL_BLAU);
		table.put("TablePanel.borderColor"       , BORDER_GREY);
		table.put("TablePanel.headerFont"        , new Font( WESTLB_FONT.getName(), Font.BOLD, 12));

		// Detailspanel (Homepage Visitenkarten)
		table.put("DetailsPanel.foreground"      , BLACK);
		table.put("DetailsPanel.background"      , WHITE);
		table.put("DetailsPanel.borderColor"     , WHITE);
		table.put("DetailsPanel.font"            , WESTLB_FONT);
		table.put("DetailsPanel.entityFont"      , WESTLB_FONT_STRONG);

		table.put("OptionPane.errorIcon",		makeIcon(c, "icons/Error.gif"));
		table.put("OptionPane.informationIcon",	makeIcon(c, "icons/Inform.gif"));
		table.put("OptionPane.warningIcon",		makeIcon(c, "icons/Warn.gif"));
		table.put("OptionPane.questionIcon",	makeIcon(c, "icons/Question.gif"));

		table.put("Tree.openIcon",				makeIcon(c, "icons/TreeOpen.gif"));	
		table.put("Tree.closedIcon",			makeIcon(c, "icons/TreeClosed.gif"));	
		table.put("Tree.leafIcon",				makeIcon(c, "icons/TreeLeaf.gif"));

		table.put("Tree.textForeground",		table.get("DetailsPanel.foreground"));
		table.put("Tree.textBackground",		table.get("DetailsPanel.background"));
		table.put("Tree.selectionForeground",	table.get("S3Bar.selectionForeground"));
		table.put("Tree.selectionBackground",	LIGHT_BLUE_FOR_SELECTION);
		table.put("Tree.selectionBorderColor",	BLACK);

		table.put("mandatory.Font"               , WESTLB_FONT_STRONG);
		table.put("Button.font"                  , WESTLB_FONT);
		table.put("TitledBorder.font"            , WESTLB_FONT);
		table.put("MenuItem.font"                , WESTLB_FONT);
		table.put("Table.font"                   , WESTLB_FONT);
		table.put("Panel.font"                   , WESTLB_FONT);
		table.put("CheckBox.font"                , WESTLB_FONT);
		table.put("Label.font"                   , WESTLB_FONT);
		table.put("Table.font"                   , WESTLB_FONT_STRONG);
		table.put("TextPane.font"                , WESTLB_FONT);
		table.put("ScrollPane.font"              , WESTLB_FONT);
		table.put("EditorPane.font"              , WESTLB_FONT);
		table.put("TextArea.font"                , WESTLB_FONT);
		table.put("RadioButtonMenuItem.font"     , WESTLB_FONT);
		table.put("Mandatory.color"              , LIGHT_YELLOW);
		table.put("Mandatory.textColor"          , BLACK);
		// table.put("TableOdd.color", newOddTableColor);

		table.put("text", 					WHITE);
		table.put("textInactiveText",		origControlColor);
		table.put("textInactiveForeground", BLACK);
		table.put("textText", 				BLACK);
		table.put("Button.background", 		origControlColor);
		table.put("control", 				origControlColor);
		table.put("controlText", 			WESTLB_FONT);
		table.put("controlBackground",		origControlColor);
		table.put("controlShadow", 			origControlColor);

		// Farbverlaeufe
		table.put("CheckStateView.foreground"                 , BLACK);
		table.put("CheckStateView.titleBackground1"           , WHITE);
		table.put("CheckStateView.titleBackground2"           , TRADE);

		table.put("TradeListView.foreground"                  , BLACK);
		table.put("TradeListView.titleBackground1"            , WHITE);
		table.put("TradeListView.titleBackground2"            , ADMIN);

		table.put("TradeView.foreground"                 	 , BLACK);
		table.put("TradeView.titleBackground1"           	 , WHITE);
		table.put("TradeView.titleBackground2"           	 , ADMIN);

		table.put("DataSelectionView.foreground",				WHITE);
		table.put("DataSelectionView.textColor",				WHITE);
		table.put("DataSelectionView.background",				WESTLB_DUNKEL_BLAU);

		table.put("EmployeeListView.foreground",				BLACK);
		table.put("EmployeeListView.titleBackground1",			WHITE);
		table.put("EmployeeListView.titleBackground2",			RED);

		table.put("EmployeeOverviewView.foreground",			BLACK);
		table.put("EmployeeOverviewView.titleBackground1",		WHITE);
		table.put("EmployeeOverviewView.titleBackground2",		RED2);

		table.put("SendReclamationListView.foreground",			BLACK);
		table.put("SendReclamationListView.titleBackground1",	WHITE);
		table.put("SendReclamationListView.titleBackground2",	RED2);

		table.put("StateCodeListView.foreground",				BLACK);
		table.put("StateCodeListView.titleBackground1",			WHITE);
		table.put("StateCodeListView.titleBackground2",			RED2);

		table.put("ExchangeView.foreground",					BLACK);
		table.put("ExchangeView.titleBackground1",				WHITE);
		table.put("ExchangeView.titleBackground2",				RED2);

		table.put("PriceCheckCategoryListView.foreground",					BLACK);
		table.put("PriceCheckCategoryListView.titleBackground1",				WHITE);
		table.put("PriceCheckCategoryListView.titleBackground2",				RED2);

		table.put("InstrumentListView.foreground",				BLACK);
		table.put("InstrumentListView.titleBackground1",		WHITE);
		table.put("InstrumentListView.titleBackground2",		RED2);

		table.put("resourceName.for.PriceCheckCategoryTableModel", "label/PriceCheckCategoryTableModel");

		// Color used in read only masks to emphasize a field
		table.put("Field.emphasizedColor", EMPHASIZED_FIELD_COLOR);
		table.put("Field.attentionColor", ATTENTION_FIELD_COLOR);

		table.put("StornoGroupTableModel.stdTimeFormat", stdTimeFormat);
		table.put("StornoGroupTableModel.stdDateFormat", stdDateFormat);
		table.put("StornoGroupTableModel.stdIntegerFormat", stdIntegerFormat);
		table.put("StornoGroupTableModel.stdDecimalFormat", stdCurrencyFormat);

	} 

}
