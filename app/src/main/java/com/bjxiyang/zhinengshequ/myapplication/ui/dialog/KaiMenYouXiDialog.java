package com.bjxiyang.zhinengshequ.myapplication.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.model.OpenDoor;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.MyWebViewActivity;
import com.bjxiyang.zhinengshequ.myapplication.until.DateUtils;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class KaiMenYouXiDialog extends Dialog implements View.OnClickListener{
    private ImageView kaimenyouxi_shengzi;
    private TextView kaimenyouxi_name;
    private TextView kaimenyouxi_youxiaoqi;
    private ImageView kaimenyouxi_zhenknow;
    private LinearLayout kaimenyouxi_guize;
    private ImageView kaimenyouxi_close;
    private ImageView xixian_kaimenyouxi;
    private OpenDoor.ObjBean objBean;



    public KaiMenYouXiDialog(@NonNull Context context,OpenDoor.ObjBean objBean) {
        super(context,R.style.dialog);
        this.objBean=objBean;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaimenyouxi);
        initUI();
    }
    private void initUI() {
        xixian_kaimenyouxi= (ImageView) findViewById(R.id.xixian_kaimenyouxi);
        kaimenyouxi_shengzi= (ImageView) findViewById(R.id.kaimenyouxi_shengzi);
        kaimenyouxi_zhenknow= (ImageView) findViewById(R.id.kaimenyouxi_zhenknow);
        kaimenyouxi_close= (ImageView) findViewById(R.id.kaimenyouxi_close);
        kaimenyouxi_name= (TextView) findViewById(R.id.kaimenyouxi_name);
        kaimenyouxi_youxiaoqi= (TextView) findViewById(R.id.kaimenyouxi_youxiaoqi);
        kaimenyouxi_guize= (LinearLayout) findViewById(R.id.kaimenyouxi_guize);
        kaimenyouxi_guize.setOnClickListener(this);
        kaimenyouxi_close.setOnClickListener(this);
        kaimenyouxi_zhenknow.setOnClickListener(this);
        kaimenyouxi_name.setText(objBean.getBondName());
        kaimenyouxi_youxiaoqi.setText(DateUtils.getDateString(objBean.getValidityDate()));
        //跳转到URL
//        kaimenyouxi_guize

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kaimenyouxi_guize:
                Intent intent=new Intent(getContext(), MyWebViewActivity.class);
                intent.putExtra("url",objBean.getBondUrl());
                getContext().startActivity(intent);

                break;
            case R.id.kaimenyouxi_zhenknow:
                cancel();
                break;
            case R.id.kaimenyouxi_close:
                cancel();
                break;
        }
    }
}
