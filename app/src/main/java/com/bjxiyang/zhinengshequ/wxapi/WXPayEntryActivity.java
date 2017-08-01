package com.bjxiyang.zhinengshequ.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.bjxiyang.zhinengshequ.myapplication.ui.activity.BeasActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.PlaceOrderActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.WuYeJiaoFeiActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.ZhiFuXiangQing;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.WuYeJiaoFeiAdapter;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class WXPayEntryActivity extends BeasActivity implements IWXAPIEventHandler {
    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pay_result);

        //这个是必须写的
        api = WXAPIFactory.createWXAPI(this, WuYeJiaoFeiAdapter.propertyId);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    /**
     * 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
     * arg0。errCode  0成功 -1支付失败 -2取消
     */
    @Override
    public void onResp(BaseResp resp) {
//
//        DialogUntil.showLoadingDialog(WXPayEntryActivity.this,"正在加载",true);
//        String url= XY_Response.URL_CONFIRMPAYORDER+"cmemberId="+
//                UserManager.getInstance().getUser().getObj().getC_memberId()+"&propertyId="+
//                WuYeJiaoFeiAdapter.propertyId+"&payOrder="+WuYeJiaoFeiAdapter.payOrder;
//
//        RequestCenter.confirmPayOrder(url, new DisposeDataListener() {
//            @Override
//            public void onSuccess(Object responseObj) {
//                DialogUntil.closeLoadingDialog();
//                DingDanQueDing dingDanQueDing= (DingDanQueDing) responseObj;
//                if (dingDanQueDing.getCode().equals("1000")){
//
//                }else {
//                    Toast.makeText(WXPayEntryActivity.this,dingDanQueDing.getObj().get(0).getORDERNOTEXIST(),Toast.LENGTH_LONG);
//                }
//            }
//
//            @Override
//            public void onFailure(Object reasonObj) {
//                DialogUntil.closeLoadingDialog();
//                MyDialog.showDialog(WXPayEntryActivity.this);
//
//            }
//        });



        if (resp.errCode == 0) {//支付成功
//            Intent intent = new Intent(this, WuYeJiaoFeiActivity.class);
//            startActivity(intent);
//            finish();

//            intent.setAction("fbPayAction");
//          intent.setAction("goodsPayAction");
//          intent.setAction("integraPayAction");
//            sendBroadcast(intent);
            Toast.makeText(getApplicationContext(), "支付成功", Toast.LENGTH_LONG).show();
            if (PlaceOrderActivity.placeorder!=null) {
                PlaceOrderActivity.placeorder.finish();
            }
            if (ZhiFuXiangQing.zhiFuXiangQing!=null) {
                ZhiFuXiangQing.zhiFuXiangQing.finish();
            }
            finish();
        } else if (resp.errCode == -1) {//支付失败
            Toast.makeText(getApplicationContext(), "支付失败", Toast.LENGTH_LONG).show();
            finish();
        } else {//取消
            Toast.makeText(getApplicationContext(), "支付取消", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
