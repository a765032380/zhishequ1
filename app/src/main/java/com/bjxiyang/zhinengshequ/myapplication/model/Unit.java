package com.bjxiyang.zhinengshequ.myapplication.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class Unit {

    /**
     * code : 1000
     * msg : 获取单元列表成功
     * obj : [{"unitName":"一单元","unitId":1},{"unitName":"二单元","unitId":2},{"unitName":"三单元","unitId":3},{"unitName":"四单元","unitId":4},{"unitName":"五单元","unitId":5}]
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
         * unitName : 一单元
         * unitId : 1
         */

        private String unitName;
        private int unitId;

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public int getUnitId() {
            return unitId;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }
    }
}
