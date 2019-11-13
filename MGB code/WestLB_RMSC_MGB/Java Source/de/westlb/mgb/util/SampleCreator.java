/*
 * Created on Jul 19, 2006
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.util;

import java.util.Random;

import org.apache.log4j.Logger;

/**
 * @author d055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SampleCreator {

	private static Logger logger = Logger.getLogger(SampleCreator.class);

	public static final double NO_SAMPLE = 0d;

	public static final double FULL_SAMPLE = 100d;
	
	private Random random = null;
	
	public SampleCreator() {
		random = new Random(System.currentTimeMillis()); 
	}
	
	/**
	 * @param percentage between 0 and 100. e.g. if percentage is 70, the function returns true with a probability of 0.7 
	 * @return
	 */
	public boolean decide(double percentage) {
		checkPlausibility(percentage);
		return random.nextDouble()*FULL_SAMPLE < percentage;
	}

	public boolean decide(double percentage1, double percentage2) {
		double percentage = combinePercentage(percentage1, percentage2);
		return decide(percentage);
	}

	public boolean decide(double percentage1, double percentage2, double percentage3) {
		double percentage = combinePercentage(percentage1, percentage2, percentage3);
		return decide(percentage);
	}
	
	private double combinePercentage(double d1, double d2) {
		return d1+d2;
	}

	private double combinePercentage(double d1, double d2, double d3) {
		return d1+d2+d3;
	}
	
	private void checkPlausibility(double percentage) {
		if (percentage < NO_SAMPLE) {
			logger.warn("Negative percentage "+percentage+" for sample will cause an empty sample.");
		} else if (percentage > FULL_SAMPLE) {
			logger.warn("A percentage "+percentage+" of more than "+FULL_SAMPLE+" will cause a full sample.");
		}
	}

}
