package com.bjxiyang.zhinengshequ.myapplication.model;

/**
 * Created by Administrator on 2017/6/23 0023.
 */

public class Users1 {

    /**
     * code : 1000
     * msg : 登录成功
     * obj : {"birthday":"","qq":"","ownerStatus":"1","address":"","nickName":"","headPhotoUrl":"","c_memberId":6,"sex":"","idNumber":"","ownerId":"","realName":"","propertyStatus":"1","password":"bbb8aae57c104cda40c93843ad5e6db8","mobilePhone":"18813045215","weChat":"","age":"","email":""}
     */

    private String code;
    private String msg;
    private Users1.Obj obj;

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

    public Users1.Obj getObj() {
        return obj;
    }

    public void setObj(Users1.Obj obj) {
        this.obj = obj;
    }

    public static class Obj {
        /**
         * birthday :
         * qq :
         * ownerStatus : 1
         * address :
         * nickName :
         * headPhotoUrl :
         * c_memberId : 6
         * sex :
         * idNumber :
         * ownerId :
         * realName :
         * propertyStatus : 1
         * password : bbb8aae57c104cda40c93843ad5e6db8
         * mobilePhone : 18813045215
         * weChat :
         * age :
         * email :
         */
        private int integral;
        private String birthday;
        private String qq;
        private String ownerStatus;
        private String address;
        private String nickName;
        private String headPhotoUrl;
        private int c_memberId;
        private String sex;
        private String idNumber;
        private String ownerId;
        private String realName;
        private String propertyStatus;
        private String password;
        private String mobilePhone;
        private String weChat;
        private String age;
        private String email;

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getOwnerStatus() {
            return ownerStatus;
        }

        public void setOwnerStatus(String ownerStatus) {
            this.ownerStatus = ownerStatus;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadPhotoUrl() {
            return headPhotoUrl;
        }

        public void setHeadPhotoUrl(String headPhotoUrl) {
            this.headPhotoUrl = headPhotoUrl;
        }

        public int getC_memberId() {
            return c_memberId;
        }

        public void setC_memberId(int c_memberId) {
            this.c_memberId = c_memberId;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(String ownerId) {
            this.ownerId = ownerId;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getPropertyStatus() {
            return propertyStatus;
        }

        public void setPropertyStatus(String propertyStatus) {
            this.propertyStatus = propertyStatus;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getWeChat() {
            return weChat;
        }

        public void setWeChat(String weChat) {
            this.weChat = weChat;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
