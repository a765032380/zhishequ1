package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.AliZhiFu;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.GouWuChe;
import com.bjxiyang.zhinengshequ.myapplication.bianlidianstatus.BianLiDianStatus;
import com.bjxiyang.zhinengshequ.myapplication.greendao.DaoUtils;
import com.bjxiyang.zhinengshequ.myapplication.model.OrderWeiXin;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.BianLiDianResponse;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.app.GuardApplication;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.ProPayOrder;
import com.bjxiyang.zhinengshequ.myapplication.model.ProPayOrderByAli;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.MyDialog;
import com.bjxiyang.zhinengshequ.myapplication.zhifubao.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/15 0015.
 */


//【程序猿的三重境界】第一重:无尽bug常作客,困闷调试伴不眠。第二重:千行代码过,bug不沾身。第三重:编码间,bug灰飞烟灭。
public class ZhiFuXiangQing extends MySwipeBackActivity implements View.OnClickListener{
    private RelativeLayout rl_chaoshifukuan_xiangqing_fanhui;
    private TextView tv_item_chaoshifukuan_xiangqing_dianming;
    private EditText et_item_chaoshifukuan_xiangqing_money;
    private LinearLayout zhifu_yinhangka;
    private LinearLayout zhifu_weixin;
    private LinearLayout zhifu_zhifubao;
    private TextView zhifu;
    private String jiage;
    private ImageView iv_item_chaoshifukuan_xiangqing_yinhanka;
    private ImageView iv_item_chaoshifukuan_xiangqing_weixin;
    private ImageView iv_item_chaoshifukuan_xiangqing_zhifubao;
    private TextView tv_gudingjine;
    DecimalFormat df=new DecimalFormat("0.##");
    private String propertyName;
    //支付
    private int select=0;
    public static String payOrder="";
    private int propertyId;
    private double fee;
//    private String fee;
    private int orderId;
    private int type;
    private int spId;
    private List<GouWuChe> mList=DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
    private static final int SDK_PAY_FLAG=1000;
    public static ZhiFuXiangQing zhiFuXiangQing;

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {//如果消息是 SDK正常运行
                    @SuppressWarnings("unchecked")
                    //将随该消息附带的msg.obj强转回map中，建立新的payresult支付结果
                            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息。从支付结果中取到resultinfo
                    String resultStatus = payResult.getResultStatus();//得到resultstatus
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(ZhiFuXiangQing.this, "支付成功", Toast.LENGTH_SHORT).show();
                        if (PlaceOrderActivity.placeorder!=null) {
                            PlaceOrderActivity.placeorder.finish();
                        }
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(ZhiFuXiangQing.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fukuan_xiangqing);
        zhiFuXiangQing=this;
        Intent intent=getIntent();
        fee=intent.getDoubleExtra("fee",0);
//        fee= Double.parseDouble(intent.getStringExtra("fee"));
//        fee=fee*100;

        orderId=intent.getIntExtra("orderId",0);
        propertyName=intent.getStringExtra("propertyName");
        propertyId=intent.getIntExtra("propertyId",0);
        type=intent.getIntExtra("type",0);
        spId=intent.getIntExtra("spId",-1);
        initUI();
    }

    private void initUI() {
        tv_gudingjine= (TextView) findViewById(R.id.tv_gudingjine);
        iv_item_chaoshifukuan_xiangqing_yinhanka= (ImageView) findViewById(R.id.iv_item_chaoshifukuan_xiangqing_yinhanka);
        iv_item_chaoshifukuan_xiangqing_weixin= (ImageView) findViewById(R.id.iv_item_chaoshifukuan_xiangqing_weixin);
        iv_item_chaoshifukuan_xiangqing_zhifubao= (ImageView) findViewById(R.id.iv_item_chaoshifukuan_xiangqing_zhifubao);
        rl_chaoshifukuan_xiangqing_fanhui= (RelativeLayout) findViewById(R.id.rl_chaoshifukuan_xiangqing_fanhui);
        rl_chaoshifukuan_xiangqing_fanhui.setOnClickListener(this);
        tv_item_chaoshifukuan_xiangqing_dianming= (TextView) findViewById(R.id.tv_item_chaoshifukuan_xiangqing_dianming);
        zhifu_yinhangka= (LinearLayout) findViewById(R.id.zhifu_yinhangka);
        zhifu_yinhangka.setOnClickListener(this);
        zhifu_weixin= (LinearLayout) findViewById(R.id.zhifu_weixin);
        zhifu_weixin.setOnClickListener(this);
        zhifu_zhifubao= (LinearLayout) findViewById(R.id.zhifu_zhifubao);
        zhifu_zhifubao.setOnClickListener(this);
        zhifu= (TextView) findViewById(R.id.tv_item_chaoshifukuan_xiangqing_quedingzhifu);
        zhifu.setOnClickListener(this);
        Log.i("YYYY",df.format(fee/100));
        tv_gudingjine.setText(String.valueOf(df.format(fee/100)));
        tv_item_chaoshifukuan_xiangqing_dianming.setText(propertyName);
        et_item_chaoshifukuan_xiangqing_money= (EditText) findViewById(R.id.et_item_chaoshifukuan_xiangqing_money);
        panduan(String.valueOf(tv_gudingjine.getText()));

        et_item_chaoshifukuan_xiangqing_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                panduan(String.valueOf(s));

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                panduan(String.valueOf(s));
            }
            @Override
            public void afterTextChanged(Editable s) {
                panduan(String.valueOf(s));
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回的按键
            case R.id.rl_chaoshifukuan_xiangqing_fanhui:
                    finish();
                break;
            //选择银行卡支付方式
            case R.id.zhifu_yinhangka:
                select=0;
                iv_item_chaoshifukuan_xiangqing_yinhanka.setBackgroundResource(R.drawable.k_btn_zhong_pre);
                iv_item_chaoshifukuan_xiangqing_weixin.setBackgroundResource(R.drawable.e_btn_xuanze);
                iv_item_chaoshifukuan_xiangqing_zhifubao.setBackgroundResource(R.drawable.e_btn_xuanze);
                break;
            //选择微信支付方式
            case R.id.zhifu_weixin:
                select=1;
                iv_item_chaoshifukuan_xiangqing_yinhanka.setBackgroundResource(R.drawable.e_btn_xuanze);
                iv_item_chaoshifukuan_xiangqing_weixin.setBackgroundResource(R.drawable.k_btn_zhong_pre);
                iv_item_chaoshifukuan_xiangqing_zhifubao.setBackgroundResource(R.drawable.e_btn_xuanze);
                break;
            //选择支付宝支付方式
            case R.id.zhifu_zhifubao:
                select=2;
                iv_item_chaoshifukuan_xiangqing_zhifubao.setBackgroundResource(R.drawable.k_btn_zhong_pre);
                iv_item_chaoshifukuan_xiangqing_weixin.setBackgroundResource(R.drawable.e_btn_xuanze);
                iv_item_chaoshifukuan_xiangqing_yinhanka.setBackgroundResource(R.drawable.e_btn_xuanze);
                break;
            //提交按钮
            case R.id.tv_item_chaoshifukuan_xiangqing_quedingzhifu:
                switch (select){
                    case 0:
                        break;
                    case 1:
                        sendRequestWeiXin();
                        break;
                    case 2:
                        sendRequestAli();
                        break;
                }
                break;

        }
    }
    private void panduan(String s){
        if (s.length()>0&&!s.equals("0")){
            zhifu.setEnabled(true);
            zhifu.setBackgroundResource(R.drawable.i_btn_quedingzhifu);
        }else {
            zhifu.setEnabled(false);
            zhifu.setBackgroundResource(R.drawable.h_btn_quedingzhifu);
        }
    }
    private void sendRequestWeiXin(){
        DialogUntil.showLoadingDialog(ZhiFuXiangQing.this,"正在提交",true);

        if (type==1){
            String url_wx= BianLiDianResponse.URL_ORDER_PROPAYORDERBWX+"orderId="+orderId;
            RequestCenter.order_proPayOrderByWx(url_wx, new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    DialogUntil.closeLoadingDialog();
                    OrderWeiXin orderWeiXin = (OrderWeiXin) responseObj;
                    if (orderWeiXin.getCode()==BianLiDianStatus.STATUS_CODE_SUCCESS) {
                        MyUntil.show(ZhiFuXiangQing.this, "已提交订单");
                        sendReqToWeiXi_order(orderWeiXin);
                    }
                }
                @Override
                public void onFailure(Object reasonObj) {
                    DialogUntil.closeLoadingDialog();

                }
            });

        }else {

            MyUntil.show(ZhiFuXiangQing.this, UserManager.getInstance().getUser().getObj().getC_memberId()
                    + "--" + propertyId + "--" + fee);
            String url = XY_Response.URL_PROPAYORDER + "cmemberId=" +
                    UserManager.getInstance().getUser().getObj().getC_memberId() +
                    "&propertyId=" + propertyId +
                    "&fee=" + fee;
            RequestCenter.proPayOrder(url, new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    DialogUntil.closeLoadingDialog();
                    ProPayOrder proPayOrder = (ProPayOrder) responseObj;
                    if (proPayOrder.getCode().equals("1000")) {
                        MyUntil.show(ZhiFuXiangQing.this, "已提交订单");
                        sendReqToWeiXi(proPayOrder);
                    }
                }

                @Override
                public void onFailure(Object reasonObj) {
                    DialogUntil.closeLoadingDialog();
                    MyDialog.showDialog(ZhiFuXiangQing.this);

                }
            });
        }
    }
    private void sendReqToWeiXi(ProPayOrder proPayOrder) {
        ProPayOrder.Obj obj=proPayOrder.getObj();
        IWXAPI msgApi = WXAPIFactory.createWXAPI(GuardApplication.getContent(),null);
        msgApi.registerApp(obj.getApp_id());
        // 将该app注册到微信
        PayReq request = new PayReq();
//        propertyId=obj.getPrepay_id();
//        payOrder=obj.getOrder_no();
        request.appId = obj.getApp_id();
        request.partnerId =obj.getPartnerid();
        request.prepayId= obj.getPrepay_id();
        request.packageValue = obj.getReturn_package();
        request.nonceStr= obj.getNonce_str();
        request.timeStamp= String.valueOf(obj.getTime_stamp());
        request.sign= obj.getPay_info_sign();
        msgApi.registerApp(obj.getApp_id());
        msgApi.sendReq(request);

//        }


    }

    private void sendReqToWeiXi_order(OrderWeiXin orderWeiXin) {

        OrderWeiXin.ResultBean obj=orderWeiXin.getResult();
        IWXAPI msgApi = WXAPIFactory.createWXAPI(GuardApplication.getContent(),null);
        msgApi.registerApp(obj.getAppid());
        // 将该app注册到微信
        PayReq request = new PayReq();
//        propertyId=obj.getPrepay_id();
//        payOrder=obj.getOrder_no();
        request.appId = obj.getAppid();
        request.partnerId =obj.getPartnerid();
        request.prepayId= obj.getPrepay_id();
        request.packageValue = obj.getReturn_package();
        request.nonceStr= obj.getNonce_str();
        request.timeStamp= String.valueOf(obj.getTime_stamp());
        request.sign= obj.getPay_info_sign();
        msgApi.registerApp(obj.getAppid());
        msgApi.sendReq(request);

//        }

        if (spId!=-1) {
            if (type == 0) {
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).getSpid() == spId) {
                        DaoUtils.getStudentInstance().deleteObject(mList.get(i));
                    }
                }
            } else {
                DaoUtils.getStudentInstance().deleteAll(GouWuChe.class);
            }
        }


    }

    private void sendRequestAli(){
        String url;
        if (type==1){
            url= BianLiDianResponse.URL_ORDER_PROPAYORDERBYALI+"orderId="+orderId;
            RequestCenter.order_proPayOrderByAli(url, new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    AliZhiFu aliZhiFu= (AliZhiFu) responseObj;
                    if (aliZhiFu.getCode() == BianLiDianStatus.STATUS_CODE_SUCCESS){
                        sendReqToAli(aliZhiFu.getResult());

                    }
                }
                @Override
                public void onFailure(Object reasonObj) {

                }
            });

        }else {
            url= XY_Response.URL_PROPAYORDERBYALI+"cmemberId="+
                    UserManager.getInstance().getUser().getObj().getC_memberId()+
                    "&propertyId="+propertyId+
                    "&fee="+fee;
            DialogUntil.showLoadingDialog(ZhiFuXiangQing.this,"正在提交",true);

            MyUntil.show(ZhiFuXiangQing.this,UserManager.getInstance().getUser().getObj().getC_memberId()
                    +"--"+propertyId+"--"+fee);
            RequestCenter.proPayOrderByAli(url, new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    DialogUntil.closeLoadingDialog();
                    ProPayOrderByAli proPayOrder= (ProPayOrderByAli) responseObj;
                    if (proPayOrder.getCode().equals("1000")){
//                    MyUntil.show(ZhiFuXiangQing.this,"已提交订单");
                        sendReqToAli(proPayOrder.getObj());
                    }
                }
                @Override
                public void onFailure(Object reasonObj) {
                    DialogUntil.closeLoadingDialog();
                    MyDialog.showDialog(ZhiFuXiangQing.this);

                }
            });
        }

    }
    private void sendReqToAli(final String orderInfo){
        if (spId!=-1) {
            if (type == 0) {
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).getSpid() == spId) {
                        DaoUtils.getStudentInstance().deleteObject(mList.get(i));
                    }
                }
            } else {
                DaoUtils.getStudentInstance().deleteAll(GouWuChe.class);
            }
        }

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(ZhiFuXiangQing.this);
                Map<String, String> result = alipay.payV2(orderInfo,true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}
