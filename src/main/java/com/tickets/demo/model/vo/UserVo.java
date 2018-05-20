package com.tickets.demo.model.vo;

/**
 * Created by ${zrz} on 2018/3/21.
 */
public class UserVo {
    private String email;
    private int lv;
    private String password;
    private double totalCost;
    private int canUse;
    private String vcode;
    private double score;
    private int orderCount;
    private int refoundCount;
    private int finishCount;
    private int doingCount;
    private int unpayCount;

    private String name;
    private String introduction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getPassword() {
        return password;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserVo(String email, int lv, double totalCost, int canUse, String vcode, double score, int orderCount, int refoundCount, int finishCount, int doingCount, int unpayCount) {
        this.email = email;
        this.lv = lv;
        this.totalCost = totalCost;
        this.canUse = canUse;
        this.vcode = vcode;
        this.score = score;
        this.orderCount = orderCount;
        this.refoundCount = refoundCount;
        this.finishCount = finishCount;
        this.doingCount = doingCount;
        this.unpayCount = unpayCount;
    }

    public int getUnpayCount() {
        return unpayCount;
    }

    public void setUnpayCount(int unpayCount) {
        this.unpayCount = unpayCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getCanUse() {
        return canUse;
    }

    public void setCanUse(int canUse) {
        this.canUse = canUse;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getRefoundCount() {
        return refoundCount;
    }

    public void setRefoundCount(int refoundCount) {
        this.refoundCount = refoundCount;
    }

    public int getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(int finishCount) {
        this.finishCount = finishCount;
    }

    public int getDoingCount() {
        return doingCount;
    }

    public void setDoingCount(int doingCount) {
        this.doingCount = doingCount;
    }
}
