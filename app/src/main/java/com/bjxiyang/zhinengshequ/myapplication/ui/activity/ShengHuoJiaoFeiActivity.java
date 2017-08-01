package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.SelectPlot;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.widgets.CommonActionSheetDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class ShengHuoJiaoFeiActivity extends MySwipeBackActivity implements View.OnClickListener{

    public static final int SHUIFEI=1;
    public static final int DIANFEI=2;
    public static final int RANQIFEI=3;
    public static final int KUANDAIFEI=4;
    public static final int WUYEFEI=5;


    /***
     * UI
     */
    private LinearLayout ll_address;
    private LinearLayout ll_shuifei;
    private LinearLayout ll_dianfei;
    private LinearLayout ll_ranqifei;
    private LinearLayout ll_kuandaifei;
    private LinearLayout ll_wuyefei;
    private TextView tv_shopname;
    private RelativeLayout iv_shenghuojiaofei_fanhui;


    public CommonActionSheetDialog commonActionSheetDialog;
    private List<SelectPlot.Obj> mList_plot;
    private int communityId;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shenghuojiaofei);
        initUI();
    }

    private void initUI() {
        iv_shenghuojiaofei_fanhui= (RelativeLayout) findViewById(R.id.iv_shenghuojiaofei_fanhui);
        tv_shopname= (TextView) findViewById(R.id.tv_shopname);
        ll_address= (LinearLayout) findViewById(R.id.ll_address);
        ll_shuifei= (LinearLayout) findViewById(R.id.ll_shuifei);
        ll_dianfei= (LinearLayout) findViewById(R.id.ll_dianfei);
        ll_ranqifei= (LinearLayout) findViewById(R.id.ll_ranqifei);
        ll_kuandaifei= (LinearLayout) findViewById(R.id.ll_kuandaifei);
        ll_wuyefei= (LinearLayout) findViewById(R.id.ll_wuyefei);
        iv_shenghuojiaofei_fanhui.setOnClickListener(this);
        ll_address.setOnClickListener(this);
        ll_shuifei.setOnClickListener(this);
        ll_dianfei.setOnClickListener(this);
        ll_ranqifei.setOnClickListener(this);
        ll_kuandaifei.setOnClickListener(this);
        ll_wuyefei.setOnClickListener(this);

    }

    private void getData(){
        mList_plot=new ArrayList<>();
        DialogUntil.showLoadingDialog(ShengHuoJiaoFeiActivity.this,"正在加载",true);
        String url= XY_Response.URL_FINDCOMMUNITYBYPER+"mobilePhone="+
                UserManager.getInstance().getUser().getObj().getMobilePhone();

        RequestCenter.findCommunityByPer(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                SelectPlot selectPlot= (SelectPlot) responseObj;
                DialogUntil.closeLoadingDialog();
                if (selectPlot.getCode().equals("1000")){
                    mList_plot = selectPlot.getObj();
                    if (mList_plot==null){
                        MyUntil.show(ShengHuoJiaoFeiActivity.this,"请选择小区");
//                        Intent intent = new Intent(getContext(),XYXuanZeXiaoQuActivity.class);
//                        startActivity(intent);
                    }else {
                        showActionSheet(mList_plot);
                    }

                }else {
//                    Toast.makeText(getActivity(),selectPlot.getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
            }
        });
    }
    public void showActionSheet(final List<SelectPlot.Obj> mList)
    {
        if (mList.size()>0){
            commonActionSheetDialog = new CommonActionSheetDialog(ShengHuoJiaoFeiActivity.this);





            for (int i=0;i<mList.size();i++) {
//                    for (int j=0;j<i;j++){
//                        if (!(mList.get(i).getCommunityName()+mList.get(i).getNperName())
//                                .equals((mList.get(j).getCommunityName()+mList.get(j).getNperName())))
//                        {
//                            commonActionSheetDialog.addMenuItem(mList.get(i).getCommunityName()
//                                    + mList.get(i).getNperName());
//                        }
//                        else {
//                            commonActionSheetDialog.addMenuItem(mList.get(i).getCommunityName()
//                                    + mList.get(i).getNperName());
//                        }
//
//                    }


                commonActionSheetDialog.addMenuItem(mList.get(i).getCommunityName()
                        + mList.get(i).getNperName() +
                        mList.get(i).getUnitName() +
                        mList.get(i).getDoorName());
//                if (i>0){
//                    if (!(mList.get(i).getCommunityName()+mList.get(i).getNperName())
//                            .equals((mList.get(i-1).getCommunityName()+mList.get(i-1).getNperName())))
//                    {
//                        commonActionSheetDialog.addMenuItem(mList.get(i).getCommunityName()
//                                + mList.get(i).getNperName());
//                    }
//                }else {
//                    commonActionSheetDialog.addMenuItem(mList.get(i).getCommunityName()
//                            + mList.get(i).getNperName());
//                }
            }
//                for (SelectPlot.Obj item:mList){
//
//                    commonActionSheetDialog.addMenuItem(item.getCommunityName()
//                            +item.getNperName());
//                }
            commonActionSheetDialog.setMenuListener(new CommonActionSheetDialog.MenuListener() {
                @Override
                public void onItemSelected(int position, String item) {
                    communityId=mList.get(position).getCommunityId();
                    name=mList.get(position).getCommunityName()
                            + mList.get(position).getNperName() +
                            mList.get(position).getUnitName() +
                            mList.get(position).getDoorName();

                    tv_shopname.setText(name);
                }
                @Override
                public void onCancel() {
                }
            });
            commonActionSheetDialog.show();
        }else {
            Toast.makeText(ShengHuoJiaoFeiActivity.this,"当前数据为空，请添加小区",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_shenghuojiaofei_fanhui:
                finish();
                break;
            case R.id.ll_address:
                getData();
                break;
            case R.id.ll_shuifei:
                startActivity(SHUIFEI);
                break;
            case R.id.ll_dianfei:
                startActivity(DIANFEI);
                break;
            case R.id.ll_ranqifei:
                startActivity(RANQIFEI);
                break;
            case R.id.ll_kuandaifei:
                startActivity(KUANDAIFEI);
                break;
            case R.id.ll_wuyefei:
                startActivity(WUYEFEI);
                break;

        }
    }
    private void startActivity(int type){
        Intent intent=new Intent(ShengHuoJiaoFeiActivity.this,ShengHuoPayActivity.class);
        intent.putExtra("type",type);
        startActivity(intent);
    }
}
