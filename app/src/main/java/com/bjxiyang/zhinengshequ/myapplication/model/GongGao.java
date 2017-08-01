package com.bjxiyang.zhinengshequ.myapplication.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class GongGao implements Serializable {

    /**
     * code : 1000
     * msg : 获取小区列表成功
     * obj : [{"noticer":"是豆腐干士大夫敢死队风格的","addTime":"2017-06-18 10:25:36","title":"撒地方撒旦飞洒的","type":0,"noticeId":8,"content":"撒旦飞洒的发射点发射点发射点发"},{"noticer":"撒旦飞洒的","addTime":"2017-06-18 09:25:36","title":"阿斯蒂芬撒旦发射点发","type":1,"noticeId":7,"content":"是打发士大夫撒旦发生发射点发射点"},{"noticer":"北京德胜物业公司","addTime":"2017-06-18 08:25:36","title":"缴纳物业费通知","type":1,"noticeId":4,"content":"撒旦飞洒地方撒打发士大夫撒旦发生大单反"},{"noticer":"北京德胜","addTime":"2017-06-18 08:25:36","title":"撒旦发顺丰","type":0,"noticeId":5,"content":"孙大发孙大发孙大发"},{"noticer":"啊士大夫但是","addTime":"2017-06-18 08:25:36","title":"士大夫撒旦发","type":1,"noticeId":6,"content":"士大夫撒旦发射点发射点"}]
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

    public static class Obj implements Serializable {
        /**
         * noticer : 是豆腐干士大夫敢死队风格的
         * addTime : 2017-06-18 10:25:36
         * title : 撒地方撒旦飞洒的
         * type : 0
         * noticeId : 8
         * content : 撒旦飞洒的发射点发射点发射点发
         */

        private String noticer;
        private String addTime;
        private String title;
        private int type;
        private int noticeId;
        private String content;

        public String getNoticer() {
            return noticer;
        }

        public void setNoticer(String noticer) {
            this.noticer = noticer;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(int noticeId) {
            this.noticeId = noticeId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
