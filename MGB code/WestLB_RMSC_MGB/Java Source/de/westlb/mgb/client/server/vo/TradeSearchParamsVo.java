/**
 * TradeSearchParamsVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class TradeSearchParamsVo  implements java.io.Serializable {
    private java.util.Calendar fromDate;

    private java.util.Calendar fromJobCobDate;

    private java.lang.Boolean hasLowTurnover;

    private java.lang.Boolean hasPreDecessor;

    private java.lang.String[] instruments;

    private java.lang.Boolean isLateEntry;

    private java.lang.Boolean isManualCheckRequired;

    private java.lang.Boolean isManualCheckRequiredButNotHandled;

    private java.lang.Boolean isMarketDataRequestRequired;

    private java.lang.Boolean isReclamationClosed;

    private java.lang.Boolean isReclamationRequiredButNotHandled;

    private java.lang.Boolean isReclamationRequiredOpen;

    private java.lang.Boolean isSmallCustomer;

    private java.lang.Boolean isStorno;

    private java.lang.Long[] jobIds;

    private java.lang.String[] jobStatus;

    private java.lang.String locations;

    private java.lang.String mandantCode;

    private boolean searchInAllJobs;

    private java.lang.String sourceSystemCode;

    private java.lang.Long sparkassenId;

    private java.lang.String sparkassenReportType;

    private java.lang.String stateCode;

    private java.util.Calendar toDate;

    private java.util.Calendar toJobCobDate;

    private java.lang.String[] tradeIds;

    private java.util.Calendar fromJobCreationDate;

    private java.util.Calendar toJobCreationDate;

    private int maxResults;

    public TradeSearchParamsVo() {
    }

    public TradeSearchParamsVo(
           java.util.Calendar fromDate,
           java.util.Calendar fromJobCobDate,
           java.lang.Boolean hasLowTurnover,
           java.lang.Boolean hasPreDecessor,
           java.lang.String[] instruments,
           java.lang.Boolean isLateEntry,
           java.lang.Boolean isManualCheckRequired,
           java.lang.Boolean isManualCheckRequiredButNotHandled,
           java.lang.Boolean isMarketDataRequestRequired,
           java.lang.Boolean isReclamationClosed,
           java.lang.Boolean isReclamationRequiredButNotHandled,
           java.lang.Boolean isReclamationRequiredOpen,
           java.lang.Boolean isSmallCustomer,
           java.lang.Boolean isStorno,
           java.lang.Long[] jobIds,
           java.lang.String[] jobStatus,
           java.lang.String locations,
           java.lang.String mandantCode,
           boolean searchInAllJobs,
           java.lang.String sourceSystemCode,
           java.lang.Long sparkassenId,
           java.lang.String sparkassenReportType,
           java.lang.String stateCode,
           java.util.Calendar toDate,
           java.util.Calendar toJobCobDate,
           java.lang.String[] tradeIds,
           java.util.Calendar fromJobCreationDate,
           java.util.Calendar toJobCreationDate,
           int maxResults) {
           this.fromDate = fromDate;
           this.fromJobCobDate = fromJobCobDate;
           this.hasLowTurnover = hasLowTurnover;
           this.hasPreDecessor = hasPreDecessor;
           this.instruments = instruments;
           this.isLateEntry = isLateEntry;
           this.isManualCheckRequired = isManualCheckRequired;
           this.isManualCheckRequiredButNotHandled = isManualCheckRequiredButNotHandled;
           this.isMarketDataRequestRequired = isMarketDataRequestRequired;
           this.isReclamationClosed = isReclamationClosed;
           this.isReclamationRequiredButNotHandled = isReclamationRequiredButNotHandled;
           this.isReclamationRequiredOpen = isReclamationRequiredOpen;
           this.isSmallCustomer = isSmallCustomer;
           this.isStorno = isStorno;
           this.jobIds = jobIds;
           this.jobStatus = jobStatus;
           this.locations = locations;
           this.mandantCode = mandantCode;
           this.searchInAllJobs = searchInAllJobs;
           this.sourceSystemCode = sourceSystemCode;
           this.sparkassenId = sparkassenId;
           this.sparkassenReportType = sparkassenReportType;
           this.stateCode = stateCode;
           this.toDate = toDate;
           this.toJobCobDate = toJobCobDate;
           this.tradeIds = tradeIds;
           this.fromJobCreationDate = fromJobCreationDate;
           this.toJobCreationDate = toJobCreationDate;
           this.maxResults = maxResults;
    }


    /**
     * Gets the fromDate value for this TradeSearchParamsVo.
     * 
     * @return fromDate
     */
    public java.util.Calendar getFromDate() {
        return fromDate;
    }


    /**
     * Sets the fromDate value for this TradeSearchParamsVo.
     * 
     * @param fromDate
     */
    public void setFromDate(java.util.Calendar fromDate) {
        this.fromDate = fromDate;
    }


    /**
     * Gets the fromJobCobDate value for this TradeSearchParamsVo.
     * 
     * @return fromJobCobDate
     */
    public java.util.Calendar getFromJobCobDate() {
        return fromJobCobDate;
    }


    /**
     * Sets the fromJobCobDate value for this TradeSearchParamsVo.
     * 
     * @param fromJobCobDate
     */
    public void setFromJobCobDate(java.util.Calendar fromJobCobDate) {
        this.fromJobCobDate = fromJobCobDate;
    }


    /**
     * Gets the hasLowTurnover value for this TradeSearchParamsVo.
     * 
     * @return hasLowTurnover
     */
    public java.lang.Boolean getHasLowTurnover() {
        return hasLowTurnover;
    }


    /**
     * Sets the hasLowTurnover value for this TradeSearchParamsVo.
     * 
     * @param hasLowTurnover
     */
    public void setHasLowTurnover(java.lang.Boolean hasLowTurnover) {
        this.hasLowTurnover = hasLowTurnover;
    }


    /**
     * Gets the hasPreDecessor value for this TradeSearchParamsVo.
     * 
     * @return hasPreDecessor
     */
    public java.lang.Boolean getHasPreDecessor() {
        return hasPreDecessor;
    }


    /**
     * Sets the hasPreDecessor value for this TradeSearchParamsVo.
     * 
     * @param hasPreDecessor
     */
    public void setHasPreDecessor(java.lang.Boolean hasPreDecessor) {
        this.hasPreDecessor = hasPreDecessor;
    }


    /**
     * Gets the instruments value for this TradeSearchParamsVo.
     * 
     * @return instruments
     */
    public java.lang.String[] getInstruments() {
        return instruments;
    }


    /**
     * Sets the instruments value for this TradeSearchParamsVo.
     * 
     * @param instruments
     */
    public void setInstruments(java.lang.String[] instruments) {
        this.instruments = instruments;
    }


    /**
     * Gets the isLateEntry value for this TradeSearchParamsVo.
     * 
     * @return isLateEntry
     */
    public java.lang.Boolean getIsLateEntry() {
        return isLateEntry;
    }


    /**
     * Sets the isLateEntry value for this TradeSearchParamsVo.
     * 
     * @param isLateEntry
     */
    public void setIsLateEntry(java.lang.Boolean isLateEntry) {
        this.isLateEntry = isLateEntry;
    }


    /**
     * Gets the isManualCheckRequired value for this TradeSearchParamsVo.
     * 
     * @return isManualCheckRequired
     */
    public java.lang.Boolean getIsManualCheckRequired() {
        return isManualCheckRequired;
    }


    /**
     * Sets the isManualCheckRequired value for this TradeSearchParamsVo.
     * 
     * @param isManualCheckRequired
     */
    public void setIsManualCheckRequired(java.lang.Boolean isManualCheckRequired) {
        this.isManualCheckRequired = isManualCheckRequired;
    }


    /**
     * Gets the isManualCheckRequiredButNotHandled value for this TradeSearchParamsVo.
     * 
     * @return isManualCheckRequiredButNotHandled
     */
    public java.lang.Boolean getIsManualCheckRequiredButNotHandled() {
        return isManualCheckRequiredButNotHandled;
    }


    /**
     * Sets the isManualCheckRequiredButNotHandled value for this TradeSearchParamsVo.
     * 
     * @param isManualCheckRequiredButNotHandled
     */
    public void setIsManualCheckRequiredButNotHandled(java.lang.Boolean isManualCheckRequiredButNotHandled) {
        this.isManualCheckRequiredButNotHandled = isManualCheckRequiredButNotHandled;
    }


    /**
     * Gets the isMarketDataRequestRequired value for this TradeSearchParamsVo.
     * 
     * @return isMarketDataRequestRequired
     */
    public java.lang.Boolean getIsMarketDataRequestRequired() {
        return isMarketDataRequestRequired;
    }


    /**
     * Sets the isMarketDataRequestRequired value for this TradeSearchParamsVo.
     * 
     * @param isMarketDataRequestRequired
     */
    public void setIsMarketDataRequestRequired(java.lang.Boolean isMarketDataRequestRequired) {
        this.isMarketDataRequestRequired = isMarketDataRequestRequired;
    }


    /**
     * Gets the isReclamationClosed value for this TradeSearchParamsVo.
     * 
     * @return isReclamationClosed
     */
    public java.lang.Boolean getIsReclamationClosed() {
        return isReclamationClosed;
    }


    /**
     * Sets the isReclamationClosed value for this TradeSearchParamsVo.
     * 
     * @param isReclamationClosed
     */
    public void setIsReclamationClosed(java.lang.Boolean isReclamationClosed) {
        this.isReclamationClosed = isReclamationClosed;
    }


    /**
     * Gets the isReclamationRequiredButNotHandled value for this TradeSearchParamsVo.
     * 
     * @return isReclamationRequiredButNotHandled
     */
    public java.lang.Boolean getIsReclamationRequiredButNotHandled() {
        return isReclamationRequiredButNotHandled;
    }


    /**
     * Sets the isReclamationRequiredButNotHandled value for this TradeSearchParamsVo.
     * 
     * @param isReclamationRequiredButNotHandled
     */
    public void setIsReclamationRequiredButNotHandled(java.lang.Boolean isReclamationRequiredButNotHandled) {
        this.isReclamationRequiredButNotHandled = isReclamationRequiredButNotHandled;
    }


    /**
     * Gets the isReclamationRequiredOpen value for this TradeSearchParamsVo.
     * 
     * @return isReclamationRequiredOpen
     */
    public java.lang.Boolean getIsReclamationRequiredOpen() {
        return isReclamationRequiredOpen;
    }


    /**
     * Sets the isReclamationRequiredOpen value for this TradeSearchParamsVo.
     * 
     * @param isReclamationRequiredOpen
     */
    public void setIsReclamationRequiredOpen(java.lang.Boolean isReclamationRequiredOpen) {
        this.isReclamationRequiredOpen = isReclamationRequiredOpen;
    }


    /**
     * Gets the isSmallCustomer value for this TradeSearchParamsVo.
     * 
     * @return isSmallCustomer
     */
    public java.lang.Boolean getIsSmallCustomer() {
        return isSmallCustomer;
    }


    /**
     * Sets the isSmallCustomer value for this TradeSearchParamsVo.
     * 
     * @param isSmallCustomer
     */
    public void setIsSmallCustomer(java.lang.Boolean isSmallCustomer) {
        this.isSmallCustomer = isSmallCustomer;
    }


    /**
     * Gets the isStorno value for this TradeSearchParamsVo.
     * 
     * @return isStorno
     */
    public java.lang.Boolean getIsStorno() {
        return isStorno;
    }


    /**
     * Sets the isStorno value for this TradeSearchParamsVo.
     * 
     * @param isStorno
     */
    public void setIsStorno(java.lang.Boolean isStorno) {
        this.isStorno = isStorno;
    }


    /**
     * Gets the jobIds value for this TradeSearchParamsVo.
     * 
     * @return jobIds
     */
    public java.lang.Long[] getJobIds() {
        return jobIds;
    }


    /**
     * Sets the jobIds value for this TradeSearchParamsVo.
     * 
     * @param jobIds
     */
    public void setJobIds(java.lang.Long[] jobIds) {
        this.jobIds = jobIds;
    }


    /**
     * Gets the jobStatus value for this TradeSearchParamsVo.
     * 
     * @return jobStatus
     */
    public java.lang.String[] getJobStatus() {
        return jobStatus;
    }


    /**
     * Sets the jobStatus value for this TradeSearchParamsVo.
     * 
     * @param jobStatus
     */
    public void setJobStatus(java.lang.String[] jobStatus) {
        this.jobStatus = jobStatus;
    }


    /**
     * Gets the locations value for this TradeSearchParamsVo.
     * 
     * @return locations
     */
    public java.lang.String getLocations() {
        return locations;
    }


    /**
     * Sets the locations value for this TradeSearchParamsVo.
     * 
     * @param locations
     */
    public void setLocations(java.lang.String locations) {
        this.locations = locations;
    }


    /**
     * Gets the mandantCode value for this TradeSearchParamsVo.
     * 
     * @return mandantCode
     */
    public java.lang.String getMandantCode() {
        return mandantCode;
    }


    /**
     * Sets the mandantCode value for this TradeSearchParamsVo.
     * 
     * @param mandantCode
     */
    public void setMandantCode(java.lang.String mandantCode) {
        this.mandantCode = mandantCode;
    }


    /**
     * Gets the searchInAllJobs value for this TradeSearchParamsVo.
     * 
     * @return searchInAllJobs
     */
    public boolean isSearchInAllJobs() {
        return searchInAllJobs;
    }


    /**
     * Sets the searchInAllJobs value for this TradeSearchParamsVo.
     * 
     * @param searchInAllJobs
     */
    public void setSearchInAllJobs(boolean searchInAllJobs) {
        this.searchInAllJobs = searchInAllJobs;
    }


    /**
     * Gets the sourceSystemCode value for this TradeSearchParamsVo.
     * 
     * @return sourceSystemCode
     */
    public java.lang.String getSourceSystemCode() {
        return sourceSystemCode;
    }


    /**
     * Sets the sourceSystemCode value for this TradeSearchParamsVo.
     * 
     * @param sourceSystemCode
     */
    public void setSourceSystemCode(java.lang.String sourceSystemCode) {
        this.sourceSystemCode = sourceSystemCode;
    }


    /**
     * Gets the sparkassenId value for this TradeSearchParamsVo.
     * 
     * @return sparkassenId
     */
    public java.lang.Long getSparkassenId() {
        return sparkassenId;
    }


    /**
     * Sets the sparkassenId value for this TradeSearchParamsVo.
     * 
     * @param sparkassenId
     */
    public void setSparkassenId(java.lang.Long sparkassenId) {
        this.sparkassenId = sparkassenId;
    }


    /**
     * Gets the sparkassenReportType value for this TradeSearchParamsVo.
     * 
     * @return sparkassenReportType
     */
    public java.lang.String getSparkassenReportType() {
        return sparkassenReportType;
    }


    /**
     * Sets the sparkassenReportType value for this TradeSearchParamsVo.
     * 
     * @param sparkassenReportType
     */
    public void setSparkassenReportType(java.lang.String sparkassenReportType) {
        this.sparkassenReportType = sparkassenReportType;
    }


    /**
     * Gets the stateCode value for this TradeSearchParamsVo.
     * 
     * @return stateCode
     */
    public java.lang.String getStateCode() {
        return stateCode;
    }


    /**
     * Sets the stateCode value for this TradeSearchParamsVo.
     * 
     * @param stateCode
     */
    public void setStateCode(java.lang.String stateCode) {
        this.stateCode = stateCode;
    }


    /**
     * Gets the toDate value for this TradeSearchParamsVo.
     * 
     * @return toDate
     */
    public java.util.Calendar getToDate() {
        return toDate;
    }


    /**
     * Sets the toDate value for this TradeSearchParamsVo.
     * 
     * @param toDate
     */
    public void setToDate(java.util.Calendar toDate) {
        this.toDate = toDate;
    }


    /**
     * Gets the toJobCobDate value for this TradeSearchParamsVo.
     * 
     * @return toJobCobDate
     */
    public java.util.Calendar getToJobCobDate() {
        return toJobCobDate;
    }


    /**
     * Sets the toJobCobDate value for this TradeSearchParamsVo.
     * 
     * @param toJobCobDate
     */
    public void setToJobCobDate(java.util.Calendar toJobCobDate) {
        this.toJobCobDate = toJobCobDate;
    }


    /**
     * Gets the tradeIds value for this TradeSearchParamsVo.
     * 
     * @return tradeIds
     */
    public java.lang.String[] getTradeIds() {
        return tradeIds;
    }


    /**
     * Sets the tradeIds value for this TradeSearchParamsVo.
     * 
     * @param tradeIds
     */
    public void setTradeIds(java.lang.String[] tradeIds) {
        this.tradeIds = tradeIds;
    }


    /**
     * Gets the fromJobCreationDate value for this TradeSearchParamsVo.
     * 
     * @return fromJobCreationDate
     */
    public java.util.Calendar getFromJobCreationDate() {
        return fromJobCreationDate;
    }


    /**
     * Sets the fromJobCreationDate value for this TradeSearchParamsVo.
     * 
     * @param fromJobCreationDate
     */
    public void setFromJobCreationDate(java.util.Calendar fromJobCreationDate) {
        this.fromJobCreationDate = fromJobCreationDate;
    }


    /**
     * Gets the toJobCreationDate value for this TradeSearchParamsVo.
     * 
     * @return toJobCreationDate
     */
    public java.util.Calendar getToJobCreationDate() {
        return toJobCreationDate;
    }


    /**
     * Sets the toJobCreationDate value for this TradeSearchParamsVo.
     * 
     * @param toJobCreationDate
     */
    public void setToJobCreationDate(java.util.Calendar toJobCreationDate) {
        this.toJobCreationDate = toJobCreationDate;
    }


    /**
     * Gets the maxResults value for this TradeSearchParamsVo.
     * 
     * @return maxResults
     */
    public int getMaxResults() {
        return maxResults;
    }


    /**
     * Sets the maxResults value for this TradeSearchParamsVo.
     * 
     * @param maxResults
     */
    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TradeSearchParamsVo)) return false;
        TradeSearchParamsVo other = (TradeSearchParamsVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fromDate==null && other.getFromDate()==null) || 
             (this.fromDate!=null &&
              this.fromDate.equals(other.getFromDate()))) &&
            ((this.fromJobCobDate==null && other.getFromJobCobDate()==null) || 
             (this.fromJobCobDate!=null &&
              this.fromJobCobDate.equals(other.getFromJobCobDate()))) &&
            ((this.hasLowTurnover==null && other.getHasLowTurnover()==null) || 
             (this.hasLowTurnover!=null &&
              this.hasLowTurnover.equals(other.getHasLowTurnover()))) &&
            ((this.hasPreDecessor==null && other.getHasPreDecessor()==null) || 
             (this.hasPreDecessor!=null &&
              this.hasPreDecessor.equals(other.getHasPreDecessor()))) &&
            ((this.instruments==null && other.getInstruments()==null) || 
             (this.instruments!=null &&
              java.util.Arrays.equals(this.instruments, other.getInstruments()))) &&
            ((this.isLateEntry==null && other.getIsLateEntry()==null) || 
             (this.isLateEntry!=null &&
              this.isLateEntry.equals(other.getIsLateEntry()))) &&
            ((this.isManualCheckRequired==null && other.getIsManualCheckRequired()==null) || 
             (this.isManualCheckRequired!=null &&
              this.isManualCheckRequired.equals(other.getIsManualCheckRequired()))) &&
            ((this.isManualCheckRequiredButNotHandled==null && other.getIsManualCheckRequiredButNotHandled()==null) || 
             (this.isManualCheckRequiredButNotHandled!=null &&
              this.isManualCheckRequiredButNotHandled.equals(other.getIsManualCheckRequiredButNotHandled()))) &&
            ((this.isMarketDataRequestRequired==null && other.getIsMarketDataRequestRequired()==null) || 
             (this.isMarketDataRequestRequired!=null &&
              this.isMarketDataRequestRequired.equals(other.getIsMarketDataRequestRequired()))) &&
            ((this.isReclamationClosed==null && other.getIsReclamationClosed()==null) || 
             (this.isReclamationClosed!=null &&
              this.isReclamationClosed.equals(other.getIsReclamationClosed()))) &&
            ((this.isReclamationRequiredButNotHandled==null && other.getIsReclamationRequiredButNotHandled()==null) || 
             (this.isReclamationRequiredButNotHandled!=null &&
              this.isReclamationRequiredButNotHandled.equals(other.getIsReclamationRequiredButNotHandled()))) &&
            ((this.isReclamationRequiredOpen==null && other.getIsReclamationRequiredOpen()==null) || 
             (this.isReclamationRequiredOpen!=null &&
              this.isReclamationRequiredOpen.equals(other.getIsReclamationRequiredOpen()))) &&
            ((this.isSmallCustomer==null && other.getIsSmallCustomer()==null) || 
             (this.isSmallCustomer!=null &&
              this.isSmallCustomer.equals(other.getIsSmallCustomer()))) &&
            ((this.isStorno==null && other.getIsStorno()==null) || 
             (this.isStorno!=null &&
              this.isStorno.equals(other.getIsStorno()))) &&
            ((this.jobIds==null && other.getJobIds()==null) || 
             (this.jobIds!=null &&
              java.util.Arrays.equals(this.jobIds, other.getJobIds()))) &&
            ((this.jobStatus==null && other.getJobStatus()==null) || 
             (this.jobStatus!=null &&
              java.util.Arrays.equals(this.jobStatus, other.getJobStatus()))) &&
            ((this.locations==null && other.getLocations()==null) || 
             (this.locations!=null &&
              this.locations.equals(other.getLocations()))) &&
            ((this.mandantCode==null && other.getMandantCode()==null) || 
             (this.mandantCode!=null &&
              this.mandantCode.equals(other.getMandantCode()))) &&
            this.searchInAllJobs == other.isSearchInAllJobs() &&
            ((this.sourceSystemCode==null && other.getSourceSystemCode()==null) || 
             (this.sourceSystemCode!=null &&
              this.sourceSystemCode.equals(other.getSourceSystemCode()))) &&
            ((this.sparkassenId==null && other.getSparkassenId()==null) || 
             (this.sparkassenId!=null &&
              this.sparkassenId.equals(other.getSparkassenId()))) &&
            ((this.sparkassenReportType==null && other.getSparkassenReportType()==null) || 
             (this.sparkassenReportType!=null &&
              this.sparkassenReportType.equals(other.getSparkassenReportType()))) &&
            ((this.stateCode==null && other.getStateCode()==null) || 
             (this.stateCode!=null &&
              this.stateCode.equals(other.getStateCode()))) &&
            ((this.toDate==null && other.getToDate()==null) || 
             (this.toDate!=null &&
              this.toDate.equals(other.getToDate()))) &&
            ((this.toJobCobDate==null && other.getToJobCobDate()==null) || 
             (this.toJobCobDate!=null &&
              this.toJobCobDate.equals(other.getToJobCobDate()))) &&
            ((this.tradeIds==null && other.getTradeIds()==null) || 
             (this.tradeIds!=null &&
              java.util.Arrays.equals(this.tradeIds, other.getTradeIds()))) &&
            ((this.fromJobCreationDate==null && other.getFromJobCreationDate()==null) || 
             (this.fromJobCreationDate!=null &&
              this.fromJobCreationDate.equals(other.getFromJobCreationDate()))) &&
            ((this.toJobCreationDate==null && other.getToJobCreationDate()==null) || 
             (this.toJobCreationDate!=null &&
              this.toJobCreationDate.equals(other.getToJobCreationDate()))) &&
            this.maxResults == other.getMaxResults();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getFromDate() != null) {
            _hashCode += getFromDate().hashCode();
        }
        if (getFromJobCobDate() != null) {
            _hashCode += getFromJobCobDate().hashCode();
        }
        if (getHasLowTurnover() != null) {
            _hashCode += getHasLowTurnover().hashCode();
        }
        if (getHasPreDecessor() != null) {
            _hashCode += getHasPreDecessor().hashCode();
        }
        if (getInstruments() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getInstruments());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getInstruments(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIsLateEntry() != null) {
            _hashCode += getIsLateEntry().hashCode();
        }
        if (getIsManualCheckRequired() != null) {
            _hashCode += getIsManualCheckRequired().hashCode();
        }
        if (getIsManualCheckRequiredButNotHandled() != null) {
            _hashCode += getIsManualCheckRequiredButNotHandled().hashCode();
        }
        if (getIsMarketDataRequestRequired() != null) {
            _hashCode += getIsMarketDataRequestRequired().hashCode();
        }
        if (getIsReclamationClosed() != null) {
            _hashCode += getIsReclamationClosed().hashCode();
        }
        if (getIsReclamationRequiredButNotHandled() != null) {
            _hashCode += getIsReclamationRequiredButNotHandled().hashCode();
        }
        if (getIsReclamationRequiredOpen() != null) {
            _hashCode += getIsReclamationRequiredOpen().hashCode();
        }
        if (getIsSmallCustomer() != null) {
            _hashCode += getIsSmallCustomer().hashCode();
        }
        if (getIsStorno() != null) {
            _hashCode += getIsStorno().hashCode();
        }
        if (getJobIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getJobIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getJobIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getJobStatus() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getJobStatus());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getJobStatus(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLocations() != null) {
            _hashCode += getLocations().hashCode();
        }
        if (getMandantCode() != null) {
            _hashCode += getMandantCode().hashCode();
        }
        _hashCode += (isSearchInAllJobs() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getSourceSystemCode() != null) {
            _hashCode += getSourceSystemCode().hashCode();
        }
        if (getSparkassenId() != null) {
            _hashCode += getSparkassenId().hashCode();
        }
        if (getSparkassenReportType() != null) {
            _hashCode += getSparkassenReportType().hashCode();
        }
        if (getStateCode() != null) {
            _hashCode += getStateCode().hashCode();
        }
        if (getToDate() != null) {
            _hashCode += getToDate().hashCode();
        }
        if (getToJobCobDate() != null) {
            _hashCode += getToJobCobDate().hashCode();
        }
        if (getTradeIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTradeIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTradeIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFromJobCreationDate() != null) {
            _hashCode += getFromJobCreationDate().hashCode();
        }
        if (getToJobCreationDate() != null) {
            _hashCode += getToJobCreationDate().hashCode();
        }
        _hashCode += getMaxResults();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TradeSearchParamsVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "TradeSearchParamsVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fromDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromJobCobDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fromJobCobDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hasLowTurnover");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hasLowTurnover"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hasPreDecessor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hasPreDecessor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instruments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "instruments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isLateEntry");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isLateEntry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isManualCheckRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isManualCheckRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isManualCheckRequiredButNotHandled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isManualCheckRequiredButNotHandled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isMarketDataRequestRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isMarketDataRequestRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isReclamationClosed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isReclamationClosed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isReclamationRequiredButNotHandled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isReclamationRequiredButNotHandled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isReclamationRequiredOpen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isReclamationRequiredOpen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isSmallCustomer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isSmallCustomer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isStorno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isStorno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobIds");
        elemField.setXmlName(new javax.xml.namespace.QName("", "jobIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "jobStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locations");
        elemField.setXmlName(new javax.xml.namespace.QName("", "locations"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mandantCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mandantCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchInAllJobs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "searchInAllJobs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceSystemCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceSystemCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sparkassenId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sparkassenId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sparkassenReportType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sparkassenReportType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stateCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stateCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("toDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "toDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("toJobCobDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "toJobCobDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeIds");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromJobCreationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fromJobCreationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("toJobCreationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "toJobCreationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxResults");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maxResults"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
