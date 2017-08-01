package com.bjxiyang.zhinengshequ.myapplication.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.bjxiyang.zhinengshequ.R;

/**
 * Created by Administrator on 2017/6/8 0008.
 */

public class MyDialog{
    private static AlertDialog dialog;
    private static AlertDialog.Builder builder;

//    public static void showDialog(final Context context, String message){
//        builder=new AlertDialog.Builder(context);
//        builder.setTitle(message);
//        builder.setIcon(R.mipmap.ic_launcher);
//        builder.setPositiveButton("设置网络", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
//                context.startActivity(wifiSettingsIntent);
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                cancelDialog();
//            }
//        });
//    }

    public static void showDialog(final Activity context, String message){
        builder=new AlertDialog.Builder(context);

        builder.setTitle(message);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton("设置网络", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
                context.startActivity(wifiSettingsIntent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelDialog();
            }
        });
        if (!context.isFinishing()){
            builder.show();
        }
    }
    public static void showDialog(final Activity context){
        builder=new AlertDialog.Builder(context);

        builder.setTitle("请检查网络");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton("设置网络", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
                context.startActivity(wifiSettingsIntent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelDialog();
            }
        });
        if (!context.isFinishing()){
            builder.show();
        }
    }
    public static void showDialog(final Context context){
        builder=new AlertDialog.Builder(context);

        builder.setTitle("请检查网络");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton("设置网络", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
                context.startActivity(wifiSettingsIntent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelDialog();
            }
        });
//        if (!context.isFinishing()){
//            builder.show();
//        }
    }


    public static void cancelDialog(){
        builder.create();
    }

}
