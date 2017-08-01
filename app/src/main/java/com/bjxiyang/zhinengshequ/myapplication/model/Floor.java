package com.bjxiyang.zhinengshequ.myapplication.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class Floor {

    /**
     * code : 1000
     * msg : 获取楼栋列表成功
     * obj : [{"floorId":1,"floorName":"三栋"},{"floorId":2,"floorName":"二栋"},{"floorId":3,"floorName":"一栋"}]
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
         * floorName : 三栋
         */

        private int floorId;
        private String floorName;

        public int getFloorId() {
            return floorId;
        }

        public void setFloorId(int floorId) {
            this.floorId = floorId;
        }

        public String getFloorName() {
            return floorName;
        }

        public void setFloorName(String floorName) {
            this.floorName = floorName;
        }
    }
}
