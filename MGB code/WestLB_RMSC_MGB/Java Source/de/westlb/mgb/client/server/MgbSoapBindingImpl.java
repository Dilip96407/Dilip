package de.westlb.mgb.client.server;

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
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MgbSoapBindingImpl implements Mgb {
	
	/**
     * 
     */
    private static final long serialVersionUID = 5615349308706420119L;
    private Mgb delegate = MgbSoapBindingProxyImpl.getProxy();

	/**
	 * Constructor for MgbSoapBindingImpl.
	 */
	public MgbSoapBindingImpl() {
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#deleteAddon(Long)
	 */
	@Override
    public void deleteAddon(Long addonId) throws RemoteException {
		delegate.deleteAddon(addonId);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#deleteTrader(Long)
	 */
	@Override
    public void deleteTrader(Long traderId) throws RemoteException {
		delegate.deleteTrader(traderId);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#deleteEmployee(Long)
	 */
	@Override
    public void deleteEmployee(Long employeeId) throws RemoteException {
		delegate.deleteEmployee(employeeId);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#deleteExchange(Long)
	 */
	@Override
    public void deleteExchange(Long exchangeId) throws RemoteException {
		delegate.deleteExchange(exchangeId);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#deleteExchangeMapping(Long)
	 */
	@Override
    public void deleteExchangeMapping(Long exchangeMappingId) throws RemoteException {
		delegate.deleteExchangeMapping(exchangeMappingId);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#deletePriceCheckCategory(Long)
	 */
	@Override
    public void deletePriceCheckCategory(Long priceCheckCategoryId) throws RemoteException {
		delegate.deletePriceCheckCategory(priceCheckCategoryId);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#deleteStateCode(String)
	 */
	@Override
    public void deleteStateCode(String code) throws RemoteException {
		delegate.deleteStateCode(code);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#findAllAddons()
	 */
	@Override
    public AddonSearchResultEntryVo[] findAllAddons() throws RemoteException {
		return delegate.findAllAddons();
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#findEmployeesByReclamationRequired()
	 */
	@Override
    public EmployeeSearchResultEntryVo[] findEmployeesByReclamationRequired() throws RemoteException {
		return delegate.findEmployeesByReclamationRequired();
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#findInstruments(InstrumentSearchParamsVo)
	 */
	@Override
    public InstrumentSearchResultEntryVo[] findInstruments(InstrumentSearchParamsVo searchParams) throws RemoteException {
		return delegate.findInstruments(searchParams);
	}
	
	/**
	 * @see de.westlb.mgb.client.server.Mgb#findNewLocations()
	 */
	@Override
    public TradeSearchResultEntryVo[] findNewLocations() throws RemoteException {
		return delegate.findNewLocations();
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#findNewInstruments()
	 */
	@Override
    public InstrumentSearchResultEntryVo[] findNewInstruments() throws RemoteException {
		return delegate.findNewInstruments();
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#findTradesLateReclReqByEmployee(Long)
	 */
	@Override
    public TradeSearchResultEntryVo[] findTradesLateReclReqByEmployee(Long employeeId) throws RemoteException {
		return delegate.findTradesLateReclReqByEmployee(employeeId);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#findTradesMiscReclReqByEmployee(Long)
	 */
	@Override
    public TradeSearchResultEntryVo[] findTradesMiscReclReqByEmployee(Long employeeId) throws RemoteException {
		return delegate.findTradesMiscReclReqByEmployee(employeeId);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#findDualControlJobs(DualControlJobSearchParamsVo)
	 */
	@Override
    public DualControlJobSearchResultEntryVo[] findDualControlJobs(DualControlJobSearchParamsVo searchParams) throws RemoteException {
		return delegate.findDualControlJobs(searchParams);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getDualControlJobVo(Long)
	 */
	@Override
    public DualControlJobVo getDualControlJobVo(Long dualControlJobId) throws RemoteException {
		return delegate.getDualControlJobVo(dualControlJobId);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getExchange(Long)
	 */
	@Override
    public ExchangeVo getExchange(Long exchangeId) throws RemoteException {
		return delegate.getExchange(exchangeId);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getInstrument(Long)
	 */
	@Override
    public InstrumentVo getInstrument(Long instrumentId) throws RemoteException {
		return delegate.getInstrument(instrumentId);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getStateCode(String)
	 */
	@Override
    public StateCodeVo getStateCode(String code) throws RemoteException {
		return delegate.getStateCode(code);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#assignPriceCheckCategoryToInstrument(Long, Long)
	 */
	@Override
    public void assignPriceCheckCategoryToInstrument(Long priceCheckCategory, Long instrumentId) throws RemoteException {
		delegate.assignPriceCheckCategoryToInstrument(priceCheckCategory, instrumentId);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#findAllExchanges()
	 */
	@Override
    public ExchangeSearchResultEntryVo[] findAllExchanges() throws RemoteException {
		return delegate.findAllExchanges();
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#findAllPriceCheckCategories()
	 */
	@Override
    public PriceCheckCategoryVo[] findAllPriceCheckCategories() throws RemoteException {
		return delegate.findAllPriceCheckCategories();
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#findAllConditions()
	 */
	@Override
    public ConditionVo[] findAllConditions() throws RemoteException {
		return delegate.findAllConditions();
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getAddon(Long)
	 */
	@Override
    public AddonVo getAddon(Long id) throws RemoteException {
		return delegate.getAddon(id);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getDataSelection()
	 */
	@Override
    public DataSelectionVo getDataSelection() throws RemoteException {
		return delegate.getDataSelection();
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#setDataSelection(DataSelectionVo)
	 */
	@Override
    public void setDataSelection(DataSelectionVo in0) throws RemoteException {
		delegate.setDataSelection(in0);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getStateCodes(String)
	 */
	@Override
    public StateCodeVo[] getStateCodes(String in0) throws RemoteException {
		return delegate.getStateCodes(in0);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getCheckState()
	 */
	@Override
    public CheckStateVo getCheckState() throws RemoteException {
		return delegate.getCheckState();
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#findTrades(TradeSearchParamsVo)
	 */
	@Override
    public TradeSearchResultEntryVo[] findTrades(TradeSearchParamsVo in0) throws RemoteException {
		return delegate.findTrades(in0);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getTradeOverview(Object)
	 */
	@Override
    public TradeOverviewVo getTradeOverview(Object in0) throws RemoteException {
		return delegate.getTradeOverview(in0);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getTradeReclamation(Object)
	 */
	@Override
    public TradeReclamationVo getTradeReclamation(Object in0) throws RemoteException {
		return delegate.getTradeReclamation(in0);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getStateStatistic(String)
	 */
	@Override
    public StateStatisticEntryVo getStateStatistic(String in0) throws RemoteException {
		return delegate.getStateStatistic(in0);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getHistory(Object)
	 */
	@Override
    public TradeHistoryEntryVo[] getHistory(Object in0) throws RemoteException {
		return delegate.getHistory(in0);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getTradeParameter(Object)
	 */
	@Override
    public TradeParameterVo getTradeParameter(Object in0) throws RemoteException {
		return delegate.getTradeParameter(in0);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getCurrentReclamationState(Long)
	 */
	@Override
    public TradeHistoryEntryVo getCurrentReclamationState(Long in0) throws RemoteException {
		return delegate.getCurrentReclamationState(in0);
	}


	/**
	 * @see de.westlb.mgb.client.server.Mgb#saveStateCode(StateCodeVo)
	 */
	@Override
    public void saveStateCode(StateCodeVo stateCodeVo) throws RemoteException {
		delegate.saveStateCode(stateCodeVo);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#saveTradeState(TradeHistoryEntryVo, byte[])
	 */
	@Override
    public void saveTradeState(TradeHistoryEntryVo in0, byte[] in1) throws RemoteException {
		delegate.saveTradeState(in0, in1);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#startReadAttachment(Long)
	 */
	@Override
    public long startReadAttachment(Long in0) throws RemoteException {
		return delegate.startReadAttachment(in0);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#readAttachment(Long, long, long)
	 */
	@Override
    public byte[] readAttachment(Long in0, long in1, long in2) throws RemoteException {
		return delegate.readAttachment(in0, in1, in2);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#endReadAttachment(Long)
	 */
	@Override
    public void endReadAttachment(Long in0) throws RemoteException {
		delegate.endReadAttachment(in0);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#closeReclamation(Long, String)
	 */
	@Override
    public void closeReclamation(Long in0, String in1) throws RemoteException {
		delegate.closeReclamation(in0, in1);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#findNewTrader()
	 */
	@Override
    public TraderSearchResultEntryVo[] findNewTrader() throws RemoteException {
		return delegate.findNewTrader();
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#findEmployees(EmployeeSearchParamsVo)
	 */
	@Override
    public EmployeeSearchResultEntryVo[] findEmployees(EmployeeSearchParamsVo in0) throws RemoteException {
		return delegate.findEmployees(in0);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#assignTraderToEmployee(Long, Long)
	 */
	@Override
    public void assignTraderToEmployee(Long in0, Long in1) throws RemoteException {
		delegate.assignTraderToEmployee(in0, in1);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getEmployee(Long)
	 */
	@Override
    public EmployeeVo getEmployee(Long employeeId) throws RemoteException {
		return delegate.getEmployee(employeeId);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#saveAddon(AddonVo)
	 */
	@Override
    public void saveAddon(AddonVo addon) throws RemoteException {
		delegate.saveAddon(addon);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#saveEmployee(EmployeeVo)
	 */
	@Override
    public Long saveEmployee(EmployeeVo in0) throws RemoteException {
		return delegate.saveEmployee(in0);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#saveExchange(ExchangeVo)
	 */
	@Override
    public void saveExchange(ExchangeVo exchangeVo) throws RemoteException {
		delegate.saveExchange(exchangeVo);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#saveExchangeMapping(ExchangeMappingVo)
	 */
	@Override
    public void saveExchangeMapping(ExchangeMappingVo exchangeMappingVo) throws RemoteException {
		delegate.saveExchangeMapping(exchangeMappingVo);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getSessionInfo()
	 */
	@Override
    public SessionInfoVo getSessionInfo() throws RemoteException {
		return delegate.getSessionInfo();
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getPriceCheckCategory(Long)
	 */
	@Override
    public PriceCheckCategoryVo getPriceCheckCategory(Long priceCheckCategoryId) throws RemoteException {
		return delegate.getPriceCheckCategory(priceCheckCategoryId);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getPrice(RequestVo)
	 */
	@Override
    public PriceVo getPrice(RequestVo in0) throws RemoteException {
		return delegate.getPrice(in0);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#savePrice(RequestVo, PriceVo)
	 */
	@Override
    public void savePrice(RequestVo in0, PriceVo in1) throws RemoteException {
		delegate.savePrice(in0, in1);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#savePriceCheckCategory(PriceCheckCategoryVo)
	 */
	@Override
    public void savePriceCheckCategory(PriceCheckCategoryVo priceCheckCategory) throws RemoteException {
		delegate.savePriceCheckCategory(priceCheckCategory);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getUnsolvedRequests()
	 */
	@Override
    public RequestVo[] getUnsolvedRequests(String requestClassName) throws RemoteException {
		return delegate.getUnsolvedRequests(requestClassName);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#updateRequest(RequestVo)
	 */
	@Override
    public void updateRequest(RequestVo in0) throws RemoteException {
		delegate.updateRequest(in0);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getTradeState(Object)
	 */
	@Override
    public TradeHistoryEntryVo getTradeState(Object in0) throws RemoteException {
		return delegate.getTradeState(in0);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#confirmDualControlJobs(Long[])
	 */
	@Override
    public void confirmDualControlJobs(Long[] ids) throws RemoteException {
		delegate.confirmDualControlJobs(ids);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#deleteDualControlJobs(Long[])
	 */
	@Override
    public void deleteDualControlJobs(Long[] ids) throws RemoteException {
		delegate.deleteDualControlJobs(ids);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getAutoCheckTrades()
	 */
	@Override
    public AutoCheckVo[] getAutoCheckTrades() throws RemoteException {
		return delegate.getAutoCheckTrades();
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getStateRules()
	 */
	@Override
    public StateRulesVo[] getStateRules() throws RemoteException {
		return delegate.getStateRules();
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#saveInitialTradeReclamationState(Long, byte[], String)
	 */
	@Override
    public void saveInitialTradeReclamationState(Long employeeId, byte[] attachmentContent, String attachmentName) throws RemoteException {
		delegate.saveInitialTradeReclamationState(employeeId, attachmentContent, attachmentName);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getStornoGroup(Long)
	 */
	@Override
    public StornoGroupVo[] getStornoGroup(Long referenceTradeId) throws RemoteException {
		return delegate.getStornoGroup(referenceTradeId);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getRepoTradeOverview(Object)
	 */
	@Override
    public RepoTradeOverviewVo getRepoTradeOverview(Object tradeId) throws RemoteException {
		return delegate.getRepoTradeOverview(tradeId);
	}


	/**
	 * @see de.westlb.mgb.client.server.Mgb#findAllBloombergCurrencyCodes()
	 */
	@Override
    public BloombergCurrencyCodeVo[] findAllBloombergCurrencyCodes() throws RemoteException {
		return delegate.findAllBloombergCurrencyCodes();
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#findAllBloombergMaturityCodes()
	 */
	@Override
    public BloombergMaturityCodeVo[] findAllBloombergMaturityCodes() throws RemoteException {
		return delegate.findAllBloombergMaturityCodes();
	}

    /**
     * @param sourceSystemCode
     * @param tradeData
     * @throws RemoteException
     */
    @Override
    public void createNewJob(String sourceSystemCode, byte[] tradeData) throws RemoteException {
        delegate.createNewJob(sourceSystemCode, tradeData);
    }

    /**
     * @return
     * @throws RemoteException
     */
    @Override
    public JobVo[] findAllJobs(JobSearchParamsVo paramVo) throws RemoteException {
        return delegate.findAllJobs(paramVo);
    }

    /**
     * 
     */
    @Override
    public void keepAlive()  throws RemoteException {
        delegate.keepAlive();
    }


	/**
	 * @see de.westlb.mgb.client.server.Mgb#deleteJob(Long)
	 */
	@Override
    public void deleteJob(Long jobId) throws RemoteException {
		delegate.deleteJob(jobId);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#findAllMgbConfigurations()
	 */
	@Override
    public MgbConfigurationVo[] findAllMgbConfigurations() throws RemoteException {
		return delegate.findAllMgbConfigurations();
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#saveMgbConfiguration(MgbConfigurationVo)
	 */
	@Override
    public void saveMgbConfiguration(MgbConfigurationVo mgbConfiguration) throws RemoteException {
		delegate.saveMgbConfiguration(mgbConfiguration);
	}

    /**
     * @return
     * @throws RemoteException
     */
    @Override
    public UserStatisticVo[] getUserStatistic() throws RemoteException {
        return delegate.getUserStatistic();
    }

     /**
     * @param in0
     * @return
     * @throws RemoteException
     */
    @Override
    public TradeOverviewVo[] findTradesOverviewVo(TradeSearchParamsVo in0) throws RemoteException {
        return delegate.findTradesOverviewVo(in0);
    }

	/**
	 * @see de.westlb.mgb.client.server.Mgb#setJobStatus(Long, String)
	 */
	@Override
    public void setJobStatus(Long jobId, String statusCode) throws RemoteException {
		delegate.setJobStatus(jobId, statusCode);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getCheckState(Long[])
	 */
	@Override
    public CheckStateVo getCheckState(Long[] jobIds) throws RemoteException {
		return delegate.getCheckState(jobIds);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#saveTradeState(Long[], TradeHistoryEntryVo, byte[])
	 */
	@Override
    public void saveTradeState(Long[] tradeIds, TradeHistoryEntryVo tradeHistoryEntry, byte[] attachment) throws RemoteException {
		delegate.saveTradeState(tradeIds, tradeHistoryEntry, attachment);		
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#savePrices(RequestVo[], PriceVo[])
	 */
	@Override
    public void savePrices(RequestVo[] requests, PriceVo[] prices) throws RemoteException {
		delegate.savePrices(requests, prices);
	}

	/**
	 * @see de.westlb.mgb.client.server.Mgb#updateRequests(RequestVo[])
	 */
	@Override
    public void updateRequests(RequestVo[] requests) throws RemoteException {
		delegate.updateRequests(requests);
	}

    /**
     * @param key
     * @return
     * @throws RemoteException
     */
    @Override
    public String getMgbConfigurationValue(String key) throws RemoteException {
        return delegate.getMgbConfigurationValue(key);
    }

    /**
     * @param path
     * @throws RemoteException
     */
    @Override
    public void setMarketDataProxyDirectory(String path) throws RemoteException {
        delegate.setMarketDataProxyDirectory(path);
    }
    /**
     * @param params
     * @return
     * @throws RemoteException
     */
    @Override
    public StatisticReportVo getStatisticReport(StatisticReportParamsVo params) throws RemoteException {
        return delegate.getStatisticReport(params);
    }

    @Override
    public StatisticCubeReportVo getStatisticCubeReport(
            StatisticReportParamsVo params) throws RemoteException
    {
        return delegate.getStatisticCubeReport(params);
    }

    /**
     * @return
     * @throws RemoteException
     */
    @Override
    public BookSearchResultEntryVo[] findAllBooks() throws RemoteException {
        return delegate.findAllBooks();
    }

    /**
     * @return
     * @throws RemoteException
     */
    @Override
    public SourceSystemVo[] findAllSourceSystems() throws RemoteException {
        return delegate.findAllSourceSystems();
    }

	/**
	 * @see de.westlb.mgb.client.server.Mgb#getMail(long)
	 */
	@Override
    public MailVo getMail(long mailId) throws RemoteException {
		return delegate.getMail(mailId);
	}


	/**
	 * @param tradeId
	 * @return
	 * @throws RemoteException
	 */
	@Override
    public MailSearchResultEntryVo[] findMailsByTradeId(Long tradeId)
		throws RemoteException {
		return delegate.findMailsByTradeId(tradeId);
	}

	/**
	 * @param params
	 * @return
	 * @throws RemoteException
	 */
	@Override
    public MailVo prepareMail(SendMailParamsVo params) throws RemoteException {
		return delegate.prepareMail(params);
	}

	/**
	 * @param mailVo
	 * @throws RemoteException
	 */
	@Override
    public void sendMail(MailVo mailVo, byte[] reclAttachmContent) throws RemoteException {
		delegate.sendMail(mailVo, reclAttachmContent);
	}

	/**
	 * @return
	 * @throws RemoteException
	 */
	@Override
    public InboxMailVo[] findTraderInboxMails() throws RemoteException {
		return delegate.findTraderInboxMails();
	}

	/* (non-Javadoc)
	 * @see de.westlb.mgb.client.server.Mgb#sendTraderResponseMail(java.lang.Long, java.lang.String)
	 */
	@Override
    public void sendTraderResponseMail(Long parentMailId, String responseText)
			throws RemoteException {
		delegate.sendTraderResponseMail(parentMailId, responseText);
	}
	
	/* (non-Javadoc)
	 * @see de.westlb.mgb.client.server.Mgb#getCurrentDataSelection()
	 */
	@Override
    public DataSelectionVo getCurrentDataSelection() throws RemoteException {
		return delegate.getCurrentDataSelection();
	}
	
	/**
	 * @param ntId
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	@Override
    public boolean adminLogin(String ntId, String password)
		throws RemoteException {
		return delegate.adminLogin(ntId, password);
	}

	/**
	 * @param ntId
	 * @param mandant
	 * @throws RemoteException
	 */
	@Override
    public void login(String ntId, String mandant, String comment) throws RemoteException {
		delegate.login(ntId, mandant, comment);
	}

	
	/**
	 * @see de.westlb.mgb.client.server.Mgb#runAndSaveAutomaticCheck(de.westlb.mgb.client.server.vo.PriceVo[])
	 */
	@Override
    public void runAndSaveAutomaticCheck(PriceVo[] prices)
			throws RemoteException {
		delegate.runAndSaveAutomaticCheck(prices);
	}
	
	
    /**
     * @return
     * @throws RemoteException
     */
    @Override
    public TradeReclRequiredVo[] findTradesReclRequired() throws RemoteException {
        return delegate.findTradesReclRequired();
    }
    /**
     * @param paramsArray
     * @throws RemoteException
     */
    @Override
    public void prepareAndSendMail(SendMailParamsVo[] paramsArray) throws RemoteException {
        delegate.prepareAndSendMail(paramsArray);
    }

    
	/**
	 * @param employeeId
	 * @throws RemoteException
	 */
	@Override
    public void saveReclStateAfterEmployeeReport(Long employeeId)
		throws RemoteException {
		delegate.saveReclStateAfterEmployeeReport(employeeId);
	}

	/**
	  * @param params
	  * @return
	  * @throws RemoteException
	  */
	 @Override
    public TradeSearchResultEntryVo[] findTradesForStatisticRow(StatisticReportParamsVo params) throws RemoteException {
		 return delegate.findTradesForStatisticRow(params);
	 }
	 
	/**
	 * @param tradeId
	 * @param ntId
	 * @return
	 * @throws RemoteException
	 */
	@Override
    public boolean isTradeOwned(Long tradeId, String ntId)
		throws RemoteException {
		return delegate.isTradeOwned(tradeId, ntId);
	}

    /**
     * @param searchParam
     * @return
     * @throws RemoteException
     */
    @Override
    public EmployeeVo[] findAllEmployees(EmployeeSearchParamsVo searchParam) throws RemoteException {
        return delegate.findAllEmployees(searchParam);
    }
    /**
     * @param employee
     * @return
     * @throws RemoteException
     */
    @Override
    public Long saveEmployeeAndRoles(EmployeeVo employee) throws RemoteException {
        return delegate.saveEmployeeAndRoles(employee);
    }
	/**
	 * @param tradeId
	 * @param ntId
	 * @return
	 * @throws RemoteException
	 */
	@Override
    public boolean isTradeAccessGranted(Long tradeId, String ntId)
		throws RemoteException {
		return delegate.isTradeAccessGranted(tradeId, ntId);
	}

	/**
	 * @param mailId
	 * @param recursiv
	 * @param mailTypes
	 * @return
	 * @throws RemoteException
	 */
	@Override
    public MailVo[] getChildMails(
		Long mailId,
		boolean recursiv,
		String[] mailTypes)
		throws RemoteException {
		return delegate.getChildMails(mailId, recursiv, mailTypes);
	}

    /**
     * @return
     * @throws RemoteException
     */
    @Override
    public RequestVo[] getUnvalidatedRequests(String requestClassName) throws RemoteException {
        return delegate.getUnvalidatedRequests(requestClassName);
    }
    
    
    /**
     * @param params
     * @return
     * @throws RemoteException
     */
    @Override
    public TradeOverviewVo[] findTradeOverviewVosForStatisticRow(StatisticReportParamsVo params) throws RemoteException {
        return delegate.findTradeOverviewVosForStatisticRow(params);
    }


	/**
	 * @param tradeId
	 * @return
	 * @throws RemoteException
	 */
	@Override
    public Boolean getStornoGroupFromTrade(Object tradeId) throws RemoteException {
		return delegate.getStornoGroupFromTrade(tradeId);
	}
	
	/**
	 * @param taskId
	 * @return
	 * @throws RemoteException
	 */
	@Override
    public MgbTaskVo getMgbTask(Long taskId) throws RemoteException {
		return delegate.getMgbTask(taskId);
	}
	/**
	 * @param mand
	 * @return
	 * @throws RemoteException
	 */
	@Override
    public JobVo[] getRecentJobs(String mandantCode) throws RemoteException {
		return delegate.getRecentJobs(mandantCode);
	}
	/**
	 * @param mgbTask
	 * @return
	 * @throws RemoteException
	 */
	@Override
    public Long saveMgbTask(MgbTaskVo mgbTask) throws RemoteException {
		return delegate.saveMgbTask(mgbTask);
	}
	
	/**
	 * @param searchParams
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	@Override
    public PriceDeviationVo[] findPriceDeviations(
			TradeSearchParamsVo searchParams) throws RemoteException {
		return delegate.findPriceDeviations(searchParams);
	}

	/**
	 * @param searchParams
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	@Override
    public PriceDeviationStatisticVo[] getPriceDeviationStatistic(
			TradeSearchParamsVo searchParams) throws RemoteException {
		return delegate.getPriceDeviationStatistic(searchParams);
	}
	
	@Override
    public String getDomainUser() throws java.rmi.RemoteException {
		return delegate.getDomainUser();
	}
	
	
	@Override
    public boolean fetchPrices(String requestClassName, byte[] encodedPassword) throws java.rmi.RemoteException {
		return delegate.fetchPrices(requestClassName, encodedPassword);
	}
	
		
	@Override
    public boolean isBusy() throws RemoteException {
		return delegate.isBusy();
	}
	
	@Override
    public Object getClientConfig(String key) throws RemoteException {
		return delegate.getClientConfig(key);
	}
	@Override
    public void setClientConfig(String key, Object value) throws RemoteException {
		delegate.setClientConfig(key, value);
	}
	
	@Override
    public void close() throws RemoteException {
		delegate.close();
	}

	@Override
    public void runSampleChecks() throws RemoteException {
		delegate.runSampleChecks();
	}

    @Override
    public Object getStateCodeMap(String mandantCode, String stateType) throws RemoteException {
        return delegate.getStateCodeMap(mandantCode, stateType);
    }

    @Override
    public String[] findAllTimePeriods(String mandantCode) throws RemoteException{
        return delegate.findAllTimePeriods(mandantCode);
    }

    @Override
    public Object getRequestPriceConversionFactorMap() throws RemoteException {
        return delegate.getRequestPriceConversionFactorMap();
    }

    @Override
    public String[] findAllReports() throws RemoteException {
        return delegate.findAllReports();
    }

    @Override
    public String[] findReports(String type) throws RemoteException {
        return delegate.findReports(type);
    }

    @Override
    public String[] findAllReportClients() throws RemoteException {
        return delegate.findAllReportClients();
    }

    @Override
    public byte[] downloadJobFile(Long jobId, String fileNameExtension) throws RemoteException {
        return delegate.downloadJobFile(jobId, fileNameExtension);
    }

    @Override
    public MandantVo[] findAllMandants() throws RemoteException {
        return delegate.findAllMandants();
    }

}

