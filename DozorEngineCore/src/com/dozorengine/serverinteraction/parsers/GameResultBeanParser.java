package com.dozorengine.serverinteraction.parsers;

import com.dozorengine.serverinteraction.bean.GameResultBean;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author IGOR-K
 */
public class GameResultBeanParser {

    public static void addJsonDataToGameResultBean(JSONObject gameObj, GameResultBean grb) throws JSONException {
        if (gameObj.has(JsonConsts.ERROR)) {
            grb.setErrorCode(gameObj.getString(JsonConsts.ERROR));
        }
        if (gameObj.has(JsonConsts.RESPONSE_TYPE)) {
            grb.setTypeOfData(gameObj.getString(JsonConsts.RESPONSE_TYPE));
        }
    }

    public static void addGameResultBeanDataToJson(JSONObject gameObj, GameResultBean grb) throws JSONException {
        if (grb.getErrorCode() != null) {
            gameObj.put(JsonConsts.ERROR, grb.getErrorCode());
        }
        if (grb.getTypeOfData() != null) {
            gameObj.put(JsonConsts.RESPONSE_TYPE, grb.getTypeOfData());
        }
    }
}
