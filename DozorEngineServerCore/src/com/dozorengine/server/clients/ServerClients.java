package com.dozorengine.server.clients;

import com.dozorengine.serverinteraction.SocketClient;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author IGOR-K
 */
public class ServerClients {

    private static class InstanceHolder {
        public static ServerClients instance = new ServerClients();
    }

    private ServerClients() {
    }

    public static ServerClients getInstance() {
        return InstanceHolder.instance;
    }

    private List<SocketClient> listClients = new CopyOnWriteArrayList<SocketClient>();

    public void add(SocketClient client) {
        listClients.add(client);
    }

}
