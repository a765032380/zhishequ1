package com.bjxiyang.zhinengshequ.myapplication.response_xy;

/**
 * Created by gll on 17-5-23.
 */

public class XY_Response {


    public static final String URL_WEIXIAPI="https://api.mch.weixin.qq.com/pay/unifiedorder";

//    public static final String URL="http://192.168.1.139:8080/zsq/";
//    public static final String URL="http://192.168.1.254:8080/zsq/";
    public static final String URL="http://47.92.106.249:8088/zsq/";
//    public static final String URL="http://192.168.1.103:8080//zsq/";

    public static final String URL_LOGIN=URL+"init/login?";
    public static final String URL_REGISTER=URL+"init/registered?";
    public static final String URL_FINDPWD=URL+"init/findPwd?";
    public static final String URL_SED_MSM=URL+"init/getDynamic?";
    public static final String URL_BANNERLIST=URL+"init/bannerList?";
    public static final String URL_STARTADPAGE=URL+"init/startadpage?";

    public static final String URL_FINDCOMMUNITY=URL+"community/findCommunity?";
    public static final String URL_ADDCOMMUNITY=URL+"community/addCommunity?";
    public static final String URL_FINDFLOOR=URL+"community/findFloor?";
    public static final String URL_FINDUNIT=URL+"community/findUnit?";
    public static final String URL_FINDDOOR=URL+"community/findDoor?";
    public static final String URL_FINDCOMMUNITYBYPER=URL+"community/findCommunityByPer?";
    //添加授权
    public static final String URL_ADDPERMISSION=URL+"community/addPermission?";
    //获得授权列表
    public static final String URL_FINDPERMISSIONS=URL+"community/findPermissions?";
    //修改授权
    public static final String URL_UPDATEPERMISSIONS=URL+"community/updatePermission?";
    //删除授权
    public static final String URL_DELETEPERMISSIONS=URL+"community/deletePermission?";


    public static final String URL_UPDATEUSERINFO=URL+"usercenter/updateUserInfo?";

    public static final String URL_UPDATEUSERINFOFORANDROID=URL+"usercenter/updateUserInfoForAndroid?";

    public static final String URL_USERSUGGEST=URL+"usercenter/userSuggest?";

    public static final String URL_GETUSERINFO=URL+"usercenter/getUserInfo?";

    public static final String URL_GETLOCKBYCOM=URL+"community/getLockByCom?";

    public static final String URL_OPENDOORLIST=URL+"community/openDoorList?";

    public static final String URL_OPENDOOR=URL+"community/openDoor?";
    //获取物业公告列表
    public static final String URL_GETNOTICELIST=URL+"centerinfo/getNoticeList?";
    //获取物业信息
    public static final String URL_GETPROPERTYLIST=URL+"centerinfo/getPropertyList?";

    public static final String URL_PROPAYORDER=URL+"centerinfo/proPayOrder?";

    public static final String URL_ADDPHONELIST=URL+"usercenter/addPhoneList?";

    public static final String URL_CONFIRMPAYORDER=URL+"centerinfo/confirmPayOrder?";

    public static final String URL_UPDATEVERSION=URL+"usercenter/queryLastVersion?";

    public static final String URL_SELECTADVANCEINFO=URL+"finance/selectAdvanceInfo?";

    public static final String URL_SELECTSECONDHAND=URL+"finance/selectSecondHand?";

    public static final String URL_PROPAYORDERBYALI=URL+"centerinfo/proPayOrderByAli?";

    public static final String URL_CENTERINFO_EBPPBILLADD=URL+"centerinfo/ebppBillAdd?";

    public static final String URL_CENTERINFO_EBPPBILLGET=URL+"centerinfo/ebppBillGet?";

}
