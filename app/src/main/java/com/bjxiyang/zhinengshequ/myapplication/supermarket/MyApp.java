package com.bjxiyang.zhinengshequ.myapplication.supermarket;

import android.app.Activity;
import android.content.SharedPreferences;

import com.bjxiyang.zhinengshequ.myapplication.app.GuardApplication;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengyongge on 2016/5/19 0019.
 */
public class MyApp extends GuardApplication {

    private static List<Activity> con_list = new ArrayList<Activity>();
    private static MyApp instance;
    private SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = (MyApp) getApplicationContext();

        //imageload初始化
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(
                defaultOptions).build();
        ImageLoader.getInstance().init(config);
    }

}
