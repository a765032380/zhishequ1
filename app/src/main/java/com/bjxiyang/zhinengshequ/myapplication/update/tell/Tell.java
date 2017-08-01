package com.bjxiyang.zhinengshequ.myapplication.update.tell;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by gll on 17-5-18.
 */

public class Tell {
    private static final String phoneNumber="18813045215";

    public static void callPhone(Context context){

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
