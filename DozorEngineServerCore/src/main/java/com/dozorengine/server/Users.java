package com.dozorengine.server;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author IGOR-K
 */
public class Users {

    private static class InstanceHolder {

        public static Users instance = new Users();
    }

    private Users() {
    }

    public static Users getInstance() {
        return InstanceHolder.instance;
    }

    private List<User> listClients = new CopyOnWriteArrayList<>();

    public synchronized boolean auth(User player) {
        if (!listClients.contains(player)) {
            listClients.add(player);
        }
        return true;
    }

    public User getUserByLogin(String login) {
        for (User client : listClients) {
            if (login.equals(client.getLogin())) {
                return client;
            }
        }
        return null;
    }
}
