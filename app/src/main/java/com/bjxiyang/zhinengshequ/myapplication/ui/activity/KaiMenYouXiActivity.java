package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class KaiMenYouXiActivity extends BeasActivity implements View.OnClickListener{
    private ImageView kaimenyouxi_shengzi;
    private TextView kaimenyouxi_name;
    private TextView kaimenyouxi_youxiaoqi;
    private ImageView kaimenyouxi_zhenknow;
    private LinearLayout kaimenyouxi_guize;
    private ImageView kaimenyouxi_close;
    private ImageView xixian_kaimenyouxi;

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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kaimenyouxi_guize:


                break;
            case R.id.kaimenyouxi_zhenknow:
                finish();
                break;
            case R.id.kaimenyouxi_close:
                finish();
                break;
        }
    }
}
