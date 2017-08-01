package com.bjxiyang.zhinengshequ.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.DialogDiZhiAdapter;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DiZhiList;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.YouHuiQuan;
import com.bjxiyang.zhinengshequ.myapplication.bianlidianstatus.BianLiDianStatus;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.BianLiDianResponse;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.YouHuiJuanActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.ZheKouQuanActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.ZheKouQuanAdapter;
import com.bjxiyang.zhinengshequ.myapplication.until.DateUtils;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.YouHuiJuanAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class YouHuiQuanDialog extends Dialog
        implements AdapterView.OnItemClickListener {
    private RelativeLayout rl_shouhuopdizhi_fanghui;
    private ListView dialog_listView_dizhi;
    private TextView lv_add_shouhuodizhi;
    private RelativeLayout rl_shouhuodizhi_guanli;

    private ListView lv_youhuiquan;
    private ZheKouQuanAdapter adapter;
    private List<YouHuiQuan.ResultBean> mList;

    private OngetData ongetData;
//    private List<DiZhiList.ResultBean> mList;
int headerViewsCount;
    private int id;
    private double jiage;

    public YouHuiQuanDialog(@NonNull Context context,int sellerId,double jiage) {
        super(context);
        id=sellerId;
        this.jiage=jiage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_youhuiquan);
        initUI();
        initData();
    }

    private void initData() {
        headerViewsCount = lv_youhuiquan.getHeaderViewsCount();
        DialogUntil.showLoadingDialog(getContext(),"正在加载",true);
        mList=new ArrayList<>();
        String url_youhuiquan= BianLiDianResponse.URL_ORDER_COUPON_LIST;
//                +"sellerId="+id;
        RequestCenter.order_coupon_list(url_youhuiquan, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                YouHuiQuan youhuiquan= (YouHuiQuan) responseObj;
                if (youhuiquan.getCode() == BianLiDianStatus.STATUS_CODE_SUCCESS){
                    mList=youhuiquan.getResult();

                    for (int i=mList.size()-1;i>=0;i--){
                        Date startdate=null;
                        Date enddate=null;
                        //优惠券开始时间
                        if (mList.get(i).getStartDate().equals("")){
                            startdate = DateUtils.getDate(mList.get(i).getStartDate());
                        }
                        if (mList.get(i).getEndDate().equals("")) {
                            enddate = DateUtils.getDate(mList.get(i).getEndDate());
                        }
                        Date date = DateUtils.getDate(DateUtils.getCurrentTime_Today());
                       
                       
                        //优惠券截止时间
                       
                        //当前时间
                       
                        
//                        if (date.before(startdate)||date.after(enddate)){
//                            mList.remove(i);
//                            continue;
//                        }
                        if (mList.get(i).getStatus()==0){
                            mList.remove(i);
                            continue;
                        }
                        if (mList.get(i).getSellerId()!=id){
                            mList.remove(i);
                            continue;
                        }
                        if (mList.get(i).getMinConsume()/100>jiage){
                            mList.remove(i);
                            continue;
                        }
                    }

                    TextView textView=new TextView(getContext());
                    textView.setMinimumHeight(130);
                    textView.setTextSize(18);
                    textView.setBackgroundResource(R.color.color_eeeeee);

                    textView.setGravity(Gravity.CENTER);
                    if (mList.size()<0){
                        textView.setText("无可用优惠券");
                        lv_youhuiquan.addHeaderView(textView);
                    }else {
                        textView.setText("不使用优惠券");
                        lv_youhuiquan.addHeaderView(textView);
                    }
                    adapter=new ZheKouQuanAdapter(getContext(),mList);
                    lv_youhuiquan.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();

            }
        });

    }

    private void initUI() {
        lv_youhuiquan= (ListView) findViewById(R.id.lv_youhuiquan);


        lv_youhuiquan.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mList.size()>0) {
            if (position==0){
                YouHuiQuan.ResultBean resultBean=null;
                ongetData.getYouhuiquan(resultBean);
                cancel();
            }else {
                ongetData.getYouhuiquan(mList.get(position-1));
                cancel();
            }
        }else {
            YouHuiQuan.ResultBean resultBean=null;
            ongetData.getYouhuiquan(resultBean);
            cancel();
        }
    }
    public void setOngetYouhuiquan(OngetData ongetdata){
        this.ongetData=ongetdata;
    }

    public interface OngetData{
        public void getYouhuiquan(YouHuiQuan.ResultBean dizhiId);
    }

}
