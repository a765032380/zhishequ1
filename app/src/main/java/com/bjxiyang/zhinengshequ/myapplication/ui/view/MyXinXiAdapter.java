package com.bjxiyang.zhinengshequ.myapplication.ui.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.XYXiuGaiActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class MyXinXiAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList;

    public MyXinXiAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Viewholder viewholder;
        if (view==null){
            viewholder=new Viewholder();
            view= LayoutInflater.from(mContext).inflate(R.layout.item_gerenxinxi,null);
            viewholder.iv_gerenxinxi_xiugaixinxi=
                    (ImageView) view.findViewById(R.id.iv_gerenxinxi_xiugaixinxi);
            viewholder.iv_gerenxinxi_touxiang=
                    (ImageView) view.findViewById(R.id.iv_gerenxinxi_touxiang);
            viewholder.tv_gerenxinxi_name=
                    (TextView) view.findViewById(R.id.tv_gerenxinxi_name);
            viewholder.tv_gerenxinxi_zhuhuxingzhi=
                    (TextView) view.findViewById(R.id.tv_gerenxinxi_zhuhuxingzhi);
            viewholder.tv_gerenxinxi_qiyongshijian=
                    (TextView) view.findViewById(R.id.tv_gerenxinxi_qiyongshijian);
            viewholder.tv_gerenxinxi_xiaoqudizhi=
                    (TextView) view.findViewById(R.id.tv_gerenxinxi_xiaoqudizhi);
            viewholder.tv_gerenxinxi_shoujihaoma=
                    (TextView) view.findViewById(R.id.tv_gerenxinxi_shoujihaoma);
            viewholder.tv_gerenxinxi_yiqiyong=
                    (TextView) view.findViewById(R.id.tv_gerenxinxi_yiqiyong);
            view.setTag(viewholder);
        }else {
            viewholder= (Viewholder) view.getTag();
        }
        viewholder.iv_gerenxinxi_xiugaixinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, XYXiuGaiActivity.class);
                mContext.startActivity(intent);
            }
        });
//        viewholder.iv_gerenxinxi_touxiang;
//        viewholder.tv_gerenxinxi_name;
//        viewholder.tv_gerenxinxi_zhuhuxingzhi;
//        viewholder.tv_gerenxinxi_qiyongshijian;
//        viewholder.tv_gerenxinxi_xiaoqudizhi;
//        viewholder.tv_gerenxinxi_shoujihaoma;
//        //该用户的状态
//        viewholder.tv_gerenxinxi_yiqiyong;
//
//        viewholder.iv_gerenxinxi_xiugaixinxi;




        return view;
    }




    class Viewholder{
        ImageView iv_gerenxinxi_touxiang;
        TextView tv_gerenxinxi_name;
        TextView tv_gerenxinxi_zhuhuxingzhi;
        TextView tv_gerenxinxi_qiyongshijian;
        TextView tv_gerenxinxi_xiaoqudizhi;
        TextView tv_gerenxinxi_shoujihaoma;
        //该用户的状态
        TextView tv_gerenxinxi_yiqiyong;

        ImageView iv_gerenxinxi_xiugaixinxi;

    }
}
