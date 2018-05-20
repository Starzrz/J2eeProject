package com.tickets.demo.service.impl;

import com.tickets.demo.Respository.OrdersRepository;
import com.tickets.demo.Respository.PerformRepository;
import com.tickets.demo.Respository.UserRepository;
import com.tickets.demo.model.Orders;
import com.tickets.demo.model.Perform;
import com.tickets.demo.model.User;
import com.tickets.demo.model.vo.PerformVo;
import com.tickets.demo.model.vo.UserVo;
import com.tickets.demo.service.MailService;
import com.tickets.demo.service.UserService;
import com.tickets.demo.util.DataTransUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${zrz} on 2018/3/6.
 */
@Component
public class UserServiceImpl implements UserService {

    //消费转换为积分的汇率
    private static final double  scoreExchange = 10;

    private static final double[] lvStandard = {0,100,233,500,1000,5000};
    @Autowired
    UserRepository userRepository;
    @Autowired
    MailService mailService;

    @Autowired
    OrdersRepository orderRespository;
    @Autowired
    PerformRepository performRepository;


    public void register(String mail, String password){
        //生成验证码
        Integer x =(int)((Math.random()*9+1)*10000);
        String text = x.toString();
        User user = new User(mail,password,0,0,0,text);
        userRepository.saveAndFlush(user);
        mailService.sendMail(mail,text);
    }

    public UserVo getUser(String email){
       User user = userRepository.findByEmail(email);
        List<Orders> orders = orderRespository.findAllByUserEmail(email);
        int orderCount = orders.size();
        int refoundCount =0;
        int finishCount =0;
        int doingCount = 0;
        int unpayCount = 0;
        for(Orders o:orders){
            int status = o.getStatus();
            if(status==0){
                unpayCount++;
            }
            if(status==1){
                doingCount++;
            }
            if(status==2){
                finishCount++;
            }
            if(status==3){
                refoundCount++;
            }
        }
        UserVo po = new UserVo(user.getEmail(),user.getLv(),user.getTotalCost(),user.getCanUse(),user.getVcode(),user.getScore(),orderCount,refoundCount,finishCount,doingCount,unpayCount);
        po.setPassword(user.getPwd());
        po.setName(user.getName());
        po.setIntroduction(user.getIntroduction());
        return po;
    }

    @Override
    public void updateUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public void costChange(String email, double cost) {
        User realUser = userRepository.findByEmail(email);
        double nowCost = realUser.getTotalCost();
        nowCost+=cost;
        realUser.setTotalCost(nowCost);
        double scoreChange = cost/scoreExchange;
        double nowScore = realUser.getScore();
        nowScore+=scoreChange;
        realUser.setScore(nowScore);
        int realLv = calLv(nowScore);
        realUser.setLv(realLv);
        userRepository.saveAndFlush(realUser);
    }

    @Override
    public void costScore(String email, double score) {
        User realUser = userRepository.findByEmail(email);
        double nowScore = realUser.getScore();
        nowScore+=score;
        realUser.setScore(nowScore);
        userRepository.saveAndFlush(realUser);
    }

    @Override
    public void cancelUser(String email) {
        User realUser = userRepository.findByEmail(email);
        realUser.setCanUse(0);
        userRepository.saveAndFlush(realUser);
    }

    @Override
    public void changeBasicInfo(String email, String name, String introducation, String password) {
        User user = userRepository.findByEmail(email);
        user.setName(name);
        user.setIntroduction(introducation);
        if(password!=null &&password.length()>0){
          user.setPwd(password);
        }
        userRepository.saveAndFlush(user);
    }

    @Override
    public double calDiscount(String email) {
        User user = userRepository.findByEmail(email);
        return DataTransUtil.calDiscount(user.getLv());
    }


    public boolean checkReg(String mail,String text){
        User realUser= userRepository.findByEmail(mail);
        if(realUser.getVcode().equals(text)){
            realUser.setCanUse(1);
            userRepository.saveAndFlush(realUser);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 根据积分计算等级
     * @param score
     * @return
     */
    private int calLv(double score){
        int length = lvStandard.length;
        for(int i=0;i<length;i++){
            double standard = lvStandard[i];
            if(score<=standard){
                return i;
            }

        }
        return length;
    }
}
