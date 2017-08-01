package com.bjxiyang.zhinengshequ.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.DialogDiZhiAdapter;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DiZhiList;
import com.bjxiyang.zhinengshequ.myapplication.bianlidianstatus.BianLiDianStatus;
import com.bjxiyang.zhinengshequ.myapplication.model.OrderWeiXin;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.BianLiDianResponse;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.AddShouHuoDiZhiActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.MySwipeBackActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.ShouHuoDiZhiAdapter;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class ShouHuoDiZhiDialog extends Dialog
        implements AdapterView.OnItemClickListener {
    private RelativeLayout rl_shouhuopdizhi_fanghui;
    private ListView dialog_listView_dizhi;
    private TextView lv_add_shouhuodizhi;
    private RelativeLayout rl_shouhuodizhi_guanli;
    private DialogDiZhiAdapter adapter;

    private OngetData ongetData;
    private List<DiZhiList.ResultBean> mList;


    public ShouHuoDiZhiDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_list_view_dizhi);
        initUI();
        initData();
    }

    private void initData() {
        DialogUntil.showLoadingDialog(getContext(),"正在加载",true);
        mList=new ArrayList<>();
        String url_dizhi= BianLiDianResponse.URL_ORDER_USER_ADDRESS_LIST;
        RequestCenter.order_user_address_list(url_dizhi, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                DiZhiList diZhiList= (DiZhiList) responseObj;
                if (diZhiList.getCode()== BianLiDianStatus.STATUS_CODE_SUCCESS){
                    mList=diZhiList.getResult();
                    if (mList.size()>0) {
                        adapter = new DialogDiZhiAdapter(getContext(), mList);
                        dialog_listView_dizhi.setAdapter(adapter);
                    }else {
                        MyUntil.show(getContext(),"请添加收货地址");
                        cancel();
                    }
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();

            }
        });

    }

    private void initUI() {
        dialog_listView_dizhi= (ListView) findViewById(R.id.dialog_listView_dizhi);
        dialog_listView_dizhi.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mList.size()>0) {
            ongetData.getDizhiId(mList.get(position));
            cancel();
        }else {
            DiZhiList.ResultBean dizhi=null;
            ongetData.getDizhiId(dizhi);
            cancel();
        }
    }
    public void setOngetdata(OngetData ongetdata){
        this.ongetData=ongetdata;
    }

    public interface OngetData{
        public void getDizhiId(DiZhiList.ResultBean dizhiId);
    }

}
