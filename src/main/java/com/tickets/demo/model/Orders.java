package com.tickets.demo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

/**
 * Created by ${zrz} on 2018/3/16.
 */
@Entity
public class Orders {
    @ManyToOne(cascade = CascadeType.ALL)
    private Perform perform;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderTime;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    //选座的座位号
    //以，分割每张票，以-分割横列
    private String sites;
    //数量
    private int count;
    //单价
    private double unitPrice;
    //优惠
    private double preferential;
    //总价
    private double totalPrice;
    //订单状态，0表示未付款，1表示已付款，2表示已完成，3表示已取消
    private int status;

    private String payId; //付款账号

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public Perform getPerform() {
        return perform;
    }

    public void setPerform(Perform perform) {
        this.perform = perform;
    }

    public Orders() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }



    public String getSites() {
        return sites;
    }

    public void setSites(String sites) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Orders(Perform perform, Date orderTime, String sites, int count, double unitPrice, double preferential, double totalPrice, int status) {
        this.perform = perform;

        this.orderTime = orderTime;
        this.sites = sites;
        this.count = count;
        this.unitPrice = unitPrice;
        this.preferential = preferential;
        this.totalPrice = totalPrice;
        this.status = status;
    }
}
