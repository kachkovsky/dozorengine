package com.dozorengine.server.clients;

import com.dozorengine.serverinteraction.StringSocketClient;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author IGOR-K
 */
public class StringSocketClientWithAsyncSender extends StringSocketClient {

    protected BlockingQueue<String> queue;
    protected Sender sender;
    private Thread senderThread;

    public StringSocketClientWithAsyncSender(Socket socket) {
        super(socket);
    }

    public StringSocketClientWithAsyncSender(String server, int port) throws IOException {
        super(server, port);
    }

    @Override
    public void init() throws IOException {
        queue = new LinkedBlockingQueue<>();
        super.init();
        sender = new Sender(this, queue);
        senderThread = new Thread(sender);
        senderThread.start();
    }

    @Override
    public boolean sendString(String s) {
        if (closed) {
            return false;
        }
        try {
            queue.put(s);
        } catch (InterruptedException ex) {
            Logger.getLogger(StringSocketClient.class.getName()).log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
        return true;
    }

    protected boolean superSendString(String s) {
        return super.sendString(s);
    }

}
