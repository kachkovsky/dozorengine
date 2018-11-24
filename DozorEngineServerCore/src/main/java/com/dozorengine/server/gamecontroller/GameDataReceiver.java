package com.dozorengine.server.gamecontroller;

import com.dozorengine.serverinteraction.StringSocketClient;
import org.json.JSONException;

import java.io.IOException;

/**
 * @author IGOR-K
 */
public interface GameDataReceiver {

    void parseGameData(StringSocketClient client, String data, int player);

    boolean isGameFinished();

    void calculateAndSendStartData() throws JSONException, IOException;
}
