package com.dozorengine.serverinteraction.bean.converters;

import com.dozorengine.server.User;
import com.dozorengine.serverinteraction.bean.SessionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IGOR-K
 */
public class UserConverter {

    public static User toUser(SessionBean csBean) {
        User u = new User();
        u.setLogin(csBean.getLogin());
        u.setNick(csBean.getNick());
        return u;
    }

    public static List<String> listUsersToNicks(List<User> users) {
        List<String> list = new ArrayList<>(users.size());
        for (User u : users) {
            list.add(u.getNick());
        }
        return list;
    }
}
