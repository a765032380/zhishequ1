package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.YouHuiQuan;
import com.bjxiyang.zhinengshequ.myapplication.bianlidianstatus.BianLiDianStatus;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.BianLiDianResponse;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.ZheKouQuanAdapter;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.YouHuiJuanAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/2 0002.
 */

public class ZheKouQuanActivity extends MySwipeBackActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RelativeLayout rl_zhekouquan_fanhui;
    private ListView lv_zhekouquan;
private SwipeRefreshLayout swipeRefreshLayout;

    /**
     *
     * Data
     */
//    private List mList;
            private ZheKouQuanAdapter adapter;

    private List<YouHuiQuan.ResultBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhekouquan);
        initUI();
        initData();
        getData();
    }

    private void getData() {
        String url_youhuiquan= BianLiDianResponse.URL_ORDER_COUPON_LIST;
        RequestCenter.order_coupon_list(url_youhuiquan, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                YouHuiQuan youhuiquan= (YouHuiQuan) responseObj;
                if (youhuiquan.getCode() == BianLiDianStatus.STATUS_CODE_SUCCESS){
                    mList=youhuiquan.getResult();
                    adapter=new ZheKouQuanAdapter(ZheKouQuanActivity.this,mList);
                    lv_zhekouquan.setAdapter(adapter);
                }else {
                    MyUntil.show(ZheKouQuanActivity.this,youhuiquan.getMsg());
                }
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });
    }

    private void initData() {
    }

    private void initUI() {
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        rl_zhekouquan_fanhui= (RelativeLayout) findViewById(R.id.rl_zhekouquan_fanhui);
        rl_zhekouquan_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv_zhekouquan= (ListView) findViewById(R.id.lv_zhekouquan);

//        mList=new ArrayList();

    }

    @Override
    public void onRefresh() {
        getData();
        swipeRefreshLayout.setRefreshing(false);
    }
}
