package com.tickets.demo.service;

import org.springframework.stereotype.Service;

/**
 * Created by ${zrz} on 2018/3/6.
 */
@Service
public interface MailService {
    public void sendMail(String mail,String vcode);
}
