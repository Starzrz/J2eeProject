package com.tickets.demo.service.impl;

import com.tickets.demo.Respository.HostRepository;
import com.tickets.demo.Respository.OrdersRepository;
import com.tickets.demo.Respository.PerformRepository;
import com.tickets.demo.model.Host;
import com.tickets.demo.model.Orders;
import com.tickets.demo.model.Perform;
import com.tickets.demo.model.vo.HostVo;
import com.tickets.demo.model.vo.PerformVo;
import com.tickets.demo.service.HostService;
import com.tickets.demo.util.DataTransUtil;
import com.tickets.demo.util.SiteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.parsers.DocumentBuilderFactory;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ${zrz} on 2018/3/24.
 */
@Component
public class HostServiceImpl implements HostService {

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    HostRepository hostRepository;

    @Autowired
    PerformRepository performRepository;

    @Override
    public boolean hostCheck(String hostId) {
        long id = Long.parseLong(hostId);
        Host host = hostRepository.findById(id);
        if(host!=null &&(host.getIsRatify()==1 ||host.getIsRatify()==3)){

            return true;
        }
        else return false;
    }

    @Override
    public void hostApply(HostVo vo) {
        Host host = new Host();
        host.setName(vo.getName());
        host.setPlace(vo.getPlace());
        String siteStr =vo.getXsites()+"_"+vo.getYsites();
        host.setSiteSize(siteStr);
        host.setIsRatify(0);
        host.setIntroduction(vo.getIntroduction());
        host.setId(Long.parseLong(vo.getHostId()));
        host.setVip1(vo.getVip1());
        host.setVip2(vo.getVip2());
        hostRepository.saveAndFlush(host);

    }



    @Override
        public HostVo getInfo(long id) {
        Host host = hostRepository.findById(id);
        int status = 1;
        String hId = id+"";
        hId="1"+hId;
        long lId = Long.parseLong(hId);
        if(hostRepository.findById(lId)!=null){
            status = 0;
        }
        if(host.getIsRatify()==3){
            status = 2;
        }

        int [] sites = DataTransUtil.siteSizeTrans(host.getSiteSize());
        HostVo vo = new HostVo(id+"",host.getName(),host.getPlace(),sites[0],sites[1],host.getIntroduction(),host.getVip1(),host.getVip2());
        int orderCount = 0;
        for(Perform perform:host.getPerforms()){
            orderCount+=perform.getOrders().size();
        }
        int perforCount = host.getPerforms().size();
        vo.setOrderCount(orderCount);
        vo.setPerformCount(perforCount);
        vo.setProfits(host.getProfits());
        vo.setIncomes(host.getIncomes());
        vo.setStatus(status);
        return vo;

    }



    @Override
    public void changeInfo(HostVo vo) {
        Host host = new Host();
        String hId = vo.getHostId();
        hId="1"+hId;
        long lId = Long.parseLong(hId);
        host.setId(lId);
        host.setName(vo.getName());
        host.setPlace(vo.getPlace());
        host.setVip1(vo.getVip1());
        host.setVip2(vo.getVip2());
        host.setIntroduction(vo.getIntroduction());
        String siteStr =vo.getXsites()+"_"+vo.getYsites();
        host.setSiteSize(siteStr);
        hostRepository.saveAndFlush(host);
    }

    @Override
    public void publishPerform(PerformVo performVo) {
        Perform perform = new Perform();
        perform.setName(performVo.getName());
        perform.setPerformTime(DataTransUtil.stringToDate(performVo.getPerformTime()));
        perform.setDescribes(performVo.getDescribes());
        perform.setPerformType(performVo.getPerformType());
        Host host = hostRepository.findById(performVo.getHostId());
        perform.setHost(host);
        perform.setPlace(performVo.getPlace());
        perform.setSitePrice(performVo.getSitePrice());
        perform.setEndTime(DataTransUtil.stringToDate(performVo.getEndTime()));
        perform.setPrice(performVo.getPrice());
        perform.setVipPrice(performVo.getVipPrice());
        performRepository.saveAndFlush(perform);
    }

    @Override
    public ArrayList<HostVo> getAllHost() {
        List<Host> hostList = hostRepository.findAllByIsRatify(1);
        hostList.addAll(hostRepository.findAllByIsRatify(3));
        ArrayList<HostVo> hostVos = new ArrayList<>();
        for(Host host:hostList){
            int status = 1;
            String hId = host.getId()+"";
            hId="1"+hId;
            long lId = Long.parseLong(hId);
            if(hostRepository.findById(lId)!=null){
                status = 0;
            }
            if(host.getIsRatify()==3){
                status = 2;
            }

            int [] sites = DataTransUtil.siteSizeTrans(host.getSiteSize());
            HostVo vo = new HostVo(host.getId()+"",host.getName(),host.getPlace(),sites[0],sites[1],host.getIntroduction(),host.getVip1(),host.getVip2());
            int orderCount = 0;
            for(Perform perform:host.getPerforms()){
                orderCount+=perform.getOrders().size();
            }
            int perforCount = host.getPerforms().size();
            vo.setOrderCount(orderCount);
            vo.setPerformCount(perforCount);
            vo.setStatus(status);
            vo.setProfits(host.getProfits());
            vo.setIncomes(host.getIncomes());
            hostVos.add(vo);
        }
        return hostVos;
    }

    @Override
    public void liveSell(long performId, int memberLv, ArrayList<int[]> sites,double totalPrice) {
        double rate = DataTransUtil.calDiscount(memberLv);
        Perform perform = performRepository.findById(performId);
        Host host = perform.getHost();
        String nowBook = DataTransUtil.siteArrayTrans(sites);
        String bookSites = perform.getBookSites();
        bookSites = bookSites+","+nowBook;
        perform.setBookSites(bookSites);
        Map<Date,Double> incomes = host.getIncomes();
        Map<Date,Double> profits = host.getProfits();
        double tIncome = host.getIncome();
        tIncome+=totalPrice;
        double tProfit = host.getProfit();
        tProfit+=totalPrice;
        host.setIncome(tIncome);
        host.setProfit(tProfit);
        java.util.Date date =new java.util.Date();
        Date sqlDate= new Date(date.getTime());
        if(incomes.containsValue(sqlDate)){
            double income = incomes.get(sqlDate);
            double profit = profits.get(sqlDate);
            income+=totalPrice;
            profit+=totalPrice;
            incomes.replace(sqlDate,income);
            profits.replace(sqlDate,profit);
        }
        else{
            incomes.put(sqlDate,totalPrice);
            profits.put(sqlDate,totalPrice);
        }
        host.setIncomes(incomes);
        host.setProfits(profits);
        hostRepository.saveAndFlush(host);
    }

    @Override
    public void checkIn(long orderId) {
        Orders orders = ordersRepository.findById(orderId);
        orders.setStatus(2);
        ordersRepository.saveAndFlush(orders);
    }
}
