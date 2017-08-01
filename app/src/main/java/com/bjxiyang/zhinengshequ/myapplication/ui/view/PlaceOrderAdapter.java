package com.bjxiyang.zhinengshequ.myapplication.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.GouWuChe;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class PlaceOrderAdapter extends BaseAdapter {
    private Context mContext;
    private List<GouWuChe> list;
    private int type;
    DecimalFormat df=new DecimalFormat("0.##");

    public PlaceOrderAdapter(Context mContext, List<GouWuChe> list) {
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
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(mContext).inflate(R.layout.item_dingdanguanli_item,null);

            viewHolder.tv_goodsname= (TextView) view.findViewById(R.id.tv_goodsname);
            viewHolder.tv_goodscount= (TextView) view.findViewById(R.id.tv_goodscount);
            viewHolder.tv_goodsprice= (TextView) view.findViewById(R.id.tv_goodsprice);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.tv_goodsname.setText(list.get(position).getName());
        viewHolder.tv_goodscount.setText(list.get(position).getCount()+"");
        if (list.get(position).getIfDiscount()==0){
            viewHolder.tv_goodsprice.setText(df.format((double)list.get(position).getPrice()*list.get(position).getCount()/100)+"");
        }else {
            viewHolder.tv_goodsprice.setText(df.format((double)list.get(position).getDiscountPrice()*list.get(position).getCount()/100)+"");
        }
        return view;
    }
    class ViewHolder{
        TextView tv_goodsname;
        TextView tv_goodscount;
        TextView tv_goodsprice;


        //选择框
        ImageView iv_item_tijiaodingdan;
        //商品图片
        ImageView iv_item_tijiaodingdan_shangpinimage;
        //品牌名称
        TextView tv_item_tijiaodingdan_shangpinname;
        //商品规格
        TextView tv_item_tijiaodingdan_shangpinguige;
        //商品单价
        TextView tv_item_tijiaodingdan_money;
        //加的按钮
        ImageView iv_item_tijiaodingdan_jian;
        //减的按钮
        ImageView iv_item_tijiaodingdan_jia;
        //数量显示
        TextView tv_item_tijiaodingdan_count;

    }
}
