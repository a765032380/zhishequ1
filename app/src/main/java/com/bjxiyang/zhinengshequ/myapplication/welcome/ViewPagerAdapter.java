package com.bjxiyang.zhinengshequ.myapplication.welcome;



import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
        ((ViewPager) container).removeView(viewList.get(position)); // �����õ�view�Ƴ�
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(viewList.get(position)); // ��ʹ�õ�view���
   
        return viewList.get(position);
    }

    // ������
    @Override
    public int getCount() {
        return viewList.size();
    }

    // �ж����view���㵱ǰ�Ķ����Ƿ�һ��
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
