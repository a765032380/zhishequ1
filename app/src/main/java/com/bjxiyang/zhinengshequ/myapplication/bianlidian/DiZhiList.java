package com.bjxiyang.zhinengshequ.myapplication.bianlidian;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4 0004.
 */

public class DiZhiList {
    /**
     * code : 0
     * time : 1499323234907
     * msg :
     * result : [{"doorId":35,"floorName":"三栋","sex":1,"phone":"18818888888","doorName":"109","id":9,"nperId":4,"nperName":"二期","floorId":10,"unitName":"","userId":1,"name":"磊磊","communityName":"国风美唐","unitId":22,"communityId":2},{"doorId":35,"floorName":"三栋","sex":2,"phone":"488467","doorName":"109","id":2,"nperId":4,"nperName":"二期","floorId":10,"unitName":"","userId":1,"name":"77777","communityName":"国风美唐","unitId":22,"communityId":2},{"doorId":35,"floorName":"三栋","sex":1,"phone":"8888","doorName":"109","id":1,"nperId":4,"nperName":"二期","floorId":10,"unitName":"","userId":1,"name":"111","communityName":"国风美唐","unitId":22,"communityId":2}]
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
         * doorId : 35
         * floorName : 三栋
         * sex : 1
         * phone : 18818888888
         * doorName : 109
         * id : 9
         * nperId : 4
         * nperName : 二期
         * floorId : 10
         * unitName :
         * userId : 1
         * name : 磊磊
         * communityName : 国风美唐
         * unitId : 22
         * communityId : 2
         */

        private int doorId;
        private String floorName;
        private int sex;
        private String phone;
        private String doorName;
        private int id;
        private int nperId;
        private String nperName;
        private int floorId;
        private String unitName;
        private int userId;
        private String name;
        private String communityName;
        private int unitId;
        private int communityId;

        public int getDoorId() {
            return doorId;
        }

        public void setDoorId(int doorId) {
            this.doorId = doorId;
        }

        public String getFloorName() {
            return floorName;
        }

        public void setFloorName(String floorName) {
            this.floorName = floorName;
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

        public String getDoorName() {
            return doorName;
        }

        public void setDoorName(String doorName) {
            this.doorName = doorName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNperId() {
            return nperId;
        }

        public void setNperId(int nperId) {
            this.nperId = nperId;
        }

        public String getNperName() {
            return nperName;
        }

        public void setNperName(String nperName) {
            this.nperName = nperName;
        }

        public int getFloorId() {
            return floorId;
        }

        public void setFloorId(int floorId) {
            this.floorId = floorId;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
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

        public String getCommunityName() {
            return communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
        }

        public int getUnitId() {
            return unitId;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }

        public int getCommunityId() {
            return communityId;
        }

        public void setCommunityId(int communityId) {
            this.communityId = communityId;
        }
    }
}
