package com.bjxiyang.zhinengshequ.myapplication.dialog;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.baisi.myapplication.okhttp.request.RequestParams;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.model.Phoneinfo;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.MainActivity;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/4 0004.
 */

public class MyQuanXianDialog extends Dialog {
    private TextView tv_permission_btn;

    private static final String[] PHONES_PROJECTION = new String[] {
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER};
    private String phoneInfoString;
    private List<Phoneinfo> list;
    private MainActivity mainActivity;

    protected String[] needPermissions = {
//            Manifest.permission.ACCESS_COARSE_LOCATION,//定位权限
//            Manifest.permission.ACCESS_FINE_LOCATION,//定位权限
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,//存储卡写入权限
            Manifest.permission.READ_EXTERNAL_STORAGE,//存储卡读取权限
            Manifest.permission.READ_PHONE_STATE//读取手机状态权限

    };
    private static final int PERMISSON_REQUESTCODE = 0;
    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;


    public MyQuanXianDialog(@NonNull Context context) {
        super(context,R.style.dialog);
    }
    public MyQuanXianDialog(@NonNull Context context, MainActivity mainActivity) {
        super(context,R.style.dialog);
        this.mainActivity=mainActivity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_permission);
        initUI();
    }
    private void initUI() {
        tv_permission_btn= (TextView) findViewById(R.id.tv_permission_btn);
        tv_permission_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPManager.getInstance().putBoolean("isQuanXian",true);
                mainActivity.checkPermissions(needPermissions);
//                getQuanXian();
                cancel();

            }
        });
    }

    //动态权限的封装
    private void getQuanXian(){

        if(ContextCompat.checkSelfPermission(mainActivity,
                Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(mainActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(mainActivity,
                Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){

            android.support.v4.app.ActivityCompat.requestPermissions(mainActivity,
                    new String[]{Manifest.permission.READ_CONTACTS,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA}, 0);
        }
    }
    public String getPhoneNumberFromMobile() {
        // TODO Auto-generated constructor stub
        list = new ArrayList<>();
        Cursor cursor = getContext().getContentResolver()
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
}
