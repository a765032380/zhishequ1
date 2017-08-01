package com.bjxiyang.zhinengshequ.myapplication.model;

/**
 * Created by sander on 2017/4/10.
 */

public class District {
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    private String district;

    public District(){
        this.district = "朝阳小区";
    }
}
