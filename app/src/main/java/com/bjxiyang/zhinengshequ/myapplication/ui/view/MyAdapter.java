package com.bjxiyang.zhinengshequ.myapplication.ui.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.ByCom;
import com.bjxiyang.zhinengshequ.myapplication.model.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.model.OpenDoor;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.KaiMenYouXiActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.dialog.KaiMenYouXiDialog;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.MyDialog;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
    public List<ByCom.Obj> mList;
    private int lockId;
    private int customberId;
    public MyAdapter(List<ByCom.Obj> mList) {
        this.mList = mList;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_key,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.tv_open_door.setText(mList.get(position).getLockName());
        int selectColor=position%3;
        if (selectColor==0){
            viewHolder.iv_open_door.setBackgroundResource(R.drawable.a_btn_lan);
        }else if (selectColor==1){
            viewHolder.iv_open_door.setBackgroundResource(R.drawable.a_btn_zi);
        }else {
            viewHolder.iv_open_door.setBackgroundResource(R.drawable.a_btn_huang);
        }

        viewHolder.iv_open_door.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                DialogUntil.showLoadingDialog(v.getContext(),"正在开门",true);
                customberId= UserManager.getInstance().getUser().getObj().getC_memberId();
                lockId=mList.get(position).getLockId();
                String url= XY_Response.URL_OPENDOOR+"customberId="+customberId+"&lockId="+lockId;
                RequestCenter.openDoor(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogUntil.closeLoadingDialog();
                        OpenDoor openDoor= (OpenDoor) responseObj;
                        if (openDoor.getCode().equals("1000")){
                            Toast.makeText(v.getContext(),"开门成功",Toast.LENGTH_LONG).show();
                            if (openDoor.getObj().getType()==1){
                                KaiMenYouXiDialog kaiMenYouXiDialog=new KaiMenYouXiDialog(v.getContext(),openDoor.getObj());
                                kaiMenYouXiDialog.show();
                            }
//                            showDialog("开门成功",v.getContext());
                        }else {
                            showDialog(openDoor.getMsg(),v.getContext());
//                            Toast.makeText(v.getContext(),fanHui.getMsg(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogUntil.closeLoadingDialog();
                        MyDialog.showDialog(v.getContext());
                    }
                });

            }
        });

    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return mList.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_open_door;
        public ImageView iv_open_door;
        public ViewHolder(View view){
            super(view);
            tv_open_door = (TextView) view.findViewById(R.id.tv_open_door);
            iv_open_door= (ImageView) view.findViewById(R.id.iv_open_door);
        }
    }
    private void showDialog(String message, Context mContext){

        final AlertDialog.Builder builder;
        builder=new AlertDialog.Builder(mContext)
                .setMessage(message)
        .setPositiveButton("确定", null);
        builder.show();


    }

    
}