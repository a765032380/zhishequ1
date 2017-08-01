package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.until.ShangPing;

import java.util.List;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class GouWuCheAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShangPing> mList;

    public GouWuCheAdapter(Context mContext, List<ShangPing> mList) {

        this.mContext = mContext;
        this.mList = mList;
}
    public void setData(List<ShangPing> mList) {
        this.mList = mList;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if (mList!=null&& mList.size() > 0){
            Log.i("TEXT",String.valueOf(mList.get(0).getName()));
            return mList.size();
        }else {
            return  0;
        }

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
        Log.i("TEXT",String.valueOf(mList.get(position).getName()));
        final ViewHolder viewHolder;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_dialog,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_item_dialog= (ImageView) view.findViewById(R.id.iv_item_dialog);
            viewHolder.tv_item_dialog_shangpinming= (TextView) view.findViewById(R.id.tv_item_dialog_shangpinming);
            viewHolder.tv_item_dialog_shangpinguige= (TextView) view.findViewById(R.id.tv_item_dialog_shangpinguige);
            viewHolder.tv_item_dialog_money= (TextView) view.findViewById(R.id.tv_item_dialog_money);
            viewHolder.iv_item_dialog_jian= (ImageView) view.findViewById(R.id.iv_item_dialog_jian);
            viewHolder.iv_item_dialog_jia= (ImageView) view.findViewById(R.id.iv_item_dialog_jia);
            viewHolder.tv_item_dialog_count= (TextView) view.findViewById(R.id.tv_item_dialog_count);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
//        ImageLoaderManager manager=ImageLoaderManager.getInstance(mContext);
//        manager.displayImage(viewHolder.iv_item_dialog,mList.get(position).getSptupian());
        viewHolder.tv_item_dialog_shangpinming.setText(mList.get(position).getName());
        viewHolder.tv_item_dialog_shangpinguige.setText(mList.get(position).getGuige());
        viewHolder.tv_item_dialog_money.setText(mList.get(position).getJiage());
        viewHolder.iv_item_dialog_jian.setBackgroundResource(R.drawable.a_btn_jian);
        viewHolder.iv_item_dialog_jia.setBackgroundResource(R.drawable.a_btn_jia);
        viewHolder.tv_item_dialog_count.setText("2");

        return view;
    }
    class ViewHolder{
        //商品图片
        ImageView iv_item_dialog;
        //商品名
        TextView tv_item_dialog_shangpinming;
        //商品规格
        TextView tv_item_dialog_shangpinguige;
        //商品价格
        TextView tv_item_dialog_money;
        //减的按钮
        ImageView iv_item_dialog_jian;
        //加的按钮
        ImageView iv_item_dialog_jia;
        //商品数量
        TextView tv_item_dialog_count;

    }
}
