package com.bjxiyang.zhinengshequ.myapplication.model;

/**
 * Created by Administrator on 2017/7/4 0004.
 */

public class ProPayOrders {

    /**
     * msg : 通信状态:SUCCESS业务状态:SUCCESS
     * obj : {"appid":"wxe0f54cc08a51597f","mch_id":"1481506302","partnerid":"1481506302","order_no":"52256671","device_info":"APP","nonce_str":"oX5tuf0AMWZbB7PI","pay_info_sign":"A84A7C21EC4A4779B254F8595FD0FD38","time_stamp":1499165238747,"return_package":"Sign=WXPay","trade_type":"APP","return_msg":"OK","return_code":"SUCCESS","prepay_id":"wx2017070418471963c4cc32b90432169383"}
     */

    private String msg;
    private ObjBean obj;

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
         * appid : wxe0f54cc08a51597f
         * mch_id : 1481506302
         * partnerid : 1481506302
         * order_no : 52256671
         * device_info : APP
         * nonce_str : oX5tuf0AMWZbB7PI
         * pay_info_sign : A84A7C21EC4A4779B254F8595FD0FD38
         * time_stamp : 1499165238747
         * return_package : Sign=WXPay
         * trade_type : APP
         * return_msg : OK
         * return_code : SUCCESS
         * prepay_id : wx2017070418471963c4cc32b90432169383
         */


//        private String order_no;
//        private String mch_id;
//        private String app_id;
//        private String partnerid;
//        private String prepay_id;
//        private String prepayid;
//        private String nonce_str;
//        private String time_stamp;
//        private String pay_info_sign;
//        private String sign;
//        private String return_package;
//        private String wsign;

        private String appid;
        private String mch_id;
        private String partnerid;
        private String order_no;
        private String device_info;
        private String nonce_str;
        private String pay_info_sign;
        private long time_stamp;
        private String return_package;
        private String trade_type;
        private String return_msg;
        private String return_code;
        private String prepay_id;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getDevice_info() {
            return device_info;
        }

        public void setDevice_info(String device_info) {
            this.device_info = device_info;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getPay_info_sign() {
            return pay_info_sign;
        }

        public void setPay_info_sign(String pay_info_sign) {
            this.pay_info_sign = pay_info_sign;
        }

        public long getTime_stamp() {
            return time_stamp;
        }

        public void setTime_stamp(long time_stamp) {
            this.time_stamp = time_stamp;
        }

        public String getReturn_package() {
            return return_package;
        }

        public void setReturn_package(String return_package) {
            this.return_package = return_package;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }
    }
}
