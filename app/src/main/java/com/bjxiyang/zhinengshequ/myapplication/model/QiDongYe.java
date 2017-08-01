package com.bjxiyang.zhinengshequ.myapplication.model;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class QiDongYe {

    /**
     * code : 1000
     * msg : 获取启动广告位成功
     * obj : {"adRedirectUrl":"www.bjxiyang.com","adUrl":"http://47.92.106.249:8087/upload/6s.jpg"}
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
         * adRedirectUrl : www.bjxiyang.com
         * adUrl : http://47.92.106.249:8087/upload/6s.jpg
         */

        private String adRedirectUrl;
        private String adUrl;

        public String getAdRedirectUrl() {
            return adRedirectUrl;
        }

        public void setAdRedirectUrl(String adRedirectUrl) {
            this.adRedirectUrl = adRedirectUrl;
        }

        public String getAdUrl() {
            return adUrl;
        }

        public void setAdUrl(String adUrl) {
            this.adUrl = adUrl;
        }
    }
}
