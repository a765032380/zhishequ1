package com.bjxiyang.zhinengshequ.myapplication.bianlidian;

/**
 * Created by Administrator on 2017/7/7 0007.
 */

public class TiJiaoDingDan {

    /**
     * code : 0
     * time : 1499414844459
     * msg :
     * result : {"id":10,"orderNo":"1002017070710","userId":1,"sellerId":1,"totalAmount":12,"afterDiscountAmount":12,"couponId":0,"payType":0,"userAddressId":9,"communityId":2,"nperId":4,"floorId":10,"unitId":0,"doorId":0,"receiver":"磊磊","receiveAddress":"","receivePhone":"18818888888","remark":"","status":10,"subStatus":0,"sendTime":"","expectSendTime":"","createTime":"2017-07-07 08:07:24","payLimitTime":"2017-07-07 08:17:24","cancelRemark":"","tradeNo":""}
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
         * id : 10
         * orderNo : 1002017070710
         * userId : 1
         * sellerId : 1
         * totalAmount : 12
         * afterDiscountAmount : 12
         * couponId : 0
         * payType : 0
         * userAddressId : 9
         * communityId : 2
         * nperId : 4
         * floorId : 10
         * unitId : 0
         * doorId : 0
         * receiver : 磊磊
         * receiveAddress :
         * receivePhone : 18818888888
         * remark :
         * status : 10
         * subStatus : 0
         * sendTime :
         * expectSendTime :
         * createTime : 2017-07-07 08:07:24
         * payLimitTime : 2017-07-07 08:17:24
         * cancelRemark :
         * tradeNo :
         */

        private int id;
        private String orderNo;
        private int userId;
        private int sellerId;
        private int totalAmount;
        private int afterDiscountAmount;
        private int couponId;
        private int payType;
        private int userAddressId;
        private int communityId;
        private int nperId;
        private int floorId;
        private int unitId;
        private int doorId;
        private String receiver;
        private String receiveAddress;
        private String receivePhone;
        private String remark;
        private int status;
        private int subStatus;
        private String sendTime;
        private String expectSendTime;
        private String createTime;
        private String payLimitTime;
        private String cancelRemark;
        private String tradeNo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public int getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(int totalAmount) {
            this.totalAmount = totalAmount;
        }

        public int getAfterDiscountAmount() {
            return afterDiscountAmount;
        }

        public void setAfterDiscountAmount(int afterDiscountAmount) {
            this.afterDiscountAmount = afterDiscountAmount;
        }

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public int getUserAddressId() {
            return userAddressId;
        }

        public void setUserAddressId(int userAddressId) {
            this.userAddressId = userAddressId;
        }

        public int getCommunityId() {
            return communityId;
        }

        public void setCommunityId(int communityId) {
            this.communityId = communityId;
        }

        public int getNperId() {
            return nperId;
        }

        public void setNperId(int nperId) {
            this.nperId = nperId;
        }

        public int getFloorId() {
            return floorId;
        }

        public void setFloorId(int floorId) {
            this.floorId = floorId;
        }

        public int getUnitId() {
            return unitId;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }

        public int getDoorId() {
            return doorId;
        }

        public void setDoorId(int doorId) {
            this.doorId = doorId;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getReceiveAddress() {
            return receiveAddress;
        }

        public void setReceiveAddress(String receiveAddress) {
            this.receiveAddress = receiveAddress;
        }

        public String getReceivePhone() {
            return receivePhone;
        }

        public void setReceivePhone(String receivePhone) {
            this.receivePhone = receivePhone;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getSubStatus() {
            return subStatus;
        }

        public void setSubStatus(int subStatus) {
            this.subStatus = subStatus;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public String getExpectSendTime() {
            return expectSendTime;
        }

        public void setExpectSendTime(String expectSendTime) {
            this.expectSendTime = expectSendTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPayLimitTime() {
            return payLimitTime;
        }

        public void setPayLimitTime(String payLimitTime) {
            this.payLimitTime = payLimitTime;
        }

        public String getCancelRemark() {
            return cancelRemark;
        }

        public void setCancelRemark(String cancelRemark) {
            this.cancelRemark = cancelRemark;
        }

        public String getTradeNo() {
            return tradeNo;
        }

        public void setTradeNo(String tradeNo) {
            this.tradeNo = tradeNo;
        }
    }
}
