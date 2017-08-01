package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.model.GongGao;

/**
 * Created by Administrator on 2017/6/10 0010.
 */

public class XiaoQuGongGaoXiangQingActivity extends MySwipeBackActivity {
    /**
     * UI
     */
    private RelativeLayout rl_xiaoqugonggao_xiangqing_fanhui;
    private TextView tv_xiaoqugonggao_xiangqing_title;
    private ImageView iv_xiaoqugonggao_xiangqing_jinji;
    private TextView tv_xiaoqugonggao_xiangqing_date;
    private TextView tv_xiaoqugonggao_xiangqing_time;
    private TextView tv_xiaoqugonggao_xiangqing_wuyeguanlichu;
    private TextView tv_xiaoqugonggao_xiangqing_gonggaoxiangqing;
    /**
     * Data
     */

    private GongGao.Obj obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoqugonggao_xiangqing);
        initUI();
        initData();
        sendData();

    }

    private void sendData() {
        tv_xiaoqugonggao_xiangqing_title.setText(obj.getTitle());
        tv_xiaoqugonggao_xiangqing_date.setText(obj.getAddTime());
        if (obj.getType()==0){
            iv_xiaoqugonggao_xiangqing_jinji.setVisibility(View.GONE);
        }
        tv_xiaoqugonggao_xiangqing_wuyeguanlichu.setText(obj.getNoticer());
        tv_xiaoqugonggao_xiangqing_gonggaoxiangqing.setText(obj.getContent());
    }
    private void initUI() {
        rl_xiaoqugonggao_xiangqing_fanhui= (RelativeLayout) findViewById(R.id.rl_xiaoqugonggao_xiangqing_fanhui);
        rl_xiaoqugonggao_xiangqing_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_xiaoqugonggao_xiangqing_title= (TextView) findViewById(R.id.tv_xiaoqugonggao_xiangqing_title);
        iv_xiaoqugonggao_xiangqing_jinji= (ImageView) findViewById(R.id.iv_xiaoqugonggao_xiangqing_jinji);
        tv_xiaoqugonggao_xiangqing_date= (TextView) findViewById(R.id.tv_xiaoqugonggao_xiangqing_date);
        tv_xiaoqugonggao_xiangqing_wuyeguanlichu= (TextView) findViewById(R.id.tv_xiaoqugonggao_xiangqing_wuyeguanlichu);
        tv_xiaoqugonggao_xiangqing_gonggaoxiangqing= (TextView) findViewById(R.id.tv_xiaoqugonggao_xiangqing_gonggaoxiangqing);
    }
    private void initData(){
        Intent intent=getIntent();
        obj= (GongGao.Obj) intent.getSerializableExtra("data");
//        gonggao= (GongGao) bundle.get("data");
    }
}
