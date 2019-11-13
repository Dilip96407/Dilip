package de.westlb.mgb.client.mask.model.shared;

import de.westlb.mgb.client.server.vo.ExchangeMappingVo;



/**
 * Gui model for the employee overview view.
 *
 * @author Manfred Boerner
 */

public interface ExchangeModel  {
	public static final String 
		P_BLOOMBERG_ID			= ExchangeEditModel.P_BLOOMBERG_ID,
		P_REUTERS_ID			= ExchangeEditModel.P_REUTERS_ID,
		P_MAPPING_TABLE_MODEL	= ExchangeEditModel.P_MAPPING_TABLE_MODEL
	;

	public Long getExchangeId();

	public ExchangeMappingVo getExchangeMappingVo(int row);

	public boolean doDeleteMapping(int row);
	
	public void reload();
}
