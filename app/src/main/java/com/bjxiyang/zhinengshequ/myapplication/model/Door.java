package com.bjxiyang.zhinengshequ.myapplication.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class Door {

    /**
     * code : 1000
     * msg : 获取门牌列表成功
     * obj : [{"doorName":"101","doorId":21},{"doorName":"102","doorId":22},{"doorName":"103","doorId":23}]
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
         * doorName : 101
         * doorId : 21
         */

        private String doorName;
        private int doorId;

        public String getDoorName() {
            return doorName;
        }

        public void setDoorName(String doorName) {
            this.doorName = doorName;
        }

        public int getDoorId() {
            return doorId;
        }

        public void setDoorId(int doorId) {
            this.doorId = doorId;
        }
    }
}
