package com.tickets.demo.model.vo;

import java.sql.Date;
import java.util.Map;

/**
 * Created by ${zrz} on 2018/3/24.
 */
public class HostVo {
    private String hostId;
    private String name;
    private String place;
    private int xsites;
    private int ysites;
    private String introduction;
    private int vip1;
    private int vip2;
    private int status;

    private Map<Date,Double> incomes;
    private Map<Date,Double> profits;

    public Map<Date, Double> getIncomes() {
        return incomes;
    }

    public void setIncomes(Map<Date, Double> incomes) {
        this.incomes = incomes;
    }

    public Map<Date, Double> getProfits() {
        return profits;
    }

    public void setProfits(Map<Date, Double> profits) {
        this.profits = profits;
    }

    private int performCount;

    private int orderCount;

    public int getPerformCount() {
        return performCount;
    }

    public void setPerformCount(int performCount) {
        this.performCount = performCount;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public HostVo(String hostId, String name, String place, int xsites, int ysites, String introduction, int vip1, int vip2) {
        this.hostId = hostId;
        this.name = name;
        this.place = place;
        this.xsites = xsites;
        this.ysites = ysites;
        this.introduction = introduction;
        this.vip1 = vip1;
        this.vip2 = vip2;
    }

    public int getVip1() {
        return vip1;
    }

    public void setVip1(int vip1) {
        this.vip1 = vip1;
    }

    public int getVip2() {
        return vip2;
    }

    public void setVip2(int vip2) {
        this.vip2 = vip2;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }


    public HostVo() {
    }

    public int getXsites() {
        return xsites;
    }

    public void setXsites(int xsites) {
        this.xsites = xsites;
    }

    public int getYsites() {
        return ysites;
    }

    public void setYsites(int ysites) {
        this.ysites = ysites;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}
