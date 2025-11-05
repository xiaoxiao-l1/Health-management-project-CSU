package com.example.healthapp.bean;

import java.io.Serializable;

public class UserBean implements Serializable {
    private String id;
    private String name;
    private String psw;
    private String sex;
    private String des;
    private String nickname;

    public UserBean() {
    }


    public UserBean(String id, String name, String psw, String sex, String des, String nickname) {
        this.id = id;
        this.name = name;
        this.psw = psw;
        this.sex = sex;
        this.des = des;
        this.nickname = nickname;
    }

    public UserBean(String name, String psw, String sex, String des, String nickname) {
        this.name = name;
        this.psw = psw;
        this.sex = sex;
        this.des = des;
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
