package com.bjxiyang.zhinengshequ.myapplication.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.UpdateVersion;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.DaiFuKuanActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.DiYongJuanActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.MainActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.MyXinXiActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.RegisteredActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.SDLoginActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.ShouHuoDiZhiActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.YiJianFanKuiActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.YouHuiJuanActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.ZheKouQuanActivity;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.CommonDialog;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.update.service.UpdateService;
import com.bjxiyang.zhinengshequ.myapplication.update.util.Util;
import com.bjxiyang.zhinengshequ.myapplication.view.CircleImageView;

import java.io.ByteArrayInputStream;

/**
 * Created by gll on 17-5-20.
 * 56214450
 */

public class MyFragment extends Fragment implements View.OnClickListener,MainActivity.FragmentBackListener{
    //个人中心
    private LinearLayout gerenxinxi;
    private LinearLayout changepassworldlativelayout;
    private LinearLayout jianchagengxin;
    private LinearLayout yijianfankui;
    private LinearLayout lianxikefu;
    //商超的内容
    private LinearLayout daifukuan;
    private LinearLayout daifahuo;
    private LinearLayout quanbudingdan;
    private LinearLayout youhuiquan;
    private LinearLayout diyongjuan;
    private LinearLayout daishouhuo;
    private LinearLayout tuikuantuihuo;
    private LinearLayout wodedizhi;

    //退出按钮
    private Button siginoutbutton;

    //显示用户名
    private TextView account_people;
    //显示用户头像
    private CircleImageView gerenxinxi_touxiang;
    private TextView my_daifukuan_tishi;
    private TextView my_daifahuo_tishi;
    private boolean isShow=false;


    private Context mContext;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext=getContext();
        view= inflater.inflate(R.layout.fragment_my,container,false);
        initView();
        getUserInfo();
        return view;
    }

    @Override
    public void onResume() {
        if (!UserManager.getInstance().getUser().getObj().getHeadPhotoUrl().equals("")&&
                UserManager.getInstance().getUser().getObj().getHeadPhotoUrl()!=null ){
            ImageLoaderManager.getInstance(getContext()).displayImage(gerenxinxi_touxiang,
                    UserManager.getInstance().getUser().getObj().getHeadPhotoUrl());
        }
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            if (!UserManager.getInstance().getUser().getObj().getHeadPhotoUrl().equals("")&&
                    UserManager.getInstance().getUser().getObj().getHeadPhotoUrl()!=null ){
                ImageLoaderManager.getInstance(getContext()).displayImage(gerenxinxi_touxiang,
                        UserManager.getInstance().getUser().getObj().getHeadPhotoUrl());
            }
        }
        super.onHiddenChanged(hidden);
    }

    public void initView(){

        daishouhuo= (LinearLayout) view.findViewById(R.id.daishouhuo);
        tuikuantuihuo= (LinearLayout) view.findViewById(R.id.tuikuantuihuo);
        wodedizhi= (LinearLayout) view.findViewById(R.id.wodedizhi);
        daishouhuo.setOnClickListener(this);
        tuikuantuihuo.setOnClickListener(this);
        wodedizhi.setOnClickListener(this);
        lianxikefu= (LinearLayout) view.findViewById(R.id.lianxikefu);
        lianxikefu.setOnClickListener(this);
        diyongjuan= (LinearLayout) view.findViewById(R.id.diyongjuan);
        diyongjuan.setOnClickListener(this);
        //个人中心
        gerenxinxi= (LinearLayout) view.findViewById(R.id.gerenxinxi);
        changepassworldlativelayout= (LinearLayout) view.findViewById(R.id.changepassworldlativelayout);
        jianchagengxin= (LinearLayout) view.findViewById(R.id.jianchagengxin);
        yijianfankui= (LinearLayout) view.findViewById(R.id.yijianfankui);
        //商超的内容
        daifukuan= (LinearLayout) view.findViewById(R.id.daifukuan);
        daifahuo= (LinearLayout) view.findViewById(R.id.daifahuo);
        quanbudingdan= (LinearLayout) view.findViewById(R.id.quanbudingdan);
        youhuiquan= (LinearLayout) view.findViewById(R.id.youhuiquan);
        siginoutbutton= (Button) view.findViewById(R.id.siginoutbutton);
        account_people= (TextView) view.findViewById(R.id.account_people);
        gerenxinxi_touxiang= (CircleImageView) view.findViewById(R.id.gerenxinxi_touxiang);
        if (!UserManager.getInstance().getUser().getObj().getHeadPhotoUrl().equals("")&&
                UserManager.getInstance().getUser().getObj().getHeadPhotoUrl()!=null ){
            ImageLoaderManager.getInstance(getContext()).displayImage(gerenxinxi_touxiang,
                    UserManager.getInstance().getUser().getObj().getHeadPhotoUrl());
        }
        my_daifukuan_tishi= (TextView) view.findViewById(R.id.my_daifukuan_tishi);
        my_daifahuo_tishi= (TextView) view.findViewById(R.id.my_daifahuo_tishi);

        gerenxinxi.setOnClickListener(this);
        changepassworldlativelayout.setOnClickListener(this);
        jianchagengxin.setOnClickListener(this);
        yijianfankui.setOnClickListener(this);

        daifukuan.setOnClickListener(this);
        daifahuo.setOnClickListener(this);
        quanbudingdan.setOnClickListener(this);
        youhuiquan.setOnClickListener(this);

        siginoutbutton.setOnClickListener(this);
        gerenxinxi_touxiang.setOnClickListener(this);

    }

    //得到用户对象
    public void getUserInfo(){
        if (UserManager.getInstance().getUser()!=null){
            String phone = String.valueOf(UserManager.getInstance().getUser().getObj().getMobilePhone());
            account_people.setText(phone);
        }
    }
    //检查更新代码
    private void checkVersion() {
        DialogUntil.showLoadingDialog(mContext,"正在检查更新",true);
        RequestCenter.checkVersion(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();

                final UpdateVersion updateModel = (UpdateVersion) responseObj;

                if (updateModel.getCode().equals("1000")) {
                    UpdateVersion.ObjBean obj=updateModel.getObj();
                    if (Double.valueOf(Util.getVersionCode(getActivity()))<Double.valueOf(obj.getVerNo())) {
                        //说明有新版本,开始下载
                        CommonDialog dialog = new CommonDialog(getActivity(),
                                getString(R.string.update_new_version),
                                obj.getVerDescript(),
                                getString(R.string.cancel),
                                getString(R.string.update_install),
                                new CommonDialog.DialogClickListener() {
                                    @Override
                                    public void onDialogClick() {
                                        Intent intent = new Intent(getActivity(), UpdateService.class);
                                        intent.putExtra("APPURL", updateModel.getObj().getVerUrl());
                                        getActivity().startService(intent);
                                    }
                                });
                        dialog.setCancelable(false);
                        dialog.show();
                    } else {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                        dialog.setTitle("提示");
                        dialog.setCancelable(false);
                        dialog.setMessage("该版本已是最新版本");
                        dialog.setIcon(R.mipmap.ic_launcher);
                        dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                            }
                        });
                        dialog.show();
                        //弹出一个toast提示当前已经是最新版本等处理
                    }
                }else {
                    MyUntil.show(getContext(),updateModel.getMsg());
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();

                AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
                AlertDialog dialog=builder
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("检查更新")
                        .setMessage("网络连接失败")
                        .setPositiveButton("确定", null)
                        .show();

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            //跳转到个人信息页面
            case R.id.gerenxinxi:
                startIntent(MyXinXiActivity.class);
                break;
            //跳转到修改密码页面
            case R.id.changepassworldlativelayout:
                startIntent(RegisteredActivity.class);
                break;
            //检查更新
            case R.id.jianchagengxin:

                checkVersion();
                break;
            //跳转到意见反馈页面
            case R.id.yijianfankui:
                startIntent(YiJianFanKuiActivity.class);
                break;
            //待付款页面
            case R.id.daifukuan:
                intent=new Intent(getContext(), DaiFuKuanActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            //待发货页面
            case R.id.daifahuo:
                intent=new Intent(getContext(), DaiFuKuanActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);
                break;
            //待收货
            case R.id.daishouhuo:
                intent=new Intent(getContext(), DaiFuKuanActivity.class);
                intent.putExtra("type",3);
                startActivity(intent);
                break;
            //退款退货
            case R.id.tuikuantuihuo:
                intent=new Intent(getContext(), DaiFuKuanActivity.class);
                intent.putExtra("type",4);
                startActivity(intent);
                break;
            //我的地址
            case R.id.wodedizhi:
                startIntent(ShouHuoDiZhiActivity.class);
                break;

            //全部订单页面
            case R.id.quanbudingdan:
                intent=new Intent(getContext(), DaiFuKuanActivity.class);
                intent.putExtra("type",0);
                startActivity(intent);
                break;
            //优惠券页面
            case R.id.youhuiquan:
                intent=new Intent(getContext(), ZheKouQuanActivity.class);
                startActivity(intent);
                break;
            //抵用券页面

            case R.id.lianxikefu:
                Intent i = new Intent();
                i.setAction(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:4000080828"));
                startActivity(i);
                break;

            case R.id.diyongjuan:
                Intent intent1=new Intent(getActivity(), DiYongJuanActivity.class);
                startActivity(intent1);
                break;
            //退出登录
            case R.id.siginoutbutton:
                SPManager.getInstance().remove("mobilePhone");
                SPManager.getInstance().remove("communityId_one");
                UserManager.getInstance().removeUser();
                startIntent(SDLoginActivity.class);
                getActivity().finish();
                break;


        }
    }

    private void startIntent(Class c){
        Intent intent = new Intent(mContext, c);
        startActivity(intent);
    }
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        if(activity instanceof MainActivity){
            if (!isShow){
                ((MainActivity)activity).setBackListener(this);
                ((MainActivity)activity).setInterception(true);
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (isShow){
            ((MainActivity)getActivity()).setBackListener(null);
            ((MainActivity)getActivity()).setInterception(false);
        }
    }

    @Override
    public void onbackForward() {
        // 处理fragment的返回事件
        if (isShow){
            ((MainActivity)getActivity()).setBackListener(null);
            ((MainActivity)getActivity()).setInterception(false);
            isShow=false;
        }
        DialogUntil.closeLoadingDialog();

    }
    private Bitmap getBitmapFromSharedPreferences() {
        Bitmap bitmap = null;
        SharedPreferences sharedPreferences;
        sharedPreferences = mContext.getSharedPreferences("testSP", Context.MODE_PRIVATE);
        //第一步:取出字符串形式的Bitmap
        String imageString = sharedPreferences.getString("image", "");
        //第二步:利用Base64将字符串转换为ByteArrayInputStream
        byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
        if (byteArray.length == 0) {
            gerenxinxi_touxiang.setImageResource(R.drawable.c_img_touxiang);
        } else {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);

            //第三步:利用ByteArrayInputStream生成Bitmap
            bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
        }
        return bitmap;

    }
}
