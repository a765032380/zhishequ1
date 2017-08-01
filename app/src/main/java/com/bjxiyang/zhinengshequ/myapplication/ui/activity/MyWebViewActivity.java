package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bjxiyang.zhinengshequ.R;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class MyWebViewActivity extends MySwipeBackActivity{
    private WebView web;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xy_jinrongweb_activity);
        Intent intent=getIntent();
        url=intent.getStringExtra("url");
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
        web.setWebViewClient(new MyWebViewActivity.Callback());
        web.loadUrl(url);



//        web.setWebViewClient(new WebViewClient() {
//            //覆盖shouldOverrideUrlLoading 方法
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url1) {
//                web.loadUrl("http://"+url);
//                return true;
//            }
//        });
    }
    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            // TODO Auto-generated method stub
            if (MyWebViewActivity.this == null) {
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
