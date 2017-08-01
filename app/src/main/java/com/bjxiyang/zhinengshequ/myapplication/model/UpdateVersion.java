package com.bjxiyang.zhinengshequ.myapplication.model;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class UpdateVersion {

    /**
     * code : 1000
     * msg : 获取成功
     * obj : {"addTime":"2017-07-05 14:14:49","verDescript":"<p>最新版<\/p>","verNo":1,"verSn":"1.0","verUrl":"http://47.92.106.249:8087/upload/Wisdom_community.apk","ver_force":0}
     */

    private String code;
    private String msg;
    private ObjBean obj;

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

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * addTime : 2017-07-05 14:14:49
         * verDescript : <p>最新版</p>
         * verNo : 1.0
         * verSn : 1.0
         * verUrl : http://47.92.106.249:8087/upload/Wisdom_community.apk
         * ver_force : 0
         */

        private String addTime;
        private String verDescript;
        private double verNo;
        private String verSn;
        private String verUrl;
        private int ver_force;

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getVerDescript() {
            return verDescript;
        }

        public void setVerDescript(String verDescript) {
            this.verDescript = verDescript;
        }

        public double getVerNo() {
            return verNo;
        }

        public void setVerNo(double verNo) {
            this.verNo = verNo;
        }

        public String getVerSn() {
            return verSn;
        }

        public void setVerSn(String verSn) {
            this.verSn = verSn;
        }

        public String getVerUrl() {
            return verUrl;
        }

        public void setVerUrl(String verUrl) {
            this.verUrl = verUrl;
        }

        public int getVer_force() {
            return ver_force;
        }

        public void setVer_force(int ver_force) {
            this.ver_force = ver_force;
        }
    }
}
