package com.bjxiyang.zhinengshequ.myapplication.model;

import java.util.List;

/**
 * Created by gll on 17-5-24.
 */

public class Loan {

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

//1	gId	Int	信息Id
//2	  gName	String	信息名称
//3	gLoanTerm	String	贷款期限
//4	gCollateral	Int	抵押物 0：无1：车辆2：房产
//5	gQuota	String	贷款额度
//6	  gEntertainTime	  String	下款期限
//7	gMonthlyRate	String	参考月利率
//8	gMomo	String	详细说明
        private int gType;
        private int gId;
        private String gName;
        private String gLoanTerm;
        private int gCollateral;
        private String gQuota;
        private String gEntertainTime;
        private String gMonthlyRate;
        private String gMomo;
        private String url;


        private String sId;
        private String sName;
        private String firstNper;
        private String firstInterestRate;
        private String secendNper;
        private String secendInterestRate;
        private String firstLife;
        private String secendLife;
        private String sMemo;

        public String getsId() {
            return sId;
        }

        public void setsId(String sId) {
            this.sId = sId;
        }

        public String getsName() {
            return sName;
        }

        public void setsName(String sName) {
            this.sName = sName;
        }

        public String getFirstNper() {
            return firstNper;
        }

        public void setFirstNper(String firstNper) {
            this.firstNper = firstNper;
        }

        public String getFirstInterestRate() {
            return firstInterestRate;
        }

        public void setFirstInterestRate(String firstInterestRate) {
            this.firstInterestRate = firstInterestRate;
        }

        public String getSecendNper() {
            return secendNper;
        }

        public void setSecendNper(String secendNper) {
            this.secendNper = secendNper;
        }

        public String getSecendInterestRate() {
            return secendInterestRate;
        }

        public void setSecendInterestRate(String secendInterestRate) {
            this.secendInterestRate = secendInterestRate;
        }

        public String getFirstLife() {
            return firstLife;
        }

        public void setFirstLife(String firstLife) {
            this.firstLife = firstLife;
        }

        public String getSecendLife() {
            return secendLife;
        }

        public void setSecendLife(String secendLife) {
            this.secendLife = secendLife;
        }

        public String getsMemo() {
            return sMemo;
        }

        public void setsMemo(String sMemo) {
            this.sMemo = sMemo;
        }

        public int getgType() {
            return gType;
        }

        public void setgType(int gType) {
            this.gType = gType;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }



        public int getgId() {
            return gId;
        }

        public void setgId(int gId) {
            this.gId = gId;
        }

        public String getgName() {
            return gName;
        }

        public void setgName(String gName) {
            this.gName = gName;
        }

        public String getgLoanTerm() {
            return gLoanTerm;
        }

        public void setgLoanTerm(String gLoanTerm) {
            this.gLoanTerm = gLoanTerm;
        }

        public int getgCollateral() {
            return gCollateral;
        }

        public void setgCollateral(int gCollateral) {
            this.gCollateral = gCollateral;
        }

        public String getgQuota() {
            return gQuota;
        }

        public void setgQuota(String gQuota) {
            this.gQuota = gQuota;
        }

        public String getgEntertainTime() {
            return gEntertainTime;
        }

        public void setgEntertainTime(String gEntertainTime) {
            this.gEntertainTime = gEntertainTime;
        }

        public String getgMonthlyRate() {
            return gMonthlyRate;
        }

        public void setgMonthlyRate(String gMonthlyRate) {
            this.gMonthlyRate = gMonthlyRate;
        }

        public String getgMomo() {
            return gMomo;
        }

        public void setgMomo(String gMomo) {
            this.gMomo = gMomo;
        }
    }



//    //类型
//    private int type;
//    //产品名称
//    private String name;
//    //贷款期限
//    private String daikuanTime;
//    //下款期限
//    private String xiaTime;
//    //抵押物
//    private String diyawu;
//    //参考利率
//    private String cankaolilv;
//    //贷款额度
//    private String daikuanedu;
//    //首期首付
//    private String oneshoufu;
//    //二套首付
//    private String twoshoufu;
//    //首期利率
//    private String onelilv;
//    //二套利率
//    private String twolilv;
//    //首期年限
//    private String onenianxian;
//    //二套年限
//    private String twonianxian;
//    //简介
//    private String jianjie;
//
//
//
//    public int getType() {
//        return type;
//    }
//
//    public void setType(int type) {
//        this.type = type;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDaikuanTime() {
//        return daikuanTime;
//    }
//
//    public void setDaikuanTime(String daikuanTime) {
//        this.daikuanTime = daikuanTime;
//    }
//
//    public String getXiaTime() {
//        return xiaTime;
//    }
//
//    public void setXiaTime(String xiaTime) {
//        this.xiaTime = xiaTime;
//    }
//
//    public String getDiyawu() {
//        return diyawu;
//    }
//
//    public void setDiyawu(String diyawu) {
//        this.diyawu = diyawu;
//    }
//
//    public String getCankaolilv() {
//        return cankaolilv;
//    }
//
//    public void setCankaolilv(String cankaolilv) {
//        this.cankaolilv = cankaolilv;
//    }
//
//    public String getDaikuanedu() {
//        return daikuanedu;
//    }
//
//    public void setDaikuanedu(String daikuanedu) {
//        this.daikuanedu = daikuanedu;
//    }
//
//    public String getOneshoufu() {
//        return oneshoufu;
//    }
//
//    public void setOneshoufu(String oneshoufu) {
//        this.oneshoufu = oneshoufu;
//    }
//
//    public String getTwoshoufu() {
//        return twoshoufu;
//    }
//
//    public void setTwoshoufu(String twoshoufu) {
//        this.twoshoufu = twoshoufu;
//    }
//
//    public String getOnelilv() {
//        return onelilv;
//    }
//
//    public void setOnelilv(String onelilv) {
//        this.onelilv = onelilv;
//    }
//
//    public String getTwolilv() {
//        return twolilv;
//    }
//
//    public void setTwolilv(String twolilv) {
//        this.twolilv = twolilv;
//    }
//
//    public String getOnenianxian() {
//        return onenianxian;
//    }
//
//    public void setOnenianxian(String onenianxian) {
//        this.onenianxian = onenianxian;
//    }
//
//    public String getTwonianxian() {
//        return twonianxian;
//    }
//
//    public void setTwonianxian(String twonianxian) {
//        this.twonianxian = twonianxian;
//    }
//
//    public String getJianjie() {
//        return jianjie;
//    }
//
//    public void setJianjie(String jianjie) {
//        this.jianjie = jianjie;
//    }
}
