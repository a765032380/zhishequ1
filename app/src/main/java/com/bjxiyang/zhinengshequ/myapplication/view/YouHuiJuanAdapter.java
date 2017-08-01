package com.bjxiyang.zhinengshequ.myapplication.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.YouHuiQuan;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13 0013.
 */

public class YouHuiJuanAdapter extends BaseAdapter {

    private Context mContext;
    private List<YouHuiQuan.ResultBean> mList;

    public YouHuiJuanAdapter(Context mContext, List<YouHuiQuan.ResultBean> mList) {
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
        ViewHolder viewHolder;
        if (view==null){
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(mContext).inflate(R.layout.item_youhuiquan,null);
            viewHolder.tv_yopuhuiquan_hongbaoleixing= (TextView) view.findViewById(R.id.tv_yopuhuiquan_hongbaoleixing);
            viewHolder.tv_youhuiquan_hongbaojine= (TextView) view.findViewById(R.id.tv_youhuiquan_hongbaojine);
            viewHolder.tv_yopuhuiquan_youxiaoqixian= (TextView) view.findViewById(R.id.tv_yopuhuiquan_youxiaoqixian);
            viewHolder.tv_youhuiquan_mankeyong= (TextView) view.findViewById(R.id.tv_youhuiquan_mankeyong);
            viewHolder.tv_yopuhuiquan_xianzhileixing= (TextView) view.findViewById(R.id.tv_yopuhuiquan_xianzhileixing);
            viewHolder.tv_youhuiquan_xianzhishoujihao= (TextView) view.findViewById(R.id.tv_youhuiquan_xianzhishoujihao);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tv_yopuhuiquan_hongbaoleixing.setText(mList.get(position).getName());
//        viewHolder.tv_youhuiquan_hongbaojine.setText(mList.get(position));
        viewHolder.tv_yopuhuiquan_youxiaoqixian.setText(mList.get(position).getStartDate()+"--"+mList.get(position).getEndDate());
//        viewHolder.tv_youhuiquan_mankeyong.setText(mList.get(position));
//        viewHolder.tv_yopuhuiquan_xianzhileixing.setText(mList.get(position));
//        viewHolder.tv_youhuiquan_xianzhishoujihao.setText(mList.get(position));

        return view;
    }

    class ViewHolder{
        TextView tv_yopuhuiquan_hongbaoleixing;
        TextView tv_youhuiquan_hongbaojine;
        TextView tv_yopuhuiquan_youxiaoqixian;
        TextView tv_youhuiquan_mankeyong;
        TextView tv_yopuhuiquan_xianzhileixing;
        TextView tv_youhuiquan_xianzhishoujihao;
    }
}
