package com.bjxiyang.zhinengshequ.myapplication.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class SelectPlot {

    /**
     * code : 1000
     * msg : 获取社区权限成功
     * obj : [{"floorId":1,"nperId":1,"unitName":"一单元","doorName":"101","unitId":1,"communityName":"鹏阁小区","nperName":"二期","floorName":"三栋","communityId":1,"doorId":1},{"floorId":1,"nperId":1,"unitName":"一单元","doorName":"101","unitId":1,"communityName":"鹏阁小区","nperName":"二期","floorName":"三栋","communityId":1,"doorId":1},{"floorId":1,"nperId":1,"unitName":"一单元","doorName":"101","unitId":1,"communityName":"鹏阁小区","nperName":"二期","floorName":"三栋","communityId":1,"doorId":1},{"floorId":1,"nperId":1,"unitName":"一单元","doorName":"101","unitId":1,"communityName":"鹏阁小区","nperName":"二期","floorName":"三栋","communityId":1,"doorId":1}]
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
         * floorId : 1
         * nperId : 1
         * unitName : 一单元
         * doorName : 101
         * unitId : 1
         * communityName : 鹏阁小区
         * nperName : 二期
         * floorName : 三栋
         * communityId : 1
         * doorId : 1
         */

        private int floorId;
        private int nperId;
        private int unitId;
        private int communityId;
        private int doorId;
        private int roleType;
        private String communityName;
        private String unitName;
        private String doorName;
        private String nperName;
        private String floorName;

        public int getRoleType() {
            return roleType;
        }

        public void setRoleType(int roleType) {
            this.roleType = roleType;
        }

        public List<Map> getLockList() {
            return lockList;
        }

        public void setLockList(List<Map> lockList) {
            this.lockList = lockList;
        }

        public Map getMap() {
            return map;
        }

        public void setMap(Map map) {
            this.map = map;
        }

        private List<Map> lockList;
        private Map map;
        public static class Map{
            public int getLockId() {
                return lockId;
            }

            public void setLockId(int lockId) {
                this.lockId = lockId;
            }

            public String getLockName() {
                return lockName;
            }

            public void setLockName(String lockName) {
                this.lockName = lockName;
            }

            private int lockId;
            private String lockName;

        }

        public int getFloorId() {
            return floorId;
        }

        public void setFloorId(int floorId) {
            this.floorId = floorId;
        }

        public int getNperId() {
            return nperId;
        }

        public void setNperId(int nperId) {
            this.nperId = nperId;
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

        public int getUnitId() {
            return unitId;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }

        public String getCommunityName() {
            return communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
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

        public int getDoorId() {
            return doorId;
        }

        public void setDoorId(int doorId) {
            this.doorId = doorId;
        }
    }
}
