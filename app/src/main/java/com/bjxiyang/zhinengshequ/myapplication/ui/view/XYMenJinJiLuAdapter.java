package com.bjxiyang.zhinengshequ.myapplication.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.myapplication.model.OpenDoorList;

import java.util.List;

import com.bjxiyang.zhinengshequ.R;

/**
 * Created by gll on 17-5-23.
 */

public class XYMenJinJiLuAdapter extends BaseAdapter {
    private Context mContext;
    private List<OpenDoorList.Obj> list;

    public XYMenJinJiLuAdapter(Context mContext, List<OpenDoorList.Obj> list) {
        this.mContext = mContext;
        this.list = list;
    }
    public void setList(List<OpenDoorList.Obj> list){
        for (int i=0;i<list.size();i++){
            this.list.add(list.get(i));
        }
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
        ViewHolder viewHolder=null;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.xy_item_menjinjilu,null);
            viewHolder=new ViewHolder();
            viewHolder.menjinjilu_name= (TextView) view.findViewById(R.id.menjinjilu_name);
            viewHolder.menjinjilu_address= (TextView) view.findViewById(R.id.menjinjilu_address);
            viewHolder.menjinjilu_date= (TextView) view.findViewById(R.id.menjinjilu_date);
            viewHolder.im_menjinjilu= (ImageView) view.findViewById(R.id.im_menjinjilu);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.menjinjilu_name.setText(list.get(position).getUserName());

//        Plot plot=list.get(position).getCommunityName();
        viewHolder.menjinjilu_address.setText(
                list.get(position).getCommunityName()+list.get(position).getNperName()+
                        list.get(position).getFloorName()+list.get(position).getUnitName());
//        viewHolder.menjinjilu_address.setText(plot.getPlot()+" " +
//                ""+plot.getBuildingNo()+"号楼"+plot.getUnitNumber()+"单元");

        viewHolder.menjinjilu_date.setText(list.get(position).getOpenTime());
//        if (list.get(position).get== UserType.USER_FOLK){
//            viewHolder.im_menjinjilu.setBackgroundResource(R.drawable.c_icon_jiaren);
//        }else if(list.get(position).getRoleType()== UserType.USER_LESSEE){
//            viewHolder.im_menjinjilu.setBackgroundResource(R.drawable.c_icon_zuge);
//        }

        return view;
    }

    class ViewHolder{
        TextView menjinjilu_name;
        TextView menjinjilu_address;
        TextView menjinjilu_date;
        ImageView im_menjinjilu;

    }
}
