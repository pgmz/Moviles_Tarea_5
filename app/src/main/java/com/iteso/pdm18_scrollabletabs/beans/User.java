package com.iteso.pdm18_scrollabletabs.beans;

public class User {

    private String name, password;
    private boolean isLogged;

    public User(){

    }

    public User(String name, String password, boolean isLogged) {
        this.name = name;
        this.password = password;
        this.isLogged = isLogged;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}
