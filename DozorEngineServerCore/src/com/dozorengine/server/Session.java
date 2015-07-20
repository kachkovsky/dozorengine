package com.dozorengine.server;

import com.dozorengine.server.gamecontroller.GameDataReceiver;
import com.dozorengine.server.gamecontroller.GameImplFactory;
import com.dozorengine.serverinteraction.bean.SessionResultBean;
import com.dozorengine.serverinteraction.bean.converters.UserConverter;
import com.dozorengine.serverinteraction.parsers.ResultSessionBeanToParser;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author IGOR-K
 */
public class Session implements ReadyToReceive {

    private static final Logger log = Logger.getLogger(Session.class.getName());


    private final int id;
    private final int maxUsers;
    private final List<User> users = new CopyOnWriteArrayList<>();
    private final List<SocketReceiverWhileSession> clientsList = new CopyOnWriteArrayList<>();
    private boolean gameStarted = false;
    private GameDataReceiver game;

    public Session(int id, int maxPlayers) {
        this.id = id;
        this.maxUsers = maxPlayers;
    }

    private void createGame() {
        game = GameImplFactory.getInstance().getGameImplCreator().createGameDataReceiver(this);

    }

    public synchronized boolean addUser(User user, SocketReceiverWhileSession sc) throws JSONException {
        for (User p : users) {
            if (user.getLogin().equals(p.getLogin())) {
                log.log(Level.INFO, "Login exists cann't connect(modify this, for allow reconnect)");
                return false;
            }
        }
        users.add(user);
        clientsList.add(sc);
        sendPlayerAdded(user, sc);
        System.out.println("session:" + id + ". Player connected: " + user.getNick());
        if (users.size() >= maxUsers) {
            if (!gameStarted) {
                createGame();
                gameStarted = true;
                game.calculateAndSendStartData();
            }
        }
        return true;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getPlayer(String login) {
        for (User p : users) {
            if (login.equals(p.getLogin())) {
                return p;
            }
        }
        return null;
    }

    public int getPlayerIndex(String login) {
        int i = 0;
        for (User p : users) {
            if (login.equals(p.getLogin())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public int getId() {
        return id;
    }

    public void sendString(String s) {
        for (SocketReceiverWhileSession scwar : clientsList) {
            if (scwar.getSocketClient().isClosed()) {
                clientsList.remove(scwar);
            } else {
                scwar.getSocketClient().sendString(s);
            }
        }
    }

    private void sendPlayerAdded(User user, SocketReceiverWhileSession sc) {
        SessionResultBean srb = new SessionResultBean();
        srb.setSessionNumber(id);
        srb.setSessionPlayerIndex(getPlayerIndex(user.getLogin()));
        srb.setNicks(UserConverter.listUsersToNicks(getUsers()));
        srb.setTypeOfData("WAITING_FOR_USERS");
        JSONObject resultSessionToJson = null;
        try {
            resultSessionToJson = ResultSessionBeanToParser.resultSessionToJson(srb);
        } catch (JSONException ex) {
            log.log(Level.SEVERE, null, ex);
            sendString(null);
            return;
        }
        sc.getSocketClient().sendString(resultSessionToJson.toString());
    }

    @Override
    public boolean isReadyToReceive() {
        return gameStarted;
    }

    @Override
    public GameDataReceiver getGameDataReceiver() {
        return game;
    }


}
