package com.bjxiyang.zhinengshequ.myapplication.weixin;

import android.os.Bundle;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.MySwipeBackActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class WeiXin extends MySwipeBackActivity{
    IWXAPI msgApi = WXAPIFactory.createWXAPI(this, "wx75014952d4daeaf2");
//    public IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        // 将该app注册到微信
        msgApi.registerApp("wx75014952d4daeaf2");
        PayReq request = new PayReq();
        request.appId = "wxe0f54cc08a51597f";
        request.partnerId = "23955501";
        request.prepayId= "wx20170621120457c0fff5a4a40434168567";
        request.packageValue = "Sign=WXPay";
        request.nonceStr= "O2GcV2KT6BgI1pRp";
        request.timeStamp= "1498017898";
        request.sign= "0CB6E6F1D728ADC2BEB5FD4DB96452F3";

        msgApi.sendReq(request);
    }


}
