package com.bjxiyang.zhinengshequ.myapplication.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.model.GongGao;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class XiaoQuGongGaoAdapter extends BaseAdapter {

    private Context mContext;
    private List<GongGao.Obj> mList;


    public XiaoQuGongGaoAdapter(Context mContext, List<GongGao.Obj> mList) {
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
            view= LayoutInflater.from(mContext).inflate(R.layout.item_xiaoqugonggao,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_item_xiaoqugonggao_date= (TextView) view.findViewById(R.id.tv_item_xiaoqugonggao_date);
//            viewHolder.tv_item_xiaoqugonggao_time= (TextView) view.findViewById(R.id.tv_item_xiaoqugonggao_time);
            viewHolder.tv_item_xiaoqugonggao_title= (TextView) view.findViewById(R.id.tv_item_xiaoqugonggao_title);
            viewHolder.iv_item_xiaoqugonggao_jinji= (ImageView) view.findViewById(R.id.iv_item_xiaoqugonggao_jinji);
            viewHolder.tv_item_xiaoqugonggao_wuyeguanlichu= (TextView) view.findViewById(R.id.tv_item_xiaoqugonggao_wuyeguanlichu);
            viewHolder.ll_item_xiaoqugonggaoo_xiangqing= (LinearLayout) view.findViewById(R.id.ll_item_xiaoqugonggaoo_xiangqing);
            viewHolder.tv_item_xiaoqugonggao_itmexiangqing= (TextView) view.findViewById(R.id.tv_item_xiaoqugonggao_itmexiangqing);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tv_item_xiaoqugonggao_date.setText(mList.get(position).getAddTime());
//        viewHolder.tv_item_xiaoqugonggao_time.setText(mList.get(position).getTime());
        viewHolder.tv_item_xiaoqugonggao_title.setText(mList.get(position).getTitle());
        viewHolder.tv_item_xiaoqugonggao_wuyeguanlichu.setText(mList.get(position).getNoticer());
        viewHolder.tv_item_xiaoqugonggao_itmexiangqing.setText(mList.get(position).getContent());
        if (mList.get(position).getType()==0){
            viewHolder.iv_item_xiaoqugonggao_jinji.setVisibility(View.GONE);
        }
        return view;
    }

    class ViewHolder{
        TextView tv_item_xiaoqugonggao_date;
//        TextView tv_item_xiaoqugonggao_time;
        TextView tv_item_xiaoqugonggao_title;
        ImageView iv_item_xiaoqugonggao_jinji;
        TextView tv_item_xiaoqugonggao_wuyeguanlichu;
        TextView tv_item_xiaoqugonggao_itmexiangqing;
        LinearLayout ll_item_xiaoqugonggaoo_xiangqing;
    }
}
