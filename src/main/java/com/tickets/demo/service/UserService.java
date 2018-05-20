package com.tickets.demo.service;

import com.tickets.demo.model.User;
import com.tickets.demo.model.vo.PerformVo;
import com.tickets.demo.model.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by ${zrz} on 2018/3/6.
 */
@Service
public interface UserService {




    /**
     * 注册
     * @param mail
     * @param password
     */
    public void register(String mail,String password);

    /**
     * 注册验证
     * @param mail
     * @param text
     * @return
     */
    public boolean checkReg(String mail,String text);

    /**
     * 得到用户基本信息
     * @param email
     * @return
     */
    public UserVo getUser(String email);


    /**
     * 修改基本信息
     * @param user
     */
    public void updateUser(User user);

    /**
     * 用户消费
     * @param email 用户id
     * @param cost 消费金额
     */
    public void costChange(String email,double cost);

    /**
     * 消费积分
     * @param email id
     * @param score 积分变化
     */
    public void costScore(String email,double score);


    /**
     * 停止会员资格
     * @param email
     */
    public void cancelUser(String email);


    /**
     * 更改基本信息
     * @param email
     * @param name
     * @param introducation
     * @param password
     */
    public void changeBasicInfo(String email,String name,String introducation,String password);

    /**
     * 计算折扣
     * @param email
     * @return
     */
    public double calDiscount(String email);

}
