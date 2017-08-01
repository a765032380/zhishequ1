package com.bjxiyang.zhinengshequ.myapplication.bianlidian;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6 0006.
 */

public class YouHuiQuan {

    /**
     * code : 0
     * time : 1500450729899
     * msg :
     * result : [{"id":77,"startDate":"2017-07-19 11:12:43","couponNo":"200201707198","status":1,"sellerId":0,"name":"中秋","receiveTime":"2017-07-19 15:48:17","endDate":"2017-07-22 11:12:45","minConsume":5000,"expireType":1,"discount":1},{"id":76,"startDate":"2017-07-19 11:12:43","couponNo":"200201707198","status":1,"sellerId":0,"name":"中秋","receiveTime":"2017-07-19 15:47:48","endDate":"2017-07-22 11:12:45","minConsume":5000,"expireType":1,"discount":1},{"id":74,"startDate":"2017-07-19 11:12:43","couponNo":"200201707198","status":1,"sellerId":0,"name":"中秋","receiveTime":"2017-07-19 15:43:07","endDate":"2017-07-22 11:12:45","minConsume":5000,"expireType":1,"discount":1},{"id":73,"startDate":"2017-07-19 11:12:43","couponNo":"200201707198","status":1,"sellerId":0,"name":"中秋","receiveTime":"2017-07-19 15:33:15","endDate":"2017-07-22 11:12:45","minConsume":5000,"expireType":1,"discount":1},{"id":71,"startDate":"2017-07-20 00:00:00","couponNo":"200201707186","status":1,"sellerId":0,"name":"测试优惠","receiveTime":"2017-07-19 15:06:46","endDate":"2017-07-25 00:00:00","minConsume":10000,"expireType":2,"discount":1},{"id":70,"startDate":"","couponNo":"200201707094","status":1,"sellerId":0,"name":"十一活动","receiveTime":"2017-07-19 15:06:44","endDate":"","minConsume":1000,"expireType":0,"discount":85}]
     */

    private int code;
    private long time;
    private String msg;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 77
         * startDate : 2017-07-19 11:12:43
         * couponNo : 200201707198
         * status : 1
         * sellerId : 0
         * name : 中秋
         * receiveTime : 2017-07-19 15:48:17
         * endDate : 2017-07-22 11:12:45
         * minConsume : 5000
         * expireType : 1
         * discount : 1
         */

        private int id;
        private String startDate;
        private String couponNo;
        private int status;
        private int sellerId;
        private String name;
        private String receiveTime;
        private String endDate;
        private int minConsume;
        private int expireType;
        private int discount;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getCouponNo() {
            return couponNo;
        }

        public void setCouponNo(String couponNo) {
            this.couponNo = couponNo;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getReceiveTime() {
            return receiveTime;
        }

        public void setReceiveTime(String receiveTime) {
            this.receiveTime = receiveTime;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public int getMinConsume() {
            return minConsume;
        }

        public void setMinConsume(int minConsume) {
            this.minConsume = minConsume;
        }

        public int getExpireType() {
            return expireType;
        }

        public void setExpireType(int expireType) {
            this.expireType = expireType;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }
    }
}
