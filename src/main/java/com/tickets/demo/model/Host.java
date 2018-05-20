package com.tickets.demo.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by ${zrz} on 2018/3/20.
 */
@Entity
public class Host {

    @Id
    @Column(length =8)
    private long id;
    private String name;
    private String place;
    private String siteSize; //座位情况,用n-n表示几排几座
    @OneToMany(mappedBy = "host",cascade = CascadeType.ALL)
    private Set<Perform> performs = new HashSet<>();
    //总收入
    private double income;
    //总盈利
    private double profit;


    private int vip1;

    private int vip2;

    private String introduction;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }


    @ElementCollection
    @CollectionTable(name="HINCOME")
    @MapKeyColumn(name="IN_DATE")
    @Column(name="IN_MONEY")
    private Map<Date,Double> incomes ;

    @ElementCollection
    @CollectionTable(name="HPROFIT")
    @MapKeyColumn(name="PR_DATE")
    @Column(name="PR_MONEY")
    private Map<java.sql.Date,Double> profits ;

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

    private int isRatify;//是否审批通过，0表示注册，1表示修改，2表示通过，3表示不通过；

    public Host() {
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSiteSize() {
        return siteSize;
    }

    public void setSiteSize(String siteSize) {
        this.siteSize = siteSize;
    }

    public Set<Perform> getPerforms() {
        return performs;
    }

    public void setPerforms(Set<Perform> performs) {
        this.performs = performs;
    }

    public int getIsRatify() {
        return isRatify;
    }

    public void setIsRatify(int isRatify) {
        this.isRatify = isRatify;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
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

    public Host(long id, String name, String place, String siteSize, Set<Perform> performs, double income, double profit, int vip1, int vip2, String introduction, Map<Date, Double> incomes, Map<Date, Double> profits, int isRatify) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.siteSize = siteSize;
        this.performs = performs;
        this.income = income;
        this.profit = profit;
        this.vip1 = vip1;
        this.vip2 = vip2;
        this.introduction = introduction;
        this.incomes = incomes;
        this.profits = profits;
        this.isRatify = isRatify;
    }
}
