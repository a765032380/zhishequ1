package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DiZhiList;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.ShouHuoDiZhiAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6 0006.
 */

public class DialogDiZhiAdapter extends BaseAdapter{
    private Context mContext;
    private List<DiZhiList.ResultBean> mList;


    private boolean isSelect=true;
    public DialogDiZhiAdapter(Context mContext, List<DiZhiList.ResultBean> mList) {
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
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_shouhuodizhi, null);
            viewHolder.tv_item_shouhuodizhi_dizhi =
                    (TextView) view.findViewById(R.id.tv_item_shouhuodizhi_dizhi);
            viewHolder.tv_item_shouhuodizhi_name =
                    (TextView) view.findViewById(R.id.tv_item_shouhuodizhi_name);
            viewHolder.tv_item_shouhuodizhi_sex =
                    (TextView) view.findViewById(R.id.tv_item_shouhuodizhi_sex);
            viewHolder.tv_item_shouhuodizhi_phone =
                    (TextView) view.findViewById(R.id.tv_item_shouhuodizhi_phone);
            viewHolder.tv_item_shouhuodizhi_peisongfanwei =
                    (TextView) view.findViewById(R.id.tv_item_shouhuodizhi_peisongfanwei);
            viewHolder.iv_item_shouhuodizhi_button =
                    (ImageView) view.findViewById(R.id.iv_item_shouhuodizhi_button);
            viewHolder.rl_item_bianji_button =
                    (RelativeLayout) view.findViewById(R.id.rl_item_bianji_button);
            viewHolder.rl_item_shanchu_button =
                    (RelativeLayout) view.findViewById(R.id.rl_item_shanchu_button);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (mList.size()>0) {
            viewHolder.tv_item_shouhuodizhi_name.setText(mList.get(position).getName());
            if (mList.get(position).getSex() == 1) {
                viewHolder.tv_item_shouhuodizhi_sex.setText("先生");
            }
            if (mList.get(position).getSex() == 2) {
                viewHolder.tv_item_shouhuodizhi_sex.setText("女士");
            }
            viewHolder.tv_item_shouhuodizhi_phone.setText(mList.get(position).getPhone());

            viewHolder.tv_item_shouhuodizhi_dizhi.setText(mList.get(position).getCommunityName() +
                    mList.get(position).getFloorName() + mList.get(position).getUnitName()
                    + mList.get(position).getDoorName());

            viewHolder.iv_item_shouhuodizhi_button.setVisibility(View.INVISIBLE);
            viewHolder.rl_item_shanchu_button.setVisibility(View.INVISIBLE);
            viewHolder.rl_item_bianji_button.setVisibility(View.INVISIBLE);
        }else {
            viewHolder.tv_item_shouhuodizhi_name.setText("当前无优惠券");
        }

        return view;
    }
        class ViewHolder{
            TextView tv_item_shouhuodizhi_dizhi;
            TextView tv_item_shouhuodizhi_name;
            TextView tv_item_shouhuodizhi_sex;
            TextView tv_item_shouhuodizhi_phone;
            TextView tv_item_shouhuodizhi_peisongfanwei;
            ImageView iv_item_shouhuodizhi_button;
            RelativeLayout rl_item_shanchu_button;
            RelativeLayout rl_item_bianji_button;

        }
}
