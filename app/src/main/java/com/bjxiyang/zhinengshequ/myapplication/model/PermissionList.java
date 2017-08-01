package com.bjxiyang.zhinengshequ.myapplication.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/10 0010.
 */

public class PermissionList {


    /**
     * code : 1000
     * msg : 获取钥匙授权成功
     * obj : [{"permissionId":3,"nickName":"张三","headPhotoUrl":"http://s22/sdf","communityName":"鹏阁小区","roleType":"业主","add_time":"2017-06-09"},{"permissionId":16,"nickName":"张三","headPhotoUrl":"http://s22/sdf","roleType":"租客家人","add_time":"2017-06-10"}]
     */

    private String code;
    private String msg;
    private List<Obj> obj;

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

    public List<Obj> getObj() {
        return obj;
    }

    public void setObj(List<Obj> obj) {
        this.obj = obj;
    }

    public static class Obj {
        /**
         * permissionId : 3
         * nickName : 张三
         * headPhotoUrl : http://s22/sdf
         * communityName : 鹏阁小区
         * roleType : 业主
         * add_time : 2017-06-09
         */

        private int  c_memberId;

        private int status;
        private int permissionId;
        private String nickName;
        private String headPhotoUrl;
        private int roleType;
        private String add_time;

        private int communityId;
        private int nperId;
        private int floorId;
        private int unitId;
        private int doorId;

        private String communityName;
        private String unitName;
        private String doorName;
        private String nperName;
        private String floorName;


        public int getC_memberId() {
            return c_memberId;
        }

        public void setC_memberId(int c_memberId) {
            this.c_memberId = c_memberId;
        }


        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getDoorName() {
            return doorName;
        }

        public void setDoorName(String doorName) {
            this.doorName = doorName;
        }

        public String getNperName() {
            return nperName;
        }

        public void setNperName(String nperName) {
            this.nperName = nperName;
        }

        public String getFloorName() {
            return floorName;
        }

        public void setFloorName(String floorName) {
            this.floorName = floorName;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPermissionId() {
            return permissionId;
        }

        public void setPermissionId(int permissionId) {
            this.permissionId = permissionId;
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

        public String getCommunityName() {
            return communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
        }

        public int getRoleType() {
            return roleType;
        }

        public void setRoleType(int roleType) {
            this.roleType = roleType;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
