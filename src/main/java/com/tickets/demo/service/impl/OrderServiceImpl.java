package com.tickets.demo.service.impl;

import com.tickets.demo.Respository.HostRepository;
import com.tickets.demo.Respository.OrdersRepository;
import com.tickets.demo.Respository.PerformRepository;
import com.tickets.demo.Respository.UserRepository;
import com.tickets.demo.model.Host;
import com.tickets.demo.model.Orders;
import com.tickets.demo.model.Perform;
import com.tickets.demo.model.User;
import com.tickets.demo.model.vo.OrderVo;
import com.tickets.demo.service.OrderService;
import com.tickets.demo.service.PayService;
import com.tickets.demo.service.UserService;
import com.tickets.demo.util.DataTransUtil;
import com.tickets.demo.util.SiteUtil;
import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by ${zrz} on 2018/3/20.
 */
@Component
public class OrderServiceImpl implements OrderService {

    //超时时间
    private final long outTime = 60*1000;
    //检查时间
    private final long checkTime=2*1000;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrdersRepository orderRespository;
    @Autowired
    private PerformRepository performRespository;

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    private double[] refoundRate={1,0.8,0.5,0}; //退订返还比例

    private long[] refoundDate ={86400000,43200000,21600000};//退订返还时间差，以毫秒为单位
    @Autowired
    private PayService payService;

    @Override
    public void sysBook(long performId) {
        Perform perform = performRespository.findById(performId);
        int [][] siteStatus = SiteUtil.fillSite(DataTransUtil.siteSizeTrans(perform.getHost().getSiteSize()),DataTransUtil.siteStrTrans(perform.getBookSites()));
        int remain = SiteUtil.remainSite(siteStatus); //计算剩余座位数
        Set<Orders> orders = perform.getOrders();
        for(Orders o:orders){
            if(o.getSites()!=null || o.getSites().length()>=1){   //已经选座不需要配票
                continue;
            }else {
                if(remain<o.getCount()){ //座位不足，退票
                    orderService.cancelOrder(o.getId());
                    continue;
                }
                //系统分配座位
                String sites = o.getSites();
                ArrayList<int []> bookSites = SiteUtil.sysBook(siteStatus,o.getCount());
                siteStatus = SiteUtil.bookSite(siteStatus,bookSites);
                sites = DataTransUtil.siteArrayTrans(bookSites);
                o.setSites(sites);

                //计算总价
                double discount = DataTransUtil.calDiscount(o.getUser().getLv());
                double totalPrice = SiteUtil.sitePriceCal(sites,perform.getSitePrice(),discount);
                o.setTotalPrice(totalPrice);
                String performSites = perform.getBookSites();
                performSites = performSites+","+sites;
                perform.setBookSites(performSites);
                remain-=o.getCount();

                orderRespository.saveAndFlush(o);
            }
        }
        performRespository.saveAndFlush(perform);

    }

    @Override
    @Scheduled(fixedRate = checkTime)
    public void checkOrder() {
        System.out.println("check"+new Date());
        List<Orders> ordersList = orderRespository.findAllByStatus(0);
        for(Orders o:ordersList){
            Date date = o.getOrderTime();
            Date nowDate = new Date();
            long diffTime = nowDate.getTime()-date.getTime();
            if(diffTime>outTime){  //订单超时
                cancelOrder(o.getId());
            }
        }
    }

    @Override
    public boolean finishOrder(long id) {
        Orders orders = orderRespository.findById(id);
        if(orders==null){
            return false;
        }
        if(orders.getStatus()!=1){
            return false;
        }
        orders.setStatus(2);
        orderRespository.saveAndFlush(orders);
        return true;
    }

    public Orders getOrder(long id){
        return orderRespository.findById(id);
    }

    @Override
    public List<Orders> findUserOrder(String email) {
        return orderRespository.findAllByUserEmail(email);
    }



//    private String id;
//    private String orderTime;
//    private String ticketName;
//
//    private String userEmail;
//
//    //选座的座位号
//
//    private String[] sites;
//    //数量
//    private int count;
//    //单价
//    private double unitPrice;
//    //优惠
//    private double preferential=1;
//    //总价
//    private double totalPrice;
//    //是否付款,0没付款,1付款，2完成
//    private String status;
//    //表演的名字
//    private String performName;
//    //表演的地点
//    private String place;
//    //表演的时间
//    private String performTime;
//
//    private String endTime;
    @Override
    public List<OrderVo> findUserOrderVo(String email) {
        List<Orders> ordersList = orderRespository.findAllByUserEmail(email);
        List<OrderVo> orderVos = new ArrayList<>();
        for(Orders o:ordersList){

            orderVos.add(makeVo(o));
        }
        return orderVos;
    }

    private OrderVo makeVo(Orders o){
        User user = o.getUser();
        String email =user.getEmail();
        Perform perform = o.getPerform();
        Host host = perform.getHost();
        OrderVo vo = new OrderVo();
        vo.setId(o.getId()+"");
        vo.setOrderTime(DataTransUtil.dateToString(o.getOrderTime()));
        vo.setTicketName(perform.getName());
        vo.setUserEmail(email);
        vo.setSites(DataTransUtil.siteStrToArray(o.getSites()));
        vo.setCount(o.getCount());
        vo.setPreferential(userService.calDiscount(email));
        vo.setTotalPrice(o.getTotalPrice());
        vo.setStatus(DataTransUtil.statusTrans(o.getStatus()));
        vo.setPlace(perform.getPlace());
        vo.setHostPlace(host.getPlace());
        vo.setPerformTime(DataTransUtil.dateToString(perform.getPerformTime()));
        vo.setEndTime(DataTransUtil.dateToString(perform.getEndTime()));
        return vo;
    }

    @Override
    public List<OrderVo> findUserUnpayOrder(String email) {
        List<Orders> ordersList = orderRespository.findAllByUserEmailAndStatus(email,0);
        List<OrderVo> orderVos = new ArrayList<>();
        for(Orders o:ordersList){

            orderVos.add(makeVo(o));
        }
        return orderVos;
    }


    @Override
    public void makeOrder(OrderVo po) {
        Perform perform = performRespository.findById(Long.parseLong(po.getPerformName()));
        String userEmail = po.getUserEmail();
        Date orderTime = new Date();

        int count = po.getCount();
        double unitPrice = po.getUnitPrice();
        double preferential = po.getPreferential();

        int status =0;
        Orders orders = new Orders();
        orders.setPerform(perform);
        orders.setUser(userRepository.findByEmail(userEmail));
        orders.setOrderTime(orderTime);
        orders.setCount(count);
        orders.setUnitPrice(unitPrice);
        orders.setPreferential(preferential);

        orders.setStatus(status);


        String siteStr = perform.getBookSites();
        if(siteStr==null){
            siteStr="";
        }
        String sites="";
//        //如果没有选择座位，系统自动分配
//        if(vo.getSites()==null || vo.getSites().size()==0){
//            int [][] siteStatus = SiteUtil.fillSite(DataTransUtil.siteSizeTrans(perform.getHost().getSiteSize()),DataTransUtil.siteStrTrans(perform.getBookSites()));
//            ArrayList<int []> bookSites = SiteUtil.sysBook(siteStatus,count);
//            sites = DataTransUtil.siteArrayTrans(bookSites);
//        }
//        else {
//
//        }

        sites = DataTransUtil.siteArrayToStr(po.getSites());
        if(po.getSites()!=null && po.getSites().length!=0){ //如果已经选定座位，则计算票价
            double totalPrice = po.getTotalPrice()*preferential;
            orders.setTotalPrice(totalPrice);
        }
        if(siteStr.length()<2){
            siteStr=sites;
        }
        else {
            siteStr = siteStr + "," + sites;
        }
        orders.setSites(sites);

        perform.setBookSites(siteStr);
        performRespository.saveAndFlush(perform);
        orderRespository.saveAndFlush(orders);


    }

    @Override
    public boolean payOrder(long id, String payId, String pwd) {
        Orders orders = orderRespository.findById(id);

        double payMoney = orders.getTotalPrice();
        boolean payStatus = payService.pay(payId,pwd,payMoney);
        if(payStatus==false){
            return false;
        }
        else {
            orders.setStatus(1);
            orders.setPayId(payId);
            //结算场馆的收入
            Date orderTime = orders.getOrderTime();
            Perform perform = orders.getPerform();
            Host host = perform.getHost();
            java.sql.Date sqlDate = new java.sql.Date(orderTime.getTime());
            Map<java.sql.Date,Double> incomes = host.getIncomes();
            boolean flag = true;
            for(java.sql.Date d:incomes.keySet()){
                if(d.getYear()==sqlDate.getYear() && d.getMonth()==sqlDate.getMonth() && d.getDate()==sqlDate.getDate()){
                    double income = incomes.get(d);
                    income+=orders.getTotalPrice();
                    incomes.remove(d);
                    incomes.put(d,income);
                    flag=false;
                    break;
                }

            }
            if(flag){
                incomes.put(sqlDate,orders.getTotalPrice());
            }
            double totalIncome = host.getIncome();
            totalIncome+=orders.getTotalPrice();
            host.setIncome(totalIncome);
            host.setIncomes(incomes);

            //个人总消费增加
            userService.costChange(orders.getUser().getEmail(),payMoney);
            hostRepository.saveAndFlush(host);
            orderRespository.saveAndFlush(orders);
            return true;
        }

    }

    @Override
    public void cancelOrder(long id) {
        Orders orders = orderRespository.findById(id);
        Perform perform = orders.getPerform();
        Host host = perform.getHost();
        int orderStatus = orders.getStatus();
        if(orderStatus==0){

        }
        else {
            Date tickeDate = orders.getPerform().getPerformTime();
            Date nowDate = new Date();
            long dateDif = tickeDate.getTime()-nowDate.getTime();


            //根据退订时间，确定返还比例
            int i=0;
            do{
                long standard = refoundDate[i];
                if(dateDif>standard){
                    break;
                }
                i++;
            }while (i<3);
            double reRate = refoundRate[i];

            //退款处理
            double reMoney = reRate* orders.getTotalPrice();
            payService.refund(orders.getPayId(),reMoney);


        }
        //座位状态改变
        String performSite = perform.getBookSites();
        String orderSite = orders.getSites();
        performSite = SiteUtil.refoundSite(performSite,orderSite);
        perform.setBookSites(performSite);
        //订单状态改变
        orders.setStatus(3);
        performRespository.save(perform);
        orderRespository.saveAndFlush(orders);
    }


}
