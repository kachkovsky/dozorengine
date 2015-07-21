package com.dozorengine.server;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author IGOR-K
 */
public class Sessions {

    private static class InstanceHolder {

        public static Sessions instance = new Sessions();
    }

    private Sessions() {
    }

    public static Sessions getInstance() {
        return InstanceHolder.instance;
    }

    private List<Session> listSessions = new CopyOnWriteArrayList<>();

    public synchronized Session createSession(int maxPlayers) {
        Session session = new Session(listSessions.size(), maxPlayers);
        listSessions.add(session);
        return session;
    }

    public Session getSession(int id) {
        return listSessions.get(id);
    }
}
