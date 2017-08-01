package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.model.TestChaoShiGouWu;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.ChaoShiFuKuanAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class ChaoShiFuKuanActivity extends MySwipeBackActivity implements View.OnClickListener {

    private LinearLayout ll_chaoshifukuan_wufukuanxiang;
    private LinearLayout ll_chaoshifukuan_listview;
    private LinearLayout ll_chaoshifukuan_wuwangluo;
    private RelativeLayout rl_chaoshifukuan_fanhui;
    private ImageView iv_activity_chaoshifukuan_fukuan;
    private ListView lv_chaoshifukuan;
    private LinearLayout xiangqing;
    private List<TestChaoShiGouWu> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chaoshifukuan);
        initUI();
        initDate();

    }

    private void initDate() {
        ChaoShiFuKuanAdapter adapter=new ChaoShiFuKuanAdapter(this,getData());
        lv_chaoshifukuan.setAdapter(adapter);

    }

    private void initUI() {
        rl_chaoshifukuan_fanhui= (RelativeLayout) findViewById(R.id.rl_chaoshifukuan_fanhui);
        rl_chaoshifukuan_fanhui.setOnClickListener(this);
        iv_activity_chaoshifukuan_fukuan= (ImageView) findViewById(R.id.iv_activity_chaoshifukuan_fukuan);
        lv_chaoshifukuan= (ListView) findViewById(R.id.lv_chaoshifukuan);
        iv_activity_chaoshifukuan_fukuan.setOnClickListener(this);
        xiangqing= (LinearLayout) findViewById(R.id.xiangqing);
        xiangqing.setOnClickListener(this);
        ll_chaoshifukuan_wufukuanxiang= (LinearLayout) findViewById(R.id.ll_chaoshifukuan_wufukuanxiang);
        ll_chaoshifukuan_listview= (LinearLayout) findViewById(R.id.ll_chaoshifukuan_listview);
        ll_chaoshifukuan_wuwangluo= (LinearLayout) findViewById(R.id.ll_chaoshifukuan_wuwangluo);


    }
    private List<TestChaoShiGouWu> getData(){
        mList=new ArrayList<>();
        for (int i=0;i<10;i++){
            TestChaoShiGouWu test=new TestChaoShiGouWu();
            test.setDate("2017-1-2");
            test.setTime("12:00");
            test.setName("希洋便利店");
            test.setDingdanhao("200000001000");
            test.setJiage("88");
            test.setJiaofeitime("2017-5-5");
            test.setWuyename("希洋物业公司");
            test.setZhifufangshi("支付宝");
            mList.add(test);
        }
        return mList;
    }

    private void showListView(){
        ll_chaoshifukuan_wuwangluo.setVisibility(View.GONE);
        ll_chaoshifukuan_wufukuanxiang.setVisibility(View.GONE);
        ll_chaoshifukuan_listview.setVisibility(View.VISIBLE);
    }
    private void showWuWangLuo(){
        ll_chaoshifukuan_wuwangluo.setVisibility(View.VISIBLE);
        ll_chaoshifukuan_wufukuanxiang.setVisibility(View.GONE);
        ll_chaoshifukuan_listview.setVisibility(View.GONE);
    }
    private void showWuJiLu(){
        ll_chaoshifukuan_wuwangluo.setVisibility(View.GONE);
        ll_chaoshifukuan_wufukuanxiang.setVisibility(View.VISIBLE);
        ll_chaoshifukuan_listview.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //跳转到详情
            case R.id.xiangqing:

                break;
            //返回按键
            case R.id.rl_chaoshifukuan_fanhui:
                finish();
                break;
            //付款按键
            case R.id.iv_activity_chaoshifukuan_fukuan:
                Intent intent=new Intent(ChaoShiFuKuanActivity.this,ZhiFuXiangQing.class);
                startActivity(intent);
                break;
        }
    }
}
