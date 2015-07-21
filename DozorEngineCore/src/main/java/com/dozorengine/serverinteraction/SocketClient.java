package com.dozorengine.serverinteraction;

import com.dozorengine.serverinteraction.protocol.ByteProtocolInterface;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author IGOR-K
 */
public class SocketClient {

    private Socket socket;

    protected volatile boolean closed = false;
    private DataOutputStream out;
    private DataInputStream in;

    public SocketClient(Socket socket) {
        this.socket = socket;
    }

    public SocketClient(String server, int port) throws IOException {
        this(new Socket(server, port));
    }

    public void init() throws IOException {
        closed = false;
        System.out.println("Opened socket with ip:" + getIp());
        out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    public void sendData(ByteProtocolInterface protocol) throws IOException {
        protocol.writeToStream(out);
    }

    public void readData(ByteProtocolInterface protocol) throws IOException {
        protocol.readFromStream(in);
    }

    public synchronized void closeAll() {
        if (!closed) {
            closed = true;
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Closed socket with ip:" + getIp());
        }

    }

    public boolean isClosed() {
        return closed;
    }

    public String getIp() {
        return socket.getInetAddress().getHostAddress();
    }
}
