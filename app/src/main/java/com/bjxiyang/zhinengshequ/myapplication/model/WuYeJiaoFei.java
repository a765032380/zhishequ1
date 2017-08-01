package com.bjxiyang.zhinengshequ.myapplication.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class WuYeJiaoFei {

    private String code;
    private String msg;
    private List<WuYeJiaoFei.Obj> obj;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<WuYeJiaoFei.Obj> getObj() {
        return obj;
    }

    public void setObj(List<WuYeJiaoFei.Obj> obj) {
        this.obj = obj;
    }

    public static class Obj{
        private int  integral;
        private int propertyId;
        private String propertyName;
        private String fee;
        private String doorName;
        private String unitName;
        private String floorName;
        private String nperName;
        private String communityName;
        private String addTime;
        private String orderNo;
        private String paymentTime;
        private int paymentType;
        private String havePayment;


        public int getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public int getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(int propertyId) {
            this.propertyId = propertyId;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getDoorName() {
            return doorName;
        }

        public void setDoorName(String doorName) {
            this.doorName = doorName;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getFloorName() {
            return floorName;
        }

        public void setFloorName(String floorName) {
            this.floorName = floorName;
        }

        public String getNperName() {
            return nperName;
        }

        public void setNperName(String nperName) {
            this.nperName = nperName;
        }

        public String getCommunityName() {
            return communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getPaymentTime() {
            return paymentTime;
        }

        public void setPaymentTime(String paymentTime) {
            this.paymentTime = paymentTime;
        }



        public String getHavePayment() {
            return havePayment;
        }

        public void setHavePayment(String havePayment) {
            this.havePayment = havePayment;
        }
    }

















    private int type;
    private String date;
    private String time;
    private String address;
    private String jiaofeizhuangtai;
    private String money;
    private String wuyeName;
    private String orderNumber;
    private String captureExpendsTime;
    private String modeOfPayment;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJiaofeizhuangtai() {
        return jiaofeizhuangtai;
    }

    public void setJiaofeizhuangtai(String jiaofeizhuangtai) {
        this.jiaofeizhuangtai = jiaofeizhuangtai;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getWuyeName() {
        return wuyeName;
    }

    public void setWuyeName(String wuyeName) {
        this.wuyeName = wuyeName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCaptureExpendsTime() {
        return captureExpendsTime;
    }

    public void setCaptureExpendsTime(String captureExpendsTime) {
        this.captureExpendsTime = captureExpendsTime;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }
}
