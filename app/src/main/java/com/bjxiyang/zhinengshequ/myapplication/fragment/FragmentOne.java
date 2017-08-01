package com.bjxiyang.zhinengshequ.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.Loan;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.XY_JinRongWebActivity;

import java.util.List;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.XY_ErshoufangAdapter;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;


/**
 * Created by gll on 17-4-26.
 */

public class FragmentOne extends Fragment implements AdapterView.OnItemClickListener
,SwipeRefreshLayout.OnRefreshListener{
    private ListView lv_ershoufan;
    private List<Loan.Obj> mList;
    private XY_ErshoufangAdapter adapter;
    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getContext(), R.layout.xy_fragment_ershoufang,null);
        initUI();
        return view;
    }
    private void initUI(){
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        lv_ershoufan= (ListView) view.findViewById(R.id.lv_ershoufan);
        getData();
        lv_ershoufan.setOnItemClickListener(this);
    }

    private List<Loan.Obj> getData() {
        String url= XY_Response.URL_SELECTADVANCEINFO+"cmemberId="
                + UserManager.getInstance().getUser().getObj().getC_memberId()+"&gType="+
                XY_ErshoufangAdapter.SHOULOUGUOQIAO;
        RequestCenter.selectAdvanceInfo(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Loan loan= (Loan) responseObj;
                if (loan.getCode().equals("1000")){
                    mList=loan.getObj();
                    if (mList.size()>0){

                        adapter= new XY_ErshoufangAdapter(getActivity(),mList);
                        lv_ershoufan.setAdapter(adapter);
                    }
                }else {
                    MyUntil.show(getContext(),loan.getMsg());
                }
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });
        return mList;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getActivity(), XY_JinRongWebActivity.class);
        intent.putExtra("URL",mList.get(position).getUrl());
        intent.putExtra("GID",mList.get(position).getgId());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        getData();
        swipeRefreshLayout.setRefreshing(false);
    }
}
