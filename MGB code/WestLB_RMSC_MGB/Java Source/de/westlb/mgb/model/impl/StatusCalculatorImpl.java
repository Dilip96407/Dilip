package de.westlb.mgb.model.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import de.westlb.mgb.model.definition.MgbConfigurationDef;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;

/**
 * @author D055625
 *
 * The StatusCalculatorImpl calculates the status from a String[].
 * Each array-entry has the following format:
 * 
 * final_status = [&lt;condition&gt;] 
 * 
 * A &lt;condition&gt; can have the following form:
 * <ul>
 * <li>(&lt;condition&gt;)</li>
 * <li>!&lt;condition&gt;</li>
 * <li>&lt;condition&gt; &amp;&amp; &lt;condition&gt;</li>
 * <li>&lt;condition&gt; || &lt;condition&gt;</li>
 * </ul>
 *  
 * So normal boolean arethmetic may be used:
 * The conditions are evaluated from left to right. The &amp;&amp; and the || operator have the same precidence.
 * Bracket can be used for grouping.
 * The unary operator ! is applied only on the succeeding condition.
 * <br>  
 * If the right-hand-side condition returns true, the left-hand-side final status is returned.
 * If the right-hand-side condition returns false, next String of the array is evaluated..
 * 
 */
public class StatusCalculatorImpl {

	static Logger logger = Logger.getLogger(StatusCalculatorImpl.class);

	private TradeImpl trade;

	private Collection<StateRulesImpl> rules;

	private HashMap<String, ConditionImpl> conditions;

	private HashMap<String, List<String>> collections;

	private String lastInternalCalculatorStateComment;
	
    private static final char OPEN = '(';
    private static final char CLOSE = ')';
    private static final String NOT = "!";
    private static final String AND = "&&";
    private static final String OR = "||";
    private static final String BLANK = " ";
    
	

	public StatusCalculatorImpl() {
	}

	public AutomaticStateImpl calculateStatus() throws Exception {
		lastInternalCalculatorStateComment = "";
		Iterator<StateRulesImpl> i = rules.iterator();
		while (i.hasNext()) {
			StateRulesImpl rule = i.next();
			if (evalCondition(rule.getConditionName().trim())) {
				logger.debug("Final status: " + rule.getFinalState().getStateCode());
				if (rule.getConditionName() != null && rule.getConditionName().trim().length() > 0 ) {
					lastInternalCalculatorStateComment = lastInternalCalculatorStateComment + " Final condition: " + rule.getConditionName().trim();
				} else {
					lastInternalCalculatorStateComment = lastInternalCalculatorStateComment + " Final condition: DEFAULT-Condition";				    
				}
				return rule.getFinalState();
			}
			logger.debug("Get next state");
		}
		logger.error("No finite state found");
		Iterator<StateRulesImpl> it = rules.iterator();
		while (it.hasNext()) {
			StateRulesImpl rule = it.next();
			logger.error("Rules: " + rule.getFinalState().getStateCode() + " = " + rule.getConditionName());

		}
		throw new Exception("No finite state found");
	}

	public AutomaticStateImpl calculateStatus(TradeImpl trade) throws Exception {
		this.trade = trade;
		logger.debug("trade: "+trade.getTradeId());
		return calculateStatus();
	}

	public AutomaticStateImpl calculateStatus(TradeImpl trade, MandantImpl mandant) {
		this.trade = trade;
		try {
			return calculateStatus(trade);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			lastInternalCalculatorStateComment = e.getMessage();
			return new AutomaticStateImpl(new StateIdImpl(mandant, AutomaticStateImpl.AUTO_ERROR));
		}

	}

	public boolean checkCondition(TradeImpl trade, String condition) throws Exception {
		this.trade = trade;
		return evalCondition(condition);
	}

	private boolean evalCondition(String conditionString) throws Exception {
		String condition = conditionString.trim();
		logger.debug("condition: '" + condition + "'");
		if (condition == null || condition.length() == 0) {
			logger.debug("no condition->true");
			return true;
		}
			String lhs = null;
			String rhs = null;
			String operator = null;
            if (condition.charAt(0) == OPEN) {
                logger.debug("(...)");
                int parenthesisLevel = 1;
                int pos = 1;
                while (parenthesisLevel > 0 && pos < condition.length() ) {
                    if (condition.charAt(pos) == OPEN) {
                        parenthesisLevel++;
                    } else if (condition.charAt(pos) == CLOSE) {
                        parenthesisLevel--;
                    }
                    pos++;
                }
                if (pos > condition.length()) {
                    throw new ParseException("Missing '"+CLOSE+"' in '" + condition + "'", pos);
                }
                int rightParenthesisPos = pos-1;

                lhs = condition.substring(condition.indexOf(OPEN) + 1, rightParenthesisPos).trim();
				String outsideParenthesis = condition.substring(rightParenthesisPos + 1).trim();
				if (outsideParenthesis == null || outsideParenthesis.length() == 0) {
					return evalCondition(lhs);
				} else if (!outsideParenthesis.startsWith(OR) && !outsideParenthesis.startsWith(AND)) {
					throw new ParseException("Missing '"+OR+"' or '"+AND+"' in '" + condition + "'", 0);
				}
				operator = outsideParenthesis.substring(0, 2);
				rhs = outsideParenthesis.substring(operator.length()).trim();
			} else {
				int operatorPos = minPositiveNumber(condition.indexOf(OR), condition.indexOf(AND));
				if (operatorPos < 0) {
					if (condition.indexOf(BLANK) < 0) {
						if (condition.startsWith(NOT)) {
							logger.debug(NOT);
							return !checkCondition(condition.substring(condition.indexOf(NOT) + 1).trim());
						}
						return checkCondition(condition);
					}
                    throw new ParseException("Missing operator '"+OR+"' or '"+AND+"' in '" + condition + "'", 0);
				}
                lhs = condition.substring(0, operatorPos).trim();
                operator = condition.substring(operatorPos, operatorPos + 2);
                rhs = condition.substring(operatorPos + operator.length()).trim();
			}
			logger.debug("..." + operator + "...");
			if (rhs == null || rhs.length() == 0) {
				throw new ParseException("No right side of operator '"+OR+"' or '"+AND+"' in '" + condition + "'", 0);
			}
			if (AND.equals(operator)) {
				return evalCondition(lhs) && evalCondition(rhs);
			} else if (OR.equals(operator)) {
				return evalCondition(lhs) || evalCondition(rhs);
			} else {
				throw new ParseException("Unknown operator '" + operator + "' in '" + condition + "'", 0);
			}
	}

	private int minPositiveNumber(int i1, int i2) {
		if (i1 < 0) {
			i1 = Integer.MAX_VALUE;
		}
		if (i2 < 0) {
			i2 = Integer.MAX_VALUE;
		}
		int result = Math.min(i1, i2);
		if (result == Integer.MAX_VALUE) {
			result = -1;
		}
		return result;
	}

	private boolean checkCondition(String condition) throws Exception {
		if ("true".equalsIgnoreCase(condition)) {
			return true;
		}
        if (getCondition(condition) != null) {
        	String conditionEvaluator = getCondition(condition).getConditionEvaluator();
        	Method method = trade.getClass().getMethod(conditionEvaluator);
        	try {
        		if (ConditionImpl.JAVA_SERVER .equals(getCondition(condition).getConditionType())) {
        			boolean result = ((Boolean) method.invoke(trade)).booleanValue();
        			logger.debug("result of " + conditionEvaluator + "(): " + result);
        			lastInternalCalculatorStateComment = lastInternalCalculatorStateComment + condition + "=" + result+ ", ";
        			return result;
        		}
                if (getCollection(condition) != null) {
                	Object o = method.invoke(trade);
                	boolean result = o != null && getCollection(condition).contains(o.toString());
                	logger.debug("result of " + conditionEvaluator + "(): "+o+" in "+getCollection(condition)+": " + result);
                	lastInternalCalculatorStateComment = lastInternalCalculatorStateComment + condition + "=" + result+ ", ";
                	return result;							
                }
                throw new Exception("Unable to lookup the collection data in MgbConfiguration for the condition '" + condition + "' and the method '" + conditionEvaluator + "'.");
        	} catch (InvocationTargetException ite) {
        		throw new Exception("InvocationTargetException: ",ite.getTargetException());
        	}
        }
        throw new Exception("Unable to map the conditionName '" + condition + "' to a method to be evaluated.");
	}

	/**
	* Returns the rules.
	* @return String[]
	*/
	public Collection<StateRulesImpl> getRules() {
		return rules;
	}

	/**
	* Returns the trade.
	* @return TradeImpl
	*/
	public TradeImpl getTrade() {
		return trade;
	}

	/**
	* Sets the rules.
	* @param rules The rules to set
	*/
	public void setRules(Collection<StateRulesImpl> rules) {
		this.rules = rules;
	}

	/**
	* Sets the trade.
	* @param trade The trade to set
	*/
	public void setTrade(TradeImpl trade) {
		this.trade = trade;
	}

	public void loadConfiguration(Session sess, SourceSystemImpl sourceSystem, int stage) throws PersistenceException {
		loadRules(sess, sourceSystem, stage);
		loadConditions(sess, sourceSystem);
	}

	private void loadRules(Session sess, SourceSystemImpl sourceSystem, int stage) throws PersistenceException {
		Query query = sess.createQuery("from StateRulesImpl rule where rule.stage="+stage+" and rule.sourceSystem = :sourceSystem order by rule.priority");
		logger.debug("HQL: "+query.getQueryString());
		query.setParameter("sourceSystem", sourceSystem);
        Collection<StateRulesImpl> result = query.list(); 
		setRules(result);
	}

    private void loadConditions(Session sess, SourceSystemImpl sourceSystem) throws PersistenceException {
		HashMap<String, ConditionImpl> condMap = new HashMap<String, ConditionImpl>();
		HashMap<String, List<String>> collMap = new HashMap<String, List<String>>();
		Query query = sess.createQuery("from ConditionImpl cond where cond.conditionType = '" + ConditionImpl.JAVA_SERVER + "' and cond.sourceSystem = :sourceSystem");
		logger.debug("HQL: "+query.getQueryString());
		query.setParameter("sourceSystem", sourceSystem);
	    Iterator<ConditionImpl> conIter = query.iterate();
		while (conIter.hasNext()) {
			ConditionImpl cond = conIter.next();
			condMap.put(cond.getConditionName(), cond);
		}
		query = sess.createQuery("select cond, conf.value from ConditionImpl cond, MgbConfigurationImpl conf where cond.conditionType <> '" + ConditionImpl.JAVA_SERVER + "' and cond.sourceSystem = :sourceSystem and cond.sourceSystem.mandant = conf.mgbConfigurationId.mandant and cond.conditionType = conf.mgbConfigurationId.key ");
		logger.debug("HQL: "+query.getQueryString());
		query.setParameter("sourceSystem", sourceSystem);
        Iterator<Object[]> i = query.iterate();
		while (i.hasNext()) {
			Object[] o = i.next();
			ConditionImpl cond = (ConditionImpl) o[0];
			condMap.put(cond.getConditionName(), cond);
			collMap.put(cond.getConditionName(), listFromCsv(o[1].toString()));
		}
		setCollections(collMap);
		setConditions(condMap);
	}

	private List<String> listFromCsv(String csvList) throws PersistenceException {
		if (csvList != null) {
			return new ArrayList<String>(Arrays.asList(StringUtils.split(csvList, MgbConfigurationDef.MGB_CONFIGURATION_LIST_DELIMITER)));
		}
        return new ArrayList<String>();
	}

	/**
	 * Returns the conditions.
	 * @return HashMap
	 */
	public ConditionImpl getCondition(String conditionName) {
		if (conditions != null && conditions.containsKey(conditionName)) {
			return conditions.get(conditionName);
		}
        return null;
	}

	public List<String> getCollection(String conditionName) {
		if (collections != null && collections.containsKey(conditionName)) {
			return collections.get(conditionName);
		}
        return null;
	}

	/**
	 * Sets the conditions.
	 * @param conditions The conditions to set
	 */
	public void setConditions(HashMap<String, ConditionImpl> conditions) {
		this.conditions = conditions;
	}

	public void setCollections(HashMap<String, List<String>> collections) {
		this.collections = collections;
	}

	/**
	 * Returns the lastInternelCalculatorStateComment.
	 * @return String
	 */
	public String getLastInternalCalculatorStateComment() {
        if (lastInternalCalculatorStateComment != null) {
            return lastInternalCalculatorStateComment.substring(0, Math.min(TradeHistEntryImpl.COMMENT_MAX_LENGTH, lastInternalCalculatorStateComment.length()));
        }
		return lastInternalCalculatorStateComment;
	}

	public String getFullLastInternalCalculatorStateComment() {
		return lastInternalCalculatorStateComment;
	}

}
