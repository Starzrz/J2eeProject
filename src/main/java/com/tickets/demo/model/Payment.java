package com.tickets.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ${zrz} on 2018/3/21.
 */
@Entity
public class Payment {
    @Id
    private String userId;
    private String password;
    private double balance; //余额


    public Payment() {
    }

    public Payment(String userId, String password, double balance) {
        this.userId = userId;
        this.password = password;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
