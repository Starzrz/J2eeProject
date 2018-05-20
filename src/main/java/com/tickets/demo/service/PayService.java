package com.tickets.demo.service;

import org.springframework.stereotype.Service;

/**
 * Created by ${zrz} on 2018/3/21.
 */
@Service
public interface PayService {
    /**
     * 付款的方法，如果用户名或密码错误则返回false，付款成功返回true
     * @param userId 付款账号
     * @param password 付款密码
     * @param money 付款的金额
     * @return
     */
    public boolean pay(String userId,String password,double money);


    /**
     * 退款的方法
     * @param userId 退款账号
     * @param money  密码
     * @return
     */
    public void refund(String userId,double money);
}
