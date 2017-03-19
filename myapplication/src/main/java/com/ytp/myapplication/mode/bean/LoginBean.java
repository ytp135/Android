package com.ytp.myapplication.mode.bean;

/**
 * Created by Administrator on 2017年03月10日:19:33.
 */

public class LoginBean {

    public int code;
    public String message;
    public ObjDataBean obj_data;

    public static class ObjDataBean {

        public String devplatform;
        public String expertise;
        public int    fans;
        public int    favorite_count;
        public int    followers;
        public String from;
        public int    gender;
        public int    id;
        public String jointime;
        public String latestonline;
        public String name;
        public String portrait;
        public int    score;
    }
}
