package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bjxiyang.zhinengshequ.R;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class XiaoQuFuWuActivity extends MySwipeBackActivity implements View.OnClickListener {
    private RelativeLayout iv_xiaoqufuwu_fanhui;
    private LinearLayout ll_xiaoqufuwu_wuyejiaofei_jinru;
    private LinearLayout ll_xiaoqufuwu_xiaoqugonggao_jinru;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoqufuwu);
        initUi();
    }

    private void initUi() {
        iv_xiaoqufuwu_fanhui= (RelativeLayout) findViewById(R.id.iv_xiaoqufuwu_fanhui);
        ll_xiaoqufuwu_wuyejiaofei_jinru= (LinearLayout) findViewById(R.id.ll_xiaoqufuwu_wuyejiaofei_jinru);
        ll_xiaoqufuwu_xiaoqugonggao_jinru= (LinearLayout) findViewById(R.id.ll_xiaoqufuwu_xiaoqugonggao_jinru);

        iv_xiaoqufuwu_fanhui.setOnClickListener(this);
        ll_xiaoqufuwu_wuyejiaofei_jinru.setOnClickListener(this);
        ll_xiaoqufuwu_xiaoqugonggao_jinru.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_xiaoqufuwu_fanhui:
                finish();
                break;
            case R.id.ll_xiaoqufuwu_wuyejiaofei_jinru:
                Intent intent=new Intent(XiaoQuFuWuActivity.this,WuYeJiaoFeiActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_xiaoqufuwu_xiaoqugonggao_jinru:
                Intent intent1=new Intent(XiaoQuFuWuActivity.this,XiaoQuGongGaoActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
