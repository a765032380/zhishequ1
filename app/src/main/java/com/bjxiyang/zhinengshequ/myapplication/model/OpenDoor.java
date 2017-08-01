package com.bjxiyang.zhinengshequ.myapplication.model;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class OpenDoor {

    /**
     * code : 1000
     * msg : 开门成功
     * Obj : {"bondUrl":"http://www.bjxiyang.com","validityDate":"2017-07-08---2017-07-30","bondName":"开门有喜","bondLimit":"7折优惠"}
     */

    private String code;
    private String msg;
    private ObjBean Obj;

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

    public ObjBean getObj() {
        return Obj;
    }

    public void setObj(ObjBean Obj) {
        this.Obj = Obj;
    }

    public static class ObjBean {
        /**
         * bondUrl : http://www.bjxiyang.com
         * validityDate : 2017-07-08---2017-07-30
         * bondName : 开门有喜
         * bondLimit : 7折优惠
         */

        private String bondUrl;
        private String validityDate;
        private String bondName;
        private String bondLimit;
        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getBondUrl() {
            return bondUrl;
        }

        public void setBondUrl(String bondUrl) {
            this.bondUrl = bondUrl;
        }

        public String getValidityDate() {
            return validityDate;
        }

        public void setValidityDate(String validityDate) {
            this.validityDate = validityDate;
        }

        public String getBondName() {
            return bondName;
        }

        public void setBondName(String bondName) {
            this.bondName = bondName;
        }

        public String getBondLimit() {
            return bondLimit;
        }

        public void setBondLimit(String bondLimit) {
            this.bondLimit = bondLimit;
        }
    }
}
