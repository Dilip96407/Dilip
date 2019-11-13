/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.model.impl;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;


/**
 * @author Peristent class representing a book.
 */
public class BookImpl extends EntityImpl {
    /**
     * 
     */
	public static final String LOC_HONGKONG				= "026";
	public static final String LOC_SINGAPORE_1          = "056";
	public static final String LOC_TOKYO_1              = "024";
	public static final String LOC_SYDNEY				= "041";

	private static final long serialVersionUID = 5304104186517468003L;
    private String bookId;					// Primary Key
	private String type;					// e.g. Financial investment portfolio
    private String description;				// e.g. DERIVATIVES TRADING
	private Long account;
	private String accountingSystem;		// e.g Konto
    private Long accountingBranch;			// Betriebsstelle
    private Long costCenter; 				// Organisation Unit
    private String limitPosition;
    private String mpireLuName;
    private String pcrPlDesk;				// e.g. ALM Other Investments
    private String pcrPlGroup;
    private String foSystem;				// e.g. Atlas
    private String locationConsolidation;	// e.g. Duesseldorf
    private String locationGl;				// e.g. Duesseldorf
    private String locationPl;				// e.g. Duesseldorf
    private String locationTrader;			// e.g. Duesseldorf
    private String trader;
    private String tradingHead;
    private String tradingBanking;
    private String tradingType;
    private Long wersUnit;
    private String businessUnit;
    private String costCenterName;			// e.g. TRADE FINANCE - BRAZIL
    private String desk;					// e.g. MAGRP
    private String overwriteLocationTrader;


    public BookImpl() {
        super();
    }

    public BookImpl(String bookId) {
        super();
        this.bookId = bookId;
    }


    /**
     */
    @Override
    public Serializable getId() {
        return bookId;
    }


	public String getOverwriteLocationTrader() {
		return overwriteLocationTrader;
	}

	public void setOverwriteLocationTrader(String overwriteLocationTrader) {
		this.overwriteLocationTrader = overwriteLocationTrader;
	}
     /**
     * @return
     */
    public String getAccountingSystem() {
        return accountingSystem;
    }

    /**
     * @return
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * @return
     */
    public String getBusinessUnit() {
        return businessUnit;
    }


    /**
     * @return
     */
    public String getCostCenterName() {
        return costCenterName;
    }

    /**
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return
     */
    public String getDesk() {
        return desk;
    }

    /**
     * @return
     */
    public String getFoSystem() {
        return foSystem;
    }

    /**
     * @return
     */
    public String getLimitPosition() {
        return limitPosition;
    }

    /**
     * @return
     */
    public String getLocationConsolidation() {
        return locationConsolidation;
    }

    /**
     * @return
     */
    public String getLocationGl() {
        return locationGl;
    }

    /**
     * @return
     */
    public String getLocationPl() {
        return locationPl;
    }

    /**
     * @return
     */
    public String getLocationTrader() {
        return locationTrader;
    }

    /**
     * @return
     */
    public String getMpireLuName() {
        return mpireLuName;
    }

    /**
     * @return
     */
    public String getPcrPlDesk() {
        return pcrPlDesk;
    }

    /**
     * @return
     */
    public String getPcrPlGroup() {
        return pcrPlGroup;
    }

    /**
     * @return
     */
    public String getTrader() {
        return trader;
    }

    /**
     * @return
     */
    public String getTradingBanking() {
        return tradingBanking;
    }

    /**
     * @return
     */
    public String getTradingHead() {
        return tradingHead;
    }

    /**
     * @return
     */
    public String getTradingType() {
        return tradingType;
    }

    /**
     * @return
     */
    public String getType() {
        return type;
    }

 
    /**
     * @param string
     */
    public void setAccountingSystem(String string) {
        accountingSystem = string;
    }

    /**
     * @param string
     */
    public void setBookId(String string) {
        bookId = string;
    }

    /**
     * @param string
     */
    public void setBusinessUnit(String string) {
        businessUnit = string;
    }

    /**
     * @param string
     */
    public void setCostCenterName(String string) {
        costCenterName = string;
    }

    /**
     * @param string
     */
    public void setDescription(String string) {
        description = string;
    }

    /**
     * @param string
     */
    public void setDesk(String string) {
        desk = string;
    }

    /**
     * @param string
     */
    public void setFoSystem(String string) {
        foSystem = string;
    }

    /**
     * @param string
     */
    public void setLimitPosition(String string) {
        limitPosition = string;
    }

    /**
     * @param string
     */
    public void setLocationConsolidation(String string) {
        locationConsolidation = string;
    }

    /**
     * @param string
     */
    public void setLocationGl(String string) {
        locationGl = string;
    }

    /**
     * @param string
     */
    public void setLocationPl(String string) {
        locationPl = string;
    }

    /**
     * @param string
     */
    public void setLocationTrader(String string) {
        locationTrader = string;
    }

    /**
     * @param string
     */
    public void setMpireLuName(String string) {
        mpireLuName = string;
    }

    /**
     * @param string
     */
    public void setPcrPlDesk(String string) {
        pcrPlDesk = string;
    }

    /**
     * @param string
     */
    public void setPcrPlGroup(String string) {
        pcrPlGroup = string;
    }

    /**
     * @param string
     */
    public void setTrader(String string) {
        trader = string;
    }

    /**
     * @param string
     */
    public void setTradingBanking(String string) {
        tradingBanking = string;
    }

    /**
     * @param string
     */
    public void setTradingHead(String string) {
        tradingHead = string;
    }

    /**
     * @param string
     */
    public void setTradingType(String string) {
        tradingType = string;
    }

    /**
     * @param string
     */
    public void setType(String string) {
        type = string;
    }


    /**
     * @param impl
     */
    public void convert(LoadParisBookImpl loadData) {
		setBookId(loadData.getBookId());
		setType(loadData.getType());
		setDescription(loadData.getDescription());
		setAccount(loadData.getAccount());
		setAccountingSystem(loadData.getAccountingSystem());
		setAccountingBranch(loadData.getAccountingBranch());
		setCostCenter(loadData.getCostCenter());
		setLimitPosition(loadData.getLimitPosition());
		setMpireLuName(loadData.getMpireLuName());
		setPcrPlDesk(loadData.getPcrPlDesk());
		setPcrPlGroup(loadData.getPcrPlGroup());
		setFoSystem(loadData.getFoSystem());
		setLocationConsolidation(loadData.getLocationConsolidation());
		setLocationGl(loadData.getLocationGl());
		setLocationPl(loadData.getLocationPl());
		setLocationTrader(checkLocationTrader(loadData.getLocationTrader()));
		setTrader(loadData.getTrader());
		setTradingHead(loadData.getTradingHead());
		setTradingBanking(loadData.getTradingBanking());
		setTradingType(loadData.getTradingType());
		setWersUnit(loadData.getWersUnit());
		setBusinessUnit(loadData.getBusinessUnit());
		setCostCenterName(loadData.getCostCenterName());
		setDesk(loadData.getDesk());
		if (getOverwriteLocationTrader() != null && getOverwriteLocationTrader().length() > 0) {
			setLocationTrader(getOverwriteLocationTrader());
		}
		setChangedDate(Calendar.getInstance());
    }

	private String checkLocationTrader(String loc) {
    	if ("Hong Kong".equals(loc))
    		return LOC_HONGKONG;
    	if ("Hongkong".equals(loc))
    		return LOC_HONGKONG;
    	if ("Singapore".equals(loc))
    		return LOC_SINGAPORE_1;
    	if ("Sydney".equals(loc))
    		return LOC_SYDNEY;
    	if ("Tokyo".equals(loc))
    		return LOC_TOKYO_1;
    	if (loc != null && loc.length() < 3)
    		return StringUtils.leftPad(loc, 3, "0");
    	return loc;    	
    }
    
    /**
     * @return
     */
    public Long getAccount() {
        return account;
    }

    /**
     * @return
     */
    public Long getAccountingBranch() {
        return accountingBranch;
    }

    /**
     * @return
     */
    public Long getCostCenter() {
        return costCenter;
    }

    /**
     * @return
     */
    public Long getWersUnit() {
        return wersUnit;
    }

    /**
     * @param long1
     */
    public void setAccount(Long long1) {
        account = long1;
    }

    /**
     * @param long1
     */
    public void setAccountingBranch(Long long1) {
        accountingBranch = long1;
    }

    /**
     * @param long1
     */
    public void setCostCenter(Long long1) {
        costCenter = long1;
    }

    /**
     * @param long1
     */
    public void setWersUnit(Long long1) {
        wersUnit = long1;
    }

}