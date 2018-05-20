package com.tickets.demo.controller;

import com.tickets.demo.model.vo.ManagerVo;
import com.tickets.demo.model.vo.PerformVo;
import com.tickets.demo.model.vo.UserVo;
import com.tickets.demo.model.vo.userInfo;
import com.tickets.demo.service.ManagerService;
import com.tickets.demo.service.PerformService;
import com.tickets.demo.service.UserService;
import com.tickets.demo.util.DataTransUtil;
import org.hibernate.engine.transaction.jta.platform.internal.WebSphereLibertyJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Severity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ${zrz} on 2018/3/15.
 */
@RestController
public class InfoController {


    @Autowired
    private PerformService performService;
    @Autowired
    private UserService userService;

    @Autowired
    private ManagerService managerService;

    @RequestMapping("/9view/askinfo")
    public UserVo getUserInfo(HttpSession session){
        return userService.getUser(session.getAttribute("id").toString());
    }

    @RequestMapping("/9view/askMInfo")
    public ManagerVo managerView(){
        return managerService.display();
    }
    @RequestMapping("/9view/askMchart")
    public String[][] askMChart(){
        ManagerVo managerVo = managerService.display();
        return DataTransUtil.mapToArray(managerVo.getProfits());

    }
    @RequestMapping("/9view/askMIncomeChart")
    public String[][] askMIncomeChart(){
        ManagerVo managerVo = managerService.display();
        return DataTransUtil.mapToArray(managerVo.getIncomes());

    }

    @RequestMapping("/perform/getInfo")
    @ResponseBody
    public ArrayList<PerformVo> getPerformInfo(){
        return performService.getAllPerform();
    }

    @RequestMapping("/9view/changeInfo")
    public void changeInfo(HttpServletRequest servletRequest, @RequestBody userInfo userInfo ,HttpSession session){

       String name = userInfo.getName();
        String introduction = userInfo.getIntroduction();
        String newPass =userInfo.getNewPass();
        System.out.println(servletRequest.getParameterMap());
        System.out.println(session.getAttribute("id"));
        System.out.println("data"+name+introduction+newPass);
        String email = session.getAttribute("id").toString();
        userService.changeBasicInfo(email,name,introduction,newPass);
    }
}
