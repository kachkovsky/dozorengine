package com.dozorengine.server.games;

/**
 *
 * @author IGOR-K
 */
public class ServerGame<T> {
    
    private int session;
    private T gameInfo;

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public T getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(T gameInfo) {
        this.gameInfo = gameInfo;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ServerGame){
            return getSession() == ((ServerGame)obj).getSession();
        }
        return false;
    }
    
    
}
