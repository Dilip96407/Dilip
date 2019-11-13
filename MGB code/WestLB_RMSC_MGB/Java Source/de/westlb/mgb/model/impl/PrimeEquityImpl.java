package de.westlb.mgb.model.impl;

import java.util.Calendar;
import java.util.regex.Pattern;

import de.westlb.mgb.util.DateTimeUtils;

public class PrimeEquityImpl extends EquityImpl {

	private static final long serialVersionUID = -8024136800929024037L;
	private String automaticTradeFlag;
	private Calendar creationDateTime;
	private String instrumentId;
	private String instrumentName;
	private String internalInstrumentFlag;
	private String tradeType;
	private String underlyingInstrumentId;
	private String underlyingInstrumentIsinCode;
    private String underlyingInstrumentName;
    private String freeText;
    private Calendar expiryCombinationDate;
    private Calendar expiryOptionDate;
    private Calendar issueDate;
    private String subType;
    private double strikePrice;
    private String putCallFlag;
    private double contractSize;
    private String quoteType;
	
	public PrimeEquityImpl() {
	}

	/**
	 * Returns the automaticTradeFlag.
	 * @return String
	 */
	public String getAutomaticTradeFlag() {
		return automaticTradeFlag;
	}


    @Override
    public String getTradeCategory() {
        return getTradeType();
    }

	/**
	 * Returns the instrumentId.
	 * @return String
	 */
	public String getInstrumentId() {
		return instrumentId;
	}

	/**
	 * Returns the instrumentName.
	 * @return String
	 */
	public String getInstrumentName() {
		return instrumentName;
	}

	/**
	 * Returns the internalInstrumentFlag.
	 * @return String
	 */
	public String getInternalInstrumentFlag() {
		return internalInstrumentFlag;
	}

	/**
	 * Returns the tradeType.
	 * @return String
	 */
	public String getTradeType() {
		return tradeType;
	}

	/**
	 * Returns the underlyingInstrumentId.
	 * @return String
	 */
	public String getUnderlyingInstrumentId() {
		return underlyingInstrumentId;
	}

	/**
	 * Returns the underlyingInstrumentIsinCode.
	 * @return String
	 */
	public String getUnderlyingInstrumentIsinCode() {
		return underlyingInstrumentIsinCode;
	}

	/**
	 * Returns the underlyingInstrumentName.
	 * @return String
	 */
	public String getUnderlyingInstrumentName() {
		return underlyingInstrumentName;
	}


	/**
	 * Sets the automaticTradeFlag.
	 * @param automaticTradeFlag The automaticTradeFlag to set
	 */
	public void setAutomaticTradeFlag(String automaticTradeFlag) {
		this.automaticTradeFlag = automaticTradeFlag;
	}


	/**
	 * Sets the instrumentId.
	 * @param instrumentId The instrumentId to set
	 */
	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	/**
	 * Sets the instrumentName.
	 * @param instrumentName The instrumentName to set
	 */
	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	/**
	 * Sets the internalInstrumentFlag.
	 * @param internalInstrumentFlag The internalInstrumentFlag to set
	 */
	public void setInternalInstrumentFlag(String internalInstrumentFlag) {
		this.internalInstrumentFlag = internalInstrumentFlag;
	}

	/**
	 * Sets the tradeType.
	 * @param tradeType The tradeType to set
	 */
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	/**
	 * Sets the underlyingInstrumentId.
	 * @param underlyingInstrumentId The underlyingInstrumentId to set
	 */
	public void setUnderlyingInstrumentId(String underlyingInstrumentId) {
		this.underlyingInstrumentId = underlyingInstrumentId;
	}

	/**
	 * Sets the underlyingInstrumentIsinCode.
	 * @param underlyingInstrumentIsinCode The underlyingInstrumentIsinCode to set
	 */
	public void setUnderlyingInstrumentIsinCode(String underlyingInstrumentIsinCode) {
		this.underlyingInstrumentIsinCode = underlyingInstrumentIsinCode;
	}

	/**
	 * Sets the underlyingInstrumentName.
	 * @param underlyingInstrumentName The underlyingInstrumentName to set
	 */
	public void setUnderlyingInstrumentName(String underlyingInstrumentName) {
		this.underlyingInstrumentName = underlyingInstrumentName;
	}


	@Override
    public boolean hasNoIsin() {
		return getSourceSystemInstrument() == null || getSourceSystemInstrument().length() == 0;
	}

	@Override
    public boolean isNonExchangeTraded() {
		return "XAB".equalsIgnoreCase(getSourceSystemExchange());
	}

	public boolean isGross() {
		return !isNet();
	}

	@Override
    public boolean isInternalInstrument() {
		return "Y".equalsIgnoreCase(getInternalInstrumentFlag());
	}

	@Override
    public boolean isAutomatic() {
		return "Y".equalsIgnoreCase(getAutomaticTradeFlag());
	}

	public boolean isStock() {
	    return "Stock".equals(getTradeType());
	}

    public boolean isOption() {
        return "Option".equals(getTradeType());
    }

    public boolean isSecurityLoan() {
        return "SecurityLoan".equals(getTradeType());
    }

    public boolean isFutureForward() {
        return "Future/Forward".equals(getTradeType());
    }

    public boolean isConvertible() {
        return "Convertible".equals(getTradeType());
    }

    public boolean isBond() {
        return "Bond".equals(getTradeType());
    }
    
    public boolean isCombination() {
        return "Combination".equals(getTradeType());
    }

    public boolean isCollateral() {
        return "Collateral".equals(getTradeType());
    }

    public boolean isCurr() {
        return "Curr".equals(getTradeType());
    }

    public boolean isDeposit() {
        return "Deposit".equals(getTradeType());
    }

    public boolean isEquityIndex() {
        return "EquityIndex".equals(getTradeType());
    }

    public boolean isSwap() {
        return "Swap".equals(getTradeType());
    }

    @Override
    public boolean isWarrant() {
        return "Warrant".equals(getTradeType());
    }

    public boolean isZero() {
        return "Zero".equals(getTradeType());
    }

    public boolean isUmbuchung() {
        return "UMBUCHUNG".equals(getTraderId());
    }

    public boolean isSell() {
		return !isBuy();
	}

	public boolean isSeloInstrument() {
		// Instrument name of the form "<CUR>/SELO/*"
		return getInstrumentName() != null && Pattern.matches(".{3}/SELO/.*", getInstrumentName());
	}

	public boolean isDepositInstrument() {
		// Instrument name of the form "<CUR>/DEP/*"
		return getInstrumentName() != null && Pattern.matches(".{3}/DEP/.*", getInstrumentName());
	}

	public boolean isBondInstrument() {
		// Instrument name of the form "<CUR>/BD/*"
		// or of the form "<number>%*" where <number> is a decimal number with "." as optional decimal point 
		return getInstrumentName() != null && 
		(Pattern.matches(".{3}/BD/.*", getInstrumentName()) || Pattern.matches("\\d+\\.?\\d*%.*", getInstrumentName()) );
	}

	public boolean isTradedOnCombinationExpriryDate() {
	    return DateTimeUtils.isSameDate(getTradeDate(), getExpiryCombinationDate());
	}
    
    public boolean isTradedOnOptionExpriryDate() {
        return DateTimeUtils.isSameDate(getTradeDate(), getExpiryOptionDate());
    }

    public boolean isTradedOnIssueDate() {
        return DateTimeUtils.isSameDate(getTradeDate(), getIssueDate());
    }
    
    public Calendar getCreationDateTime() {
        return creationDateTime;
    }

    
    public void setCreationDateTime(Calendar creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    
    public String getFreeText() {
        return freeText;
    }

    
    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    
    public Calendar getExpiryCombinationDate() {
        return expiryCombinationDate;
    }

    
    public void setExpiryCombinationDate(Calendar expiryCombinationDate) {
        this.expiryCombinationDate = expiryCombinationDate;
    }

    
    public Calendar getExpiryOptionDate() {
        return expiryOptionDate;
    }

    
    public void setExpiryOptionDate(Calendar expiryOptionDate) {
        this.expiryOptionDate = expiryOptionDate;
    }

    
    public Calendar getIssueDate() {
        return issueDate;
    }

    
    public void setIssueDate(Calendar issueDate) {
        this.issueDate = issueDate;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getSubType() {
        return subType;
    }

    public void setStrikePrice(double strikePrice) {
        this.strikePrice = strikePrice;
    }

    public double getStrikePrice() {
        return strikePrice;
    }

    public void setPutCallFlag(String putCallFlag) {
        this.putCallFlag = putCallFlag;
    }

    public String getPutCallFlag() {
        return putCallFlag;
    }
	
    public boolean isPut() {
        return "P".equals(getPutCallFlag());
    }

    public boolean isCall() {
        return "C".equals(getPutCallFlag());
    }

    public void setContractSize(double contractSize) {
        this.contractSize = contractSize;
    }

    public double getContractSize() {
        return contractSize;
    }

    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    public String getQuoteType() {
        return quoteType;
    }
}
