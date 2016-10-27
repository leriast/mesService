package com.common.dao.entity.incoming;

import java.io.Serializable;

/**
 * Created by root on 10/21/16.
 */
public class Autor implements Serializable {
    private int id;
    private String login;
    private String password;

    public Autor(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}