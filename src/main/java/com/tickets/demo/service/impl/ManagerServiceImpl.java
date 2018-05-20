package com.tickets.demo.service.impl;

import com.tickets.demo.Respository.*;
import com.tickets.demo.model.Host;
import com.tickets.demo.model.Manager;
import com.tickets.demo.model.vo.HostVo;
import com.tickets.demo.model.vo.ManagerVo;
import com.tickets.demo.service.ManagerService;
import com.tickets.demo.util.DataTransUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ${zrz} on 2018/3/23.
 */
@Component
public class ManagerServiceImpl implements ManagerService {


    private final double rate = 0.5; //分成比例

    private final String managerId = "admin";

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    PerformRepository performRepository;

    @Autowired
    HostRepository hostRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public ArrayList<HostVo> getAllChange() {
        ArrayList<Host> hosts = (ArrayList)hostRepository.findAllByIsRatify(0);
        ArrayList<HostVo> hostVos = new ArrayList<>();
        for(Host host:hosts){
            String id = host.getId()+"";
            int status = 2;
            if((id).length()==8){
                status=1;
               id = id.substring(1);
            }

            int [] sites = DataTransUtil.siteSizeTrans(host.getSiteSize());
            HostVo vo = new HostVo(id,host.getName(),host.getPlace(),sites[0],sites[1],host.getIntroduction(),host.getVip1(),host.getVip2());
            vo.setStatus(status);
            hostVos.add(vo);
        }
        return hostVos;
    }

    @Override
    public ArrayList<Host> getAllApply() {
        ArrayList<Host> hosts = (ArrayList)hostRepository.findAllByIsRatify(0);
        return hosts;
    }

    @Override
    public void passHost(long hostId) {

        Host host = hostRepository.findById(hostId);
        if(host.getIsRatify()==0) {
            host.setIsRatify(1);
            hostRepository.saveAndFlush(host);
        }
        else {
            String hostStr = hostId+"";
            hostStr = "1"+hostStr;

            long hostId2=Long.parseLong(hostStr);
            Host host2 = hostRepository.findById(hostId2);
            //如果是修改信息的申请，先删除存储的修改信息，然后将原有信息改为修改的信息
            hostRepository.deleteById(hostId2);
            host2.setIsRatify(1);
            host2.setId(hostId);
            host2.setIncome(host.getIncome());
            host2.setProfits(host.getProfits());
            host2.setProfit(host.getProfit());
            host2.setIncomes(host.getIncomes());
            hostRepository.saveAndFlush(host2);

        }
    }

    @Override
    public void refuseHost(long hostId) {
        Host host = hostRepository.findById(hostId);
        if(host.getIsRatify()==0) {
            host.setIsRatify(3);
            hostRepository.saveAndFlush(host);
        }
        else {
            String hostStr = hostId+"";
            hostStr = "1"+hostStr;

            long hostId2=Long.parseLong(hostStr);
            hostRepository.deleteById(hostId2);
            host.setIsRatify(3);
            hostRepository.saveAndFlush(host);

        }
 }

    @Override
    public void settle() {
        Manager manager = managerRepository.findByUserId(managerId);
        Map<Date,Double> mIncomes = manager.getIncomes();
        Map<Date,Double> mProfits = manager.getProfits();
        double allIncome = manager.getIncome();
        double allProfit = manager.getAllProfit();
        List<Host> hosts = hostRepository.findAll();
        for(Host h:hosts){  //对每个场馆
            Map<Date,Double> incomes = h.getIncomes();
            Map<Date,Double> profits = h.getProfits();
            double hProfit = h.getProfit();
            for(Date d:incomes.keySet()){    //每天的收益
                boolean flag1=false;
                for(Date md:mProfits.keySet()){
                    if(md.getYear()==d.getYear() && md.getMonth()==d.getMonth() && md.getDate()==d.getDate()){
                        flag1=true;
                        break;
                    }
                }
                if(flag1){
                    continue;
                }
                double income = incomes.get(d);
                allIncome+=income;
                double profit = income*rate;
                double diff = income-profit;
                allProfit+=diff;
                hProfit+=profit;
                profits.put(d,profit);
                boolean flag2=true;
                for(Date mid:mIncomes.keySet()){
                    if(mid.getYear()==d.getYear() && mid.getMonth()==d.getMonth() && mid.getDate()==d.getDate()){
                        flag2=false;
                        double mIncome = mIncomes.get(mid);
                        mIncome+=income;
                        mIncomes.replace(mid,mIncome);
                        double mProfit = mProfits.get(mid);
                        mProfit+=diff;
                        mProfits.replace(mid,mProfit);
                        flag2=false;
                        break;
                    }
                }
//                if(mIncomes.containsKey(d)){
//                    double mIncome = mIncomes.get(d);
//                    mIncome+=income;
//                    mIncomes.replace(d,mIncome);
//                    double mProfit = mProfits.get(d);
//                    mProfit+=diff;
//                    mProfits.replace(d,mProfit);
//                }
               if(flag2){
                    mIncomes.put(d,income);
                    mProfits.put(d,diff);
                }

            }
            h.setProfits(profits);
            h.setProfit(hProfit);
            hostRepository.saveAndFlush(h);
        }
        manager.setAllProfit(allProfit);
        manager.setIncome(allIncome);
        manager.setProfits(mProfits);
        manager.setIncomes(mIncomes);
        managerRepository.saveAndFlush(manager);
    }

    @Override
    public ManagerVo display() {
        Manager manager = managerRepository.findByUserId(managerId);
        Map<Date,Double> mIncomes =manager.getIncomes();
        Map<Date,Double> mProfits = manager.getProfits();
        String mId = managerId;
        ArrayList<Host> hosts = (ArrayList)hostRepository.findAll();
        int mCount = userRepository.findAll().size();
        ManagerVo managerVo = new ManagerVo(managerId,mIncomes,mProfits,mCount);
        managerVo.setHostCount(hosts.size());
        managerVo.setPerformCount(performRepository.findAll().size());
        managerVo.setOrderCount(ordersRepository.findAll().size());
        return managerVo;
    }
}
