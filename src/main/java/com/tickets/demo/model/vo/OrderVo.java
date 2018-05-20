package com.tickets.demo.model.vo;

import com.tickets.demo.model.Orders;
import com.tickets.demo.util.DataTransUtil;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ${zrz} on 2018/3/16.
 */
public class OrderVo {


    private String id;
    private String orderTime;
    private String ticketName;

    private String userEmail;

    //选座的座位号

    private String[] sites;
    //数量
    private int count;
    //单价
    private double unitPrice;
    //优惠
    private double preferential=1;
    //总价
    private double totalPrice;
    //是否付款,0没付款,1付款，2完成
    private String status;
    //表演的名字
    private String performName;
    //表演的地点



    private String place;
    //表演的时间
    private String performTime;

    private String endTime;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    private String hostPlace;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHostPlace() {
        return hostPlace;
    }

    public void setHostPlace(String hostPlace) {
        this.hostPlace = hostPlace;
    }

    public String getPerformTime() {
        return performTime;
    }

    public void setPerformTime(String performTime) {
        this.performTime = performTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public OrderVo(String id, String orderTime, String ticketName, String userEmail, String[] sites, int count, double unitPrice, double preferential, double totalPrice, String status, String performName, String place, String performTime, String hostPlace) {
        this.id = id;
        this.orderTime = orderTime;
        this.ticketName = ticketName;
        this.userEmail = userEmail;
        this.sites = sites;
        this.count = count;
        this.unitPrice = unitPrice;
        this.preferential = preferential;
        this.totalPrice = totalPrice;
        this.status = status;
        this.performName = performName;
        this.place = place;
        this.performTime = performTime;
        this.hostPlace = hostPlace;
    }

    public OrderVo() {
    }


    public String getPerformName() {
        return performName;
    }

    public void setPerformName(String performName) {
        this.performName = performName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }


    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String[] getSites() {
        return sites;
    }

    public void setSites(String[] sites) {
        this.sites = sites;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getPreferential() {
        return preferential;
    }

    public void setPreferential(double preferential) {
        this.preferential = preferential;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
