package com.tickets.demo.service;

import com.tickets.demo.model.Host;
import com.tickets.demo.model.vo.HostVo;
import com.tickets.demo.model.vo.ManagerVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by ${zrz} on 2018/3/23.
 */
@Service
public interface ManagerService {


    /**
     * 得到所有修改信息的列表
     * @return
     */
    public ArrayList<HostVo> getAllChange();

    /**
     * 得到所有的申请信息
     * @return 申请信息列表
     */
    public ArrayList<Host> getAllApply();


    /**
     * 批准场馆信息
     * @param hostId
     */
    public void passHost(long hostId);


    /**
     * 拒绝场馆信息的申请
     * @param hostId
     */
    public void refuseHost(long hostId);


    /**
     * 结算
     */
    public void settle();


    /**
     * 展示网站统计信息
     * @return
     */
    public ManagerVo display();


}
