package com.common.dao.entity.incoming;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by root on 10/13/16.
 */
public class Recipient implements Serializable {
    private int id;


    private String address;
    private Param param;

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }

    public Recipient(String address, Param param) {

        this.address = address;
        this.param = param;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Recipient(String s, String put) {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public Recipient(String address) {

        this.address = address;
    }


}