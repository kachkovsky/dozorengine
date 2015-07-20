package com.dozorengine.server.clients;

import com.dozorengine.server.User;
import com.dozorengine.server.Users;
import com.dozorengine.server.ReadyToReceive;
import com.dozorengine.server.Session;
import com.dozorengine.server.Sessions;
import com.dozorengine.server.SocketReceiverWhileSession;
import com.dozorengine.serverinteraction.StringSocketClient;
import com.dozorengine.serverinteraction.bean.CreateSessionBean;
import com.dozorengine.serverinteraction.bean.JoinSessionBean;
import com.dozorengine.serverinteraction.bean.converters.UserConverter;
import com.dozorengine.serverinteraction.parsers.SessionBeanFromParser;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author IGOR-K
 */
public class SockClientWithActionReader implements Runnable, SocketReceiverWhileSession {

    private final StringSocketClient client;
    private int gamePlayerIndex = -1;

    public SockClientWithActionReader(StringSocketClient client) {
        this.client = client;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public StringSocketClient getSocketClient() {
        return client;
    }

    @Override
    public void run() {
        Session session = null;
        try {
            String socketData = client.receiveString();
            if (socketData == null) {
                return;
            }
            System.out.println(socketData);
            boolean isCreateBean = false;
            CreateSessionBean csBean = null;
            JoinSessionBean jsBean = null;
            try {
                JSONObject o = new JSONObject(socketData);
                isCreateBean = SessionBeanFromParser.isCreateSessionBean(o);
                if (isCreateBean) {
                    csBean = SessionBeanFromParser.createBeanFromJson(o);
                } else {
                    jsBean = SessionBeanFromParser.joinBeanFromJson(o);
                }
            } catch (JSONException ex) {
                Logger.getLogger(SockClientWithActionReader.class.getName()).log(Level.SEVERE, null, ex);
                client.closeAll();
                return;
            }

            User p = UserConverter.toUser(isCreateBean ? csBean : jsBean);
            if (!Users.getInstance().auth(p)) {
                System.out.println("auth failed" + p.getLogin());
                client.closeAll();
                return;
            }
            User userByLogin = Users.getInstance().getUserByLogin(p.getLogin());
            if (userByLogin == null) {
                System.out.println("Player is null");
                client.closeAll();
                return;
            }
            if (isCreateBean) {
                if (csBean.getMaxPlayers() < 2) {
                    System.out.println("Incorrect player count:" + csBean.getMaxPlayers());
                    client.closeAll();
                    return;
                }
                session = Sessions.getInstance().createSession(csBean.getMaxPlayers());
                System.out.println("create session:" + session.getId());
            } else {
                session = Sessions.getInstance().getSession(jsBean.getSessionNumber());
            }
            if (!session.addUser(userByLogin, this)) {
                System.out.println("Player not added");
                return;
            }
            gamePlayerIndex = session.getPlayerIndex(userByLogin.getLogin());
        } catch (Throwable e) {
            Logger.getLogger(SockClientWithActionReader.class.getName()).log(Level.SEVERE, null, e);
            client.closeAll();
            return;
        }
        onStartReceive(session);
    }

    public void onStartReceive(ReadyToReceive readyToReceive) {
        do {
            String socketData = client.receiveString();
            if (socketData == null) {
                return;
            }
            if (!readyToReceive.isReadyToReceive()) {
                System.err.println("Data received but no need" + client.getIp());
                client.closeAll();
                return;
            }
            readyToReceive.getGameDataReceiver().parseGameData(client, socketData, gamePlayerIndex);
        } while (!readyToReceive.getGameDataReceiver().isGameFinished());
        System.out.println("Game finished for user:" + client.getIp());
    }
}
