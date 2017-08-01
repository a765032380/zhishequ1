package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v13.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.baisi.myapplication.okhttp.request.RequestParams;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.app.GuardApplication;
import com.bjxiyang.zhinengshequ.myapplication.dialog.MyQuanXianDialog;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.model.JiGuang;
import com.bjxiyang.zhinengshequ.myapplication.model.Phoneinfo;
import com.bjxiyang.zhinengshequ.myapplication.model.UpdateVersion;
import com.bjxiyang.zhinengshequ.myapplication.model.WuYeJiaoFei;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.ui.fragment.BankingFragment;
import com.bjxiyang.zhinengshequ.myapplication.ui.fragment.HomeFragment;
import com.bjxiyang.zhinengshequ.myapplication.ui.fragment.MyFragment;
import com.bjxiyang.zhinengshequ.myapplication.ui.fragment.Supermarketfragment;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.CommonDialog;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.update.service.UpdateService;
import com.bjxiyang.zhinengshequ.myapplication.update.util.Util;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by gll on 17-5-20.
 */

public class MainActivity extends BeasActivity implements View.OnClickListener{

    private FragmentBackListener backListener;
    private boolean isInterception = false;

    private RelativeLayout mHomeLayout;
    private RelativeLayout mPondLayout;
    private RelativeLayout mMessageLayout;
    private RelativeLayout mMyLayou;

    private TextView mHomeView;
    private TextView mPondView;
    private TextView mMessageView;
    private TextView mMyView;

    private FragmentManager fm;
    private Fragment mHomeFragment;
    private Fragment mMyFragment;
    private Fragment mBankingFragment;
    private Fragment mSupermarketFragment;

    public static LinearLayout linearLayout;
    private long vT[]={300,100,300,100};

    private String phoneInfoString="";

    public static boolean isForeground = false;
    private MessageReceiver mMessageReceiver;

    public static final String MESSAGE_RECEIVED_ACTION = "com.bjxiyang.com.myapplication.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public static MainActivity mainActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity=this;
        checkVersion();

        quanxian();
        //打印获取到的设备码
//        Log.i("LOGIMEI",getIMEI());
        //极光推送初始化
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        setAlias();
        JPushInterface.resumePush(MainActivity.this);
        registerMessageReceiver();
        initDate();
        initUi();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            if (!SPManager.getInstance().getBoolean("isShangchuan", false)) {
                getPhoneNumberFromMobile();
            }
        }
        //开门有喜的Dialog
//        KaiMenYouXiDialog dialog=new KaiMenYouXiDialog(this);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();


    }
    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {

//                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String title=intent.getStringExtra(KEY_TITLE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    Gson gson=new Gson();
                JiGuang.Extras extras1=gson.fromJson(extras,JiGuang.Extras.class);
//                    JiGuang jiGuang=gson.fromJson(extras,JiGuang.class);
//                    JiGuang.Extras extras1=jiGuang.getExtras();
                    switch (extras1.getType()){
                        //授权开通
                        case "1":
                            showNotification(extras1,XYKeyAccredit.class);
                            break;
                        //物业费缴纳通知
                        case "2":
                            showNotification(extras1,WuYeJiaoFei.class);
                            break;
                        //小区公告
                        case "3":
                            showNotification(extras1,XiaoQuGongGaoActivity.class);
                            break;
                        //物业授权信息
                        case "4":
                            showNotification(extras1,XYKeyAccredit.class);
                            break;
//                    }

//                    StringBuilder showMsg = new StringBuilder();
//                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
//                    if (!ExampleUtil.isEmpty(extras)) {
//                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
//                    }


                }
            } catch (Exception e){
            }
        }
    }
    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }
    @Override
    protected void onPause() {
//        isForeground = false;
        super.onPause();
    }
    @Override
    protected void onDestroy() {
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }
    private void initUi() {
        fm=getSupportFragmentManager();
        //获取事务
        FragmentTransaction fragmentTransaction=fm.beginTransaction();
        //显示首页的Fragment
        mHomeFragment=new HomeFragment();
        fragmentTransaction.add(R.id.content_layout,mHomeFragment).show(mHomeFragment);
        fragmentTransaction.commit();

        mHomeLayout= (RelativeLayout) findViewById(R.id.home_layout_view);
        mHomeLayout.setOnClickListener(this);
        mPondLayout=(RelativeLayout) findViewById(R.id.pond_layout_view);
        mPondLayout.setOnClickListener(this);
        mMessageLayout= (RelativeLayout) findViewById(R.id.message_layout_view);
        mMessageLayout.setOnClickListener(this);
        mMyLayou= (RelativeLayout) findViewById(R.id.mine_layout_view);
        mMyLayou.setOnClickListener(this);
        mHomeView= (TextView) findViewById(R.id.home_image_view);
        mPondView= (TextView) findViewById(R.id.fish_image_view);
        mMessageView= (TextView) findViewById(R.id.message_image_view);
        mMyView= (TextView) findViewById(R.id.mine_image_view);
        mHomeView.setBackgroundResource(R.drawable.a_btn_shouye_pre);

        linearLayout= (LinearLayout) findViewById(R.id.linearLayout);
    }
    private void initDate() {

    }
    //四个按键的监听事件，以及对四个Fragment的操作
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        switch (view.getId()) {
            //主页
            case R.id.home_layout_view:
                mHomeView.setBackgroundResource(R.drawable.a_btn_shouye_pre);
                mPondView.setBackgroundResource(R.drawable.a_btn_jinrong);
                mMessageView.setBackgroundResource(R.drawable.a_btn_bianlidian);
                mMyView.setBackgroundResource(R.drawable.a_btn_wode);
                hideFragment(mSupermarketFragment,fragmentTransaction);
                hideFragment(mMyFragment,fragmentTransaction);
                hideFragment(mBankingFragment,fragmentTransaction);
//                将我们的HomeFragment显示出来
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.content_layout, mHomeFragment).show(mHomeFragment).commit();
                } else {
                    fragmentTransaction.show(mHomeFragment).commit();
                }
                break;
            //金融
            case R.id.pond_layout_view:
                mHomeView.setBackgroundResource(R.drawable.a_icon_shouye);
                mPondView.setBackgroundResource(R.drawable.a_icon_jinrong_e);
                mMessageView.setBackgroundResource(R.drawable.a_btn_bianlidian);
                mMyView.setBackgroundResource(R.drawable.a_btn_wode);
                hideFragment(mSupermarketFragment, fragmentTransaction);
                hideFragment(mMyFragment, fragmentTransaction);
                hideFragment(mHomeFragment, fragmentTransaction);
                //将我们的HomeFragment显示出来
                if (mBankingFragment == null) {
                    mBankingFragment = new BankingFragment();
                    fragmentTransaction.add(R.id.content_layout, mBankingFragment).show(mBankingFragment).commit();
                } else {
                    fragmentTransaction.show(mBankingFragment).commit();
                }
                break;
            //商超
            case R.id.message_layout_view:
                mHomeView.setBackgroundResource(R.drawable.a_icon_shouye);
                mPondView.setBackgroundResource(R.drawable.a_btn_jinrong);
                mMessageView.setBackgroundResource(R.drawable.a_icon_bianli_c);
                mMyView.setBackgroundResource(R.drawable.a_btn_wode);
                hideFragment(mHomeFragment, fragmentTransaction);
                hideFragment(mMyFragment, fragmentTransaction);
                hideFragment(mBankingFragment, fragmentTransaction);
                //将我们的HomeFragment显示出来
                if (mSupermarketFragment == null) {
                    mSupermarketFragment = new Supermarketfragment();
                    fragmentTransaction.add(R.id.content_layout, mSupermarketFragment).show(mSupermarketFragment).commit();
                } else {
                    fragmentTransaction.show(mSupermarketFragment).commit();
                }

                break;
            //我的
            case R.id.mine_layout_view:
                mHomeView.setBackgroundResource(R.drawable.a_icon_shouye);
                mPondView.setBackgroundResource(R.drawable.a_btn_jinrong);
                mMessageView.setBackgroundResource(R.drawable.a_btn_bianlidian);
                mMyView.setBackgroundResource(R.drawable.a_icon_wode_c);
                hideFragment(mHomeFragment, fragmentTransaction);
                hideFragment(mSupermarketFragment, fragmentTransaction);
                hideFragment(mBankingFragment, fragmentTransaction);
                //将我们的HomeFragment显示出来
                if (mMyFragment == null) {
                    mMyFragment = new MyFragment();
                    fragmentTransaction.add(R.id.content_layout, mMyFragment).show(mMyFragment).commit();
                } else {
                    fragmentTransaction.show(mMyFragment).commit();
                }
                break;
        }
    }
        /**
         * 用来隐藏具体的Fragment
         * @param fragment
         * @param ft
         */
    private void hideFragment(Fragment fragment, FragmentTransaction ft) {
        if (fragment != null) {
            ft.hide(fragment);
        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            moveTaskToBack(true);
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
    //为Fragment自定义返回按键写的方法
    public FragmentBackListener getBackListener() {
        return backListener;
    }
    public void setBackListener(FragmentBackListener backListener) {
        this.backListener = backListener;
    }
    public boolean isInterception() {
        return isInterception;
    }

    public void setInterception(boolean isInterception) {
        this.isInterception = isInterception;
    }

    //点击返回键的逻辑处理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            moveTaskToBack(true);
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isInterception()) {
                if (backListener != null) {
                    backListener.onbackForward();
                    return false;
                }else {
                    return true;
                }
            }else {
                moveTaskToBack(true);
//                MyUntil.show(MainActivity.this,"再次点击返回桌面");
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    //自定义返回键的监听事件接口
    public interface FragmentBackListener{
        void onbackForward();
    }

    private List<Phoneinfo> list;
    private static final String[] PHONES_PROJECTION = new String[] {
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER};
    public String getPhoneNumberFromMobile() {
        // TODO Auto-generated constructor stub
        list = new ArrayList<>();
        Cursor cursor = this.getContentResolver()
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,PHONES_PROJECTION,
                null, null, null);
        //moveToNext方法返回的是一个boolean类型的数据
        if (cursor!=null) {
            while (cursor.moveToNext()) {
                //读取通讯录的姓名
                String name = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                //读取通讯录的号码
                String number = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Phoneinfo phoneInfo = new Phoneinfo(name, number);

                list.add(phoneInfo);
            }
            Gson gson=new Gson();
            for (int i=0;i<list.size();i++){
                phoneInfoString=gson.toJson(list);
            }
            Log.i("PHONE",phoneInfoString);
            if (!phoneInfoString.equals("")||phoneInfoString!=null){
            shangChuan();
            }
        }

        return phoneInfoString;
    }
    //得到设备码
    private String getIMEI(){
        TelephonyManager telephonyManager=(TelephonyManager)
                this.getSystemService(Context.TELEPHONY_SERVICE);
        String imei=telephonyManager.getDeviceId();
        RequestCenter.requestRecommandData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.i("LOGBBBBB","发送数据成功");
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });
        return imei;
    }


    private void getUserHttp(){

    }
    //动态权限
    private void quanxian(){
        if (Build.VERSION.SDK_INT >= 23) {
            if (!SPManager.getInstance().getBoolean("isQuanXian",false)){
                MyQuanXianDialog dialog=new MyQuanXianDialog(this,this);
                dialog.show();

            }
//            getQuanXian();
        }

    }
    //动态权限的封装
    private void getQuanXian(){
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
//            if (!dialog.isShowing()) {
                android.support.v4.app.ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, 0);
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                    if (!SPManager.getInstance().getBoolean("isShangchuan", false)) {
                        getPhoneNumberFromMobile();
                    }
                }
//            }

        }
    }
    private void setAlias() {

        String alias= SPManager.getInstance().getString("mobilePhone",null);
        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            switch (code) {
                case 0:
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    break;
                case 6002:
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
            }
        }
    };

    private void showNotification(JiGuang.Extras extras,Class mClass){
        Intent resultIntent = new Intent(GuardApplication.getContent(), mClass);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(GuardApplication.getContent());
        stackBuilder.addParentStack(mClass);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder= (NotificationCompat.Builder)new NotificationCompat.Builder(MainActivity.this)
                .setSmallIcon(R.mipmap.ic_launcher)   //若没有设置largeicon，此为左边的大icon，设置了largeicon，则为右下角的小icon，无论怎样，都影响Notifications area显示的图标
                .setContentTitle(extras.getTitle()) //标题
                .setContentText(extras.getCount())         //正文
//                            .setNumber(3)                       //设置信息条数
                .setDefaults(Notification.DEFAULT_SOUND)//设置声音，此为默认声音
                .setVibrate(vT)
                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1000, mBuilder.build());
    }


    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    // 调用 JPush 接口来设置别名。
//                    JPushInterface.setAlias(MainActivity.this,UserManager.getInstance().getUser().getObj().getMobilePhone(),mAliasCallback);

                    JPushInterface.setAliasAndTags(getApplicationContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
            }
        }
    };


    private void shangChuan(){

        String url= XY_Response.URL_ADDPHONELIST;
//        +"cmemberId="+
//                UserManager.getInstance().getUser().getObj().getC_memberId()+"&mobilePhone="+
//                UserManager.getInstance().getUser().getObj().getMobilePhone()+"&phoneNolist="+
//                phoneInfoString;
        RequestParams params=new RequestParams();

        params.put("cmemberId",String.valueOf(UserManager.getInstance().getUser().getObj().getC_memberId()));
        params.put("mobilePhone",UserManager.getInstance().getUser().getObj().getMobilePhone());
        params.put("phoneNolist",phoneInfoString);


        RequestCenter.addPhoneList(url,params, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                FanHui fanHui= (FanHui) responseObj;
                if (fanHui.getCode().equals("1000")){
                    SPManager.getInstance().putBoolean("isShangchuan",true);
                }
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });



    }


    //检查更新代码
    private void checkVersion() {
        DialogUntil.showLoadingDialog(MainActivity.this, "正在检查更新", true);
        RequestCenter.checkVersion(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();

                final UpdateVersion updateModel = (UpdateVersion) responseObj;

                if (updateModel.getCode().equals("1000")) {
                    UpdateVersion.ObjBean obj = updateModel.getObj();
                    if (Double.valueOf(Util.getVersionCode(MainActivity.this)) < Double.valueOf(obj.getVerSn())) {
                        if (!SPManager.getInstance().getBoolean("isOne", false)) {
                        //说明有新版本,开始下载
                        CommonDialog dialog = new CommonDialog(MainActivity.this,
                                getString(R.string.update_new_version),
                                obj.getVerDescript(),

                                getString(R.string.cancel),
                                getString(R.string.update_install),
                                new CommonDialog.DialogClickListener() {
                                    @Override
                                    public void onDialogClick() {
                                        Intent intent = new Intent(MainActivity.this, UpdateService.class);
                                        intent.putExtra("APPURL", updateModel.getObj().getVerUrl());
                                        MainActivity.this.startService(intent);
                                    }
                                });
                        dialog.setCancelable(false);

                        dialog.show();
                            
                        }
                    }
                    if (updateModel.getObj().getVer_force()==0){
                        SPManager.getInstance().putBoolean("isOne", true);
                    }
                    if (updateModel.getObj().getVer_force()==1){
                        SPManager.getInstance().putBoolean("isOne", false);
                    }

                }
            }

            @Override
            public void onFailure(Object reasonObj) {
            }
        });
    }

    private static final int PERMISSON_REQUESTCODE = 0;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;




    /**
     * 检查权限
     */
    public void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否有的权限都已经授权
     *
     * @param grantResults
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }

    /**
     * 弹出对话框, 提示用户手动授权
     *
     */
    private void showMissingPermissionDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.notifyTitle);
        builder.setMessage(R.string.notifyMsg);

        // 拒绝授权  退出应用
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        //同意授权
        builder.setPositiveButton(R.string.setting,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }

    /**
     * 启动应用的设置
     *
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

}

