package com.tickets.demo.service.impl;

import com.tickets.demo.Respository.HostRepository;
import com.tickets.demo.Respository.PerformRepository;
import com.tickets.demo.model.Host;
import com.tickets.demo.model.Perform;
import com.tickets.demo.model.vo.PerformVo;
import com.tickets.demo.service.PerformService;
import com.tickets.demo.util.DataTransUtil;
import com.tickets.demo.util.SiteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by ${zrz} on 2018/3/31.
 */
@Component
public class PerformServiceImpl implements PerformService{

    @Autowired
    PerformRepository performRepository;


    @Autowired
    HostRepository hostRepository;

    @Override
    public ArrayList<PerformVo> getAllPerform() {
        ArrayList<PerformVo> performVos = new ArrayList<>();
        ArrayList<Perform> performs=(ArrayList)performRepository.findAll();
        for(Perform perform:performs){
            PerformVo performVo = new PerformVo();
            performVo.setName(perform.getName());
            performVo.setPlace(perform.getPlace());
            performVo.setEndTime(DataTransUtil.dateToString(perform.getEndTime()));
            performVo.setPerformTime(DataTransUtil.dateToString(perform.getPerformTime()));
            performVo.setBookSites(perform.getBookSites());
            performVo.setPrice(perform.getPrice());
            performVo.setVipPrice(perform.getVipPrice());
            performVo.setDescribes(perform.getDescribes());
            performVo.setId(perform.getId());
            performVo.setPerformType(perform.getPerformType());
            String[] siteMap = SiteUtil.siteMap(perform.getHost().getSiteSize(),perform.getHost().getVip1(),perform.getHost().getVip2());
            performVo.setSiteMap(siteMap);
            performVo.setHostPlace(perform.getHost().getPlace());
            performVos.add(performVo);

        }
        return performVos;
    }

    @Override
    public ArrayList<PerformVo> getAllPerformByHost(String hostId) {
        ArrayList<PerformVo> performVos = new ArrayList<>();
        Host host = hostRepository.findById(Long.parseLong(hostId));
        Set<Perform> performs = host.getPerforms();
        for(Perform perform:performs){
            PerformVo performVo = new PerformVo();
            performVo.setName(perform.getName());
            performVo.setPlace(perform.getPlace());
            performVo.setEndTime(DataTransUtil.dateToString(perform.getEndTime()));
            performVo.setPerformTime(DataTransUtil.dateToString(perform.getPerformTime()));
            performVo.setBookSites(perform.getBookSites());
            performVo.setPrice(perform.getPrice());
            performVo.setVipPrice(perform.getVipPrice());
            performVo.setDescribes(perform.getDescribes());
            performVo.setId(perform.getId());
            performVo.setPerformType(perform.getPerformType());
            String[] siteMap = SiteUtil.siteMap(perform.getHost().getSiteSize(),perform.getHost().getVip1(),perform.getHost().getVip2());
            performVo.setSiteMap(siteMap);
            performVo.setHostPlace(perform.getHost().getPlace());
            performVos.add(performVo);
        }
        return performVos;
    }

    @Override
    public PerformVo getPerformById(String id) {
        long lId = Long.parseLong(id);
        Perform perform= performRepository.findById(lId);
        PerformVo performVo = new PerformVo();
        performVo.setName(perform.getName());
        performVo.setPlace(perform.getPlace());
        performVo.setEndTime(DataTransUtil.dateToString(perform.getEndTime()));
        performVo.setPerformTime(DataTransUtil.dateToString(perform.getPerformTime()));
        performVo.setBookSites(perform.getBookSites());
        performVo.setPrice(perform.getPrice());
        performVo.setVipPrice(perform.getVipPrice());
        performVo.setDescribes(perform.getDescribes());
        performVo.setId(perform.getId());
        performVo.setPerformType(perform.getPerformType());
        String[] siteMap = SiteUtil.siteMap(perform.getHost().getSiteSize(),perform.getHost().getVip1(),perform.getHost().getVip2());
        performVo.setSiteMap(siteMap);
        performVo.setHostPlace(perform.getHost().getPlace());
        return performVo;

    }
}
