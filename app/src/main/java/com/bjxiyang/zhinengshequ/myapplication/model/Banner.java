package com.bjxiyang.zhinengshequ.myapplication.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13 0013.
 */

public class Banner {

    /**
     * code : 1000
     * msg : 获取广告位成功
     * obj : [{"ad_Url":"http://47.92.106.249:8088//img//23.jpg","ad_Type":1,"ad_Inf":"www.baidu.com","status":1}]
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
         * ad_Url : http://47.92.106.249:8088//img//23.jpg
         * ad_Type : 1
         * ad_Inf : www.baidu.com
         * status : 1
         */

        private String adUrl;
        private int adType;
        private String adInf;
        private int status;

        public String getAdUrl() {
            return adUrl;
        }

        public void setAdUrl(String adUrl) {
            this.adUrl = adUrl;
        }

        public int getAdType() {
            return adType;
        }

        public void setAdType(int adType) {
            this.adType = adType;
        }

        public String getAdInf() {
            return adInf;
        }

        public void setAdInf(String ad_Inf) {
            this.adInf = adInf;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
