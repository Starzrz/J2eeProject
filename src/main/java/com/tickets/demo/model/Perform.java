package com.tickets.demo.model;



import com.tickets.demo.model.Enum.PerformType;

import javax.persistence.*;
import java.util.*;

/**
 * Created by ${zrz} on 2018/3/20.
 * 每场演出的bean
 */
@Entity
public class Perform{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length =7)
    private long id;
    @Enumerated(EnumType.STRING)
    private PerformType performType;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date performTime; //演出时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime; //演出时间

    private String place;  //演出地点
    private String describes; //描述

    @ManyToOne(cascade = CascadeType.ALL)
    private Host host;
    //已经被选座的座位号
    //以，分割每张票，以-分割横列
    private String bookSites="";
    @ElementCollection
    @CollectionTable(name="SITEPRICE")
    @MapKeyColumn(name="SITEC")
    @Column(name="SPRICE")
    private Map<Integer,Double> sitePrice = new HashMap<>();  //座位对应价格，第几排多少钱


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


    @OneToMany(cascade = CascadeType.ALL)
    private Set<Orders> orders = new HashSet<>();



    public Perform() {
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public PerformType getPerformType() {
        return performType;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public void setPerformType(PerformType performType) {
        this.performType = performType;
    }

    public Perform(PerformType performType, String name, Date performTime, String place, String describes, Host host, Set<Orders> orders) {
        this.performType = performType;
        this.name = name;
        this.performTime = performTime;
        this.place = place;
        this.describes = describes;
        this.host = host;
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPerformTime() {
        return performTime;
    }

    public void setPerformTime(Date performTime) {
        this.performTime = performTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }
}
