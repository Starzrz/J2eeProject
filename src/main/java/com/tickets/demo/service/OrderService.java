package com.tickets.demo.service;

import com.tickets.demo.model.Orders;
import com.tickets.demo.model.vo.OrderVo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ${zrz} on 2018/3/20.
 */
@Service
public interface OrderService {


    public void sysBook(long performId);

    /**
     * 检查order是否超时
     */
    @Scheduled(fixedRate = 60000)
    public void checkOrder();

    /**
     * 完成订单
     * @param id
     */
    public boolean finishOrder(long id);


    /**
     * 根据id查找order
     * @param id
     * @return
     */
    public Orders getOrder(long id);

    /**
     * 找出用户的所有订单
     * @param email
     * @return
     */
    public List<Orders> findUserOrder(String email);

    /**
     * 取得用户所有的订单
     * @param email
     * @return
     */
    public List<OrderVo> findUserOrderVo(String email);

    public List<OrderVo> findUserUnpayOrder(String email);

    /**
     * 生成订单
     * @param po
     */
    public void makeOrder(OrderVo po);


    /**
     * 支付订单，返回false则支付失败
     * @param id 订单id
     * @param payId 支付账号
     * @param pwd 支付密码
     * @return
     */
    public boolean payOrder(long id,String payId,String pwd);

    public void cancelOrder(long id);



}
