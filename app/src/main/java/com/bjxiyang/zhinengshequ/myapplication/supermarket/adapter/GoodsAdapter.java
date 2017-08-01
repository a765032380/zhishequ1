package com.bjxiyang.zhinengshequ.myapplication.supermarket.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.GouWuChe;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.ShangPinList;
import com.bjxiyang.zhinengshequ.myapplication.greendao.DaoUtils;
import com.bjxiyang.zhinengshequ.myapplication.supermarket.bean.GoodsBean;
import com.bjxiyang.zhinengshequ.myapplication.ui.fragment.Supermarketfragment;
import com.bjxiyang.zhinengshequ.myapplication.until.SPToGouWuChe;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by fengyongge on 2016/5/24 0024.
 */

public class GoodsAdapter extends BaseAdapter{
    private List<ShangPinList.Result.Products> list;
    private Context context;
    private CatograyAdapter catograyAdapter;
    private Supermarketfragment supermarketfragment;
    DecimalFormat df=new DecimalFormat("0.##");
    private List<GouWuChe> mList;
    private GouWuChe gouwuche;

    public GoodsAdapter(Context context, Supermarketfragment supermarketfragment, List<ShangPinList.Result.Products> list2, CatograyAdapter catograyAdapter) {
        this.supermarketfragment=supermarketfragment;
        this.context=context;
        this.list=list2;
        this.catograyAdapter=catograyAdapter;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final Viewholder viewholder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.super_shopcart_right_listview,null);
            viewholder=new Viewholder();
            viewholder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            viewholder.tv_original_price= (TextView) convertView.findViewById(R.id.tv_original_price);
            viewholder.tv_price= (TextView) convertView.findViewById(R.id.tv_price);
            viewholder.iv_add= (ImageView) convertView.findViewById(R.id.iv_add);
            viewholder.iv_remove= (ImageView) convertView.findViewById(R.id.iv_remove);
            viewholder.tv_acount= (TextView) convertView.findViewById(R.id.tv_acount);
            viewholder.iv_pic= (ImageView) convertView.findViewById(R.id.iv_pic);
            viewholder.rl_item= (RelativeLayout) convertView.findViewById(R.id.rl_item);
            convertView.setTag(viewholder);
        }else {
            viewholder = (Viewholder) convertView.getTag();
        }
        int position1=position;
        mList= DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
        GouWuChe gouwuche1 = null;
        for (int i=0;i<mList.size();i++){
            if (mList.get(i).getSpid()==list.get(position1).getId()){
               gouwuche1=mList.get(i);
            }
        }
//        if (gouwuche==null){
//            gouwuche=SPToGouWuChe.splistToGouWuChe(list.get(position1));
//            DaoUtils.getStudentInstance().insertObject(gouwuche);
//        }


        viewholder.tv_name.setText(list.get(position).getName());



        if (list.get(position).getIfDiscount()==0){
            viewholder.tv_original_price.setVisibility(View.INVISIBLE);
            viewholder.tv_price.setText("￥"+df.format((double) list.get(position).getPrice()/100));
//            viewholder.tv_price.setVisibility(View.INVISIBLE);
//            viewholder.tv_original_price.setText("￥"+df.format((double) list.get(position).getPrice()/100));
        }else {
            viewholder.tv_original_price.setVisibility(View.VISIBLE);
            viewholder.tv_price.setText("￥"+df.format((double) list.get(position).getDiscountPrice()/100));
            viewholder.tv_original_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
            viewholder.tv_original_price.setText("￥"+df.format((double) list.get(position).getPrice()/100));
        }

        if(gouwuche1!=null){
        //默认进来数量
            if (gouwuche1.getCount()<1){
                viewholder.tv_acount.setVisibility(View.INVISIBLE);
                viewholder.iv_remove.setVisibility(View.INVISIBLE);
                catograyAdapter.notifyDataSetChanged();
            }else{
                viewholder.tv_acount.setVisibility(View.VISIBLE);
                viewholder.iv_remove.setVisibility(View.VISIBLE);
                viewholder.tv_acount.setText(String.valueOf(gouwuche1.getCount()));
                catograyAdapter.notifyDataSetChanged();
            }
        }else{
            viewholder.tv_acount.setVisibility(View.INVISIBLE);
            viewholder.iv_remove.setVisibility(View.INVISIBLE);
        }

        //商品图片
        if(list.get(position).getLogo()!=null){
            ImageLoader.getInstance().displayImage(
                    list.get(position).getLogo(), viewholder.iv_pic);
        }
        final int position2=position;
        viewholder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int count = supermarketfragment.getSelectedItemCountById(list.get(position).getId());
//                Log.i("fyg","iv_add"+ String.valueOf(count));
//                if (count < 1) {
//                    viewholder.iv_remove.setAnimation(getShowAnimation());
//                    viewholder.iv_remove.setVisibility(View.VISIBLE);
//                    viewholder.tv_acount.setVisibility(View.VISIBLE);
//                }
                gouwuche=null;
                mList= DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
                for (int i=0;i<mList.size();i++){
                    if (mList.get(i).getSpid()==list.get(position2).getId()){
                        gouwuche=mList.get(i);
                    }
                }
                if (gouwuche==null){
                    gouwuche=SPToGouWuChe.splistToGouWuChe(list.get(position2));
                    DaoUtils.getStudentInstance().insertObject(gouwuche);
                }
                supermarketfragment.handlerCarNum(1,gouwuche,true);

                catograyAdapter.notifyDataSetChanged();

                int[] loc = new int[2];
                viewholder.iv_add.getLocationInWindow(loc);
                for (int i=0;i<loc.length;i++)
                {
                    Log.i("fyg", String.valueOf(loc[i]));
                }
                int[] startLocation = new int[2];// 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
                v.getLocationInWindow(startLocation);// 这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
                ImageView ball = new ImageView(context);
                ball.setImageResource(R.drawable.number);
                supermarketfragment.setAnim(ball, startLocation);// 开始执行动画

            }
        });

        viewholder.iv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gouwuche=null;
                mList= DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
                for (int i=0;i<mList.size();i++){
                    if (mList.get(i).getSpid()==list.get(position2).getId()){
                        gouwuche=mList.get(i);
                    }
                }
                if (gouwuche==null){
                    gouwuche=SPToGouWuChe.splistToGouWuChe(list.get(position2));
                    DaoUtils.getStudentInstance().insertObject(gouwuche);
                }
                int count = gouwuche.getCount();
                if (count < 2) {
                    viewholder.iv_remove.setAnimation(getHiddenAnimation());
                    viewholder.iv_remove.setVisibility(View.GONE);
                    viewholder.tv_acount.setVisibility(View.GONE);
                }
                supermarketfragment.handlerCarNum(0,gouwuche,true);

//                supermarketfragment.handlerCarNum(0,list.get(position),true);
                catograyAdapter.notifyDataSetChanged();

            }
        });

        //查看商品详情页面
        viewholder.rl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(list.get(position).getPackage_product_info()!=null&&list.get(position).getTitle()!=null){
                catograyAdapter.notifyDataSetChanged();
                supermarketfragment.showDetailSheet(supermarketfragment.getListAll(),position2,list.get(position2));
//                }else{
//                    Toast.makeText(context, "没有详情!", Toast.LENGTH_SHORT).show();
//                }
            }
        });
        return convertView;
    }

    class Viewholder{
        TextView tv_name,tv_original_price,tv_price;
        ImageView iv_add,iv_remove,iv_pic;
        TextView tv_acount;
        RelativeLayout rl_item;
    }



    //显示减号的动画
    private Animation getShowAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720, RotateAnimation.RELATIVE_TO_SELF,0.5f, RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,2f
                , TranslateAnimation.RELATIVE_TO_SELF,0
                , TranslateAnimation.RELATIVE_TO_SELF,0
                , TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }
    //隐藏减号的动画
    private Animation getHiddenAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720, RotateAnimation.RELATIVE_TO_SELF,0.5f, RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0
                , TranslateAnimation.RELATIVE_TO_SELF,2f
                , TranslateAnimation.RELATIVE_TO_SELF,0
                , TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(1,0);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }



}
