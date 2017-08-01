package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.MyDialog;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisteredActivity extends MySwipeBackActivity implements View.OnClickListener{

    private ImageView zhuce_logo;
    private String sms_id;

    private int timeCount = 60;


    boolean isChangePassworld;
    boolean isXiuGai=false;

    private Timer mtimer;
    LinearLayout regiestView;
    private LinearLayout ln_fuwutiaokuan;

    private Button bt_get_smscode;
    private EditText et_zhuce_password_again;
    //注册模块的
    EditText input_user_name_regiest_editview;
    EditText input_user_sms_code_editview;
    EditText input_user_sms_code_again_editview;
    EditText input_user_password_regiest_editeView;
    Button regiest_action_button;

    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what){
                case 5000:
                    startCountdown();
                    break;
                case 4000:
                    SPManager.getInstance().remove("mobilePhone");
                    UserManager.getInstance().removeUser();
                    Intent intent=new Intent(RegisteredActivity.this,SDLoginActivity.class);
                    startActivity(intent);
                    break;
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        Intent intent = getIntent();
        isXiuGai=intent.getBooleanExtra("isXiuGai",false);
        isChangePassworld = intent.getBooleanExtra("ischange",true);

        initCallBack();
        initData();
        initView();
    }

    private void initCallBack(){
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.callbackLayout);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initData(){

    }
    public void initView(){
        ln_fuwutiaokuan= (LinearLayout) findViewById(R.id.ln_fuwutiaokuan);
        zhuce_logo= (ImageView) findViewById(R.id.zhuce_logo);
        //手机号输入框
        input_user_name_regiest_editview = (EditText)findViewById(R.id.inputusernameedittext);
        //发送验证码的按钮
        bt_get_smscode= (Button) findViewById(R.id.bt_get_smscode);
        bt_get_smscode.setOnClickListener(this);

        //验证码输入框
        input_user_sms_code_editview = (EditText) findViewById(R.id.et_reg_smscode) ;
        //首次输入密码
        input_user_password_regiest_editeView = (EditText)findViewById(R.id.et_reg_password);
        //第二次密码输入框
        et_zhuce_password_again= (EditText) findViewById(R.id.et_zhuce_password_again);

        //显示LOGO
        zhuce_logo.setVisibility(View.VISIBLE);
        //注册按钮
        regiest_action_button = (Button) findViewById(R.id.reg_confirm);
        regiest_action_button.setOnClickListener(this);
        //设置提交按钮的文本
        regiest_action_button.setText("修改密码");
        //布局的控制
        regiestView = (LinearLayout)findViewById(R.id.registerView);
        //显示布局
        regiestView.setVisibility(View.VISIBLE);

        if (isXiuGai){
            ln_fuwutiaokuan.setVisibility(View.GONE);
        }else {
            ln_fuwutiaokuan.setVisibility(View.VISIBLE);
        }

    }

    public void setTimerTask(){
        mtimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message1 = new Message();
                message1.what = 1;
                timerHandler.sendMessage(message1);
            }
        },1000,1000);
    }
    public void startCountdown(){
        regiest_action_button.setEnabled(true);
        changeSmsButton();
        bt_get_smscode.setEnabled(false);
        setTimerTask();

    }

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


    public void showToastMessage(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_get_smscode:
                timeCount=60;
                mtimer = new Timer();
                String phone1= String.valueOf(input_user_name_regiest_editview.getText());
                if (!isMobilephone(phone1)) {
                    Toast.makeText(RegisteredActivity.this,"请输入正确的手机号",Toast.LENGTH_LONG).show();
                    return;
                }

                DialogUntil.showLoadingDialog(RegisteredActivity.this,"请稍等",true);
                String type="1";
                String test= XY_Response.URL_SED_MSM+"mobilePhone="+phone1+"&type="+type;
                //发送短信验证码
                RequestCenter.register(test, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogUntil.closeLoadingDialog();
                        FanHui fanHui= (FanHui) responseObj;
                        if (fanHui.getCode().equals("1000")){
                            Message message=new Message();
                            message.what=5000;
                            handler.sendMessage(message);

                        }else if (fanHui.getCode().equals("500")){
                            Toast.makeText(RegisteredActivity.this,
                                    fanHui.getMsg(),Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogUntil.closeLoadingDialog();
                        MyDialog.showDialog(RegisteredActivity.this,"请检查网络连接");
                    }
                });

                break;
            case R.id.reg_confirm:
                //修改密码的提交按钮
                String phone= String.valueOf(input_user_name_regiest_editview.getText());
                String yanzhengma= String.valueOf(input_user_sms_code_editview.getText());
                String password= String.valueOf(input_user_password_regiest_editeView.getText());
                String password_two= String.valueOf(et_zhuce_password_again.getText());
                //得到数据,进行验证和提交
                //修改完成到登录页面进行登录
                //修改完成以后在轻量级储存器中移除当前保存的用户,重新登录
                UserManager.getInstance().removeUser();
                SPManager.getInstance().remove("phone");

                if (yanzhengma.equals("")||yanzhengma==null){
                    Toast.makeText(RegisteredActivity.this,"请输入验证码",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!isMobilephone(phone)) {
                    Toast.makeText(RegisteredActivity.this,"请输入正确的手机号",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!rexCheckPassword(password)){
                    Toast.makeText(RegisteredActivity.this,"请输入8~16位密码,密码必须包含字母和数字",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.equals(password_two)){
                    Toast.makeText(RegisteredActivity.this,"两次输入密码不一致",Toast.LENGTH_LONG).show();
                    return;
                }
                DialogUntil.showLoadingDialog(RegisteredActivity.this,"请稍等",true);

                String url=XY_Response.URL_FINDPWD+"mobilePhone="+phone+"&Dynamic="+yanzhengma+"&password="+getMD5(password);
                RequestCenter.register(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogUntil.closeLoadingDialog();

                        FanHui fanHui= (FanHui) responseObj;
                        if (fanHui.getCode().equals("1000")){
                            //注册成功
                            Toast.makeText(RegisteredActivity.this,"修改成功",Toast.LENGTH_LONG).show();
                            Message message=new Message();
                            message.what=4000;
                            handler.sendMessage(message);
                            DialogUntil.closeLoadingDialog();
                        }else if (fanHui.getCode().equals("500")){

                            //注册失败
                            Toast.makeText(RegisteredActivity.this,
                                    fanHui.getMsg(),Toast.LENGTH_LONG).show();
                        }else {
                            //注册失败
                            Toast.makeText(RegisteredActivity.this,"注册失败",Toast.LENGTH_LONG).show();
                        }

                    }
                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogUntil.closeLoadingDialog();
                        MyDialog.showDialog(RegisteredActivity.this,"请检查网络连接");
                    }
                });

                break;


        }
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
//        String regStr = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){8,16}$";
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

}
