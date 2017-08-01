package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.UpdateVersion;
import com.bjxiyang.zhinengshequ.myapplication.model.WuYeJiaoFei;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.WuYeJiaoFeiAdapter;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.CommonDialog;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.update.service.UpdateService;
import com.bjxiyang.zhinengshequ.myapplication.update.util.Util;
import com.bjxiyang.zhinengshequ.myapplication.view.MyDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class WuYeJiaoFeiActivity extends MySwipeBackActivity
        implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener{

    private RelativeLayout iv_wuyejiaofei_fanhui;
    private ListView lv_wuyejiaofei;
    private LinearLayout ll_activity_wuxiangmu;
    private LinearLayout ll_wuyejiaofei_listview;
    private LinearLayout ll_activity_wuwangluo;
    private WuYeJiaoFeiAdapter adapter;
    private List<WuYeJiaoFei.Obj> mList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshLayout swipeRefreshLayout1;
    private SwipeRefreshLayout swipeRefreshLayout2;
    private LinearLayout ll_activity_banbentishi;


    private static AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuyejiaofei);
        initUI();
//        if (Util.getVersionCode(WuYeJiaoFeiActivity.this)==1){
//            showBanBen();
//            }else {
                getData();
//            }

        }
    private void initUI() {
        ll_activity_banbentishi= (LinearLayout) findViewById(R.id.ll_activity_banbentishi);
        ll_activity_wuxiangmu= (LinearLayout) findViewById(R.id.ll_activity_wuxiangmu);
        ll_activity_wuwangluo= (LinearLayout) findViewById(R.id.ll_activity_wuwangluo);
        iv_wuyejiaofei_fanhui= (RelativeLayout) findViewById(R.id.iv_wuyejiaofei_fanhui);
        iv_wuyejiaofei_fanhui.setOnClickListener(this);
        lv_wuyejiaofei= (ListView) findViewById(R.id.lv_wuyejiaofei);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.color_898787);
        swipeRefreshLayout1= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout1);
        swipeRefreshLayout1.setOnRefreshListener(this);
        swipeRefreshLayout1.setColorSchemeResources(R.color.color_898787);
        swipeRefreshLayout2= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout2);
        swipeRefreshLayout2.setOnRefreshListener(this);
        swipeRefreshLayout2.setColorSchemeResources(R.color.color_898787);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_wuyejiaofei_fanhui:
                finish();
                break;
        }
    }

    private List<WuYeJiaoFei.Obj> getData(){
        mList=new ArrayList<>();
        DialogUntil.showLoadingDialog(this,"正在加载",true);
        String url= XY_Response.URL_GETPROPERTYLIST+"cmemberId="+
                UserManager.getInstance().getUser().getObj().getC_memberId();
        RequestCenter.getPropertyList(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                WuYeJiaoFei wuYeJiaoFei= (WuYeJiaoFei) responseObj;
                if (wuYeJiaoFei.getCode().equals("1000")){
                    mList=wuYeJiaoFei.getObj();
                    if (mList.size()>0){
                        adapter=new WuYeJiaoFeiAdapter(WuYeJiaoFeiActivity.this,mList);
                        lv_wuyejiaofei.setAdapter(adapter);
                        showListView();
                    }else {
                        showWuJiLu();
                    }
                }else {
                    showWuJiLu();
                    MyUntil.show(WuYeJiaoFeiActivity.this,wuYeJiaoFei.getMsg());
                }

            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
                showWuWangLuo();
//                MyDialog.showDialog(WuYeJiaoFeiActivity.this);

            }
        });
        return mList;
    }
    private void showListView(){
        ll_activity_banbentishi.setVisibility(View.GONE);
        swipeRefreshLayout1.setVisibility(View.GONE);
        swipeRefreshLayout2.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }
    private void showWuWangLuo(){
        ll_activity_banbentishi.setVisibility(View.GONE);
        swipeRefreshLayout2.setVisibility(View.VISIBLE);
        swipeRefreshLayout1.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }
    private void showWuJiLu(){
        ll_activity_banbentishi.setVisibility(View.GONE);
        swipeRefreshLayout2.setVisibility(View.GONE);
        swipeRefreshLayout1.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }
    private void showBanBen(){
        ll_activity_banbentishi.setVisibility(View.VISIBLE);
        swipeRefreshLayout2.setVisibility(View.GONE);
        swipeRefreshLayout1.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }
    @Override
    public void onRefresh() {
        getData();
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout1.setRefreshing(false);
        swipeRefreshLayout2.setRefreshing(false);
    }

    private void checkVersion() {
        DialogUntil.showLoadingDialog(WuYeJiaoFeiActivity.this, "正在检查更新", true);
        RequestCenter.checkVersion(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();

                final UpdateVersion updateModel = (UpdateVersion) responseObj;

                if (updateModel.getCode().equals("1000")) {
                    UpdateVersion.ObjBean obj = updateModel.getObj();
                    if (Double.valueOf(Util.getVersionCode(WuYeJiaoFeiActivity.this)) < Double.valueOf(obj.getVerSn())) {
                        if (!SPManager.getInstance().getBoolean("isOne", false)) {
                            //说明有新版本,开始下载
                            CommonDialog dialog = new CommonDialog(WuYeJiaoFeiActivity.this,
                                    getString(R.string.update_new_version),
                                    obj.getVerDescript(),

                                    getString(R.string.cancel),
                                    getString(R.string.update_install),
                                    new CommonDialog.DialogClickListener() {
                                        @Override
                                        public void onDialogClick() {
                                            Intent intent = new Intent(WuYeJiaoFeiActivity.this, UpdateService.class);
                                            intent.putExtra("APPURL", updateModel.getObj().getVerUrl());
                                            WuYeJiaoFeiActivity.this.startService(intent);
                                        }
                                    });
                            dialog.setCancelable(false);

                            dialog.show();

                        }
                    }
                    if (updateModel.getObj().getVer_force()==0){
                        SPManager.getInstance().putBoolean("isOne", true);
                    }
                    if (updateModel.getObj().getVer_force()==0){
                        SPManager.getInstance().putBoolean("isOne", false);
                    }

                }
            }

            @Override
            public void onFailure(Object reasonObj) {
            }
        });
    }

}
