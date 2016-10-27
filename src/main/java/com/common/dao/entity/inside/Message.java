package com.common.dao.entity.inside;

/**
 * Created by root on 10/13/16.
 */
public class Message {
    private String msg;
    private String address;

    public Message(String msg, String address) {
        this.msg = msg;
        this.address = address;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String generateTextMessage(){
        return this.msg+" - message "+this.address+ " - address and this we use some stencil";
    }
}