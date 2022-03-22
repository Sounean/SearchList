package com.example.testdemo.Entity;

public class RESIDENTtest {
    String phone;   // 手机号
    String email;   // 邮箱
    String whitchs[];  // 哪个快递柜（一个人可能有很多个快递）

    public RESIDENTtest(String phone, String email, String[] whitchs) {
        this.phone = phone;
        this.email = email;
        this.whitchs = whitchs;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getWhitchs() {
        return whitchs;
    }

    public void setWhitchs(String[] whitchs) {
        this.whitchs = whitchs;
    }
}
