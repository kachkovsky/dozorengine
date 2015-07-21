package com.dozorengine.server.clients;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Серверный отправитель сообщений
 *
 * @author IGOR-K
 */
public class Sender implements Runnable {

    private final StringSocketClientWithAsyncSender scwas;
    private final BlockingQueue<String> queue;

    public Sender(StringSocketClientWithAsyncSender scwas, BlockingQueue<String> queue) {
        this.scwas = scwas;
        this.queue = queue;
    }

    /**
     * отправка сообщений
     */
    @Override
    public void run() {
        boolean close = false;
        while (!scwas.isClosed() && !close) {
            String take = null;
            try {
                do {
                    take = queue.poll(10, TimeUnit.SECONDS);
                } while (!scwas.isClosed() && take == null);
            } catch (InterruptedException ex) {
                Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
                close = true;
                continue;
            }
            if (take == null) {
                close = true;
            } else {
                scwas.superSendString(take);
            }
        }
        scwas.closeAll();
        queue.clear();
        System.out.println("close sender by 'close':" + close);
    }

}
