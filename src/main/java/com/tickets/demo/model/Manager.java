package com.tickets.demo.model;

import org.hibernate.annotations.MapKeyType;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${zrz} on 2018/3/23.
 */
@Entity
public class Manager {
    @Id
    private String userId="admin";
    private String pwd;
    private double income; //收入
    private double expend; //支出
    private double allProfit; //盈利

    @ElementCollection
    @CollectionTable(name="MINCOME")
    @MapKeyColumn(name="IN_DATE")
    @Column(name="IN_MONEY")
    private Map<Date,Double> incomes = new HashMap<>();


    @ElementCollection
    @CollectionTable(name="MPROFIT")
    @MapKeyColumn(name="PR_DATE")
    @Column(name="PR_MONEY")
    private Map<java.sql.Date,Double> profits = new HashMap<>();

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpend() {
        return expend;
    }

    public void setExpend(double expend) {
        this.expend = expend;
    }

    public double getAllProfit() {
        return allProfit;
    }

    public void setAllProfit(double allProfit) {
        this.allProfit = allProfit;
    }


    public Manager() {
    }

    public Manager(String userId, String pwd, double income, double expend, double allProfit) {
        this.userId = userId;
        this.pwd = pwd;
        this.income = income;
        this.expend = expend;
        this.allProfit = allProfit;

    }
}
