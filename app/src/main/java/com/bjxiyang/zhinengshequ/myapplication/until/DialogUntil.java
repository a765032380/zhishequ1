package com.bjxiyang.zhinengshequ.myapplication.until;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class DialogUntil {
    private static ProgressDialog processDia;
    /**
     * 显示加载中对话框
     *
     * @param context
     */
    public static void showLoadingDialog(Context context, String message, boolean isCancelable) {
        closeLoadingDialog();
        if (processDia == null) {
            processDia= new ProgressDialog(context);
            //点击提示框外面是否取消提示框
            processDia.setCanceledOnTouchOutside(true);
            //点击返回键是否取消提示框
            processDia.setCancelable(isCancelable);
            processDia.setIndeterminate(true);
            processDia.setMessage(message);
            setOndismiss();
            processDia.show();
        }
    }
    /**
     * 关闭加载对话框
     */
    public static void closeLoadingDialog() {
        if (processDia != null) {
            if (processDia.isShowing()) {
                processDia.cancel();
            }
            processDia = null;
        }
    }
    public static boolean isShow(){
        if (processDia.isShowing()){
            return true;
        }else {
            return false;
        }
    }
    public static void setOndismiss(){
        processDia.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                RequestCenter.cancel();
            }
        });
    }

}
