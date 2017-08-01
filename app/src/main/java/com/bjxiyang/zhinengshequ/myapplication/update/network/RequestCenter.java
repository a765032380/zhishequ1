package com.bjxiyang.zhinengshequ.myapplication.update.network;

import com.baisi.myapplication.okhttp.CommonOkHttpClient;
import com.baisi.myapplication.okhttp.listener.DisposeDataHandle;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.baisi.myapplication.okhttp.listener.DisposeDownloadListener;
import com.baisi.myapplication.okhttp.request.CommonRequest;
import com.baisi.myapplication.okhttp.request.RequestParams;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.AliZhiFu;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DiZhiAdd;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DiZhiDelete;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DiZhiList;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DiZhiUpdate;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DianMing;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DingDan;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.OrderDelete;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.ShangPinList;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.ShangPingDetail;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.TiJiaoDingDan;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.YouHuiQuan;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.Banner;
import com.bjxiyang.zhinengshequ.myapplication.model.ByCom;
import com.bjxiyang.zhinengshequ.myapplication.model.Door;
import com.bjxiyang.zhinengshequ.myapplication.model.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.model.Floor;
import com.bjxiyang.zhinengshequ.myapplication.model.GongGao;
import com.bjxiyang.zhinengshequ.myapplication.model.Loan;
import com.bjxiyang.zhinengshequ.myapplication.model.OpenDoor;
import com.bjxiyang.zhinengshequ.myapplication.model.OpenDoorList;
import com.bjxiyang.zhinengshequ.myapplication.model.OrderWeiXin;
import com.bjxiyang.zhinengshequ.myapplication.model.PermissionList;
import com.bjxiyang.zhinengshequ.myapplication.model.Plots;
import com.bjxiyang.zhinengshequ.myapplication.model.ProPayOrderByAli;
import com.bjxiyang.zhinengshequ.myapplication.model.QiDongYe;
import com.bjxiyang.zhinengshequ.myapplication.model.SelectPlot;
import com.bjxiyang.zhinengshequ.myapplication.model.Unit;
import com.bjxiyang.zhinengshequ.myapplication.model.UpdateVersion;
import com.bjxiyang.zhinengshequ.myapplication.model.Users;
import com.bjxiyang.zhinengshequ.myapplication.model.Users1;
import com.bjxiyang.zhinengshequ.myapplication.model.WuYeJiaoFei;
import com.bjxiyang.zhinengshequ.myapplication.model.ProPayOrder;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.update.util.GetHeaders;

import java.util.Map;

/**
 * Created by gll on 17-3-9.
 * 存放应用中所有的请求
 */
public class RequestCenter {
    public static void postRequest1(String url, RequestParams params,RequestParams headers,
                                    DisposeDataListener disposeDataListener, Class<?> clazz){
        CommonOkHttpClient.post(CommonRequest.createPostRequest1(url,params,headers)
                ,new DisposeDataHandle(disposeDataListener,clazz));
    }
    public static void postRequest(String url, RequestParams params,
                                    DisposeDataListener disposeDataListener, Class<?> clazz){
        CommonOkHttpClient.post(CommonRequest.createPostRequest1(url,params,GetHeaders.getHeaders())
                ,new DisposeDataHandle(disposeDataListener,clazz));
    }






    public static void uploadPictures(String url, Map<String, Object> map,DisposeDataListener listener){
       CommonOkHttpClient.uploadImgAndParameter(map,url,new DisposeDataHandle(listener,FanHui.class));
    }

    public static void uploadPictures2(String url,RequestParams params,DisposeDataListener listener){
        CommonOkHttpClient.uploadPictures2(
                CommonRequest.createMultiPostRequest(url,params),new DisposeDataHandle(listener,FanHui.class));
    }
    public static void cancel(){
        CommonOkHttpClient.breakLink();
    }
    /**
     * 应用版本号请求
     *
     * @param listener
     */
    public static void checkVersion(DisposeDataListener listener) {
        RequestCenter.postRequest(XY_Response.URL_UPDATEVERSION +"cmemberId="+
                        UserManager.getInstance().getUser().getObj().getC_memberId(),
                null, listener, UpdateVersion.class);
    }

    public static void downloadFile(String url, String path, DisposeDownloadListener listener) {
        CommonOkHttpClient.downloadFile(CommonRequest.createGetRequest(url, null),
                new DisposeDataHandle(listener, path));
    }
    //获取JSON并转化为实体类的请求。
    public static void requestRecommandData(DisposeDataListener listener){
        //第一个参数为请求的地址
        //第二个参数为上传的参数。请求的时候为null
        //第三个参数为监听事件
        //第四个参数为JSON的实体类
        RequestParams params=new RequestParams("TEXT","我是测试数据");
        RequestCenter.postRequest(HttpConstants.TEXT,
                params,listener, null);
    }
    public static void login(String url,RequestParams headers,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,headers,listener,Users.class);
    }
    public static void register(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,null,listener, FanHui.class);
    }
    public static void findCommunity(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url, GetHeaders.getHeaders(),listener, Plots.class);
    }
    public static void findFloor(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, Floor.class);
    }
    public static void findUnit(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, Unit.class);
    }
    public static void findDoor(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, Door.class);
    }
    public static void addCommunity(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, FanHui.class);
    }
    public static void findCommunityByPer(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, SelectPlot.class);
    }
    public static void addPermission(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, FanHui.class);
    }
    public static void findPermissions(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, PermissionList.class);
    }
    public static void updatePermission(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, FanHui.class);
    }
    public static void deletePermission(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, FanHui.class);
    }
    public static void updateUserInfo(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, FanHui.class);
    }

    public static void bannerList(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, Banner.class);
    }
    public static void userSuggest(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, FanHui.class);
    }
    public static void getUserInfo(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, Users1.class);
    }
    public static void getLockByCom(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, ByCom.class);
    }

    public static void openDoorList(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, OpenDoorList.class);
    }
    public static void openDoor(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, OpenDoor.class);
    }

    public static void getNoticeList(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, GongGao.class);
    }
    public static void getPropertyList(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, WuYeJiaoFei.class);
    }
    public static void weixiapi(String url,RequestParams params,DisposeDataListener listener){
        RequestCenter.postRequest1(url,params,null,listener, FanHui.class);
    }

    public static void proPayOrder(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, ProPayOrder.class);
    }
    public static void proPayOrderByAli(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, ProPayOrderByAli.class);
    }



    public static void addPhoneList(String url,RequestParams params,DisposeDataListener listener){
        RequestCenter.postRequest1(url,params,null,listener, FanHui.class);
    }
    public static void confirmPayOrder(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, FanHui.class);
    }


    public static void selectAdvanceInfo(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, Loan.class);
    }
    public static void selectSecondHand(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, Loan.class);
    }
    /***
     * 便利店的请求
     */
    public static void order_seller_list(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, DianMing.class);
    }
    public static void order_product_list(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, ShangPinList.class);
    }
    public static void order_product_detail(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, ShangPingDetail.class);
    }
    public static void order_user_address_list(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,null,listener, DiZhiList.class);
    }
    public static void order_user_address_add(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, DiZhiAdd.class);
    }
    public static void order_user_address_update(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, DiZhiUpdate.class);
    }
    public static void order_user_address_delete(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, DiZhiDelete.class);
    }
    public static void order_coupon_list(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, YouHuiQuan.class);
    }
    public static void order_submit(String url,RequestParams params,DisposeDataListener listener){
        RequestCenter.postRequest1(url,params,GetHeaders.getHeaders(),listener, TiJiaoDingDan.class);
    }
    public static void order_receive(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, OrderDelete.class);
    }
    public static void order_detail(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, DianMing.class);
    }
    public static void order_list(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, DingDan.class);
    }
    public static void order_cancel(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, DianMing.class);
    }
    public static void order_delete(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, OrderDelete.class);
    }

    public static void order_reback_apply(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, OrderDelete.class);
    }


    public static void order_proPayOrderByAli(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, AliZhiFu.class);
    }
    public static void order_proPayOrderByWx(String url,DisposeDataListener listener){
        RequestCenter.postRequest(url,GetHeaders.getHeaders(),listener, OrderWeiXin.class);
    }


    public static void startadpage(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,null,listener, QiDongYe.class);
    }

    public static void centerinfo_ebppBillAdd(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,null,listener, FanHui.class);
    }

    public static void centerinfo_ebppBillGet(String url,DisposeDataListener listener){
        RequestCenter.postRequest1(url,null,null,listener, FanHui.class);
    }


}
