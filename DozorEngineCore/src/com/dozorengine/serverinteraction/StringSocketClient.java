package com.dozorengine.serverinteraction;

import com.dozorengine.serverinteraction.protocol.StringProtocolBean;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author IGOR-K
 */
public class StringSocketClient extends SocketClient {

    private static final Logger log = Logger.getLogger(StringSocketClient.class.getName());

    private boolean logMessages = false;

    public StringSocketClient(Socket socket) {
        super(socket);
    }

    public StringSocketClient(String server, int port) throws IOException {
        super(server, port);
    }

    public void setLogging(boolean logMessages) {
        this.logMessages = logMessages;
    }

    public boolean sendString(String s) {
        StringProtocolBean spb = new StringProtocolBean();
        spb.addString(s);
        if (logMessages) {
            log.log(Level.INFO, "sending message: " + s);
        }
        try {
            sendData(spb);
            return true;
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
            closeAll();
            return false;
        }
    }

    public String receiveString() {
        StringProtocolBean spb = new StringProtocolBean();
        try {
            readData(spb);
            String s = spb.getString();
            if (logMessages) {
                log.log(Level.INFO, "receive message: " + s);
            }
            return s;
        } catch (Throwable ex) {
            log.log(Level.SEVERE, null, ex);
            closeAll();
            return null;
        }
    }
}
