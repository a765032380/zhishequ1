package com.example.b88;



import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Cl on 2016/10/10.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<View> viewList;

    

    public ViewPagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(viewList.get(position)); // 将不用的view移除
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(viewList.get(position)); // 将使用的view添加
   
        return viewList.get(position);
    }

    // 个数的
    @Override
    public int getCount() {
        return viewList.size();
    }

    // 判断你的view和你当前的对象是否一致
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
