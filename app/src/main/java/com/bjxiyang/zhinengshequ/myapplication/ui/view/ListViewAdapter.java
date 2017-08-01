package com.bjxiyang.zhinengshequ.myapplication.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DianMing;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public class ListViewAdapter extends BaseAdapter {
    private List<DianMing.Result> mList;
    private Context mContext;

    public ListViewAdapter(List<DianMing.Result> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
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
        ViewHolder viewHolder;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_textview,null);
            viewHolder=new ViewHolder();
            viewHolder.item_textview= (TextView) view.findViewById(R.id.item_textview);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.item_textview.setText(mList.get(position).getShopName());

        return view;
    }
    class ViewHolder{
        TextView item_textview;


    }
}
