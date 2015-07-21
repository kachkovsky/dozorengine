package com.dozorengine.server.gamecontroller;

/**
 *
 * @author IGOR-K
 */
public class GameImplFactory {

    private static class InstanceHolder {
        public static GameImplFactory instance = new GameImplFactory();
    }

    private GameImplFactory() {
    }

    public static GameImplFactory getInstance() {
        return InstanceHolder.instance;
    }

    private GameImplCreator gic; 
    
    public GameImplCreator getGameImplCreator() {
        return gic;
    }

    public void setGameImplCreator(GameImplCreator gic) {
        this.gic = gic;
    }

}
