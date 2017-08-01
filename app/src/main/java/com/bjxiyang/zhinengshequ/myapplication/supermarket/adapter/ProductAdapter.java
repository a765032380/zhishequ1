package com.bjxiyang.zhinengshequ.myapplication.supermarket.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.GouWuChe;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.ShangPinList;
import com.bjxiyang.zhinengshequ.myapplication.greendao.DaoUtils;
import com.bjxiyang.zhinengshequ.myapplication.supermarket.bean.GoodsBean;
import com.bjxiyang.zhinengshequ.myapplication.supermarket.util.StringUtils;
import com.bjxiyang.zhinengshequ.myapplication.ui.fragment.Supermarketfragment;
import com.bjxiyang.zhinengshequ.myapplication.until.SPToGouWuChe;

import java.text.DecimalFormat;
import java.util.List;


/**
 * Created by fengyongge on 2016/5/24 0024.
 */

/***
 * 底部购物车
 */
public class ProductAdapter extends BaseAdapter {
    GoodsAdapter goodsAdapter;
    private Supermarketfragment activity;
    private List<GouWuChe> dataList;
    private Context mContext;
    private List<GouWuChe> mList;
    private GouWuChe gouWuChe;
    DecimalFormat df=new DecimalFormat("0.##");
    public ProductAdapter(Context mContext,Supermarketfragment activity,
                          GoodsAdapter goodsAdapter, List<GouWuChe> dataList) {
        this.goodsAdapter =goodsAdapter;
        this.activity = activity;
        this.mContext=mContext;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Viewholder viewholder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.super_product_item, null);
            viewholder = new Viewholder();
            viewholder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            viewholder.tv_price = (TextView) view.findViewById(R.id.tv_price);
            viewholder.iv_add= (ImageView) view.findViewById(R.id.iv_add);
            viewholder.iv_remove= (ImageView) view.findViewById(R.id.iv_remove);
            viewholder.tv_count= (TextView) view.findViewById(R.id.tv_count);
            view.setTag(viewholder);
        } else {
            viewholder = (Viewholder) view.getTag();
        }

//        int position1=position;
//        mList= DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
//        GouWuChe gouwuche1 = null;
//        for (int i=0;i<mList.size();i++){
//            if (mList.get(i).getSpid()==dataList.get(position1).getId()){
//                gouwuche1=mList.get(i);
//            }
//        }
//        mList=dataList;
//        mList = DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
//        if (mList.size() > 0) {
//            for (int i = 0; i < mList.size(); i++) {
//                if (mList.get(i).getSpid() == dataList.get(position).getId()) {
        gouWuChe=null;
//        mList=DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
        gouWuChe =dataList.get(position);

            if (gouWuChe != null) {
                //默认进来数量
                if (gouWuChe.getCount() < 1) {
                    DaoUtils.getStudentInstance().deleteObject(gouWuChe);
//                    dataList.remove(position);
                    notifyDataSetChanged();
                } else {
                    viewholder.tv_count.setText(String.valueOf(gouWuChe.getCount()));
                    notifyDataSetChanged();
                }
            }
//                }
//            }
//        } else {
//            gouWuChe= SPToGouWuChe.spToGouWuChe(dataList.get(position));
//            DaoUtils.getStudentInstance().insertObject(gouWuChe);
//        }
            StringUtils.filtNull(viewholder.tv_name, dataList.get(position).getName());//商品名称
            if (dataList.get(position).getIfDiscount() == 0) {
                StringUtils.filtNull(viewholder.tv_price, "￥" + df.format((double) dataList.get(position).getPrice() / 100));//商品价格
            } else {
                StringUtils.filtNull(viewholder.tv_price, "￥" + df.format((double) dataList.get(position).getDiscountPrice() / 100));//商品价格
            }


            viewholder.tv_count.setText(String.valueOf(dataList.get(position).getCount()));//商品数量

            final int position1 = position;
            viewholder.iv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.handlerCarNum(1, dataList.get(position1), true);
                    goodsAdapter.notifyDataSetChanged();

                }
            });
            viewholder.iv_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    gouWuChe.setCount(gouWuChe.getCount() - 1);
//                    if (gouWuChe.getCount()==0) {
//                        dataList.remove(position1);
//                    }
                    activity.handlerCarNum(0, dataList.get(position1), true);
                    goodsAdapter.notifyDataSetChanged();
                }
            });


        return view;
    }

    class Viewholder {
        TextView tv_price;
        TextView tv_name;
        ImageView iv_add,iv_remove;
        TextView tv_count;
    }


}