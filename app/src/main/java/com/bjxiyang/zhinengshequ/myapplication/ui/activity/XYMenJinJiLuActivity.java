package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.OpenDoorList;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.ui.myview.RefreshLayout;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;

import java.util.ArrayList;
import java.util.List;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.XYMenJinJiLuAdapter;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;

/**
 * Created by gll on 17-5-23.
 */

public class XYMenJinJiLuActivity extends MySwipeBackActivity
        implements
        SwipeRefreshLayout.OnRefreshListener
,RefreshLayout.OnLoadListener
{
    private RelativeLayout ib_menjinjilu_fanghui;
    private ListView lv_menjinjilu;
    private XYMenJinJiLuAdapter adapter;
    private List<OpenDoorList.Obj> mList;
    private List<OpenDoorList.Obj> mListAll;
    private LinearLayout ll_activity_wujilu;
    private LinearLayout ll_activity_listview;
    private LinearLayout ll_activity_wuwangluo;
    private RefreshLayout swipeRefreshLayout;
    private SwipeRefreshLayout swipeRefreshLayout1;
    private SwipeRefreshLayout swipeRefreshLayout2;

    private boolean isScoll=true;


    /**
     * 上拉加载更多
     */
    private int pageNo=1;
    private int pageSize=10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xy_activity_menjinjilu);
        initUI();

    }

    private void initUI() {
        mListAll=new ArrayList<>();

        ll_activity_wuwangluo= (LinearLayout) findViewById(R.id.ll_activity_wuwangluo);
        ll_activity_wujilu= (LinearLayout) findViewById(R.id.ll_activity_wujilu);

        ib_menjinjilu_fanghui= (RelativeLayout) findViewById(R.id.ib_menjinjilu_fanghui);
        ib_menjinjilu_fanghui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv_menjinjilu= (ListView) findViewById(R.id.lv_menjinjilu);
        swipeRefreshLayout= (RefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.color_898787);
        swipeRefreshLayout1= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout1);
        swipeRefreshLayout1.setOnRefreshListener(this);
        swipeRefreshLayout1.setColorSchemeResources(R.color.color_898787);
        swipeRefreshLayout2= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout2);
        swipeRefreshLayout2.setOnRefreshListener(this);
        swipeRefreshLayout2.setColorSchemeResources(R.color.color_898787);

        swipeRefreshLayout.setOnLoadListener(this);
        getData();
//        swipeRefreshLayout.post(new Thread(new Runnable() {
//            @Override
//            public void run() {
//                swipeRefreshLayout.setRefreshing(true);
//            }
//        }));
//        onRefresh();
    }

    private void getData() {
        mList=new ArrayList<>();
        DialogUntil.showLoadingDialog(XYMenJinJiLuActivity.this,"正在加载",true);

        String url= XY_Response.URL_OPENDOORLIST+"customberId="+
                UserManager.getInstance().getUser().getObj().getC_memberId()+
                "&pageNo="+pageNo+"&pageSize="+pageSize;

        RequestCenter.openDoorList(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                OpenDoorList openDoorList= (OpenDoorList) responseObj;
                DialogUntil.closeLoadingDialog();
                if (openDoorList.getCode().equals("1000")){
                    mList=openDoorList.getObj();

                    if (mList.size()>0){
                        mListAll=mList;
                        adapter=new XYMenJinJiLuAdapter(XYMenJinJiLuActivity.this,mListAll);
                        lv_menjinjilu.setAdapter(adapter);
                        showListView();
                    }else {
                        showWuJiLu();
                    }
                }else {
                    showWuJiLu();
                    Toast.makeText(XYMenJinJiLuActivity.this,openDoorList.getMsg(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Object reasonObj) {
//                MyDialog.showDialog(XYMenJinJiLuActivity.this);
                DialogUntil.closeLoadingDialog();
                showWuWangLuo();
            }
        });
    }

    private void showListView(){
        swipeRefreshLayout2.setVisibility(View.GONE);
        swipeRefreshLayout1.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }
    private void showWuWangLuo(){
        swipeRefreshLayout1.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.GONE);
        swipeRefreshLayout2.setVisibility(View.GONE);
    }
    private void showWuJiLu(){
        swipeRefreshLayout1.setVisibility(View.GONE);
        swipeRefreshLayout2.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 更新数据
                // 更新完后调用该方法结束刷新
//                getData();
                if (adapter!=null){
                    adapter.notifyDataSetChanged();
                }
            }
        }, 0);

        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout1.setRefreshing(false);
        swipeRefreshLayout2.setRefreshing(false);

    }

    @Override
    public void onLoad() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 更新数据
                // 更新完后调用该方法结束刷新
                swipeRefreshLayout.setLoading(false);
                if (isScoll){
                    pageNo=pageNo+1;
                    upData();
                }else {
                    MyUntil.show(XYMenJinJiLuActivity.this,"无更多数据");
                }
            }
        }, 1000);
    }
    private void upData() {
        mList=new ArrayList<>();
        String url= XY_Response.URL_OPENDOORLIST+"customberId="+
                UserManager.getInstance().getUser().getObj().getC_memberId()+
                "&pageNo="+pageNo+"&pageSize="+pageSize;

        RequestCenter.openDoorList(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                OpenDoorList openDoorList= (OpenDoorList) responseObj;
                DialogUntil.closeLoadingDialog();
                if (openDoorList.getCode().equals("1000")){
                    mList=openDoorList.getObj();
                    if (mList.size()<pageSize){
                        isScoll=false;
                    }
                    for (int i = 0; i < mList.size(); i++) {
                        mListAll.add(mList.get(i));
                    }
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(XYMenJinJiLuActivity.this,openDoorList.getMsg(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Object reasonObj) {
//                MyDialog.showDialog(XYMenJinJiLuActivity.this);
                DialogUntil.closeLoadingDialog();
                showWuWangLuo();
            }
        });
    }
}
