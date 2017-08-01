package com.bjxiyang.zhinengshequ.myapplication.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28 0028.
 */

public class ErShouFang {
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

    public static class Obj{
        private String sId;
        private String sName;
        private String firstNper;
        private String firstInterestRate;
        private String secendNper;
        private String secendInterestRate;
        private String firstLife;
        private String secendLife;
        private String sMemo;
        private String url;

        public String getsId() {
            return sId;
        }

        public void setsId(String sId) {
            this.sId = sId;
        }

        public String getsName() {
            return sName;
        }

        public void setsName(String sName) {
            this.sName = sName;
        }

        public String getFirstNper() {
            return firstNper;
        }

        public void setFirstNper(String firstNper) {
            this.firstNper = firstNper;
        }

        public String getFirstInterestRate() {
            return firstInterestRate;
        }

        public void setFirstInterestRate(String firstInterestRate) {
            this.firstInterestRate = firstInterestRate;
        }

        public String getSecendNper() {
            return secendNper;
        }

        public void setSecendNper(String secendNper) {
            this.secendNper = secendNper;
        }

        public String getSecendInterestRate() {
            return secendInterestRate;
        }

        public void setSecendInterestRate(String secendInterestRate) {
            this.secendInterestRate = secendInterestRate;
        }

        public String getFirstLife() {
            return firstLife;
        }

        public void setFirstLife(String firstLife) {
            this.firstLife = firstLife;
        }

        public String getSecendLife() {
            return secendLife;
        }

        public void setSecendLife(String secendLife) {
            this.secendLife = secendLife;
        }

        public String getsMemo() {
            return sMemo;
        }

        public void setsMemo(String sMemo) {
            this.sMemo = sMemo;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
