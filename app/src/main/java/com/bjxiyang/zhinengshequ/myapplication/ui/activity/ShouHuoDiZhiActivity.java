package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DiZhiList;
import com.bjxiyang.zhinengshequ.myapplication.bianlidianstatus.BianLiDianStatus;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.BianLiDianResponse;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.ShouHuoDiZhiAdapter;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class ShouHuoDiZhiActivity extends MySwipeBackActivity
        implements View.OnClickListener,AdapterView.OnItemClickListener,
         SwipeRefreshLayout.OnRefreshListener{
    private RelativeLayout rl_shouhuopdizhi_fanghui;
    private ListView lv_shouhuodizhi;
    private RelativeLayout lv_add_shouhuodizhi;
    private RelativeLayout rl_shouhuodizhi_guanli;
    private ShouHuoDiZhiAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<DiZhiList.ResultBean> mList;

    private Ongetdata ongetdata;

    public static ShouHuoDiZhiActivity shouHuoDiZhiActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuanzeshouhuodizhi);
        shouHuoDiZhiActivity=this;
        initUI();
//        initData();
    }

    @Override
    protected void onResume() {
        initData();
        super.onResume();
    }

    private void initData() {
        mList=new ArrayList<>();
        String url_dizhi= BianLiDianResponse.URL_ORDER_USER_ADDRESS_LIST;
        RequestCenter.order_user_address_list(url_dizhi, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DiZhiList diZhiList= (DiZhiList) responseObj;
                if (diZhiList.getCode()== BianLiDianStatus.STATUS_CODE_SUCCESS){
                    mList=diZhiList.getResult();
                    adapter=new ShouHuoDiZhiAdapter(ShouHuoDiZhiActivity.this,mList);
                    lv_shouhuodizhi.setAdapter(adapter);
                }else {
                    MyUntil.show(ShouHuoDiZhiActivity.this,diZhiList.getMsg());
                }
            }

            @Override
            public void onFailure(Object reasonObj) {


            }
        });



//        mList=new ArrayList<>();
//        mList.add("ddd");
//        adapter=new ShouHuoDiZhiAdapter(this,mList);
//        lv_shouhuodizhi.setAdapter(adapter);
    }

    private void initUI() {
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        rl_shouhuopdizhi_fanghui= (RelativeLayout) findViewById(R.id.rl_shouhuopdizhi_fanghui);
        lv_shouhuodizhi= (ListView) findViewById(R.id.lv_shouhuodizhi);
        lv_add_shouhuodizhi= (RelativeLayout) findViewById(R.id.lv_add_shouhuodizhi);
//        rl_shouhuodizhi_guanli= (RelativeLayout) findViewById(R.id.rl_shouhuodizhi_guanli);
        rl_shouhuopdizhi_fanghui.setOnClickListener(this);
        lv_add_shouhuodizhi.setOnClickListener(this);
        lv_shouhuodizhi.setOnItemClickListener(this);
//        rl_shouhuodizhi_guanli.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_shouhuopdizhi_fanghui:
                finish();
                break;
            case R.id.lv_add_shouhuodizhi:
                Intent intent=new Intent(ShouHuoDiZhiActivity.this,AddShouHuoDiZhiActivity.class);
                startActivity(intent);
                break;
//            case R.id.rl_shouhuodizhi_guanli:
//                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (ongetdata!=null){
            ongetdata.getData(mList.get(position));
            finish();
        }

//        Intent intent=new Intent(ShouHuoDiZhiActivity.this,PlaceOrderActivity.class);
//        intent.putExtra("userAddressId",mList.get(position).getId());
//        startActivity(intent);

    }
    public void setOngetdata(Ongetdata ongetdata){
        this.ongetdata=ongetdata;
    }
    interface Ongetdata{
        public void getData(DiZhiList.ResultBean data);
    }

    @Override
    public void onRefresh() {
        initData();
        swipeRefreshLayout.setRefreshing(false);
    }
}
