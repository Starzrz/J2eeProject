package com.tickets.demo.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ${zrz} on 2018/3/5.
 */
@Entity
public class User {
    @Id
    private String email;
    private String pwd;
    private int lv;
    private double totalCost;
    private int canUse=0;
    private String vcode;
    private double score=0;
    private String name;
    private String introduction;

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

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Orders> ordersSet = new HashSet<>();

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getVcode() {
        return vcode;
    }

    public User(String email, String pwd, int lv, double totalCost, int canUse, String vcode) {
        this.email = email;
        this.pwd = pwd;
        this.lv = lv;
        this.totalCost = totalCost;
        this.canUse = canUse;
        this.vcode = vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public User() {

    }

    public int getCanUse() {
        return canUse;
    }

    public void setCanUse(int canUse) {
        this.canUse = canUse;
    }

    public Set<Orders> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(Set<Orders> ordersSet) {
        this.ordersSet = ordersSet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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
}
