package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by gll on 17-4-26.
 */

public class MyPagerAdapter extends PagerAdapter {
    //View数据源
    private List<View> list;
    //指示标题文本信息数据源
    private List<String> mTitleList;

    public MyPagerAdapter(List<View> list,List<String> mTitleList){
        this.list=list;
        this.mTitleList=mTitleList;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    //判断View是否来自对象
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    //实例化一个页卡
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }
    //销毁一个页卡
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }

    //设置ViewPager每页对应的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
