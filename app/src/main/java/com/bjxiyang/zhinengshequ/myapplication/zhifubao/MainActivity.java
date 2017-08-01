package com.bjxiyang.zhinengshequ.myapplication.zhifubao;

/**
 * Created by Administrator on 2017/6/30 0030.
 */
import java.util.Map;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.MySwipeBackActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//这里的mainactivity就是官方的PayDemoActivity ；
//里面的方法都在这里，就是类名不一样而已，大可不必纠结
public class MainActivity extends MySwipeBackActivity implements OnClickListener {

    private TextView tv;
    private Button buy;

    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "2016083000128338";

    /** 支付宝账户登录授权业务：入参pid值 */
    public static final String PID = "2088502954592834";
    /** 支付宝账户登录授权业务：入参target_id值 商户收款账号*///此处应该不起任何作用，下文无调用
    public static final String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
    //公钥已上传至支付宝平台，私钥已保存本地，这是本应用私钥
    public static final String RSA2_PRIVATE = "MIIEvasefDA.....9w0BAQEFAASCBKYwgg....RUJCt7xt+EaJRCX/mGktAi5BQ4iwKTQea8PPDK9m+DzyfVECgYAtKMhyH...86CocnJrNqbtyoq......0pDStHa98LrihY+XeiyXUME6.....HBjey/..kBjmUb30PYdHU1OfTL3qHvy9MAE1U6GyOJgahZCPYHeqg==";
    //rsa没用
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;//sdk正常运行了吗？
    private static final int SDK_AUTH_FLAG = 2;//sdk确认了吗？

    private Activity activity;//这里声明一个活动是干什么的？
    private String orderNo;//订单详情



    @SuppressLint("HandlerLeak")
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
                        Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(MainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {//如果消息是SDK已经确认
                    @SuppressWarnings("unchecked")
                    //将随该消息附带的msg.obj强转回MAP，第二个参数为“去除消息中包含的括号吗？（boolean）”
                            AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);//新建自定义的authResult对象
                    //利用Authresult中的自定义方法的到resultstatus
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(MainActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(MainActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

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
        setContentView(R.layout.activity_main);//当前活动界面赋值
        initView();//初始化控件
    }

    private void initView() {
        // TODO Auto-generated method stub

    }

    /**
     * 支付宝pay方法
     * 支付宝支付业务
     *
     * @param v
     */
    public void payV2(View v) {
        /**
         * 如果APPID是空值或私钥两个全是空，则弹出警告对话框告诉开发者“需要配置APPID|RSA_PRIVATE”
         */
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；防止本地orderInfo被认为更改，例如买个冰箱改成买个鸡蛋
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);//rsa2私钥已经被赋值
        //在这里，传入APPID和私钥，得到请求map，即包含支付订单信息的map
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        //将map解析成一个String类型的支付订单
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        //是rsa2类型的私钥吗？如果rsa2私钥已经被赋值，那么privateKey就被设置成rsa2，否则就被设置成rsa。这就是支付宝说的“如果你有俩私钥，优先使用RSa2私钥，靠！”
        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        //对，privateKey就是商户私钥！通过此方法对商户的私钥进行“签名”处理，处理后就会生成（返回）一个sign=GKHJL%……&*的一大串字串
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        //最终，结合orderparam参数与sign签名字串，搞成orderInfo字串；
        final String orderInfo = orderParam + "&" + sign;
        //新开一个线程，将orderInfo字串传入到PayTask任务中去
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                //新建一个PAyTask对象
                PayTask alipay = new PayTask(MainActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 支付宝账户授权业务
     *
     * @param v
     */
    public void authV2(View v) {
        if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
                || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))
                || TextUtils.isEmpty(TARGET_ID)) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER |APP_ID| RSA_PRIVATE| TARGET_ID")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * authInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
        //利用这个方法，我可以将map解析成一个String类型的支付订单，即得到info
        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        //对支付参数信息进行签名 传入进来一个Map,一个rsaKey，
        //并且询问是否是rsa2格式的私钥 这个rsaKey是商户的私钥 就是说通过此方法对商户的私钥进行“签名”处理，
        //处理后就会生成（返回）一个sign=GKHJL%……&*的一大串字串
        String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
        final String authInfo = info + "&" + sign;
        //得到orderInfo后，我们开启新线程，发送相关信息
        Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask(MainActivity.this);
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(authInfo, true);

                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

    /**
     * get the sdk version. 获取SDK版本号
     *
     */
    public void getSDKVersion() {
        PayTask payTask = new PayTask(this);
        String version = payTask.getVersion();
        Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
    }

    /**
     * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
     * 意思就是我应用里面有一个h5的页面，页面里面有好多
     * 商品售卖，当我点击h5中的末一个商品支付时，支付表就会在此时调出来
     * 如果app中没有webView实现h5，那完全用不到此方法
     *
     * @param v
     */
    public void h5Pay(View v) {
        Intent intent = new Intent(this, H5PayDemoActivity.class);
        Bundle extras = new Bundle();
        /**
         * url是测试的网站，在app内部打开页面是基于webview打开的，demo中的webview是H5PayDemoActivity，
         * demo中拦截url进行支付的逻辑是在H5PayDemoActivity中shouldOverrideUrlLoading方法实现，
         * 商户可以根据自己的需求来实现
         */
        String url = "http://m.taobao.com";
        // url可以是一号店或者淘宝等第三方的购物wap站点，在该网站的支付过程中，支付宝sdk完成拦截支付
        extras.putString("url", url);
        intent.putExtras(extras);
        startActivity(intent);
    }



    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }



}