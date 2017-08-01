package com.bjxiyang.zhinengshequ.myapplication.bianlidian;

/**
 * Created by Administrator on 2017/7/5 0005.
 */

public class DiZhiUpdate {

    /**
     * code : 0
     * time : 1499245967775
     * msg :
     * result : {"id":2,"userId":1,"name":"777","sex":1,"phone":"888888","communityId":2,"nperId":4,"floorId":10,"unitId":22,"doorId":35}
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
         * id : 2
         * userId : 1
         * name : 777
         * sex : 1
         * phone : 888888
         * communityId : 2
         * nperId : 4
         * floorId : 10
         * unitId : 22
         * doorId : 35
         */

        private int id;
        private int userId;
        private String name;
        private int sex;
        private String phone;
        private int communityId;
        private int nperId;
        private int floorId;
        private int unitId;
        private int doorId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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
    }
}
