package com.tickets.demo.controller;

import com.tickets.demo.model.Enum.PerformType;
import com.tickets.demo.model.Host;
import com.tickets.demo.model.Perform;
import com.tickets.demo.model.SiteSell;
import com.tickets.demo.model.vo.HostVo;
import com.tickets.demo.model.vo.PerformVo;
import com.tickets.demo.service.HostService;
import com.tickets.demo.service.PerformService;
import com.tickets.demo.util.DataTransUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ${zrz} on 2018/3/29.
 */
@Controller
public class HostController {
    @Autowired
    HostService hostService;

    @Autowired
    PerformService performService;

    String hostId;

    @RequestMapping("/hostPublish")
    private String hostPublish(){
        return "/9view/Publish";
    }
    @RequestMapping("/hostLogin.html")
    private String hostRegister(){
        return "/9view/hostLogin";
    }

    @RequestMapping("/host/register1")
    @ResponseBody
    private String register(@RequestBody HostVo hostVo){
        hostService.hostApply(hostVo);
        return hostVo.getHostId();


    }

    @RequestMapping("hostPerformList.html")
    public String performList(){
        return "/9view/hostPerformList";
    }

    @RequestMapping("/host/getPerformList")
    @ResponseBody
    public ArrayList<PerformVo> getAllPerform(HttpSession httpSession){
        return performService.getAllPerformByHost((String) httpSession.getAttribute("hostId"));
    }



    @RequestMapping("hostCheckIn.html")
    public String checkInd(){
        return "/9view/hostCheckIn";
    }

    @RequestMapping("hostPerformDetail.html")
    public String performDetail(@RequestParam("id")String id, HttpSession session){
        session.setAttribute("performId",id);
        return "/9view/hostPerformDetail";
    }

    @RequestMapping("/host/sellSites")
    @ResponseBody
    public String sellSites(@RequestBody SiteSell siteSell,HttpSession session){
        long performId = Long.parseLong((String) session.getAttribute("performId"));
        String [] sitesStr = siteSell.getSites();
        System.out.println(sitesStr);
        hostService.liveSell(performId,siteSell.getLv(),DataTransUtil.siteStrTrans(DataTransUtil.siteArrayToStr(sitesStr)),siteSell.getTotalPrice());
        return "right";
    }

    @RequestMapping("hostInfoDetail.html")
    public String mHostDetail(){

        return "/9view/hostInfoDetail";
    }

    @RequestMapping("/host/askInfo")
    @ResponseBody
    public HostVo getInfo(HttpSession httpSession){
        System.out.println(httpSession.getAttributeNames());
        return hostService.getInfo(Long.parseLong((String)httpSession.getAttribute("hostId")));
    }

    @RequestMapping("/host/changeInfo")
    public String changeInfo( @RequestBody HostVo hostVo,HttpSession httpSession){
        hostVo.setHostId((String)httpSession.getAttribute("hostId"));
        hostService.changeInfo(hostVo);
        return "/9view/hostIndex";
    }

    @RequestMapping("/hostInfo.html")
    public String info(){
        return "9view/hostInfo";
    }

    @RequestMapping("/hostLogin1")
    private String login(String vcode, HttpSession session){
       boolean result = hostService.hostCheck(vcode);
       if(result){
           System.out.println(vcode);
           session.setAttribute("hostId",vcode);
           return "/9view/hostIndex";
       }
       else {
           return "/9view/loginerror";
       }
    }

    @RequestMapping("/publish")
    private String getPublish(String select2,String textarea,String date,String name,String place,double price,double vipPrice,HttpSession session){

        long hostId = Long.parseLong((String) session.getAttribute("hostId"));
        System.out.println(date+textarea);
        String []strings = date.split("-");
        String startDate = strings[0].substring(0,strings[0].length()-1);
        String endDate =strings[1].substring(1);
        PerformVo vo = new PerformVo();
        System.out.println(select2);
        vo.setPerformType(PerformType.valueOf(select2));
        vo.setDescribes(textarea);
        vo.setName(name);
        vo.setPrice(price);
        vo.setVipPrice(vipPrice);
        vo.setPerformTime(startDate);
        vo.setEndTime(endDate);
        vo.setPlace(place);
        vo.setHostId(hostId);
        hostService.publishPerform(vo);

        System.out.println(DataTransUtil.stringToDate(strings[0].substring(0,strings[0].length()-1)).toString());
        return "/9view/Publish";
    }

}
