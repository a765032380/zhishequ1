package com.bjxiyang.zhinengshequ.myapplication.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.bjxiyang.zhinengshequ.R;

import java.util.List;

/**
 * Created by gll on 17-5-23.
 */

public class XYXuanZeXiaoQuAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> list;

    public XYXuanZeXiaoQuAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.xy_item_xuanzexiaoqu,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_item_xuanzexiaoqu= (TextView) view.findViewById(R.id.tv_item_xuanzexiaoqu);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
            viewHolder.tv_item_xuanzexiaoqu.setText(list.get(position));
        return view;
    }

    class ViewHolder{
        TextView tv_item_xuanzexiaoqu;

    }
}
