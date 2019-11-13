/**
 * Mgb.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package de.westlb.mgb.client.server;

import java.io.Serializable;
import java.rmi.RemoteException;

import de.westlb.mgb.client.server.vo.AddonSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.AddonVo;
import de.westlb.mgb.client.server.vo.AutoCheckVo;
import de.westlb.mgb.client.server.vo.BloombergCurrencyCodeVo;
import de.westlb.mgb.client.server.vo.BloombergMaturityCodeVo;
import de.westlb.mgb.client.server.vo.BookSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.CheckStateVo;
import de.westlb.mgb.client.server.vo.ConditionVo;
import de.westlb.mgb.client.server.vo.DataSelectionVo;
import de.westlb.mgb.client.server.vo.DualControlJobSearchParamsVo;
import de.westlb.mgb.client.server.vo.DualControlJobSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.DualControlJobVo;
import de.westlb.mgb.client.server.vo.EmployeeSearchParamsVo;
import de.westlb.mgb.client.server.vo.EmployeeSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.EmployeeVo;
import de.westlb.mgb.client.server.vo.ExchangeMappingVo;
import de.westlb.mgb.client.server.vo.ExchangeSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.ExchangeVo;
import de.westlb.mgb.client.server.vo.InboxMailVo;
import de.westlb.mgb.client.server.vo.InstrumentSearchParamsVo;
import de.westlb.mgb.client.server.vo.InstrumentSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.InstrumentVo;
import de.westlb.mgb.client.server.vo.JobSearchParamsVo;
import de.westlb.mgb.client.server.vo.JobVo;
import de.westlb.mgb.client.server.vo.MailSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.MailVo;
import de.westlb.mgb.client.server.vo.MandantVo;
import de.westlb.mgb.client.server.vo.MgbConfigurationVo;
import de.westlb.mgb.client.server.vo.MgbTaskVo;
import de.westlb.mgb.client.server.vo.PriceCheckCategoryVo;
import de.westlb.mgb.client.server.vo.PriceDeviationStatisticVo;
import de.westlb.mgb.client.server.vo.PriceDeviationVo;
import de.westlb.mgb.client.server.vo.PriceVo;
import de.westlb.mgb.client.server.vo.RepoTradeOverviewVo;
import de.westlb.mgb.client.server.vo.RequestVo;
import de.westlb.mgb.client.server.vo.SendMailParamsVo;
import de.westlb.mgb.client.server.vo.SessionInfoVo;
import de.westlb.mgb.client.server.vo.SourceSystemVo;
import de.westlb.mgb.client.server.vo.StateCodeVo;
import de.westlb.mgb.client.server.vo.StateRulesVo;
import de.westlb.mgb.client.server.vo.StateStatisticEntryVo;
import de.westlb.mgb.client.server.vo.StatisticCubeReportVo;
import de.westlb.mgb.client.server.vo.StatisticReportParamsVo;
import de.westlb.mgb.client.server.vo.StatisticReportVo;
import de.westlb.mgb.client.server.vo.StornoGroupVo;
import de.westlb.mgb.client.server.vo.TradeHistoryEntryVo;
import de.westlb.mgb.client.server.vo.TradeOverviewVo;
import de.westlb.mgb.client.server.vo.TradeParameterVo;
import de.westlb.mgb.client.server.vo.TradeReclRequiredVo;
import de.westlb.mgb.client.server.vo.TradeReclamationVo;
import de.westlb.mgb.client.server.vo.TradeSearchParamsVo;
import de.westlb.mgb.client.server.vo.TradeSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.TraderSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.UserStatisticVo;

/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface Mgb extends java.rmi.Remote, Serializable {

    /**
     * Method login. Login with a NT-account ntId.
     * @param ntId
     * @param mandant might be null
     * @param comment might be null
     * @throws RemoteException
     */
    public void login(String ntId, String mandant, String comment) throws RemoteException;

    public void close() throws RemoteException;

	/**
	 * Method returns the NT-account with the optional domain.
	 * E.g. 'GSA-WLB/d055625'
	 * @return ntId
	 */
    public String getDomainUser() throws java.rmi.RemoteException;

	/**
	 * Method login. Login with a NT-account ntId.
	 * Authenticats user against technical password. The function is used
     * for test and support purposes.
	 * @param ntId
	 * @return boolean
	 * @throws RemoteException
	 */
    public boolean adminLogin(String ntId, String password) throws RemoteException;

	/**
	 * Method deleteAddon. Deletes an Addon with the ID addonId.
	 * @param addonId Internal MGB id.
	 * @throws RemoteException
	 */
    public void deleteAddon(Long addonId) throws RemoteException;
    
	/**
	 * Method getUserStatistic.
	 * @return UserStatisticVo[]
	 * @throws RemoteException
	 */
    public UserStatisticVo[] getUserStatistic() throws RemoteException;

	/**
	 * Method deleteJob. Deletes a Job with the ID jobId.
	 * @param jobId Internal MGB id.
	 * @throws RemoteException
	 */
    public void deleteJob(Long jobId) throws RemoteException;

	/**
	 * Method deleteTrader. Deassigns the trader code of traderId from the employee and deletes it when it
     * is not referenced.
	 * @param traderId Internal MGB id.
	 * @throws RemoteException
	 */
    public void deleteTrader(Long traderId) throws RemoteException;

	/**
	 * Method deleteEmployee. Deletes an employee with the ID employeeId.
	 * @param employeeId Internal MGB id.
	 * @throws RemoteException
	 */
    public void deleteEmployee(Long employeeId) throws RemoteException;

	/**
	 * Method deleteExchange. Deletes an exchange with the ID exchangeId.
	 * @param exchangeId Internal MGB id.
	 * @throws RemoteException
	 */
    public void deleteExchange(Long exchangeId) throws RemoteException;

	/**
	 * Method deleteExchangeMapping. Deletes an exchange mapping with the ID exchangeMappingId.
	 * @param exchangeMappingId Internal MGB id.
	 * @throws RemoteException
	 */
    public void deleteExchangeMapping(Long exchangeMappingId) throws RemoteException;

	/**
	 * Method deletePriceCheckCategory. Deletes a price check category with the ID priceCheckCategoryId.
	 * @param priceCheckCategoryId Internal MGB id.
	 * @throws RemoteException
	 */
    public void deletePriceCheckCategory(Long priceCheckCategoryId) throws RemoteException;

	/**
	 * Method deleteStateCode. Deletes the state code with the given code in the currently selected mandant.
	 * @param code
	 * @throws RemoteException
	 */
    public void deleteStateCode(String code) throws RemoteException;

	/**
	 * Method findAllAddons.
	 * @return AddonSearchResultEntryVo[]
	 * @throws RemoteException
	 */
    public AddonSearchResultEntryVo[] findAllAddons() throws RemoteException;

	/**
	 * Method findAllBloombergMaturityCodes. Returns an array of all BloombergMaturityCodes.
	 * @return BloombergMaturityCodeVo[]
	 * @throws RemoteException
	 */
     public BloombergMaturityCodeVo[] findAllBloombergMaturityCodes() throws RemoteException;

	/**
	 * Method findAllBloombergCurrencyCodes. Returns an array of all BloombergCurrencyCodes.
	 * @return BloombergCurrencyCodeVo[]
	 * @throws RemoteException
	 */
    public BloombergCurrencyCodeVo[] findAllBloombergCurrencyCodes() throws RemoteException;

	/**
	 * Method findEmployeesByReclamationRequired. Returns an array of employees/traders, whose trades have been reclaimed.
	 * @return EmployeeSearchResultEntryVo[]
	 * @throws RemoteException
	 */
    public EmployeeSearchResultEntryVo[] findEmployeesByReclamationRequired() throws RemoteException;

	/**
	 * Method findInstruments. Returns an array of instruments dependend on searchParams.
	 * @param searchParams
	 * @return InstrumentSearchResultEntryVo[]
	 * @throws RemoteException
	 */
     public InstrumentSearchResultEntryVo[] findInstruments(InstrumentSearchParamsVo searchParams) throws RemoteException;

	/**
	 * Finds trades that have been imported in the last 12 months where trader location doesn't occur in any report as defined by {@link  de.westlb.mgb.model.impl.BatchStatisticReportImpl}.
	 * @return Array of {@link TradeSearchResultEntryVo} objects
	 * @throws RemoteException
	 */
	public TradeSearchResultEntryVo[] findNewLocations() throws RemoteException;
     
	/**
	 * Method findNewInstruments. Returns an array of instruments with no check parameter assigned.
	 * @return InstrumentSearchResultEntryVo[]
	 * @throws RemoteException
	 */
    public InstrumentSearchResultEntryVo[] findNewInstruments() throws RemoteException;

	/**
	 * Method findTradesLateReclReqByEmployee. Returns an array of trades owned by an employee 
	 * in state "LATE ENTERED" and a reclamation is required.
	 * @param employeeId Internal MGB id.
	 * @return TradeSearchResultEntryVo[]
	 * @throws RemoteException
	 */
   public TradeSearchResultEntryVo[] findTradesLateReclReqByEmployee(Long employeeId) throws RemoteException;

	/**
	 * Method findTradesMiscReclReqByEmployee. Returns an array of trades in state owned by an employee 
	 * where a reclamation is required and the
     * reason is not "LATE ENTERED".
	 * @param employeeId Internal MGB id.
	 * @return TradeSearchResultEntryVo[]
	 * @throws RemoteException
	 */
    public TradeSearchResultEntryVo[] findTradesMiscReclReqByEmployee(Long employeeId) throws RemoteException;

	/**
	 * Method findDualControlJobs. Finds all open DualControlJobs entries.
	 * @param searchParams
	 * @return DualControlJobSearchResultEntryVo[]
	 * @throws RemoteException
	 */
    public DualControlJobSearchResultEntryVo[] findDualControlJobs(DualControlJobSearchParamsVo searchParams) throws RemoteException;

	/**
	 * Method getDualControlJobVo. Returns the DualControlJobVo with the id dualControlJobId.
	 * @param dualControlJobId Internal MGB id.
	 * @return DualControlJobVo
	 * @throws RemoteException
	 */
    public DualControlJobVo getDualControlJobVo(Long dualControlJobId) throws RemoteException;

	/**
	 * Method getExchange. Returns the ExchangeVo with the id exchangeId.
	 * @param exchangeId Internal MGB id.
	 * @return ExchangeVo
	 * @throws RemoteException
	 */
    public ExchangeVo getExchange(Long exchangeId) throws RemoteException;
    
	/**
	 * Method getInstrument. Returns the DualControlJobVo with the id dualControlJobId;
	 * @param instrumentId Internal MGB id.
	 * @return InstrumentVo
	 * @throws RemoteException
	 */
     public InstrumentVo getInstrument(Long instrumentId) throws RemoteException;

	/**
	 * Method getStateCode. Returns state code data with the id code.
	 * @param code Internal MGB code.
	 * @return StateCodeVo
	 * @throws RemoteException
	 */
    public StateCodeVo getStateCode(String code) throws RemoteException;

	/**
	 * Method assignPriceCheckCategoryToInstrument. Changes the association of an instrument to a price check category.
	 * @param priceCheckCategoryId Internal MGB id.
	 * @param instrumentId Internal MGB id.
	 * @throws RemoteException
	 */
    public void assignPriceCheckCategoryToInstrument(Long priceCheckCategoryId, Long instrumentId) throws RemoteException;

	/**
	 * Method findAllExchanges. Returns an array of all exchanges.
	 * @return ExchangeSearchResultEntryVo[]
	 * @throws RemoteException
	 */
    public ExchangeSearchResultEntryVo[] findAllExchanges() throws RemoteException;

	/**
	 * Method findAllJobs. Returns an array of all jobs.
	 * @return JobVo[]
	 * @throws RemoteException
	 */
    public JobVo[] findAllJobs(JobSearchParamsVo paramVo) throws RemoteException;

	/**
	 * Method findAllPriceCheckCategories. Returns an array of all price check categories.
	 * @return PriceCheckCategoryVo[]
	 * @throws RemoteException
	 */
    public PriceCheckCategoryVo[] findAllPriceCheckCategories() throws RemoteException;

	/**
	 * Method findAllSourceSystems. Returns an array of all Source systems.
	 * @return SourceSystemVo[]
	 * @throws RemoteException
	 */
	public SourceSystemVo[] findAllSourceSystems() throws RemoteException;

	/**
	 * Method findAllConditions. Returns an array of all conditions.
	 * @return ConditionVo[]
	 * @throws RemoteException
	 */
    public ConditionVo[] findAllConditions() throws RemoteException;

	/**
	 * Method getAddon. Returns the addon with the id addonId.
	 * @param addonId Internal MGB id.
	 * @return AddonVo
	 * @throws RemoteException
	 */
    public AddonVo getAddon(Long addonId) throws RemoteException;

	/**
	 * Method getDataSelection. Returns the data that defines the trades/jobs that are currently selected by the user.
	 * @return DataSelectionVo
	 * @throws RemoteException
	 */
    public DataSelectionVo getCurrentDataSelection() throws java.rmi.RemoteException;

    /**
	 * Method getDataSelection. Returns the data that defines the trades/jobs that are selected by the user.
	 * @return DataSelectionVo
	 * @throws RemoteException
	 */
    public DataSelectionVo getDataSelection() throws RemoteException;
    
	/**
	 * Method setDataSelection. Sets the data that defines the trades/jobs that are selected by the user.
	 * @param dataSelection
	 * @throws RemoteException
	 */
    public void setDataSelection(DataSelectionVo dataSelection) throws RemoteException;
    
	/**
	 * Method getStateCodes.
	 * @param in0
	 * @return StateCodeVo[]
	 * @throws RemoteException
	 */

    public StateCodeVo[] getStateCodes(String in0) throws RemoteException;
	/**
	 * Method getCheckState.
	 * @return CheckStateVo
	 * @throws RemoteException
	 */
    public CheckStateVo getCheckState() throws RemoteException;

	/**
	 * Returns statistic information for a list of jobs.
	 * 
     * @param jobIds
     * @return
     * @throws RemoteException
     */
    public CheckStateVo getCheckState(Long[] jobIds) throws RemoteException;

	/**
	 * Method findTrades.
	 * @param tradeSearchParam
	 * @return TradeSearchResultEntryVo[]
	 * @throws RemoteException
	 */
    public TradeSearchResultEntryVo[] findTrades(TradeSearchParamsVo tradeSearchParam) throws RemoteException;
    
	/**
	 * Method findTradesOverviewVo.
	 * @param tradeSearchParam
	 * @return TradeOverviewVo[]
	 * @throws RemoteException
	 */
    /**
     * Same functionality as findTrades, but returns TraderViewVo objects.
     */
    public TradeOverviewVo[] findTradesOverviewVo(TradeSearchParamsVo tradeSearchParam) throws RemoteException;
	/**
	 * Method getTradeOverview.
	 * @param tradeId
	 * @return TradeOverviewVo
	 * @throws RemoteException
	 */
    public TradeOverviewVo getTradeOverview(Object tradeId) throws RemoteException;
	/**
	 * Method getTradeReclamation.
	 * @param in0
	 * @return TradeReclamationVo
	 * @throws RemoteException
	 */
    public TradeReclamationVo getTradeReclamation(Object in0) throws RemoteException;
	/**
	 * Method getStateStatistic.
	 * @param in0
	 * @return StateStatisticEntryVo
	 * @throws RemoteException
	 */
    public StateStatisticEntryVo getStateStatistic(String in0) throws RemoteException;
	/**
	 * Method getHistory.
	 * @param in0
	 * @return TradeHistoryEntryVo[]
	 * @throws RemoteException
	 */
    public TradeHistoryEntryVo[] getHistory(Object in0) throws RemoteException;
    
	/**
	 * Method getTradeParameter.
	 * @param in0
	 * @return TradeParameterVo
	 * @throws RemoteException
	 */
    public TradeParameterVo getTradeParameter(Object in0) throws RemoteException;
	/**
	 * Method getCurrentReclamationState.
	 * @param tradeId
	 * @return TradeHistoryEntryVo
	 * @throws RemoteException
	 */
    public TradeHistoryEntryVo getCurrentReclamationState(Long tradeId) throws RemoteException;
    
	/**
	 * Method createNewJob.
	 * @param sourceSystemCode
	 * @param tradeData
	 * @throws RemoteException
	 */
    /**
     * Queues the trade data for an import 
     * @param sourceSystemCode
     * @param tradeData
     * @throws RemoteException
     */
    public void createNewJob(String sourceSystemCode, byte[] tradeData) throws RemoteException;
    
	/**
	 * Method saveStateCode.
	 * @param stateCodeVo
	 * @throws RemoteException
	 */
    /**
     * Updates the data of a StateCode or creates a new entry, if the flag createNew=true.
     */
    public void saveStateCode(StateCodeVo stateCodeVo) throws RemoteException;

	/**
	 * Method saveTradeState.
	 * @param tradeHistEntry
	 * @param attachmentContent
	 * @throws RemoteException
	 */
    public void saveTradeState(TradeHistoryEntryVo tradeHistEntry, byte[] attachmentContent) throws RemoteException;
    
    
	/**
	 * Saves a new Trade state for a give list of trades. If attachment
	 * is not null the attachment is appended to all states.
	 * 
     * @param tradeIds
     * @param tradeHistoryEntry
     * @param attachment
     * @throws RemoteException
     */
    public void saveTradeState(Long[] tradeIds, TradeHistoryEntryVo tradeHistoryEntry, byte[] attachment) throws RemoteException;

	/**
	 * Method startReadAttachment.
	 * @param attachmentId
	 * @return long
	 * @throws RemoteException
	 */
    public long startReadAttachment(Long attachmentId) throws RemoteException;
	/**
	 * Method readAttachment.
	 * @param attachmentId
	 * @param startBytePos
	 * @param stopBytePos
	 * @return byte[]
	 * @throws RemoteException
	 */
    public byte[] readAttachment(Long attachmentId, long startBytePos, long stopBytePos) throws RemoteException;
	/**
	 * Method endReadAttachment.
	 * @param attachmentId
	 * @throws RemoteException
	 */
    public void endReadAttachment(Long attachmentId) throws RemoteException;
	/**
	 * Method closeReclamation.
	 * @param tradeId
	 * @param closeComment
	 * @throws RemoteException
	 */
    public void closeReclamation(Long tradeId, String closeComment) throws RemoteException;
	/**
	 * Method findNewTrader.
	 * @return TraderSearchResultEntryVo[]
	 * @throws RemoteException
	 */
    public TraderSearchResultEntryVo[] findNewTrader() throws RemoteException;
	/**
	 * Method findEmployees.
	 * @param in0
	 * @return EmployeeSearchResultEntryVo[]
	 * @throws RemoteException
	 */
    public EmployeeSearchResultEntryVo[] findEmployees(EmployeeSearchParamsVo in0) throws RemoteException;
	/**
	 * Method assignTraderToEmployee.
	 * @param employeeId
	 * @param traderId
	 * @throws RemoteException
	 */
    public void assignTraderToEmployee(Long employeeId, Long traderId) throws RemoteException;
	/**
	 * Method getEmployee.
	 * @param employeeId
	 * @return EmployeeVo
	 * @throws RemoteException
	 */
    public EmployeeVo getEmployee(Long employeeId) throws RemoteException;

	/**
	 * Method saveAddon.
	 * @param addon
	 * @throws RemoteException
	 */
    public void saveAddon(AddonVo addon) throws RemoteException;

	/**
	 * Method saveEmployee.
	 * @param employee
	 * @throws RemoteException
	 */
    public Long saveEmployee(EmployeeVo employee) throws RemoteException;

	/**
	 * Method saveExchange.
	 * @param exchangeVo
	 * @throws RemoteException
	 */
    public void saveExchange(ExchangeVo exchangeVo) throws RemoteException;

	/**
	 * Method saveExchangeMapping.
	 * @param exchangeMappingVo
	 * @throws RemoteException
	 */
    public void saveExchangeMapping(ExchangeMappingVo exchangeMappingVo) throws RemoteException;

	/**
	 * Method getSessionInfo.
	 * @return SessionInfoVo
	 * @throws RemoteException
	 */
	public SessionInfoVo getSessionInfo() throws RemoteException;

	/**
	 * Method getPriceCheckCategory.
	 * @param priceCheckCategoryId
	 * @return PriceCheckCategoryVo
	 * @throws RemoteException
	 */
    public PriceCheckCategoryVo getPriceCheckCategory(Long priceCheckCategoryId) throws RemoteException;

	/**
	 * Method getPrice.
	 * @param request
	 * @return PriceVo
	 * @throws RemoteException
	 */
    public PriceVo getPrice(RequestVo request) throws RemoteException;
	/**
	 * Method savePrice.
	 * @param request
	 * @param price
	 * @throws RemoteException
	 */
    public void savePrice(RequestVo request, PriceVo price) throws RemoteException;

	/**
	 * Method savePrices.
	 * @param requests
	 * @param prices
	 * @throws RemoteException
	 */
    public void savePrices(RequestVo[] requests, PriceVo[] prices) throws RemoteException;

	/**
	 * Method savePriceCheckCategory.
	 * @param priceCheckCategory
	 * @throws RemoteException
	 */
    public void savePriceCheckCategory(PriceCheckCategoryVo priceCheckCategory) throws RemoteException;

	/**
	 * Method getUnsolvedRequests.
	 * @param requestClassName
	 * @return RequestVo[]
	 * @throws RemoteException
	 */
    public RequestVo[] getUnsolvedRequests(String requestClassName) throws RemoteException;
    
	/**
	 * Method getUnvalidatedRequests.
	 * @param requestClassName
	 * @return RequestVo[]
	 * @throws RemoteException
	 */
	public RequestVo[] getUnvalidatedRequests(String requestClassName) throws RemoteException;

	/**
	 * Method updateRequest.
	 * @param request
	 * @throws RemoteException
	 */
    public void updateRequest(RequestVo request) throws RemoteException;
    
	/**
	 * Method updateRequests.
	 * @param requests 
	 * @throws RemoteException
	 */
    public void updateRequests(RequestVo[] requests) throws RemoteException;
    
	/**
	 * Method getTradeState.
	 * @param tradeId
	 * @return TradeHistoryEntryVo
	 * @throws RemoteException
	 */
    public TradeHistoryEntryVo getTradeState(Object tradeId) throws RemoteException;

	/**
	 * Method confirmDualControlJobs.
	 * @param ids
	 * @throws RemoteException
	 */
    /**
     * Confirms dual control jobs entries by reviewer. 
     * Throws an exception if current user is the same user who requested the change.
     * 
     */
    public void confirmDualControlJobs(Long[] ids) throws RemoteException;

	/**
	 * Method deleteDualControlJobs.
	 * @param ids
	 * @throws RemoteException
	 */
    /**
     * Confirms dual control jobs entries by reviewer. 
     * Throws an exception if current user is the same user who requested the change.
     * 
     */
    public void deleteDualControlJobs(Long[] ids) throws RemoteException;

	/**
	 * Method getAutoCheckTrades.
	 * @return AutoCheckVo[]
	 * @throws RemoteException
	 */
    /**
     * returns all trades that need to be checked automatically with some additional parameters
     */
    public AutoCheckVo[] getAutoCheckTrades() throws RemoteException;

	/**
	 * Method getStateRules.
	 * @return StateRulesVo[]
	 * @throws RemoteException
	 */
    public StateRulesVo[] getStateRules() throws RemoteException;

	/**
	 * Method saveInitialTradeReclamationState.
	 * @param employeeId
	 * @param attachmentContent
	 * @param attachmentName
	 * @throws RemoteException
	 */
    /**
     * save an initial state of the trades that is linked to the current manual state and saves the single attachment 
     */
    public void saveInitialTradeReclamationState(Long employeeId, byte[] attachmentContent, String attachmentName) throws RemoteException;

	/**
	 * Method getStornoGroup.
	 * @param referenceTradeId
	 * @return StornoGroupVo[]
	 * @throws RemoteException
	 */
    public StornoGroupVo[] getStornoGroup(Long referenceTradeId) throws RemoteException;

	/**
	 * Method getRepoTradeOverview.
	 * @param tradeId
	 * @return RepoTradeOverviewVo
	 * @throws RemoteException
	 */
    public RepoTradeOverviewVo getRepoTradeOverview(Object tradeId) throws RemoteException;

  
	/**
	 * Method keepAlive.
	 * @throws RemoteException
	 */
    public void keepAlive() throws RemoteException;

	/**
	 * Method getMgbConfiguration
	 * @return String
	 * @throws RemoteException
	 */
	public String getMgbConfigurationValue(String key) throws RemoteException;


	/**
	 * Method findAllMgbConfigurations.
	 * @return MgbConfigurationVo[]
	 * @throws RemoteException
	 */
	public MgbConfigurationVo[] findAllMgbConfigurations() throws RemoteException;
	
	/**
	 * Method saveMgbConfiguration.
	 * @param mgbConfiguration
	 * @throws RemoteException
	 */
	public void saveMgbConfiguration(MgbConfigurationVo mgbConfiguration) throws RemoteException;

	/**
	 * Method setJobStatus.
	 * @param jobId
	 * @param statusCode
	 * @throws RemoteException
	 */
    public void setJobStatus(Long jobId, String statusCode) throws RemoteException;

	/**
	 * Sets the directory of the market data proxy.
	 * @param path Path to the directory.
	 * @param statusCode
	 * @throws RemoteException
	 */
	public void setMarketDataProxyDirectory(String path) throws RemoteException;	
	
	/**
	 * Returns mgb statistic information dependend on params.
	 *
	 * @param params Contains the name of the report to return and selection criteria
	 * for data.
	 *
	 * @return statistic data.
	 *
	 * @throws RemoteException If something unexpected happens.
	 */
	public StatisticReportVo getStatisticReport(StatisticReportParamsVo params) throws RemoteException;
	
    /**
     * Returns a variant of the statistics report that uses a GROUP BY CUBE-like
     * format, i. e. contains totals as extra lines.
     *
     * @param params Contains the name of the report to return and selection criteria
     * for data.
     *
     * @return statistic data.
     *
     * @throws RemoteException If something unexpected happens.
     * 
     * @see #getStatisticReport(StatisticReportParamsVo)
     */
	public StatisticCubeReportVo getStatisticCubeReport(StatisticReportParamsVo params) throws RemoteException;
	
	/**
	 * Returns a list of all books.
	 * @return book list.
	 *
	 * @throws RemoteException If something unexpected happens.
	 */
	public BookSearchResultEntryVo[] findAllBooks() throws RemoteException;

	/**
	 * Returns information about mail which has been sent by the MGB application.
	 *
	 * @throws RemoteException If something unexpected happens.
	 */	
	public MailVo getMail(long mailId) throws RemoteException;
	
	/**
	 * Prepares the mail data for the sending. The method is intended
	 * to be used for a preview function to display the calculated
	 * mail subject and mail text. 
	 * 
	 * @throws RemoteException If something unexpected happens.
	 */	
	public MailVo prepareMail(SendMailParamsVo params) throws RemoteException;

	/**
	 * Sends and saves a mail.
	 * @param mailVo The mail to be send.
	 * @param newReclamationState The new reclamation state of the trade. If null the current reclamation state is not changed.
	 * @param reclAttachmContent Content of the attachment of the reclamation state. Might be null.
	 * @throws RemoteException If something unexpected happens.
	 */	
	public void sendMail(MailVo mailVo, byte[]reclAttachmContent) throws RemoteException;

	/**
	 * Method findMailsByTradeId.
	 * @param tradeId
	 * @return MailVo[]
	 * @throws RemoteException
	 */
	public MailSearchResultEntryVo[] findMailsByTradeId(Long tradeId) throws RemoteException;

	/**
	 * Returnes all mails send by a controller to the current user where a response is 
	 * still missing. Only mails for trades with open reclamation are returned.
	 * 
	 * @return mail search result entries.
	 * @throws RemoteException
	 */
	public InboxMailVo[] findTraderInboxMails() throws RemoteException;
	
	/**
	 * Sends an email containing the response text back to the controller.
	 */
	public void sendTraderResponseMail(Long parentMailId, String responseText) throws RemoteException;
	
	/**
	 * Returnes a list of all trades containted in the current data selection that require
	 * a reclamation.
	 */ 
	public TradeReclRequiredVo[] findTradesReclRequired() throws RemoteException;
	
	/**
	 * Prepare and send a list of mails. The function may be used for the automatic
	 * sending of mails without user interaction.
	 * 
	 * @param paramsArray Contains an entry for every mail to send.
	 * @throws RemoteException
	 */
	public void prepareAndSendMail(SendMailParamsVo[] paramsArray) throws RemoteException;

	public void runAndSaveAutomaticCheck(PriceVo[] prices) throws RemoteException;

	/**
	 * Finds the trades that are aggregated in a trade statistic row.
	 * 
	 * @param params Report parameter containing the context and the parameter of the report statistic row.
	 * @return list of trades contained in the statistic row.
	 * @throws RemoteException
	 */
	public TradeSearchResultEntryVo[] findTradesForStatisticRow(StatisticReportParamsVo params) throws RemoteException;

	/**
	 * Does the same as @see findTradesForStatisticRow, but returns a list of TradeOverviewVo instead
	 * of TradesearchResultEntryVo.
	 * 
	 * @param params Report parameter containing the context and the parameter of the report statistic row.
	 * @return list of trades contained in the statistic row.
	 * @throws RemoteException
	 */
	public TradeOverviewVo[] findTradeOverviewVosForStatisticRow(StatisticReportParamsVo params) throws RemoteException;
	
	/**
	 * Saves the state for all trades that requrire a reclamation for an employee 
	 * in the current data selection.
	 * 
	 * @param employeeId Name of the report of the statistic.
	 * @param statisticRowVo 
	 * @throws RemoteException
	 */
	public void saveReclStateAfterEmployeeReport(Long employeeId) throws RemoteException;
	
	/**
	 * Checks if a trade is owned by an user having a specific user id.
	 * @param employeeId Name of the report of the statistic.
	 * @param statisticRowVo 
	 * @throws RemoteException
	 */	
	public boolean isTradeOwned(Long tradeId, String ntId) throws RemoteException;

	/**
	 * 
	 * Checks if the trader is allowed to view the trade to add a comment for this 
	 * trade.
	 *  
	 * @return
	 */
	public boolean isTradeAccessGranted(Long tradeId, String ntId) throws RemoteException;
		
	public Long saveEmployeeAndRoles(EmployeeVo employee) throws RemoteException;

	public EmployeeVo[] findAllEmployees(EmployeeSearchParamsVo searchParam) throws RemoteException;

	/**
	 * Returns all childs for a mail.
	 * 
	 * @param mailId The id of the mail the successors should be returned. 
	 * @param recursiv If false, only the direct response mails are returned.
	 * @param mailTypes Which mail types should be returned.
	 * @return An array of the requested successor mails.
	 * @throws RemoteException
	 */
   public MailVo[] getChildMails(Long mailId, boolean recursiv, String[] mailTypes) throws RemoteException;

   public Boolean getStornoGroupFromTrade(Object tradeId) throws RemoteException;

   public JobVo[] getRecentJobs(String mandantCode) throws RemoteException;

   public MgbTaskVo getMgbTask(Long taskId) throws RemoteException;

   public Long saveMgbTask(MgbTaskVo mgbTask) throws RemoteException;

   public PriceDeviationVo[] findPriceDeviations(TradeSearchParamsVo searchParams) throws java.rmi.RemoteException;

   public PriceDeviationStatisticVo[] getPriceDeviationStatistic(TradeSearchParamsVo searchParams) throws java.rmi.RemoteException;
   
   public boolean fetchPrices(String requestClassName, byte[] encodedPassword) throws java.rmi.RemoteException;

   public boolean isBusy() throws RemoteException;
   
   public Object getClientConfig(String key) throws java.rmi.RemoteException;
	
   public void setClientConfig(String key, Object value) throws java.rmi.RemoteException;

   public void runSampleChecks() throws RemoteException;

   public Object getStateCodeMap(String mandantCode,  String stateType) throws RemoteException;

   public String[] findAllTimePeriods(String mandantCode) throws RemoteException;

   public Object getRequestPriceConversionFactorMap() throws RemoteException;

   public String[] findAllReports() throws RemoteException;

   public String[] findReports(String type) throws RemoteException;

   public String[] findAllReportClients() throws RemoteException;

   public byte[] downloadJobFile(Long jobId, String fileNameExtension) throws RemoteException;

   public MandantVo[] findAllMandants() throws RemoteException;

}
