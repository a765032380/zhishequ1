package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class ShengHuoPayActivity extends MySwipeBackActivity{

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        type=intent.getIntExtra("type",-1);
        switch (type){
            case ShengHuoJiaoFeiActivity.SHUIFEI:
                break;
            case ShengHuoJiaoFeiActivity.DIANFEI:
                break;
            case ShengHuoJiaoFeiActivity.RANQIFEI:
                break;
            case ShengHuoJiaoFeiActivity.KUANDAIFEI:
                break;
            case ShengHuoJiaoFeiActivity.WUYEFEI:
                break;
        }

    }
}
