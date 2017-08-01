package com.bjxiyang.zhinengshequ.myapplication.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.Banner;
import com.bjxiyang.zhinengshequ.myapplication.model.ByCom;
import com.bjxiyang.zhinengshequ.myapplication.model.SelectPlot;
import com.bjxiyang.zhinengshequ.myapplication.model.UpdateVersion;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.XYKeyAccredit;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.XYMenJinJiLuActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.XYXuanZeXiaoQuActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.XiaoQuGongGaoActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.MyAdapter;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.CommonDialog;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.update.service.UpdateService;
import com.bjxiyang.zhinengshequ.myapplication.update.util.Util;
import com.bjxiyang.zhinengshequ.myapplication.widgets.CommonActionSheetDialog;
import com.jude.rollviewpager.RollPagerView;

import java.util.ArrayList;
import java.util.List;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.RollViewAdapter;


public class HomeFragment extends Fragment implements View.OnClickListener {

    /**
     * RecyclerView
     */

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private List<ByCom.Obj> mListByCom;
    private Context mContext;
    /**
     * UI
     */
    private LinearLayout text_men;
    private LinearLayout bt_yaoshishouquan;
    private LinearLayout bt_menjinjilu;
    private LinearLayout xiaoqugonggao_home;
    private LinearLayout shenghuojiaofei_home;
    private ImageButton login_confirm;
    private ImageButton tishi_home;
    private TextView jifen;
    private TextView tishi_tishi_text;
    /**
     * Data
     */
    private Banner banner;
    private ByCom byCom;

    //初始化
    private RollPagerView mRollViewPager;

    private List<SelectPlot.Obj> mList;
    private List<Banner.Obj> list = new ArrayList<>();
    private TextView mTextSwitcher;
    private View view;
    public CommonActionSheetDialog commonActionSheetDialog;
    public int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1010;

    private static OngetData ongetData;
    private RollViewAdapter adapter;


    private int communityId=-1;
    private int nperId;
    private int floorId;
    private int unitId;
    private boolean isOne=true;

    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what==1000){
                mList= (List<SelectPlot.Obj>) msg.obj;
                if (mList==null){
                    Intent intent=new Intent(getContext(),XYXuanZeXiaoQuActivity.class);
                    startActivity(intent);
                }else {
//                    showActionSheet();
                }
            }else if (msg.what==2000){

            }
            return false;
        }
    });
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,container,false);
        initView();
        initDate();
        if (SPManager.getInstance().getBoolean("isOne",false)){
            nperId=SPManager.getInstance().getInt("nperId_one",0);
            communityId=SPManager.getInstance().getInt("communityId_one",0);

            floorId=SPManager.getInstance().getInt("floorId_one",0);
            unitId=SPManager.getInstance().getInt("unitId_one",0);
            mTextSwitcher.setText(SPManager.getInstance().getString("test_men",""));
            getDummyDatas();
        }
//        getData();
        return view;
    }

//    @Override
//    public void onStart() {
//        getData();
//        super.onStart();
//    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            if (communityId==-1&&adapter!=null){
                initDate();
            }
        }

        super.onHiddenChanged(hidden);
    }

    @Override
    public void onResume() {
        if (communityId!=-1&&adapter!=null){
            initDate();
        }
        super.onResume();
    }

    private void initView(){
        tishi_tishi_text= (TextView) view.findViewById(R.id.tishi_tishi_text);
        text_men= (LinearLayout) view.findViewById(R.id.text_men);
        jifen= (TextView) view.findViewById(R.id.jifen);
        tishi_home= (ImageButton) view.findViewById(R.id.tishi_home);
        tishi_home.setOnClickListener(this);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);

        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        //创建并设置Adapter

        shenghuojiaofei_home= (LinearLayout) view.findViewById(R.id.shenghuojiaofei_home);
        shenghuojiaofei_home.setOnClickListener(this);
        mTextSwitcher= (TextView) view.findViewById(R.id.mTextSwitcher);
        mRollViewPager= (RollPagerView) view.findViewById(R.id.roll_view_pager);
        //设置播放时间间隔
        mRollViewPager.setPlayDelay(2000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        bt_yaoshishouquan= (LinearLayout) view.findViewById(R.id.bt_yaoshishouquan);
        bt_yaoshishouquan.setOnClickListener(this);
        bt_menjinjilu= (LinearLayout) view.findViewById(R.id.bt_menjinjilu);
        bt_menjinjilu.setOnClickListener(this);
        xiaoqugonggao_home= (LinearLayout) view.findViewById(R.id.xiaoqugonggao_home);
        xiaoqugonggao_home.setOnClickListener(this);
        mTextSwitcher= (TextView) view.findViewById(R.id.mTextSwitcher);
        mTextSwitcher.setOnClickListener(this);
        login_confirm= (ImageButton) view.findViewById(R.id.login_confirm);
        login_confirm.setOnClickListener(this);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("sander","用户同意了");
            } else {
                Log.d("sander","用户不同意，没有办法，不做啥事情了");
            }
        }
    }
    public void showActionSheet(final List<SelectPlot.Obj> mList)
    {
        if (mList.size()>0){
                commonActionSheetDialog = new CommonActionSheetDialog(getActivity());

                for (int i=0;i<mList.size();i++) {
//                    for (int j=0;j<i;j++){
//                        if (!(mList.get(i).getCommunityName()+mList.get(i).getNperName())
//                                .equals((mList.get(j).getCommunityName()+mList.get(j).getNperName())))
//                        {
//                            commonActionSheetDialog.addMenuItem(mList.get(i).getCommunityName()
//                                    + mList.get(i).getNperName());
//                        }
//                        else {
//                            commonActionSheetDialog.addMenuItem(mList.get(i).getCommunityName()
//                                    + mList.get(i).getNperName());
//                        }
//
//                    }
                        if (i>0){
                            if (!(mList.get(i).getCommunityName()+mList.get(i).getNperName())
                                    .equals((mList.get(i-1).getCommunityName()+mList.get(i-1).getNperName())))
                            {
                                commonActionSheetDialog.addMenuItem(mList.get(i).getCommunityName()
                                        + mList.get(i).getNperName());
                            }
                        }else {
                            commonActionSheetDialog.addMenuItem(mList.get(i).getCommunityName()
                                    + mList.get(i).getNperName());
                        }

                }



//                for (SelectPlot.Obj item:mList){
//
//                    commonActionSheetDialog.addMenuItem(item.getCommunityName()
//                            +item.getNperName());
//                }
                commonActionSheetDialog.setMenuListener(new CommonActionSheetDialog.MenuListener() {
                    @Override
                    public void onItemSelected(int position, String item) {
                        nperId=mList.get(position).getNperId();
                        communityId=mList.get(position).getCommunityId();
                        floorId=mList.get(position).getFloorId();
                        unitId=mList.get(position).getUnitId();
                        SPManager.getInstance().putBoolean("isOne",true);
                        SPManager.getInstance().putInt("communityId_one",communityId);
                        SPManager.getInstance().putInt("communityId",communityId);
                        SPManager.getInstance().putInt("nperId_one",nperId);
                        SPManager.getInstance().putInt("floorId_one",floorId);
                        SPManager.getInstance().putInt("unitId_one",unitId);
                        SPManager.getInstance().putString("test_men",mList.get(position).getCommunityName()
                                +mList.get(position).getNperName());
                        mTextSwitcher.setText(
                                mList.get(position).getCommunityName()
                                +mList.get(position).getNperName());

                        getDummyDatas();

                    }
                    @Override
                    public void onCancel() {

                    }
                });
         commonActionSheetDialog.show();
        }else {
            Toast.makeText(getActivity(),"当前数据为空，请添加小区",Toast.LENGTH_LONG).show();
        }
    }
    //系统本地图片加载
     public void local() {
//         if (list.size()<0){
//             mRollViewPager.setBackgroundResource(R.drawable.a_img_banner);
//         }else {
             //设置适配器
             adapter = new RollViewAdapter(mRollViewPager);
             mRollViewPager.setAdapter(adapter);
             ongetData.OngetData(list);
//         }
     }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_yaoshishouquan:
                Intent intent=new Intent(getActivity(), XYKeyAccredit.class);
                startActivity(intent);
                break;
            case R.id.mTextSwitcher:
                getData();
                break;
            case R.id.bt_menjinjilu:
                Intent intent1=new Intent(getActivity(), XYMenJinJiLuActivity.class);
                startActivity(intent1);
//                Intent intent1=new Intent(getActivity(), MainActivityVideo.class);
//                startActivity(intent1);
                break;
            case R.id.xiaoqugonggao_home:
                Intent intent3=new Intent(getActivity(), XiaoQuGongGaoActivity.class);
                startActivity(intent3);
                break;
            case R.id.login_confirm:
                Intent intent2=new Intent(getActivity(), XYXuanZeXiaoQuActivity.class);
                startActivity(intent2);
                break;
            case R.id.shenghuojiaofei_home:
                startAli();

//                Intent intent4=new Intent(getActivity(), WuYeJiaoFeiActivity.class);
//                startActivity(intent4);
                break;
            case R.id.tishi_home:
                getData();
                break;

        }
    }
    private List<SelectPlot.Obj> getData(){

        mList=new ArrayList<>();
        DialogUntil.showLoadingDialog(getActivity(),"正在加载",true);
        String url= XY_Response.URL_FINDCOMMUNITYBYPER+"mobilePhone="+
                UserManager.getInstance().getUser().getObj().getMobilePhone();

        RequestCenter.findCommunityByPer(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                SelectPlot selectPlot= (SelectPlot) responseObj;
                DialogUntil.closeLoadingDialog();
                if (selectPlot.getCode().equals("1000")){
                    mList = selectPlot.getObj();
                    if (mList==null){
                        Intent intent = new Intent(getContext(),XYXuanZeXiaoQuActivity.class);
                        startActivity(intent);
                    }else {
                        if (mList.size()<0){
                            SPManager.getInstance().putBoolean("isOne",false);
                        }else {
                            showActionSheet(mList);
                        }
//                        if (SPManager.getInstance().getInt("communityId_one",-2)!=-2){
//
//                            nperId=SPManager.getInstance().getInt("nperId_one",0);
//                            communityId=SPManager.getInstance().getInt("communityId_one",0);
//                            floorId=SPManager.getInstance().getInt("floorId_one",0);
//                            unitId=SPManager.getInstance().getInt("unitId_one",0);
//                            mTextSwitcher.setText(SPManager.getInstance().getString("test_men",""));
//                            getDummyDatas();
//                        }
//                        if (isOne){
//                            isOne=false;

                    }

                }else {
//                    Toast.makeText(getActivity(),selectPlot.getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
            }
        });
        return mList;

    }

    public static void setOngetData(OngetData ongetDat){
        ongetData=ongetDat;
    }
    public interface OngetData{
        public void OngetData(List<Banner.Obj> mList);
    }
    private  List<Banner.Obj> initDate() {

        list=new ArrayList<>();
        String url= XY_Response.URL_BANNERLIST+"mobilePhone="+
                SPManager.getInstance().getString("mobilePhone",null);
        RequestCenter.bannerList(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                banner = (Banner) responseObj;
                if (banner.getCode().equals("1000")){
                    list= banner.getObj();
                    if (list.size()>0){
                        local();
                    }
                }
            }
            @Override
            public void onFailure(Object reasonObj) {
            }
        });
        return list;
    }
    private List<ByCom.Obj> getDummyDatas(){
        mListByCom = new ArrayList<>();

        DialogUntil.showLoadingDialog(getContext(),"正在加载",true);

        String url=XY_Response.URL_GETLOCKBYCOM+"mobilePhone="+
                UserManager.getInstance().getUser().getObj().getMobilePhone()+
                "&communityId="+communityId+
                "&nperId="+nperId+
                "&floorId="+floorId+
                "&unitId="+unitId;

        RequestCenter.getLockByCom(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                byCom= (ByCom) responseObj;
                if (byCom.getCode().equals("1000")){
                    mListByCom=byCom.getObj();
                    if (mListByCom.size()>0){
                        mAdapter = new MyAdapter(mListByCom);
                        mRecyclerView.setAdapter(mAdapter);
                        showText();
                    }else {
                        MyUntil.show(getContext(),"当前数据为空");
                    }
                }else {
                    showText_TiShi();
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
//                MyDialog.showDialog(getActivity());
            }
        });
        return mListByCom;
    }

    private void showText(){
        mRecyclerView.setVisibility(View.VISIBLE);
        text_men.setVisibility(View.GONE);
    }
    private void showText_TiShi(){
        mRecyclerView.setVisibility(View.GONE);
        text_men.setVisibility(View.VISIBLE);
        tishi_tishi_text.setText("无数据");
    }
    //检查更新代码
    private void checkVersion() {
        DialogUntil.showLoadingDialog(mContext, "正在检查更新", true);
        RequestCenter.checkVersion(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();

                final UpdateVersion updateModel = (UpdateVersion) responseObj;

                if (updateModel.getCode().equals("1000")) {
                    UpdateVersion.ObjBean obj = updateModel.getObj();
                    if (Double.valueOf(Util.getVersionCode(getActivity())) < Double.valueOf(obj.getVerSn())) {
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
                } else {
                    MyUntil.show(getContext(), updateModel.getMsg());
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                AlertDialog dialog = builder
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("检查更新")
                        .setMessage("网络连接失败")
                        .setPositiveButton("确定", null)
                        .show();

            }
        });
    }
//    20000193
    private void startAli(){
        String appPackageName="com.eg.android.AlipayGphone";
        try{
            Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(appPackageName);
            intent.setData(Uri.parse("alipays://platformapi/startapp?appId=20000193"));
            startActivity(intent);
        }catch(Exception e){
            Toast.makeText(getActivity(), "没有安装支付宝", Toast.LENGTH_LONG).show();
        }
//        Intent i = new Intent();
////        i.setAction(Intent.);
//        i.setData(Uri.parse("alipays://platformapi/startapp?appId=49"));
//        startActivity(i);
    }



}
