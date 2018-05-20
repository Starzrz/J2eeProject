package com.tickets.demo.model.vo;

/**
 * Created by ${zrz} on 2018/3/27.
 */
public class userInfo {
    String name;
    String introduction;
    String newPass;

    public userInfo(String name, String introduction, String newPass) {
        this.name = name;
        this.introduction = introduction;
        this.newPass = newPass;
    }

    public userInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
}
