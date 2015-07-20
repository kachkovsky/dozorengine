package com.dozorengine.server;

import com.dozorengine.server.bean.INick;

/**
 * @author IGOR-K
 */
public class User implements INick {
    private String login;
    private String nick;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
