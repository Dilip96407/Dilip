/*
 * Created on Jan 8, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.server.statistic;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
 * @author WSY4148
 *
 */
public class UserStatisticHolder {
	private String userId;
	private int sessionCount;
	private HashMap<String, SessionData> activeSessions;
	private SessionData latestSession;
	
	private static class SessionData {
		private String sessionId;
		private long creationTime;
		private long destroyTime;
		
		@SuppressWarnings("unused")
        public SessionData(String sessionId) {
			this(sessionId, -1L);
		}
		
		public SessionData(String sessionId, long creationTime) {
			this.sessionId = sessionId;
			this.creationTime = creationTime;
			this.destroyTime = -1;
		}
		
        /**
         * @return
         */
        public long getCreationTime() {
            return creationTime;
        }

        /**
         * @return
         */
        public String getSessionId() {
            return sessionId;
        }

        /* (Kein Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            
            if (!(obj instanceof SessionData)) {
            	return false;
            }

            return sessionId.equals(((SessionData)obj).getSessionId());
        }

        /* (Kein Javadoc)
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
           return sessionId.hashCode();
        }

        /**
         * @return
         */
        public long getDestroyTime() {
            return destroyTime;
        }

        /**
         * @param l
         */
        public void setDestroyTime(long l) {
            destroyTime = l;
        }

	}

	
	private SessionData addActiveSession(HttpSession httpSession) {
		SessionData sessionData = new SessionData(httpSession.getId(), httpSession.getCreationTime());
		activeSessions.put(httpSession.getId(), sessionData);
		return sessionData;
	}
	
	private void removeActiveSession(String sessionId) {
		activeSessions.remove(sessionId);
	}
	
	public boolean isSessionActive(String sessionId) {
		return activeSessions.get(sessionId) != null;
	}
	
    /**
     * 
     */
    public UserStatisticHolder(String userId) {
        super();
        activeSessions = new HashMap<String, SessionData>();
        this.userId = userId;
    }

    /**
     * @return
     */
    public int getSessionCount() {
        return sessionCount;
    }

    /**
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param i
     */
    public void setSessionCount(int i) {
        sessionCount = i;
    }

    /**
     * @param string
     */
    public void setUserId(String string) {
        userId = string;
    }

    /**
     * 
     */
    public void openSession(HttpSession httpSession) {
		latestSession = addActiveSession(httpSession);
        sessionCount ++;
    }

    /**
     * 
     */
    public void closeSession(String sessionId) {
		removeActiveSession(sessionId);
		if (sessionId.equals(latestSession.getSessionId())) {
			latestSession.setDestroyTime(System.currentTimeMillis());	
		}
    }

	public int getActiveSessionCount() {
		return activeSessions.size();
	}
    /**
     * @return
     */
    public long getLatestSessionDestroyTime() {
		return latestSession == null ? -1 : latestSession.getDestroyTime();
    }

    /**
     * @return
     */
    public long getLatestSessionCreationTime() {
		return latestSession == null ? -1 : latestSession.getCreationTime();
    }

}
