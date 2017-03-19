package com.ytp.myapplication.mode.bean;

public class User {
    public String username;
    public String pwd;
    public String keep_login;

    public User(String username, String pwd,String keep_login) {
        this.username = username;
        this.pwd = pwd;
        this.keep_login = keep_login;
    }
}