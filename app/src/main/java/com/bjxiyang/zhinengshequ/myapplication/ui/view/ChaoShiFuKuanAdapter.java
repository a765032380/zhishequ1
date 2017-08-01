package com.bjxiyang.zhinengshequ.myapplication.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.model.TestChaoShiGouWu;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class ChaoShiFuKuanAdapter extends BaseAdapter {

    private Context mContext;
    private List<TestChaoShiGouWu> mList;

    public ChaoShiFuKuanAdapter(Context mContext, List<TestChaoShiGouWu> mList) {
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
        ViewHolder viewHolder=null;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.ieme_chaoshifukuan,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_item_chaoshifukuan_date= (TextView) view.findViewById(R.id.tv_item_chaoshifukuan_date);
            viewHolder.tv_item_chaoshifukuan_time= (TextView) view.findViewById(R.id.tv_item_chaoshifukuan_time);
            viewHolder.tv_item_chaoshifukuan_dianming= (TextView) view.findViewById(R.id.tv_item_chaoshifukuan_dianming);
            viewHolder.tv_item_chaoshifukuan_money= (TextView) view.findViewById(R.id.tv_item_chaoshifukuan_money);
            viewHolder.tv_item_chaoshifukuan_dingdanhao= (TextView) view.findViewById(R.id.tv_item_chaoshifukuan_dingdanhao);
            viewHolder.tv_item_chaoshifukuan_jiaofeishijian= (TextView) view.findViewById(R.id.tv_item_chaoshifukuan_jiaofeishijian);
            viewHolder.tv_item_chaoshifukuan_zhifufangshi= (TextView) view.findViewById(R.id.tv_item_chaoshifukuan_zhifufangshi);
            viewHolder.tv_item_wuyejiaofei_wuyegongsiname= (TextView) view.findViewById(R.id.tv_item_wuyejiaofei_wuyegongsiname);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tv_item_chaoshifukuan_date.setText(mList.get(position).getDate());
        viewHolder.tv_item_chaoshifukuan_time.setText(mList.get(position).getTime());
        viewHolder.tv_item_chaoshifukuan_dianming.setText(mList.get(position).getName());
        viewHolder.tv_item_chaoshifukuan_money.setText(mList.get(position).getJiage());
        viewHolder.tv_item_chaoshifukuan_dingdanhao.setText(mList.get(position).getDingdanhao());
        viewHolder.tv_item_chaoshifukuan_jiaofeishijian.setText(mList.get(position).getJiaofeitime());
        viewHolder.tv_item_chaoshifukuan_zhifufangshi.setText(mList.get(position).getZhifufangshi());
        viewHolder.tv_item_wuyejiaofei_wuyegongsiname.setText(mList.get(position).getWuyename());
        return view;
    }


    class ViewHolder{
        TextView tv_item_chaoshifukuan_date;
        TextView tv_item_chaoshifukuan_time;
        TextView tv_item_chaoshifukuan_dianming;
        TextView tv_item_chaoshifukuan_money;
        TextView tv_item_chaoshifukuan_dingdanhao;
        TextView tv_item_chaoshifukuan_jiaofeishijian;
        TextView tv_item_chaoshifukuan_zhifufangshi;
        TextView tv_item_wuyejiaofei_wuyegongsiname;



    }
}
