package com.dozorengine.serverinteraction.parsers;

import com.dozorengine.serverinteraction.bean.CreateSessionBean;
import com.dozorengine.serverinteraction.bean.JoinSessionBean;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author IGOR-K
 */
public class SessionBeanFromParser {

    public static boolean isCreateSessionBean(JSONObject o) {
        return o.has(JsonConsts.PLAYERS_COUNT);
    }

    public static CreateSessionBean createBeanFromJson(JSONObject o) throws JSONException {
        CreateSessionBean csb = new CreateSessionBean();
        csb.setLogin(o.getString(JsonConsts.LOGIN));
        csb.setNick(o.optString(JsonConsts.NICK));
        csb.setMaxPlayers(o.optInt(JsonConsts.PLAYERS_COUNT, 2));
        return csb;
    }

    public static JoinSessionBean joinBeanFromJson(JSONObject o) throws JSONException {
        JoinSessionBean sb = new JoinSessionBean();
        sb.setLogin(o.getString(JsonConsts.LOGIN));
        sb.setNick(o.optString(JsonConsts.NICK));
        sb.setSessionNumber(o.optInt(JsonConsts.SESSION_ID, 2));
        return sb;
    }
}
