package com.example.testdemo;



import com.example.testdemo.dao.PickUpResendEntity2;

import java.util.ArrayList;
import java.util.List;

/*
*  resend部分的辅助类
* init()返回一个虚拟数据放进去供展示
* */
public class PickUpResendUtil {
    // 数据测试
    public static List<String> groupEntity = new ArrayList<String>();
    public static List<List<String>> entityBoxs =new ArrayList<List<String>>();    // 把所有人的相关的快递都获取出来
    static List<PickUpResendEntity2> resendEntities = new ArrayList<PickUpResendEntity2>();

    public static List<PickUpResendEntity2> init(){
        resendEntities = new ArrayList<>();
        //String[][] entityBoxs =null;
        List<String> boxs = new ArrayList<String>();
        boxs.add("快递柜:26");
        resendEntities.add(new PickUpResendEntity2("13911111111", "12911111111@qq.com", boxs));
        boxs = new ArrayList<String>();
        boxs.add("快递柜:27");boxs.add("快递柜28");
        resendEntities.add(new PickUpResendEntity2("13922222222" , "12922222222@qq.com" , boxs));
        boxs = new ArrayList<String>();
        boxs.add("快递柜:29");boxs.add("快递柜30");boxs.add("快递柜31");
        resendEntities.add(new PickUpResendEntity2("13933333333" , "1293333333@qq.com" , boxs));

         return resendEntities;
    }
}
