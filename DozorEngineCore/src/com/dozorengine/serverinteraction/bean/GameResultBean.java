package com.dozorengine.serverinteraction.bean;

/**
 * @author IGOR-K
 */
public class GameResultBean {

    private String typeOfData;
    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getTypeOfData() {
        return typeOfData;
    }

    public void setTypeOfData(String typeOfData) {
        this.typeOfData = typeOfData;
    }

}
