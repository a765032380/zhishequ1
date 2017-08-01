package com.bjxiyang.zhinengshequ.myapplication.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class ByCom {

    /**
     * code : 1000
     * msg : 获取门禁列表成功
     * obj : [{"lockId":2,"lockName":"f "},{"lockId":14,"lockName":"东门"},{"lockId":16,"lockName":"校门"},{"lockId":17,"lockName":"大门"}]
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
         * lockId : 2
         * lockName : f
         */

        private int lockId;
        private String lockName;

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
    }
}
