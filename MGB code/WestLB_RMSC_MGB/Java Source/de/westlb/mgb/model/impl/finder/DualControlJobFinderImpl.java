/*
 * Created on 22.08.2007
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.model.impl.finder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import de.westlb.mgb.model.Entity;
import de.westlb.mgb.model.definition.DualControlJobProcessorDef;
import de.westlb.mgb.model.impl.AddonImpl;
import de.westlb.mgb.model.impl.DualControlJobImpl;
import de.westlb.mgb.model.impl.EmployeeImpl;
import de.westlb.mgb.model.impl.ExchangeImpl;
import de.westlb.mgb.model.impl.ExchangeMappingEntryImpl;
import de.westlb.mgb.model.impl.InstrumentImpl;
import de.westlb.mgb.model.impl.MandantImpl;
import de.westlb.mgb.model.impl.MgbConfigurationImpl;
import de.westlb.mgb.model.impl.PriceCheckCategoryImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;

public class DualControlJobFinderImpl extends MgbFinderImpl {

    public DualControlJobFinderImpl(Session sess) {
        super(sess);
    }

    public Collection<DualControlJobImpl> findDualControlJobsEntries(DualControlJobSearchParams params) throws PersistenceException {
        HashMap<String, Object> columnValues = new HashMap<String, Object>();
        ArrayList<String> clauseTokens = new ArrayList<String>();
        String table = "from " + DualControlJobImpl.class.getName() + " dcj";
        if (params != null) {
            if (params.getMandant() != null) {
                clauseTokens.add("dcj.mandant = :mandant");
                columnValues.put("mandant", params.getMandant());
            }
            if (isNotNullOrZero(params.getExcludedRequesterNtId())) {
                clauseTokens.add("dcj.requestedBy.ntId <> :excludedRequester");
                columnValues.put("excludedRequester", params.getExcludedRequesterNtId());
            }
            if (!params.isShowCommitedJobs()) {
                clauseTokens.add("dcj.commitedBy is null");
            }
        }
        Query query = buildQuery(table, columnValues, clauseTokens);
        Collection<DualControlJobImpl> result = query.list();
        return result;
    }

    public Collection<EmployeeImpl> findPendingDualControlJobsEntryRequester(Entity entity) throws PersistenceException {
        HashMap<String, Object> columnValues = new HashMap<String, Object>();
        ArrayList<String> clauseTokens = new ArrayList<String>();
        String table = "select dcj.requestedBy from " + DualControlJobImpl.class.getName() + " dcj";
        clauseTokens.add("dcj.state != '" + DualControlJobProcessorDef.EXECUTED + "'");
        if (entity != null) {
            if (entity instanceof AddonImpl) {
                clauseTokens.add("dcj.addon = :addon");
                columnValues.put("addon", entity);
            } else if (entity instanceof PriceCheckCategoryImpl) {
                clauseTokens.add("dcj.priceCheckCategory = :priceCheckCategory");
                columnValues.put("priceCheckCategory", entity);
            } else if (entity instanceof ExchangeImpl) {
                clauseTokens.add("dcj.exchange = :exchange");
                columnValues.put("exchange", entity);
            } else if (entity instanceof ExchangeMappingEntryImpl) {
                clauseTokens.add("dcj.exchangeMappingEntry = :exchangeMappingEntry");
                columnValues.put("exchangeMappingEntry", entity);
            } else if (entity instanceof InstrumentImpl) {
                clauseTokens.add("dcj.instrument = :instrument");
                columnValues.put("instrument", entity);
            } else if (entity instanceof MgbConfigurationImpl) {
                clauseTokens.add("dcj.mgbConfiguration.mgbConfigurationId.key = :mgbConfigurationKey");
                clauseTokens.add("dcj.mgbConfiguration.mgbConfigurationId.mandant = :mgbConfigurationMandant");
                columnValues.put("mgbConfigurationKey", ((MgbConfigurationImpl) entity).getMgbConfigurationId()
                        .getKey());
                columnValues.put("mgbConfigurationMandant", ((MgbConfigurationImpl) entity).getMgbConfigurationId()
                        .getMandant());
            }
        }
        Query query = buildQuery(table, columnValues, clauseTokens);
        Collection<EmployeeImpl> result = query.list();
        return result;
    }

    public Collection<DualControlJobImpl> findOthersUncommitedTasks(MandantImpl mandant, EmployeeImpl committer)
            throws PersistenceException {
        Query query = sess.createQuery("from " + DualControlJobImpl.class.getName()
                + " dcj where dcj.commitedBy is null and dcj.mandant = :mandant and dcj.requestedBy <> :committer");
        query.setParameter("mandant", mandant);
        query.setParameter("committer", committer);
        Collection<DualControlJobImpl> result = query.list();
        return result;
    }

    public Collection<DualControlJobImpl> findUncommitedTasks(MandantImpl mandant) throws PersistenceException {
        Query query = sess.createQuery("from " + DualControlJobImpl.class.getName()
                + " dcj where dcj.commitedBy is null and dcj.mandant = :mandant");
        query.setParameter("mandant", mandant);
        Collection<DualControlJobImpl> result = query.list();
        return result;
    }

}
