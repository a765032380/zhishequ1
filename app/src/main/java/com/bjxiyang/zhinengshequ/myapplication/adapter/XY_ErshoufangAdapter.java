package com.bjxiyang.zhinengshequ.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.myapplication.model.Loan;

import java.util.List;

import com.bjxiyang.zhinengshequ.R;

/**
 * Created by gll on 17-5-24.
 */

public class XY_ErshoufangAdapter extends BaseAdapter {
    //信用的类型参数
    public static final int XINYONG=1;
    //担保贷的类型参数
    public static final int DANBAODAI=2;
    //二手房的类型参数
    public static final int ERSHOUFANG=3;
    //赎楼过桥的类型参数
    public static final int SHOULOUGUOQIAO=0;


    private Context mContext;
    private List<Loan.Obj> mList;

    public XY_ErshoufangAdapter(Context mContext, List<Loan.Obj> mList) {
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
        Viewholder viewholder=null;
        if (view==null){
            viewholder=new Viewholder();
            if (mList.get(position).getgType()==DANBAODAI){
                view= LayoutInflater.from(mContext).inflate(R.layout.xy_item_xinyong,null);
                viewholder.tv_xinyong_danbaodai= (TextView) view.findViewById(R.id.tv_xinyong_danbaodai);
                viewholder.tv_xinyong_daikuanqixian= (TextView) view.findViewById(R.id.tv_xinyong_daikuanqixian);
                viewholder.tv_xinyong_xiakuanqixian= (TextView) view.findViewById(R.id.tv_xinyong_xiakuanqixian);
                viewholder.tv_xinyon_yuecankaolilv= (TextView) view.findViewById(R.id.tv_xinyon_yuecankaolilv);
                viewholder.tv_xinyong_daikuanedu= (TextView) view.findViewById(R.id.tv_xinyong_daikuanedu);
                viewholder.tv_xinyong_dibuxinxi= (TextView) view.findViewById(R.id.tv_xinyong_dibuxinxi);
            }else
            if (mList.get(position).getgType()==SHOULOUGUOQIAO){
                view= LayoutInflater.from(mContext).inflate(R.layout.xy_item_shulouguoqiao,null);
                viewholder.tv_fangchanshuloudianzi= (TextView) view.findViewById(R.id.tv_fangchanshuloudianzi);
                viewholder.tv_daikuanqixian= (TextView) view.findViewById(R.id.tv_daikuanqixian);
                viewholder.tv_diyawu= (TextView) view.findViewById(R.id.tv_diyawu);
                viewholder.tv_daikuanedu= (TextView) view.findViewById(R.id.tv_daikuanedu);
                viewholder.tv_xiakuanqixian= (TextView) view.findViewById(R.id.tv_xiakuanqixian);
                viewholder.tv_yuecankaolilv= (TextView) view.findViewById(R.id.tv_yuecankaolilv);
                viewholder.tv_dibuxinxi= (TextView) view.findViewById(R.id.tv_dibuxinxi);
            }else if (mList.get(position).getgType()==XINYONG){
                view= LayoutInflater.from(mContext).inflate(R.layout.xy_item_shulouguoqiao,null);
                viewholder.tv_fangchanshuloudianzi= (TextView) view.findViewById(R.id.tv_fangchanshuloudianzi);
                viewholder.tv_daikuanqixian= (TextView) view.findViewById(R.id.tv_daikuanqixian);
                viewholder.tv_diyawu= (TextView) view.findViewById(R.id.tv_diyawu);
                viewholder.tv_daikuanedu= (TextView) view.findViewById(R.id.tv_daikuanedu);
                viewholder.tv_xiakuanqixian= (TextView) view.findViewById(R.id.tv_xiakuanqixian);
                viewholder.tv_yuecankaolilv= (TextView) view.findViewById(R.id.tv_yuecankaolilv);
                viewholder.tv_dibuxinxi= (TextView) view.findViewById(R.id.tv_dibuxinxi);
                viewholder.shuloudianzi_diyawu= (TextView) view.findViewById(R.id.shuloudianzi_diyawu);
            }else if (mList.get(position).getgType()==ERSHOUFANG){
                view= LayoutInflater.from(mContext).inflate(R.layout.xy_item_ershoufanganjie,null);
                viewholder.tv_ershoufananjie_title= (TextView) view.findViewById(R.id.tv_ershoufananjie_title);
                viewholder.tv_ershoufanganjie_shouqi= (TextView) view.findViewById(R.id.tv_ershoufanganjie_shouqi);
                viewholder.tv_ershoufang_shouqililv= (TextView) view.findViewById(R.id.tv_ershoufang_shouqililv);
                viewholder.tv_ershoufang_shouqinianxian= (TextView) view.findViewById(R.id.tv_ershoufang_shouqinianxian);
                viewholder.tv_ershoufanganjie_ertao= (TextView) view.findViewById(R.id.tv_ershoufanganjie_ertao);
                viewholder.tv_ershoufang_ertaolilv= (TextView) view.findViewById(R.id.tv_ershoufang_ertaolilv);
                viewholder.tv_ershoufang_ertaonianxian= (TextView) view.findViewById(R.id.tv_ershoufang_ertaonianxian);
                viewholder.tv_ershoufang_dibuxinxi= (TextView) view.findViewById(R.id.tv_ershoufang_dibuxinxi);
                viewholder.tv_ershoufang_dibuxinxi.setSelected(true);
            }
            view.setTag(viewholder);
        }else {
            viewholder= (Viewholder) view.getTag();
        }
        if (mList.get(position).getgType()==DANBAODAI){
            viewholder.tv_xinyong_danbaodai.setText(mList.get(position).getgName());
            viewholder.tv_xinyong_daikuanqixian.setText(mList.get(position).getgLoanTerm());
            viewholder.tv_xinyong_xiakuanqixian.setText(mList.get(position).getgEntertainTime());
            viewholder.tv_xinyon_yuecankaolilv.setText(mList.get(position).getgMonthlyRate());
            viewholder.tv_xinyong_daikuanedu.setText(mList.get(position).getgQuota());
            viewholder.tv_xinyong_dibuxinxi.setText(mList.get(position).getgMomo());
        }else if (mList.get(position).getgType()==SHOULOUGUOQIAO){
            viewholder.tv_fangchanshuloudianzi.setText(mList.get(position).getgName());
            viewholder.tv_daikuanqixian.setText(mList.get(position).getgLoanTerm());
            if (mList.get(position).getgCollateral()==0){
                viewholder.tv_diyawu.setText("无");
            }else if (mList.get(position).getgCollateral()==1){
                viewholder.tv_diyawu.setText("车辆");
            }else if (mList.get(position).getgCollateral()==2){
                viewholder.tv_diyawu.setText("房产");
            }

            viewholder.tv_daikuanedu.setText(mList.get(position).getgQuota());
            viewholder.tv_xiakuanqixian.setText(mList.get(position).getgEntertainTime());
            viewholder.tv_yuecankaolilv.setText(mList.get(position).getgMonthlyRate());
            viewholder.tv_dibuxinxi.setText(mList.get(position).getgMomo());
        }else  if (mList.get(position).getgType()==XINYONG){
                viewholder.shuloudianzi_diyawu.setVisibility(View.INVISIBLE);
            viewholder.tv_fangchanshuloudianzi.setText(mList.get(position).getgName());
            viewholder.tv_daikuanqixian.setText(mList.get(position).getgLoanTerm());
            viewholder.tv_diyawu.setVisibility(View.INVISIBLE);

            viewholder.tv_daikuanedu.setText(mList.get(position).getgQuota());
            viewholder.tv_xiakuanqixian.setText(mList.get(position).getgEntertainTime());
            viewholder.tv_yuecankaolilv.setText(mList.get(position).getgMonthlyRate());
            viewholder.tv_dibuxinxi.setText(mList.get(position).getgMomo());
        }else if (mList.get(position).getgType()==ERSHOUFANG){
//            if (mList.get(position).getgType()==ERSHOUFANG){
            viewholder.tv_ershoufananjie_title.setText(mList.get(position).getsName());
            viewholder.tv_ershoufanganjie_shouqi.setText(mList.get(position).getFirstNper());
            viewholder.tv_ershoufang_shouqililv.setText(mList.get(position).getFirstInterestRate());
            viewholder.tv_ershoufang_shouqinianxian.setText(mList.get(position).getFirstLife());
            viewholder.tv_ershoufanganjie_ertao.setText(mList.get(position).getSecendNper());
            viewholder.tv_ershoufang_ertaolilv.setText(mList.get(position).getSecendInterestRate());
            viewholder.tv_ershoufang_ertaonianxian.setText(mList.get(position).getSecendLife());
            viewholder.tv_ershoufang_dibuxinxi.setText(mList.get(position).getsMemo());
        }


        return view;
    }
    class Viewholder{
        //信用页面的参数
        TextView tv_xinyong_danbaodai;
        TextView tv_xinyong_daikuanqixian;
        TextView tv_xinyong_xiakuanqixian;
        TextView tv_xinyon_yuecankaolilv;
        TextView tv_xinyong_daikuanedu;
        TextView tv_xinyong_dibuxinxi;
        //二手房页面参数
        TextView tv_ershoufananjie_title;
        TextView tv_ershoufanganjie_shouqi;
        TextView tv_ershoufang_shouqililv;
        TextView tv_ershoufang_shouqinianxian;
        TextView tv_ershoufanganjie_ertao;
        TextView tv_ershoufang_ertaolilv;
        TextView tv_ershoufang_ertaonianxian;
        TextView tv_ershoufang_dibuxinxi;
        //售楼过桥页面参数
        TextView tv_fangchanshuloudianzi;
        TextView tv_daikuanqixian;
        TextView tv_diyawu;
        TextView tv_daikuanedu;
        TextView tv_xiakuanqixian;
        TextView tv_yuecankaolilv;
        TextView tv_dibuxinxi;
        TextView shuloudianzi_diyawu;
    }
}
