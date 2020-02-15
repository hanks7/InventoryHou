package com.easyway.mismclient.base;

import java.io.Serializable;

/**
 * Created by CC on 2016/12/11.
 */

public class BaseModel implements Serializable {

    /**
     * Code : 1
     * Message : 用户名密码不正确!
     */

    private int Code;
    private String Message;

    public BaseModel(int code, String message) {
        Code = code;
        Message = message;
    }

    public BaseModel() {

    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
}
