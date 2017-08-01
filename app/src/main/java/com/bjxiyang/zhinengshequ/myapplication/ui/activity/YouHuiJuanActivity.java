package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.YouHuiQuan;
import com.bjxiyang.zhinengshequ.myapplication.bianlidianstatus.BianLiDianStatus;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.BianLiDianResponse;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.ZheKouQuanAdapter;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.YouHuiJuanAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13 0013.
 */

public class YouHuiJuanActivity extends MySwipeBackActivity {
    private RelativeLayout iv_gerenxinxi_fanhui;
    private ListView lv_youhuiquan;
    private YouHuiJuanAdapter adapter;
    private List<YouHuiQuan.ResultBean> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youhuiquan);
        initUI();
        initDate();
    }

    private void initDate() {
        String url_youhuiquan= BianLiDianResponse.URL_ORDER_COUPON_LIST;
        RequestCenter.order_coupon_list(url_youhuiquan, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                YouHuiQuan youhuiquan= (YouHuiQuan) responseObj;
                if (youhuiquan.getCode() == BianLiDianStatus.STATUS_CODE_SUCCESS){
                    mList=youhuiquan.getResult();
                    adapter=new YouHuiJuanAdapter(YouHuiJuanActivity.this,mList);
                    lv_youhuiquan.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });
    }

    private void initUI() {
        iv_gerenxinxi_fanhui= (RelativeLayout) findViewById(R.id.iv_gerenxinxi_fanhui);
        iv_gerenxinxi_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv_youhuiquan= (ListView) findViewById(R.id.lv_youhuiquan);
    }
//    private List<String> getDate(){
//        mList=new ArrayList<>();
//        for (int i=0;i<10;i++){
////            for (int j=0;j<6;j++){
//                mList.add("通用红包"+i);
//                mList.add("12"+i);
//                mList.add("有效期至2017.05.23"+i);
//                mList.add("满100可用"+i);
//                mList.add("限20:00-23:00、夜宵频道使用。"+i);
//                mList.add("仅限13333333333"+i);
////            }
//
//        }
//        return mList;
//    }
}
