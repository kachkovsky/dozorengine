/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dozorengine.serverinteraction.protocol;

import com.dozorengine.utils.ByteConverter;

/**
 *
 * @author IGOR-K
 */
public class StringProtocolBean extends ByteProtocolBean {
    
    public void addString(String string){
        add(ByteConverter.stringToByteArray(string));
    }
    
    public String getString(){
        return ByteConverter.byteArrayToString(getList().get(0));
    }
    
}
