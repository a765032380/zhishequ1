package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.Users;
import com.bjxiyang.zhinengshequ.myapplication.model.Users1;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.CircleImageView;
import com.bjxiyang.zhinengshequ.myapplication.view.MyDialog;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class MyXinXiActivity extends MySwipeBackActivity {
    private CircleImageView iv_gerenxinxi_xiugai_touxiang;
    private TextView tv_gerenxinxi_xiugai_name;
    private TextView tv_gerenxinxi_xiugai_sex;
    private TextView tv_gerenxinxi_xiugai_chushengriqi;
    private TextView tv_gerenxinxi_xiugai_tel;
    private TextView tv_gerenxinxi_xiugai_email;
    private TextView tv_gerenxinxi_xiugai_qq;
    private TextView tv_gerenxinxi_xiugai_weixin;
    private TextView tv_gerenxinxi_xiugai_address;
    private ImageView iv_gerenxinxi_xiugai_bt_xiugai;

    private String arr[]={"c_memberId","mobilePhone","realName",
            "nickName","sex","headPhotoUrl","status","birthday","email","address",
            "qq","weChat","age","ownerId"};
    private RelativeLayout iv_gerenxinxi_xiugai_fanhui;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenxinxi_xiugai);
        initUI();
        getData();
//          setTextForUsers();
    }


    private void initUI() {
        iv_gerenxinxi_xiugai_touxiang= (CircleImageView) findViewById(R.id.iv_gerenxinxi_xiugai_touxiang);
        tv_gerenxinxi_xiugai_name= (TextView) findViewById(R.id.tv_gerenxinxi_xiugai_name);
        tv_gerenxinxi_xiugai_sex=(TextView) findViewById(R.id.tv_gerenxinxi_xiugai_sex);
        tv_gerenxinxi_xiugai_chushengriqi=(TextView) findViewById(R.id.tv_gerenxinxi_xiugai_chushengriqi);
        tv_gerenxinxi_xiugai_tel=(TextView) findViewById(R.id.tv_gerenxinxi_xiugai_tel);
        tv_gerenxinxi_xiugai_email=(TextView) findViewById(R.id.tv_gerenxinxi_xiugai_email);
        tv_gerenxinxi_xiugai_qq=(TextView) findViewById(R.id.tv_gerenxinxi_xiugai_qq);
        tv_gerenxinxi_xiugai_weixin=(TextView) findViewById(R.id.tv_gerenxinxi_xiugai_weixin);
        tv_gerenxinxi_xiugai_address=(TextView) findViewById(R.id.tv_gerenxinxi_xiugai_address);
        iv_gerenxinxi_xiugai_bt_xiugai=(ImageView) findViewById(R.id.iv_gerenxinxi_xiugai_bt_xiugai);
        iv_gerenxinxi_xiugai_bt_xiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyXinXiActivity.this,MyXinXi_XiuGaiActivity.class);
                startActivity(intent);
                finish();
            }
        });
        iv_gerenxinxi_xiugai_fanhui= (RelativeLayout) findViewById(R.id.iv_gerenxinxi_xiugai_fanhui);
        iv_gerenxinxi_xiugai_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getData(){
        DialogUntil.showLoadingDialog(this,"正在加载",true);
        String url= XY_Response.URL_GETUSERINFO+"mobilePhone="+
                SPManager.getInstance().getString("mobilePhone",null);
        RequestCenter.getUserInfo(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                Users1 users= (Users1) responseObj;
                if (users.getCode().equals("1000")){
                    setTextForHTTP(users);
                }

            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
                MyDialog.showDialog(MyXinXiActivity.this);

            }
        });
    }
    private void setTextForUsers(){
        Users users= UserManager.getInstance().getUser();
        Users.Obj obj=users.getObj();
        if ((obj.getNickName().trim())!=null){
            Log.i("ASDFG",obj.getNickName().trim());
            tv_gerenxinxi_xiugai_name.setText(obj.getNickName());
        }
//        if (obj.getSex().trim()!=null&&!obj.getSex().trim().equals("")){
//            tv_gerenxinxi_xiugai_sex.setText(obj.getSex());
//
//        }
        if (obj.getBirthday().trim()!=null&&!obj.getBirthday().trim().equals("")){
            tv_gerenxinxi_xiugai_chushengriqi.setText(obj.getBirthday());
        }
        if (obj.getMobilePhone().trim()!=null&&!obj.getMobilePhone().trim().equals("")){
            tv_gerenxinxi_xiugai_tel.setText(obj.getMobilePhone());
        }
        if (obj.getEmail()!=null&&!obj.getEmail().equals("")){
            tv_gerenxinxi_xiugai_email.setText(obj.getEmail());
        }
        if (obj.getQq()!=null&&!obj.getQq().equals("")){
            tv_gerenxinxi_xiugai_qq.setText(obj.getQq());
        }
        if (obj.getWeChat()!=null&&!obj.getWeChat().equals("")){
            tv_gerenxinxi_xiugai_weixin.setText(obj.getWeChat());
        }
        if (obj.getAddress()!=null&&!obj.getAddress().equals("")){
            tv_gerenxinxi_xiugai_address.setText(obj.getAddress());
        }
    }
    private void setTextForHTTP(Users1 users){
        Users1.Obj obj=users.getObj();
        if ((obj.getNickName())!=null){
            tv_gerenxinxi_xiugai_name.setText(obj.getRealName());
        }
        if (obj.getSex().equals("0")){
            tv_gerenxinxi_xiugai_sex.setText("女");
        }else {
            tv_gerenxinxi_xiugai_sex.setText("男");
        }

        if (obj.getBirthday()!=null){
            tv_gerenxinxi_xiugai_chushengriqi.setText(obj.getBirthday());
        }
        if (obj.getMobilePhone()!=null){
            tv_gerenxinxi_xiugai_tel.setText(obj.getMobilePhone());
        }
        if (obj.getEmail()!=null){
            tv_gerenxinxi_xiugai_email.setText(obj.getEmail());
        }
        if (obj.getQq()!=null){
            tv_gerenxinxi_xiugai_qq.setText(obj.getQq());
        }
        if (obj.getWeChat()!=null){
            tv_gerenxinxi_xiugai_weixin.setText(obj.getWeChat());
        }
        if (obj.getAddress()!=null){
            tv_gerenxinxi_xiugai_address.setText(obj.getAddress());
        }

        if (obj.getHeadPhotoUrl()!=null&&!obj.getHeadPhotoUrl().equals("")){
            ImageLoaderManager.getInstance(this).displayImage(
                    iv_gerenxinxi_xiugai_touxiang,obj.getHeadPhotoUrl());
            UserManager.getInstance().getUser().getObj().setHeadPhotoUrl(obj.getHeadPhotoUrl());

        }
    }
    private void savetoSP(){

    }


}
