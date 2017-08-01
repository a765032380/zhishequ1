package com.bjxiyang.zhinengshequ.myapplication.until;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class DiaLogUnit {
    private static boolean isback=false;

    public static boolean showDialogTishi(Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确认退货吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            isback=true;
             dialog.dismiss();
            }
         });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
             isback=false;
              dialog.dismiss();
          }
         });
         builder.create().show();
        return isback;
        }
}
