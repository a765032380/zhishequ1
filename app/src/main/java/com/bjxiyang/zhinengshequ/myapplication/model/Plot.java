package com.bjxiyang.zhinengshequ.myapplication.model;

/**
 * Created by gll on 17-5-22.
 */

public class Plot {
    private int id;
    //城市名
    private String city_name;
    //小区名
    private String plot;
    //小区楼号
    private int buildingNo;
    //小区单元号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public int getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(int buildingNo) {
        this.buildingNo = buildingNo;
    }

    public int getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(int unitNumber) {
        this.unitNumber = unitNumber;
    }

    private int unitNumber;
}
