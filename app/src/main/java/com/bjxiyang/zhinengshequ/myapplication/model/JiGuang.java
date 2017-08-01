package com.bjxiyang.zhinengshequ.myapplication.model;

/**
 * Created by Administrator on 2017/6/17 0017.
 */

public class JiGuang {

    /**
     * msg_content : 亲爱的用户您好，您的门禁系统已经被禁用，请联系业主开通
     * extras : {"count":"亲爱的用户您好，您的门禁系统已经被禁用，请联系业主开通","type":"1","title":"授权改变通知"}
     * title : 授权改变通知
     */

    private String msg_content;
    private Extras extras;
    private String title;

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public Extras getExtras() {
        return extras;
    }

    public void setExtras(Extras extras) {
        this.extras = extras;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class Extras {
        /**
         * count : 亲爱的用户您好，您的门禁系统已经被禁用，请联系业主开通
         * type : 1
         * title : 授权改变通知
         */

        private String count;
        private String type;
        private String title;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
