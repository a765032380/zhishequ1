package com.bjxiyang.zhinengshequ.myapplication.ui.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DiZhiDelete;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DiZhiList;
import com.bjxiyang.zhinengshequ.myapplication.bianlidianstatus.BianLiDianStatus;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.BianLiDianResponse;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.AddShouHuoDiZhiActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.PlaceOrderActivity;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29 0029.
 */

public class ShouHuoDiZhiAdapter extends BaseAdapter {
    private Context mContext;
    private List<DiZhiList.ResultBean> mList;


    private boolean isSelect=true;
    public ShouHuoDiZhiAdapter(Context mContext, List<DiZhiList.ResultBean> mList) {
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
        ViewHolder viewHolder=null;
        if (view==null){
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(mContext).inflate(R.layout.item_shouhuodizhi,null);
            viewHolder.tv_item_shouhuodizhi_dizhi=
                    (TextView) view.findViewById(R.id.tv_item_shouhuodizhi_dizhi);
            viewHolder.tv_item_shouhuodizhi_name=
                    (TextView) view.findViewById(R.id.tv_item_shouhuodizhi_name);
            viewHolder.tv_item_shouhuodizhi_sex=
                    (TextView) view.findViewById(R.id.tv_item_shouhuodizhi_sex);
            viewHolder.tv_item_shouhuodizhi_phone=
                    (TextView) view.findViewById(R.id.tv_item_shouhuodizhi_phone);
            viewHolder.tv_item_shouhuodizhi_peisongfanwei=
                    (TextView) view.findViewById(R.id.tv_item_shouhuodizhi_peisongfanwei);
            viewHolder.iv_item_shouhuodizhi_button=
                    (ImageView) view.findViewById(R.id.iv_item_shouhuodizhi_button);
            viewHolder.rl_item_bianji_button=
                    (RelativeLayout) view.findViewById(R.id.rl_item_bianji_button);
            viewHolder.rl_item_shanchu_button=
                    (RelativeLayout) view.findViewById(R.id.rl_item_shanchu_button);

            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tv_item_shouhuodizhi_dizhi.setText(
                mList.get(position).getCommunityName()+mList.get(position).getNperName()
                        +mList.get(position).getUnitName()+mList.get(position).getDoorName());
        viewHolder.tv_item_shouhuodizhi_name.setText(mList.get(position).getName());
        if (mList.get(position).getSex()==1){
            viewHolder.tv_item_shouhuodizhi_sex.setText("先生");
        }
        if (mList.get(position).getSex()==2){
            viewHolder.tv_item_shouhuodizhi_sex.setText("女士");
        }
        viewHolder.tv_item_shouhuodizhi_phone.setText(mList.get(position).getPhone());

        viewHolder.tv_item_shouhuodizhi_peisongfanwei.setText("鹏景阁大厦");
//        if (isSelect){
//            viewHolder.iv_item_shouhuodizhi_button.setImageResource(R.drawable.b_btn_xuanze_pre);
//        }else {
//            viewHolder.iv_item_shouhuodizhi_button.setImageResource(R.drawable.h_btn_xuanze);
//        }


        viewHolder.rl_item_shanchu_button.setOnClickListener(new View.OnClickListener() {

//            addressId
//                    name
//            sex
//                    phone
//            communityId
//                    nperId
//            floorId
//                    unitId

            @Override
            public void onClick(View v) {
                String url_dizhi_delete= BianLiDianResponse.URL_ORDER_USER_ADDRESS_DELETE+
                        "addressId="+mList.get(position).getId();
                RequestCenter.order_user_address_delete(url_dizhi_delete, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DiZhiDelete diZhiDelete= (DiZhiDelete) responseObj;
                        if (diZhiDelete.getCode()== BianLiDianStatus.STATUS_CODE_SUCCESS){
                            mList.remove(position);
                            notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onFailure(Object reasonObj) {

                    }
                });


            }
        });
        viewHolder.rl_item_bianji_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, AddShouHuoDiZhiActivity.class);
                intent.putExtra("type",1);
                intent.putExtra("addressId",mList.get(position).getId());
                mContext.startActivity(intent);


            }
        });

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
