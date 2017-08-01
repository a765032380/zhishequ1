package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.bjxiyang.zhinengshequ.R;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class FuWuTiaoKuanActivity extends MySwipeBackActivity {
    private RelativeLayout rl_fuwutiaokuan_fanhui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuwutiaokuan);
        rl_fuwutiaokuan_fanhui= (RelativeLayout) findViewById(R.id.rl_fuwutiaokuan_fanhui);
        rl_fuwutiaokuan_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
