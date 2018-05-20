package com.tickets.demo.model;

/**
 * Created by ${zrz} on 2018/4/2.
 */
public class SiteSell {
    private  String[] sites;
    private  int lv;
    private double totalPrice;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public SiteSell() {
    }

    public SiteSell(String[] sites, int lv) {
        this.sites = sites;
        this.lv = lv;
    }

    public String[] getSites() {
        return sites;
    }

    public void setSites(String[] sites) {
        this.sites = sites;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }
}
