package de.westlb.mgb.client.ui.util;

import org.apache.commons.lang3.StringUtils;

import de.westlb.mgb.model.definition.InstrumentNameDef;

/**
 * Formatter and parser for a money market instrument code.
 * @author: M. Boerner
 */

public class MmkInstrumentCodeFormat extends java.text.Format {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2058551023318182828L;
	public final static String CURRENCY_SEPARATOR  = "-";
	public final static String MATURITY_CODE_SEPARATOR  = "_";

    public MmkInstrumentCodeFormat() {
        super();
    }

    /**
     * Formats a Money market instrument name
     *
     * A money market instrument name is coded in the model as String
     * with format <currency code>{-<currency_code>}{_maturity_code} 
     */
    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, java.text.FieldPosition pos) {
        if (obj == null) {
            return toAppendTo;
        }
        if (!(obj instanceof MmkInstrumentCode)) {
            throw new IllegalArgumentException("Can't format " + obj.getClass().getName() + " as MmkInstrumentCode");
        }
        
		MmkInstrumentCode code = (MmkInstrumentCode) obj;
		
		StringBuffer buf = new StringBuffer();
		if (code.getCurrencyCode1() != null) {
			buf.append(code.getCurrencyCode1());
		}
		if (code.getCurrencyCode2() != null) {
			buf.append(CURRENCY_SEPARATOR);
			buf.append(code.getCurrencyCode2());			
		}
		if (code.getMaturityCode() != null) {
			buf.append(MATURITY_CODE_SEPARATOR);
			buf.append((code.getMaturityCode()));
		}
		
		if (code.isGeneratedCrossCurrency()) {
			buf.append(InstrumentNameDef.CROSS_CURRENCY_COMMENT);
		}
		toAppendTo.append(buf);
		return toAppendTo;
    }
    
    /**
     * Parse a string as MmkInstrumentCode
     * @param: source String to parse
     * @param: status Start position for parsing
     * @return: a MmkInstrumentCode or null in error case.
     */
    @Override
    public Object parseObject(String source, java.text.ParsePosition status) {
		MmkInstrumentCode code = new MmkInstrumentCode();
		
		if (source.endsWith(InstrumentNameDef.CROSS_CURRENCY_COMMENT)) {
			source = source.substring(0, source.length() - InstrumentNameDef.CROSS_CURRENCY_COMMENT.length());
			code.setGeneratedCrossCurrency(true);
		}

		String[] parts = StringUtils.split(source, MATURITY_CODE_SEPARATOR);
		if (parts.length != 2) {
//			throw new IllegalArgumentException("Invalid MmkInstrumentCode <" + source + ">");			
		} else {
			code.setMaturityCode(parts[1]);
		}
		
		parts = StringUtils.split(parts[0], CURRENCY_SEPARATOR);
		if (parts.length < 1 || parts.length > 2) {
			throw new IllegalArgumentException("Invalid MmkInstrumentCode <" + source + ">");			
		}
		code.setCurrencyCode1(parts[0]);
		if (parts.length > 1) {
			code.setCurrencyCode2(parts[1]);			
		}

		status.setIndex(source.length()-1);
		
		return code;
    }
}
