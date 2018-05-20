package com.tickets.demo.controller;

import com.tickets.demo.model.PayData;
import com.tickets.demo.model.User;
import com.tickets.demo.model.vo.OrderVo;
import com.tickets.demo.model.vo.PerformVo;
import com.tickets.demo.service.OrderService;
import com.tickets.demo.service.PayService;
import com.tickets.demo.service.PerformService;
import com.tickets.demo.service.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import java.util.Date;
import java.util.List;

/**
 * Created by ${zrz} on 2018/4/1.
 */
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    PerformService performService;
    @Autowired
    UserService userService;


    @RequestMapping("/order/finish")
    @ResponseBody
    public String checkIn(@RequestParam("id") String orderId){
         boolean b = orderService.finishOrder(Long.parseLong(orderId));
        if(b){
            return "right";
        }
        else {
            return "false";
        }
    }

    @RequestMapping("confirmOrder.html")
    public String confirmOrder(){
        return "/confirmOrder";
    }

    @RequestMapping("orderList.html")
    public String orderList(){
        return "/9view/orderList";
    }

    @RequestMapping("unpayList.html")
    public String unpayList(){
        return "/9view/unpayList";
    }

    @RequestMapping("/order/getUnpayOrder")
    @ResponseBody
    public List<OrderVo> getUserUnpayOrder(HttpSession session){
        String email =(String ) session.getAttribute("id");
        return orderService.findUserUnpayOrder(email);
    }


    @RequestMapping("/order/cancelOrder")
    @ResponseBody
    public String cancelOrder(@RequestParam("orderId") String orderId){
        orderService.cancelOrder(Long.parseLong(orderId));
        return "right";
    }

    @RequestMapping("/order/payOrder")
    @ResponseBody
    public String payOrder(@RequestParam("orderId")String orderId,@RequestParam("password") String password,HttpSession httpSession){
        String userId = (String)httpSession.getAttribute("id");

       boolean flag=orderService.payOrder(Long.parseLong(orderId),userId,password);
       if(flag){
           System.out.println("right");
           return "right";
       }
       else {
           return "false";
       }
    }

    @RequestMapping("/order/getUserOrder")
    @ResponseBody
    public List<OrderVo> getUserOrder(HttpSession session){
        String email =(String ) session.getAttribute("id");
        return orderService.findUserOrderVo(email);
    }


    @RequestMapping("/order/askInfo")
    @ResponseBody
    public OrderVo orderInfo(HttpSession session){
        String id =(String) session.getAttribute("performId");
        PerformVo performVo = performService.getPerformById(id);
        String time = performVo.getPerformTime();
        double discount = userService.calDiscount((String)session.getAttribute("id") );
        OrderVo vo = (OrderVo)session.getAttribute("order");
        vo.setPerformTime(time);
        vo.setPreferential(discount);
        vo.setHostPlace(performVo.getHostPlace());

        return vo;
    }

    @RequestMapping("/order/makeOrder")
    @ResponseBody
    public String makeOrder(@RequestBody OrderVo orderVo, HttpSession httpSession){
        orderVo.setUserEmail((String)httpSession.getAttribute("id"));
        orderVo.setPerformName((String)httpSession.getAttribute("performId"));

        httpSession.setAttribute("order",orderVo);
        orderService.makeOrder(orderVo);
        return "right";
    }
}
