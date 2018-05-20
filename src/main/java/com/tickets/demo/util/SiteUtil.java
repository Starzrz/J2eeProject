package com.tickets.demo.util;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * 处理关于订座以及分配座位的工具类
 * Created by ${zrz} on 2018/3/21.
 */
public class SiteUtil {

    /**
     * 做出座位的map图
     * @param siteStr
     * @param vip1
     * @param vip2

     * @return
     */
    public static String[] siteMap(String siteStr,int vip1,int vip2){

        int [] siteSize = DataTransUtil.siteSizeTrans(siteStr);
        int x=siteSize[0];
        int y=siteSize[1];
        String oneSite = "a";
        String oneLine = "";
        String[] result =new String[x];

        for(int i=0;i<x;i++){
            if(i>=vip1-1 && i<vip2-1){
                oneSite="b";
            }else {
                oneSite="a";
            }
            for(int j=0;j<y;j++){
                oneLine+=oneSite;
            }
            System.out.println(oneLine);
            result[i]=oneLine;
            oneLine="";

        }

        return result;
    }


    /**
     * 根据座位号以及座位价格,优惠比例计算总价
     * @param siteStr
     * @param sitePrice
     * @return
     */
    public static double sitePriceCal(String siteStr, Map<Integer,Double> sitePrice,double rate){
        double totalPrice = 0;
        ArrayList<int []> siteArray = DataTransUtil.siteStrTrans(siteStr);
        for(int[] s:siteArray){
            double sPrice = sitePrice.get(s[0])*rate;
            totalPrice+=sPrice;
        }
        return totalPrice;
    }


    /**
     * 计算剩余的座位数
     * @param siteStatus
     * @return
     */
    public static int remainSite(int [][]siteStatus){
        int maxX = siteStatus.length;
        int maxY = siteStatus[0].length;
        //依次扫描空闲座位
        int count = 0;//连座的座位数
        for(int x=0;x<maxX;x++){
            for(int y=0;y<maxY;y++){
                if(siteStatus[x][y]==0){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 根据给出的长宽以及定好的座位，生成当前座位情况的数组
     * @param siteSize
     * @param bookSites
     * @return 二维数组，0表示空，1表示定好
     */
    public static int[][] fillSite(int []siteSize,ArrayList<int []> bookSites){
        int x = siteSize[0];
        int y = siteSize[1];
        int reSite[][] = new int[x][y];
        for(int []siteNum:bookSites){
            int siteX = siteNum[0];
            int siteY = siteNum[1];
            reSite[siteX][siteY] = 1;
        }
        return reSite;

    }

    /**
     * 订座
     * @param siteStatus 目前座位状况
     * @param bookSites 选择的座位
     * @return 订座之后的座位状况
     */
    public static int[][] bookSite(int [][]siteStatus,ArrayList<int []> bookSites){
        for(int []siteNum:bookSites){
            int siteX = siteNum[0];
            int siteY = siteNum[1];
            siteStatus[siteX][siteY] = 1;
        }
        return siteStatus;
    }

    /**
     * 退订，将座位情况中退订的座位删除
     * @param nowSites
     * @param bookSites
     * @return
     */
    public static String refoundSite(String nowSites,String bookSites){
       String[] nowSitesArray = nowSites.split(",");
       ArrayList<String> newArray = new ArrayList<String>(Arrays.asList(nowSitesArray));
       String[] bookSitesArray = bookSites.split(",");
       for(String s:bookSitesArray){
            newArray.remove(s);
       }
       String result = "";
       for(String s2:newArray){
           result=result+","+s2;
       }
       if(result!=null &&result.length()>1)
       result = result.substring(1);
        return result;
    }

    /**
     * 系统分配订票
     * @param siteStatus 目前座位状况
     * @param number 订票张数
     * @return 选定的座位
     */
    public static ArrayList<int []> sysBook(int [][]siteStatus,int number){
        ArrayList<int []> freeSites = new ArrayList<>();
        int maxX = siteStatus.length;
        int maxY = siteStatus[0].length;
        //依次扫描空闲座位
        int count = 0;//连座的座位数
        for(int x=0;x<maxX;x++){
            for(int y=0;y<maxY;y++){
                if(siteStatus[x][y]==0){
                    freeSites.add(new int[]{x,y});
                    count++;
                    if(count==number){  //找到连座
                        int siteY = y;
                        ArrayList<int []> resultSites = new ArrayList<>();
                        for(int i=0;i<count;i++){
                            resultSites.add(new int[]{x,siteY});
                            siteY--;
                        }
                        return resultSites;

                    }
                }
                else { //清除连座计数
                    count=0;

                }


            }
        }
        //没有找到连座，随机分配座位
        ArrayList<int []> resultSites = new ArrayList<>();
        int freeSize = freeSites.size();
        //取中间的数
        int mid = freeSize/2;
        mid = mid-(number/2);
        if(mid<0){
            mid=0;
        }
        for(int j=0;j<number;j++){
            int index = mid+j;
            if(index>=freeSize){
                index-=freeSize;
            }
            resultSites.add(freeSites.get(index));
        }
        return resultSites;

    }

}
