package com.bjxiyang.zhinengshequ.myapplication.app;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DaoMaster;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DaoSession;
import com.bjxiyang.zhinengshequ.myapplication.greendao.DaoUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;
import java.util.List;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cn.yunzt.top.yztsdk.YZTEsdk;

/**
 * Created by sander on 2017/4/7.
 */

public class GuardApplication extends MultiDexApplication {

    /**
     * 是否加密数据库.
     */
    public static final boolean ENCRYPTED = false;
    private static DaoSession daoSession;
    private final String DB_ENCRYPIED_NAME = "encrypied.db";
    private final String DB_NAME = "normal.db";

    private static List<Activity> con_list = new ArrayList<Activity>();
    private SharedPreferences sp;

    public static GuardApplication instance;

    public GuardApplication(){
        instance = this;
    }

    public static GuardApplication getContent(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        YZTEsdk.initYZT(this);

        DaoUtils.init(this);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? DB_ENCRYPIED_NAME : DB_NAME);
//        Database db = ENCRYPTED ? helper.getEncryptedWritableDb(getResources().getString(R.string.db_psw)) : helper.getWritableDb();//如果使用了加密，则需要用密码打开
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();//获取到daoSession，用于后续操作

        Log.d("sander","on create");

        Fresco.initialize(this);
//
        CustomActivityOnCrash.install(this);


        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(
                defaultOptions).build();
        ImageLoader.getInstance().init(config);

    }


    /**
     * 请求DaoSession
     *
     * @return
     */
    public static DaoSession getDaoSession() {
        return daoSession;
    }


}
