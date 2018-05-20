package com.tickets.demo.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ${zrz} on 2018/3/16.
 * 用于各种数据类型转换的类
 */
public class DataTransUtil {




   public  static String statusTrans(int s){
      String[] statuStr={"未付款","已付款","已完成","已取消"};
      return statuStr[s];
   }
    public static String[] siteStrToArray(String s){
        return s.split(",");
    }


    public static String siteArrayToStr(String[] strings){
        String result = "";
        for(String s:strings){
            result=result+","+s;
        }
        return result.substring(1);
    }


    public static String dateToString(java.util.Date date){

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.ENGLISH);
           return (sdf.format(date));

    }
    public static java.util.Date stringToDate(String dateStr){
        java.util.Date date =new java.util.Date();
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa",Locale.ENGLISH);
            System.out.println(sdf.format(date));
            date= sdf.parse(dateStr);
        }
        catch (ParseException e)
        {
            System.out.println(e.getMessage());
        }
        return date;
    }

    public static String[][] mapToArray(Map<Date,Double> map){
        map = mapSort(map);
        String [][] result = new String[map.size()][2];
        int i=0;
        for(Date s:map.keySet()){
            System.out.println(s);
            String val = map.get(s).toString();
            result[i][0] = s.toString();
            result[i][1]=val;
            i++;
        }
        return result;
    }


    public static Map<Date,Double> mapSort(Map<Date,Double> dateDoubleMap){
        Map<Date, Double> sortedMap = new TreeMap<Date, Double>(java.util.Date::compareTo);

        sortedMap.putAll(dateDoubleMap);
        System.out.println(sortedMap.toString());
        return sortedMap;
    }
    /**
     * 将map的键值转换为string的方法
     * @param dateMap
     * @return
     */
    public static Map<String,Double> mapDateTrans(Map<Date,Double> dateMap){
        dateMap = mapSort(dateMap);
        Map<String,Double> resultMap = new HashMap<>();
        for(Date d:dateMap.keySet()){
            double o = dateMap.get(d);
            resultMap.put(d.toString(),o);
        }
        return resultMap;
    }


    /**
     * 根据等级计算折扣
     * @param lv
     * @return
     */
    public static double calDiscount(int lv) {
        double basicRate = 0.1;
        double basic = 1;
        return basic-(basicRate*lv);
    }

    /**
     * 将字符串类型的座位情况转换成长宽的int数组
     * @param siteSize
     * @return
     */
    public static int[] siteSizeTrans(String siteSize){
        String[] siteSizes =siteSize.split("_");
        int [] result = new int[2];
        result[0] = Integer.valueOf(siteSizes[0]);
        result[1] = Integer.valueOf(siteSizes[1]);
        return result;
    }

    /**
     * 将字符串样式的座位号转化为数组样式
     * @param siteStr 以，分割每张票，以-分割行列，先行后列
     * @return 二维数组，第二维是长度为2的数组，下标0对应行，下标1对应列
     */
    public static ArrayList<int []> siteStrTrans(String siteStr){
        ArrayList<int []> result = new ArrayList<>();
        if(siteStr==null){
            return result;
        }

        String[] sites = siteStr.split(",");
        for(String s:sites){
            String []site = s.split("_");
            int []siteNum = new int[2];
            siteNum[0] = Integer.valueOf(site[0]);
            siteNum[1] = Integer.valueOf(site[1]);
            result.add(siteNum);
        }
        return result;
    }

    /**
     * 将数组样式的座位号转化为字符串样式
     * @param siteArray 二维数组，第二维是长度为2的数组，下标0对应行，下标1对应列
     * @return  以，分割每张票，以-分割行列，先行后列
     */
    public static String siteArrayTrans(ArrayList<int []> siteArray){
        String resultStr="";
        for(int[] site:siteArray){
            String siteStr =","+ site[0]+"_"+site[1];
            resultStr+=siteStr;
        }
        resultStr=resultStr.substring(1,resultStr.length());
        return resultStr;
    }


}
