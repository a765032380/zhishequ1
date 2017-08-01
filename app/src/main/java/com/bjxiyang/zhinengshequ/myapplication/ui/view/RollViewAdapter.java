package com.bjxiyang.zhinengshequ.myapplication.ui.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bjxiyang.zhinengshequ.myapplication.model.Banner;
import com.bjxiyang.zhinengshequ.myapplication.ui.fragment.HomeFragment;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.List;

/**
 * Created by gll on 17-5-22.
 */

public class RollViewAdapter extends LoopPagerAdapter{

    private List<Banner.Obj> mList;
    private ImageLoaderManager manager;
    public RollViewAdapter(RollPagerView viewPager) {
        super(viewPager);
        HomeFragment.setOngetData(new HomeFragment.OngetData() {
            @Override
            public void OngetData(List<Banner.Obj> list) {
                mList=list;
                notifyDataSetChanged();
            }
        });
    }
    @Override
    public View getView(final ViewGroup container,  int position) {

        manager=ImageLoaderManager.getInstance(container.getContext());
        ImageView view = new ImageView(container.getContext());

            manager.displayImage(view, mList.get(position).getAdUrl());
        final int position1=position;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mList.get(position1).getAdType()==0){
                        Toast.makeText(container.getContext(),"调用H5",Toast.LENGTH_LONG).show();
                    }else if (mList.get(position1).getAdType()==2){
                        Toast.makeText(container.getContext(),"跳转到商超页面",Toast.LENGTH_LONG).show();
                    }

                }
            });



        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }
    @Override
    public int getRealCount() {
        if (mList!=null){
            if (mList.size()<0){
                return 0;
            }else {
                return mList.size();
            }
        }else {
            return 0;
        }
    }
}
