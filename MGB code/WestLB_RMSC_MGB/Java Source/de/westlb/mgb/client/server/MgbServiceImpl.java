package de.westlb.mgb.client.server;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;

import de.westlb.mgb.MgbException;
import de.westlb.mgb.bloomberg.model.BlpApiBloombergRequester;
import de.westlb.mgb.bloomberg.model.EuwaxWebRequester;
import de.westlb.mgb.bloomberg.model.PricingRequestComunicationException;
import de.westlb.mgb.bloomberg.model.PricingRequestException;
import de.westlb.mgb.bloomberg.model.PricingRequestTimeOutException;
import de.westlb.mgb.bloomberg.model.PricingRequesterFactory;
import de.westlb.mgb.client.mail.MailConnection;
import de.westlb.mgb.client.mail.MailException;
import de.westlb.mgb.client.mask.model.shared.PriceFetcherModel;
import de.westlb.mgb.client.priceprovider.DbPriceProvider;
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
import de.westlb.mgb.client.server.vo.StatisticContextVo;
import de.westlb.mgb.client.server.vo.StatisticCubeReportVo;
import de.westlb.mgb.client.server.vo.StatisticReportParamsVo;
import de.westlb.mgb.client.server.vo.StatisticReportVo;
import de.westlb.mgb.client.server.vo.StornoGroupVo;
import de.westlb.mgb.client.server.vo.TradeHistoryEntryAttachmentVo;
import de.westlb.mgb.client.server.vo.TradeHistoryEntryVo;
import de.westlb.mgb.client.server.vo.TradeOverviewVo;
import de.westlb.mgb.client.server.vo.TradeParameterVo;
import de.westlb.mgb.client.server.vo.TradeReclRequiredVo;
import de.westlb.mgb.client.server.vo.TradeReclamationVo;
import de.westlb.mgb.client.server.vo.TradeSearchParamsVo;
import de.westlb.mgb.client.server.vo.TradeSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.TraderSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.TraderVo;
import de.westlb.mgb.client.server.vo.UserStatisticVo;
import de.westlb.mgb.client.ui.util.CurrencyFormat;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.converter.ConverterException;
import de.westlb.mgb.converter.MarketDataRequestable;
import de.westlb.mgb.converter.MgbConverter;
import de.westlb.mgb.converter.MgbConverterFactory;
import de.westlb.mgb.model.HistoricPrice;
import de.westlb.mgb.model.Price;
import de.westlb.mgb.model.definition.DualControlJobOperationDef;
import de.westlb.mgb.model.definition.ImportPropertiesDef;
import de.westlb.mgb.model.definition.InstrumentNameDef;
import de.westlb.mgb.model.definition.JobStateDef;
import de.westlb.mgb.model.definition.MailDefinition;
import de.westlb.mgb.model.definition.MailTypeDef;
import de.westlb.mgb.model.definition.MandantDef;
import de.westlb.mgb.model.definition.MarketDataRequestStateDef;
import de.westlb.mgb.model.definition.MarketDataRequestTypeDef;
import de.westlb.mgb.model.definition.MarketDataSourceDef;
import de.westlb.mgb.model.definition.MgbConfigurationDef;
import de.westlb.mgb.model.definition.PriceDef;
import de.westlb.mgb.model.definition.RoleDef;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
import de.westlb.mgb.model.impl.AccessControlImpl;
import de.westlb.mgb.model.impl.AddonImpl;
import de.westlb.mgb.model.impl.AutoStateHistEntryImpl;
import de.westlb.mgb.model.impl.AutomaticStateImpl;
import de.westlb.mgb.model.impl.BloombergCurrencyCodesImpl;
import de.westlb.mgb.model.impl.BloombergMaturityCodesImpl;
import de.westlb.mgb.model.impl.BookImpl;
import de.westlb.mgb.model.impl.ConditionImpl;
import de.westlb.mgb.model.impl.DualControlJobImpl;
import de.westlb.mgb.model.impl.DualControlJobProcessorImpl;
import de.westlb.mgb.model.impl.EmployeeImpl;
import de.westlb.mgb.model.impl.EuwaxRequestImpl;
import de.westlb.mgb.model.impl.ExchangeImpl;
import de.westlb.mgb.model.impl.ExchangeMappingEntryImpl;
import de.westlb.mgb.model.impl.ExchangeMappingEntryPrioImpl;
import de.westlb.mgb.model.impl.InstrumentImpl;
import de.westlb.mgb.model.impl.JobImpl;
import de.westlb.mgb.model.impl.MailCopyRecipientImpl;
import de.westlb.mgb.model.impl.MailImpl;
import de.westlb.mgb.model.impl.MailRecipientImpl;
import de.westlb.mgb.model.impl.MandantImpl;
import de.westlb.mgb.model.impl.ManualSampleStateImpl;
import de.westlb.mgb.model.impl.ManualStateHistEntryImpl;
import de.westlb.mgb.model.impl.ManualStateImpl;
import de.westlb.mgb.model.impl.MgbConfigurationIdImpl;
import de.westlb.mgb.model.impl.MgbConfigurationImpl;
import de.westlb.mgb.model.impl.MgbTaskImpl;
import de.westlb.mgb.model.impl.PriceCheckCategoryImpl;
import de.westlb.mgb.model.impl.PriceCheckInstrumentImpl;
import de.westlb.mgb.model.impl.PriceImpl;
import de.westlb.mgb.model.impl.ReclStateHistEntryImpl;
import de.westlb.mgb.model.impl.ReclamationStateImpl;
import de.westlb.mgb.model.impl.RepoImpl;
import de.westlb.mgb.model.impl.ReportImageImpl;
import de.westlb.mgb.model.impl.RequestImpl;
import de.westlb.mgb.model.impl.ReutersRequestImpl;
import de.westlb.mgb.model.impl.RoleImpl;
import de.westlb.mgb.model.impl.SourceSystemImpl;
import de.westlb.mgb.model.impl.StateIdImpl;
import de.westlb.mgb.model.impl.StateImpl;
import de.westlb.mgb.model.impl.StateRulesImpl;
import de.westlb.mgb.model.impl.StatusCalculatorImpl;
import de.westlb.mgb.model.impl.SummitAmendImpl;
import de.westlb.mgb.model.impl.SummitBondImpl;
import de.westlb.mgb.model.impl.SummitDerivativeImpl;
import de.westlb.mgb.model.impl.SummitForeignExchangeImpl;
import de.westlb.mgb.model.impl.SummitRepoImpl;
import de.westlb.mgb.model.impl.TimePeriodCategoryImpl;
import de.westlb.mgb.model.impl.TradeHistEntryImpl;
import de.westlb.mgb.model.impl.TradeImpl;
import de.westlb.mgb.model.impl.TraderImpl;
import de.westlb.mgb.model.impl.UserRoleImpl;
import de.westlb.mgb.model.impl.finder.BloombergCurrencyFinderImpl;
import de.westlb.mgb.model.impl.finder.BloombergMaturityFinderImpl;
import de.westlb.mgb.model.impl.finder.DualControlJobFinderImpl;
import de.westlb.mgb.model.impl.finder.DualControlJobSearchParams;
import de.westlb.mgb.model.impl.finder.EmployeeSearchParams;
import de.westlb.mgb.model.impl.finder.InstrumentSearchParams;
import de.westlb.mgb.model.impl.finder.JobSearchParams;
import de.westlb.mgb.model.impl.finder.MailFinderImpl;
import de.westlb.mgb.model.impl.finder.MailSearchParams;
import de.westlb.mgb.model.impl.finder.MgbConfigurationSearchParams;
import de.westlb.mgb.model.impl.finder.MgbFinderImpl;
import de.westlb.mgb.model.impl.finder.PriceCheckCategorySearchParams;
import de.westlb.mgb.model.impl.finder.RequestSearchParams;
import de.westlb.mgb.model.impl.finder.SearchParams;
import de.westlb.mgb.model.impl.finder.TradeSearchParams;
import de.westlb.mgb.model.impl.finder.TraderSearchParams;
import de.westlb.mgb.model.impl.statistic.ReportSearchParams;
import de.westlb.mgb.model.impl.statistic.StatisticContextImpl;
import de.westlb.mgb.model.impl.statistic.StatisticCubeReportFactoryImpl;
import de.westlb.mgb.model.impl.statistic.StatisticCubeReportImpl;
import de.westlb.mgb.model.impl.statistic.StatisticReportFactoryImpl;
import de.westlb.mgb.model.impl.statistic.StatisticReportImpl;
import de.westlb.mgb.model.impl.statistic.StatisticReportParamsImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.StoreSingleton;
import de.westlb.mgb.persistence.Transaction;
import de.westlb.mgb.server.security.SecurityUtils;
import de.westlb.mgb.server.statistic.ApplicationStatisticHolder;
import de.westlb.mgb.server.statistic.UserStatisticHolder;
import de.westlb.mgb.util.DualControlJobEntityAccessorFilter;
import de.westlb.mgb.util.MgbMethodFilter;
import de.westlb.mgb.util.MgbSummitBondMethodFilter;
import de.westlb.mgb.util.MgbSummitDerivativeMethodFilter;
import de.westlb.mgb.util.MgbSummitRepoMethodFilter;
import de.westlb.mgb.util.ObjectComparator;
import de.westlb.mgb.util.PriceUtils;
import de.westlb_systems.xaf.util.PropertyFactory;


/**
 * @author D055625
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class MgbServiceImpl implements Mgb, RoleDef {

    /**
     * 
     */
    private static final long serialVersionUID = -3465055526622929635L;

    static Logger logger = Logger.getLogger(MgbServiceImpl.class.getName());
	
    private boolean performFourEyeSave = true;

    // Session information
    private transient Session session = null;

    private boolean isBusy = false;

    private EmployeeImpl user = null;

    private String domainUserNtId = null;
	
    private MandantImpl mandant = null;

    private JobImpl[] selectedJobs = null;

    private Map<Long, Blob> attachmentBlobMap = new HashMap<Long, Blob>();
    
    private Map<String, Object> clientConfig = new HashMap<String, Object>();
    
    private Map<String, String> reportConfigMapping = null; 

    private JobImpl[] getInitialJobSelection() throws PersistenceException, RemoteException {
        MgbFinderImpl finder = new MgbFinderImpl(getSession());
        JobSearchParams param = new JobSearchParams(mandant, null, null, new String[] {JobStateDef.JOB_OK_STATUS, JobStateDef.JOB_SPK_CLOSED_STATUS}, true);
        Collection<JobImpl> col = finder.findJobs(param);
        logger.debug("Found " + col.size() + " jobs.");
        return col.toArray(new JobImpl[0]);
    }

    private JobImpl[] getValidatedJobSelection(JobVo[] selectedJobVos) throws PersistenceException, RemoteException {
        ArrayList<JobImpl> list = new ArrayList<JobImpl>();
        for (int i = 0; i < selectedJobVos.length; i++) {
            JobImpl job = (JobImpl) getSession().select(JobImpl.class, selectedJobVos[i].getJobId());
            if (job != null && !JobStateDef.JOB_RUNNING_STATUS.equals(job.getStatus()) && job.getStatus() != null && !job.getStatus().startsWith(JobStateDef.JOB_ERROR_STATUS)) {
                list.add(job);
            }
        }
        return list.toArray(new JobImpl[0]);
    }

    private JobVo[] getAvailableJobVos() throws PersistenceException, RemoteException {
        MgbFinderImpl finder = new MgbFinderImpl(getSession());
        JobSearchParams param = new JobSearchParams(mandant, null, null, new String[] {JobStateDef.JOB_OK_STATUS, JobStateDef.JOB_SPK_CLOSED_STATUS}, false);
        Collection<JobImpl> col = finder.findJobs(param);
        logger.debug("Found " + col.size() + " jobs.");
        JobVo[] jobVos = new JobVo[col.size()];
        Iterator<JobImpl> it = col.iterator();
        int i = 0;
        while (it.hasNext()) {
            jobVos[i] = VoFactory.createJobVo(it.next());
            i++;
        }
        return jobVos;
    }

    private JobVo[] getSelectedJobVos() {
        JobVo[] jobVos = new JobVo[selectedJobs.length];
        for (int i = 0; i < jobVos.length; i++) {
            jobVos[i] = VoFactory.createJobVo(selectedJobs[i]);
        }
        return jobVos;
    }

    private TradeSearchParams createCurrentTradeSearchParam() {
        TradeSearchParams param = new TradeSearchParams();
        param.setMandant(mandant);
        param.setJobs(selectedJobs);
        return param;
    }

    private void throwRemoteException(Exception exception) throws RemoteException {
        logger.error(exception.getMessage(),exception);
        throw new RemoteException(exception.getMessage());
    }

    private String valueAsString(Object o) {
        DecimalFormat df = new DecimalFormat("0.0000");
        if (o == null) {
            return "";
        } else if (o instanceof Calendar) {
            return ((Calendar) o).getTime().toString();
        } else if (o instanceof Float || o instanceof Double) {
            return df.format(o);
        } else {
            return o.toString();
        }
    }

    public void openSession() throws PersistenceException {
        if (mandant != null && user != null && user.getNtId() != null) {
            session = StoreSingleton.getUniqueInstance().openSession(mandant, user.getNtId());
        } else {
            session = StoreSingleton.getUniqueInstance().openSession();
        }
        isBusy = true;
    }

    public void closeSession() throws PersistenceException {
        if (session != null) {
            session.close();
        }
        isBusy = false;
    }

    private Session getSession() throws RemoteException {
    	if (session == null || !session.isOpen()) {
    		try {
    			closeSession();
    			openSession();
    		} catch (PersistenceException pe) {
	           throwRemoteException(pe);
    	 	}
    	}
    	return session;
    }
 
    
	@Override
    public boolean isBusy() throws RemoteException {
		if (isBusy) {
			logger.warn("MGB is busy (session="+session+")");
		}
		return isBusy;
	}
    /**
     * @see de.westlb.mgb.client.server.Mgb#getStateStatistic(String)
     */
    @Override
    public StateStatisticEntryVo getStateStatistic(String stateCode) throws RemoteException {
        // DUMMY
        return null;
    }

	@Override
    public String getDomainUser() throws RemoteException {
		return domainUserNtId;
	}

	private void readObject(java.io.ObjectInputStream in)
	throws IOException,ClassNotFoundException 
	{
	    in.defaultReadObject();
        PropertyFactory.load("import");
        PropertyFactory.load("server");
	}
	
    /**
     * @see de.westlb.mgb.client.server.Mgb#login(String)
     */
    @Override
    public void login(String domainUserNtId, String mandantCode, String comment) throws java.rmi.RemoteException {
        MgbFinderImpl finder = new MgbFinderImpl(getSession());
        PropertyFactory.load("import");
        PropertyFactory.load("server");

        this.domainUserNtId = domainUserNtId.toLowerCase();
        String userNtId = SecurityUtils.extractUserFromDomainUser(domainUserNtId);

        try {
            logger.debug("Looking up user with NT-ID " + userNtId + "("+domainUserNtId+") for mandant " + mandantCode + ". "+(comment==null?"":comment));
            /*
             * 2004/06/24 MB Removed check for CONTROLLER role, because the
             * authorization check is implemented on method level now and all
             * users which any role must be able to call the login method.
             */
            MandantImpl requestedMandant = null;
            if (mandantCode != null) {
                requestedMandant = (MandantImpl) getSession().select(MandantImpl.class, mandantCode);
            }
            EmployeeImpl employee = finder.findFirstEmployeeWithNtId(userNtId, requestedMandant, true);
                
            if (employee == null) {
                MgbException mgbE = new MgbException(MgbException.E_NOT_AUTHORIZED, userNtId);
                throwRemoteException(mgbE);
            } else {
                saveEmployeeLastLogin(employee);
                logger.debug("Found user " + employee.getFullName() + " with NT-ID " + userNtId + " for mandant " + employee.getMandant() + ".");
            }
            user = employee;
            mandant = employee.getMandant();
            
            selectedJobs = getInitialJobSelection();
            
            reportConfigMapping = finder.findReportConfigMapping(true); 

        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
    }

    private void saveEmployeeLastLogin(EmployeeImpl employee) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            employee.setLastLoginDate(Calendar.getInstance());
            t.commit();
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
    }
    

	@Override
    public JobVo[] getRecentJobs(String mandantCode) throws RemoteException {
    	JobVo[] jobVos = null;
        try {
        	MgbFinderImpl finder = new MgbFinderImpl(getSession());
        	JobSearchParams param = new JobSearchParams(new MandantImpl(mandantCode), null, null, new String[] {JobStateDef.JOB_OK_STATUS, JobStateDef.JOB_SPK_CLOSED_STATUS}, true);
        	Collection<JobImpl> col = finder.findJobs(param);
        	
        	jobVos = new JobVo[col.size()];
        	Iterator<JobImpl> it = col.iterator();
        	int i = 0;
        	while (it.hasNext()) {
        		jobVos[i] = VoFactory.createJobVo(it.next());
        		i++;
        	}
        	logger.debug("Found " + col.size() + " jobs.");
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
       	return jobVos;
    }

    @Override
    public CheckStateVo getCheckState() throws RemoteException {
        CheckStateVo result = null;

        try {
            ReportSearchParams searchParams = new ReportSearchParams() {

                @Override
                public Calendar getFromDate() {
                    return null;
                }

                @Override
                public Calendar getToDate() {
                    return null;
                }

                @Override
                public JobImpl[] getJobs() {
                    return selectedJobs;
                }

                @Override
                public MandantImpl getMandant() {
                    return mandant;
                }
            };
            result = new StatisticReportFactoryImpl(getSession()).createBasicStatistic(searchParams);
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }

        return result;
    }

    @SuppressWarnings("unused")
    private Collection<TradeImpl> getSelectedTrades() throws RemoteException {
        Long jobIds[] = new Long[selectedJobs.length];
        for (int i = 0; i < selectedJobs.length; i++) {
            jobIds[i] = selectedJobs[i].getLongId();
        }
        return getTradesForJobIds(jobIds);
    }

    private Collection<TradeImpl> getTradesForJobIds(Long[] jobIds) throws RemoteException {
        JobImpl[] jobs = new JobImpl[jobIds.length];
        try {
            for (int i = 0; i < jobIds.length; i++) {
                jobs[i] = (JobImpl) getSession().select(JobImpl.class, jobIds[i]);
            }

        } catch (PersistenceException pE) {
            throwRemoteException(pE);

        }
        return getTradesForJobs(jobs);
    }

    private Collection<TradeImpl> getTradesForJobs(JobImpl[] jobs) throws RemoteException {
        Collection<TradeImpl> trades = new ArrayList<TradeImpl>();
        for (int i = 0; i < jobs.length; i++) {
            Collection<TradeImpl> jobTrades = jobs[i].getTrades();
            trades.addAll(jobTrades);
        }

        return trades;
    }

    @Override
    public CheckStateVo getCheckState(final Long[] jobIds) throws RemoteException {
        CheckStateVo result = null;
        ReportSearchParams searchParams = new ReportSearchParams() {

            @Override
            public Calendar getFromDate() {
                return null;
            }

            @Override
            public Calendar getToDate() {
                return null;
            }

            @Override
            public JobImpl[] getJobs() {
                return JobImpl.getJobs(jobIds);
            }

            @Override
            public MandantImpl getMandant() {
                return mandant;
            }
        };

        try {
            result = new StatisticReportFactoryImpl(getSession()).createBasicStatistic(searchParams);
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }

        return result;
    }

    @Override
    public TradeSearchResultEntryVo[] findTrades(TradeSearchParamsVo searchParams) throws java.rmi.RemoteException {
        ArrayList<TradeSearchResultEntryVo> resultArray = new ArrayList<TradeSearchResultEntryVo>();
        try {

            Collection<TradeImpl> col = findTradeImplCollection(searchParams);

            logger.debug("Found " + col.size() + " trades.");
            Iterator<TradeImpl> it = col.iterator();
            while (it.hasNext()) {
                TradeImpl trade = it.next();
                TradeSearchResultEntryVo result = VoFactory.createTradeSearchResultEntryVo(trade);
                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return resultArray.toArray(new TradeSearchResultEntryVo[0]);
    }

    private Collection<TradeImpl> findTradeImplCollection(TradeSearchParamsVo searchParams) throws PersistenceException,
            RemoteException {
        TradeSearchParams param = new TradeSearchParams();

        if (searchParams.getMandantCode() != null) {
            MandantImpl man = new MandantImpl(searchParams.getMandantCode());
            param.setMandant(man);
            if (searchParams.getStateCode() != null) {
                param.setStateId(new StateIdImpl(man, searchParams.getStateCode()));
            }
        }
        param.setFromDate(searchParams.getFromDate());
        param.setToDate(searchParams.getToDate());
        param.setJobIds(searchParams.getJobIds());
        param.setTradeIds(searchParams.getTradeIds());
        param.setInstruments(searchParams.getInstruments());
        param.setJobStatus(searchParams.getJobStatus());
        param.setFromJobCobDate(searchParams.getFromJobCobDate());
        param.setToJobCobDate(searchParams.getToJobCobDate());
        param.setFromJobCreationDate(searchParams.getFromJobCreationDate());
        param.setToJobCreationDate(searchParams.getToJobCreationDate());

        param.setIsManualCheckRequired(searchParams.getIsManualCheckRequired());
        param.setIsManualCheckRequiredButNotHandled(searchParams.getIsManualCheckRequiredButNotHandled());
        param.setIsMarketDataRequestRequired(searchParams.getIsMarketDataRequestRequired());
        param.setIsReclamationRequiredButNotHandled(searchParams.getIsReclamationRequiredButNotHandled());
        param.setIsReclamationClosed(searchParams.getIsReclamationClosed());
        param.setIsLateEntry(searchParams.getIsLateEntry());
        param.setIsSmallCustomer(searchParams.getIsSmallCustomer());
        param.setIsStorno(searchParams.getIsStorno());
        param.setHasLowTurnover(searchParams.getHasLowTurnover());
        param.setHasPreDecessor(searchParams.getHasPreDecessor());
        param.setSparkassenId(searchParams.getSparkassenId());
        param.setMaxResults(searchParams.getMaxResults());
        param.setSparkassenReportType(searchParams.getSparkassenReportType());
        param.setLocations(extractLocationList(searchParams.getLocations()));

        if (searchParams.getIsReclamationRequiredOpen() != null && searchParams.getIsReclamationRequiredOpen().booleanValue()) {
        	// prepare a union query
            param.setIsReclamationRequiredButNotHandled(Boolean.TRUE);
            param.setIsReclamationClosed(null);            	
        }
        getSession().flush();

        MgbFinderImpl finder = new MgbFinderImpl(getSession());
        Collection<TradeImpl> col = finder.findTrades(param);

        if (searchParams.getIsReclamationRequiredOpen() != null && searchParams.getIsReclamationRequiredOpen().booleanValue()) {
        	// execute a union query
            param.setIsReclamationRequiredButNotHandled(null);
            param.setIsReclamationClosed(Boolean.FALSE);
            col.addAll(finder.findTrades(param));
        }
        return col;
    }

    private String[] extractLocationList(String reportId) throws PersistenceException, RemoteException {
        if (reportId != null) {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            Map<String,List<String>> reportConfigMap = finder.findReportConfiguration();
            if (reportConfigMap.containsKey(reportId)) {
                return reportConfigMap.get(reportId).toArray(new String[0]);
            }
        }
        return null;
    }

    @Override
    public TradeReclamationVo getTradeReclamation(Object tradeId) throws java.rmi.RemoteException {
        TradeReclamationVo result = new TradeReclamationVo();
        try {
            TradeImpl trade = (TradeImpl) getSession().select(TradeImpl.class, (Serializable) tradeId);
//            TradeImpl trade = (TradeImpl) getSession().select(AbstractFinder.getTradeClassForMandantCode(mandant.getCode()), (Serializable)tradeId);
            if (trade != null && trade.getCurrentReclStateHistEntry() != null) {
                ReclStateHistEntryImpl currentRecl = trade.getCurrentReclStateHistEntry();
                result.setLevel(currentRecl.getLevel());
                result.setComment(currentRecl.getComment());
                result.setClosed(currentRecl.isClosed());
                result.setClosedComment(currentRecl.getClosedComment());
                result.setReason(currentRecl.getReclamationState().getStateCode());
                if (currentRecl.getChangedDate() != null) {
                    result.setSendDate(currentRecl.getChangedDate());
                    result.setSenderName(currentRecl.getChangedBy());
                } else {
                    result.setSendDate(currentRecl.getCreationDate());
                    result.setSenderName(currentRecl.getCreatedBy());
                }
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return result;
    }

    @Override
    public DataSelectionVo getCurrentDataSelection() {
        DataSelectionVo data = new DataSelectionVo();
        if (mandant != null) {
            data.setMandantCode(mandant.getCode());
        }
        data.setSelectedJobs(getSelectedJobVos());
        return data;
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getDataSelection()
     */
    @Override
    public DataSelectionVo getDataSelection() throws RemoteException {
        DataSelectionVo data = new DataSelectionVo();
        try {
            if (mandant != null) {
                data.setMandantCode(mandant.getCode());
            }
            data.setAvailableJobs(getAvailableJobVos());
            data.setSelectedJobs(getSelectedJobVos());
        } catch (PersistenceException pEx) {
            throwRemoteException(pEx);
        }
        return data;
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#setDataSelection(DataSelectionVo)
     */
    @Override
    public void setDataSelection(DataSelectionVo data) throws RemoteException {
        try {
            boolean mandantChange = false;
            if (data.getMandantCode() == null) {
                mandant = null;
            } else {
                if (mandant != null && !data.getMandantCode().equals(mandant.getCode())) {
                    // a different mandant
                    mandantChange = true;
                }
                mandant = (MandantImpl) getSession().select(MandantImpl.class, data.getMandantCode());
            }

            if (mandantChange || data.getSelectedJobs().length == 0) {
                selectedJobs = getInitialJobSelection();

                MgbFinderImpl finder = new MgbFinderImpl(getSession());
                EmployeeSearchParams param = new EmployeeSearchParams();
                param.setNtId(user.getNtId());
                param.setMandant(mandant);
                Collection<EmployeeImpl> employees = finder.findEmployees(param);
                if (employees.isEmpty()) { throw new RemoteException("User " + user.getNtId()
                        + " not found in mandant " + data.getMandantCode()); }
                user = employees.iterator().next();
            } else {
                selectedJobs = getValidatedJobSelection(data.getSelectedJobs());
            }
            String dataSelection = "DataSelection set to: mandant=" + mandant + "; user=" + user +"; jobs=";
            for (int i = 0; i < selectedJobs.length; i++) {
            	dataSelection = dataSelection + " "+ selectedJobs[i].getLongId();
			}
            logger.debug(dataSelection);
        } catch (PersistenceException pEx) {
            throwRemoteException(pEx);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getHistory(long)
     */
    @Override
    public TradeHistoryEntryVo[] getHistory(Object tradeId) throws RemoteException {
        ArrayList<TradeHistoryEntryVo> resultArray = new ArrayList<TradeHistoryEntryVo>();
        try {
            TradeImpl trade = (TradeImpl) getSession().select(TradeImpl.class, (Serializable) tradeId);
//            TradeImpl trade = (TradeImpl) getSession().select(AbstractFinder.getTradeClassForMandantCode(mandant.getCode()), (Serializable)tradeId);
            if (trade != null) {
                ArrayList<TradeHistEntryImpl> list = new ArrayList<TradeHistEntryImpl>();
                list.addAll(trade.getHistoryEntries());
                logger.debug("Found " + list.size() + " trade history enties.");
                Collections.sort(list);
                Iterator<TradeHistEntryImpl> it = list.iterator();                
                while (it.hasNext()) {
                    TradeHistEntryImpl tradeHistEntry = it.next();
                    TradeHistoryEntryVo result = VoFactory.createTradeHistoryEntryVo(tradeHistEntry);
                    resultArray.add(result);
                }
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return resultArray.toArray(new TradeHistoryEntryVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getTradeParameter(long)
     */
    @Override
    public TradeParameterVo getTradeParameter(Object tradeId) throws RemoteException {
        TradeParameterVo result = new TradeParameterVo();
        try {
            TradeImpl trade = (TradeImpl) getSession().select(TradeImpl.class, (Serializable) tradeId);
//            TradeImpl trade = (TradeImpl) getSession().select(AbstractFinder.getTradeClassForMandantCode(mandant.getCode()), (Serializable)tradeId);
            result = getTradeParameter(trade);
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return result;
    }

    private TradeParameterVo getTradeParameter(TradeImpl trade) {
        TradeParameterVo result = new TradeParameterVo();
        if (trade != null) {
            if (trade.getInstrument() != null) {
                PriceCheckInstrumentImpl instrument = trade.getInstrument();
                result.setInstrumentIsin(trade.getSourceSystemInstrument());
                result.setInstrumentName(instrument.getInstrument());
                PriceCheckCategoryImpl priceCheckCategory = instrument.getPriceCheckCategory();
                if (priceCheckCategory != null) {
                    result.setCheckCategoryName(priceCheckCategory.getName());
                    result.setPriceTolerancePercent(priceCheckCategory.getPriceTolerancePercent());
                    result.setPriceToleranceAbsolute(priceCheckCategory.getPriceToleranceAbsolute());
                    result.setTimeTolerance(priceCheckCategory.getTimeToleranceMinutes());
                }
            }
            if (trade.getRequests() != null) {
                Iterator<RequestImpl> it = trade.getRequests().iterator();
                if (it.hasNext()) {
                    RequestImpl request =  it.next();
                    result.setTickerUsed(request.getRequestString());
                    AddonImpl addon = request.getAddon();
                    if (addon != null) {
                        result.setAddonName(addon.getComment());
                        result.setPriceToleranceAddon(addon.getPriceTolerancePercent());
                        result.setTimeToleranceAddon(addon.getTimeToleranceMinutes());
                    }
                }
            }
        }
        return result;
    }

    @Override
    public void saveTradeState(TradeHistoryEntryVo tradeHistEntry, byte[] attachmentContent) throws RemoteException {
        saveTradeState(tradeHistEntry, attachmentContent, false);
    }

    private ReportImageImpl saveTradeStateAttachment(TradeHistoryEntryVo tradeHistEntry, byte[] attachmentContent)
        throws RemoteException {
        try {
            if (attachmentContent != null) {
                ReportImageImpl image = (ReportImageImpl) getSession().create(ReportImageImpl.class);
                image.setName(tradeHistEntry.getAttachmentName().replaceAll(" ", ""));
                image.setImage(getSession().getHibernateSession().getLobHelper().createBlob(attachmentContent));
                return image;
            } else if (tradeHistEntry.getAttachmentId() != null) {
                ReportImageImpl image = (ReportImageImpl) getSession().select(ReportImageImpl.class, tradeHistEntry
                        .getAttachmentId());
                return image;
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return null;
    }

    private void saveTradeState(TradeHistoryEntryVo tradeHistEntry, byte[] attachmentContent, boolean useEnclosingTrx) 
         throws RemoteException {
        saveTradeState(tradeHistEntry, saveTradeStateAttachment(tradeHistEntry, attachmentContent), useEnclosingTrx);
    }
    
    /**
     * @see de.westlb.mgb.client.server.Mgb#saveTradeState(TradeHistoryEntryVo,
     *      byte[])
     */
//    private void saveTradeState(TradeHistoryEntryVo tradeHistEntry, byte[] attachmentContent, boolean useEnclosingTrx)
    private void saveTradeState(TradeHistoryEntryVo tradeHistEntry, ReportImageImpl image, boolean useEnclosingTrx)
            throws RemoteException {
        try {
            Transaction t = null;
            if (!useEnclosingTrx) {
                t = getSession().beginTransaction();
            }

            TradeImpl trade = (TradeImpl) getSession().select(TradeImpl.class, tradeHistEntry.getInternalTradeId());
//            TradeImpl trade = (TradeImpl) getSession().select(AbstractFinder.getTradeClassForMandantCode(mandant.getCode()), tradeHistEntry.getInternalTradeId());
            StateIdImpl stateId = new StateIdImpl(mandant, tradeHistEntry.getStateCode());
            if (StateCodeTypeDef.AUTO.equals(tradeHistEntry.getType())) {
                AutomaticStateImpl state = (AutomaticStateImpl) getSession().select(AutomaticStateImpl.class, stateId);
                AutoStateHistEntryImpl autoState = new AutoStateHistEntryImpl();
                autoState.setComment(tradeHistEntry.getComment());
                autoState.setStateTime(new GregorianCalendar());
                autoState.setAutomaticState(state);
                autoState.setTrade(trade);
                getSession().save(autoState);
                trade.setCurrentAutoStateHistEntry(autoState);
            } else if (StateCodeTypeDef.MANUAL.equals(tradeHistEntry.getType())) {
                ManualStateHistEntryImpl manState = new ManualStateHistEntryImpl();
                ManualStateImpl state = (ManualStateImpl) getSession().select(ManualStateImpl.class, stateId);
                manState.setManualState(state);
                manState.setComment(tradeHistEntry.getComment());
                manState.setStateTime(new GregorianCalendar());
                manState.setCreatedByEmployee(user);
//                if (attachmentContent != null) {
//                    ReportImageImpl image = (ReportImageImpl) getSession().create(ReportImageImpl.class);
//                    image.setName(tradeHistEntry.getAttachmentName().replaceAll(" ", ""));
//                    image.setImage(Hibernate.createBlob(attachmentContent));
//                    manState.setReportImage(image);
//                } else if (tradeHistEntry.getAttachmentId() != null) {
//                    ReportImageImpl image = (ReportImageImpl) getSession().select(ReportImageImpl.class, tradeHistEntry
//                            .getAttachmentId());
//                    manState.setReportImage(image);
//                }
                manState.setReportImage(image);
                manState.setTrade(trade);
                getSession().save(manState);
                trade.setCurrentManualStateHistEntry(manState);
            } else if (StateCodeTypeDef.RECLAMATION.equals(tradeHistEntry.getType())) {
                ReclStateHistEntryImpl reclState = new ReclStateHistEntryImpl();
                ReclamationStateImpl state = (ReclamationStateImpl) getSession().select(ReclamationStateImpl.class, stateId);
                reclState.setReclamationState(state);
                reclState.setComment(tradeHistEntry.getComment());
                reclState.setLevel(tradeHistEntry.getReclamationLevel().intValue());
                reclState.setStateTime(new GregorianCalendar());
                reclState.setCreatedByEmployee(user);
//                if (attachmentContent != null) {
//                    ReportImageImpl image = (ReportImageImpl) getSession().create(ReportImageImpl.class);
//                    image.setName(tradeHistEntry.getAttachmentName().replaceAll(" ", ""));
//                    image.setImage(Hibernate.createBlob(attachmentContent));
//                    reclState.setReportImage(image);
//                } else if (tradeHistEntry.getAttachmentId() != null) {
//                    ReportImageImpl image = (ReportImageImpl) getSession().select(ReportImageImpl.class, tradeHistEntry
//                            .getAttachmentId());
//                    reclState.setReportImage(image);
//                }
                reclState.setReportImage(image);
                reclState.setIsClosed(tradeHistEntry.getReclamationClosed() != null && tradeHistEntry.getReclamationClosed().booleanValue());
                reclState.setTrade(trade);
                getSession().save(reclState);
                trade.setCurrentReclStateHistEntry(reclState);
            } else if (StateCodeTypeDef.SAMPLE.equals(tradeHistEntry.getType())) {
                ManualStateHistEntryImpl manState = new ManualStateHistEntryImpl();
                ManualSampleStateImpl state = (ManualSampleStateImpl) getSession().select(ManualSampleStateImpl.class, stateId);
                manState.setManualState(state);
                manState.setComment(tradeHistEntry.getComment());
                manState.setStateTime(new GregorianCalendar());
                manState.setCreatedByEmployee(user);
//                if (attachmentContent != null) {
//                    ReportImageImpl image = (ReportImageImpl) getSession().create(ReportImageImpl.class);
//                    image.setName(tradeHistEntry.getAttachmentName().replaceAll(" ", ""));
//                    image.setImage(Hibernate.createBlob(attachmentContent));
//                    manState.setReportImage(image);
//                } else if (tradeHistEntry.getAttachmentId() != null) {
//                    ReportImageImpl image = (ReportImageImpl) getSession().select(ReportImageImpl.class, tradeHistEntry
//                            .getAttachmentId());
//                    manState.setReportImage(image);
//                }
                manState.setReportImage(image);
                manState.setTrade(trade);
                getSession().save(manState);
                trade.setCurrentManualStateHistEntry(manState);
            } else {
                throw new RemoteException("Invalid StateCodeType: " + tradeHistEntry.getType());
            }
            if (!useEnclosingTrx) {
                t.commit();
                getSession().refresh(trade);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getCurrentReclamationState(Long)
     */
    @Override
    public TradeHistoryEntryVo getCurrentReclamationState(Long tradeId) throws RemoteException {
        try {
            TradeImpl trade = (TradeImpl) getSession().select(TradeImpl.class, tradeId);
//            TradeImpl trade = (TradeImpl) getSession().select(AbstractFinder.getTradeClassForMandantCode(mandant.getCode()), tradeId);
            ReclStateHistEntryImpl reclState = trade.getCurrentReclStateHistEntry();
            if (reclState != null) {
                return VoFactory.createTradeHistoryEntryVo(reclState);
            }
        } catch (Exception e) {
            throwRemoteException(e);
        }
        return null;
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getStateCodes(String)
     */
    @Override
    public StateCodeVo[] getStateCodes(String stateType) throws RemoteException {
        ArrayList<StateCodeVo> resultArray = new ArrayList<StateCodeVo>();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            Collection<? extends StateImpl> col = finder.findStateCodes(mandant, stateType);
            logger.debug("Found " + col.size() + " state codes.");
            Iterator<? extends StateImpl> it = col.iterator();
            while (it.hasNext()) {
                StateImpl state = it.next();
                StateCodeVo result = VoFactory.createStateCodeVo(state);
                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return resultArray.toArray(new StateCodeVo[0]);
    }

    @Override
    public Object getStateCodeMap(String mandantCode,  String stateType) throws RemoteException {
        HashMap<String, String> result = new HashMap<String, String>();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            MandantImpl mandant = null;
            if (mandantCode != null) {
                mandant = new MandantImpl(mandantCode);
            }
            Collection<? extends StateImpl> col = finder.findStateCodes(mandant, stateType, false);
            logger.debug("Found " + col.size() + " state codes.");
            Iterator<? extends StateImpl> it = col.iterator();
            while (it.hasNext()) {
                StateImpl state = it.next();
                result.put(state.getStateCode(), state.getShortDescription());
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return result;
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getTradeHistoryEntryAttachment(Object)
     */
    public TradeHistoryEntryAttachmentVo getTradeHistoryEntryAttachment(Object attachmentId) throws RemoteException {
        TradeHistoryEntryAttachmentVo result = null;
        try {
            ReportImageImpl image = (ReportImageImpl) getSession()
                    .select(ReportImageImpl.class, (Serializable) attachmentId);
            result = new TradeHistoryEntryAttachmentVo();
            result.setAttachmentId(image.getLongId());
            result.setAttachmentName(image.getName());
            Blob blob = image.getImage();
            result.setAttachment(blob.getBytes(1, (int) blob.length()));
        } catch (Exception e) {
            throwRemoteException(e);
        }
        return result;
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#endReadAttachment(Long)
     */
    @Override
    public void endReadAttachment(Long attachmentId) throws RemoteException {
        attachmentBlobMap.remove(attachmentId);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#readAttachment(Long, long, long)
     */
    @Override
    public byte[] readAttachment(Long attachmentId, long startBytePos, long stopBytePos) throws RemoteException {
        try {
            int length = (int) (stopBytePos - startBytePos);
            // the method getBytes(setoff, length) expects a setoff starting at
            // 1!
            return attachmentBlobMap.get(attachmentId).getBytes(startBytePos + 1,length);
        } catch (Exception e) {
            throwRemoteException(e);
        }
        return null;
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#startReadAttachment(Long)
     */
    @Override
    public long startReadAttachment(Long attachmentId) throws RemoteException {
        try {
            ReportImageImpl image = (ReportImageImpl) getSession()
                    .select(ReportImageImpl.class, attachmentId);
            // The following refresh is importent to syncronize the cache with
            // the database.
            // If this isn't done, the cache might return a
            // net.sf.hibernate.lob.BlobImpl which can not be changed to a
            // byte[]
            getSession().refresh(image);
            Blob blob = image.getImage();
            attachmentBlobMap.put(attachmentId, blob);
            return blob.length();
        } catch (Exception e) {
            throwRemoteException(e);
        }
        return 0;
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#closeReclamation(Long, String)
     */
    @Override
    public void closeReclamation(Long tradeId, String closeComment) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            TradeImpl trade = (TradeImpl) getSession().select(TradeImpl.class, tradeId);
//            TradeImpl trade = (TradeImpl) getSession().select(AbstractFinder.getTradeClassForMandantCode(mandant.getCode()), tradeId);
            ReclStateHistEntryImpl reclState = trade.getCurrentReclStateHistEntry();
            if (reclState != null) {
                if (closeComment != null) {
                    reclState.setClosedComment(closeComment.substring(0, Math.min(ReclStateHistEntryImpl.CLOSED_COMMENT_MAX_LENGTH, closeComment.length())));
                }
            	reclState.setIsClosed(true);
            }
            t.commit();
        } catch (Exception e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#findNewTrader()
     */
    @Override
    public TraderSearchResultEntryVo[] findNewTrader() throws RemoteException {
        ArrayList<TraderSearchResultEntryVo> resultArray = new ArrayList<TraderSearchResultEntryVo>();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            Collection<Object[]> col = finder.findNewTraders(createCurrentTradeSearchParam());
            logger.debug("Found " + col.size() + " traders.");
            Iterator<Object[]> it = col.iterator();
            while (it.hasNext()) {
                Object[] oa = it.next();
                TraderImpl trader = (TraderImpl) oa[0];
                String traderName = (String) oa[1];
                TraderSearchResultEntryVo result = new TraderSearchResultEntryVo();
                result.setSourceSystemCode(trader.getSourceSystem().getCode());
                result.setTraderCode(trader.getTraderCode());
                result.setTraderName(traderName);
                result.setId(trader.getLongId());
                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return resultArray.toArray(new TraderSearchResultEntryVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#findEmployees(EmployeeSearchParamsVo)
     */
    @Override
    public EmployeeSearchResultEntryVo[] findEmployees(EmployeeSearchParamsVo searchParam) throws RemoteException {
        HashSet<EmployeeSearchResultEntryVo> resultArray = new HashSet<EmployeeSearchResultEntryVo>();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());

            EmployeeSearchParams param = new EmployeeSearchParams();
            param.setMandant(mandant);
            if (searchParam.getFirstName() != null) {
                param.setFirstName(searchParam.getFirstName().replace('*', '%'));
            }
            if (searchParam.getLastName() != null) {
                param.setLastName(searchParam.getLastName().replace('*', '%'));
            }
            if (searchParam.getTraderCode() != null) {
                param.setTraderCode(searchParam.getTraderCode().replace('*', '%'));
            }

            Collection<EmployeeImpl> col = finder.findEmployees(param);
            Iterator<EmployeeImpl> it = col.iterator();
            while (it.hasNext()) {
                EmployeeImpl employee = it.next();
                resultArray.add(VoFactory.createEmployeeSearchResultEntryVo(employee));
            }
            logger.debug("Found " + resultArray.size() + " employees.");
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return  resultArray.toArray(new EmployeeSearchResultEntryVo[0]);
    }

    @Override
    public EmployeeVo[] findAllEmployees(EmployeeSearchParamsVo searchParam) throws RemoteException {
        ArrayList<EmployeeVo> resultArray = new ArrayList<EmployeeVo>();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());

            EmployeeSearchParams param = new EmployeeSearchParams();
            if (searchParam.getNtId() != null) {
                param.setNtId(searchParam.getNtId());
            } else {
                if (searchParam.getMandantCode() != null) {
                    param.setMandant(new MandantImpl(searchParam.getMandantCode()));
                    param.setHasRoles(false);
                } else {
                    param.setMandant(mandant);
                }
                if (searchParam.getFirstName() != null) {
                    param.setFirstName(searchParam.getFirstName().replace('*', '%'));
                }
                if (searchParam.getLastName() != null) {
                    param.setLastName(searchParam.getLastName().replace('*', '%'));
                }
                if (searchParam.getTraderCode() != null) {
                    param.setTraderCode(searchParam.getTraderCode().replace('*', '%'));
                }
            }

            Collection<EmployeeImpl> col = finder.findEmployees(param);
            Iterator<EmployeeImpl> it = col.iterator();
            while (it.hasNext()) {
                EmployeeImpl employee = it.next();
                resultArray.add(VoFactory.createEmployeeVo(employee));
            }
            logger.debug("Found " + resultArray.size() + " employees.");
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return  resultArray.toArray(new EmployeeVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#assignTraderToEmployee(Long, Long)
     */
    @Override
    public void assignTraderToEmployee(Long employeeId, Long traderId) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            EmployeeImpl employee = (EmployeeImpl) getSession().select(EmployeeImpl.class, employeeId);
            TraderImpl trader = (TraderImpl) getSession().select(TraderImpl.class, traderId);
            if (trader != null && employee != null) {
                employee.addTraderId(trader);
            }
            t.commit();
        } catch (Exception e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getSessionInfo()
     */
    @Override
    public SessionInfoVo getSessionInfo() throws RemoteException {
        SessionInfoVo info = new SessionInfoVo();
        try {
            // Re-read employee, to be able to read of lazy initialised
            // collections
            user = (EmployeeImpl) getSession().select(EmployeeImpl.class, user.getId());

            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            EmployeeVo employeeVo = VoFactory.createEmployeeVo(user);
            info.setEmployee(employeeVo);

            EmployeeSearchParams param = new EmployeeSearchParams();
            param.setNtId(user.getNtId());
            param.setOnlyEnabled(true);
            Collection<MandantImpl> col = finder.findMandants(param);
            logger.debug("Found " + col.size() + " mandants.");
            MandantVo[] mandants = new MandantVo[col.size()];
            Iterator<MandantImpl> it = col.iterator();
            int i = 0;
            while (it.hasNext()) {
                MandantImpl mandant = it.next();
                MandantVo mand = VoFactory.createMandantVo(mandant);
                mandants[i] = mand;
                i++;
            }
            info.setMandants(mandants);

            Collection<SourceSystemImpl> col1 = finder.findSourceSystems(new String[]{user.getMandant().getCode()});
            logger.debug("Found " + col1.size() + " source systems.");
            SourceSystemVo[] sourceSystems = new SourceSystemVo[col1.size()];
            Iterator<SourceSystemImpl> it1 = col1.iterator();
            i = 0;
            while (it1.hasNext()) {
                SourceSystemImpl sourceSystem = it1.next();
                SourceSystemVo source = VoFactory.createSourceSystemVo(sourceSystem);
                sourceSystems[i] = source;
                i++;
            }
            info.setSourceSystems(sourceSystems);

        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return info;
    }

    @Override
    public MandantVo[] findAllMandants() throws RemoteException {
        ArrayList<MandantVo> resultArray = new ArrayList<MandantVo>();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            Collection<MandantImpl> col = finder.findMandants(true);
            logger.debug("Found " + col.size() + " mandants.");
            Iterator<MandantImpl> it = col.iterator();
            while (it.hasNext()) {
                MandantImpl mandant = it.next();
                MandantVo mand = VoFactory.createMandantVo(mandant);
                resultArray.add(mand);
            }            
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return resultArray.toArray(new MandantVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#saveEmployee(EmployeeVo)
     */
    @Override
    public Long saveEmployee(EmployeeVo employee) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            EmployeeImpl newEmployee = null;
            if (employee.getEmployeeId() != null) {
                newEmployee = (EmployeeImpl) getSession().select(EmployeeImpl.class, employee.getEmployeeId());
            } else {
                newEmployee = (EmployeeImpl) getSession().create(EmployeeImpl.class);
            }
            newEmployee.setEmail(employee.getEmail());
            newEmployee.setFirstName(employee.getFirstName());
            newEmployee.setLastName(employee.getLastName());
            newEmployee.setMandant(mandant);
            if (newEmployee.getNtId() != null && newEmployee.getNtId().length() > 0
                    && !newEmployee.getNtId().equals(employee.getNtId())) { throw new RemoteException(
                    "Due to security reason, it is not allowed to change an existing NT-ID. Please contact the user administrator"); }
            // MB 2004/10/05 Save user id always in lower case characters
            // because login would fail either.
            String ntId = employee.getNtId();
            if (ntId != null) {
                ntId = ntId.toLowerCase();
            }
            newEmployee.setNtId(ntId);

            newEmployee.setPhone(employee.getPhone());

            if (employee.isTrader() && !newEmployee.hasTraderRole()) {
                UserRoleImpl userRole = (UserRoleImpl)getSession().create(UserRoleImpl.class);
                userRole.setType(new RoleImpl(TRADER));
                newEmployee.addUserRole(userRole);
            }
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            TraderSearchParams param = new TraderSearchParams();
            param.setEmployee(newEmployee);
            newEmployee.setTraders(new HashSet<TraderImpl>(finder.findTrader(param)));

            if (newEmployee.getRoles() != null) {
                Iterator<UserRoleImpl> it = newEmployee.getRoles().iterator();
                while (it.hasNext()) {
                    UserRoleImpl role = it.next();
                    if (!employee.isTrader() && RoleDef.TRADER.equals(role.getType().getCode())) {
                        it.remove();
                        getSession().delete(UserRoleImpl.class, role.getLongId());
                    }
                }
            }
            t.commit();
            return newEmployee.getLongId();
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return null;
    }

    @Override
    public Long saveEmployeeAndRoles(EmployeeVo employee) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            EmployeeImpl newEmployee = null;
            if (employee.getEmployeeId() != null) {
                try {
                    newEmployee = (EmployeeImpl) getSession().select(EmployeeImpl.class, employee.getEmployeeId());
                } catch (PersistenceException pe) {
                    logger.error(pe.getMessage(), pe);
                    newEmployee = (EmployeeImpl) getSession().create(EmployeeImpl.class);
                }
            } else {
                newEmployee = (EmployeeImpl) getSession().create(EmployeeImpl.class);
            }
            newEmployee.setEmail(employee.getEmail());
            newEmployee.setFirstName(employee.getFirstName());
            newEmployee.setLastName(employee.getLastName());
            if (employee.getMandantCode() != null && employee.getMandantCode().length() > 0) {
                newEmployee.setMandant(new MandantImpl(employee.getMandantCode()));
            } else {
                newEmployee.setMandant(mandant);
            }
            newEmployee.setNtId(employee.getNtId());
            newEmployee.setPhone(employee.getPhone());
            if (employee.isController() && !newEmployee.hasControllerRole()) {
                UserRoleImpl userRole = (UserRoleImpl)getSession().create(UserRoleImpl.class);
                userRole.setType(new RoleImpl(CONTROLLER));
                newEmployee.addUserRole(userRole);
            }
            if (employee.isTrader() && !newEmployee.hasTraderRole()) {
                UserRoleImpl userRole = (UserRoleImpl)getSession().create(UserRoleImpl.class);
                userRole.setType(new RoleImpl(TRADER));
                newEmployee.addUserRole(userRole);
            }
            if (employee.isAdmin() && !newEmployee.hasAdminRole()) {
                UserRoleImpl userRole = (UserRoleImpl)getSession().create(UserRoleImpl.class);
                userRole.setType(new RoleImpl(ADMIN));
                newEmployee.addUserRole(userRole);
            }
            if (employee.isSystemAdmin() && !newEmployee.hasSystemAdminRole()) {
                UserRoleImpl userRole = (UserRoleImpl)getSession().create(UserRoleImpl.class);
                userRole.setType(new RoleImpl(SYSTEM_ADMIN));
                newEmployee.addUserRole(userRole);
            }
            if (employee.isUserMaintainAdmin() && !newEmployee.hasUserMaintainAdminRole()) {
                UserRoleImpl userRole = (UserRoleImpl)getSession().create(UserRoleImpl.class);
                userRole.setType(new RoleImpl(USER_MAINTAIN_ADMIN));
                newEmployee.addUserRole(userRole);
            }
            if (employee.isSparkassenAdmin() && !newEmployee.hasSparkassenAdminRole()) {
                UserRoleImpl userRole = (UserRoleImpl)getSession().create(UserRoleImpl.class);
                userRole.setType(new RoleImpl(SPK_ADMIN));
                newEmployee.addUserRole(userRole);
            }
            if (employee.isReadOnly() && !newEmployee.hasReadOnlyRole()) {
                UserRoleImpl userRole = (UserRoleImpl)getSession().create(UserRoleImpl.class);
                userRole.setType(new RoleImpl(READ_ONLY));
                newEmployee.addUserRole(userRole);
            }
            if (employee.isReporter() && !newEmployee.hasReporterRole()) {
                UserRoleImpl userRole = (UserRoleImpl)getSession().create(UserRoleImpl.class);
                userRole.setType(new RoleImpl(REPORTER));
                newEmployee.addUserRole(userRole);
            }

            newEmployee.setReport(employee.getReport());
            
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            TraderSearchParams param = new TraderSearchParams();
            param.setEmployee(newEmployee);
            newEmployee.setTraders(new HashSet<TraderImpl>(finder.findTrader(param)));
            if (newEmployee.getRoles() != null) {
                Iterator<UserRoleImpl> it = newEmployee.getRoles().iterator();
                while (it.hasNext()) {
                    UserRoleImpl role = it.next();
                    if (!employee.isController() && RoleDef.CONTROLLER.equals(role.getType().getCode())) {
                        it.remove();
                        getSession().delete(UserRoleImpl.class, role.getLongId());
                    }
                    if (!employee.isTrader() && RoleDef.TRADER.equals(role.getType().getCode())) {
                        it.remove();
                        getSession().delete(UserRoleImpl.class, role.getLongId());
                    }
                    if (!employee.isAdmin() && RoleDef.ADMIN.equals(role.getType().getCode())) {
                        it.remove();
                        getSession().delete(UserRoleImpl.class, role.getLongId());
                    }
                    if (!employee.isSystemAdmin() && RoleDef.SYSTEM_ADMIN.equals(role.getType().getCode())) {
                        it.remove();
                        getSession().delete(UserRoleImpl.class, role.getLongId());
                    }
                    if (!employee.isSparkassenAdmin() && RoleDef.SPK_ADMIN.equals(role.getType().getCode())) {
                        it.remove();
                        getSession().delete(UserRoleImpl.class, role.getLongId());
                    }
                    if (!employee.isUserMaintainAdmin() && RoleDef.USER_MAINTAIN_ADMIN.equals(role.getType().getCode())) {
                        it.remove();
                        getSession().delete(UserRoleImpl.class, role.getLongId());
                    }
                    if (!employee.isReporter() && RoleDef.REPORTER.equals(role.getType().getCode())) {
                        it.remove();
                        getSession().delete(UserRoleImpl.class, role.getLongId());
                    }
                    if (!employee.isReadOnly() && RoleDef.READ_ONLY.equals(role.getType().getCode())) {
                        it.remove();
                        getSession().delete(UserRoleImpl.class, role.getLongId());
                    }
                }
            }
            t.commit();

            return newEmployee.getLongId();
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return null;
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getPrice(RequestVo)
     */
    @Override
    public PriceVo getPrice(RequestVo request) throws RemoteException {
        DbPriceProvider provider = new DbPriceProvider(getSession());
        return provider.getPrice(request);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#savePrice(RequestVo, PriceVo)
     */
    @Override
    public void savePrice(RequestVo request, PriceVo price) throws RemoteException {
    	try {
    		DbPriceProvider provider = new DbPriceProvider(getSession());
    		logger.debug("Save price " + price.getMidPrice() + " at "+price.getPriceDate().getTime());
    		provider.savePrice(request, price);
    	} catch (RemoteException re) {
    		logger.error(re);
    		DbPriceProvider provider = new DbPriceProvider(getSession());
    		logger.debug("Save price " + price.getMidPrice() + " at "+price.getPriceDate().getTime());
    		provider.savePrice(request, price);    		
    	}
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#savePrice(RequestVo, PriceVo)
     */
    @Override
    public void savePrices(RequestVo[] requests, PriceVo[] prices) throws RemoteException {
    	try {
    		DbPriceProvider provider = new DbPriceProvider(getSession());
    		provider.savePrices(requests, prices);
       	} catch (RemoteException re) {
    		logger.error(re);
    		DbPriceProvider provider = new DbPriceProvider(getSession());
    		provider.savePrices(requests, prices);
    	}
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getEmployee(Long)
     */
    @Override
    public EmployeeVo getEmployee(Long employeeId) throws RemoteException {
        EmployeeVo result = null;
        try {
            EmployeeImpl employee = (EmployeeImpl) getSession().select(EmployeeImpl.class, employeeId);
            result = VoFactory.createEmployeeVo(employee);
            TraderVo[] traders = new TraderVo[employee.getTraders().size()];
            int i = 0;
            Iterator<TraderImpl> it = employee.getTraders().iterator();
            while (it.hasNext()) {
                TraderImpl element = it.next();
                TraderVo trader = new TraderVo();
                trader.setId(element.getLongId());
                trader.setSourceSystemCode(element.getSourceSystem().getCode());
                trader.setTraderCode(element.getTraderCode());
                trader.setTraderName(employee.getFullName());
                traders[i++] = trader;
            }
            result.setTraderIds(traders);
        } catch (Exception e) {
            throwRemoteException(e);
        }
        return result;
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getUnsolvedRequests()
     */
    @Override
    public RequestVo[] getUnsolvedRequests(String requestClassName) throws RemoteException {
        ArrayList<RequestVo> resultArray = new ArrayList<RequestVo>();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            RequestSearchParams param = new RequestSearchParams();
            param.setMandant(mandant);
            param.setJobs(selectedJobs);
            try {
            	param.setClassName(Class.forName(requestClassName));
            } catch(ClassNotFoundException cnfe) {
            	logger.error(cnfe);
            }
            param.setStateNotOkOnly(true);

            // Currently requesting a huge data selection causes a lot of
            // problems.
            // Proxy timeout and out of memory. So the maximum number of trades
            // can
            // be limited by a system parameter. The automatic check has to be
            // started again then.
            int maxResults;
            String maxResultStr = finder.findMgbConfigurationValue(mandant, MgbConfigurationDef.MAX_AUTOCHECK_TRADES);
            if (maxResultStr != null) {
                maxResults = Integer.parseInt(maxResultStr);
            } else {
                maxResults = -1;
            }
            param.setMaxResults(maxResults);

            Collection<RequestImpl> col = finder.findRequests(param);
            logger.debug("Found " + col.size() + " request.");
            Iterator<RequestImpl> it = col.iterator();
            while (it.hasNext()) {
                RequestImpl request = it.next();
                RequestVo result = VoFactory.createRequestVo(request);
                result.setJobId(request.getTrade().getJob().getLongId());
                if (request.getTrade().getTradeDate() != null) {
                	Calendar tradeDate = (Calendar)request.getTrade().getTradeDate().clone();
    				tradeDate.setTimeZone(TimeZone.getDefault());
                	result.setPriceDate(tradeDate);
                }
                result.setTradePrice(request.getTrade().getPrice());
                result.setIsoCurrency(request.getTrade().getCurrency());
                result.setPriceSource(request.getMarketDataSource());
                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        logger.debug("Found " + resultArray.size() + " unsolved request.");
        return resultArray.toArray(new RequestVo[0]);
    }

    @Override
    public RequestVo[] getUnvalidatedRequests(String requestClassName) throws RemoteException {
        ArrayList<RequestVo> resultArray = new ArrayList<RequestVo>();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            RequestSearchParams param = new RequestSearchParams();
            param.setMandant(mandant);
            try {
            	param.setClassName(Class.forName(requestClassName));
            } catch(ClassNotFoundException cnfe) {
            	logger.error(cnfe);
            }
            param.setJobs(selectedJobs);
            String[] states = { MarketDataRequestStateDef.OK_PRICE_UNVALIDATED, MarketDataRequestStateDef.ERROR};
            for (int i = 0; i < states.length; i++) {
                param.setState(states[i]);

                Collection<RequestImpl> col = finder.findRequests(param);
                logger.debug("Found " + col.size() + " request with state "+states[i]);
                Iterator<RequestImpl> it = col.iterator();
                while (it.hasNext()) {
                    RequestImpl request = it.next();
                    RequestVo result = VoFactory.createRequestVo(request);
                    result.setJobId(request.getTrade().getJob().getLongId());
                    result.setPriceDate(request.getTrade().getTradeDate());
                    result.setTradePrice(request.getTrade().getPrice());
                    result.setIsoCurrency(request.getTrade().getCurrency());
                    result.setPriceSource(request.getMarketDataSource());
                    resultArray.add(result);
                }
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        logger.debug("Found " + resultArray.size() + " unvalidated request.");
        return resultArray.toArray(new RequestVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#assignPriceCheckCategoryToInstrument(Long,
     *      Long)
     */
    @Override
    public void assignPriceCheckCategoryToInstrument(Long priceCheckCategoryId, Long instrumentId)
            throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            PriceCheckCategoryImpl priceCheckCategory = (PriceCheckCategoryImpl) getSession().select(
                    PriceCheckCategoryImpl.class, priceCheckCategoryId);
            PriceCheckInstrumentImpl instrument = (PriceCheckInstrumentImpl) getSession().select(
                    PriceCheckInstrumentImpl.class, instrumentId);
            if (priceCheckCategory != null && instrument != null) {
                instrument.setPriceCheckCategory(priceCheckCategory);
            }

            if (performFourEyeSave) {
                DualControlJobProcessorImpl processor = new DualControlJobProcessorImpl();
                t.rollback();
                closeSession();
                session = StoreSingleton.getUniqueInstance().openSession(mandant, user.getNtId());
                if (priceCheckCategory.getId() != null) {
                    processor.update(session, instrument, user);
                } else {
                    t.commit();
                }
            }
        } catch (Exception e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#deleteEmployee(Long)
     */
    @Override
    public void deleteEmployee(Long employeeId) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            EmployeeImpl employee = (EmployeeImpl) getSession().select(EmployeeImpl.class, employeeId);
            getSession().deleteAll(employee.getRoles());
            getSession().delete(EmployeeImpl.class, employeeId);
            t.commit();
        } catch (Exception e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#deleteTrader(Long)
     */
    @Override
    public void deleteTrader(Long traderId) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            TraderImpl trader = (TraderImpl) getSession().select(TraderImpl.class, traderId);
            trader.setEmployee(null);
            t.commit();
        } catch (Exception e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#findAllPriceCheckCategories()
     */
    @Override
    public PriceCheckCategoryVo[] findAllPriceCheckCategories() throws RemoteException {
        ArrayList<PriceCheckCategoryVo> resultArray = new ArrayList<PriceCheckCategoryVo>();
        try {
        	DualControlJobFinderImpl finder = new DualControlJobFinderImpl(getSession());
            PriceCheckCategorySearchParams param = new PriceCheckCategorySearchParams();
            param.setOnlyEnabled(true);
            param.setMandant(mandant);
            Collection<PriceCheckCategoryImpl> col = finder.findPriceCheckCategories(param);
            logger.debug("Found " + col.size() + " price check categories.");
            Iterator<PriceCheckCategoryImpl> it = col.iterator();
            while (it.hasNext()) {
                PriceCheckCategoryImpl priceCheckCategory = it.next();
                PriceCheckCategoryVo result = VoFactory.createPriceCheckCategoryVo(priceCheckCategory);
                Iterator<EmployeeImpl> iter = finder.findPendingDualControlJobsEntryRequester(priceCheckCategory).iterator();
                while (iter.hasNext()) {
                    result.setChangePending(true);
                    EmployeeImpl requester = iter.next();
                    if (requester != null) {
                        result.setChangeRequestedByName(requester.getFullName());
                    }
                }

                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return resultArray.toArray(new PriceCheckCategoryVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#findInstruments(InstrumentSearchParamsVo)
     */
    @Override
    public InstrumentSearchResultEntryVo[] findInstruments(InstrumentSearchParamsVo searchParams)
            throws RemoteException {
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            InstrumentSearchParams params = new InstrumentSearchParams();
            params.setOnlyEnabled(true);
            params.setMandant(mandant);
            params.setOnlyPriceCheckInstruments(true);
            if (searchParams.getIsin() != null) {
                params.setInstrument(searchParams.getIsin().replace('*', '%'));
            }
            Collection<InstrumentSearchResultEntryVo> result = finder.findInstrumentVos(params);
            return result.toArray(new InstrumentSearchResultEntryVo[0]);
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return new InstrumentSearchResultEntryVo[]{};
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#findNewInstruments()
     */
    @Override
    public InstrumentSearchResultEntryVo[] findNewInstruments() throws RemoteException {
        ArrayList<InstrumentSearchResultEntryVo> resultArray = new ArrayList<InstrumentSearchResultEntryVo>();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            Collection<PriceCheckInstrumentImpl> col = finder.findNewInstruments(createCurrentTradeSearchParam());
            logger.debug("Found " + col.size() + " instruments.");
            Iterator<PriceCheckInstrumentImpl> it = col.iterator();
            while (it.hasNext()) {
                PriceCheckInstrumentImpl instrument = it.next();
                InstrumentSearchResultEntryVo result = new InstrumentSearchResultEntryVo();
                result.setId(instrument.getLongId());
                result.setIsin(instrument.getInstrument());
                result.setInstrumentName(instrument.getInstrumentName());
                if (instrument.getPriceCheckCategory() != null) {
                    result.setPriceCheckCategoryId(instrument.getPriceCheckCategory().getLongId());
                }
                result.setChangePending(false);
                Iterator<EmployeeImpl> iter = (new DualControlJobFinderImpl(getSession())).findPendingDualControlJobsEntryRequester(instrument).iterator();
                while (iter.hasNext()) {
                    EmployeeImpl requester = iter.next();
                    if (requester != null) {
                        result.setChangeRequestedByName(requester.getFullName());
                    }
                    result.setChangePending(true);
                }
                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return  resultArray.toArray(new InstrumentSearchResultEntryVo[0]);
    }
    
    /**
     * @see de.westlb.mgb.client.server.Mgb#findNewLocations()
     */
    @Override
    public TradeSearchResultEntryVo[] findNewLocations() throws RemoteException {
    	ArrayList<TradeSearchResultEntryVo> resultArray = new ArrayList<TradeSearchResultEntryVo>();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            Collection<TradeImpl> col = finder.findNewLocations(createCurrentTradeSearchParam());
            logger.debug("Found " + col.size() + " trades.");
            Iterator<TradeImpl> it = col.iterator();
            while (it.hasNext()) {
                TradeImpl trade = it.next();
                TradeSearchResultEntryVo result = VoFactory.createTradeSearchResultEntryVo(trade);
                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return  resultArray.toArray(new TradeSearchResultEntryVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getInstrument(Long)
     */
    @Override
    public InstrumentVo getInstrument(Long instrumentId) throws RemoteException {
        InstrumentVo result = new InstrumentVo();
        try {
            InstrumentImpl instrument = (InstrumentImpl) getSession().select(InstrumentImpl.class,
                     instrumentId);
            result.setId(instrument.getLongId());
            result.setIsin(instrument.getInstrument());
            result.setInstrumentName(instrument.getInstrumentName());
            if (instrument instanceof PriceCheckInstrumentImpl
                    && ((PriceCheckInstrumentImpl) instrument).getPriceCheckCategory() != null) {
                result.setPriceCheckCategoryId(((PriceCheckInstrumentImpl) instrument).getPriceCheckCategory()
                        .getLongId());
            }
        } catch (Exception e) {
            throwRemoteException(e);
        }
        return result;
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#findAllDualControlJobs()
     */
    @Override
    public DualControlJobSearchResultEntryVo[] findDualControlJobs(DualControlJobSearchParamsVo searchParams)
            throws RemoteException {
        ArrayList<DualControlJobSearchResultEntryVo> resultArray = new ArrayList<DualControlJobSearchResultEntryVo>();
        try {
        	DualControlJobFinderImpl finder = new DualControlJobFinderImpl(getSession());
            DualControlJobSearchParams param = new DualControlJobSearchParams();
            param.setMandant(mandant);
            if (searchParams != null) {
                //				param.setExcludedRequesterNtId(searchParams.getExcludedRequesterNtId());
                param.setShowCommitedJobs(searchParams.isShowCommitedJobs());
            }
            Collection<DualControlJobImpl> col = finder.findDualControlJobsEntries(param);
            logger.debug("Found " + col.size() + " dual control jobs.");
            Iterator<DualControlJobImpl> it = col.iterator();
            while (it.hasNext()) {
                DualControlJobImpl dualControlJob = it.next();
                DualControlJobSearchResultEntryVo result = new DualControlJobSearchResultEntryVo();
                result.setEntityType(dualControlJob.getEntityTypeName());
                if (DualControlJobOperationDef.CREATE.equals(dualControlJob.getOperation())) {
                    result.setEntityName("a new entity");
                }
                if (dualControlJob.getEntity() != null) {
                    result.setEntityName(dualControlJob.getEntity().toString());
                }
                result.setId(dualControlJob.getLongId());
                result.setOperation(dualControlJob.getOperation());
                result.setRequestedByName(dualControlJob.getRequestedBy().getFullName());
                result.setRequestedByNtId(dualControlJob.getRequestedBy().getNtId());
                result.setRequestedDate(dualControlJob.getCreationDate());
                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return  resultArray.toArray(new DualControlJobSearchResultEntryVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getDualControlJobVo()
     */
    @Override
    public DualControlJobVo getDualControlJobVo(Long dualControlJobId) throws RemoteException {
        DualControlJobVo result = new DualControlJobVo();
        try {
            DualControlJobImpl dualControlJob = (DualControlJobImpl) getSession().select(DualControlJobImpl.class,
                     dualControlJobId);
            result.setId(dualControlJob.getLongId());
            result.setEntityType(dualControlJob.getEntityTypeName());
            result.setOperation(dualControlJob.getOperation());
            result.setRequestedByName(dualControlJob.getRequestedBy().getFullName());
            result.setRequestedByNtId(dualControlJob.getRequestedBy().getNtId());
            result.setRequestedDate(dualControlJob.getCreationDate());
            Object newObject = null;
            Object oldObject = dualControlJob.getEntity();
            Method[] methods = null;
            ObjectComparator objectComparator = new ObjectComparator();
            objectComparator.setFilter(DualControlJobEntityAccessorFilter.getInstance());

            if (DualControlJobOperationDef.DELETE.equals(dualControlJob.getOperation())) {
                methods = objectComparator.getAccessorList(oldObject);
            } else if (DualControlJobOperationDef.CREATE.equals(dualControlJob.getOperation())) {
                newObject = DualControlJobProcessorImpl.streamToObject(dualControlJob.getPersistentObject()
                        .getBinaryStream());
                methods = objectComparator.getAccessorList(newObject);
            } else {
                newObject = DualControlJobProcessorImpl.streamToObject(dualControlJob.getPersistentObject()
                        .getBinaryStream());
                methods = objectComparator.getChangedFieldsAccessorList(newObject, oldObject);
            }

            String[] names = new String[methods.length];
            String[] newValues = new String[methods.length];
            String[] oldValues = new String[methods.length];
            for (int i = 0; i < methods.length; i++) {
                names[i] = methods[i].getName();
                if (!DualControlJobOperationDef.DELETE.equals(dualControlJob.getOperation())) {
                    newValues[i] = valueAsString(methods[i].invoke(newObject));
                }
                if (!DualControlJobOperationDef.CREATE.equals(dualControlJob.getOperation())) {
                    oldValues[i] = valueAsString(methods[i].invoke(oldObject));
                }
            }
            result.setNames(names);
            result.setNewValues(newValues);
            result.setOldValues(oldValues);
        } catch (PersistenceException e) {
            throwRemoteException(e);
        } catch (IllegalArgumentException e) {
            throwRemoteException(e);
        } catch (IllegalAccessException e) {
            throwRemoteException(e);
        } catch (InvocationTargetException e) {
            throwRemoteException(e);
        } catch (IOException e) {
            throwRemoteException(e);
        } catch (ClassNotFoundException e) {
            throwRemoteException(e);
        } catch (SQLException e) {
            throwRemoteException(e);
        }
        return result;
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#saveRequest(RequestVo)
     */
    @Override
    public void updateRequest(RequestVo request) throws RemoteException {
        try {
            if (request != null) {
            	Session sess = getSession();
                Transaction t = sess.beginTransaction();
                RequestImpl newRequest = null;
                if (request.getRequestId() != null && request.getRequestId().longValue() > 0) {
                    newRequest = (RequestImpl) sess.select(RequestImpl.class, request.getRequestId());
                    if (request.getRequestString() != null) {
                    	newRequest.setRequestString(request.getRequestString());
                    }
                    // the following lines store the sources of the last request for the instrument
                    // (this feedback is not required any more)
//                    if (request.getRequestSources() != null && request.getRequestSources().length > 0) {
//                    	if (newRequest.getTrade() instanceof SummitBondImpl) {
//                    		InstrumentImpl instr = ((SummitBondImpl) newRequest.getTrade()).getBloombergInstrument();
//                    		instr.setBloombergRequestSourceArray(request.getRequestSources());
//                    		sess.update(instr);
//                    	}
//                    }

                    if (request.getRequestDate() != null) {
                	//	ensure that the calendar is set to the local timezone, to save a consistant date in the DB
                    	Calendar cal = (Calendar)request.getRequestDate().clone();
                    	cal.setTimeZone(TimeZone.getDefault());
                    	newRequest.setRequestDate(cal);
                    }
                    if (request.getRequestState() != null) {
                    	newRequest.setRequestState(request.getRequestState().substring(0,
                            Math.min(request.getRequestState().length(), RequestImpl.REQUEST_STATE_MAX_LENGTH)));
                    }
                } else {
                	logger.warn("Cannot update unknown request");
                }
                t.commit();
            }
        } catch (Exception e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#updateRequests(RequestVo[])
     */
    @Override
    public void updateRequests(RequestVo[] requests) throws RemoteException {
        for (int i = 0; i < requests.length; i++) {
            updateRequest(requests[i]);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#confirmDualControlJobs(Long[])
     */
    @Override
    public void confirmDualControlJobs(Long[] ids) throws RemoteException {
        try {
            DualControlJobProcessorImpl dualControlJobProcessor = new DualControlJobProcessorImpl();
            for (int i = 0; i < ids.length; i++) {
                DualControlJobImpl dualJob = (DualControlJobImpl) getSession().select(DualControlJobImpl.class, ids[i]);
                dualControlJobProcessor.commit(getSession(), dualJob, user);
            }
        } catch (Exception e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#deleteDualControlJobs(Long[])
     */
    @Override
    public void deleteDualControlJobs(Long[] ids) throws RemoteException {
        try {
            for (int i = 0; i < ids.length; i++) {
                Transaction t = getSession().beginTransaction();
                getSession().delete(DualControlJobImpl.class, ids[i]);
                t.commit();
            }
        } catch (Exception e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#login(String, String)
     */
    @Override
    public boolean adminLogin(String ntId, String password) throws RemoteException {
        login(ntId, null, null);
        return password != null;
    }

    /**
     * @deprecated
     */
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes"})
    public AutoCheckVo[] getAutoCheckTrades() throws RemoteException {
        ArrayList resultArray = new ArrayList();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            TradeSearchParams param = createCurrentTradeSearchParam();
            param.setIsMarketDataRequestRequired(Boolean.TRUE);

            // Currently requesting a huge data selection causes a lot of
            // problems.
            // Proxy timeout and out of memory. So the maximum number of trades
            // can
            // be limited by a system parameter. The automatic check has to be
            // started again then.
            int maxResults;
            String maxResultStr = finder.findMgbConfigurationValue(mandant, MgbConfigurationDef.MAX_AUTOCHECK_TRADES);
            if (maxResultStr != null) {
                maxResults = Integer.parseInt(maxResultStr);
            } else {
                maxResults = -1;
            }
            param.setMaxResults(maxResults);

            Collection col = finder.findTrades(param);
            logger.debug("Found " + col.size() + " auto checks.");

            Iterator it = col.iterator();
            while (it.hasNext()) {
                TradeImpl trade = (TradeImpl) it.next();
                AutoCheckVo result = new AutoCheckVo();
                result.setTradeId(trade.getLongId());
                Collection reuqestCol = trade.getBloombergRequests();
                result.setBloombergPriceToleranceDeviationFactor(1.0d);
                RequestVo[] requests = new RequestVo[reuqestCol.size()];
                PriceVo[] prices = new PriceVo[reuqestCol.size()];
                Double[] priceTolerancePercents = new Double[reuqestCol.size()];
                Double[] priceToleranceAbsolutes = new Double[reuqestCol.size()];
                Integer[] timeToleranceMinutes = new Integer[reuqestCol.size()];
                int i = 0;
                Iterator iter = reuqestCol.iterator();
                while (iter.hasNext()) {
                    RequestImpl requestElement = (RequestImpl) iter.next();
                    RequestVo request = new RequestVo();
                    request.setJobId(trade.getJob().getLongId());
                    request.setPriceDate(trade.getTradeDate());
                    request.setPriceSource(requestElement.getMarketDataSource());

                    request.setRequestId(requestElement.getLongId());
                    request.setRequestString(requestElement.getRequestString());
                    request.setRequestType(requestElement.getRequestType());
                    request.setRequestState(requestElement.getRequestState());
                    request.setRequestDate(requestElement.getRequestDate());
                    request.setTradePrice(trade.getPrice());
                    request.setIsoCurrency(requestElement.getTrade().getCurrency());

                    requests[i] = request;
                    PriceCheckCategoryImpl priceCheckCategory = requestElement.getPriceCheckCategory();
                    if (priceCheckCategory != null) {
                        priceTolerancePercents[i] = Double.valueOf(priceCheckCategory.getPriceTolerancePercent());
                        priceToleranceAbsolutes[i] = Double.valueOf(priceCheckCategory.getPriceToleranceAbsolute());
                        timeToleranceMinutes[i] = Integer.valueOf(priceCheckCategory.getTimeToleranceMinutes());
                    }
                    AddonImpl addon = requestElement.getAddon();
                    if (addon != null) {
                        priceTolerancePercents[i] = Double.valueOf(addon.getPriceTolerancePercent() + priceTolerancePercents[i].doubleValue());
                        timeToleranceMinutes[i] = Integer.valueOf(addon.getTimeToleranceMinutes() + timeToleranceMinutes[i].intValue());
                    }
                    PriceImpl price = requestElement.getPriceResult();
                    if (price != null) {
                    	PriceVo pricevo = VoFactory.createPriceVo(price, requestElement.getLongId());
                        prices[i] = pricevo;
                    }
                    i++;
                }
                result.setRequests(requests);
                result.setPrices(prices);
                result.setPriceTolerancePercent(priceTolerancePercents);
                result.setPriceToleranceAbsolute(priceToleranceAbsolutes);
                result.setTimeToleranceMinutes(timeToleranceMinutes);
                result.setSourceSystemCode(trade.getSourceSystem().getCode());
                result.setBloombergPriceToleranceDeviationFactor(1.0f);
                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return (AutoCheckVo[]) resultArray.toArray(new AutoCheckVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getStateRules(SourceSystemVo)
     */
    @Override
    public StateRulesVo[] getStateRules() throws RemoteException {
        ArrayList<StateRulesVo> resultArray = new ArrayList<StateRulesVo>();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            Collection<StateRulesImpl> col = finder.findStateRules(mandant, StateCodeTypeDef.SECOND_STAGE);
            logger.debug("Found " + col.size() + " state rules.");
            Collection<ConditionImpl> condCol = finder.findStateRuleConditions(null);
            Iterator<StateRulesImpl> it = col.iterator();
            while (it.hasNext()) {
                StateRulesImpl rule = it.next();
                StateRulesVo result = new StateRulesVo();
                result.setComment(rule.getComment());
                result.setPriority(rule.getPriority());
                result.setSourceSystemCode(rule.getSourceSystem().getCode());
                StateCodeVo state = new StateCodeVo();
                state.setFinalState(Boolean.valueOf(rule.getFinalState().getFinalState()));
                state.setMarketDataRequestType(rule.getFinalState().getMarketDataRequestType());
                state.setLongDescription(rule.getFinalState().getLongDescription());
                state.setMandantCode(rule.getFinalState().getMandant().getCode());
                state.setShortDescription(rule.getFinalState().getShortDescription());
                state.setStateCode(rule.getFinalState().getStateCode());
                result.setConditionName(rule.getConditionName());
                result.setState(state);
                Iterator<ConditionImpl> iter = condCol.iterator();
                while (iter.hasNext()) {
                    ConditionImpl condition = iter.next();
                    if (rule.getConditionName().equals(condition.getConditionName())
                            && rule.getSourceSystem().equals(condition.getSourceSystem())) {
                        result.setConditionEvaluator(condition.getConditionEvaluator());
                        break;
                    }
                }
                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return resultArray.toArray(new StateRulesVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#findEmployeesByReclamationRequired()
     */
    @Override
    public EmployeeSearchResultEntryVo[] findEmployeesByReclamationRequired() throws RemoteException {
        ArrayList<EmployeeSearchResultEntryVo> resultArray = new ArrayList<EmployeeSearchResultEntryVo>();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            TradeSearchParams param;
            ArrayList<EmployeeImpl> employees = new ArrayList<EmployeeImpl>();
 
            // Collect all trades where reclamation is required without
            // duplicates
            param = createCurrentTradeSearchParam();
            param.setIsReclamationRequiredButNotHandled(Boolean.TRUE);
            Collection<TradeImpl> col = finder.findTrades(param);
            logger.debug("Found " + col.size() + " trades with open reclamation.");

            Iterator<TradeImpl> it = col.iterator();
            while (it.hasNext()) {
                TradeImpl trade = it.next();
                if (trade.getTrader() != null && trade.getTrader().getEmployee() != null) {
                    EmployeeImpl employee = trade.getTrader().getEmployee();
                    if (employees.contains(employee)) {
                        continue;
                    }
                    employees.add(employee);
                }
            }

            Iterator<EmployeeImpl> it1 = employees.iterator();
            // Build resultlist
            while (it1.hasNext()) {
                EmployeeImpl employee = it1.next();
                resultArray.add(VoFactory.createEmployeeSearchResultEntryVo(employee));
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        logger.debug("Found " + resultArray.size() + " employees as traders which require reclamation.");
        return resultArray.toArray(new EmployeeSearchResultEntryVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#findTradesLateReclReqByEmployee(Long)
     */
    @Override
    public TradeSearchResultEntryVo[] findTradesLateReclReqByEmployee(Long employeeId) throws RemoteException {
        ArrayList<TradeSearchResultEntryVo> resultArray = new ArrayList<TradeSearchResultEntryVo>();
        try {
            TradeSearchParams param = createCurrentTradeSearchParam();
            param.setIsLateEntry(Boolean.TRUE);
            EmployeeImpl emplyoee = (EmployeeImpl) getSession().select(EmployeeImpl.class, employeeId);
            param.setEmployee(emplyoee);
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            Collection<TradeImpl> col = finder.findTrades(param);
            logger.debug("Found " + col.size() + " trades with late night entries.");
            Iterator<TradeImpl> it = col.iterator();
            while (it.hasNext()) {
                TradeImpl trade = it.next();
                TradeSearchResultEntryVo result = VoFactory.createTradeSearchResultEntryVo(trade);
                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return resultArray.toArray(new TradeSearchResultEntryVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#findTradesMiscReclReqByEmployee(Long)
     */
    @Override
    public TradeSearchResultEntryVo[] findTradesMiscReclReqByEmployee(Long employeeId) throws RemoteException {
        ArrayList<TradeSearchResultEntryVo> resultArray = new ArrayList<TradeSearchResultEntryVo>();
        try {
            TradeSearchParams param = createCurrentTradeSearchParam();
            param.setIsReclamationRequiredButNotHandled(Boolean.TRUE);
            EmployeeImpl emplyoee = (EmployeeImpl) getSession().select(EmployeeImpl.class, employeeId);
            param.setEmployee(emplyoee);
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            Collection<TradeImpl> col = finder.findTrades(param);
            logger.debug("Found " + col.size() + " trades where reclamation required.");
            Iterator<TradeImpl> it = col.iterator();
            while (it.hasNext()) {
                TradeImpl trade = it.next();
                TradeSearchResultEntryVo result = VoFactory.createTradeSearchResultEntryVo(trade);
                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return resultArray.toArray(new TradeSearchResultEntryVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getTradeState(Object)
     */
    @Override
    public TradeHistoryEntryVo getTradeState(Object tradeId) throws RemoteException {
        TradeHistoryEntryVo result = null;
        try {
            TradeImpl trade = (TradeImpl) getSession().select(TradeImpl.class, (Serializable) tradeId);
//            TradeImpl trade = (TradeImpl) getSession().select(AbstractFinder.getTradeClassForMandantCode(mandant.getCode()), (Serializable)tradeId);
            if (trade != null && trade.getCurrentManualStateHistEntry() != null) {
                result = VoFactory.createTradeHistoryEntryVo(trade.getCurrentManualStateHistEntry());
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return result;
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#saveInitialTradeReclamationState(Long,
     *      byte[], String)
     */
    @Override
    public void saveInitialTradeReclamationState(Long employeeId, byte[] attachmentContent, String attachmentName)
            throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            TradeSearchParams param = null;
            param = createCurrentTradeSearchParam();
            EmployeeImpl emplyoee = (EmployeeImpl) getSession().select(EmployeeImpl.class, employeeId);
            param.setEmployee(emplyoee);
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            Collection<TradeImpl> col = finder.findTrades(param);
            logger.debug("Found " + col.size() + " trades of user with id " + employeeId + ".");
            Iterator<TradeImpl> it = col.iterator();
            ReportImageImpl image = null;

            if (attachmentContent != null && it.hasNext()) {
                image = (ReportImageImpl) getSession().create(ReportImageImpl.class);
                image.setName(attachmentName);
                image.setImage(getSession().getHibernateSession().getLobHelper().createBlob(attachmentContent));
            }

            while (it.hasNext()) {
                TradeImpl trade = it.next();
                if (trade.getCurrentManualState() != null) {
                    ReclamationStateImpl reclState = trade.getCurrentManualState().getReclamationState();
                    if (reclState != null) {
                        ReclStateHistEntryImpl reclStateHist = (ReclStateHistEntryImpl) getSession()
                                .create(ReclStateHistEntryImpl.class);
                        reclStateHist.setReclamationState(reclState);
                        reclStateHist.setLevel(0);
                        reclStateHist.setStateTime(new GregorianCalendar());
                        reclStateHist.setReportImage(image);
                        reclStateHist.setTrade(trade);
                        reclStateHist.setCreatedByEmployee(user);
                        trade.setCurrentReclStateHistEntry(reclStateHist);
                        getSession().update(trade);
                    }
                }
            }
            t.commit();
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#deleteStateCode(String)
     */
    @Override
    public void deleteStateCode(String code) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            if (mandant != null && code != null) {
                StateIdImpl stateId = new StateIdImpl(mandant, code);
                getSession().delete(StateImpl.class,  stateId);
            }
            t.commit();
        } catch (Exception e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getStateCode(String)
     */
    @Override
    public StateCodeVo getStateCode(String code) throws RemoteException {
        StateCodeVo result = new StateCodeVo();
        try {
            if (mandant != null && code != null) {
                StateIdImpl stateId = new StateIdImpl(mandant, code);
                StateImpl state = (StateImpl) getSession().select(StateImpl.class, stateId);
                result = VoFactory.createStateCodeVo(state);
            }
        } catch (Exception e) {
            throwRemoteException(e);
        }
        return result;
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#saveStateCode(StateCodeVo)
     */
    @Override
    public void saveStateCode(StateCodeVo stateCodeVo) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();

            MandantImpl mand = null;
            if (stateCodeVo.getMandantCode() != null) {
                mand = new MandantImpl(stateCodeVo.getMandantCode());
            } else {
                mand = mandant;
            }

            StateIdImpl identifier = null;
            if (stateCodeVo.getStateCode() != null) {
                identifier = new StateIdImpl(mand, stateCodeVo.getStateCode());
            } else {
                identifier = new StateIdImpl(mand, stateCodeVo.getShortDescription());
            }

            StateImpl state = null;
            if (StateCodeTypeDef.AUTO.equals(stateCodeVo.getType())) {
                if (stateCodeVo.isCreateNew()) {
                    state = new AutomaticStateImpl(identifier);
                    state.setEnabled(true);
                } else {
                    state = (AutomaticStateImpl) getSession().select(AutomaticStateImpl.class, identifier);
                }
                if (stateCodeVo.getManualCheckRequired() != null) {
                    ((AutomaticStateImpl) state).setIsManualCheckRequired(stateCodeVo.getManualCheckRequired()
                            .booleanValue());
                } else {
                    ((AutomaticStateImpl) state).setIsManualCheckRequired(false);
                }
                if (stateCodeVo.getMccChecked() != null) {
                    ((AutomaticStateImpl) state).setIsMccChecked(stateCodeVo.getMccChecked().booleanValue());
                } else {
                    ((AutomaticStateImpl) state).setIsMccChecked(false);
                }
                ((AutomaticStateImpl) state).setMarketDataRequestType(stateCodeVo.getMarketDataRequestType());
                if (stateCodeVo.getManualSampleCode() != null) {
                	ManualSampleStateImpl sampleState = (ManualSampleStateImpl) getSession().select(ManualSampleStateImpl.class,
                            new StateIdImpl(mand, stateCodeVo.getManualSampleCode())); 
                    ((AutomaticStateImpl) state).setSampleState(sampleState);
                }
                if (stateCodeVo.getManualSamplePercentage() != null) {
                	((AutomaticStateImpl) state).setSamplePercentage(stateCodeVo.getManualSamplePercentage().intValue());
                }
            } else if (StateCodeTypeDef.MANUAL.equals(stateCodeVo.getType())) {
                if (stateCodeVo.isCreateNew()) {
                    state = new ManualStateImpl(identifier);
                    state.setEnabled(true);
               } else {
                    state = (ManualStateImpl) getSession().select(ManualStateImpl.class, identifier);
                }
                if (stateCodeVo.getReclamationRequired() != null) {
                    ((ManualStateImpl) state).setIsReclamationRequired(stateCodeVo.getReclamationRequired()
                            .booleanValue());
                } else {
                    ((ManualStateImpl) state).setIsReclamationRequired(false);
                }
                if (stateCodeVo.getReclamationCode() != null) {
                    ReclamationStateImpl reclState = (ReclamationStateImpl) getSession().select(ReclamationStateImpl.class,
                            new StateIdImpl(mand, stateCodeVo.getReclamationCode()));
                    if (reclState != null) {
                        ((ManualStateImpl) state).setReclamationState(reclState);
                    }
                }
            } else if (StateCodeTypeDef.SAMPLE.equals(stateCodeVo.getType())) {
                if (stateCodeVo.isCreateNew()) {
                    state = new ManualSampleStateImpl(identifier);
                    state.setEnabled(true);
                } else {
                    state = (ManualSampleStateImpl) getSession().select(ManualSampleStateImpl.class, identifier);
                }
            } else if (StateCodeTypeDef.RECLAMATION.equals(stateCodeVo.getType())) {
                if (stateCodeVo.isCreateNew()) {
                    state = new ReclamationStateImpl(identifier);
                    state.setEnabled(true);
                } else {
                    state = (ReclamationStateImpl) getSession().select(ReclamationStateImpl.class, identifier);
                }
            }

            if (state != null) {
                if (stateCodeVo.getFinalState() != null) {
                    state.setFinalState(stateCodeVo.getFinalState().booleanValue());
                } else {
                    state.setFinalState(false);
                }
                state.setLongDescription(stateCodeVo.getLongDescription());
                state.setMandant(mand);
                state.setShortDescription(stateCodeVo.getShortDescription());
                state.setComment(stateCodeVo.getComment());
                getSession().save(state);
            }
            t.commit();
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#deleteExchange(Long)
     */
    @Override
    public void deleteExchange(Long exchangeId) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            if (exchangeId != null) {
                ExchangeImpl exchange = (ExchangeImpl) getSession().select(ExchangeImpl.class, exchangeId);
                Collection<ExchangeMappingEntryImpl> col = exchange.getMappings();
                Iterator<ExchangeMappingEntryImpl> iter = col.iterator();
                while (iter.hasNext()) {
                    ExchangeMappingEntryImpl exchangeMapping = iter.next();
                    deleteExchangeMapping(exchangeMapping.getLongId());
                }
                if (performFourEyeSave) {
                    DualControlJobProcessorImpl processor = new DualControlJobProcessorImpl();
                    processor.delete(getSession(), exchange, user);
                } else {
                    getSession().delete(ExchangeImpl.class, exchangeId);
                }
            }
            t.commit();
        } catch (Exception e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#deleteExchangeMapping(Long)
     */
    @Override
    public void deleteExchangeMapping(Long exchangeMappingId) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            if (exchangeMappingId != null) {
                if (performFourEyeSave) {
                    DualControlJobProcessorImpl processor = new DualControlJobProcessorImpl();
                    ExchangeMappingEntryImpl exchangeMapping = (ExchangeMappingEntryImpl) getSession().select(
                            ExchangeMappingEntryImpl.class, exchangeMappingId);
                    processor.delete(getSession(), exchangeMapping, user);
                } else {
                    getSession().delete(ExchangeMappingEntryImpl.class, exchangeMappingId);
                }
            }
            t.commit();
        } catch (Exception e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#findAllExchanges()
     */
    @Override
    public ExchangeSearchResultEntryVo[] findAllExchanges() throws RemoteException {
        ArrayList<ExchangeSearchResultEntryVo> resultArray = new ArrayList<ExchangeSearchResultEntryVo>();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            Collection<ExchangeImpl> col = finder.findExchanges(createCurrentTradeSearchParam());
            logger.debug("Found " + col.size() + " exchanges.");
            Iterator<ExchangeImpl> it = col.iterator();
            while (it.hasNext()) {
                ExchangeImpl exchange = it.next();
                ExchangeSearchResultEntryVo result = new ExchangeSearchResultEntryVo();
                result.setBloombergId(exchange.getBloombergId());
                result.setReutersId(exchange.getReutersId());
                result.setId(exchange.getLongId());
                result.setChangePending(false);
                Iterator<EmployeeImpl> iter = (new DualControlJobFinderImpl(getSession())).findPendingDualControlJobsEntryRequester(exchange).iterator();
                while (iter.hasNext()) {
                    result.setChangePending(true);
                    EmployeeImpl requester = iter.next();
                    if (requester != null) {
                        result.setChangeRequestedByName(requester.getFullName());
                    }
                }

                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return resultArray.toArray(new ExchangeSearchResultEntryVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getExchange(Long)
     */
    @Override
    public ExchangeVo getExchange(Long exchangeId) throws RemoteException {
        ExchangeVo result = new ExchangeVo();
        try {
            if (exchangeId != null) {
            	DualControlJobFinderImpl finder = new DualControlJobFinderImpl(getSession());
                ExchangeImpl exchange = (ExchangeImpl) getSession().select(ExchangeImpl.class, exchangeId);
                result.setBloombergId(exchange.getBloombergId());
                result.setReutersId(exchange.getReutersId());
                result.setId(exchange.getLongId());
                result.setChangePending(false);
                Iterator<EmployeeImpl> iterat = finder.findPendingDualControlJobsEntryRequester(exchange).iterator();
                while (iterat.hasNext()) {
                    result.setChangePending(true);
                    EmployeeImpl requester = iterat.next();
                    if (requester != null) {
                        result.setChangeRequestedByName(requester.getFullName());
                    }
                }

                Collection<ExchangeMappingEntryImpl> col = exchange.getMappings();
                if (col != null) {
                    ExchangeMappingVo[] mapArray = new ExchangeMappingVo[col.size()];
                    Iterator<ExchangeMappingEntryImpl> iter = col.iterator();
                    int i = 0;
                    while (iter.hasNext()) {
                        ExchangeMappingEntryImpl mapping = iter.next();
                        ExchangeMappingVo map = new ExchangeMappingVo();
                        map.setCurrency(mapping.getCurrency());
                        map.setId(mapping.getLongId());
                        map.setIsin(mapping.getIsin());
                        map.setIsinCountryPrefix(mapping.getIsinCountryPrefix());
                        if (mapping.getSourceSystem() != null) {
                            map.setSourceSystemCode(mapping.getSourceSystem().getCode());
                        }
                        map.setSourceSystemExchangeId(mapping.getSourceSystemExchangeId());
                        map.setExchangeId(exchange.getLongId());

                        map.setChangePending(false);
                        Iterator<EmployeeImpl> it = finder.findPendingDualControlJobsEntryRequester(mapping).iterator();
                        while (it.hasNext()) {
                            map.setChangePending(true);
                            EmployeeImpl requester = it.next();
                            if (requester != null) {
                                map.setChangeRequestedByName(requester.getFullName());
                            }
                        }

                        mapArray[i] = map;
                        i++;
                    }
                    result.setMappings(mapArray);
                }
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return result;
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#saveExchangeMapping(ExchangeMappingVo)
     */
    @Override
    public void saveExchangeMapping(ExchangeMappingVo exchangeMapping) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            ExchangeMappingEntryImpl newExchangeMapping = null;
            if (exchangeMapping.getId() != null) {
                newExchangeMapping = (ExchangeMappingEntryImpl) getSession().select(ExchangeMappingEntryImpl.class,
                        exchangeMapping.getId());
            } else {
                if (performFourEyeSave) {
                    newExchangeMapping = new ExchangeMappingEntryImpl();
                } else {
                    newExchangeMapping = (ExchangeMappingEntryImpl) getSession().create(ExchangeMappingEntryImpl.class);
                }
            }
            newExchangeMapping.setCurrency(exchangeMapping.getCurrency());
            ExchangeImpl exchange = (ExchangeImpl) getSession().select(ExchangeImpl.class, exchangeMapping.getExchangeId());
            newExchangeMapping.setExchange(exchange);
            newExchangeMapping.setIsin(exchangeMapping.getIsin());
            newExchangeMapping.setIsinCountryPrefix(exchangeMapping.getIsinCountryPrefix());
            if (exchangeMapping.getSourceSystemCode() != null) {
                newExchangeMapping.setSourceSystem(new SourceSystemImpl(exchangeMapping.getSourceSystemCode()));
            }
            newExchangeMapping.setSourceSystemExchangeId(exchangeMapping.getSourceSystemExchangeId());

            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            ExchangeMappingEntryPrioImpl prio = finder.findExchangeMappingEntryPrio(exchangeMapping.getSourceSystemCode() != null && exchangeMapping.getExchangeId() != null, exchangeMapping.getIsin() != null, exchangeMapping.getIsinCountryPrefix() != null, exchangeMapping.getCurrency() != null);
            if (prio == null) {
                throw new RemoteException("No exchange priority was found to the input (refer to the documentation of valid priorities)"); 
            }
            newExchangeMapping.setMappingPrio(prio);
            
            if (performFourEyeSave) {
                DualControlJobProcessorImpl processor = new DualControlJobProcessorImpl();
                t.rollback();
                closeSession();
                session = StoreSingleton.getUniqueInstance().openSession(mandant, user.getNtId());
                if (exchangeMapping.getId() != null) {
                    processor.update(session, newExchangeMapping, user);
                } else {
                    processor.insert(session, newExchangeMapping, user);
                }
            } else {
                getSession().save(newExchangeMapping);
                t.commit();
                getSession().refresh(exchange);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#saveExchange(ExchangeVo)
     */
    @Override
    public void saveExchange(ExchangeVo exchange) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            ExchangeImpl newExchange = null;
            if (exchange.getId() != null) {
                newExchange = (ExchangeImpl) getSession().select(ExchangeImpl.class, exchange.getId());
            } else {
                if (performFourEyeSave) {
                    newExchange = new ExchangeImpl();
                } else {
                    newExchange = (ExchangeImpl) getSession().create(ExchangeImpl.class);
                }
            }
            newExchange.setBloombergId(exchange.getBloombergId());
            newExchange.setReutersId(exchange.getReutersId());
            if (performFourEyeSave) {
                DualControlJobProcessorImpl processor = new DualControlJobProcessorImpl();
                t.rollback();
                closeSession();
                session = StoreSingleton.getUniqueInstance().openSession(mandant, user.getNtId());
                if (exchange.getId() != null) {
                    processor.update(session, newExchange, user);
                } else {
                    processor.insert(session, newExchange, user);
                }
            } else {
                getSession().save(newExchange);
                t.commit();
            }
        } catch (Exception e) {
            throwRemoteException(e);
        }
    }

    /**
     * Converts objects to be displayed as values in the storno table All
     * Objects except Numbers and calendars are converted to Strings.
     * 
     * @param object
     * @return the converted objects.
     */
    private Object cvtStornoFieldValue(Object object) {
        if (object instanceof Number || object instanceof Calendar) {
            return object;
        } else if (object == null) {
            return "";
        } else {
            return object.toString();
        }
    }

    @Override
    public StornoGroupVo[] getStornoGroup(Long referenceTradeId) throws RemoteException {
        ArrayList<StornoGroupVo> resultArray = new ArrayList<StornoGroupVo>();
        try {
            TradeImpl referenceTrade = (TradeImpl) getSession().select(TradeImpl.class, referenceTradeId);
//            TradeImpl referenceTrade = (TradeImpl) getSession().select(AbstractFinder.getTradeClassForMandantCode(mandant.getCode()), referenceTradeId);
            if (referenceTrade != null) {
                MgbFinderImpl finder = new MgbFinderImpl(getSession());
                if (referenceTrade instanceof SummitDerivativeImpl) {
                    Collection<SummitAmendImpl> col = finder.findSummitAmends(referenceTrade.getTradeId());
                    logger.debug("Found " + col.size() + " amendments with the referenceId " + referenceTradeId + ".");
                    Iterator<SummitAmendImpl> it = col.iterator();
                    while (it.hasNext()) {
                        SummitAmendImpl amend = it.next();
                        StornoGroupVo result = new StornoGroupVo();
                        result.setFieldValue(amend.getFieldValueOld());
                        TradeSearchParams params = new TradeSearchParams();
                        if (amend.getTradeIdOld() != null) {
                            params.setTradeIds(new String[]{amend.getTradeIdOld()});
                            params.setMaxResults(1);
                            Iterator<TradeImpl> trades = finder.findTrades(params).iterator();
                            if (trades.hasNext()){
                                result.setId(trades.next().getLongId());
                            }
                        }
                        result.setTradeId(amend.getAmendedTradeIdOld());
                        result.setReferenceValue(amend.getFieldValueNew());
                        result.setReferenceId(referenceTrade.getLongId());
                        result.setReferenceTradeId(amend.getAmendedTradeIdNew());
                        result.setFieldName(amend.getFieldModified());
                        result.setFieldValueChange(amend.getFieldValueChange());
                        resultArray.add(result);
                     }
                } else {
                    TradeSearchParams param = createCurrentTradeSearchParam();
                    param.setTradeGroupId(referenceTrade.getTradeGroupId());
                    param.setClassName(referenceTrade.getClass());
                    Collection<TradeImpl> col = finder.findTradeGroups(param);
                    logger.debug("Found " + col.size() + " trades with the referenceId " + referenceTradeId + ".");
                    Iterator<TradeImpl> it = col.iterator();
                    while (it.hasNext()) {
                        TradeImpl trade = it.next();
                        ObjectComparator objectComparator = new ObjectComparator();
                        if (trade instanceof SummitBondImpl) {
                            objectComparator.setFilter(MgbSummitBondMethodFilter.getInstance());
                        } else if (trade instanceof SummitRepoImpl) {
                            objectComparator.setFilter(MgbSummitRepoMethodFilter.getInstance());
                        } else if (trade instanceof SummitDerivativeImpl) {
                            objectComparator.setFilter(MgbSummitDerivativeMethodFilter.getInstance());
                        } else {
                            objectComparator.setFilter(MgbMethodFilter.getInstance());
                        }
                        Method[] methods = objectComparator.getChangedFieldsAccessorList(trade, referenceTrade);
                        for (int i = 0; i < methods.length; i++) {
                            StornoGroupVo result = new StornoGroupVo();
                            result.setFieldValue(cvtStornoFieldValue(methods[i].invoke(trade)));
                            result.setId(trade.getLongId());
                            result.setTradeId(trade.getTradeId());
                            result.setReferenceValue(cvtStornoFieldValue(methods[i].invoke(referenceTrade)));
                            result.setReferenceId(referenceTrade.getLongId());
                            result.setReferenceTradeId(referenceTrade.getTradeId());
                            result.setFieldName(methods[i].getName());
                            resultArray.add(result);
                        }
                    }
                }
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        } catch (IllegalArgumentException e) {
            throwRemoteException(e);
        } catch (IllegalAccessException e) {
            throwRemoteException(e);
        } catch (InvocationTargetException e) {
            throwRemoteException(e);
        }
        logger.debug("Found " + resultArray.size() + " entires of the storno group.");
        return resultArray.toArray(new StornoGroupVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#deleteAddon(Long)
     */
    @Override
    public void deleteAddon(Long addonId) throws RemoteException {
        try {
            if (addonId != null) {
                if (performFourEyeSave) {
                    DualControlJobProcessorImpl processor = new DualControlJobProcessorImpl();
                    AddonImpl addon = (AddonImpl) getSession().select(AddonImpl.class, addonId);
                    processor.delete(getSession(), addon, user);
                } else {
                    Transaction t = getSession().beginTransaction();
                    getSession().delete(AddonImpl.class, addonId);
                    t.commit();
                }
            }
        } catch (Exception e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#findAllAddons()
     */
    @Override
    public AddonSearchResultEntryVo[] findAllAddons() throws RemoteException {
        ArrayList<AddonSearchResultEntryVo> resultArray = new ArrayList<AddonSearchResultEntryVo>();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            Collection<AddonImpl> col = finder.findAddons(new SearchParams(mandant));
            logger.debug("Found " + col.size() + " addons.");
            Iterator<AddonImpl> it = col.iterator();
            while (it.hasNext()) {
                AddonImpl addon = it.next();
                AddonSearchResultEntryVo result = new AddonSearchResultEntryVo();
                result.setComment(addon.getComment());
                result.setCondition(addon.getCondition());
                result.setPriceTolerancePercent(addon.getPriceTolerancePercent());
                result.setTimeToleranceMinutes(addon.getTimeToleranceMinutes());
                result.setId(addon.getLongId());
                result.setChangePending(false);
                Iterator<EmployeeImpl> iter = (new DualControlJobFinderImpl(getSession())).findPendingDualControlJobsEntryRequester(addon).iterator();
                while (iter.hasNext()) {
                    result.setChangePending(true);
                    EmployeeImpl requester = iter.next();
                    if (requester != null) {
                        result.setChangeRequestedByName(requester.getFullName());
                    }
                }
                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return resultArray.toArray(new AddonSearchResultEntryVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getAddon(Long)
     */
    @Override
    public AddonVo getAddon(Long addonId) throws RemoteException {
        AddonVo result = new AddonVo();
        try {
            if (addonId != null) {
                AddonImpl addon = (AddonImpl) getSession().select(AddonImpl.class, addonId);
                result.setComment(addon.getComment());
                result.setCondition(addon.getCondition());
                result.setPriceTolerancePercent(addon.getPriceTolerancePercent());
                result.setTimeToleranceMinutes(addon.getTimeToleranceMinutes());
                result.setId(addon.getLongId());
            }
        } catch (Exception e) {
            throwRemoteException(e);
        }
        return result;
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#saveAddon(AddonVo)
     */
    @Override
    public void saveAddon(AddonVo addon) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            AddonImpl newAddon = null;
            if (addon.getId() != null) {
                newAddon = (AddonImpl) getSession().select(AddonImpl.class, addon.getId());
            } else {
                if (performFourEyeSave) {
                    newAddon = new AddonImpl();
                } else {
                    newAddon = (AddonImpl) getSession().create(AddonImpl.class);
                }
            }
            newAddon.setComment(addon.getComment());
            newAddon.setCondition(addon.getCondition());
            newAddon.setPriceTolerancePercent(addon.getPriceTolerancePercent());
            newAddon.setTimeToleranceMinutes(addon.getTimeToleranceMinutes());
            if (performFourEyeSave) {
                DualControlJobProcessorImpl processor = new DualControlJobProcessorImpl();
                t.rollback();
                closeSession();
                session = StoreSingleton.getUniqueInstance().openSession(mandant, user.getNtId());
                if (addon.getId() != null) {
                    processor.update(session, newAddon, user);
                } else {
                    processor.insert(session, newAddon, user);
                }
            } else {
                getSession().save(newAddon);
                t.commit();
            }
        } catch (Exception e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#findAllConditions()
     */
    @Override
    public ConditionVo[] findAllConditions() throws RemoteException {
        ArrayList<ConditionVo> resultArray = new ArrayList<ConditionVo>();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            Collection<SourceSystemImpl> sysCol = finder.findSourceSystems(new String[] { mandant.getCode()});
            Iterator<SourceSystemImpl> iter = sysCol.iterator();
            if (iter.hasNext()) {
                Collection<ConditionImpl> col = finder.findConditions(iter.next());
                Iterator<ConditionImpl> it = col.iterator();
                while (it.hasNext()) {
                    ConditionImpl cond = it.next();
                    ConditionVo result = new ConditionVo();
                    result.setConditionEvaluator(cond.getConditionEvaluator());
                    result.setConditionName(cond.getConditionName());
                    result.setConditionType(cond.getConditionType());
                    result.setId(cond.getLongId());
                    if (cond.getSourceSystem() != null) {
                        result.setSourceSystemCode(cond.getSourceSystem().getCode());
                    }
                    resultArray.add(result);
                }
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        logger.debug("Found " + resultArray.size() + " conditions");
        return resultArray.toArray(new ConditionVo[0]);
    }

    /**
     * @deprecated @see de.westlb.mgb.client.server.Mgb#getRepoTradeOverview(Object)
     */
    @SuppressWarnings({ "unchecked", "rawtypes"})
    public RepoTradeOverviewVo getRepoTradeOverview(Object tradeId, boolean isListMode) throws RemoteException {
        RepoTradeOverviewVo result = new RepoTradeOverviewVo();
        try {
            RepoImpl trade = (RepoImpl) getSession().select(RepoImpl.class, (Serializable) tradeId);
            if (trade != null) {
                result.setId(trade.getLongId());

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
                if (trade.getBasePointTolerance() == 0 && trade.getInstrument() != null
                        && trade.getInstrument().getPriceCheckCategory() != null) {
                    result.setBasePointTolerance(trade.getInstrument().getPriceCheckCategory()
                            .getPriceToleranceAbsolute());
                }
                result.setBasePointDifference(trade.getRateDiff());
                result.setBookId(trade.getBookId());
                result.setCurrency(trade.getCurrency());
                result.setCounterparty(trade.getCounterpartyId());
                result.setEndCash(trade.getEndCash());
                result.setEndDate(trade.getEndDate());
                result.setForeignExchangeRate(trade.getForeignExchangeRate());
                result.setInstrument(trade.getInstrumentCode());
                result.setInstrumentType(trade.getInstrumentType());
                result.setIsin(trade.getSourceSystemInstrument());
                result.setLateEntry(Boolean.valueOf(trade.isLateDeal()));
                if (trade.getCurrentManualState() != null) {
                    result.setManualStateCode(trade.getCurrentManualState().getStateCode());
                }
                if (trade.getCurrentManualStateHistEntry() != null) {
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
                    result.setManualStateAttachmentId(trade.getCurrentManualStateHistEntry().getReportImage()
                            .getLongId());
                    result.setManualStateAttachmentName(trade.getCurrentManualStateHistEntry().getReportImage()
                            .getName());
                }
                if (trade.getRequests() != null) {
                    ArrayList requests = new ArrayList();
                    ArrayList prices = new ArrayList();
                    ArrayList priceDifferences = new ArrayList();
                    Iterator it = trade.getRequests().iterator();
                    while (it.hasNext()) {
                        RequestImpl request = (RequestImpl) it.next();
                        if (MarketDataRequestStateDef.OK_PRICE_UNVALIDATED.equals(request.getRequestState())
                                || MarketDataRequestStateDef.OK.equals(request.getRequestState())) {
                            RequestVo req = VoFactory.createRequestVo(request);
                            req.setJobId(trade.getJob().getLongId());
                            req.setPriceSource(MarketDataSourceDef.REUTERS);
                            req.setPriceDate(trade.getTradeDate());
                            req.setTradePrice(trade.getPrice());
                            requests.add(req);
                            result.setCurrentRequest(req);
                            PriceVo price = new PriceVo();
                            if (request.getPriceResult() != null) {
                                price = getPrice(req);
                            }
                            prices.add(price);
                            result.setCurrentPrice(price);

                            priceDifferences.add(new Double(trade.getPrice() - price.getMidPrice()));
                            result.setCurrentPriceDifference(trade.getPrice() - price.getMidPrice());
                        }
                    }
                    result.setRequests((RequestVo[]) requests.toArray(new RequestVo[0]));
                    result.setPrices((PriceVo[]) prices.toArray(new PriceVo[0]));
                }

                result.setMdCurrency(trade.getCurrency());
                result.setMaturityDays(trade.getDays());
                result.setOpenEnd(trade.isOpenEnd());
                result.setPortfolio(trade.getBookId());
                result.setProfitLossEffect(trade.getProfitLossDiffEuro());
                result.setRepoRate(trade.getRepoRate());

                if (trade.getReutersRequests() != null) {
                    Iterator it = trade.getReutersRequests().iterator();
                    if (it.hasNext()) {
                        ReutersRequestImpl request = (ReutersRequestImpl) it.next();
                        Price price = request.getPriceResult();
                        if (price instanceof HistoricPrice) {
                            result.setMarketPrice(((HistoricPrice) price).getPrice());
                        }
                    }
                }

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
                result.setTradePrice(trade.getPrice());
                result.setTradeId(trade.getTradeId());
                result.setTraderId(trade.getTraderId());
                result.setTraderName(trade.getTraderName());
                result.setUnderlyingType(trade.getUnderlyingInstrumentType());
                result.setUnderlyingValueGroup(trade.getUnderlyingValGroup());
                result.setVolume(trade.getVolume());
                result.setPriceToleranceViolation(Boolean.valueOf(result.getBasePointTolerance() < Math.abs(result
                        .getBasePointDifference())));
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return result;
    }

    @Override
    public TradeOverviewVo getTradeOverview(Object tradeId) throws java.rmi.RemoteException {
        return getTradeOverview(tradeId, false);
    }

    public TradeOverviewVo getTradeOverview(Object tradeId, boolean isListMode) throws java.rmi.RemoteException {
        try {
            TradeImpl trade = (TradeImpl) getSession().select(TradeImpl.class, (Serializable) tradeId);
            return getTradeOverview(trade, isListMode);
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return null;
    }
    
   private TradeOverviewVo getTradeOverview(TradeImpl trade, boolean isListMode) throws PersistenceException, java.rmi.RemoteException  {
        TradeOverviewVo result = null;
            result = VoFactory.createTradeOverviewVo(trade, isListMode);
            result.setReportLocation(reportConfigMapping.get(result.getTraderLocation()));
            result.setSourceSystemCode(trade.getSourceSystem().getCode());
            result.setParameter(getTradeParameter(trade));
            if (trade.getRequests() != null) {
                ArrayList<RequestVo> requests = new ArrayList<RequestVo>();
                ArrayList<PriceVo> prices = new ArrayList<PriceVo>();
                ArrayList<Double> priceDifferences = new ArrayList<Double>();
                Iterator<RequestImpl> it = trade.getRequests().iterator();
                while (it.hasNext()) {

                    RequestImpl request = it.next();
                    if (MarketDataRequestStateDef.OK_PRICE_UNVALIDATED.equals(request.getRequestState())
                            || MarketDataRequestStateDef.OK.equals(request.getRequestState())) {
                        RequestVo req = VoFactory.createRequestVo(request);
                        req.setJobId(trade.getJob().getLongId());
                        req.setPriceDate(trade.getTradeDate());
                        req.setTradePrice(trade.getPrice());
                        req.setIsoCurrency(trade.getCurrency());
                        requests.add(req);
                        result.setCurrentRequest(req);

                        // TODO: If there does not exist a price for a request,
                        // the turnover will calculated
                        // for a market price of 0.
                        PriceVo price = new PriceVo();
                        if (request.getPriceResult() != null) {
                            price = getPrice(req);
                        }
                        prices.add(price);
                        result.setCurrentPrice(price);

                        double midPrice = PriceUtils.calculateMidPrice(result.getCurrentPrice().getMinPrice(), result.getCurrentPrice().getMaxPrice());
                        double diff = trade.calculatePriceDifference(price.getMinPrice(), price.getMaxPrice(), PriceDef.INTERVAL.equals(price.getPriceType()));

                        if (trade instanceof SummitForeignExchangeImpl) {
                            midPrice = ((SummitForeignExchangeImpl)trade).getMarketRate();
                            diff = trade.getPrice() - midPrice; 
                        }
                        result.setTurnover(trade.calculateTurnover(midPrice));
                        priceDifferences.add(new Double(diff));
                        result.setCurrentPriceDifference(diff);
                    	if (trade instanceof SummitRepoImpl) {
                            result.setPriceTolerance(calculatePriceTolerance(((SummitRepoImpl)trade).getBondInstrument(), midPrice));
                    	} else {
                            result.setPriceTolerance(calculatePriceTolerance(trade.getInstrument(), midPrice));
                        }
                    } else if (result.getCurrentPrice() == null) {
                        // As long there is no request with price, take the
                        // latest request without price.
                        RequestVo req = VoFactory.createRequestVo(request);
                        req.setJobId(trade.getJob().getLongId());
                        result.setCurrentRequest(req);
                    }
                }
                if (result.getCurrentPrice() == null && trade.getFrontOfficeMarketRate() != 0.0) {
                    // As long there is no current price but a front office price, take it
                    result.setCurrentPriceDifference(trade.getPrice()-trade.getFrontOfficeMarketRate());
                    result.setTurnover(trade.calculateTurnover(trade.getFrontOfficeMarketRate()));
                    if (trade instanceof SummitForeignExchangeImpl) {
                        result.setNearTurnover(((SummitForeignExchangeImpl)trade).calculateNearTurnover(trade.getFrontOfficeMarketRate()));
                        result.setFarTurnover(((SummitForeignExchangeImpl)trade).calculateFarTurnover(trade.getFrontOfficeMarketRate()));
                    }
                    
                    result.setPriceTolerance(calculatePriceTolerance(trade.getInstrument(), trade.getFrontOfficeMarketRate()));
                }
                result.setRequests(requests.toArray(new RequestVo[0]));
                result.setPrices(prices.toArray(new PriceVo[0]));
                Double[] diff = new Double[priceDifferences.size()];
                for (int i = 0; i < diff.length; i++) {
                    diff[i] = priceDifferences.get(i);
                }
                result.setPriceDifferences(diff);
            }
        return result;
    }

   private double calculatePriceTolerance(PriceCheckInstrumentImpl priceCheckInstrument, double marketPrice) {
       if (priceCheckInstrument != null && priceCheckInstrument.getPriceCheckCategory() != null) {
           if (priceCheckInstrument.getPriceCheckCategory().getPriceToleranceAbsolute() != 0) {
               return priceCheckInstrument.getPriceCheckCategory().getPriceToleranceAbsolute();
           }
        return marketPrice * (priceCheckInstrument.getPriceCheckCategory().getPriceTolerancePercent() / 100.0);
       }
       return 0.0;
   }
   
    /**
     * @see de.westlb.mgb.client.server.Mgb#deletePriceCheckCategory(Long)
     */
    @Override
    public void deletePriceCheckCategory(Long priceCheckCategoryId) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            if (priceCheckCategoryId != null) {
                PriceCheckCategoryImpl priceCheckCategory = (PriceCheckCategoryImpl) getSession().select(PriceCheckCategoryImpl.class,
                        priceCheckCategoryId);
                if (performFourEyeSave) {
                    DualControlJobProcessorImpl processor = new DualControlJobProcessorImpl();
                    processor.delete(getSession(), priceCheckCategory, user);
                } else {
                    getSession().delete(PriceCheckCategoryImpl.class, priceCheckCategoryId);
                }
            }
            t.commit();
        } catch (Exception e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#getPriceCheckCategory(Long)
     */
    @Override
    public PriceCheckCategoryVo getPriceCheckCategory(Long priceCheckCategoryId) throws RemoteException {
        PriceCheckCategoryVo result = new PriceCheckCategoryVo();
        try {
            if (priceCheckCategoryId != null) {
            	PriceCheckCategoryImpl priceCheckCategory = (PriceCheckCategoryImpl) getSession().select(
                        PriceCheckCategoryImpl.class, priceCheckCategoryId);
                result = VoFactory.createPriceCheckCategoryVo(priceCheckCategory);
            }
        } catch (Exception e) {
            throwRemoteException(e);
        }
        return result;
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#savePriceCheckCategory(PriceCheckCategoryVo)
     */
    @Override
    public void savePriceCheckCategory(PriceCheckCategoryVo priceCheckCategory) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            boolean isUpdateTransaction = false;
            PriceCheckCategoryImpl newPriceCheckCategory = null;

            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            
            PriceCheckCategorySearchParams param = new PriceCheckCategorySearchParams();
            param.setMandant(mandant);
			param.setName(priceCheckCategory.getName());
            Collection<PriceCheckCategoryImpl> categories = finder.findPriceCheckCategories(param);
			param.setName(priceCheckCategory.getName()+InstrumentNameDef.CROSS_CURRENCY_COMMENT);            
            categories.addAll(finder.findPriceCheckCategories(param));
            Iterator<PriceCheckCategoryImpl> it = categories.iterator();
            if (it.hasNext()) {
                newPriceCheckCategory = it.next();
                isUpdateTransaction = true;
                if (it.hasNext()) {
                    logger.error("Found duplicate PriceCheckCategoryImpl with name " + priceCheckCategory.getName());
                }
            } else {
                if (performFourEyeSave) {
                    newPriceCheckCategory = new PriceCheckCategoryImpl();
                } else {
                    newPriceCheckCategory = (PriceCheckCategoryImpl) getSession().create(PriceCheckCategoryImpl.class);
                }
                newPriceCheckCategory.setEnabled(true);
                newPriceCheckCategory.setName(priceCheckCategory.getName());
           }

            newPriceCheckCategory.setPriceToleranceAbsolute(priceCheckCategory.getPriceToleranceAbsolute());
            newPriceCheckCategory.setPriceTolerancePercent(priceCheckCategory.getPriceTolerancePercent());
            newPriceCheckCategory.setTimeToleranceMinutes(priceCheckCategory.getTimeToleranceMinutes());
            if (priceCheckCategory.getManualSamplePercentage() != null) {
            	newPriceCheckCategory.setSamplePercentage(priceCheckCategory.getManualSamplePercentage().intValue());
            }
            if (priceCheckCategory.getManualSampleCode() != null) {
            	ManualSampleStateImpl manualSampleState = (ManualSampleStateImpl)getSession().select(ManualSampleStateImpl.class, new StateIdImpl(mandant, priceCheckCategory.getManualSampleCode())); 
            	newPriceCheckCategory.setSampleState(manualSampleState);
            }
            if (performFourEyeSave) {
                DualControlJobProcessorImpl processor = new DualControlJobProcessorImpl();
                t.rollback();
                closeSession();
                session = StoreSingleton.getUniqueInstance().openSession(mandant, user.getNtId());
                if (isUpdateTransaction) {
                    processor.update(session, newPriceCheckCategory, user);
                } else {
                    processor.insert(session, newPriceCheckCategory, user);
                }
            } else {
                getSession().save(newPriceCheckCategory);
                t.commit();
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
    }

     /**
     * @see de.westlb.mgb.client.server.Mgb#findAllBloombergCurrencyCodes()
     */
    @Override
    public BloombergCurrencyCodeVo[] findAllBloombergCurrencyCodes() throws RemoteException {
        ArrayList<BloombergCurrencyCodeVo> resultArray = new ArrayList<BloombergCurrencyCodeVo>();
        try {
        	BloombergCurrencyFinderImpl finder = new BloombergCurrencyFinderImpl(getSession());
            Collection<BloombergCurrencyCodesImpl> col = finder.findBloombergCurrencyCodes();
            Iterator<BloombergCurrencyCodesImpl> it = col.iterator();
            while (it.hasNext()) {
                BloombergCurrencyCodesImpl curr = it.next();
                BloombergCurrencyCodeVo result = new BloombergCurrencyCodeVo();
                result.setBloombergCurrencyCode(curr.getBloombergCurrencyCode());
                result.setCurrencyName(curr.getCurrencyName());
                result.setIsoCurrencyCode(curr.getIsoCurrencyCode());
                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        logger.debug("Found " + resultArray.size() + " BloombergCurrencyCodes");
        return resultArray.toArray(new BloombergCurrencyCodeVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#findAllBloombergMaturityCodes()
     */
    @Override
    public BloombergMaturityCodeVo[] findAllBloombergMaturityCodes() throws RemoteException {
        ArrayList<BloombergMaturityCodeVo> resultArray = new ArrayList<BloombergMaturityCodeVo>();
        try {
        	BloombergMaturityFinderImpl finder = new BloombergMaturityFinderImpl(getSession());
            Collection<BloombergMaturityCodesImpl> col = finder.findBloombergMaturityCodes();
            Iterator<BloombergMaturityCodesImpl> it = col.iterator();
            while (it.hasNext()) {
                BloombergMaturityCodesImpl mat = it.next();
                BloombergMaturityCodeVo result = new BloombergMaturityCodeVo();
                result.setBloombergMaturityCode(mat.getBloombergMaturityCode());
                result.setBloombergMaturityField(mat.getBloombergMaturityField());
                result.setMaturityCode(mat.getMaturityCode());
                result.setMaturityName(mat.getMaturityName());
                result.setMaxMaturityDays(mat.getMaxMaturityDays());
                result.setMinMaturityDays(mat.getMinMaturityDays());
                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        logger.debug("Found " + resultArray.size() + " BloombergCurrencyCodes");
        return resultArray.toArray(new BloombergMaturityCodeVo[0]);
    }

    /*
     * (Kein Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#createNewJob(java.lang.String,
     *      byte[])
     */
    @Override
    public void createNewJob(String sourceSystemCode, byte[] tradeData) throws RemoteException {
        String importDir = PropertyFactory.getProperty(ImportPropertiesDef.IMPORT_IMPORT_DIR);
        String fileNameExtension = PropertyFactory.getProperty(ImportPropertiesDef.IMPORT_FILE_NAME_EXT);        
        String importFilesString = PropertyFactory.getProperty(sourceSystemCode + "." + ImportPropertiesDef.IMPORT_FILE_NAMES);
        logger.debug("createNewJob(): importDir="+importDir+"; "+
                "fileNameExtension="+fileNameExtension+"; importFilesString="+
                importFilesString);
        
        if (importDir == null || importDir.length() == 0 
                || fileNameExtension == null || fileNameExtension.length() == 0
                || importFilesString == null || importFilesString.length() == 0
             ) {
            throw new RemoteException("Check import configuration");
        }
        String importFile = StringUtils.stripAll(StringUtils.split(importFilesString, ","))[0]+"."+fileNameExtension;
        String importFileTmp = importFile + "_tmp";

        File file = new File(importDir, importFileTmp);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(tradeData);
        } catch (Exception e) {
            throwRemoteException(e);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                }
            }
        }
        File targetFile = new File(importDir, importFile);
        if (targetFile.exists() || !file.renameTo(targetFile)) {
            throw new RemoteException("Unable to move "+file.getName()+ " to " + importDir + "/" + importFile);
        }
    }

    @Override
    public byte[] downloadJobFile(Long jobId, String fileNameExtension) throws RemoteException {
        try {
            String archiveDir = PropertyFactory.getProperty(ImportPropertiesDef.IMPORT_ARCHIVE_DIR);
            if (archiveDir == null || archiveDir.length() == 0 || fileNameExtension == null
                    || fileNameExtension.length() == 0) { throw new RemoteException("Check import configuration"); }

            JobImpl job = (JobImpl) getSession().select(JobImpl.class, jobId);

            File file = new File(archiveDir, job.getArchiveFile() + "." + fileNameExtension);
            if (file.exists()) {
                InputStream inputStream = null;
                try {
                    byte[] buffer = new byte[(int) file.length()];
                    inputStream = new FileInputStream(file);
                    inputStream.read(buffer);
                    logger.debug("Downloaded " + file.getName() + " for job " + jobId);
                    return buffer;
                } catch (Exception e) {
                    throwRemoteException(e);
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e) {
                        }
                    }
                }
            } else {
                logger.warn("Unable to download " + file.getName() + " for job " + jobId
                        + " (the file might be archived or zipped)");
                
                file = new File(archiveDir, job.getArchiveFile() + "." + fileNameExtension+".bz2");
                if (file.exists()) {
                    InputStream inputStream = null;
                    ByteArrayOutputStream byteArrayOutputStream = null;
                    try {
                        inputStream = new BZip2CompressorInputStream(new FileInputStream(file));
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] buffer = new byte[256];
                        int bytesRead = 0;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            byteArrayOutputStream.write(buffer, 0, bytesRead);
                        }
                        logger.debug("Downloaded " + file.getName() + " for job " + jobId);
                        return byteArrayOutputStream.toByteArray();
                    } catch (Exception e) {
                        throwRemoteException(e);
                    } finally {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e) {
                            }
                        }
                    }
                } else {
                    logger.warn("Unable to download " + file.getName() + " for job " + jobId);
                }
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return null;
    }

    /*
     * (Kein Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#findAllJobs()
     */
    @Override
    public JobVo[] findAllJobs(JobSearchParamsVo paramVo) throws RemoteException {
        MgbFinderImpl finder = new MgbFinderImpl(getSession());
        JobSearchParams param = new JobSearchParams();
        if (paramVo != null) {
            param.setJobIds(paramVo.getJobIds());
            param.setImportDayFrom(paramVo.getImportDaysFrom());
        }
        if(paramVo.getMandant() == null)
        {
            /* When used from the GUI, use the session's client */
            param.setMandant(mandant);
        }
        else 
        {
            /* From the webpage, it makes more sense to support arbitrary
             * clients (the action should check to use ones the user has
             * access to) */
            try 
            {
                Collection<MandantImpl> mandants = finder.findMandants(true);
                M: for(MandantImpl mandant : mandants)
                {
                    if(mandant.getCode().equals(paramVo.getMandant().getMandantCode()))
                    {
                        param.setMandant(mandant);
                        break M;
                    }
                }
            }
            catch(PersistenceException e)
            {
                throwRemoteException(e);
            }
        }
        if (paramVo != null && paramVo.getNumberOfJobs() != null && paramVo.getNumberOfJobs().intValue() > 0) {
        	param.setMaxResults(paramVo.getNumberOfJobs().intValue());
    	}
        ArrayList<JobVo> jobVoList = new ArrayList<JobVo>();
        try {
            Iterator<JobImpl> iterator = finder.findJobs(param).iterator();
            while (iterator.hasNext()) {
                jobVoList.add(VoFactory.createJobVo(iterator.next()));
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        logger.debug("Found " + jobVoList.size() + " Jobs");
        return jobVoList.toArray(new JobVo[0]);
    }

    /*
     * (Kein Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#keepAlive()
     */
    @Override
    public void keepAlive() throws RemoteException {
        // Do nothing
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#deleteJob(Long)
     */
    @Override
    public void deleteJob(Long jobId) throws RemoteException {
        TradeService tradeService = new TradeService();
        try {
            JobImpl job = (JobImpl) getSession().select(JobImpl.class, jobId);
            Collection<TradeImpl> col = job.getTrades();
            logger.debug("Deleting job "+jobId+" with "+col.size()+" trades");
            Iterator<TradeImpl> it = col.iterator();
            while (it.hasNext()) {
                Transaction t = getSession().beginTransaction();
                TradeImpl trade = it.next();
                tradeService.deleteTrade(getSession(), trade);
                it.remove();
                t.commit();
            }
            Transaction t = getSession().beginTransaction();
            getSession().delete(JobImpl.class, jobId);
            logger.debug("Deleted job "+jobId+" with "+job.getNumberOfTotalRecords()+" trades");
            t.commit();
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#findAllMgbConfigurations()
     */
    @Override
    public MgbConfigurationVo[] findAllMgbConfigurations() throws RemoteException {
        ArrayList<MgbConfigurationVo> resultArray = new ArrayList<MgbConfigurationVo>();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            Collection<MgbConfigurationImpl> col = finder.findMgbConfigurations(new MgbConfigurationSearchParams(mandant));
            logger.debug("Found " + col.size() + " mgbConfigurations.");
            Iterator<MgbConfigurationImpl> it = col.iterator();
            while (it.hasNext()) {
                MgbConfigurationImpl mgbConfiguration = it.next();
                MgbConfigurationVo result = VoFactory.createMgbConfigurationVo(mgbConfiguration);
                Iterator<EmployeeImpl> iter = (new DualControlJobFinderImpl(getSession())).findPendingDualControlJobsEntryRequester(mgbConfiguration).iterator();
                while (iter.hasNext()) {
                    result.setChangePending(true);
                    EmployeeImpl requester = iter.next();
                    if (requester != null) {
                        result.setChangeRequestedByName(requester.getFullName());
                    }
                }
                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return resultArray.toArray(new MgbConfigurationVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#saveMgbConfiguration(Object)
     */
    @Override
    public void saveMgbConfiguration(MgbConfigurationVo mgbConfigurationVo) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();

            MgbConfigurationIdImpl id = new MgbConfigurationIdImpl(mandant, mgbConfigurationVo.getKey());

            MgbConfigurationImpl mgbConfiguration = null;

            if (mgbConfigurationVo.isCreateNew()) {
                if (performFourEyeSave) {
                    mgbConfiguration = new MgbConfigurationImpl(id);
                } else {
                    mgbConfiguration = (MgbConfigurationImpl) getSession().create(MgbConfigurationImpl.class);
                    mgbConfiguration.setMgbConfigurationId(id);
                }
            } else {
                mgbConfiguration = (MgbConfigurationImpl) getSession().select(MgbConfigurationImpl.class, id);
            }

            mgbConfiguration.setValue(mgbConfigurationVo.getValue());

            if (performFourEyeSave) {
                DualControlJobProcessorImpl processor = new DualControlJobProcessorImpl();
                t.rollback();
                closeSession();
                session = StoreSingleton.getUniqueInstance().openSession(mandant, user.getNtId());
                if (mgbConfigurationVo.isCreateNew()) {
                    processor.insert(session, mgbConfiguration, user);
                } else {
                    processor.update(session, mgbConfiguration, user);
                }
            } else {
                getSession().save(mgbConfiguration);
                t.commit();
            }
        } catch (Exception e) {
            throwRemoteException(e);
        }
    }

    /*
     * (Kein Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#getUserStatistic()
     */
    @Override
    public UserStatisticVo[] getUserStatistic() throws RemoteException {
        ArrayList<UserStatisticVo> list = new ArrayList<UserStatisticVo>();
        ApplicationStatisticHolder statistic = ApplicationStatisticHolder.getInstance();
        Iterator<UserStatisticHolder> iterator = statistic.getUserStatistic().iterator();
        while (iterator.hasNext()) {
            UserStatisticHolder userStat = iterator.next();
            UserStatisticVo userStatVo = new UserStatisticVo();
            String ntId = SecurityUtils.extractUserFromDomainUser(userStat.getUserId());
            GregorianCalendar sessionOpenTime = new GregorianCalendar();
            sessionOpenTime.setTimeInMillis(userStat.getLatestSessionCreationTime());
            userStatVo.setLatestSessionCreateTime(sessionOpenTime);

            if (userStat.getLatestSessionDestroyTime() > 0) {
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTimeInMillis(userStat.getLatestSessionDestroyTime());
                userStatVo.setLatestSessionDestroyTime(cal);
            }
            userStatVo.setUserId(ntId);
            userStatVo.setSessionCount(userStat.getSessionCount());
            userStatVo.setActiveSessionCount(userStat.getActiveSessionCount());

            if (ntId != null) {
                try {
                    List<EmployeeImpl> employees = (List<EmployeeImpl>)getSession().select(EmployeeImpl.class, "ntId", ntId.toLowerCase());
                    boolean found = false;
                    Iterator<EmployeeImpl> employeeIterator = employees.iterator();
                    if (employeeIterator.hasNext()) {
                        EmployeeImpl employee = employeeIterator.next();
                        userStatVo.setUserName(employee.getFullName());
                        found = true;
                    }
                    if (!found) {
                        List<EmployeeImpl> employees1 = (List<EmployeeImpl>)getSession().select(EmployeeImpl.class, "ntId", ntId.toUpperCase());
                        employeeIterator = employees1.iterator();
                        if (employeeIterator.hasNext()) {
                            EmployeeImpl employee = employeeIterator.next();
                            userStatVo.setUserName(employee.getFullName());
                            found = true;
                        }
                    }
                } catch (PersistenceException e) {
                    throwRemoteException(e);
                }
            }

            list.add(userStatVo);
        }
        logger.debug("Found " + list.size() + " user statistic entries.");
        return list.toArray(new UserStatisticVo[0]);
    }


    @Override
    public TradeOverviewVo[] findTradesOverviewVo(TradeSearchParamsVo searchParams) throws RemoteException {
        ArrayList<TradeOverviewVo> resultArray = new ArrayList<TradeOverviewVo>();
        try {
            Collection<TradeImpl> col = findTradeImplCollection(searchParams);
            logger.debug("Found " + col.size() + " trade overview.");
            Iterator<TradeImpl> it = col.iterator();
            while (it.hasNext()) {
                TradeImpl trade = it.next();
                resultArray.add(getTradeOverview(trade, false));
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return resultArray.toArray(new TradeOverviewVo[0]);
    }

    /**
     * @see de.westlb.mgb.client.server.Mgb#setJobStatus(Long, String)
     */
    @Override
    public void setJobStatus(Long jobId, String statusCode) throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            JobImpl job = (JobImpl) getSession().select(JobImpl.class, jobId);
            // only jobs with no errors can be modified
            if (!job.getStatus().startsWith(JobStateDef.JOB_ERROR_STATUS) 
            		|| (job.getStatus().startsWith(JobStateDef.JOB_ERROR_STATUS) && JobStateDef.JOB_IGNORE_STATUS.equals(statusCode))
            	) {
                job.setStatus(statusCode);
                t.commit();
            }
        } catch (Exception e) {
            throwRemoteException(e);
        }
    }

    /**
     * @deprecated @see de.westlb.mgb.client.server.Mgb#getRepoTradeOverview(Object)
     */
    @Override
    public RepoTradeOverviewVo getRepoTradeOverview(Object tradeId) throws RemoteException {
        return getRepoTradeOverview(tradeId, false);
    }

    /*
     * (Kein Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#saveTradeState(java.lang.Long[],
     *      de.westlb.mgb.client.server.vo.TradeHistoryEntryVo, byte[])
     */
    @Override
    public void saveTradeState(Long[] tradeIds, TradeHistoryEntryVo tradeHistoryEntry, byte[] attachment)
            throws RemoteException {
        try {
            Transaction t = getSession().beginTransaction();
            ReportImageImpl image = saveTradeStateAttachment(tradeHistoryEntry, attachment);
            for (int i = 0; i < tradeIds.length; i++) {
                logger.debug("Saving trade state for trade " + tradeIds[i]);
                tradeHistoryEntry.setInternalTradeId(tradeIds[i]);
                saveTradeState(tradeHistoryEntry, image, true);
            }
            t.commit();
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
    }

    /*
     * (Kein Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#getMgbConfigurationValue(java.lang.String)
     */
    @Override
    public String getMgbConfigurationValue(String key) throws RemoteException {
        List<String> globalKeys = Arrays.asList(new String[]{MgbConfigurationDef.AUTO_MGB_DEFAULT_ALLOWED_CLIENT, MgbConfigurationDef.AUTO_MGB_DEFAULT_CRON});
        String value = null;
        try {
            if (globalKeys.contains(key)) {
                MgbFinderImpl finder = new MgbFinderImpl(getSession());
                MgbConfigurationSearchParams param = new MgbConfigurationSearchParams();
                param.setKey(key);
                Collection<MgbConfigurationImpl> col = finder.findMgbConfigurations(param);
                logger.debug("Found " + col.size() + " mgbConfigurations.");
                Iterator<MgbConfigurationImpl> it = col.iterator();
                if (it.hasNext()) {
                    MgbConfigurationImpl mgbConfiguration = it.next();
                    if (mgbConfiguration != null) {
                        value = mgbConfiguration.getValue();
                    }
                    if (it.hasNext()) {
                        logger.warn("Found more than one key for the global key "+key);
                    }
                }
            } else {
                MgbConfigurationIdImpl id = new MgbConfigurationIdImpl(mandant, key);
                logger.debug("id = " + id);
                MgbConfigurationImpl mgbConfiguration = (MgbConfigurationImpl) getSession().select(MgbConfigurationImpl.class, id);
                if (mgbConfiguration != null) {
                    value = mgbConfiguration.getValue();
                }
            }
        } catch (PersistenceException pe) {
            logger.warn("Unable to find a value for key "+key+" for mandant "+mandant.getCode()+ "("+pe.getMessage()+")");
        }

        return value;
    }

    /*
     * (Kein Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#setMarketDataProxyDirectory(java.lang.String)
     */
    @Override
    public void setMarketDataProxyDirectory(String path) throws RemoteException {
        // Dummy. Do nothing.
    }

    private StatisticReportParamsImpl createStatisticReportParamsImpl(StatisticReportParamsVo paramsVo)
            throws RemoteException {
        StatisticReportParamsImpl params = new StatisticReportParamsImpl();
        params.setReportName(paramsVo.getReportName());
        params.setFromDate(paramsVo.getFromDate());
        params.setToDate(paramsVo.getToDate());
        params.setMandant(mandant);
        if (paramsVo.getContext() != null) {
            StatisticContextVo[] contextVos = paramsVo.getContext();
            StatisticContextImpl[] context = new StatisticContextImpl[contextVos.length];
            for (int i = 0; i < contextVos.length; i++) {
                context[i] = new StatisticContextImpl();
                context[i].setReportName(contextVos[i].getReportName());
                context[i].setKeyValues(contextVos[i].getKeyValues());
                context[i].setAddKeyValues(contextVos[i].getAddKeyValues());
            }
            params.setContext(context);
        }

        return params;
    }

    /*
     * (Kein Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#getStatisticReport(de.westlb.mgb.client.server.vo.StatisticReportParamsVo)
     */
    @Override
    public StatisticReportVo getStatisticReport(StatisticReportParamsVo paramsVo) throws RemoteException {
        StatisticReportParamsImpl params = createStatisticReportParamsImpl(paramsVo);
        StatisticReportImpl report = null;
        try {
            report = new StatisticReportFactoryImpl(getSession()).createReport(params);
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }

        return VoFactory.createStatisticReportVo(report);
    }

    @Override
    public StatisticCubeReportVo getStatisticCubeReport(StatisticReportParamsVo paramsVo) throws RemoteException {
        StatisticReportParamsImpl params = createStatisticReportParamsImpl(paramsVo);
        StatisticCubeReportImpl report = null;
        try {
            report = new StatisticCubeReportFactoryImpl(getSession()).createReport(params);
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }

        return VoFactory.createStatisticCubeReportVo(report);
    }
    
    /*
     * (Kein Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#findAllBooks()
     */
    @Override
    public BookSearchResultEntryVo[] findAllBooks() throws RemoteException {
        ArrayList<BookSearchResultEntryVo> resultArray = new ArrayList<BookSearchResultEntryVo>();
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            Collection<BookImpl> col = finder.findBooks();
            logger.debug("Found " + col.size() + " books.");
            Iterator<BookImpl> it = col.iterator();
            while (it.hasNext()) {
                BookImpl book = it.next();
                resultArray.add(VoFactory.createBookSearchResultEntryVo(book));
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return resultArray.toArray(new BookSearchResultEntryVo[0]);
    }

    /*
     * (Kein Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#findAllSourceSystems()
     */
    @Override
    public SourceSystemVo[] findAllSourceSystems() throws RemoteException {
        ArrayList<SourceSystemVo> resultArray = new ArrayList<SourceSystemVo>();
        MgbFinderImpl finder = new MgbFinderImpl(getSession());
        try {
            Iterator<SourceSystemImpl> it = finder.findSourceSystems(null).iterator();
            while (it.hasNext()) {
                SourceSystemImpl sourceSystem = it.next();
                resultArray.add(VoFactory.createSourceSystemVo(sourceSystem));
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }

        return resultArray.toArray(new SourceSystemVo[0]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#getMail()
     */
    @Override
    public MailSearchResultEntryVo[] findMailsByTradeId(Long tradeId) throws RemoteException {
        ArrayList<MailSearchResultEntryVo> resultArray = new ArrayList<MailSearchResultEntryVo>();
        try {
            MailFinderImpl finder = new MailFinderImpl(getSession());
            Collection<MailImpl> col = finder.findMailsByTradeId(tradeId);
            logger.debug("Found " + col.size() + " mails.");
            Iterator<MailImpl> it = col.iterator();
            while (it.hasNext()) {
                MailImpl mail = it.next();
                resultArray.add(VoFactory.createMailSearchResultEntryVo(mail));
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return resultArray.toArray(new MailSearchResultEntryVo[0]);
    }

	/**
	 * 
	 * Fills the mail parameter. 
	 * 
	 * @param mail The mail to fill.
	 * @param trade The trade corresponding to the mail.
	 * @param recipient The recipient of the mail.
	 * @param sender The sender of the mail.
	 */
	private void fillMailParameter(de.westlb.mgb.client.mail.Mail mail, final SendMailParamsVo params, final TradeImpl trade, final EmployeeImpl recipient, final EmployeeImpl sender) throws PersistenceException, RemoteException {
		DateFormat dateFormat = new DateFormat(DateFormat.DATE_FORMAT);
		DateFormat timeFormat = new DateFormat(DateFormat.TIME_FORMAT);
		
		CurrencyFormat currencyFormat = new CurrencyFormat();
		currencyFormat.setMaximumFractionDigits(10);
		currencyFormat.setGroupingUsed(true);
		currencyFormat.setGroupingSize(3);
		
		GregorianCalendar tomorrow = new GregorianCalendar();
		tomorrow.add(Calendar.DAY_OF_YEAR, 1);
		MailImpl parentMail = null;
		if (params.getParentMailId() != null) {
			 parentMail = (MailImpl)getSession().select(MailImpl.class, params.getParentMailId());
		}
				
		mail.putParameter(MailDefinition.FLD_RESPONSE_TIME_TIMIT, dateFormat.format(tomorrow));  
		if (user != null) {
			mail.putParameter(MailDefinition.FLD_SND_FIRST_NAME, user.getFirstName());
			mail.putParameter(MailDefinition.FLD_SND_LAST_NAME, user.getLastName());
			mail.putParameter(MailDefinition.FLD_SND_FULL_NAME, user.getFullName());
			mail.putParameter(MailDefinition.FLD_SND_PHONE, user.getPhone());
			mail.putParameter(MailDefinition.FLD_SND_EMAIL, user.getEmail());
		}
		
		if (trade != null) {
			mail.putParameter(MailDefinition.FLD_TRD_ID,  trade.getLongId());
			mail.putParameter(MailDefinition.FLD_TRD_TRADE_ID,  trade.getTradeId());
			Object instrument = "";
			if (trade.getInstrument() != null) {
			    instrument = trade.getInstrument().getInstrumentName();
			} else if (trade.getSourceSystemInstrument() != null){
			    instrument = trade.getSourceSystemInstrument();
			}
			if (trade instanceof SummitBondImpl) {
			    instrument = ((SummitBondImpl)trade).getInstrumentName();
			}
			mail.putParameter(MailDefinition.FLD_TRD_INSTRUMENT_NAME, instrument);
            mail.putParameter(MailDefinition.FLD_TRD_COUNTERPARTY, trade.getCounterpartyId());
			mail.putParameter(MailDefinition.FLD_TRD_SOURCE_SYSTEM, trade.getSourceSystem() == null ? "" : trade.getSourceSystem().getName());
			mail.putParameter(MailDefinition.FLD_TRD_TRADE_TIME, trade.getTradeDate() == null ? "" : timeFormat.format(trade.getTradeDate()));
			mail.putParameter(MailDefinition.FLD_TRD_SYSTEM_TIME, trade.getSystemDate() == null ? "" : timeFormat.format(trade.getSystemDate()));
			mail.putParameter(MailDefinition.FLD_TRD_VOLUMNE, currencyFormat.format(new Double(trade.getVolume())));
			mail.putParameter(MailDefinition.FLD_TRD_BLOOMBERG_ID, trade.getSourceSystemBloombergId());
			mail.putParameter(MailDefinition.FLD_TRD_HAS_BLOOMBERG_ID, Boolean.valueOf(trade.getSourceSystemBloombergId() != null));
			
			String marketPrice = "";
			String tradePrice = currencyFormat.format(new Double(trade.getPrice()));
			try {
				Iterator<RequestImpl> it = trade.getRequests().iterator();
				while (it.hasNext()) {
				    RequestVo req = VoFactory.createRequestVo(it.next());
				    //TODO collect prices that are only accesible from the client
				    PriceVo price = getPrice(req);
				    if (price != null) {
				        marketPrice = currencyFormat.format(Double.valueOf(PriceUtils.calculateMidPrice(price.getMinPrice(), price.getMaxPrice())));
				    }
				}
				if (trade instanceof RepoImpl) {
					tradePrice = currencyFormat.format(Double.valueOf(((RepoImpl)trade).getRepoRate()));
				}
				if (marketPrice.length() == 0 && trade.getFrontOfficeMarketRate() != 0.0) {
                    marketPrice = currencyFormat.format(Double.valueOf(trade.getFrontOfficeMarketRate()));				    
				}
			} catch(Exception e){
			    logger.error(e.getMessage(), e);
			}
			mail.putParameter(MailDefinition.FLD_TRD_PRICE, tradePrice);
			mail.putParameter(MailDefinition.FLD_TRD_MARKET_PRICE, marketPrice);
			mail.putParameter(MailDefinition.FLD_TRD_FULL_NAME, trade.getTrader().getEmployee() == null ? "" : trade.getTrader().getEmployee().getFullName());
			mail.putParameter(MailDefinition.FLD_TRD_IS_LATE_ENTRY, Boolean.valueOf(trade.isLateDeal()));
	
			TradeHistEntryImpl manState = trade.getCurrentManualStateHistEntry(); 		
			if (manState != null) {
				mail.putParameter(MailDefinition.FLD_MAN_STATE_SHORT_DESCR,	manState.getState().getShortDescription() == null ? "" : manState.getState().getShortDescription()); 
				mail.putParameter(MailDefinition.FLD_MAN_STATE_COMMENT,	manState.getComment() == null ? "" : manState.getComment());
				mail.putParameter(MailDefinition.FLD_MAN_STATE_FULL_NAME,	manState.getCreatorsFullname());
				mail.putParameter(MailDefinition.FLD_MAN_IS_RECLAMATION, Boolean.valueOf(((ManualStateImpl)manState.getState()).isReclamationRequired()));
			}

	        TradeHistEntryImpl reclState = trade.getCurrentReclStateHistEntry();       
	        if (reclState != null) {
	            mail.putParameter(MailDefinition.FLD_RECL_STATE_SHORT_DESCR, reclState.getState().getShortDescription() == null ? "" : reclState.getState().getShortDescription()); 
	            mail.putParameter(MailDefinition.FLD_RECL_STATE_COMMENT, reclState.getComment() == null ? "" : reclState.getComment());
	            mail.putParameter(MailDefinition.FLD_RECL_STATE_FULL_NAME,   reclState.getCreatorsFullname());
	        }
		}
		
		mail.putParameter(MailDefinition.FLD_MANDANT, mandant.getCode());
		
		if (parentMail != null) {
			mail.putParameter(MailDefinition.FLD_PARENT_MAIL_SUBJECT, parentMail.getSubject());
			mail.putParameter(MailDefinition.FLD_PARENT_MAIL_TEXT, parentMail.getText());
		}
		
		if (params.getAdditionalParamNames() != null) {
			for (int i=0; i < params.getAdditionalParamNames().length; i++) {
				mail.putParameter(params.getAdditionalParamNames()[i], params.getAdditionalParamValues()[i]);
			}
		}
	}

    /*
     * (non-Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#prepareMail(de.westlb.mgb.client.server.vo.MailVo)
     */

	@Override
    public MailVo prepareMail(SendMailParamsVo params) throws RemoteException {
		MgbFinderImpl mgbFinder = new MgbFinderImpl(getSession());
		MailVo mailVo  = new MailVo();
		
		try {
			EmployeeImpl sender = null;
			if (params.getSenderEmployeeId() != null){
				sender = (EmployeeImpl)getSession().select(EmployeeImpl.class, params.getSenderEmployeeId());
			} else {
				sender = user;
	    		
			}

			TradeImpl trade  = null;
			if (params.getTradeId() != null) {
				trade  = (TradeImpl)getSession().select(TradeImpl.class, params.getTradeId());
			}
			
			// Determine recipient. If not set in params, set the trader that is assigned to the trade.
			EmployeeImpl recipient = null;
			if (params.getRecipientEmployeeId() == null && trade != null) {
				recipient = trade.getTrader().getEmployee();
				if (recipient == null) {
					throw new RemoteException("No MGB employee assigned to the trader " + trade.getTrader().getTraderCode());
				}
			} else {
				if (params.getRecipientEmployeeId() != null) {
					recipient = (EmployeeImpl)getSession().select(EmployeeImpl.class, params.getRecipientEmployeeId());
				}
				if (recipient == null) {
					throw new RemoteException("Recipient not found!" + trade.getTrader().getTraderCode());
				}
			}
						
			if (sender.getEmail() == null) {
				throw new RemoteException("Missing EMail-Address for sender!");
			}

			if (recipient.getEmail() == null) {
				throw new RemoteException("Missing EMail-Address for recipient!");
			}

			if (recipient.getNtId() == null) {
				throw new RemoteException("Missing ntid for recipient!");
			}
			

			de.westlb.mgb.client.mail.Mail mail;
			if (params.getType() != null) {
				String subjectT = mgbFinder.findMgbConfigurationValue(sender.getMandant(), "MAIL_" + params.getType() + ".SUBJECT");				
				String textT = mgbFinder.findMgbConfigurationValue(sender.getMandant(), "MAIL_" + params.getType() + ".TEXT");
				String senderT = mgbFinder.findMgbConfigurationValue(sender.getMandant(), "MAIL_" + params.getType() + ".SENDER");
				if (senderT == null || senderT.length() == 0) {
					senderT = sender.getEmail(); 
				}
				mail = new de.westlb.mgb.client.mail.Mail(senderT, subjectT, textT);
				mail.setLoadFromProperties(false);
			} else {
				mail = new de.westlb.mgb.client.mail.Mail();				
			}
	    	
			String acknowledgment = mgbFinder.findMgbConfigurationValue(sender.getMandant(), "MAIL_" + params.getType() + ".ACKNOWLEDGEMENT");
			if (acknowledgment != null && acknowledgment.equalsIgnoreCase("TRUE")) {
				mail.setAcknowledgment(sender.getEmail());
			}
			
			mail.addOriginalRecipient(recipient.getEmail());
			
			// Fill parameter map for dynamic content
			fillMailParameter(mail, params, trade, recipient, sender);

			if (params.getType() != null) {
				MailConnection mailConnection = new MailConnection();
				mailConnection.computeForms(mail);
			}

			mailVo.setCreationDate(new GregorianCalendar());
			mailVo.setParentId(params.getParentMailId());
			mailVo.setSubject(mail.getSubject());
			mailVo.setText(mail.getText());
			mailVo.setType(params.getType());
			mailVo.setAcknowledgment(mail.getAcknowledgment());

			if (!MailTypeDef.TRADER_RESPONSE.equals(params.getType())) {			
				// Default is not to add any copy recipients to a new mail. 
				EmployeeVo copyRecipients[] = new EmployeeVo[1];
				copyRecipients[0] = VoFactory.createEmployeeVo(getUser());
				mailVo.setCopyRecipients(copyRecipients);
			} else {
				if (params.getParentMailId() != null) {
					MailImpl parentMail = (MailImpl)getSession().select(MailImpl.class, params.getParentMailId());
					ArrayList<EmployeeVo> recipientList = new ArrayList<EmployeeVo>(); 
					for (Iterator<MailRecipientImpl> iter = parentMail.getRecipients().iterator(); iter.hasNext();) {
						MailRecipientImpl element = iter.next();
						if (!recipient.equals(element.getEmployee())) {
							recipientList.add( VoFactory.createEmployeeVo(element.getEmployee()) );
						}
					}
					mailVo.setCopyRecipients(recipientList.toArray(new EmployeeVo[0]) );
				}
			}
			
			mailVo.setRecipientEmployeeId(recipient.getLongId());
			mailVo.setRecipientName(recipient.getFullName());
			mailVo.setRecipientAdress(recipient.getEmail());
			mailVo.setSenderEmployeeId(sender.getLongId());
			mailVo.setSenderName(sender.getFullName());
			mailVo.setSenderAdress(mail.getSenderFormName());
			mailVo.setTradeId(params.getTradeId());
			
			if (trade != null && MailTypeDef.CONTROLLER_RECLAMATION.equals(params.getType())) {
				mailVo.setReclamationState(getDefaultReclStateVo(trade));
			}

		} catch (PersistenceException e) {
			throwRemoteException(e);
		} catch (MailException e) {
			throwRemoteException(e);
		}
		
		return mailVo;
		
	}


    /**
     * Prepares the reclamation state of the mailVo dependend on the mail type
     * and trade attributes.
     * 
     * @param trade
     *            the trade the mail is assigned to.
     * @param mailVo
     *            the mailVo to fill.
     */
    private TradeHistoryEntryVo getDefaultReclStateVo(TradeImpl trade) throws PersistenceException, RemoteException {
        TradeHistoryEntryVo stateVo = null;
        if (trade.getCurrentReclamationState() != null) {
            stateVo = new TradeHistoryEntryVo();
            ReclStateHistEntryImpl curState = trade.getCurrentReclStateHistEntry();
            stateVo.setReclamationLevel(Integer.valueOf(curState.getLevel() + 1));
            stateVo.setStateCode(curState.getState().getStateCode());
            stateVo.setComment(curState.getComment());
            if (curState.getReportImage() != null) {
                stateVo.setAttachmentId(curState.getReportImage().getLongId());
                stateVo.setAttachmentName(curState.getReportImage().getName());
            }
        } else if (trade.getCurrentManualState() != null && trade.getCurrentManualState().getReclamationState() != null) {
            // If manual state exists, take the default reclamation state that
            // is assigned to the
            // coresponding manual state code.
            stateVo = new TradeHistoryEntryVo();
            ManualStateHistEntryImpl curManState = trade.getCurrentManualStateHistEntry();
            stateVo.setStateCode(trade.getCurrentManualState().getReclamationState().getStateCode());
            stateVo.setComment(curManState.getComment());
            if (curManState.getReportImage() != null) {
                stateVo.setAttachmentId(curManState.getReportImage().getLongId());
                stateVo.setAttachmentName(curManState.getReportImage().getName());
            }
        } else if (trade.isLateDeal()) {
            stateVo = new TradeHistoryEntryVo();
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            String lateEntryCode = finder.findMgbConfigurationValue(mandant,
                    MgbConfigurationDef.LATE_ENTRY_RECLAMATION_CODE);
            if (lateEntryCode != null) {
                stateVo.setStateCode(lateEntryCode);
            }
        }

        if (stateVo != null) {
            stateVo.setInternalTradeId(trade.getLongId());
            stateVo.setType(StateCodeTypeDef.RECLAMATION);
            stateVo.setReclamationLevel(Integer.valueOf(trade.getCurrentReclLevel() + 1));
        }

        return stateVo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#sendMail(de.westlb.mgb.client.server.vo.MailVo)
     */
    @Override
    public void sendMail(MailVo mailVo, byte[] reclAttachmContent) throws RemoteException {
        try {
            // Load the sender and recipient from DB and check that mail
            // adresses exist.
            EmployeeImpl recipient = (EmployeeImpl) getSession().select(EmployeeImpl.class, mailVo.getRecipientEmployeeId());
            EmployeeVo[] copyRecpients = mailVo.getCopyRecipients();
            if (recipient.getEmail() == null) { throw new RemoteException("Missing EMail-Address for reccipient!"); }
            EmployeeImpl sender = null;
            if (mailVo.getSenderEmployeeId() != null) {
                sender = (EmployeeImpl) getSession().select(EmployeeImpl.class, mailVo.getSenderEmployeeId());
            } else {
                sender = user;

            }
            if (sender.getEmail() == null) { throw new RemoteException("Missing EMail-Address for sender!"); }

            // Save Mail into database first, because we need the mail id for
            // the url
            // in the mail text.
            Transaction t = getSession().beginTransaction();
            MailImpl dbMail = new MailImpl();
            dbMail.setCreationDate(new GregorianCalendar());
            getSession().save(dbMail);

            // Set recipient and store recipient as well to list of recpients
            dbMail.setReceiver(recipient);
            MailRecipientImpl mailRecipient = (MailRecipientImpl)getSession().create(MailRecipientImpl.class);
            mailRecipient.setEmployee(recipient);
            dbMail.addRecipient(mailRecipient);
//            dbMail.addRecipient(recipient);

            dbMail.setSender(sender);
            dbMail.setSubject(mailVo.getSubject());
            dbMail.setType(mailVo.getType());

 
            int newLevel = 1;
            if (mailVo.getTradeId() != null) {
                TradeImpl trade = (TradeImpl) getSession().select(TradeImpl.class, mailVo.getTradeId());
//                TradeImpl trade = (TradeImpl) getSession().select(AbstractFinder.getTradeClassForMandantCode(mandant.getCode()), mailVo.getTradeId());
                dbMail.setTrade(trade);
                if (trade.getHistoryEntries() != null && mailVo.getType() != null && mailVo.getType().startsWith(MailTypeDef.CONTROLLER_PREFIX)) {
                	for (Iterator<TradeHistEntryImpl> iter = trade.getHistoryEntries().iterator(); iter.hasNext();) {
						TradeHistEntryImpl element = iter.next();
						if (element instanceof ReclStateHistEntryImpl) {
		                	newLevel = ((ReclStateHistEntryImpl)element).getLevel() + 1;
		                	((ReclStateHistEntryImpl)element).setLevel(newLevel);
						}
					}
                	getSession().update(trade);
                }
            }
            
            if (mailVo.getReclamationState() != null) {
            	TradeHistoryEntryVo reclStateVo = mailVo.getReclamationState();
            	reclStateVo.setReclamationLevel(Integer.valueOf(newLevel));
                saveTradeState(reclStateVo, reclAttachmContent, true);
            }

            if (mailVo.getParentId() != null) {
                MailImpl parentMail = (MailImpl) getSession().select(MailImpl.class, mailVo.getParentId());
                dbMail.setParent(parentMail);
            }

            getSession().update(dbMail);

            if (copyRecpients != null) {
                for (int i = 0; i < copyRecpients.length; i++) {
                    EmployeeImpl empyloyee = (EmployeeImpl) getSession().select(EmployeeImpl.class, copyRecpients[i]
                            .getEmployeeId());
                    MailCopyRecipientImpl mailCopyRecipient = (MailCopyRecipientImpl)getSession().create(MailCopyRecipientImpl.class);
                    mailCopyRecipient.setEmployee(empyloyee);
                    dbMail.addRecipient(mailCopyRecipient);
//                    dbMail.addCopyRecipient(empyloyee);
                }
            }
            getSession().update(dbMail);

            // Now prepare the mail for sending. Replace URL variable in mail
            // text.
            String mailText = mailVo.getText().replaceAll("<#~mailId~#>", dbMail.getLongId().toString());

            de.westlb.mgb.client.mail.Mail mail = new de.westlb.mgb.client.mail.Mail();
            mail.addOriginalRecipient(recipient.getEmail());

            if (copyRecpients != null) {
                for (int i = 0; i < copyRecpients.length; i++) {
                    mail.addCopyRecipient(copyRecpients[i].getEmail());
                }
            }

            
            mail.setSubject(mailVo.getSubject());
            mail.setText(mailText);
           	mail.setSender(mailVo.getSenderAdress());
           	mail.setAcknowledgment(mailVo.getAcknowledgment());

            // Update mailtext in DB
            dbMail.setText(mailText);
            getSession().update(dbMail);

            MailConnection mailConnection = new MailConnection();
            mailConnection.sendMail(mail);

            t.commit();
        } catch (PersistenceException e) {
            throwRemoteException(e);
        } catch (MailException e) {
            throwRemoteException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#getMail(long)
     */
    @Override
    public MailVo getMail(long mailId) throws RemoteException {
        MailVo mailVo = null;
        try {
            MailImpl mail = (MailImpl) getSession().select(MailImpl.class, Long.valueOf(mailId));
            mailVo = VoFactory.createMailVo(mail);
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return mailVo;
    }

    public Collection<AccessControlImpl> findAllAccessControls() throws RemoteException {
        MgbFinderImpl finder = new MgbFinderImpl(getSession());
        try {
            return finder.findAccessControls(null);
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return null;
    }

    /**
     * Returns the user.
     * 
     * @return EmployeeImpl
     */
    public EmployeeImpl getUser() {
        return user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#findMailsForReclamations()
     */
    @Override
    public InboxMailVo[] findTraderInboxMails() throws RemoteException {
        MailFinderImpl finder = new MailFinderImpl(getSession());
        MailSearchParams params = new MailSearchParams();
        params.setRecipientNtId(user.getNtId());
        params.setReclamationOpenOnly(true);

        ArrayList<InboxMailVo> resultList = new ArrayList<InboxMailVo>();
        try {
            Collection<MailImpl> mails = finder.findMails(params);
            Iterator<MailImpl> iterator = mails.iterator();
            while (iterator.hasNext()) {
                MailImpl mail = iterator.next();
                if (MailTypeDef.TRADER_RESPONSE.equals(mail.getType())) {
                    continue;
                }
                resultList.add(VoFactory.createInboxMailVo(mail));
            }
        } catch (PersistenceException pE) {
            throwRemoteException(pE);
        }

        return resultList.toArray(new InboxMailVo[0]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#sendTraderResponseMail(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public void sendTraderResponseMail(Long parentMailId, String responseText) throws RemoteException {
        MandantImpl oldMandant = mandant;
        try {
            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            // Get Parent mail from DB
            MailImpl parentMail = (MailImpl) getSession().select(MailImpl.class, parentMailId);
            EmployeeImpl ctrlEmp = parentMail.getSender();
            EmployeeImpl trdEmpl = finder.findFirstEmployeeWithNtId(user.getNtId(), parentMail.getTrade().getMandant(), true);
            if (trdEmpl == null) {
                logger.error("Unable to send mail: user "+user.getNtId()+" not found for a trade from client "+parentMail.getTrade().getMandant());
                throw new RemoteException("Unable to send mail: user "+user.getNtId()+" not found for a trade from client "+parentMail.getTrade().getMandant());
            }
            // Sets the current mandant to avoid wrong mandant code in created
            // data records.
            mandant = parentMail.getTrade().getMandant();

            // Prepate email to send to controller
            SendMailParamsVo sendMailParamsVo = new SendMailParamsVo();
            sendMailParamsVo.setParentMailId(parentMailId);
            if (parentMail.getTrade() != null) {
                sendMailParamsVo.setTradeId(parentMail.getTrade().getLongId());
            }
            sendMailParamsVo.setSenderEmployeeId(trdEmpl.getLongId());
            sendMailParamsVo.setRecipientEmployeeId(ctrlEmp.getLongId());
            sendMailParamsVo.setType(MailTypeDef.TRADER_RESPONSE);
            sendMailParamsVo.setAdditionalParamNames(new String[] { "traderResponse"});
            sendMailParamsVo.setAdditionalParamValues(new String[] { responseText});
            MailVo mailVo = prepareMail(sendMailParamsVo);
            sendMail(mailVo, null);
        } catch (PersistenceException pE) {
            throwRemoteException(pE);
        } finally {
            mandant = oldMandant;
        }
    }

    @Override
    public void runAndSaveAutomaticCheck(PriceVo[] prices) throws RemoteException {
        try {
            HashMap<String, Object> configMap = buildConfigMap();
            
            HashMap<String, MarketDataRequestable> requestConverterMap = new HashMap<String, MarketDataRequestable>(); 

            StatusCalculatorImpl statusCalculator = new StatusCalculatorImpl();
            HashMap<Long, PriceVo> priceMap = new HashMap<Long, PriceVo>();
            for (int i = 0; i < prices.length; i++) {
                if (prices[i] != null) {
                    if (prices[i].getPriceDate() != null) {
                        priceMap.put(prices[i].getRequestId(), prices[i]);
                    } else {
                        priceMap.put(prices[i].getRequestId(), null);                        
                    }
                }
            }

            if(priceMap.size()==0)
            {
                logger.debug("No requests to check, returning");
                return;
            }
            
            logger.debug("Checking "+priceMap.size()+" requests");
            
            // fetch the trades with an explicit fetch to receive the full object but not only a hibernate proxy
            Query query = getSession().createQuery("select r from "+RequestImpl.class.getName()+" r join fetch r.trade where r.id in (:rids) ");
            query.setParameterList("rids", priceMap.keySet());
            Iterator requestIter = query.list().iterator();
            while (requestIter.hasNext()) {
                Transaction t = getSession().beginTransaction();
                RequestImpl request = (RequestImpl) requestIter.next();
                TradeImpl trade = request.getTrade();
                trade.setAutoCheckConfig(configMap);
                Set<String> requestTypes = new HashSet<String>(); 
                Iterator<RequestImpl> requests = trade.getExternalRequests().iterator();
                while (requests.hasNext()) {
                    RequestImpl externalRequest = requests.next();
                    if (priceMap.get(externalRequest.getLongId()) != null) {
                        trade.addAutoCheckPrices(priceMap.get(externalRequest.getLongId()));
                        priceMap.put(externalRequest.getLongId(), null);
                    }
                    if (externalRequest.getRequestType() != null) {
                        requestTypes.add(externalRequest.getRequestType());
                    }
                }

                statusCalculator.loadConfiguration(getSession(), trade.getSourceSystem(), StateCodeTypeDef.SECOND_STAGE);

                AutomaticStateImpl newAutoState = statusCalculator.calculateStatus(trade, trade.getMandant());
                AutoStateHistEntryImpl newAutoHistEntry = (AutoStateHistEntryImpl) getSession().create(AutoStateHistEntryImpl.class);
                newAutoHistEntry.setAutomaticState(newAutoState);
                newAutoHistEntry.setStateTime(new GregorianCalendar());
                newAutoHistEntry.setTrade(trade);
                String comment = statusCalculator.getLastInternalCalculatorStateComment();
                if (comment != null) {
                    newAutoHistEntry.setComment(comment.substring(0, Math.min(TradeHistEntryImpl.COMMENT_MAX_LENGTH, comment.length())));
                }
                trade.setCurrentStateHistEntry(newAutoHistEntry);
                getSession().update(trade);
                
                // Add a request if the autoState expects one that does not exist
                if (newAutoState.getMarketDataRequestType() != null && !requestTypes.contains(newAutoState.getMarketDataRequestType())) {
                    if (!requestConverterMap.containsKey(trade.getSourceSystem().getCode())) {
                        MgbConverter requestConverter = MgbConverterFactory.getMgbConverter(trade.getSourceSystem());
                        if (requestConverter instanceof MarketDataRequestable) {
                            ((MarketDataRequestable)requestConverter).initRequester(trade.getMandant());
                            requestConverterMap.put(trade.getSourceSystem().getCode(), (MarketDataRequestable)requestConverter);
                        } else {
                            throw new ConverterException("The converter "+requestConverter.getClass()+" is not an MarketDataRequestable");
                        }
                    }
                    trade.addRequest(requestConverterMap.get(trade.getSourceSystem().getCode()).buildRequest(getSession(), trade, newAutoState.getMarketDataRequestType()));
                    getSession().update(trade);
                }
                
                if ( (request.getRequestState() != null && request.getRequestState().startsWith(MarketDataRequestStateDef.ERROR)) || request.getPriceResult() == null ) {
                    // the current request has no price or has an error-state
                    if (request.getRequestType() != null && !request.getRequestType().equals(newAutoState.getMarketDataRequestType())) {
                        // the new state is not forcing the current request to be queried again, so it is set to a final OK_NO_PRICE-state 
                        request.setRequestState(MarketDataRequestStateDef.OK_NO_PRICE);
                    } else {
                        request.setRequestState(MarketDataRequestStateDef.RETRY);                        
                    }
                } else {
                    request.setRequestState(MarketDataRequestStateDef.OK);                      
                }
                getSession().update(request);
                t.commit();
            }
        } catch (PersistenceException pE) {
            throwRemoteException(pE);
        } catch (ConverterException e) {
            throwRemoteException(e);
        }
    }


    private HashMap<String, Object> buildConfigMap() throws RemoteException {
        HashMap<String, Object> configMap = new HashMap<String, Object>();
        MgbFinderImpl finder = new MgbFinderImpl(getSession());

        Double bloombergPriceToleranceDeviationFactor = null;
        try {
            bloombergPriceToleranceDeviationFactor = new Double(Double.parseDouble(finder.findMgbConfigurationValue(
                    mandant, MgbConfigurationDef.BLOOMBERG_INTERVAL_PRICE_DEVIATION_TOLERANCE_FACTOR)));
        } catch (Exception e) {
            bloombergPriceToleranceDeviationFactor = new Double(1.0);
        }
        configMap.put(MgbConfigurationDef.BLOOMBERG_INTERVAL_PRICE_DEVIATION_TOLERANCE_FACTOR,
                bloombergPriceToleranceDeviationFactor);

        return configMap;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#findReclRequiredListEntries()
     */
    @Override
    public TradeReclRequiredVo[] findTradesReclRequired() throws RemoteException {
        MgbFinderImpl finder = new MgbFinderImpl(getSession());
        TradeReclRequiredVo[] result = null;
        try {
            result = finder.findReclRequiredTradeVos(selectedJobs);
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }

        return result;
    }

    /**
     * Sends mails using one transaction per mail.
     * 
     * @see de.westlb.mgb.client.server.Mgb#prepareAndSendMail(de.westlb.mgb.client.server.vo.SendMailParamsVo[])
     */
    @Override
    public void prepareAndSendMail(SendMailParamsVo[] paramsArray) throws RemoteException {
        for (int i = 0; i < paramsArray.length; i++) {
            MailVo mailVo = prepareMail(paramsArray[i]);
            sendMail(mailVo, null);
        }
    }

    /**
     * 
     *  
     */
    @Override
    public TradeSearchResultEntryVo[] findTradesForStatisticRow(StatisticReportParamsVo paramsVo)
            throws RemoteException {
        ArrayList<TradeSearchResultEntryVo> resultArray = new ArrayList<TradeSearchResultEntryVo>();
        try {
            StatisticReportParamsImpl params = createStatisticReportParamsImpl(paramsVo);

            Collection<TradeImpl> col = new StatisticReportFactoryImpl(getSession()).getTradesForStatisticRow(params);
            logger.debug("Found " + col.size() + " trades.");
            Iterator<TradeImpl> it = col.iterator();
            while (it.hasNext()) {
                TradeImpl trade = it.next();
                TradeSearchResultEntryVo result = VoFactory.createTradeSearchResultEntryVo(trade);
                resultArray.add(result);
            }
        } catch (PersistenceException pE) {
            throwRemoteException(pE);
        }
        return resultArray.toArray(new TradeSearchResultEntryVo[resultArray.size()]);
    }

    @Override
    public void saveReclStateAfterEmployeeReport(Long employeeId) throws RemoteException {
        MgbFinderImpl finder = new MgbFinderImpl(getSession());
        TradeSearchParams param;
        try {
            EmployeeImpl employee = (EmployeeImpl) getSession().select(EmployeeImpl.class, employeeId);
            if (employee == null) { throw new RemoteException("Employee with id " + employeeId + " not found!"); }

            Transaction t = getSession().beginTransaction();
                param = createCurrentTradeSearchParam();
                param.setEmployee(employee);
                param.setIsReclamationRequiredButNotHandled(Boolean.TRUE);
                Collection<TradeImpl> col = finder.findTrades(param);
                logger.debug("Found " + col.size() + " trades.");

                Iterator<TradeImpl> it = col.iterator();
                while (it.hasNext()) {
                    TradeImpl trade = it.next();
                    TradeHistoryEntryVo vo = getDefaultReclStateVo(trade);
                    if (vo == null) {
                        t.rollback();
                        throw new RemoteException("Bad setup of state codes. Can not determine reclamation code!");
                    }
                    saveTradeState(vo, (byte[])null, true);
                }
            t.commit();
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#isTradeOwned(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public boolean isTradeOwned(Long tradeId, String userId) throws RemoteException {
        if (userId == null) { return false; }

        boolean isOwned = false;
        try {
            TradeImpl trade = (TradeImpl) getSession().select(TradeImpl.class, tradeId);
//            TradeImpl trade = (TradeImpl) getSession().select(AbstractFinder.getTradeClassForMandantCode(mandant.getCode()), tradeId);
            if (trade.getTrader() != null && trade.getTrader().getEmployee() != null) {
                EmployeeImpl tradeEmp = trade.getTrader().getEmployee();
                isOwned = userId.equalsIgnoreCase((tradeEmp.getNtId()));
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }

        return isOwned;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#isTradeAccessGranted(java.lang.Long,
     *      java.lang.String)
     */
    @Override
    public boolean isTradeAccessGranted(Long tradeId, String ntId) throws RemoteException {
        if (ntId == null) { return false; }

        MailFinderImpl finder = new MailFinderImpl(getSession());
        boolean isGranted = false;
        try {
            TradeImpl trade = (TradeImpl) getSession().select(TradeImpl.class, tradeId);
            // 1. Check if user is the assigned trader for this trade
            if (trade.getTrader() != null && trade.getTrader().getEmployee() != null) {
                EmployeeImpl tradeEmp = trade.getTrader().getEmployee();
                isGranted = ntId.equalsIgnoreCase((tradeEmp.getNtId()));
            }
            // 2. Check if users is recipient of an trade mail. In this case
            // he is invited to join the reclamation workflow.
            if (!isGranted) {
                MailSearchParams searchParams = new MailSearchParams();
                searchParams.setTradeId(tradeId);
                isGranted = finder.findMails(searchParams).size() > 0;
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }

        return isGranted;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.westlb.mgb.client.server.Mgb#findSuccessorMails(java.lang.String,
     *      boolean, java.lang.String[])
     */
    @Override
    public MailVo[] getChildMails(Long mailId, boolean recurse, String[] mailTypes) throws RemoteException {
        Collection<MailImpl> successors = null;
        ArrayList<MailVo> result = new ArrayList<MailVo>();
        try {
            MailImpl mail = (MailImpl)getSession().select(MailImpl.class, mailId);
            successors = mail.getChilds(recurse, mailTypes);
            Iterator<MailImpl> it = successors.iterator();
            while (it.hasNext()) {
                result.add(VoFactory.createMailVo(it.next()));
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }

        return result.toArray(new MailVo[result.size()]);
    }

	/* (non-Javadoc)
	 * @see de.westlb.mgb.client.server.Mgb#findTradesOverviewVosForStatisticRow(de.westlb.mgb.client.server.vo.StatisticReportParamsVo)
	 */
	@Override
    public TradeOverviewVo[] findTradeOverviewVosForStatisticRow(StatisticReportParamsVo paramsVo) throws RemoteException {
		TradeSearchResultEntryVo[] searchResult = findTradesForStatisticRow(paramsVo);
		 TradeOverviewVo[] tradeOverviewVos = new TradeOverviewVo[searchResult.length];
		 for (int i = 0; i < searchResult.length; i++) {
			 tradeOverviewVos[i] = getTradeOverview(searchResult[i].getId(), true);
		 }

		 return tradeOverviewVos;
	}    
	       
    
	/**
	 * @see de.westlb.mgb.client.server.Mgb#getStornoGroupFromTrade(java.lang.Long)
	 */
	@Override
    public Boolean getStornoGroupFromTrade(Object tradeId) throws RemoteException {
	    if (tradeId != null) {
    		try {
                TradeImpl trade = (TradeImpl) getSession().select(TradeImpl.class, (Serializable)tradeId);
    			return Boolean.valueOf(trade.isTradeGroup()); 
    		} catch (PersistenceException e) {
    			throwRemoteException(e);
    	    }
	    }
		return Boolean.FALSE;
	}
	
	/**
	 * @see de.westlb.mgb.client.server.Mgb#getMgbTask(java.lang.Long)
	 */
	@Override
    public MgbTaskVo getMgbTask(Long taskId) throws RemoteException {
		MgbTaskVo result = new MgbTaskVo();
        try {
        	if (taskId != null) {
        		MgbTaskImpl task = (MgbTaskImpl) getSession().select(MgbTaskImpl.class, taskId);
        		result = VoFactory.createMgbTaskVo(task);
        	}
        } catch (Exception e) {
            throwRemoteException(e);
        }
        return result;
	}
	
	/* (non-Javadoc)
	 * @see de.westlb.mgb.client.server.Mgb#saveMgbTask(de.westlb.mgb.client.server.vo.MgbTaskVo)
	 */
	@Override
    public Long saveMgbTask(MgbTaskVo mgbTask) throws RemoteException {
		try {
			logger.debug("Saving task");
			Transaction t = getSession().beginTransaction();
			MgbTaskImpl newMgbTask = null;
			if (mgbTask.getTaskId() != null) {
				newMgbTask = (MgbTaskImpl) getSession().select(MgbTaskImpl.class, mgbTask.getTaskId());
			} else {
				newMgbTask = (MgbTaskImpl) getSession().create(MgbTaskImpl.class);
			}
			newMgbTask.setClient(mgbTask.getClient());
			if (mgbTask.getMessage() != null) {
			    newMgbTask.setMessage(mgbTask.getMessage().substring(0,Math.min(mgbTask.getMessage().length(), MgbTaskImpl.MESSAGE_MAX_LENGTH)));
			}
			if (mgbTask.getStartTime() != null) {
				// ensure that the calendar is set to the local timezone, to save a consistant date in the DB
				Calendar startTime = (Calendar)mgbTask.getStartTime().clone();
            	startTime.setTimeZone(TimeZone.getDefault());
            	newMgbTask.setStartTime(startTime);
			}
			newMgbTask.setState(mgbTask.getState());
			
            if (mgbTask.getStopTime() != null) {
                // ensure that the calendar is set to the local timezone, to save a consistant date in the DB
            	Calendar stopTime = (Calendar)mgbTask.getStopTime().clone();
            	stopTime.setTimeZone(TimeZone.getDefault());
            	newMgbTask.setStopTime(stopTime);
            }
			newMgbTask.setTaskName(mgbTask.getTaskName());
			newMgbTask.setThreadName(mgbTask.getThreadName());
			newMgbTask.setUser(mgbTask.getUser());
			t.commit();
			return newMgbTask.getLongId();
        } catch (Exception e) {
        	throwRemoteException(e);
        }
		return null;
	}

    @Override
    public PriceDeviationVo[] findPriceDeviations(TradeSearchParamsVo searchParams) throws java.rmi.RemoteException {
        ArrayList<PriceDeviationVo> resultArray = new ArrayList<PriceDeviationVo>();
        try {

            TradeSearchParams param = new TradeSearchParams();

            if (searchParams.getMandantCode() != null) {
                MandantImpl man = new MandantImpl(searchParams.getMandantCode());
                param.setMandant(man);
                if (searchParams.getStateCode() != null) {
                    param.setStateId(new StateIdImpl(man, searchParams.getStateCode()));
                }
            }
            param.setFromDate(searchParams.getFromDate());
            param.setToDate(searchParams.getToDate());
            param.setJobIds(searchParams.getJobIds());
            param.setTradeIds(searchParams.getTradeIds());

            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            Collection<TradeImpl> col = finder.findTrades(param);
            logger.debug("Found " + col.size() + " price deviations.");
            Iterator<TradeImpl> it = col.iterator();
            while (it.hasNext()) {
                TradeImpl trade = it.next();
                PriceDeviationVo result = VoFactory.createPriceDeviationVo(trade);
                resultArray.add(result);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return resultArray.toArray(new PriceDeviationVo[0]);
    }

    @Override
    public PriceDeviationStatisticVo[] getPriceDeviationStatistic(TradeSearchParamsVo searchParams) throws java.rmi.RemoteException {
        ArrayList<PriceDeviationStatisticVo> resultArray = new ArrayList<PriceDeviationStatisticVo>();
        try {

            InstrumentSearchParams instumentCategorySearchParam = new InstrumentSearchParams();
            instumentCategorySearchParam.setOnlyPriceCheckInstruments(true);
            String mandantString = mandant.getCode(); 
            if (searchParams.getMandantCode() != null) {
            	mandantString = searchParams.getMandantCode();
                MandantImpl man = new MandantImpl(searchParams.getMandantCode());
                instumentCategorySearchParam.setMandant(man);
            }
            MgbFinderImpl finder = new MgbFinderImpl(getSession());

            String jobArray = "";
            if (searchParams.getJobIds().length > 0) {
            	jobArray = searchParams.getJobIds()[0].toString();
            	for (int i = 1; i < searchParams.getJobIds().length; i++) {
            		jobArray = jobArray + "," + searchParams.getJobIds()[i];
            	}
            }
            
            String product = null;
            Collection<MandantImpl> mandants = finder.findMandants(true);
            for (MandantImpl mandant : mandants) {
            	if (mandantString.equals(mandant.getCode()))
            	{
            		product = mandant.getProductClass();
            	}
            }
            
            StringBuffer sb = new StringBuffer();
            if (MandantDef.PRODUCT_BOND.equals(product)) {
                sb = new StringBuffer();
                sb.append("SELECT bndcategory, categ, pricecheckcategory, tolerance, COUNT(tid) AS tradeCount, SUM(exceeded) AS exceededCount ");
                sb.append("FROM ( ");
                sb.append("   SELECT ");
                sb.append("	   trd.t02_id as tid,  ");
                sb.append("	   bnd.t57_instrument_name AS instrument,  ");
                sb.append("	   inst.t05_instrument_name AS categ,  ");
                sb.append("	   bnd.t57_category AS bndcategory, ");
                sb.append("    cat.t11_name AS pricecheckcategory, ");
                sb.append("    cat.t11_tolerance_percent AS tolerance, ");
                sb.append("    cat.t11_tolerance_absolute AS toleranceabsolute, ");
                sb.append("	   bnd.t57_theor_price AS theorprice, ");
                sb.append("	   trd.t02_price AS tradeprice, ");
                sb.append("	   bnd.t57_theor_price - trd.t02_price AS theordiff, ");
                sb.append("	   NVL(prc.t12_price, prc.t12_price_min) AS blbprice, ");
                sb.append("	   NVL(prc.t12_price, prc.t12_price_min) - trd.t02_price AS blbdiff, ");
                sb.append("	   NVL(nvl(prc.t12_price, prc.t12_price_min) - trd.t02_price, bnd.t57_theor_price - trd.t02_price) AS actualdiff, ");
                sb.append("	   DECODE( SIGN(GREATEST(0, cat.t11_tolerance_percent - ABS(NVL(NVL(prc.t12_price, prc.t12_price_min) - trd.t02_price, bnd.t57_theor_price - trd.t02_price)) )), 1, 0, 1) AS exceeded ");
                sb.append("  FROM t06_request req, ");
                sb.append("       t02_trade trd, ");
                sb.append("	      t12_price prc, ");
                sb.append("       t05_instrument inst, ");
                sb.append("       t11_price_check_category cat, ");
                sb.append("       t57_summit_bond bnd ");
                sb.append(" WHERE req.fk_t06_t02_trade = trd.t02_id ");
                sb.append("   AND req.fk_t06_t12_price_result = prc.t12_id(+) ");
                sb.append("   AND trd.fk_t02_t08_job in ("+ jobArray +") ");
                sb.append("   AND trd.fk_t02_t05_instrument = inst.t05_id ");
                sb.append("   AND inst.fk_t05_t11_price_check = cat.t11_id  ");
                sb.append("   AND bnd.t57_id = trd.t02_id ");
                sb.append("   AND t06_type = 'BLB' ");
                sb.append("UNION ");
                sb.append("   SELECT  ");
                sb.append("    trd.t02_id AS tid,  ");
                sb.append("	   bnd.t57_instrument_name AS instrument,  ");
                sb.append("	   inst.t05_instrument_name AS categ,  ");
                sb.append("	   bnd.t57_category AS bndcategory, ");
                sb.append("    cat.t11_name AS pricecheckcategory, ");
                sb.append("    cat.t11_tolerance_percent AS tolerance, ");
                sb.append("    cat.t11_tolerance_absolute AS toleranceabsolute, ");
                sb.append("	   bnd.t57_theor_price AS theorprice, ");
                sb.append("	   trd.t02_price AS tradeprice, ");
                sb.append("	   bnd.t57_theor_price - trd.t02_price AS theordiff, ");
                sb.append("	   NULL AS blbprice, ");
                sb.append("	   NULL AS blbdiff, ");
                sb.append("	   (bnd.t57_theor_price - trd.t02_price) AS actualdiff, ");
                sb.append("	   DECODE( SIGN(GREATEST(0, cat.t11_tolerance_percent - ABS(bnd.t57_theor_price - trd.t02_price) )), 1, 0, 1) AS exceeded ");
                sb.append("  FROM t02_trade trd, ");
                sb.append("       t05_instrument inst, ");
                sb.append("       t11_price_check_category cat, ");
                sb.append("	      t57_summit_bond bnd ");
                sb.append(" WHERE trd.fk_t02_t08_job IN ("+ jobArray +") ");
                sb.append("   AND trd.fk_t02_t05_instrument = inst.t05_id ");
                sb.append("   AND inst.fk_t05_t11_price_check = cat.t11_id  ");
                sb.append("   AND bnd.t57_id = trd.t02_id ");
                sb.append("   AND NOT EXISTS (SELECT 'x' FROM t06_request req where req.fk_t06_t02_trade = trd.t02_id AND t06_type = 'BLB') ");
                sb.append(") ");
                sb.append("GROUP BY bndcategory, categ, pricecheckcategory, tolerance ");
                sb.append("ORDER BY categ ");
            } else if (MandantDef.PRODUCT_REPO.equals(product)) {
                sb = new StringBuffer();
                sb.append("SELECT bndcategory, categ, pricecheckcategory, tolerance, COUNT(tid) AS tradecount, SUM(exceeded) AS exceededcount ");
                sb.append("FROM ( ");
                sb.append("   SELECT ");
                sb.append("    trd.t02_id as tid, ");
                sb.append("    decode(inst.t05_instrument_name, 'SECB', 'Exceed criteria not valid for this category',  'SECL', 'Exceed criteria not valid for this category',  'BILATERAL', 'Exceed criteria not valid for this category', null) AS bndcategory, ");
                sb.append("    inst.t05_instrument_name AS categ, ");
                sb.append("    cat.t11_name AS pricecheckcategory, ");
                sb.append("    cat.t11_tolerance_percent AS tolerancepercent, ");
                sb.append("    cat.t11_tolerance_absolute AS tolerance, ");
                sb.append("    rep.t26_repo_rate AS rate, ");
                sb.append("    rep.t26_yield_curve_rate AS marketrate, ");
                sb.append("    rep.t26_rate_diff AS diff, ");
                sb.append("    DECODE( SIGN(GREATEST(0, cat.t11_tolerance_absolute - ABS(rep.t26_rate_diff) )), 1, 0, 1) AS exceeded ");
                sb.append(" FROM t02_trade trd, ");
                sb.append("      t05_instrument inst, ");
                sb.append("      t11_price_check_category cat, ");
                sb.append("      t26_repo rep ");
                sb.append(" WHERE trd.fk_t02_t08_job IN ("+ jobArray +") ");
                sb.append("   AND trd.fk_t02_t05_instrument = inst.t05_id ");
                sb.append("   AND inst.fk_t05_t11_price_check = cat.t11_id  ");
                sb.append("   AND rep.t26_id = trd.t02_id ");
                sb.append(") ");
                sb.append("GROUP BY bndcategory, categ, pricecheckcategory, tolerance ");
                sb.append("ORDER BY categ ");
            } else {
    			logger.debug("Price deviations not implemented for mandant "+mandantString);
                return resultArray.toArray(new PriceDeviationStatisticVo[0]);            	
            }
            
            Collection<Object[]> col = getSession().getHibernateSession().createSQLQuery(sb.toString())
				.addScalar("bndCategory", StringType.INSTANCE)
				.addScalar("categ", StringType.INSTANCE)
				.addScalar("priceCheckCategory", StringType.INSTANCE)
				.addScalar("tolerance", DoubleType.INSTANCE)
				.addScalar("tradeCount", DoubleType.INSTANCE)
				.addScalar("exceededCount", DoubleType.INSTANCE)
				.list();

			logger.debug("Found " + col.size() + " price deviations.");
            Iterator<Object[]> it = col.iterator();
            while (it.hasNext()) {
				Object[] o = it.next();
            	PriceDeviationStatisticVo stat = new PriceDeviationStatisticVo();
            	stat.setSummitCategory((String)o[0]);
            	stat.setCategory((String)o[1]);
            	stat.setPriceCheckCategory((String)o[2]);
            	stat.setTolerance((Double)o[3]);
               	stat.setNumberOfTrades((Double)o[4]);
               	stat.setNumberOfExceededTrades((Double)o[5]);
 				if (stat.getNumberOfTrades() != null && stat.getNumberOfTrades().doubleValue() > 0) {
					stat.setPercentageOfExceededTrades(new Double(100.0 * stat.getNumberOfExceededTrades().doubleValue()/stat.getNumberOfTrades().doubleValue()));
				} else {
					stat.setPercentageOfExceededTrades(new Double(0.0));
				}

               	instumentCategorySearchParam.setInstrument(stat.getCategory());
            	Collection<? extends InstrumentImpl> pCol = finder.findInstruments(instumentCategorySearchParam);
            	for (Iterator<? extends InstrumentImpl> iter = pCol.iterator(); iter.hasNext();) {
					PriceCheckInstrumentImpl element = (PriceCheckInstrumentImpl)iter.next();
					if (element.getChangedDate() != null) {
						stat.setPriceCheckCategoryChangeDate(element.getChangedDate());
						stat.setPriceCheckCategoryChangedBy(element.getModifiedBy());
					} else {
						stat.setPriceCheckCategoryChangeDate(element.getCreationDate());
						stat.setPriceCheckCategoryChangedBy(element.getCreatedBy());
					}
				}

            	resultArray.add(stat);
            }
        } catch (HibernateException he) {
            throwRemoteException(he);
        } catch (PersistenceException pe) {
        	throwRemoteException(pe);
        }
        return resultArray.toArray(new PriceDeviationStatisticVo[0]);

    }
    
    public PriceDeviationStatisticVo[] getPriceDeviationStatistic2(TradeSearchParamsVo searchParams) throws java.rmi.RemoteException {
        HashMap<String, PriceDeviationStatisticVo> resultMap = new HashMap<String, PriceDeviationStatisticVo>();
        try {

            TradeSearchParams param = new TradeSearchParams();
            InstrumentSearchParams instumentCategorySearchParam = new InstrumentSearchParams();
            instumentCategorySearchParam.setOnlyPriceCheckInstruments(true);

            if (searchParams.getMandantCode() != null) {
                MandantImpl man = new MandantImpl(searchParams.getMandantCode());
                param.setMandant(man);
                instumentCategorySearchParam.setMandant(man);
                if (searchParams.getStateCode() != null) {
                    param.setStateId(new StateIdImpl(man, searchParams.getStateCode()));
                }
            }
            param.setFromDate(searchParams.getFromDate());
            param.setToDate(searchParams.getToDate());
            param.setJobIds(searchParams.getJobIds());
            param.setTradeIds(searchParams.getTradeIds());

            MgbFinderImpl finder = new MgbFinderImpl(getSession());
            Collection<TradeImpl> col = finder.findTrades(param);
            logger.debug("Found " + col.size() + " price deviations.");
            Iterator<TradeImpl> it = col.iterator();
            while (it.hasNext()) {
                TradeImpl trade = it.next();
                PriceDeviationVo result = VoFactory.createPriceDeviationVo(trade);
                String key = result.getSummitCategory();
                if (resultMap.containsKey(key) ) {
                	resultMap.get(key).setNumberOfTrades(new Double(resultMap.get(key).getNumberOfTrades().doubleValue()+1));
                	if (!result.getIsNotExceeded().booleanValue()) {
                		resultMap.get(key).setNumberOfExceededTrades(new Double(resultMap.get(key).getNumberOfExceededTrades().doubleValue()+1));
                	}
                } else {
                	PriceDeviationStatisticVo stat = new PriceDeviationStatisticVo();
                	stat.setCategory(result.getCategory());
                	if (!result.getIsNotExceeded().booleanValue()) {
                		stat.setNumberOfExceededTrades(new Double(1.0));
                	} else {
                		stat.setNumberOfExceededTrades(new Double(0.0));                		
                	}
                	stat.setNumberOfTrades(new Double(1.0));
                	stat.setPriceCheckCategory(result.getPriceCheckCategory());

                	instumentCategorySearchParam.setInstrument(result.getCategory());
                	Collection<? extends InstrumentImpl> pCol = finder.findInstruments(instumentCategorySearchParam);
                	for (Iterator<? extends InstrumentImpl> iter = pCol.iterator(); iter.hasNext();) {
						PriceCheckInstrumentImpl element = (PriceCheckInstrumentImpl)iter.next();
						if (element.getChangedDate() != null) {
							stat.setPriceCheckCategoryChangeDate(element.getChangedDate());
							stat.setPriceCheckCategoryChangedBy(element.getModifiedBy());
						} else {
							stat.setPriceCheckCategoryChangeDate(element.getCreationDate());
							stat.setPriceCheckCategoryChangedBy(element.getCreatedBy());
						}
					}
                	stat.setSummitCategory(result.getSummitCategory());
                	stat.setTolerance(result.getTolerance());
                	resultMap.put(key,stat);
                }
            }
            PriceDeviationStatisticVo[] result = resultMap.values().toArray(new PriceDeviationStatisticVo[0]);
			for (int i = 0; i < result.length; i++) {
				if (result[i].getNumberOfTrades() != null) {
					result[i].setPercentageOfExceededTrades(new Double(100.0 * result[i].getNumberOfExceededTrades().doubleValue()/result[i].getNumberOfTrades().doubleValue()));
				} else {
					result[i].setPercentageOfExceededTrades(new Double(0.0));
				}
			}
	        return result;
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return null;
    }
    
    
	@Override
    public Object getRequestPriceConversionFactorMap() throws RemoteException {
	   try {
           MgbFinderImpl finder = new MgbFinderImpl(getSession()); 
           return finder.findRequestPriceConversionFaktors();
       } catch (Exception e) {
           throwRemoteException(e);
       }
       return null;
	}

	@Override
    public boolean fetchPrices(String requestClassName, byte[] encodedPassword) throws java.rmi.RemoteException {
		try {
            /*
             * RS 2015-07-27: This method seems pretty weird to me. First of
             * all, the requestClassName parameter is ignored. Well I suppose
             * since the Bloomberg Requester can only operate on the client
             * side, the need to support more than one type in this method
             * disappeared. But then why is the Bloomberg Request Mapping set
             * below? Is that somehow read by the EUWAX requester too? I don't
             * think so, but for now I'll leave it ... produces BlpApi errors as
             * no Bloomberg client is available (once per Tomcat lifetime). The
             * relevant change in CVS is between 2011-12-16 00:00 and 2011-12-16
             * 23:00:00.
             */
			// Init the Euwax requester
		    if (PricingRequesterFactory.getRequester(MarketDataSourceDef.EUWAX) instanceof EuwaxWebRequester) {
		        ((EuwaxWebRequester)PricingRequesterFactory.getRequester(MarketDataSourceDef.EUWAX)).init(getDomainUser(), encodedPassword);
		    }
            if (PricingRequesterFactory.getRequester(MarketDataSourceDef.BLOOMBERG) instanceof BlpApiBloombergRequester) {
                MgbFinderImpl finder = new MgbFinderImpl(getSession()); 
                ((BlpApiBloombergRequester)PricingRequesterFactory.getRequester(MarketDataSourceDef.BLOOMBERG)).setRequestMapping(finder.findRequestPriceConversionFaktors());
            }
			RequestVo[] requests = getUnsolvedRequests(EuwaxRequestImpl.class.getName());
			int maxRetry = 2;
			int retryCount = 0;
			while( retryCount < maxRetry && requests.length > 0) {
				retryCount++;
				logger.info("Attempt no "+retryCount+" to retreive data");
				PriceVo[] prices = new PriceVo[requests.length];
				for (int i = 0; i < requests.length; i++) {
					try {
						logger.info("Processing request "
								+ requests[i].getRequestId()
								+ ". ("
								+ requests[i].getRequestString()
								+ " at "
								+ requests[i].getPriceDate().getTime()
								+ ").");
						PriceVo priceVo = null;
						if (requests[i].getRequestString() == null || requests[i].getPriceDate() == null) {
							throw new PricingRequestException(
								"Empty request string or date (" + requests[i].getRequestString() + " at " + requests[i].getPriceDate() + ").");
						}

						if (PricingRequesterFactory.isDummyRequester()) {
							logger.warn("### You are generating a dummy price. ###");
						}
							
						PriceImpl price = null;
						if (MarketDataRequestTypeDef.HISTORIC_WEB_REQUEST.equals(requests[i].getRequestType())) {
							price = PricingRequesterFactory.getRequester(requests[i].getPriceSource()).performHistoricRequest(requests[i].getRequestString(), requests[i].getIsoCurrency(), requests[i].getPriceDate(), null, requests[i].getTradePrice());
						} else if (MarketDataRequestTypeDef.HIGH_LOW_WEB_REQUEST.equals(requests[i].getRequestType())) {
							price =	PricingRequesterFactory.getRequester(requests[i].getPriceSource()).performHighLowRequest(requests[i].getRequestString(), requests[i].getIsoCurrency(), requests[i].getPriceDate(), null, requests[i].getTradePrice());
						}
						priceVo = PriceFetcherModel.buildPriceVo(requests[i], price);

						savePrice(requests[i], priceVo);

						requests[i].setRequestState(MarketDataRequestStateDef.OK_PRICE_UNVALIDATED);
						prices[i] = priceVo;

						requests[i].setRequestDate(new GregorianCalendar());

					} catch (PricingRequestException pre) {
						if (pre instanceof PricingRequestTimeOutException) {
							logger.error(MarketDataRequestStateDef.TIMEOUT + ": " + pre.getMessage(), pre);
							requests[i].setRequestState(MarketDataRequestStateDef.TIMEOUT + ": " + pre.getMessage());
						} else if (pre instanceof PricingRequestComunicationException){
							logger.error(MarketDataRequestStateDef.COMERROR + ": " + pre.getMessage(), pre);
							requests[i].setRequestState(MarketDataRequestStateDef.COMERROR + ": " + pre.getMessage());
							throwRemoteException(pre);
						} else {
							logger.error(MarketDataRequestStateDef.ERROR + ": " + pre.getMessage());
							prices[i] = new PriceVo();
							prices[i].setRequestId(requests[i].getRequestId());
							requests[i].setRequestState(MarketDataRequestStateDef.ERROR + ": " + pre.getMessage());
						}
					} 

					updateRequest(requests[i]);

				}
				runAndSaveAutomaticCheck(prices);

				requests = getUnsolvedRequests(EuwaxRequestImpl.class.getName());
			}

        } catch (Exception e) {
            throwRemoteException(e);
        }
		return true;
	}
	
	@Override
    public Object getClientConfig(String key) throws java.rmi.RemoteException {
		return clientConfig.get(key);
	}
	@Override
    public void setClientConfig(String key, Object value) throws java.rmi.RemoteException {
		this.clientConfig.put(key, value);
	}
	
	@Override
    public void runSampleChecks() throws RemoteException {
        try {
        	MgbFinderImpl finder = new MgbFinderImpl(getSession());
            TradeSearchParams sampleParams = createCurrentTradeSearchParam();
            sampleParams.setIsSampleChecked(Boolean.TRUE);
            int existingSampleSize = finder.findTrades(sampleParams).size(); 
            if (existingSampleSize > 0) {
                logger.debug("Already generated random sample ("+existingSampleSize+") for this job selection");
            } else {
                Transaction t = getSession().beginTransaction();
                TradeSearchParams params = createCurrentTradeSearchParam();
                params.setIsManualCheckRequired(Boolean.TRUE);
            
                Random random = new Random(System.currentTimeMillis());
                int maxRandom = 100;
                int minRandom = 0;
        	
                int sampleSize = 0;
                int globalSampleSize = 0;
                ManualSampleStateImpl sampleState = null;
                try {
                	globalSampleSize = new Integer(finder.findMgbConfigurationValue(mandant,MgbConfigurationDef.SAMPLE_GLOBAL_PERCENTAGE)).intValue();
                	if (globalSampleSize != 0) {
                		String newStateCode = finder.findMgbConfigurationValue(mandant,MgbConfigurationDef.SAMPLE_GLOBAL_STATE);
                		sampleState = (ManualSampleStateImpl)getSession().select(ManualSampleStateImpl.class, new StateIdImpl(mandant, newStateCode));
                		sampleSize = globalSampleSize; 
                		logger.debug("Setting global sample code to " + sampleState.getStateCode() + " with size "+globalSampleSize);
                	}
                } catch (Exception e) {
                	logger.error("Error while loading global sample config", e);
                }

                Map<String,Integer> reportLocationSampleSizeMap = getReportLocationSampleSizeMap(finder);
                    
                Collection<TradeImpl> col = finder.findTrades(params);
                logger.debug("Found " + col.size() + " trades.");
                Iterator<TradeImpl> it = col.iterator();
                while (it.hasNext()) {
                	TradeImpl trade = it.next();
                	StringBuffer comment = new StringBuffer("Random sample percentage: ").append(globalSampleSize);
            		sampleSize = globalSampleSize;
            		
            		int priceCheckSampleSize = 0;
                	if (trade.getInstrument() != null && trade.getInstrument().getPriceCheckCategory() != null) {
                		priceCheckSampleSize = trade.getInstrument().getPriceCheckCategory().getSamplePercentage();
                		if (priceCheckSampleSize != 0 && trade.getInstrument().getPriceCheckCategory().getSampleState() != null) {
                			sampleState = trade.getInstrument().getPriceCheckCategory().getSampleState();
                			sampleSize = sampleSize + priceCheckSampleSize;
                			logger.debug("Setting priceCheck sample code to " + sampleState.getStateCode() + " with size "+sampleSize + " ("+priceCheckSampleSize+")");
                		}
                	}
        			comment.append(" + ").append(priceCheckSampleSize);
        			
        			int stateSampleSize = 0;
        			if (trade.getCurrentAutoState() != null) {
                		stateSampleSize = trade.getCurrentAutoState().getSamplePercentage();
                		if (stateSampleSize != 0 && trade.getCurrentAutoState().getSampleState() != null) {
                			sampleState = trade.getCurrentAutoState().getSampleState();
                			sampleSize = sampleSize + stateSampleSize;
                			logger.debug("Setting autoState sample code to " + sampleState.getStateCode() + " with size "+sampleSize + " ("+stateSampleSize+")");
                		}
                	}
        			comment.append(" + ").append(stateSampleSize);
        			
        			int reportLocationSampleSize = 0;
        			String reportLocation = reportConfigMapping.get(trade.getLocationTrader());
        			if (reportLocation != null && reportLocationSampleSizeMap.containsKey(reportLocation)) {
        			    reportLocationSampleSize = reportLocationSampleSizeMap.get(reportLocation);
                        if (reportLocationSampleSize != 0) {
                            sampleSize = sampleSize + reportLocationSampleSize;
                            if (reportLocationSampleSize > 0) {
                                String newStateCode = finder.findMgbConfigurationValue(mandant,MgbConfigurationDef.SAMPLE_GLOBAL_STATE);
                                sampleState = (ManualSampleStateImpl)session.select(ManualSampleStateImpl.class, new StateIdImpl(mandant, newStateCode));
                            }
                            logger.debug("Setting global sample code to " + sampleState.getStateCode() + " with size "+sampleSize + " ("+reportLocationSampleSize+")");
                        }
        			}
                    comment.append(" + ").append(reportLocationSampleSize);

        			sampleSize = Math.max(minRandom,Math.min(maxRandom,sampleSize));
                	if (random.nextInt(maxRandom) < sampleSize) {
                		ManualStateHistEntryImpl state = new ManualStateHistEntryImpl();
                		state.setComment(comment.append(" = ").append(sampleSize).toString());
                		state.setMandant(mandant);
                		state.setManualState(sampleState);
                		state.setStateTime(new GregorianCalendar());
                		state.setTrade(trade);
                		getSession().save(state);
                		trade.setCurrentManualStateHistEntry(state);
                		logger.debug("Setting sample code to " + sampleState.getStateCode() + " for trade "+trade.getLongId()+ " with size "+sampleSize);
                		getSession().save(trade);
                	} else {
                		logger.debug("No sample for trade "+trade.getLongId());                	
                	}
                }
                t.commit();
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
	}

    /**
     * Assumes an MgbConfigurationImpl entry with: 
     * key: SAMPLE_REPORT_LOCATION_PERCENTAGE
     * value: <REPORT_LOCATION1>,<PERCENTAGE1>,<REPORT_LOCATION2>,<PERCENTAGE2>
     * 
     * e.g.: EAA,-100,VBB 0,INSTANBUL,20
     * 
     * @param finder
     * @return
     * @throws PersistenceException
     */
    private Map<String, Integer> getReportLocationSampleSizeMap(MgbFinderImpl finder) throws PersistenceException {
        Map<String, Integer> result = new HashMap<String, Integer>();
        String reportLocationSampleSizeConfig = finder.findMgbConfigurationValue(mandant,MgbConfigurationDef.SAMPLE_REPORT_LOCATION_PERCENTAGE);
        String[] reportLocationSampleSizeArray = StringUtils.split(reportLocationSampleSizeConfig, MgbConfigurationDef.MGB_CONFIGURATION_LIST_DELIMITER);
        for (int i = 0; i < reportLocationSampleSizeArray.length; i++) {
            result.put(reportLocationSampleSizeArray[i], Integer.valueOf(reportLocationSampleSizeArray[++i]));
        }
        return result;
    }

    @Override
    public String[] findAllTimePeriods(String mandantCode) throws RemoteException {
        ArrayList<String> resultArray = new ArrayList<String>();
        MgbFinderImpl finder = new MgbFinderImpl(getSession());
        try {
            Iterator<TimePeriodCategoryImpl> it = finder.findAllTimePeriods(new MandantImpl(mandantCode)).iterator();
            while (it.hasNext()) {
                resultArray.add(it.next().getCode());
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return resultArray.toArray(new String[0]);
    }

    @Override
    public String[] findAllReports() throws RemoteException {
        MgbFinderImpl finder = new MgbFinderImpl(getSession());
        try {
            Map<String,List<String>> map = finder.findReportConfiguration();
            if (map != null) {
                return  map.keySet().toArray(new String[0]);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return new String[]{};
    }

    @Override
    public String[] findReports(String type) throws RemoteException {
        MgbFinderImpl finder = new MgbFinderImpl(getSession());
        try {
            return finder.findReports(type).toArray(new String[0]);
        } catch (PersistenceException e) {
            throwRemoteException(e);
            return new String[]{};
        }
    }

    @Override
    public String[] findAllReportClients() throws RemoteException {
        MgbFinderImpl finder = new MgbFinderImpl(getSession());
        try {
            List<String> list = finder.findReportClients();
            if (list != null) {
                return  list.toArray(new String[0]);
            }
        } catch (PersistenceException e) {
            throwRemoteException(e);
        }
        return new String[]{};
    }

	@Override
    public void close() throws RemoteException {
		try {
			closeSession();
			StoreSingleton.getUniqueInstance().close();
        } catch (Exception e) {
            throwRemoteException(e);
        }
	}
}