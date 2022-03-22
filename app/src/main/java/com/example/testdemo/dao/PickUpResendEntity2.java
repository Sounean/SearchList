package com.example.testdemo.dao;

import java.util.List;

/*
* 服务器返给客户端的实体类
* */
public class PickUpResendEntity2 {
    String PhoneNumber = null;
    String Emain = null;
    List<String> BoxInWhere = null;

    public PickUpResendEntity2(String phoneNumber, String emain, List<String> boxInWhere) {  // 作为还没有数据传进来时测试用，如果服务器开始有数据传进来这个可以删掉
        PhoneNumber = phoneNumber;
        Emain = emain;
        BoxInWhere = boxInWhere;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmain() {
        return Emain;
    }

    public void setEmain(String emain) {
        Emain = emain;
    }

    public List<String> getBoxInWhere() {
        return BoxInWhere;
    }

    public void setBoxInWhere(List<String> boxInWhere) {
        BoxInWhere = boxInWhere;
    }
}
