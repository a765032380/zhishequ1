package com.bjxiyang.zhinengshequ.myapplication.bianlidian;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public class DianMing {

    /**
     * code : 0
     * time : 1499048065747
     * msg :
     * result : [{"alipayNo":"lisi@qq.com","logo":"logoURL","des":"","loginKey":"","linkphone":"15812345678","status":0,"bankSubName":"农业银行XX支行","bankName":"农业银行","endTime":0,"wxNo":"lisi","bankCard":"4200234577094030","startTime":0,"id":1,"address":"","serviceTime":0,"shopName":"第一店铺","accountTotal":0,"linkman":"张三","bankUser":"李四","loginName":"test","communityId":1}]
     */

    private int code;
    private long time;
    private String msg;
    private List<Result> result;

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

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public static class Result {
        /**
         * alipayNo : lisi@qq.com
         * logo : logoURL
         * des :
         * loginKey :
         * linkphone : 15812345678
         * status : 0
         * bankSubName : 农业银行XX支行
         * bankName : 农业银行
         * endTime : 0
         * wxNo : lisi
         * bankCard : 4200234577094030
         * startTime : 0
         * id : 1
         * address :
         * serviceTime : 0
         * shopName : 第一店铺
         * accountTotal : 0
         * linkman : 张三
         * bankUser : 李四
         * loginName : test
         * communityId : 1
         */

        private String alipayNo;
        private String logo;
        private String des;
        private String loginKey;
        private String linkphone;
        private int status;
        private String bankSubName;
        private String bankName;
        private int endTime;
        private String wxNo;
        private String bankCard;
        private int startTime;
        private int id;
        private String address;
        private int serviceTime;
        private String shopName;
        private int accountTotal;
        private String linkman;
        private String bankUser;
        private String loginName;
        private int communityId;

        public String getAlipayNo() {
            return alipayNo;
        }

        public void setAlipayNo(String alipayNo) {
            this.alipayNo = alipayNo;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getLoginKey() {
            return loginKey;
        }

        public void setLoginKey(String loginKey) {
            this.loginKey = loginKey;
        }

        public String getLinkphone() {
            return linkphone;
        }

        public void setLinkphone(String linkphone) {
            this.linkphone = linkphone;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getBankSubName() {
            return bankSubName;
        }

        public void setBankSubName(String bankSubName) {
            this.bankSubName = bankSubName;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        public String getWxNo() {
            return wxNo;
        }

        public void setWxNo(String wxNo) {
            this.wxNo = wxNo;
        }

        public String getBankCard() {
            return bankCard;
        }

        public void setBankCard(String bankCard) {
            this.bankCard = bankCard;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getServiceTime() {
            return serviceTime;
        }

        public void setServiceTime(int serviceTime) {
            this.serviceTime = serviceTime;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public int getAccountTotal() {
            return accountTotal;
        }

        public void setAccountTotal(int accountTotal) {
            this.accountTotal = accountTotal;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getBankUser() {
            return bankUser;
        }

        public void setBankUser(String bankUser) {
            this.bankUser = bankUser;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public int getCommunityId() {
            return communityId;
        }

        public void setCommunityId(int communityId) {
            this.communityId = communityId;
        }
    }
}
