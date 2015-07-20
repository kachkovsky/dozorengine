package com.dozorengine.server.socket;

import com.dozorengine.server.clients.SockClientWithActionReader;
import com.dozorengine.server.clients.StringSocketClientWithAsyncSender;
import com.dozorengine.serverinteraction.StringSocketClient;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * поток создающий серверные сокеты
 * @author IGOR-K
 */
public class SocketCreatorThread extends Thread {

    private Socket socket;

    public SocketCreatorThread(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
        StringSocketClient client = new StringSocketClientWithAsyncSender(socket);
        try {
            client.init();
            new SockClientWithActionReader(client);
        } catch (IOException ex) {
            Logger.getLogger(SocketCreatorThread.class.getName()).log(Level.SEVERE, null, ex);
            client.closeAll(); 
        }
    }
}
