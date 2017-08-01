package com.bjxiyang.zhinengshequ.myapplication.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/8 0008.
 */

public class Plots {

    /**
     * code : 1000
     * msg : 获取小区列表成功
     * obj : [{"nperId":2,"communityName":"鹏阁中区","nperName":"鹏阁中区一期","communityId":3},{"nperId":1,"communityName":"鹏阁小区","nperName":"鹏阁小区二期","communityId":1}]
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
         * nperId : 2
         * communityName : 鹏阁中区
         * nperName : 鹏阁中区一期
         * communityId : 3
         */

        private int nperId;
        private String communityName;
        private String nperName;
        private int communityId;

        public int getNperId() {
            return nperId;
        }

        public void setNperId(int nperId) {
            this.nperId = nperId;
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

        public int getCommunityId() {
            return communityId;
        }

        public void setCommunityId(int communityId) {
            this.communityId = communityId;
        }
    }
}
