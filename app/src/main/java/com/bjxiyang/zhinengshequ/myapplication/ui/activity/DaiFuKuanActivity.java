package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DingDan;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.Text;
import com.bjxiyang.zhinengshequ.myapplication.bianlidianstatus.BianLiDianStatus;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.BianLiDianResponse;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.DaiFuKuanAdapter;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/1 0001.
 */

public class DaiFuKuanActivity extends MySwipeBackActivity implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener{
    private RelativeLayout iv_daifukuan_fanhui;
    private ListView lv_daifukuan;
    private TextView dingdan_title;
    private TextView wushuju;

    private SwipeRefreshLayout swipeRefreshLayout1;
    private SwipeRefreshLayout swipeRefreshLayout2;
    private SwipeRefreshLayout swipeRefreshLayout3;
    private LinearLayout ll_daishouhuo_nodata;
    private LinearLayout ll_tuikuan_nodata;


    private List<DingDan.ResultBean> mList;
    private int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daifukuan);
        Intent intent=getIntent();
        type=intent.getIntExtra("type",-1);
        initUI();
        initData();
        getData();
    }

    private void initData() {
        switch (type){
            case 0:
                dingdan_title.setText("全部订单");
                break;
            case 1:
                dingdan_title.setText("待付款");
                break;
            case 2:
                dingdan_title.setText("待发货");
                break;
            case 3:
                dingdan_title.setText("待收货");
                break;
            case 4:
                dingdan_title.setText("退款/退货");
                break;
        }
    }

    private void initUI() {
        swipeRefreshLayout1= (SwipeRefreshLayout) findViewById(R.id.show_list_view);
        swipeRefreshLayout2= (SwipeRefreshLayout) findViewById(R.id.wushuju_sw);
        swipeRefreshLayout3= (SwipeRefreshLayout) findViewById(R.id.wushuju_sw_tuikuan);
        swipeRefreshLayout1.setOnRefreshListener(this);
        swipeRefreshLayout2.setOnRefreshListener(this);
        swipeRefreshLayout3.setOnRefreshListener(this);
        ll_daishouhuo_nodata= (LinearLayout) findViewById(R.id.ll_daishouhuo_nodata);
        ll_tuikuan_nodata= (LinearLayout) findViewById(R.id.ll_tuikuan_nodata);
        wushuju= (TextView) findViewById(R.id.wushuju);
        dingdan_title= (TextView) findViewById(R.id.dingdan_title);
        iv_daifukuan_fanhui= (RelativeLayout) findViewById(R.id.iv_daifukuan_fanhui);
        iv_daifukuan_fanhui.setOnClickListener(this);
        lv_daifukuan= (ListView) findViewById(R.id.lv_daifukuan);
//        DaiFuKuanAdapter adapter=new DaiFuKuanAdapter(DaiFuKuanActivity.this,mList);
//        lv_daifukuan.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_daifukuan_fanhui:
                finish();
                break;
        }
    }

    private void getData(){
        mList=new ArrayList<>();
        DialogUntil.showLoadingDialog(DaiFuKuanActivity.this,"正在加载",true);
        String url_all_dingdan= BianLiDianResponse.URL_ORDER_LIST+"type="+type;
        RequestCenter.order_list(url_all_dingdan, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                DingDan dingDan= (DingDan) responseObj;
                if (dingDan.getCode()== BianLiDianStatus.STATUS_CODE_SUCCESS){
                    mList=dingDan.getResult();

                    if (mList.size()>0){
                        DaiFuKuanAdapter adapter=new DaiFuKuanAdapter(DaiFuKuanActivity.this,mList,type);
                        lv_daifukuan.setAdapter(adapter);
                        showListView();
                    }else {
                        Log.i("YYYY",type+"---");
                        if (type==4){
                            showwushuju_tuikuan();
                        }else {
                            showwushuju();
                        }
                        showTishi();
                    }

                }else {
                    MyUntil.show(DaiFuKuanActivity.this,dingDan.getMsg());
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
            }
        });
    }
    private void showTishi(){
        lv_daifukuan.setVisibility(View.GONE);
        switch (type){
            case 0:
                ll_daishouhuo_nodata.setVisibility(View.VISIBLE);
                wushuju.setText("当前无订单");
                break;
            case 1:
                ll_daishouhuo_nodata.setVisibility(View.VISIBLE);
                wushuju.setText("当前无待付款订单");
                break;
            case 2:
                ll_daishouhuo_nodata.setVisibility(View.VISIBLE);
                wushuju.setText("当前无代发货订单");
                break;
            case 3:
                ll_daishouhuo_nodata.setVisibility(View.VISIBLE);
                wushuju.setText("当前无待收货订单");
                break;
            case 4:
                ll_tuikuan_nodata.setVisibility(View.VISIBLE);
                break;


        }

    }

    @Override
    public void onRefresh() {
        getData();
        swipeRefreshLayout1.setRefreshing(false);
        swipeRefreshLayout2.setRefreshing(false);
        swipeRefreshLayout3.setRefreshing(false);
    }
    private void showListView(){
        swipeRefreshLayout1.setVisibility(View.VISIBLE);
        swipeRefreshLayout2.setVisibility(View.GONE);
        swipeRefreshLayout3.setVisibility(View.GONE);
    }
    private void showwushuju(){
        swipeRefreshLayout1.setVisibility(View.GONE);
        swipeRefreshLayout2.setVisibility(View.VISIBLE);
        swipeRefreshLayout3.setVisibility(View.GONE);
    }
    private void showwushuju_tuikuan(){
        swipeRefreshLayout1.setVisibility(View.GONE);
        swipeRefreshLayout2.setVisibility(View.GONE);
        swipeRefreshLayout3.setVisibility(View.VISIBLE);
    }


}
