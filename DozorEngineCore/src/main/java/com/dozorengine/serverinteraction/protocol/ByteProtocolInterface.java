/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dozorengine.serverinteraction.protocol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Protocol Interface
 * @author IGOR-K
 */
public interface ByteProtocolInterface {

    /**
     * чтение из потока
     * @param reader
     * @throws IOException 
     */
    void readFromStream(DataInputStream reader) throws IOException;

    /**
     * запись в поток
     * @param writer
     * @throws IOException 
     */
    void writeToStream(DataOutputStream writer) throws IOException;
}
