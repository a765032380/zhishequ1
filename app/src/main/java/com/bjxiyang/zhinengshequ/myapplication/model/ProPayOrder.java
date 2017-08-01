package com.bjxiyang.zhinengshequ.myapplication.model;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class ProPayOrder {




    /**
     * code : 1000
     * msg : 通信状态:SUCCESS业务状态:SUCCESS
     * obj : {"order_no":"2342343225","mch_id":"m20170330000000069","app_id":"wx75014952d4daeaf2","partnerid":"23955501","prepay_id":"wx20170621120457c0fff5a4a40434168567","prepayid":"wx20170621120457c0fff5a4a40434168567","nonce_str":"O2GcV2KT6BgI1pRp","time_stamp":"1498017898","pay_info_sign":"0CB6E6F1D728ADC2BEB5FD4DB96452F3","sign":"B623573E1462DC9F8ED28A90B70887FF","return_package":"Sign=WXPay","wsign":"B623573E1462DC9F8ED28A90B70887FF"}
     */

    private String code;
    private String msg;
    private Obj obj;

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

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }

    public static class Obj {
        /**
         * order_no : 2342343225
         * mch_id : m20170330000000069
         * app_id : wx75014952d4daeaf2
         * partnerid : 23955501
         * prepay_id : wx20170621120457c0fff5a4a40434168567
         * prepayid : wx20170621120457c0fff5a4a40434168567
         * nonce_str : O2GcV2KT6BgI1pRp
         * time_stamp : 1498017898
         * pay_info_sign : 0CB6E6F1D728ADC2BEB5FD4DB96452F3
         * sign : B623573E1462DC9F8ED28A90B70887FF
         * return_package : Sign=WXPay
         *
         * wsign : B623573E1462DC9F8ED28A90B70887FF
         */

        private String order_no;
        private String mch_id;
        private String app_id;
        private String partnerid;
        private String prepay_id;
        private String prepayid;
        private String nonce_str;
        private String time_stamp;
        private String pay_info_sign;
        private String sign;
        private String return_package;
        private String wsign;

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getApp_id() {
            return app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getTime_stamp() {
            return time_stamp;
        }

        public void setTime_stamp(String time_stamp) {
            this.time_stamp = time_stamp;
        }

        public String getPay_info_sign() {
            return pay_info_sign;
        }

        public void setPay_info_sign(String pay_info_sign) {
            this.pay_info_sign = pay_info_sign;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getReturn_package() {
            return return_package;
        }

        public void setReturn_package(String return_package) {
            this.return_package = return_package;
        }

        public String getWsign() {
            return wsign;
        }

        public void setWsign(String wsign) {
            this.wsign = wsign;
        }
    }
}
