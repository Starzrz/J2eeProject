package com.tickets.demo.service;

import com.tickets.demo.model.Host;
import com.tickets.demo.model.Perform;
import com.tickets.demo.model.vo.HostVo;
import com.tickets.demo.model.vo.PerformVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by ${zrz} on 2018/3/24.
 */
@Service
public interface HostService {

    public boolean hostCheck(String hostId);

    public void hostApply(HostVo vo);

    public HostVo getInfo(long id);

    public void changeInfo(HostVo vo);

    public void publishPerform(PerformVo performVo);


    public ArrayList<HostVo> getAllHost();

    /**
     * 现场购票的情况
     * @param performId
     * @param memberLv 会员等级
     * @param sites
     */
    public void liveSell(long performId,int memberLv, ArrayList<int[]> sites,double totalPrice);

    /**
     * 检票登记
     * @param orderId
     */
    public void checkIn(long orderId);


}
