package com.bjxiyang.zhinengshequ.myapplication.ui.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.model.PermissionList;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.UserState;
import com.bjxiyang.zhinengshequ.myapplication.until.UserType;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.CircleImageView;
import com.bjxiyang.zhinengshequ.myapplication.view.MyDialog;

import java.util.List;

/**
 * Created by gll on 17-5-23.
 */

public class XYKeyaccreditAdapter extends BaseAdapter{
    private ImageLoaderManager  manager;
    private Context mContext;
    private List<PermissionList.Obj> mList;

    private int communityId;
    private int nperId;
    private int floorId;
    private int unitId;
    private int doorId;
    private int status;
    private int activeUser;
    private int permissionId;
    private Viewholder viewholder;
    private PermissionList.Obj obj;
    private PermissionList.Obj obj1;
    private PermissionList.Obj obj2;
    private int c_memberId = UserManager.getInstance().getUser().getObj().getC_memberId();
    public XYKeyaccreditAdapter(Context mContext, List mList) {
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

        viewholder=null;
        if (view==null){
            viewholder=new Viewholder();
            view= LayoutInflater.from(mContext).inflate(R.layout.xy_item_keyaccredit,null);
            viewholder.iv_touxiang= (CircleImageView) view.findViewById(R.id.iv_touxiang);
            viewholder.name= (TextView) view.findViewById(R.id.name);
            viewholder.iv_zukejiaren= (TextView) view.findViewById(R.id.iv_zukejiaren);
            viewholder.item_address= (TextView) view.findViewById(R.id.item_address);
            viewholder.item_date= (TextView) view.findViewById(R.id.item_date);
            viewholder.ib_jinyong_qiyong= (ImageButton) view.findViewById(R.id.ib_jinyong_qiyong);
            viewholder.tv_jintog_qiyong= (TextView) view.findViewById(R.id.tv_jintog_qiyong);
            viewholder.item_xiugai= (TextView) view.findViewById(R.id.item_xiugai);
            view.setTag(viewholder);
        }else {
            viewholder= (Viewholder) view.getTag();
        }
        manager=ImageLoaderManager.getInstance(mContext);
        //图像处理直接用框架
        //文本处理直接拿服务器数据
        //设置头像 地址为null
        if (mList.get(position).getHeadPhotoUrl()==null||mList.get(position).getHeadPhotoUrl().equals("")){

        }else {
            manager.displayImage(viewholder.iv_touxiang,mList.get(position).getHeadPhotoUrl());

        }
        viewholder.name.setText(mList.get(position).getNickName());

            if (mList.get(position).getC_memberId()==c_memberId){
                    activeUser=mList.get(position).getRoleType();
            }
            //&&c_memberId==mList.get(position).getC_memberId()
//            if (activeUser==UserType.USER_OWNER&&activeUser==UserType.USER_LESSEE){
//                viewholder.ib_jinyong_qiyong.setVisibility(View.GONE);
//                viewholder.item_xiugai.setVisibility(View.GONE);
//            }else {
//                viewholder.ib_jinyong_qiyong.setVisibility(View.VISIBLE);
//                viewholder.item_xiugai.setVisibility(View.VISIBLE);
//            }
            if (mList.get(position).getRoleType()==(UserType.USER_FOLK)){
                viewholder.ib_jinyong_qiyong.setVisibility(View.VISIBLE);
                viewholder.item_xiugai.setVisibility(View.VISIBLE);
                //如果是家人类型，就显示家人类型的图片
                viewholder.iv_zukejiaren.setBackgroundResource(R.drawable.a_btn_usertype);
                viewholder.iv_zukejiaren.setText("业主家人");
            }else if (mList.get(position).getRoleType()==(UserType.USER_OWNER)) {
                if (mList.get(position).getStatus()==UserState.START_USING){
                    viewholder.ib_jinyong_qiyong.setVisibility(View.GONE);
                    viewholder.item_xiugai.setVisibility(View.GONE);
                }else {
                    viewholder.ib_jinyong_qiyong.setVisibility(View.INVISIBLE);
                    viewholder.item_xiugai.setVisibility(View.VISIBLE);
                }

                //如果是业主类型，就显示业主类型的图片
                viewholder.iv_zukejiaren.setBackgroundResource(R.drawable.a_btn_usertype);
                viewholder.iv_zukejiaren.setText("业主");
            }else if (mList.get(position).getRoleType()==(UserType.USER_LESSEE)){
                //否则就是租客类型的
                viewholder.iv_zukejiaren.setBackgroundResource(R.drawable.a_btn_usertype);
                viewholder.iv_zukejiaren.setText("租户");
                if (mList.get(position).getC_memberId()!=UserManager.getInstance().getUser().getObj().getC_memberId()){

                    viewholder.ib_jinyong_qiyong.setVisibility(View.VISIBLE);
                    viewholder.item_xiugai.setVisibility(View.VISIBLE);
                }else {
                    viewholder.ib_jinyong_qiyong.setVisibility(View.GONE);
                    viewholder.item_xiugai.setVisibility(View.GONE);
                }


            }else if(mList.get(position).getRoleType()==(UserType.USER_LESSEE_HOME)){
                viewholder.ib_jinyong_qiyong.setVisibility(View.VISIBLE);
                viewholder.item_xiugai.setVisibility(View.VISIBLE);
                //否则就是租客家人
                viewholder.iv_zukejiaren.setBackgroundResource(R.drawable.a_btn_usertype);
                viewholder.iv_zukejiaren.setText("租户家人");
            }
            obj=mList.get(position);
            viewholder.item_address.setText(obj.getCommunityName()+(obj.getNperName())+"-"+obj.getFloorName()
                    +"号楼-"+obj.getUnitName()+"单元-"+obj.getDoorName()+"室");
            viewholder.item_date.setText(obj.getAdd_time());

        if (obj.getStatus()== UserState.FORBIDDEN){
            viewholder.ib_jinyong_qiyong.setBackgroundResource(R.drawable.c_btn_jiyong);
            viewholder.tv_jintog_qiyong.setText("已禁用");
        }else if (obj.getStatus()==UserState.START_USING){
            viewholder.ib_jinyong_qiyong.setBackgroundResource(R.drawable.c_btn_jinyong);
            viewholder.tv_jintog_qiyong.setText("已启用");
        }
        else if (obj.getStatus()==UserState.SHENQINGZHONG){
            viewholder.tv_jintog_qiyong.setText("申请中");
            viewholder.ib_jinyong_qiyong.setBackgroundResource(R.drawable.c_btn_jiyong);
        }else if (obj.getStatus()==UserState.JUJUE){
            viewholder.tv_jintog_qiyong.setText("已拒绝");
            viewholder.ib_jinyong_qiyong.setBackgroundResource(R.drawable.c_btn_jiyong);
        }
        final int position1=position;
        viewholder.ib_jinyong_qiyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj1=mList.get(position1);
                communityId=obj1.getCommunityId();
                nperId=obj1.getNperId();
                floorId=obj1.getFloorId();
                unitId=obj1.getUnitId();
                doorId=obj1.getDoorId();
                int status1 =obj1.getStatus();
                permissionId=obj1.getPermissionId();
                DialogUntil.showLoadingDialog(mContext,"正在提交",true);

                int status2=0;
                if (UserState.START_USING==status1){
                    status2=UserState.FORBIDDEN;
                }else if (UserState.FORBIDDEN==status1||UserState.SHENQINGZHONG==status1){
                    status2=UserState.START_USING;
                }
        String url=XY_Response.URL_UPDATEPERMISSIONS
        +"mobilePhone="+UserManager.getInstance().getUser().getObj().getMobilePhone()
        +"&communityId="+communityId+"&nperId="+nperId+"&floorId="+floorId+"&unitId="
        +unitId+"&doorId="+doorId+"&status="+status2+"&permissionId="+permissionId;

                RequestCenter.updatePermission(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogUntil.closeLoadingDialog();
                        FanHui fanHui= (FanHui) responseObj;
                        if (fanHui.getCode().equals("1000")){
                            //这些数据需要上传给服务器的
                            if (obj1.getStatus()== UserState.FORBIDDEN||UserState.SHENQINGZHONG==obj1.getStatus()){
                                obj1.setStatus(UserState.START_USING);
                            }else {
                                obj1.setStatus(UserState.FORBIDDEN);
                            }

                            notifyDataSetChanged();
//                            Message message=new Message();
//                            message.what=1000;
//                            message.obj=obj1;
//                            handler.sendMessage(message);
                        }else {
                            Toast.makeText(mContext,fanHui.getMsg(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogUntil.closeLoadingDialog();
                        MyDialog.showDialog((Activity) mContext,"请检查网络链接");
                    }
                });


            }
        });
        viewholder.item_xiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj2=mList.get(position1);
                communityId=obj2.getCommunityId();
                nperId=obj2.getNperId();
                floorId=obj2.getFloorId();
                unitId=obj2.getUnitId();
                doorId=obj2.getDoorId();
                status=obj2.getStatus();
                permissionId=obj2.getPermissionId();
                DialogUntil.showLoadingDialog(mContext,"正在提交",true);
                String url1= XY_Response.URL_DELETEPERMISSIONS+"mobilePhone="+
                        UserManager.getInstance().getUser().getObj().getMobilePhone()+
                        "&communityId="+communityId+"&nperId="+nperId+"&floorId="+floorId+
                        "&unitId="+unitId+"&doorId="+doorId+
                        "&permissionId="+permissionId;

                RequestCenter.deletePermission(url1, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogUntil.closeLoadingDialog();
                        FanHui fanHui= (FanHui) responseObj;
                        if (fanHui.getCode().equals("1000")){
//                            Toast.makeText(mContext,"连接成功",Toast.LENGTH_LONG).show();
                            mList.remove(position1);
                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogUntil.closeLoadingDialog();
                        MyDialog.showDialog((Activity) mContext,"请检查网络连接");
                    }
                });
            }
        });
        return view;
    }

    private class Viewholder{
        //头像
        CircleImageView iv_touxiang;
        //姓名
        TextView name;
        //租客还是家人
        TextView iv_zukejiaren;
        //地址
        TextView item_address;
        //时间
        TextView item_date;
        //禁用还是启用
        ImageButton ib_jinyong_qiyong;
        //显示禁用还是启用
        TextView tv_jintog_qiyong;
        //修改按钮
        TextView item_xiugai;

    }
}
