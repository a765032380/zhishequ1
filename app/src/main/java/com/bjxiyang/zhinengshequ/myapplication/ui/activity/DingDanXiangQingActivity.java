package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.DingDanXiangQingItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class DingDanXiangQingActivity extends MySwipeBackActivity {

    private RelativeLayout iv_dingdanxiangqing_fanhui;
    private ImageView tv_dingdanxiangqing_tel;
    private TextView tv_dingdanxiangqing_paystatus;
    private TextView tv_dingdanxiangqing_paytime;
    private TextView tv_genxie_dianming;
    private TextView tv_item_dingdanxiangqing_shopname;
    private TextView tv_item_dingdanxiangqing_count;
    private ListView lv_dingdanxiangqing;
    private TextView tv_dingdanxiangqing_money;
    private TextView tv_dingdanxiangqing_youhui;
    private TextView tv_dingdanxiangqing_zongji;
    private TextView tv_item_dingdanxiangqing_peisongshijian;
    private TextView tv_item_dingdanxiangqing_dingdanhao;
    private TextView tv_item_dingdanxiangqing_xiadanshijian;
    private TextView tv_item_dingdanxiangqing_zhifufangshi;
    private TextView tv_item_dingdanxiangqing_peisongdizhi;


    private List mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdanxiangqing);
        initUI();
    }

    private void initUI() {
        iv_dingdanxiangqing_fanhui= (RelativeLayout) findViewById(R.id.iv_dingdanxiangqing_fanhui);
        tv_dingdanxiangqing_tel= (ImageView) findViewById(R.id.tv_dingdanxiangqing_tel);
        tv_dingdanxiangqing_paystatus= (TextView) findViewById(R.id.tv_dingdanxiangqing_paystatus);
        tv_dingdanxiangqing_paytime= (TextView) findViewById(R.id.tv_dingdanxiangqing_paytime);
        tv_genxie_dianming= (TextView) findViewById(R.id.tv_genxie_dianming);
        tv_item_dingdanxiangqing_shopname= (TextView) findViewById(R.id.tv_item_dingdanxiangqing_shopname);
        tv_item_dingdanxiangqing_count= (TextView) findViewById(R.id.tv_item_dingdanxiangqing_count);
        lv_dingdanxiangqing= (ListView) findViewById(R.id.lv_dingdanxiangqing);
        tv_dingdanxiangqing_money= (TextView) findViewById(R.id.tv_dingdanxiangqing_money);
        tv_dingdanxiangqing_youhui= (TextView) findViewById(R.id.tv_dingdanxiangqing_youhui);
        tv_dingdanxiangqing_zongji= (TextView) findViewById(R.id.tv_dingdanxiangqing_zongji);
        tv_item_dingdanxiangqing_peisongshijian= (TextView) findViewById(R.id.tv_item_dingdanxiangqing_peisongshijian);
        tv_item_dingdanxiangqing_dingdanhao= (TextView) findViewById(R.id.tv_item_dingdanxiangqing_dingdanhao);
        tv_item_dingdanxiangqing_xiadanshijian= (TextView) findViewById(R.id.tv_item_dingdanxiangqing_xiadanshijian);
        tv_item_dingdanxiangqing_zhifufangshi= (TextView) findViewById(R.id.tv_item_dingdanxiangqing_zhifufangshi);
        tv_item_dingdanxiangqing_peisongdizhi= (TextView) findViewById(R.id.tv_item_dingdanxiangqing_peisongdizhi);
        mList=new ArrayList();
        DingDanXiangQingItemAdapter adapter=new DingDanXiangQingItemAdapter(DingDanXiangQingActivity.this,mList);
        lv_dingdanxiangqing.setAdapter(adapter);
    }
}
