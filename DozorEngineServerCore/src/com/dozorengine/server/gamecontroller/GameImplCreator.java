package com.dozorengine.server.gamecontroller;

import com.dozorengine.server.Session;

/**
 * @author IGOR-K
 */
public interface GameImplCreator {

    GameDataReceiver createGameDataReceiver(Session session);
}
