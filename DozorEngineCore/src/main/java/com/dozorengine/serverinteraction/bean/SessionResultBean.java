package com.dozorengine.serverinteraction.bean;

import java.util.List;

/**
 * @author IGOR-K
 */
public class SessionResultBean extends GameResultBean {

    private int sessionPlayerIndex;
    private int sessionNumber;

    private List<String> nicks;

    public int getSessionPlayerIndex() {
        return sessionPlayerIndex;
    }

    public void setSessionPlayerIndex(int sessionPlayerIndex) {
        this.sessionPlayerIndex = sessionPlayerIndex;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public List<String> getNicks() {
        return nicks;
    }

    public void setNicks(List<String> nicks) {
        this.nicks = nicks;
    }
}
