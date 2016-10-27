package com.common.dao.entity.incoming;

import java.io.Serializable;

/**
 * Created by root on 10/21/16.
 */
public class Param implements Serializable {
    private String name;
    private String valuse;
    public Param(){}
    public Param(String name, String valuse) {
        this.name = name;
        this.valuse = valuse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValuse() {
        return valuse;
    }

    public void setValuse(String valuse) {
        this.valuse = valuse;
    }
}