package com.bjxiyang.zhinengshequ.myapplication.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.model.TestDiYongJuan;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class DiYongJuanAdapter extends BaseAdapter {

    private Context mContext;
    private List<TestDiYongJuan> mList;

    public DiYongJuanAdapter(Context mContext, List<TestDiYongJuan> mList) {
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (view==null){
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(mContext).inflate(R.layout.item_diyongquan,null);
            viewHolder.tv_diyongquan_name= (TextView) view.findViewById(R.id.tv_diyongquan_name);
            viewHolder.tv_diyongquan_duijianghao_yiduihuan_guoqi=
                    (TextView) view.findViewById(R.id.tv_diyongquan_duijianghao_yiduihuan_guoqi);
            viewHolder.tv_diyongquan_youxiaoqixian=
                    (TextView) view.findViewById(R.id.tv_diyongquan_youxiaoqixian);
            viewHolder.tv_diyongquan_xianzhileixing=
                    (TextView) view.findViewById(R.id.tv_diyongquan_xianzhileixing);
            viewHolder.tv_diyongquan_xianzhishoujihao=
                    (TextView) view.findViewById(R.id.tv_diyongquan_xianzhishoujihao);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tv_diyongquan_name.setText(mList.get(position).getName());
        viewHolder.tv_diyongquan_duijianghao_yiduihuan_guoqi.setText(mList.get(position).getDuijianghao());
        viewHolder.tv_diyongquan_youxiaoqixian.setText(mList.get(position).getDate());
        viewHolder.tv_diyongquan_xianzhileixing.setText(mList.get(position).getXianshi());
        viewHolder.tv_diyongquan_xianzhishoujihao.setText(mList.get(position).getShouji());
        return view;
    }
    class ViewHolder{
        TextView tv_diyongquan_name;
        TextView tv_diyongquan_duijianghao_yiduihuan_guoqi;
        TextView tv_diyongquan_youxiaoqixian;
        TextView tv_diyongquan_xianzhileixing;
        TextView tv_diyongquan_xianzhishoujihao;



    }
}
