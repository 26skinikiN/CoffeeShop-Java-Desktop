package com.example.laba5;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private String password;
    public ArrayList<moto> buyUser = new ArrayList<>();
    private boolean blocked=false;
    public User(String username, String password, boolean blocked) {
        this.username = username;
        this.password = password;
        this.blocked = false; // По умолчанию пользователь не блокирован
    }

    public boolean getBlocked() {
        return blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public  String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public  void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return "Логин: " + username + "; пароль: " + password + "; доступ: " + blocked  + ".";
    }

}
