package com.tickets.demo.service.impl;

import com.tickets.demo.service.MailService;
import javafx.application.Application;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by ${zrz} on 2018/3/6.
 */
@Component
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendMail(String mail,String vcode){
        String mailUrl = "http://localhost:8080/confirm?vcode="+vcode+"&email="+mail;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("starzrz@qq.com");
        message.setTo(mail);
        message.setSubject("测试");
        message.setText("请点击以下网址确认激活您的账号\n"+mailUrl);
        mailSender.send(message);
    }

}