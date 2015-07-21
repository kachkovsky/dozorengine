package com.dozorengine.serverinteraction.bean;

/**
 * @author IGOR-K
 */
public class CreateSessionBean extends SessionBean {

    private int maxPlayers;

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}
