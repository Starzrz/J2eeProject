package com.tickets.demo.controller;

import com.tickets.demo.model.Perform;
import com.tickets.demo.model.vo.PerformVo;
import com.tickets.demo.service.PerformService;
import com.tickets.demo.service.UserService;
import com.tickets.demo.util.SiteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ${zrz} on 2018/3/30.
 */
@Controller
public class SeatController {

    @Autowired
    PerformService performService;
    @RequestMapping("/performDetail.html")
    private String seatInfo(@RequestParam("id")String id, HttpSession session){

        session.setAttribute("performId",id);
        return "9view/performDetail";
    }
    @RequestMapping("/seat/getSeat")
    @ResponseBody
    private PerformVo getSeat( HttpSession session){
        String id =(String) session.getAttribute("performId");
        PerformVo performVo = performService.getPerformById(id);
        String[] seatMap = performVo.getSiteMap();

       return performVo;
    }
}
