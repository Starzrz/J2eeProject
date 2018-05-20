package com.tickets.demo.model.vo;

import com.tickets.demo.model.Enum.PerformType;

import java.util.Date;
import java.util.Map;

/**
 * Created by ${zrz} on 2018/3/24.
 */
public class PerformVo {
    private long id;
    private PerformType performType;
    private String name;
    private String performTime; //演出时间
    private String endTime; //结束时间
    private String place;  //演出地点
    private String describes; //描述
    private long hostId; //主办方id
    private Map<Integer,Double> sitePrice;  //座位对应价格，第几排多少钱
    private String bookSites;
    private String hostPlace;

    private String[] siteMap;

    public String[] getSiteMap() {
        return siteMap;
    }

    public void setSiteMap(String[] siteMap) {
        this.siteMap = siteMap;
    }

    public String getHostPlace() {
        return hostPlace;
    }

    public void setHostPlace(String hostPlace) {
        this.hostPlace = hostPlace;
    }

    private double price;
    private double vipPrice;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(double vipPrice) {
        this.vipPrice = vipPrice;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PerformType getPerformType() {
        return performType;
    }

    public void setPerformType(PerformType performType) {
        this.performType = performType;
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

    public String getPerformTime() {
        return performTime;
    }

    public void setPerformTime(String performTime) {
        this.performTime = performTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public long getHostId() {
        return hostId;
    }

    public void setHostId(long hostId) {
        this.hostId = hostId;
    }

    public Map<Integer, Double> getSitePrice() {
        return sitePrice;
    }

    public void setSitePrice(Map<Integer, Double> sitePrice) {
        this.sitePrice = sitePrice;
    }

    public String getBookSites() {
        return bookSites;
    }

    public void setBookSites(String bookSites) {
        this.bookSites = bookSites;
    }

    public PerformVo() {
    }

    public PerformVo(long id, PerformType performType, String name, String performTime, String endTime, String place, String describes, long hostId, Map<Integer, Double> sitePrice, String bookSites, String hostPlace, double price, double vipPrice) {
        this.id = id;
        this.performType = performType;
        this.name = name;
        this.performTime = performTime;
        this.endTime = endTime;
        this.place = place;
        this.describes = describes;
        this.hostId = hostId;
        this.sitePrice = sitePrice;
        this.bookSites = bookSites;
        this.hostPlace = hostPlace;
        this.price = price;
        this.vipPrice = vipPrice;
    }
}
