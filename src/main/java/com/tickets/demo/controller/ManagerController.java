package com.tickets.demo.controller;

import com.tickets.demo.Respository.HostRepository;
import com.tickets.demo.model.vo.HostVo;
import com.tickets.demo.model.vo.ManagerVo;
import com.tickets.demo.service.HostService;
import com.tickets.demo.service.ManagerService;
import com.tickets.demo.util.DataTransUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by ${zrz} on 2018/4/2.
 */
@Controller
public class ManagerController {
    @Autowired
    ManagerService managerService;




    String hostId ;

    @Autowired
    HostService hostService;
    @Autowired
    HostRepository hostRepository;




    @RequestMapping("/manager/getAllList")
    @ResponseBody
    public ArrayList<HostVo> allHost(){
        return hostService.getAllHost();
    }

    @RequestMapping("mHostList.html")
    public String allHostList(){
        return "/9view/mHostList";
    }

    @RequestMapping("mHostDetail.html")
    public String mHostDetail(@RequestParam("hostId")String hostId, HttpSession session){
        session.setAttribute("HostId",hostId);
        this.hostId=hostId;
        return "9view/mHostDetail";
    }

    @RequestMapping("/manager/askmHostInfo")
    @ResponseBody
    public HostVo mHostAsk(HttpSession session){
        if(hostId==null)
            hostId=(String) session.getAttribute("hostId");
       return hostService.getInfo(Long.parseLong(hostId));
    }

    @RequestMapping("/9view/askMHchart")
    @ResponseBody
    public String[][] askMChart(HttpSession session){
        if(hostId==null)
            hostId=(String) session.getAttribute("hostId");
        HostVo vo =  hostService.getInfo(Long.parseLong(hostId));
        return DataTransUtil.mapToArray(vo.getProfits());

    }
    @RequestMapping("/9view/askMHIncomeChart")
    @ResponseBody
    public String[][] askMIncomeChart(HttpSession session){
        if(hostId==null)
            hostId=(String) session.getAttribute("hostId");
        HostVo vo =  hostService.getInfo(Long.parseLong(hostId));
        return DataTransUtil.mapToArray(vo.getIncomes());

    }


    @RequestMapping("managerCheck.html")
    public String managerCheck(){
        managerService.settle();
        return "/9view/managerIndex";
    }

    @RequestMapping("managerChart.html")
    public String managerChart(){
        return "/9view/managerChart";
    }

    @RequestMapping("/manager/askHostInfo")
    @ResponseBody
    public ArrayList<HostVo> hostInfo(HttpSession session){

        System.out.println("hostid"+this.hostId);
        ArrayList<HostVo> hostVos = new ArrayList<>();
        String hostId = (String)session.getAttribute("hostId");

            hostId=this.hostId;

        hostVos.add(hostService.getInfo(Long.parseLong(hostId)));
        String hostId2 = "1"+hostId;
       if(hostRepository.findById(Long.parseLong(hostId2))!=null){
           hostVos.add(hostService.getInfo(Long.parseLong(hostId2)));
       }
       return hostVos;

    }

    @RequestMapping("/manager/agree")
    @ResponseBody
    public String agreeHost(HttpSession session){
        String id = (String)session.getAttribute("hostId");
        if(id==null){
            id=this.hostId;
        }
        managerService.passHost(Long.parseLong(id));
        return "right";
    }
    @RequestMapping("/manager/cancel")
    @ResponseBody
    public String cancelHost(HttpSession session){

        String id = (String)session.getAttribute("hostId");
        if(id==null){
            id=this.hostId;
        }
       managerService.refuseHost(Long.parseLong(id));
        return "right";
    }

    @RequestMapping("mHostInfoDetail.html")
    public String mHostInfo(HttpSession session, @RequestParam("hostId")String hostId){
       this.hostId = hostId;
        session.setAttribute("hostId",hostId);
        return "/9view/mHostInfoDetail";
    }


    @RequestMapping("/managerHostList.html")
    public String managerHostList(){
       return "/9view/managerHostList";
    }

    @RequestMapping("/manager/getHostList")
    @ResponseBody
    public ArrayList<HostVo> hostVos(){
        return managerService.getAllChange();
    }
}
