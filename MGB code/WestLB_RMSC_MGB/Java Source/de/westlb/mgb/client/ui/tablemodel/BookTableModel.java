/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.client.ui.tablemodel;

/**
 * Adapter converting an array of BookVo's to an GUI table model.
 *
 * @author WSY4148
 * 
 * @deprecated
 */
public class BookTableModel extends AbstractTableModel {
    /** Column definitions */
    public static final int COLUMN_BOOK_ID = 0;
    public static final int COLUMN_TYPE = 1;
    public static final int COLUMN_DESCRIPTION = 2;
    public static final int COLUMN_ACCOUNT = 3;
    public static final int COLUMN_ACCOUNTING_SYSTEM = 4;
    public static final int COLUMN_ACCOUNTING_BRANCH = 5;
    public static final int COLUMN_COST_CENTER = 6;
    public static final int COLUMN_LIMIT_POSITION = 7;
    public static final int COLUMN_MPRE_LU_NAME = 8;
    public static final int COLUMN_PCR_PL_DESK = 9;
    public static final int COLUMN_PCR_PL_GROUP = 10;
    public static final int COLUMN_FO_SYSTEM = 11;
    public static final int COLUMN_LOCATION_CONSOLIDATION = 12;
    public static final int COLUMN_LOCATION_GL = 13;
    public static final int COLUMN_LOCATION_PL = 14;
    public static final int COLUMN_LOCATION_TRADER = 15;
    public static final int COLUMN_TRADER = 16;
    public static final int COLUMN_TRADING_HEAD = 17;
    public static final int COLUMN_TRADING_BANKING = 18;
    public static final int COLUMN_TRADING_TYPE = 19;
    public static final int COLUMN_WERS_UNIT = 20;
    public static final int COLUMN_BUSINESS_UNIT = 21;
    public static final int COLUMN_COST_CENTER_NAME = 22;
    public static final int COLUMN_DESC = 23;
    private String[] headerKeys = new String[] {
            "BOOK_ID", 
			"TYPE", 
			"DESCRIPTION", 
			"BUSINESS_UNIT", 
			"TRADER", 
			"ACCOUNT", 
			"ACCOUNTING_SYSTEM", 
			"ACCOUNTING_BRANCH",
            "COST_CENTER", 
			"LIMIT_POSITION", 
			"MPRE_LU_NAME", 
			"PCR_PL_DESK", 
			"PCR_PL_GROUP",
            "FO_SYSTEM", 
			"LOCATION_CONSOLIDATION", 
			"LOCATION_GL", 
			"LOCATION_PL", 
			"LOCATION_TRADER",
			"TRADING_HEAD", 
			"TRADING_BANKING", 
			"TRADING_TYPE", 
			"WERS_UNIT",
			"COST_CENTER_NAME", 
			"DESK",
        };
    private String[] propertyNames = new String[] {
            "bookId", 
			"type", 
			"description", 
			"businessUnit", 
			"trader", 
			"account", 
			"accountingSystem",
            "accountingBranch", 
			"costCenter", 
			"limitPosition", 
			"mpireLuName", 
			"pcrPlDesk",
            "pcrPlGroup", 
			"foSystem", 
			"locationConsolidation", 
			"locationGl", 
			"locationPl",
            "locationTrader", 
			"tradingHead", 
			"tradingBanking", 
			"tradingType", 
			"wersUnit",
			"costCenterName", 
			"desk",
        };

    /**
     * Constructor for AutomaticStateTableModel.
     */
    public BookTableModel() {
        super();

        setHeaderKeys(headerKeys);
        setPropertyNames(propertyNames);
    }

    /**
     * @see de.westlb_systems.xaf.swing.SDataModel2#getColumnType(int)
     */
    @Override
    public int getColumnType(int column) {
        switch (column) {
        case COLUMN_ACCOUNT:
        case COLUMN_ACCOUNTING_BRANCH:
        case COLUMN_COST_CENTER:
        case COLUMN_WERS_UNIT:
            return NUMBER;

        default:
            return DEFAULT;
        }
    }

    /**
     * @see de.westlb_systems.xaf.swing.SDataModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column) {
        return super.getValueAt(row, column);
    }
}