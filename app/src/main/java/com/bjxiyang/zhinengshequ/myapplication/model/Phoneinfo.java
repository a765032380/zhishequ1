package com.bjxiyang.zhinengshequ.myapplication.model;

/**
 * Created by Administrator on 2017/6/20 0020.
 */

public class Phoneinfo {
    private String name;
    private String number;
    public Phoneinfo(String name, String number) {
        this.name = name;
        this.number = number;
    }
    public String getName() {
        return name;
    }
    public String getNumber() {
        return number;
    }
}
