package com.tickets.demo.model.vo;

import com.tickets.demo.model.Host;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ${zrz} on 2018/3/23.
 */
public class ManagerVo {
    private String managerId;

    private Map<Date,Double> incomes;
    private Map<Date,Double> profits;

    private ArrayList<Host> hosts;
    private int memberCount;

    private int hostCount;

    private int performCount;

    private int orderCount;

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getHostCount() {
        return hostCount;
    }

    public void setHostCount(int hostCount) {
        this.hostCount = hostCount;
    }

    public int getPerformCount() {
        return performCount;
    }

    public void setPerformCount(int performCount) {
        this.performCount = performCount;
    }

    public String getManagerId() {
        return managerId;
    }

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

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }


    public ArrayList<Host> getHosts() {
        return hosts;
    }

    public void setHosts(ArrayList<Host> hosts) {
        this.hosts = hosts;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public ManagerVo() {
    }

    public ManagerVo(String managerId, Map<Date, Double> incomes, Map<Date, Double> profits, int memberCount) {
        this.managerId = managerId;
        this.incomes = incomes;
        this.profits = profits;
        this.memberCount = memberCount;
    }
}
