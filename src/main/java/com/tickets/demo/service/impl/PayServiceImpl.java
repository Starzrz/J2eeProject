package com.tickets.demo.service.impl;

import com.tickets.demo.Respository.PayRepository;
import com.tickets.demo.model.Payment;
import com.tickets.demo.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ${zrz} on 2018/3/21.
 */
@Component
public class PayServiceImpl implements PayService {
    @Autowired
    private PayRepository payRepository;
    @Override
    public boolean pay(String userId, String password, double money) {
        Payment payment = payRepository.findByUserId(userId);
        if(payment==null){
            return false;
        }
        String realPwd = payment.getPassword();
        if(!password.equals(realPwd)){
            return false;
        }
        double balance = payment.getBalance();
        if(balance<money){
            return false;
        }
        balance-=money;
        payment.setBalance(balance);
        payRepository.saveAndFlush(payment);
        return true;
    }

    @Override
    public void refund(String userId, double money) {
        Payment payment = payRepository.findByUserId(userId);
        double balance = payment.getBalance();
        balance+=money;
        payment.setBalance(balance);
        payRepository.saveAndFlush(payment);
    }
}
