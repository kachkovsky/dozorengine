/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dozorengine.serverinteraction.protocol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация протокола передачи по сети через массивы байт
 * @author IGOR-K
 */
public class ByteProtocolBean implements ByteProtocolInterface {

    private final List<byte[]> list = new ArrayList<>();

    /**
     * добавить для передачи по сети
     * @param cs 
     */
    public void add(byte[] cs) {
        list.add(cs);
    }

    /**
     * получить что есть
     * @return 
     */
    public List<byte[]> getList() {
        return list;
    }

    public ByteProtocolBean() {
    }

    /**
     * чтение содержимого бина из потока
     * @param reader поток
     * @throws IOException исключения тут незачем обрабатывать
     */
    @Override
    public void readFromStream(DataInputStream reader) throws IOException {
        int sizePack = reader.readInt();
        //System.err.println("reading1:"+sizePack);
        for (int i = 0; i < sizePack; i++) {
            int size = reader.readInt();
            //System.err.println("reading2:"+size);
            byte[] buf = new byte[size];
            reader.readFully(buf);
            //System.err.println("reading3:"+buf.length);
            list.add(buf);
        }
    }

    /**
     * пишем содержимое бина в поток
     * @param writer
     * @throws IOException 
     */
    @Override
    public void writeToStream(DataOutputStream writer) throws IOException {
        writer.writeInt(list.size());
        //System.err.println("writing1:"+list.size());
        for (byte[] arr : list) {
            writer.writeInt(arr.length);
            //System.err.println("writing2:"+arr.length);
            writer.write(arr);
            //System.err.println("writing3:"+arr.length);
        }
        writer.flush();
    }

}
