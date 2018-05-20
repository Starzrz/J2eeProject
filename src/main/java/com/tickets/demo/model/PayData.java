package com.tickets.demo.model;

/**
 * Created by ${zrz} on 2018/4/1.
 */
public class PayData {
    private String password;
    private String orderId;

    public PayData(String password, String orderId) {
        this.password = password;
        this.orderId = orderId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public PayData() {
    }
}
