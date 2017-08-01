package com.bjxiyang.zhinengshequ.myapplication.ui.video;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.yunzt.top.yztsdk.YZTEsdk;

/**
 * Created by zhide on 2017/8/1.
 */

public class VideoBroadcastReceiver extends BroadcastReceiver{
    public static final String YZT_LOgin ="org.ebiao.kaoqin.YTXHelper.UsernameSN";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(YZT_LOgin)){
            /**
             * 此方法执行后 日志中会有两个返回状态：1.sdk初始化成功 2.初始化成功后，登陆
             * 账号，成功登陆 返回 200 登录成功。 否则会返回：连接状态失败，返回失败错误代码。
             */
            YZTEsdk.onInita(context);
        }
    }
}
