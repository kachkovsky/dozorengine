package com.dozorengine.server;

import com.dozorengine.server.gamecontroller.GameDataReceiver;

/**
 * @author IGOR-K
 */
public interface ReadyToReceive {
    boolean isReadyToReceive();

    GameDataReceiver getGameDataReceiver();
}
