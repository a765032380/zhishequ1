package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bjxiyang.zhinengshequ.R;

/**
 * Created by gll on 17-5-24.
 */

public class XY_JinRongWebActivity  extends MySwipeBackActivity {
    private WebView web;
    private String url1;
    private int gId;
    private String sId;
    private boolean is_sid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xy_jinrongweb_activity);
        Intent intent=getIntent();
        url1=intent.getStringExtra("URL");
        gId=intent.getIntExtra("GID",0);
        sId=intent.getStringExtra("SID");
        is_sid=intent.getBooleanExtra("IS_SID",false);
        initUI();
    }

    private void initUI() {
        web= (WebView) findViewById(R.id.web);
        WebSettings webSettings=web.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webSettings.setBuiltInZoomControls(true);
        web.setWebViewClient(new Callback());
        if (is_sid){
            web.loadUrl(url1+"?sId="+sId);
            Log.i("YYY",url1+"?sId="+sId);
        }else {
            web.loadUrl(url1+"?gId="+gId);
            Log.i("YYY",url1+"?gId="+gId);
        }

    }
    private class Callback extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            // TODO Auto-generated method stub
            if (XY_JinRongWebActivity.this == null) {
                return false;
            }


            //调用拨号程序
            if (url.startsWith("mailto:") || url.startsWith("geo:") || url.startsWith("tel:") || url.startsWith("smsto:")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }

            return false;
        }
    }

}
