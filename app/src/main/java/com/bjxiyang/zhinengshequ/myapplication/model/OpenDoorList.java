package com.bjxiyang.zhinengshequ.myapplication.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17 0017.
 */

public class OpenDoorList {


    /**
     * code : 1000
     * msg : 获取开门列表成功
     * obj : [{"unitName":"一单元","resultCode":0,"communityName":"鹏阁小区","nperName":"二期","floorName":"三栋","openTime":"2017-06-17 10:47:57","userName":"租客人"},{"unitName":"一单元","resultCode":0,"communityName":"鹏阁小区","nperName":"二期","floorName":"三栋","openTime":"2017-06-17 10:47:23","userName":"租客人"}]
     */

    private String code;
    private String msg;
    private List<Obj> obj;

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

    public List<Obj> getObj() {
        return obj;
    }

    public void setObj(List<Obj> obj) {
        this.obj = obj;
    }

    public static class Obj {
        /**
         * unitName : 一单元
         * resultCode : 0
         * communityName : 鹏阁小区
         * nperName : 二期
         * floorName : 三栋
         * openTime : 2017-06-17 10:47:57
         * userName : 租客人
         */

        private String unitName;
        private int resultCode;
        private String communityName;
        private String nperName;
        private String floorName;
        private String openTime;
        private String userName;

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public String getCommunityName() {
            return communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
        }

        public String getNperName() {
            return nperName;
        }

        public void setNperName(String nperName) {
            this.nperName = nperName;
        }

        public String getFloorName() {
            return floorName;
        }

        public void setFloorName(String floorName) {
            this.floorName = floorName;
        }

        public String getOpenTime() {
            return openTime;
        }

        public void setOpenTime(String openTime) {
            this.openTime = openTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
