
package de.westlb.mgb.server.statistic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class ApplicationStatisticHolder {
	private static ApplicationStatisticHolder instance = new ApplicationStatisticHolder();
	private static Logger logger = Logger.getLogger(ApplicationStatisticHolder.class);
    private long currentCounter;
    private long peakCounter;
    private long minLifeTime;
    private long maxLifeTime;
    private ArrayList<HttpSession> newSessions;
    private HashMap<String, UserStatisticHolder> userStatisticHolders;
    
    private ApplicationStatisticHolder()
    {
        currentCounter = 0L;
        peakCounter = 0L;
        minLifeTime = 0L;
        maxLifeTime = 0L;
        newSessions = new ArrayList<HttpSession>();
        userStatisticHolders = new HashMap<String, UserStatisticHolder>();
    }
    
	public synchronized void closeSession(String sessionId, long lifeTime) {
	    logger.debug("Closing session "+sessionId);
		if (currentCounter > 0L) {
			currentCounter--;
		}

		if (minLifeTime == 0L) {
			minLifeTime = lifeTime;
		} else if (lifeTime < minLifeTime) {
			minLifeTime = lifeTime;
		}
		if (lifeTime > maxLifeTime) {
			maxLifeTime = lifeTime;
		}

		updateUserStatistic();
		Iterator<UserStatisticHolder> iterator = userStatisticHolders.values().iterator();
		while (iterator.hasNext()) {
			UserStatisticHolder userStat = iterator.next();
			if (userStat.isSessionActive(sessionId)) {
				userStat.closeSession(sessionId);
			}
		}
	}
    public synchronized void openSession(HttpSession httpSession) {
        logger.debug("Opened session "+httpSession.getId());
    	updateUserStatistic();
        currentCounter++;
        if(currentCounter > peakCounter) {
            peakCounter = currentCounter;
		}
		
		newSessions.add(httpSession);
    }
    
    private void updateUserStatistic() {
        ArrayList<HttpSession> rest = new ArrayList<HttpSession>();

        Iterator<HttpSession> iterator = newSessions.iterator();
        while (iterator.hasNext()) {
            HttpSession session = iterator.next();
            
            String userId = null;
            try {
				userId = (String) session.getAttribute("userId");
            } catch (Throwable e) {
            	continue;
            }
            if (userId == null) {
                rest.add(session);
                continue;
            }

            UserStatisticHolder userStatisticHolder = userStatisticHolders.get(userId);
            if (userStatisticHolder == null) {
                userStatisticHolder = new UserStatisticHolder(userId);
                userStatisticHolders.put(userId, userStatisticHolder);
            }
            userStatisticHolder.openSession(session);
        }
        newSessions = rest;
    }
    
    public static ApplicationStatisticHolder getInstance() {
		return instance;    	
    }
    
    public synchronized Collection<UserStatisticHolder> getUserStatistic() {
		updateUserStatistic();
    	return userStatisticHolders.values();
    }

}
