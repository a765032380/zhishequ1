package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by gll on 17-4-26.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> mTitleList;
    public MyFragmentAdapter(FragmentManager fm,List<Fragment> list,List<String> mTitleList) {
        super(fm);
        this.list=list;
        this.mTitleList=mTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
    //设置ViewPager每页对应的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
