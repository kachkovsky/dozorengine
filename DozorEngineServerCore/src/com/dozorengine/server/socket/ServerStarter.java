package com.dozorengine.server.socket;

import java.io.IOException;
import java.net.ServerSocket;

import com.dozorengine.server.properties.ServerProperties;
import com.dozorengine.serverinteracrion.consts.Constants;

/**
 * Серверная точка входа, раздает сокеты
 *
 * @author IGOR-K
 */
public class ServerStarter {


    public static void startServer() {
        int portNumber = Constants.PORT;
        try {
            ServerProperties prop = ServerProperties.getInstance();
            String property = prop.getProperty("port");

            portNumber = Integer.parseInt(property);
            //System.err.println("can not find port in property file");
        } catch (Exception e) {
            System.err.println("can not load config");
        }
        boolean listening = true;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("start listen");
            while (listening) {
                new SocketCreatorThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }

    /**
     * Серверная точка входа, раздает сокеты
     *
     * @param args
     */
    public static void main(String[] args) {
        startServer();
    }
}
