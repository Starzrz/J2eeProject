package com.tickets.demo.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.tickets.demo.Respository.ManagerRepository;
import com.tickets.demo.Respository.UserRepository;
import com.tickets.demo.model.Manager;
import com.tickets.demo.model.User;
import com.tickets.demo.service.MailService;
import com.tickets.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by ${zrz} on 2018/3/5.
 */
@Controller
public class UserController {

    @Autowired
    ManagerRepository managerRepository;

    @RequestMapping("/performList.html")
    public String performList(){
        return "/9view/performList";
    }


    @RequestMapping("/main")
    private String domain(){
        return "/mainview/tours";
    }

    @RequestMapping("/about")
    private String ab(){
        return "/mainview/about";
    }

    @RequestMapping("/profile.html")
    private String profile(){
        return "/9view/profile";
    }

    @RequestMapping("/charts.html")
    private String charts(Model model){
        return "/9view/charts";
    }


    @RequestMapping("/info")
    private String info(){
        return "/mainview/contact";
    }
    @RequestMapping("/register")
    private String register(){
        return "/register";
    }

    @RequestMapping("/")
    private String getLog(){
        return "/9view/login";
    }
    @Autowired
    private UserService userService;
    @Autowired
   private UserRepository userRepository;
    @Autowired
    private MailService mailService;
    @RequestMapping("/login1")
    public String sayHello(String email, String password, Model model, HttpSession session){
        System.out.println(email);
        if(userRepository.findByEmail(email)==null){
            return "/9view/loginerror";
        }
        User realUser = userRepository.findByEmail(email);
        if(realUser.getCanUse()==0){
            return "/9view/loginerror";
        }
        String realPass= realUser.getPwd();
        System.out.println(email);
        System.out.println(realPass);
        if(realPass.equals(password)){
            session.setAttribute("id",email);
            return "/9view/index";
        }
       else {
            return "/9view/loginerror";
        }
    }
    @RequestMapping("/managerLogin.html")
    public String mLogin(){
        return "/9view/managerLogin";
    }

    @RequestMapping("/managerLogin1")
    public String managerLogin(String userId,String password){
        Manager manager;
        if((manager= managerRepository.findByUserId(userId))==null){
            return "/9view/loginerror";
        }
        String pwd= manager.getPwd();
        if(!pwd.equals(password)){
            return "/9view/loginerror";
        }
        return "/9view/managerIndex";
    }

    @RequestMapping("/user/cancel")
    public String cancelUser(HttpSession session){
        String userEmail =(String) session.getAttribute("id");
        userService.cancelUser(userEmail);
        return "right";
    }

    @RequestMapping("/register1")
    public String register( String email,String password){
        System.out.println(email);
       userService.register(email,password);
//
//        System.out.println(userId+passWd);
//        if(userRepository.findByEmail(userId)!=null){
//            return "/error";
//        }
//        User user = new User(userId, passWd,0,0,0);
//        userRepository.saveAndFlush(user);
     return "/9view/login";
    }

    @RequestMapping("/confirm")
    public String confirm(HttpServletRequest request){
        String vcode = request.getParameter("vcode");
        String email = request.getParameter("email");
        boolean isConfirm=userService.checkReg(email,vcode);
        if(isConfirm){
            return "/9view/confirmMessage";
        }
        else {
            return "/9view/404";
        }
    }


}
