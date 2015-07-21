package com.dozorengine.serverinteraction.parsers;

import com.dozorengine.serverinteraction.bean.SessionResultBean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author IGOR-K
 */
public class ResultSessionBeanToParser {

    public static JSONObject resultSessionToJson(SessionResultBean bean) throws JSONException {
        JSONObject o = new JSONObject();

        GameResultBeanParser.addGameResultBeanDataToJson(o, bean);
        o.put(JsonConsts.CURRENT_PLAYER, bean.getSessionPlayerIndex());
        o.put(JsonConsts.SESSION_ID, bean.getSessionNumber());
        JSONArray jsonArray = new JSONArray(bean.getNicks());
        o.put(JsonConsts.NICKS, jsonArray);
        return o;
    }
}
