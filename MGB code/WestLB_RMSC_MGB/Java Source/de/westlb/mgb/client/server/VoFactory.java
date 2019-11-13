package de.westlb.mgb.client.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import de.westlb.mgb.client.server.vo.AssetVo;
import de.westlb.mgb.client.server.vo.BookSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.EmployeeSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.EmployeeVo;
import de.westlb.mgb.client.server.vo.InboxMailVo;
import de.westlb.mgb.client.server.vo.JobVo;
import de.westlb.mgb.client.server.vo.MailSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.MailVo;
import de.westlb.mgb.client.server.vo.MandantVo;
import de.westlb.mgb.client.server.vo.MgbConfigurationVo;
import de.westlb.mgb.client.server.vo.MgbTaskVo;
import de.westlb.mgb.client.server.vo.PriceCheckCategoryVo;
import de.westlb.mgb.client.server.vo.PriceDeviationVo;
import de.westlb.mgb.client.server.vo.PriceVo;
import de.westlb.mgb.client.server.vo.RepoTradeOverviewVo;
import de.westlb.mgb.client.server.vo.RequestVo;
import de.westlb.mgb.client.server.vo.SourceSystemVo;
import de.westlb.mgb.client.server.vo.StateCodeVo;
import de.westlb.mgb.client.server.vo.StateStatisticEntryVo;
import de.westlb.mgb.client.server.vo.StatisticContextVo;
import de.westlb.mgb.client.server.vo.StatisticCubeReportVo;
import de.westlb.mgb.client.server.vo.StatisticCubeRowVo;
import de.westlb.mgb.client.server.vo.StatisticReportVo;
import de.westlb.mgb.client.server.vo.StatisticRowVo;
import de.westlb.mgb.client.server.vo.TradeHistoryEntryVo;
import de.westlb.mgb.client.server.vo.TradeOverviewVo;
import de.westlb.mgb.client.server.vo.TradeSearchResultEntryVo;
import de.westlb.mgb.model.definition.MailTypeDef;
import de.westlb.mgb.model.definition.MarketDataRequestStateDef;
import de.westlb.mgb.model.definition.MarketDataSourceDef;
import de.westlb.mgb.model.definition.PriceDef;
import de.westlb.mgb.model.definition.RoleDef;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
import de.westlb.mgb.model.impl.AssetImpl;
import de.westlb.mgb.model.impl.AutomaticStateImpl;
import de.westlb.mgb.model.impl.BloombergRequestImpl;
import de.westlb.mgb.model.impl.BookImpl;
import de.westlb.mgb.model.impl.EmployeeImpl;
import de.westlb.mgb.model.impl.HistoricPriceImpl;
import de.westlb.mgb.model.impl.IntervalPriceImpl;
import de.westlb.mgb.model.impl.JobImpl;
import de.westlb.mgb.model.impl.MailImpl;
import de.westlb.mgb.model.impl.MailRecipientImpl;
import de.westlb.mgb.model.impl.MandantImpl;
import de.westlb.mgb.model.impl.ManualStateHistEntryImpl;
import de.westlb.mgb.model.impl.ManualStateImpl;
import de.westlb.mgb.model.impl.MgbConfigurationImpl;
import de.westlb.mgb.model.impl.MgbTaskImpl;
import de.westlb.mgb.model.impl.PriceCheckCategoryImpl;
import de.westlb.mgb.model.impl.PriceImpl;
import de.westlb.mgb.model.impl.PrimeEquityImpl;
import de.westlb.mgb.model.impl.ReclStateHistEntryImpl;
import de.westlb.mgb.model.impl.RepoImpl;
import de.westlb.mgb.model.impl.RequestImpl;
import de.westlb.mgb.model.impl.RoleImpl;
import de.westlb.mgb.model.impl.SourceSystemImpl;
import de.westlb.mgb.model.impl.StateImpl;
import de.westlb.mgb.model.impl.SummitBondImpl;
import de.westlb.mgb.model.impl.SummitDerivativeImpl;
import de.westlb.mgb.model.impl.SummitForeignExchangeImpl;
import de.westlb.mgb.model.impl.SummitMoneyMarketImpl;
import de.westlb.mgb.model.impl.SummitRepoImpl;
import de.westlb.mgb.model.impl.TradeHistEntryImpl;
import de.westlb.mgb.model.impl.TradeImpl;
import de.westlb.mgb.model.impl.statistic.StateStatisticEntryImpl;
import de.westlb.mgb.model.impl.statistic.StatisticContextImpl;
import de.westlb.mgb.model.impl.statistic.StatisticCubeReportImpl;
import de.westlb.mgb.model.impl.statistic.StatisticCubeRowImpl;
import de.westlb.mgb.model.impl.statistic.StatisticReportImpl;
import de.westlb.mgb.model.impl.statistic.StatisticRowImpl;
import de.westlb.mgb.model.impl.visitor.PriceVisitor;
import de.westlb.mgb.model.impl.visitor.PriceVisitorAdapter;
import de.westlb.mgb.util.PriceUtils;

/**
 * @author D055625
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class VoFactory {

	public static AssetVo createAssetVo(AssetImpl asset) {
		AssetVo vo = new AssetVo();
		vo.setAssetId(asset.getAssetId());
		vo.setBuySell(asset.getBuySell());
		vo.setCallPut(asset.getCallPut());
		vo.setCapFloor(asset.getCapFloor());
		vo.setDelta(asset.getDelta());
		vo.setDeltaCurrency(asset.getDeltaCurrency());
		vo.setExpiryDate(asset.getExpiryDate());
		vo.setFirstCouponDate(asset.getFirstCouponDate());
		vo.setFixingDate(asset.getFixingDate());
		vo.setFormula(asset.getFormula());
		vo.setFxRate(asset.getFxRate());
		vo.setInstrumentStyle(asset.getInstrumentStyle());
		vo.setMarketRateIR(asset.getMarketRateIR());
		vo.setMarketRateVola(asset.getMarketRateVola());
		vo.setMaturityDate(asset.getMaturityDate());
		vo.setModel(asset.getModel());
		vo.setNpv(asset.getNpv());
		vo.setPayCurrency(asset.getPayCurrency());
		vo.setPayDiscountCurve(asset.getPayDiscountCurve());
		vo.setPayIndexBasis(asset.getPayIndexBasis());
		vo.setPaymentDate(asset.getPaymentDate());
		vo.setPayNotional(asset.getPayNotional());
		vo.setPayRateSpread(asset.getPayRateSpread());
		vo.setPremiumAmount(asset.getPremiumAmount());
		vo.setPremiumCurrency(asset.getPremiumCurrency());
		vo.setProdData(asset.getProdData());
		vo.setProductName(asset.getProductName());
		vo.setQuantity(asset.getQuantity());
		vo.setReceiveCurrency(asset.getReceiveCurrency());
		vo.setReceiveDiscountCurve(asset.getReceiveDiscountCurve());
		vo.setReceiveIndexBasis(asset.getReceiveIndexBasis());
		vo.setReceiveNotional(asset.getReceiveNotional());
		vo.setReceiveRateSpread(asset.getReceiveRateSpread());
		vo.setStartDate(asset.getStartDate());
		vo.setStrike(asset.getStrike());
		vo.setStyle(asset.getStyle());
		vo.setTradeSubType(asset.getTradeSubType());
		vo.setUnderlying(asset.getUnderlying());
		vo.setVega(asset.getVega());
		vo.setVegaCurrency(asset.getVegaCurrency());
		
		return vo;
	}

	public static PriceDeviationVo createPriceDeviationVo(final TradeImpl trade) {
		final PriceDeviationVo result = new PriceDeviationVo();
		
        PriceVisitor priceVisitor = new PriceVisitorAdapter() {
            @Override
            public void visit(IntervalPriceImpl price) {
                result.setBloombergMinPrice(new Double(price.getPriceMin()));
                result.setBloombergMaxPrice(new Double(price.getPriceMax()));
                result.setBloombergDiff(new Double(PriceUtils.calculatePriceDifference(trade.getPrice(), price.getPriceMin(), price.getPriceMax(),true)));
            }
            @Override
            public void visit(HistoricPriceImpl price) {
                result.setBloombergMinPrice(new Double(price.getPrice()));
                result.setBloombergMaxPrice(new Double(price.getPrice()));
                result.setBloombergDiff(new Double(PriceUtils.calculatePriceDifference(trade.getPrice(), price.getPrice(), price.getPrice(),false)));
            }            
        };

		result.setIsNotExceeded(Boolean.FALSE);
		result.setSrcTradeId(trade.getTradeId());
		result.setTradeId(trade.getLongId());
		result.setTradePrice(new Double(trade.getPrice()));
		result.setBookId(trade.getBookId());
		result.setInstrument(trade.getSourceSystemInstrument());
        result.setTheorericalPrice(new Double(trade.getFrontOfficeMarketRate()));
        result.setTheorericalDiff(new Double(trade.getFrontOfficeMarketRate() - trade.getPrice()));
        result.setSummitCategory(trade.getTradeCategory());          

		if ( trade instanceof SummitBondImpl ) {
			result.setInstrument(((SummitBondImpl)trade).getInstrumentName());
		} else if ( trade instanceof SummitRepoImpl ) {
			SummitRepoImpl repoTrade = (SummitRepoImpl)trade;
			result.setTradePrice(new Double(repoTrade.getRepoRate()));
			result.setTheorericalDiff(new Double(repoTrade.getRateDiff()));
		}
		result.setActualDiff(result.getTheorericalDiff());
		
        if (trade.getBloombergRequests() != null && trade.getBloombergRequests().size() > 0) {
        	BloombergRequestImpl request = null; 
            Iterator<BloombergRequestImpl> it = trade.getBloombergRequests().iterator();
            while (it.hasNext()) {
            	request = it.next();
            }
    		result.setRequestId(request.getLongId());
    		result.setRequestString(request.getRequestString());
            if (MarketDataRequestStateDef.OK_PRICE_UNVALIDATED.equals(request.getRequestState())
                        || MarketDataRequestStateDef.OK.equals(request.getRequestState())) {
            	if (request.getPriceResult() != null) {
            		PriceImpl price = request.getPriceResult();
                	result.setRequestDate(price.getPriceDate());
                    // Call the visitor
                    price.accept(priceVisitor);
            		result.setActualDiff(result.getBloombergDiff());
                }
            }
        }
    	if (trade.getInstrument() != null ) {
    		result.setCategory(trade.getInstrument().getInstrumentName());
    		if ( trade.getInstrument().getPriceCheckCategory() != null) {
    			result.setPriceCheckCategory(trade.getInstrument().getPriceCheckCategory().getName());
    			result.setTolerance(new Double(trade.getInstrument().getPriceCheckCategory().getPriceTolerancePercent()));
    			if (result.getActualDiff() != null) {
    				result.setIsNotExceeded(Boolean.valueOf(Math.abs(result.getActualDiff().doubleValue()) < trade.getInstrument().getPriceCheckCategory().getPriceTolerancePercent()));
    			}
    		}
    	}

		return result;
	}

	public static MgbTaskVo createMgbTaskVo(MgbTaskImpl mgbTask) {
		MgbTaskVo result = new MgbTaskVo();
		result.setClient(mgbTask.getClient());
		result.setMessage(mgbTask.getMessage());
		result.setStartTime(mgbTask.getStartTime());
		result.setState(mgbTask.getState());
		result.setStopTime(mgbTask.getStopTime());
		result.setTaskName(mgbTask.getTaskName());
		result.setTaskId(mgbTask.getLongId());
		result.setThreadName(mgbTask.getThreadName());
		result.setUser(mgbTask.getUser());
		return result;
	}
	
    public static EmployeeVo createEmployeeVo(EmployeeImpl employee) {
        EmployeeVo result = new EmployeeVo();
        result.setEmail(employee.getEmail());
        result.setEmployeeId(employee.getLongId());
        result.setFirstName(employee.getFirstName());
        result.setLastName(employee.getLastName());
        result.setNtId(employee.getNtId());
        result.setPhone(employee.getPhone());
        result.setAdmin(employee.hasAdminRole());
        result.setSystemAdmin(employee.hasSystemAdminRole());
        result.setUserMaintainAdmin(employee.hasUserMaintainAdminRole());
        result.setController(employee.hasControllerRole());
        result.setTrader(employee.hasTraderRole());
        result.setSparkassenAdmin(employee.hasSparkassenAdminRole());
        result.setReporter(employee.hasReporterRole());
        result.setReadOnly(employee.hasReadOnlyRole());
        if (employee.getMandant() != null) {
            result.setMandantCode(employee.getMandant().getCode());
            result.setMandantName(employee.getMandant().getName());
        }
        result.setReport(employee.getReport());
        return result;
    }

    public static RequestVo createRequestVo(RequestImpl request) {
        RequestVo result = new RequestVo();
        result.setRequestId(request.getLongId());
        result.setRequestString(request.getRequestString());
        result.setRequestType(request.getRequestType());
        result.setRequestDate(request.getRequestDate());
        result.setRequestState(request.getRequestState());
        result.setPriceSource(request.getMarketDataSource());
        if (MarketDataSourceDef.BLOOMBERG.equals(request.getMarketDataSource())) {
            result.setRequestField(((BloombergRequestImpl) request).getRequestField());
            result.setRequestSources(((BloombergRequestImpl) request).getRequestSourceArray());
        }
        return result;
    }

    public static PriceVo createPriceVo(PriceImpl price, Long requestId) {
        final PriceVo result = new PriceVo();
        result.setRequestId(requestId);
        result.setPriceDate(price.getPriceDate());

        PriceVisitor priceVisitor = new PriceVisitorAdapter() {
            @Override
            public void visit(IntervalPriceImpl price) {
                result.setPriceType(PriceDef.INTERVAL);
                result.setMinPrice(price.getPriceMin());
                result.setMaxPrice(price.getPriceMax());
                if (result.getMinPrice() == 0) {
                    result.setMidPrice(result.getMaxPrice());
                } else if (result.getMaxPrice() == 0) {
                    result.setMidPrice(result.getMinPrice());
                } else {
                    result.setMidPrice((result.getMinPrice()+result.getMaxPrice())/2.0);                
                }
            }
            @Override
            public void visit(HistoricPriceImpl price) {
                result.setPriceType(PriceDef.SINGLE);
                result.setMinPrice(price.getPrice());
                result.setMidPrice(price.getPrice());
                result.setMaxPrice(price.getPrice());
            }            
        };
        // Call the visitor
        price.accept(priceVisitor);
        return result;
    }

    public static MgbConfigurationVo createMgbConfigurationVo(MgbConfigurationImpl mgbConfiguration) {
        MgbConfigurationVo result = new MgbConfigurationVo();
        result.setKey(mgbConfiguration.getKey());
        result.setValue(mgbConfiguration.getValue());
        result.setHidden(mgbConfiguration.isHidden());
        if (mgbConfiguration.getMandant() != null) {
            result.setMandantCode(mgbConfiguration.getMandant().getCode());
        }

        return result;
    }

    public static TradeSearchResultEntryVo createTradeSearchResultEntryVo(TradeImpl trade) {
        TradeSearchResultEntryVo result = new TradeSearchResultEntryVo();
        result.setBookId(trade.getBookId());
        result.setCurrency(trade.getCurrency());
        result.setInstrument(trade.getSourceSystemInstrument());
        result.setPrice(trade.getPrice());
        result.setSettlementDate(trade.getSettlementDate());
        result.setMaturityDate(trade.getSettlementDate());
        result.setSystemDate(trade.getSystemDate());
        result.setId(trade.getLongId());
        if (trade.getInstrument() != null) {
            result.setIsin(trade.getInstrument().getInstrument());
        }
        result.setTradeDate(trade.getTradeDate());
        result.setTradeId(trade.getTradeId());
        result.setTraderId(trade.getTraderId());
        result.setTraderName(trade.getTraderName());
        if (trade.getBook() != null) {
        	result.setTraderLocation(trade.getBook().getLocationTrader());
        }
        result.setVolume(trade.getVolume());
        result.setTradeType(trade.getTradeCategory());
        
        if (trade instanceof SummitBondImpl) {
            result.setInstrument(((SummitBondImpl) trade).getInstrumentName());
            result.setMaturityDate(((SummitBondImpl) trade).getExpireDay());
            if (((SummitBondImpl) trade).getBloombergInstrument() != null) {
                result.setIsin(((SummitBondImpl) trade).getBloombergInstrument().getInstrument());
            } else {
                result.setIsin("");
            }
            result.setComment(((SummitBondImpl) trade).getAlias());
        }
        if (trade instanceof RepoImpl) {
            result.setPrice(((RepoImpl) trade).getRepoRate());
       }
       if (trade instanceof SummitForeignExchangeImpl) {
            result.setMaturityDate(((SummitForeignExchangeImpl) trade).getFarDate());
       }
       return result;
    }

    public static StateCodeVo createStateCodeVo(StateImpl state) {
        StateCodeVo result = new StateCodeVo();
        result.setType(state.getStateCodeType());
        if (StateCodeTypeDef.AUTO.equals(state.getStateCodeType())) {
            result.setManualCheckRequired(Boolean.valueOf(((AutomaticStateImpl) state).isManualCheckRequired()));
            result.setMccChecked(Boolean.valueOf(((AutomaticStateImpl) state).getIsMccChecked()));
            result.setMarketDataRequestType(((AutomaticStateImpl) state).getMarketDataRequestType());
            if (((AutomaticStateImpl) state).getSampleState() != null) {
            	result.setManualSampleCode(((AutomaticStateImpl) state).getSampleState().getStateCode());
            }
            result.setManualSamplePercentage(Integer.valueOf(((AutomaticStateImpl) state).getSamplePercentage()));
        } else if (StateCodeTypeDef.MANUAL.equals(state.getStateCodeType())) {
            result.setReclamationRequired(Boolean.valueOf(((ManualStateImpl) state).isReclamationRequired()));
            if (((ManualStateImpl) state).getReclamationState() != null) {
                result.setReclamationCode(((ManualStateImpl) state).getReclamationState().getStateCode());
            }
        }
        result.setFinalState(Boolean.valueOf(state.getFinalState()));
        result.setLongDescription(state.getLongDescription());
        result.setShortDescription(state.getShortDescription());
        if (state.getMandant() != null) {
            result.setMandantCode(state.getMandant().getCode());
        }
        result.setStateCode(state.getStateCode());
        result.setComment(state.getComment());
        return result;
    }

    public static JobVo createJobVo(JobImpl job) {
        JobVo result = new JobVo();
        result.setJobId(job.getLongId());
        if (job.getSourceSystem() != null) {
            result.setSourceSystemCode(job.getSourceSystem().getCode());
            result.setSourceSystemName(job.getSourceSystem().getName());
        }
        if(job.getMandant()!=null)
        {
            result.setMandantCode(job.getMandant().getCode());
            result.setMandantName(job.getMandant().getName());
        }
        result.setStartBusinessTime(job.getStartBusinessTime());
        result.setStartConvertTime(job.getStartConvertTime());
        result.setStartLoadTime(job.getStartLoadTime());
        result.setStatus(job.getStatus());
        result.setStopBusinessTime(job.getStopBusinessTime());
        result.setStopConvertTime(job.getStopConvertTime());
        result.setStopLoadTime(job.getStopLoadTime());
        result.setSystemTime(job.getSystemTime());
        result.setCob(job.getCob());
        result.setNumberOfConvertErrors(job.getNumberOfConvertErrors());
        result.setNumberOfLoadErrors(job.getNumberOfLoadErrors());
        result.setNumberOfTotalRecords(job.getNumberOfTotalRecords());
        result.setArchived(job.isArchived());
        return result;
    }

    public static TradeHistoryEntryVo createTradeHistoryEntryVo(TradeHistEntryImpl tradeHistEntry) {
        TradeHistoryEntryVo result = new TradeHistoryEntryVo();
        result.setDate(tradeHistEntry.getStateTime());
        result.setStateCode(tradeHistEntry.getState().getStateCode());
        result.setType(tradeHistEntry.getStateType());
        result.setInternalTradeId(tradeHistEntry.getLongId());
        result.setComment(tradeHistEntry.getComment());
        if (tradeHistEntry.getCreatedByEmployee() != null) {
            result.setCreatedByName(tradeHistEntry.getCreatedByEmployee().getFullName());
        } else {
            result.setCreatedByName(tradeHistEntry.getCreatedBy());
        }

        if (StateCodeTypeDef.MANUAL.equals(tradeHistEntry.getStateType())) {
            ManualStateHistEntryImpl manualState = (ManualStateHistEntryImpl) tradeHistEntry;
            if (manualState.getReportImage() != null) {
                result.setAttachmentId(manualState.getReportImage().getLongId());
                result.setAttachmentName(manualState.getReportImage().getName());
            }
        } else if (StateCodeTypeDef.RECLAMATION.equals(tradeHistEntry.getStateType())) {
            ReclStateHistEntryImpl reclState = (ReclStateHistEntryImpl) tradeHistEntry;
            if (reclState.getReportImage() != null) {
                result.setAttachmentId(reclState.getReportImage().getLongId());
                result.setAttachmentName(reclState.getReportImage().getName());
            }
        }

        return result;
    }

    public static StatisticContextVo[] createStatisticContextVos(StatisticContextImpl[] context) {
        if (context == null) { return null; }

        StatisticContextVo[] contextVos = new StatisticContextVo[context.length];
        for (int i = 0; i < context.length; i++) {
            StatisticContextVo contextEntry = new StatisticContextVo();
            contextEntry.setReportName(context[i].getReportName());
            contextEntry.setKeyValues(context[i].getKeyValues());
            contextEntry.setAddKeyValues(context[i].getAddKeyValues());
            contextVos[i] = contextEntry;
        }

        return contextVos;
    }

    public static StateStatisticEntryVo createStateStatisticEntryVo(StateStatisticEntryImpl entry) {
        StateStatisticEntryVo vo = new StateStatisticEntryVo();
        vo.setState(entry.getState().getStateCode());
        vo.setCount(entry.getCount());
        vo.setManualCheckRequired(entry.isManualCheckRequired());
        vo.setReclamationRequired(entry.isReclamationRequired());
        return vo;
    }

    public static StateStatisticEntryVo[] createStateStatisticEntryVos(Map<String, StateStatisticEntryImpl> rowEntries) {
        StateStatisticEntryVo[] vos = new StateStatisticEntryVo[rowEntries.values().size()];
        Iterator<StateStatisticEntryImpl> iterator = rowEntries.values().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            vos[i++] = createStateStatisticEntryVo(iterator.next());
        }

        return vos;
    }

    public static StateStatisticEntryVo[] createStateStatisticEntryVos(Map<String, StateStatisticEntryImpl> allEntries, Map<String, StateStatisticEntryImpl> rowEntries) {
        StateStatisticEntryVo[] vos = new StateStatisticEntryVo[allEntries.values().size()];
        Iterator<StateStatisticEntryImpl> iterator = allEntries.values().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            StateStatisticEntryImpl stateStatisticEntry = iterator.next();
            String stateCode = stateStatisticEntry.getState().getStateCode();
            if (rowEntries.get(stateCode) != null) {
                vos[i++] = createStateStatisticEntryVo(rowEntries.get(stateCode));
            } else {
                vos[i++] = createStateStatisticEntryVo(new StateStatisticEntryImpl(stateStatisticEntry.getState(), 0));
            }
        }

        return vos;
    }

    public static StatisticRowVo[] createStatisticRowVos(StatisticReportImpl report) {
        StatisticRowVo[] vos = new StatisticRowVo[report.getRows().size()];
        Iterator<StatisticRowImpl> iterator = report.getRows().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            vos[i++] = createStatisticRowVo(report, iterator.next());
        }

        return vos;
    }

    public static StatisticCubeRowVo[] createStatisticCubeRowVos(StatisticCubeReportImpl report) {
        StatisticCubeRowVo[] vos = new StatisticCubeRowVo[report.getRows().size()];
        Iterator<StatisticCubeRowImpl> iterator = report.getRows().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            vos[i++] = createStatisticCubeRowVo(report, iterator.next());
        }

        return vos;
    }

    public static StatisticRowVo createStatisticRowVo(StatisticReportImpl report, StatisticRowImpl row) {
        StatisticRowVo vo = new StatisticRowVo();
        vo.setAutomaticStates(createStateStatisticEntryVos(report.getAutomaticStates(), row.getAutomaticStates()));
        vo.setAutomaticMccCheckStates(createStateStatisticEntryVos(report.getAutomaticMccCheckStates() , row.getAutomaticMccCheckStates()));
        vo.setManualStates(createStateStatisticEntryVos(report.getManualStates(), row.getManualStates()));
        vo.setReclamationStates(createStateStatisticEntryVos(report.getReclamationStates(), row.getReclamationStates()));
        vo.setKeyValues(row.getKeyValues());
        vo.setAddKeyValues(row.getAddValues());
        vo.setNoTradesAutomaticStateNotSet(row.getNoTradesAutomaticStateNotSet());
        vo.setNoTradesLateEntry(row.getNoTradesLateEntry());
        vo.setNoTradesManualCheckRequired(row.getNoTradesManualCheckRequired());
        vo.setNoTradesManualStateRequiredButNotHandledYet(row.getNoTradesManualStateRequiredButNotHandledYet());
        vo.setNoTradesManualStateSet(row.getNoTradesManualStateSet());
        vo.setNoTradesOkAfterAutoCheck(row.getNoTradesOkAfterAutoCheck());
        vo.setNoTradesReclClosed(row.getNoTradesReclClosed());
        vo.setNoTradesReclOpen(row.getNoTradesReclOpen());
        vo.setNoTradesReclWaiting(row.getNoTradesReclWaiting());
        vo.setNoTradesTotal(row.calcNoTradesTotal());
        vo.setNoTradesAutoMccChecked(row.getNoTradesOkAfterAutoMccCheck());
        vo.setNoTradesReclRequired(row.getNoTradesReclRequired());

        return vo;
    }

    public static StatisticCubeRowVo createStatisticCubeRowVo(StatisticCubeReportImpl report, StatisticCubeRowImpl row) {
        StatisticCubeRowVo vo = new StatisticCubeRowVo();
        vo.setKeyValues(row.getKeyValues());
        vo.setAddKeyValues(row.getAddValues());
        vo.setNoTradesTotal(row.getNoTradesTotal());
        vo.setState(row.getState());
        vo.setManualCheckRequired(row.getManualCheckRequired());
        vo.setManualCheckRequiredStr(row.getManualCheckRequiredStr());

        return vo;
    }

    public static StatisticReportVo createStatisticReportVo(StatisticReportImpl report) {
        StatisticReportVo vo = new StatisticReportVo();
        vo.setName(report.getName());
        vo.setSuccessorNames(report.getSuccessorNames());
        vo.setKeyColumnNames(report.getKeyColumnNames());
        vo.setAddKeyColumnNames(report.getAddKeyColumnNames());
        vo.setContext(createStatisticContextVos(report.getContext()));
        vo.setAutomaticStates(createStateStatisticEntryVos(report.getAutomaticStates()));
        vo.setAutomaticMccCheckStates(createStateStatisticEntryVos(report.getAutomaticMccCheckStates()));
        vo.setManualStates(createStateStatisticEntryVos(report.getManualStates()));
        vo.setReclamationStates(createStateStatisticEntryVos(report.getReclamationStates()));
        vo.setFromDate(report.getFromDate());
        vo.setToDate(report.getToDate());

        vo.setRows(createStatisticRowVos(report));

        return vo;
    }

    public static StatisticCubeReportVo createStatisticCubeReportVo(StatisticCubeReportImpl report) {
        StatisticCubeReportVo vo = new StatisticCubeReportVo();
        vo.setName(report.getName());
        vo.setSuccessorNames(report.getSuccessorNames());
        vo.setKeyColumnNames(report.getKeyColumnNames());
        vo.setAddKeyColumnNames(report.getAddKeyColumnNames());
        vo.setContext(createStatisticContextVos(report.getContext()));
        vo.setFromDate(report.getFromDate());
        vo.setToDate(report.getToDate());

        vo.setRows(createStatisticCubeRowVos(report));

        return vo;
    }

    /**
     * @param book
     * @return
     */
    public static BookSearchResultEntryVo createBookSearchResultEntryVo(BookImpl book) {
        BookSearchResultEntryVo vo = new BookSearchResultEntryVo();
        vo.setAccount(book.getAccount());
        vo.setAccountingBranch(book.getAccountingBranch());
        vo.setAccountingSystem(book.getAccountingSystem());
        vo.setBookId(book.getBookId());
        vo.setBusinessUnit(book.getBusinessUnit());
        vo.setCostCenter(book.getCostCenter());
        vo.setCostCenterName(book.getCostCenterName());
        vo.setDescription(book.getDescription());
        vo.setDesk(book.getDesk());
        vo.setFoSystem(book.getFoSystem());
        vo.setLimitPosition(book.getLimitPosition());
        vo.setLocationConsolidation(book.getLocationConsolidation());
        vo.setLocationGl(book.getLocationGl());
        vo.setLocationPl(book.getLocationPl());
        vo.setLocationTrader(book.getLocationTrader());
        vo.setMpireLuName(book.getMpireLuName());
        vo.setPcrPlDesk(book.getPcrPlDesk());
        vo.setPcrPlGroup(book.getPcrPlGroup());
        vo.setTrader(book.getTrader());
        vo.setTradingBanking(book.getTradingBanking());
        vo.setTradingHead(book.getTradingHead());
        vo.setTradingType(book.getTradingType());
        vo.setType(book.getType());
        vo.setWersUnit(book.getWersUnit());
        return vo;
    }

    public static EmployeeSearchResultEntryVo createEmployeeSearchResultEntryVo(EmployeeImpl employee) {
    	EmployeeSearchResultEntryVo vo = new EmployeeSearchResultEntryVo();
    	vo.setEmployeeId(employee.getLongId());
    	vo.setFirstName(employee.getFirstName());
    	vo.setLastName(employee.getLastName());
    	vo.setEmail(employee.getEmail());
    	vo.setPhone(employee.getPhone());
        vo.setController(employee.hasUserRole(new RoleImpl(RoleDef.CONTROLLER)));
        vo.setTrader(employee.hasUserRole(new RoleImpl(RoleDef.TRADER)));
        vo.setReport(employee.getReport());
        return vo;
    }

    public static SourceSystemVo createSourceSystemVo(SourceSystemImpl sourceSystem) {
        SourceSystemVo vo = new SourceSystemVo();
        vo.setSourceSystemCode(sourceSystem.getCode());
        vo.setSourceSystemName(sourceSystem.getName());
        vo.setMandantCode(sourceSystem.getMandant().getCode());
        vo.setProductClass(sourceSystem.getMandant().getProductClass());
        vo.setClient(sourceSystem.getMandant().getClient());
        return vo;
    }

    public static MandantVo createMandantVo(MandantImpl mandant) {
    	MandantVo vo = new MandantVo();
        vo.setMandantCode(mandant.getCode());
        vo.setMandantName(mandant.getName());
        vo.setProductClass(mandant.getProductClass());
        vo.setClient(mandant.getClient());
        vo.setEnabled(mandant.getEnabled());
        vo.setAutoCheck(mandant.getAutoCheck());
        return vo;
    }

    /**
     * @param mail
     * @return
     */
    private static MailSearchResultEntryVo fillMailSearchResultEntryVo(MailImpl mail, MailSearchResultEntryVo vo) {
        vo.setId(mail.getLongId());
        vo.setCreationDate(mail.getCreationDate());
        if (mail.getParent() != null) {
            vo.setParentId(mail.getParent().getLongId());
        }
        if (mail.getReceiver() != null) {
            vo.setRecipientEmployeeId(mail.getReceiver().getLongId());
            vo.setRecipientName(mail.getReceiver().getFullName());
            vo.setRecipientAdress(mail.getReceiver().getEmail());
        }
        if (mail.getSender() != null) {
            vo.setSenderEmployeeId(mail.getSender().getLongId());
            vo.setSenderName(mail.getSender().getFullName());
            vo.setSenderAdress(mail.getSender().getEmail());
        }
        TradeImpl trade = mail.getTrade();
        vo.setSubject(mail.getSubject());
        vo.setTradeId(trade.getLongId());
        vo.setType(mail.getType());
        vo.setMandantCode(trade.getMandant().getCode());

        return vo;
    }

    /**
     * @param mail
     * @return
     */
    public static MailSearchResultEntryVo createMailSearchResultEntryVo(MailImpl mail) {
        MailSearchResultEntryVo vo = new MailSearchResultEntryVo();
        return fillMailSearchResultEntryVo(mail, vo);
    }

    /**
     * @param mail
     * @return
     */
    public static MailVo createMailVo(MailImpl mail) {
        MailVo vo = new MailVo();
        fillMailSearchResultEntryVo(mail, vo);
        vo.setText(mail.getText());
		
		ArrayList<EmployeeVo> employeeVoList = new ArrayList<EmployeeVo>();
		Iterator<MailRecipientImpl> iterator = mail.getRecipients().iterator();
		while (iterator.hasNext()) {
			MailRecipientImpl recipient = iterator.next();
			if (recipient.isCopyRecipient()) {
				employeeVoList.add(createEmployeeVo(recipient.getEmployee()));
			}
		}
		vo.setCopyRecipients(employeeVoList.toArray(new EmployeeVo[employeeVoList.size()]));
		return vo;
    }

    /**
     * @param mail
     * @return
     */
	public static InboxMailVo createInboxMailVo(MailImpl mail) {
		InboxMailVo vo = new InboxMailVo();
		fillMailSearchResultEntryVo(mail,vo);
		boolean isTraderResponseExisting = false;
		Iterator<MailImpl> iterator = mail.getChilds().iterator();
		while (iterator.hasNext()) {
			MailImpl responseMail = iterator.next();
			if (MailTypeDef.TRADER_RESPONSE.equals(responseMail.getType())) {
				isTraderResponseExisting = true;
				break;
			}
		}
		vo.setTraderResponseExisting(Boolean.valueOf(isTraderResponseExisting));
		
		return vo;
	}

    public static TradeOverviewVo createTradeOverviewVo(TradeImpl trade, boolean isListMode) {
        if (trade instanceof PrimeEquityImpl) {
            return createTradeOverviewVoFromPrimeEquity((PrimeEquityImpl)trade, isListMode);
        } else if (trade instanceof SummitForeignExchangeImpl) {
            return createTradeOverviewVoFromSummitForeignExchange((SummitForeignExchangeImpl)trade, isListMode);
        } else if (trade instanceof SummitMoneyMarketImpl) {
            return createTradeOverviewVoFromSummitMoneyMarket((SummitMoneyMarketImpl)trade, isListMode);
        } else if (trade instanceof SummitBondImpl) {
            return createTradeOverviewVoFromSummitBond((SummitBondImpl)trade, isListMode);
        } else if (trade instanceof SummitRepoImpl) {
            return createTradeOverviewVoFromSummitRepo((SummitRepoImpl)trade, isListMode);
        } else if (trade instanceof SummitDerivativeImpl) {
            return createTradeOverviewVoFromSummitDerivative((SummitDerivativeImpl)trade, isListMode);
        } else {
            return createTradeOverviewVoFromTrade(trade, isListMode);
       } 
    }

    private static TradeOverviewVo createTradeOverviewVoFromTrade(TradeImpl trade, boolean isListMode) {
        TradeOverviewVo result = new TradeOverviewVo();
        if (trade != null) {
            result.setTradePrice(trade.getPrice());
            result.setId(trade.getLongId());
            result.setAmendDate(trade.getAmendedDate());

            if (trade.getCurrentAutoState() != null) {
                result.setAutomaticStateCode(trade.getCurrentAutoState().getStateCode());
                result.setManualCheckRequired(Boolean.valueOf(trade.getCurrentAutoState().isManualCheckRequired()));
                if (!isListMode) {
                    EmployeeImpl employee = trade.getCurrentAutoStateHistEntry().getCreatedByEmployee();
                    if (employee != null) {
                        result.setAutomaticStateByEmployeeId(employee.getLongId());
                        result.setAutomaticStateByName(employee.getFullName());
                    } else {
                        result.setAutomaticStateByName(trade.getCurrentAutoStateHistEntry().getModifiedBy());
                    }
                }
            }

            result.setBookId(trade.getBookId());
            result.setCurrency(trade.getCurrency());
            result.setCounterparty(trade.getCounterpartyId());
            if (trade.getInstrument() != null) {
                if (trade.getInstrument().getInstrumentName() != null) {
                    result.setInstrument(trade.getInstrument().getInstrumentName());
                } else {
                    result.setInstrument(trade.getInstrument().getInstrument());
                }
            } else {
                if (trade.getSourceSystemInstrument() != null) {
                    result.setInstrument(trade.getSourceSystemInstrument());
                }
            }
            result.setIsin(trade.getSourceSystemInstrument());
            result.setLateEntry(Boolean.valueOf(trade.isLateDeal()));
            if (trade.getCurrentManualState() != null) {
                result.setManualStateCode(trade.getCurrentManualState().getStateCode());
                result.setManualStateComment(trade.getCurrentManualStateHistEntry().getComment());
                result.setReclamationCheckRequired(Boolean.valueOf(trade.getCurrentManualState().isReclamationRequired()));
                if (!isListMode) {
                    EmployeeImpl employee = trade.getCurrentManualStateHistEntry().getCreatedByEmployee();
                    if (employee != null) {
                        result.setManualStateByEmployeeId(employee.getLongId());
                        result.setManualStateByName(employee.getFullName());
                    } else {
                        result.setManualStateByName(trade.getCurrentManualStateHistEntry().getModifiedBy());
                    }
                }
            }
            if (trade.getCurrentManualStateHistEntry() != null
                    && trade.getCurrentManualStateHistEntry().getReportImage() != null) {
                result.setManualStateAttachmentId(trade.getCurrentManualStateHistEntry().getReportImage().getLongId());
                result.setManualStateAttachmentName(trade.getCurrentManualStateHistEntry().getReportImage().getName());
            }

            result.setMdCurrency(trade.getCurrency());
            result.setTradePrice(trade.getPrice());
            if (trade.getCurrentReclStateHistEntry() != null) {
                result.setReminderLevel(trade.getCurrentReclStateHistEntry().getLevel());
                result.setReclamationIsClosed(Boolean.valueOf(trade.getCurrentReclStateHistEntry().isClosed()));
                result.setReclamationClosedComment(trade.getCurrentReclStateHistEntry().getClosedComment());
                result.setReclamationStateComment(trade.getCurrentReclStateHistEntry().getComment());
                result.setReclamationStateCode(trade.getCurrentReclStateHistEntry().getReclamationState()
                        .getStateCode());
                if (trade.getCurrentReclStateHistEntry().getReportImage() != null) {
                    result.setReclamationStateAttachmentId(trade.getCurrentReclStateHistEntry().getReportImage()
                            .getLongId());
                    result.setReclamationStateAttachmentName(trade.getCurrentReclStateHistEntry().getReportImage()
                            .getName());
                }
                if (!isListMode) {
                    EmployeeImpl employee = trade.getCurrentReclStateHistEntry().getCreatedByEmployee();
                    if (employee != null) {
                        result.setReclamationStateByEmployeeId(employee.getLongId());
                        result.setReclamationStateByName(employee.getFullName());
                    } else {
                        result.setReclamationStateByName(trade.getCurrentReclStateHistEntry().getModifiedBy());
                    }
                }
            }
            result.setSettlementDate(trade.getSettlementDate());
            result.setSparkasse(Boolean.valueOf(false));
            result.setSystemDate(trade.getSystemDate());
            result.setStornoGroup(Boolean.valueOf(trade.isTradeGroup()));
            result.setStornoGroupId(trade.getTradeGroupId());
            result.setTradeDate(trade.getTradeDate());
            result.setTradeId(trade.getTradeId());
            result.setTradeCategory(trade.getTradeCategory());
            result.setTraderId(trade.getTraderId());
            if (trade.getTrader() != null && trade.getTrader().getEmployee() != null) {
                result.setTraderName(trade.getTrader().getEmployee().getFullName());
            } else {
                result.setTraderName(trade.getTraderName());
            }
           	result.setTraderLocation(trade.getLocationTrader());
            result.setVolume(trade.getVolume());
        }
        return result;
    }

    private static TradeOverviewVo createTradeOverviewVoFromPrimeEquity(PrimeEquityImpl primeTrade, boolean isListMode) {
        TradeOverviewVo result = createTradeOverviewVoFromTrade(primeTrade, isListMode);
        result.setAutomatic(Boolean.valueOf(primeTrade.isAutomatic()));
        result.setInternal(Boolean.valueOf(primeTrade.isInternalDeal()));
        result.setMischPrice(null);
        result.setNet(Boolean.valueOf(primeTrade.isNet()));
        result.setStorno(Boolean.valueOf(primeTrade.isStorno()));
        result.setBuy(Boolean.valueOf(primeTrade.isBuy()));
        result.setInstrument(primeTrade.getInstrumentName());
        result.setExchange(primeTrade.getSourceSystemExchange());
        result.setMemo(primeTrade.getFreeText());
        result.setStornoGroupId(primeTrade.getTradeGroupId());
        result.setFxRate(primeTrade.getFxRate());
        result.setVolume2(primeTrade.getContractSize());
        result.setSubType(primeTrade.getQuoteType());
        return result;
    }


    private static TradeOverviewVo createTradeOverviewVoFromSummitMoneyMarket(SummitMoneyMarketImpl moneyMarketTrade, boolean isListMode) {
        TradeOverviewVo result = createTradeOverviewVoFromTrade(moneyMarketTrade, isListMode);
        result.setMemo(moneyMarketTrade.getInfo());
        result.setTradeType(moneyMarketTrade.getTradeCategory());
        result.setMaturityDate(moneyMarketTrade.getMaturityDate());
        result.setFxRate(moneyMarketTrade.getEodRate());
        result.setMargin(moneyMarketTrade.getMarginPoints());
        result.setRecordType(moneyMarketTrade.getStatus());
        result.setTradeState(moneyMarketTrade.getStatus());
        result.setAdditionalPrice(moneyMarketTrade.getEodRateMM());
        return result;
    }

    private static TradeOverviewVo createTradeOverviewVoFromSummitForeignExchange(SummitForeignExchangeImpl moneyMarketTrade, boolean isListMode) {
        TradeOverviewVo result = createTradeOverviewVoFromTrade(moneyMarketTrade, isListMode);
        result.setMemo(moneyMarketTrade.getInfo());
        result.setTradeType(moneyMarketTrade.getTradeCategory());
        result.setMaturityDate(moneyMarketTrade.getFarDate());
        result.setFxRate(moneyMarketTrade.getEodRate());
        result.setMargin(moneyMarketTrade.getMarginPoints());
        result.setRecordType(moneyMarketTrade.getStatus());
        result.setTradeState(moneyMarketTrade.getStatus());
        result.setFarTurnover(moneyMarketTrade.calculateFarTurnover(moneyMarketTrade.getMarketRate()));
        result.setNearTurnover(moneyMarketTrade.calculateNearTurnover(moneyMarketTrade.getMarketRate()));
        result.setAdditionalPrice(moneyMarketTrade.getEodRateFX());
        result.setFwdPointsFarLeg(moneyMarketTrade.getFwdPointsFarLeg());
        result.setFwdPointsNearLeg(moneyMarketTrade.getFwdPointsNearLeg());
        result.setMarketPointsNearLeg(moneyMarketTrade.getMarketPointsNearLeg());
        result.setMarketPointsFarLeg(moneyMarketTrade.getMarketPointsFarLeg());
        result.setVolume2(moneyMarketTrade.getFarAmount());
        result.setAdditionalPrice(moneyMarketTrade.getEodRateFX());
        return result;
    }

    private static TradeOverviewVo createTradeOverviewVoFromSummitDerivative(SummitDerivativeImpl derivativeTrade, boolean isListMode) {
        TradeOverviewVo result = createTradeOverviewVoFromTrade(derivativeTrade, isListMode);
        result.setAmendmentNpvChange(derivativeTrade.getAmendmentNpvChange());
        result.setTradeType(derivativeTrade.getTradeType());
        result.setStructure(derivativeTrade.getStructureId());
        result.setDescription(derivativeTrade.getComment());
        result.setStornoGroupId(derivativeTrade.getTradeGroupId());
        result.setCompany(derivativeTrade.getCompany());
        result.setInternal(Boolean.valueOf("I".equalsIgnoreCase(derivativeTrade.getInternalExternal())));
        result.setDelta(derivativeTrade.getDelta());
        result.setVega(derivativeTrade.getVega());
        result.setPremium(derivativeTrade.getPremium());
        result.setTurnover(derivativeTrade.getTurnover());
        result.setSubType(derivativeTrade.getTradeSubType());
        if (derivativeTrade.getInstrument() != null) {
        	result.setInstrument(derivativeTrade.getInstrument().getInstrumentName());
        	if (derivativeTrade.getInstrument().getPriceCheckCategory() != null) {
        	 if (derivativeTrade.getInstrument().getPriceCheckCategory().getPriceToleranceAbsolute() != 0) {
        		 result.setPriceTolerance(derivativeTrade.getInstrument().getPriceCheckCategory().getPriceToleranceAbsolute());
        	 } else {
        		 result.setPriceTolerance(derivativeTrade.getInstrument().getPriceCheckCategory().getPriceTolerancePercent());
        	 }
        	}
		}
        result.setPriceToleranceViolation(Boolean.valueOf(derivativeTrade.isOutOfPrice()));
        result.setCurrentPriceDifference(derivativeTrade.getDeviation());
        
        Collection<AssetImpl> coll = derivativeTrade.getAssets();
        if (!coll.isEmpty()) {
        	AssetVo[] assetArray = new AssetVo[coll.size()];
        	int i = 0;
        	for (Iterator<AssetImpl> iter = coll.iterator(); iter.hasNext();) {
				AssetImpl asset = iter.next();
				assetArray[i] = VoFactory.createAssetVo(asset);
				i++;
			}
            result.setAssets(assetArray);
        }
        return result;
    }

    private static TradeOverviewVo createTradeOverviewVoFromSummitBond(SummitBondImpl sumitBondTrade, boolean isListMode) {
        TradeOverviewVo result = createTradeOverviewVoFromTrade(sumitBondTrade, isListMode);
        result.setTradeType(sumitBondTrade.getTradeType());
        result.setStructure(sumitBondTrade.getStructure());
        result.setMaturityDate(sumitBondTrade.getExpireDay());
        result.setMarketer(sumitBondTrade.getMarketer());
        result.setSmallCustomer(Boolean.valueOf(sumitBondTrade.getIsSmallCustomer()));
        result.setSparkasse(Boolean.FALSE);
        result.setCancelation(Boolean.valueOf(sumitBondTrade.isStatusCanceled()));
        result.setLowTurnOver(Boolean.valueOf(sumitBondTrade.getIsBagatelle()));
        result.setHighTurnOver(Boolean.valueOf(sumitBondTrade.getIsOutOfTurnoverLimit()));
        result.setVivaldisTrade(Boolean.valueOf(sumitBondTrade.getCompany() != null && sumitBondTrade.getCompany().toUpperCase().startsWith(SummitBondImpl.VIVALDIS_TRADE)));
        result.setSubType(sumitBondTrade.getSubType());
        result.setTradeState(sumitBondTrade.getStatus());
        result.setTradeCategory(sumitBondTrade.getCategory());
        result.setInstrumentName(sumitBondTrade.getInstrumentName());
        result.setTheoreticPrice(sumitBondTrade.getTheoreticalPrice());
        result.setAdditionalPrice(sumitBondTrade.getTradePrice());
        result.setMemo(sumitBondTrade.getAlias());
        result.setBloombergTradeId(sumitBondTrade.getSourceSystemBloombergId());
        result.setCounterpartyReference(sumitBondTrade.getCounterpartyReference());
        result.setDescription(sumitBondTrade.getDescription());
        result.setFxRate(sumitBondTrade.getFxEuroRate());
        if (sumitBondTrade.getBloombergInstrument() != null) {
            if (sumitBondTrade.getBloombergInstrument().getInstrumentName() != null) {
                result.setIsin(sumitBondTrade.getBloombergInstrument().getInstrumentName());
            } else {
                result.setIsin(sumitBondTrade.getBloombergInstrument().getInstrument());
            }
        } else {
            if (sumitBondTrade.getSourceSystemInstrument() != null) {
                result.setIsin(sumitBondTrade.getSourceSystemInstrument());
            }
        }
        result.setUpdatedBy(sumitBondTrade.getUpdatedBy());
        return result;
    }

    private static TradeOverviewVo createTradeOverviewVoFromSummitRepo(SummitRepoImpl trade, boolean isListMode) {
        RepoTradeOverviewVo result = new RepoTradeOverviewVo();
        if (trade != null) {
            result.setId(trade.getLongId());
            result.setAmendDate(trade.getAmendedDate());

            if (trade.getCurrentAutoState() != null) {
                result.setAutomaticStateCode(trade.getCurrentAutoState().getStateCode());
                if (!isListMode) {
                    EmployeeImpl employee = trade.getCurrentAutoStateHistEntry().getCreatedByEmployee();
                    if (employee != null) {
                        result.setAutomaticStateByEmployeeId(employee.getLongId());
                        result.setAutomaticStateByName(employee.getFullName());
                    } else {
                        result.setAutomaticStateByName(trade.getCurrentAutoStateHistEntry().getModifiedBy());
                    }
                }
            }
            result.setBasePointTolerance(trade.getBasePointTolerance());
            if (trade.getBasePointTolerance() == 0) {
                result.setBasePointTolerance(trade.calcRateTolerance());
            }
            result.setBasePointDifference(trade.getRateDiff());
            result.setBookId(trade.getBookId());
            result.setCurrency(trade.getCurrency());
            result.setCounterparty(trade.getCounterpartyId());
            result.setEndCash(trade.getEndCash());
            result.setEndDate(trade.getEndDate());
            result.setForeignExchangeRate(trade.getForeignExchangeRate());
            result.setInstrument(trade.getInstrumentCode());
            result.setInstrumentType(trade.getUnderlyingInstrumentType());
            result.setIsin(trade.getSourceSystemInstrument());
            result.setLateEntry(Boolean.valueOf(trade.isLateDeal()));
            if (trade.isSparkasse()) {
                result.setSparkasse(Boolean.valueOf(trade.getCounterparty() != null && trade.getCounterparty().getSparkasse() != null && trade.getCounterparty().getSparkasse().isActive()));
            } else {
                result.setSparkasse(Boolean.FALSE);
            }
            if (trade.getCurrentManualState() != null) {
                result.setManualStateCode(trade.getCurrentManualState().getStateCode());
                result.setManualStateComment(trade.getCurrentManualStateHistEntry().getComment());
                if (!isListMode) {
                    EmployeeImpl employee = trade.getCurrentManualStateHistEntry().getCreatedByEmployee();
                    if (employee != null) {
                        result.setManualStateByEmployeeId(employee.getLongId());
                        result.setManualStateByName(employee.getFullName());
                    } else {
                        result.setManualStateByName(trade.getCurrentManualStateHistEntry().getModifiedBy());
                    }
                }
            }
            if (trade.getCurrentManualStateHistEntry() != null
                    && trade.getCurrentManualStateHistEntry().getReportImage() != null) {
                result.setManualStateAttachmentId(trade.getCurrentManualStateHistEntry().getReportImage().getLongId());
                result.setManualStateAttachmentName(trade.getCurrentManualStateHistEntry().getReportImage().getName());
            }

            result.setMdCurrency(trade.getCurrency());
            result.setMaturityDays(trade.getDays());
            result.setOpenEnd(trade.isOpenEnd());
            result.setPortfolio(trade.getBookId());
            result.setProfitLossEffect(trade.getProfitLossDiffEuro());
            result.setRepoMarketType(trade.getRepoMarketType());
            result.setRepoRate(trade.getRepoRate());
            result.setMarketPrice(trade.calcRateReference());

            if (trade.getCurrentReclStateHistEntry() != null) {
                result.setReminderLevel(trade.getCurrentReclStateHistEntry().getLevel());
                result.setReclamationStateCode(trade.getCurrentReclStateHistEntry().getReclamationState()
                        .getStateCode());
                result.setReclamationIsClosed(Boolean.valueOf(trade.getCurrentReclStateHistEntry().isClosed()));
                result.setReclamationClosedComment(trade.getCurrentReclStateHistEntry().getClosedComment());
                if (trade.getCurrentReclStateHistEntry().getReportImage() != null) {
                    result.setReclamationStateAttachmentId(trade.getCurrentReclStateHistEntry().getReportImage()
                            .getLongId());
                    result.setReclamationStateAttachmentName(trade.getCurrentReclStateHistEntry().getReportImage()
                            .getName());
                }
                if (!isListMode) {
                    EmployeeImpl employee = trade.getCurrentReclStateHistEntry().getCreatedByEmployee();
                    if (employee != null) {
                        result.setReclamationStateByEmployeeId(employee.getLongId());
                        result.setReclamationStateByName(employee.getFullName());
                    } else {
                        result.setReclamationStateByName(trade.getCurrentReclStateHistEntry().getModifiedBy());
                    }
                }
            }
            result.setSettlementDate(trade.getSettlementDate());
            result.setStartCash(trade.getStartCash());
            result.setStartDate(trade.getStartDate());
            result.setStornoGroup(Boolean.valueOf(trade.isTradeGroup()));
            result.setSystemDate(trade.getSystemDate());
            result.setTradeDate(trade.getTradeDate());
            result.setTradePrice(trade.getStartPrice());
            result.setTradeId(trade.getTradeId());
            result.setTraderId(trade.getTraderId());
            if (trade.getTrader() != null && trade.getTrader().getEmployee() != null) {
                result.setTraderName(trade.getTrader().getEmployee().getFullName());
            } else {
                result.setTraderName(trade.getTraderName());
            }
            result.setTraderLocation(trade.getLocationTrader());
            result.setUnderlyingType(trade.getUnderlyingValGroup());
            result.setUnderlyingValueGroup(trade.getUnderlyingCategory());
            result.setVolume(trade.getVolume());
            if ( trade.getBondInstrument() != null && trade.getBondInstrument().getPriceCheckCategory() != null) {
                result.setPriceTolerance(trade.getBondInstrument().getPriceCheckCategory().getPriceTolerancePercent());
            }
            result.setPriceToleranceViolation(Boolean.valueOf(!trade.rateDiffLessBpTolerance()));
        	result.setVerifyDate(trade.getVerifyDate());
        	result.setMarketPriceUnderlying(trade.getMarketPriceUnderlying());
        	result.setDealAccruedInterest(trade.getDealAccruedInterest());	
        	result.setStartPrice(trade.getStartPrice());
        	result.setBondAccruedInterest(trade.getBondAccruedInterest());
        	result.setNpv(trade.getNpv());

        	result.setCounterpartyReference(trade.getCustomerType());

        }
        return result;
    }

    public static PriceCheckCategoryVo createPriceCheckCategoryVo(PriceCheckCategoryImpl priceCheckCategory) {
    	PriceCheckCategoryVo vo = new PriceCheckCategoryVo();
    	vo.setName(priceCheckCategory.getName());
    	vo.setId(priceCheckCategory.getLongId());
    	vo.setPriceTolerancePercent(priceCheckCategory.getPriceTolerancePercent());
    	vo.setPriceToleranceAbsolute(priceCheckCategory.getPriceToleranceAbsolute());
    	vo.setTimeToleranceMinutes(priceCheckCategory.getTimeToleranceMinutes());
    	if (priceCheckCategory.getSampleState() != null) {
    		vo.setManualSampleCode(priceCheckCategory.getSampleState().getStateCode());
    	}
    	vo.setManualSamplePercentage(Integer.valueOf(priceCheckCategory.getSamplePercentage()));
    	vo.setChangePending(false);
    	return vo;
    }
}