package de.westlb.mgb.converter;

import de.westlb.mgb.model.impl.DataLoadImpl;
import de.westlb.mgb.model.impl.JobImpl;
import de.westlb.mgb.model.impl.LoadSummitUnknownImpl;
import de.westlb.mgb.model.impl.SourceSystemImpl;
import de.westlb.mgb.model.impl.TradeImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Session;

public class SummitUnknownConverter extends MgbConverter {

    public SummitUnknownConverter(SourceSystemImpl sourceSystemImpl) {
		super(sourceSystemImpl);
	}

	@Override
	protected Class<? extends DataLoadImpl> getLoaderTable() {
		return LoadSummitUnknownImpl.class;
	}

	@Override
    protected String getLoaderTableSelectQuery() {
        return "select load from "+getLoaderTable().getName()+" load where load.sourceSystem = '" + sourceSystemImpl.getCode() + "'";
   }

    @Override
	protected void converterInit() throws ConverterException {};
	
	@Override
	protected void preProcess() throws ConverterException {};

	@Override
	protected void process() throws ConverterException {};

	@Override
	protected void checkCompleteness() throws ConverterException {};
	
	@Override
    protected Class<? extends TradeImpl> getTradeClass() {
		return null;
	}

	@Override
    protected TradeImpl convert(Session sess, DataLoadImpl loadData, JobImpl job) throws PersistenceException {
		return null;
	}

}
