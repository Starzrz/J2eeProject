package com.tickets.demo.service;

import com.tickets.demo.model.Perform;
import com.tickets.demo.model.vo.PerformVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${zrz} on 2018/3/31.
 */
@Service
public interface PerformService {

    public ArrayList<PerformVo> getAllPerform();


    public ArrayList<PerformVo> getAllPerformByHost(String hostId);

    public PerformVo getPerformById(String id);
}
