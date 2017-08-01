package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.myapplication.adutil.APP_ID;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.baisi.myapplication.okhttp.request.RequestParams;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.model.UpdateVersion;
import com.bjxiyang.zhinengshequ.myapplication.model.Users;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.CommonDialog;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.update.service.UpdateService;
import com.bjxiyang.zhinengshequ.myapplication.update.util.Util;
import com.bjxiyang.zhinengshequ.myapplication.view.MyDialog;
import com.bjxiyang.zhinengshequ.myapplication.welcome.Welcome;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mehdi.sakout.dynamicbox.DynamicBox;

public class SDLoginActivity extends BeasActivity {
    private EditText et_zhuce_password_again;
    private Button bt_get_smscode;
    LinearLayout loginMenutView;
    LinearLayout regiestMenutView;
    LinearLayout loginView;
    LinearLayout regiestView;

    ImageView point_login;
    ImageView point_regiest;

    // 登录模块的
    EditText input_user_name_login_editview;
    EditText input_user_password_login_editview;
    Button login_action_button;

    //注册模块的
    EditText input_user_name_regiest_editview;
    EditText input_user_sms_code_editview;
    EditText input_user_password_regiest_editeView;
    Button regiest_action_button;

    TextView tv_pro_content;
    TextView forget_password_button;

    ImageButton check_out;

    String sms_id;

    DynamicBox box;

    private int timeCount = 60;
    private Timer mtimer;
    //Activity最外层的Layout视图
    private RelativeLayout activityRootView;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;
    boolean islogin  =true;
    int usedsafeheight;
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what==8000){
                loginView.setVisibility(View.VISIBLE);
                regiestView.setVisibility(View.GONE);
                MyUntil.show(SDLoginActivity.this,"注册成功");
                showPointForLogin();
                qingkong();
                DialogUntil.closeLoadingDialog();

            }
            if (msg.what==7000){
                startCountdown();
            }

            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sdlogin);
        getQuanxian();

        //判断有没有用户的信息,如果有,直接跳转到主界面
        if (SPManager.getInstance().getString("mobilePhone",null)!=null){
            //将得到的用户信息保存到实体类中
            UserManager.getInstance().setUser(setUserforSP());
            Intent intent = new Intent(SDLoginActivity.this, MainActivity.class);
            startActivity(intent);
            //关闭当前页面

            finish();
           
            //跳转到主页面

        }
        //初始化控件
        initView();
        //获取屏幕高度
//        setHeight();

    }
    private void setHeight() {
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
//        阀值设置为屏幕高度的1/3
        keyHeight = screenHeight/3;

        activityRootView = (RelativeLayout) findViewById(R.id.screentViewlayout);

        activityRootView.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                Rect rect = new Rect();
                activityRootView.getWindowVisibleDisplayFrame(rect);
                int mainInvisibleHeight = activityRootView.getRootView().getHeight() - (rect.bottom - rect.top);
                Log.d("sander",mainInvisibleHeight + " ----> ");
                if (mainInvisibleHeight > 100) {
                    int[] location = new int[2];
                    if (islogin){
                        input_user_password_login_editview.getLocationInWindow(location);
                        int srollHeight = (location[1] + input_user_password_login_editview.getHeight()) - rect.bottom;
                        if (srollHeight > 0 ){
                            activityRootView.scrollTo(0, srollHeight);
                        }else{
                            activityRootView.scrollTo(0,0);
                            Log.d("sander","这个执行吗");
                        }
                    }else{
                        input_user_password_regiest_editeView.getLocationInWindow(location);
                        int srollHeight = (location[1] + input_user_password_regiest_editeView.getHeight()) - rect.bottom;
                        if (srollHeight > 0){
                            activityRootView.scrollTo(0, srollHeight);
                        }else {
                            activityRootView.scrollTo(0,0);
                        }
                    }
                    usedsafeheight = mainInvisibleHeight;
                } else {
                    usedsafeheight = 0;
                    activityRootView.scrollTo(0, 0);
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //添加layout大小发生改变监听器
//        activityRootView.addOnLayoutChangeListener(this);
    }

    private void initView()
    {

        tv_pro_content= (TextView) findViewById(R.id.tv_pro_content);
        tv_pro_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SDLoginActivity.this,FuWuTiaoKuanActivity.class);
                startActivity(intent);
            }
        });
        point_login = (ImageView)findViewById(R.id.selected_menu_login);
        point_regiest = (ImageView) findViewById(R.id.selected_menu_regiest);

        input_user_name_login_editview = (EditText) findViewById(R.id.et_login_username);
        input_user_password_login_editview = (EditText) findViewById(R.id.et_login_pwd);
        //记住密码的选择框
        check_out = (ImageButton) findViewById(R.id.checkbox_button);
        //设置记住密码为选中状态
        check_out.setSelected(true);
        check_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置状态为相反的状态
                boolean isCheckout = check_out.isSelected();
                check_out.setSelected(!isCheckout);
            }
        });
        //发送验证码按钮
        bt_get_smscode= (Button) findViewById(R.id.bt_get_smscode);
        //点击发送验证码按钮后的逻辑处理
        bt_get_smscode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置发送验证码后的倒计时的时间
                timeCount=60;
                //得到用户的手机号
                String uPhone=String.valueOf(input_user_name_regiest_editview.getText());
//                String url=XY_Response.URL+"";
                //判断用户输入的手机号是不是正确的手机号
                if (!isMobilephone(uPhone)) {
                    Toast.makeText(SDLoginActivity.this,"请输入正确的手机号",Toast.LENGTH_LONG).show();
                    return;
                }
                //弹出等待的Dialog
                DialogUntil.showLoadingDialog(SDLoginActivity.this,"请稍等",true);
                //设置类型为注册类型,后台需要的
                String type="0";
                //拼接访问的URL
                String test=XY_Response.URL_SED_MSM+"mobilePhone="+uPhone+"&type="+type;
                //请求后台数据
                RequestCenter.register(test, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        //请求成功返回数据并转化为实体类
                        FanHui fanHui= (FanHui) responseObj;
                        //清除Dialog
                        DialogUntil.closeLoadingDialog();
                        //判断后台的返回码,如果是1000的话说明发送验证码成功
                        if (fanHui.getCode().equals("1000")){
                            //发送验证码成功以后进行操作,因为是在线程中所以发送给Handler
                            Message message=new Message();
                            message.what=7000;
                            handler.sendMessage(message);
                            //如果返回的是500的话打印返回的错误信息
                        }else if (fanHui.getCode().equals("500")){
                            Toast.makeText(SDLoginActivity.this,
                                    fanHui.getMsg(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        //网络请求失败也要清掉Dialog
                        DialogUntil.closeLoadingDialog();
                        //弹出请用户检查网络的Dialog,可以选择取消或者调到网络修改的页面
                        MyDialog.showDialog(SDLoginActivity.this,"请检查网络连接");
                    }
                });
            }
        });



        //登录按钮实例化
        login_action_button = (Button) findViewById(R.id.btn_login);
        //点击登录按钮后的逻辑处理
        login_action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录时输入的手机号
                 String uPhone = String.valueOf(input_user_name_login_editview.getText());
                //登录时输入的密码
                 String uPassword = String.valueOf(input_user_password_login_editview.getText());
//                if (isMobilephone(uPhone)){
//                        Toast.makeText(SDLoginActivity.this,"输入格式没问题",Toast.LENGTH_LONG).show();
//                        String url= XY_Response.URL_LOGIN+"mobilePhone="+uPhone+"&password="+uPassword;
                //判断用户输入的手机号是否正确
                if (!isMobilephone(uPhone)) {
                    Toast.makeText(SDLoginActivity.this,"请输入正确的手机号",Toast.LENGTH_LONG).show();
                    return;
                }
                //判断用户输入的密码格式是否正确
                if (!rexCheckPassword(uPassword)){
                    Toast.makeText(SDLoginActivity.this,"请输入8~16位密码,密码必须包含字母和数字",Toast.LENGTH_LONG).show();
                    return;
                }
                //弹出正在登陆的等待动画
                DialogUntil.showLoadingDialog(SDLoginActivity.this,"正在登陆",true);
                //拼接访问的URL
                String url=XY_Response.URL_LOGIN+"mobilePhone="+uPhone+"&password="+getMD5(uPassword);

                RequestParams headers=new RequestParams();
                headers.put("user-agent", ""+"appId="+ APP_ID.APP_ID+"");
                RequestCenter.login(url,headers, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        //访问成功清除Dialog
                        DialogUntil.closeLoadingDialog();
                        //得到返回数据并保存到实体类中
                        Users users= (Users) responseObj;
                        //如果返回码为1000,说明登陆成功
                        if (users.getCode().equals("1000")){
                            //登陆成功以后将用户的实体类保存到单例模式下的用户实体类中
                            UserManager.getInstance().setUser(users);
//                            if (check_out.isSelected()){
                                //将用户的信息报错到SP中
                                setSP(users);
//                            }
                            MyUntil.show(SDLoginActivity.this,"登陆成功");
                            //登陆成功后跳转到主页面
                            Intent intent=new Intent(SDLoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                            //如果返回码是200,说明登陆有误
                        }else {
                            //弹出用户登陆的错误信息
                            Toast.makeText(SDLoginActivity.this,
                                    users.getMsg(), Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Object reasonObj) {
                        //网络访问失败也要清除Dialog
                        DialogUntil.closeLoadingDialog();
                        //弹出请用户检查网络的Dialog
                        MyDialog.showDialog(SDLoginActivity.this,"请检查网络连接");
                    }
                });
//                    }else {
//                        Toast.makeText(SDLoginActivity.this,"输入格式有问题",Toast.LENGTH_LONG).show();
//                    }
//                if (uPhone!=null&&uPassword!=null&&!uPhone.equals("")&&!uPassword.equals("")){
                    //向服务器发送请求获取返回值,请求的时候带用户名和密码进行验证
                    //在向服务器发送请求和接收服务器返回值的过程中,可加一个小动画
                    //这里还要加一个判断




//
//                    //判断用户有没有选择记住密码
//                    if (check_out.isSelected()){
////                        Toast.makeText(SDLoginActivity.this,String.valueOf(check_out),
////                                Toast.LENGTH_SHORT).show();
//                        SPManager.getInstance().putString("phone",uPhone);
//                        SPManager.getInstance().putString("password",uPassword);
//                    }
                    //以单例模式保存用户的信息,可以在任何页面直接得到用户信息
                    //目前只保存了用户名,正常来说,应该保存从服务器得到的所有信息

//                }else {
                    //向用户显示提示
//                    Toast.makeText(SDLoginActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
//                }

            }
        });
        //手机号输入框
        input_user_name_regiest_editview = (EditText)findViewById(R.id.inputusernameedittext) ;
        //验证码输入框
        input_user_sms_code_editview = (EditText) findViewById(R.id.et_reg_smscode) ;
        //首次输入密码
        input_user_password_regiest_editeView = (EditText)findViewById(R.id.et_reg_password);
        //第二次密码输入框
        et_zhuce_password_again= (EditText) findViewById(R.id.et_zhuce_password_again);
        //注册按钮
        regiest_action_button = (Button) findViewById(R.id.reg_confirm) ;
        //点击注册按钮后的操作
        regiest_action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //修改密码的提交按钮
                final String phone= String.valueOf(input_user_name_regiest_editview.getText());
                final String yanzhengma= String.valueOf(input_user_sms_code_editview.getText());
                final String password= String.valueOf(input_user_password_regiest_editeView.getText());
                final String password_two= String.valueOf(et_zhuce_password_again.getText());
                //判断用户是否输入了验证码
                if (yanzhengma.equals("")||yanzhengma==null){
                    Toast.makeText(SDLoginActivity.this,"请输入验证码",Toast.LENGTH_LONG).show();
                    return;
                }
                //判断手机号输入的格式
                if (!isMobilephone(phone)) {
                    Toast.makeText(SDLoginActivity.this,"请输入正确的手机号",Toast.LENGTH_LONG).show();
                    return;
                }
                //判断密码的输入格式
                if (!rexCheckPassword(password)){
                    Toast.makeText(SDLoginActivity.this,"请输入8~16位密码,密码必须包含字母和数字",Toast.LENGTH_LONG).show();
                    return;
                }
                //判断两次密码是否一致
                if (!password.equals(password_two)){
                    Toast.makeText(SDLoginActivity.this,"两次输入密码不一致",Toast.LENGTH_LONG).show();
                    return;
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    if(ContextCompat.checkSelfPermission(SDLoginActivity.this,
                            Manifest.permission.READ_PHONE_STATE)== PackageManager.PERMISSION_GRANTED){
                        getIMEI();
                    }

                }
                //弹出正在注册的Dialog
                DialogUntil.showLoadingDialog(SDLoginActivity.this,"正在注册",true);
                //拼接访问的URL
                String url=XY_Response.URL_REGISTER+"mobilePhone="+phone+"&Dynamic="+yanzhengma+"&password="+getMD5(password);
                RequestCenter.register(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        //访问成功清除Dialog
                        DialogUntil.closeLoadingDialog();
                        //得到后台返回的数据并保存到实体类中
                        FanHui fanHui= (FanHui) responseObj;
                        //如果返回码为1000,说明注册成功
                        if (fanHui.getCode().equals("1000")){
                            //发送信息到Handler,让Handler进行操作
                            Message message=new Message();
                            message.what=8000;
                            handler.sendMessage(message);
                            //如果返回码为500,说明注册失败
                        }else if (fanHui.getCode().equals("500")){
                            //注册失败,弹出注册失败的原因
                            Toast.makeText(SDLoginActivity.this,
                                   fanHui.getMsg(),Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Object reasonObj) {
                        //网络访问失败也要清除Dialog
                        DialogUntil.closeLoadingDialog();
                        //弹出检查网络的Dialog,让用户可以直接跳转到修改网络页面
                        MyDialog.showDialog(SDLoginActivity.this,"请检查网络连接");
                    }
                });
            }
        });

        loginView = (LinearLayout) findViewById(R.id.loginView);
        //布局的控制
        regiestView = (LinearLayout)findViewById(R.id.registerView);

        //选择登录注册按钮实例化
        loginMenutView = (LinearLayout) findViewById(R.id.loginMenutForm);
        regiestMenutView  = (LinearLayout) findViewById(R.id.registeredMenutForm);
        //选择登录按钮,点击以后为登录页面
        loginMenutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginView.setVisibility(View.VISIBLE);
                regiestView.setVisibility(View.GONE);
                showPointForLogin();
                //清空已经填写的信息
                qingkong();
                islogin = true;
            }
        });
        //选择注册按钮,点击以后为注册页面
        regiestMenutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginView.setVisibility(View.GONE);
                regiestView.setVisibility(View.VISIBLE);
                showPointForRegiest();
                //清空已经填写的信息
                qingkong();
                islogin = false;

            }
        });
        // 默认先显示登录的
        this.showPointForLogin();
        //忘记密码按钮
        forget_password_button = (TextView)findViewById(R.id.forget_password_button);
        //点击以后跳转到忘记密码页面
        forget_password_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // forget password
                Intent intent = new Intent(SDLoginActivity.this,RegisteredActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("ischange",true);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //显示为登录
    public void showPointForLogin(){
        point_login.setVisibility(View.VISIBLE);
        point_regiest.setVisibility(View.GONE);
    }
    //显示为注册页面
    public void showPointForRegiest(){
        point_regiest.setVisibility(View.VISIBLE);
        point_login.setVisibility(View.GONE);
    }
    //倒计时的方法
    public void setTimerTask(){
        mtimer = new Timer();
        mtimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                timerHandler.sendMessage(message);
            }
        },1000,1000);
    }
    //在倒计时的时候,对按钮上的字进行赋值
    public void changeSmsButton(){
        bt_get_smscode.setText(timeCount + "秒重发");

    }
    private Handler timerHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                timeCount --;
                if (timeCount >= 0){
                    changeSmsButton();
                }else{
                    mtimer.cancel();
                    bt_get_smscode.setEnabled(true);
                    bt_get_smscode.setText(R.string.getsmsversion);
                }
            }
        }
    };


//    @Override
//    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//
//        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
//        if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > keyHeight)){
//
//        }else if(oldBottom != 0 && bottom != 0 &&(bottom - oldBottom > keyHeight)){
//
//        }
//    }


    //开始倒计时
    public void startCountdown(){
        regiest_action_button.setEnabled(true);
        changeSmsButton();
        bt_get_smscode.setEnabled(false);
        setTimerTask();
    }
    public static boolean isMobilephone(String phone) {
        if (phone.startsWith("86") || phone.startsWith("+86")) {
            phone = phone.substring(phone.indexOf("86") + 2);
        }
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }
    //密码的正则验证
    public static boolean rexCheckPassword(String input) {
       // 7-16 位，字母、数字、字符
       //String reg = "^([A-Z]|[a-z]|[0-9]|[`-=[];,./~!@#$%^*()_+}{:?]){6,20}$";
        String regStr = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
//       String regStr = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){8,16}$";
       return input.matches(regStr);
    }
    //MD5加密的方法
    public static String getMD5(String info){
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();
            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++)
            {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1)
                {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                }
                else
                {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return strBuf.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            return "";
        }
        catch (UnsupportedEncodingException e)
        {
            return "";
        }
    }
    //清空已输入信息的方法
    private void qingkong(){
        input_user_name_login_editview.setText("");
        input_user_password_login_editview.setText("");
        input_user_name_regiest_editview.setText("");
        //验证码输入框
        input_user_sms_code_editview.setText("");
        //首次输入密码
        input_user_password_regiest_editeView.setText("");
        //第二次密码输入框
        et_zhuce_password_again.setText("");
    }
    //保存信息到SP中
    private void setSP(Users users){
        //会员编号
        SPManager.getInstance().putString("loginKey",String.valueOf(users.getObj().getLoginKey()));
        SPManager.getInstance().putString("c_memberId", String.valueOf(users.getObj().getC_memberId()));
        SPManager.getInstance().putString("mobilePhone",users.getObj().getMobilePhone());
        SPManager.getInstance().putString("realName",users.getObj().getRealName());
        SPManager.getInstance().putString("nickName",users.getObj().getNickName());
        SPManager.getInstance().putString("sex",users.getObj().getSex());
        SPManager.getInstance().putString("headPhotoUrl",users.getObj().getHeadPhotoUrl());
//        SPManager.getInstance().putString("status",users.getObj().get(0).getStatus());
        SPManager.getInstance().putString("birthday",users.getObj().getBirthday());
        SPManager.getInstance().putString("email",users.getObj().getEmail());
        SPManager.getInstance().putString("address",users.getObj().getAddress());
        SPManager.getInstance().putString("qq",users.getObj().getQq());
        SPManager.getInstance().putString("weChat",users.getObj().getWeChat());
        SPManager.getInstance().putString("age", users.getObj().getAge());
        SPManager.getInstance().putString("ownerId",users.getObj().getOwnerId());
    }
    //从SP中得到数据保存到实体类中
    private Users setUserforSP(){
        Users users=new Users();
        Users.Obj obj=new Users.Obj();
        obj.setC_memberId(Integer.parseInt(SPManager.getInstance().getString("c_memberId",null)));
        obj.setMobilePhone(SPManager.getInstance().getString("mobilePhone",null));
//        obj.setRealName(SPManager.getInstance().getString("realName",null));
//        obj.setNickName(SPManager.getInstance().getString("nickName",null));
//        obj.setSex(SPManager.getInstance().getString("sex",null));
        obj.setHeadPhotoUrl(SPManager.getInstance().getString("headPhotoUrl",null));
//        obj.setStatus(SPManager.getInstance().getString("status",null));
//        obj.setBirthday(SPManager.getInstance().getString("birthday",null));
//        obj.setEmail(SPManager.getInstance().getString("email",null));
//        obj.setAddress(SPManager.getInstance().getString("address",null));
//        obj.setQq(SPManager.getInstance().getString("qq",null));
//        obj.setWeChat(SPManager.getInstance().getString("weChat",null));
//        obj.setAge(SPManager.getInstance().getString("age",null));
//        obj.setOwnerId(SPManager.getInstance().getString("ownerId",null));

        users.setObj(obj);
        return users;
    }


    //得到设备码
    private void getIMEI(){
        TelephonyManager telephonyManager=(TelephonyManager)
                this.getSystemService(Context.TELEPHONY_SERVICE);
        APP_ID.APP_ID =telephonyManager.getDeviceId();

    }
    public void getQuanxian() {
        if (Build.VERSION.SDK_INT >= 23) {
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
                android.support.v4.app.ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},1);

            }

        }
    }

}
