package com.bjxiyang.zhinengshequ.myapplication.model;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class OrderWeiXin {


    /**
     * code : 0
     * time : 1500025688694
     * msg :
     * result : {"nonce_str":"WchGcygqd4zj6WKg","return_code":"SUCCESS","pay_info_sign":"990FD487BBB263AED9AE683DBD893E23","time_stamp":"1500025688","return_package":"Sign=WXPay","device_info":"APP","mch_id":"1481506302","prepay_id":"wx201707141748081f9f7febf50851257605","order_no":"10020170714260","return_msg":"OK","appid":"wxe0f54cc08a51597f","partnerid":"1481506302","trade_type":"APP"}
     */

    private int code;
    private long time;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * nonce_str : WchGcygqd4zj6WKg
         * return_code : SUCCESS
         * pay_info_sign : 990FD487BBB263AED9AE683DBD893E23
         * time_stamp : 1500025688
         * return_package : Sign=WXPay
         * device_info : APP
         * mch_id : 1481506302
         * prepay_id : wx201707141748081f9f7febf50851257605
         * order_no : 10020170714260
         * return_msg : OK
         * appid : wxe0f54cc08a51597f
         * partnerid : 1481506302
         * trade_type : APP
         */

        private String nonce_str;
        private String return_code;
        private String pay_info_sign;
        private String time_stamp;
        private String return_package;
        private String device_info;
        private String mch_id;
        private String prepay_id;
        private String order_no;
        private String return_msg;
        private String appid;
        private String partnerid;
        private String trade_type;

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getPay_info_sign() {
            return pay_info_sign;
        }

        public void setPay_info_sign(String pay_info_sign) {
            this.pay_info_sign = pay_info_sign;
        }

        public String getTime_stamp() {
            return time_stamp;
        }

        public void setTime_stamp(String time_stamp) {
            this.time_stamp = time_stamp;
        }

        public String getReturn_package() {
            return return_package;
        }

        public void setReturn_package(String return_package) {
            this.return_package = return_package;
        }

        public String getDevice_info() {
            return device_info;
        }

        public void setDevice_info(String device_info) {
            this.device_info = device_info;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }
    }
}
