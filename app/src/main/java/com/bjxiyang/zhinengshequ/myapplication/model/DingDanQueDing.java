package com.bjxiyang.zhinengshequ.myapplication.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/20 0020.
 */

public class DingDanQueDing {
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

    public static class Obj{
        private String ORDERNOTEXIST;
        public String getORDERNOTEXIST() {
            return ORDERNOTEXIST;
        }

        public void setORDERNOTEXIST(String ORDERNOTEXIST) {
            this.ORDERNOTEXIST = ORDERNOTEXIST;
        }




    }
}
