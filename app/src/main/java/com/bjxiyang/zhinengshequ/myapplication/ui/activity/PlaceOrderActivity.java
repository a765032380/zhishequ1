package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.baisi.myapplication.okhttp.request.RequestParams;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DiZhiList;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DingDan;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.GouWuChe;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.ShangPinList;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.Text;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.TiJiaoDingDan;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.YouHuiQuan;
import com.bjxiyang.zhinengshequ.myapplication.bianlidianstatus.BianLiDianStatus;
import com.bjxiyang.zhinengshequ.myapplication.dialog.ShouHuoDiZhiDialog;
import com.bjxiyang.zhinengshequ.myapplication.dialog.YouHuiQuanDialog;
import com.bjxiyang.zhinengshequ.myapplication.greendao.DaoUtils;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.BianLiDianResponse;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.PlaceOrderAdapter;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.UnitGetGouWuChe;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class PlaceOrderActivity extends MySwipeBackActivity
        implements View.OnClickListener{

    private RelativeLayout rl_tijiaodingdan_fanghui;
    private ListView lv_tijiaodingdan;
    private LinearLayout ly_tijiaodingdan_xuanzeshouhuodizhi;
    private TextView tv_tijiaodingdan_qujiesuan;
    private LinearLayout lv_songhuoshijian;
    private PlaceOrderAdapter adapter;
    private TextView tv_tijiaodingdan_count;
    private EditText et_tijiaodingdan_beizhu;
    private TextView tv_dizhi_name;
    private LinearLayout ly_tijiaodingdan_youhuiquan;
    private TextView tv_dianming;
    private TextView tv_tijiaodingdan_money;
    private TextView youhuijian_tishi;
    private double fee=0;

    private int userAddressId;
    private int sellerId;
    private String product;
    private ShouHuoDiZhiDialog shouHuoDiZhiDialog;
    private String dizhiName;
    private List<GouWuChe> mList;
    private List<GouWuChe> list=new ArrayList<>();
    private int type;
    private int spId;
    private int count=0;
    private double jiage;
    private int couponId=0;
    private YouHuiQuan.ResultBean resultBean1;
    private TiJiaoDingDan.ResultBean resultBean;
    DecimalFormat df=new DecimalFormat("0.##");
    private ShangPinList.Result.Products products;

    public static PlaceOrderActivity placeorder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_tijiaodingdan);
        placeorder=this;
        initUI();
        initData();
    }

    private void initUI() {
        youhuijian_tishi= (TextView) findViewById(R.id.youhuijian_tishi);
        tv_tijiaodingdan_money= (TextView) findViewById(R.id.tv_tijiaodingdan_money);
        tv_dianming= (TextView) findViewById(R.id.tv_dianming);
        ly_tijiaodingdan_youhuiquan= (LinearLayout) findViewById(R.id.ly_tijiaodingdan_youhuiquan);
        tv_dizhi_name= (TextView) findViewById(R.id.tv_dizhi_name);
        et_tijiaodingdan_beizhu= (EditText) findViewById(R.id.et_tijiaodingdan_beizhu);
        tv_tijiaodingdan_count= (TextView) findViewById(R.id.tv_tijiaodingdan_count);
        lv_songhuoshijian= (LinearLayout) findViewById(R.id.lv_songhuoshijian);
        tv_tijiaodingdan_qujiesuan= (TextView) findViewById(R.id.tv_tijiaodingdan_qujiesuan);
        ly_tijiaodingdan_xuanzeshouhuodizhi= (LinearLayout) findViewById(R.id.ly_tijiaodingdan_xuanzeshouhuodizhi);
        rl_tijiaodingdan_fanghui= (RelativeLayout) findViewById(R.id.rl_tijiaodingdan_fanghui);
        lv_tijiaodingdan= (ListView) findViewById(R.id.lv_tijiaodingdan);
        lv_songhuoshijian.setOnClickListener(this);
        tv_tijiaodingdan_qujiesuan.setOnClickListener(this);
        ly_tijiaodingdan_xuanzeshouhuodizhi.setOnClickListener(this);
        rl_tijiaodingdan_fanghui.setOnClickListener(this);
        ly_tijiaodingdan_youhuiquan.setOnClickListener(this);
        if (SPManager.getInstance().getBoolean("isDizhi",false)){
            userAddressId=SPManager.getInstance().getInt("userAddressId",0);
            tv_dizhi_name.setText(SPManager.getInstance().getString("dizhiName",null));
        }

    }

    private void initData() {
        tv_dianming.setText(SPManager.getInstance().getString("shopName",""));
        Intent intent=getIntent();
        type=intent.getIntExtra("type",0);
//        tv_tijiaodingdan_money.setText("￥"+df.format((double)UnitGetGouWuChe.getZongJia()));
//        if (intent.getIntExtra("userAddressId",0)!=0){
//            userAddressId=intent.getIntExtra("userAddressId",0);
//        }
        mList = DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
        for (int i=0;i<mList.size();i++){
            if (mList.get(i).getCount()==0){
                DaoUtils.getStudentInstance().deleteObject(mList.get(i));
            }
        }

        mList = DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
        if (type==0){
            spId=intent.getIntExtra("spId",0);
            sellerId=SPManager.getInstance().getInt("sellerId",0);
//            intent.getIntExtra("sellerId",0);
            products= (ShangPinList.Result.Products) intent.getSerializableExtra("product");
            Gson gson=new Gson();
            product=gson.toJson(products);
            for (int i=0;i<mList.size();i++){
                if (mList.get(i).getSpid()==spId){
                    list.add(mList.get(i));
                    count=list.get(0).getCount();
                    if(list.get(0).getIfDiscount()==0){
                        jiage=list.get(0).getCount()*list.get(0).getPrice();
                    }else {
                        jiage=list.get(0).getCount()*list.get(0).getDiscountPrice();
                    }

                }
            }
            adapter=new PlaceOrderAdapter(this,list);
            lv_tijiaodingdan.setAdapter(adapter);
        }else {

            for (int i=mList.size()-1;i>=0;i--){
                if (mList.get(i).getSellerId() != SPManager.getInstance().getInt("sellerId", 0)) {
                    mList.remove(i);
                }
            }

            for (int i=0;i<mList.size();i++){

                if(mList.get(i).getIfDiscount()==0){
                    jiage+=mList.get(i).getCount()*mList.get(i).getPrice();
                }else {
                    jiage+=mList.get(i).getCount()*mList.get(i).getDiscountPrice();
                }
                count+=mList.get(i).getCount();
            }
            adapter=new PlaceOrderAdapter(this,mList);
            lv_tijiaodingdan.setAdapter(adapter);

        }
        tv_tijiaodingdan_money.setText("￥"+df.format(jiage/100));
        tv_tijiaodingdan_count.setText(count+"");

    }
    private void getData(){


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回的按键
            case R.id.rl_tijiaodingdan_fanghui:
                finish();
                break;
            //去结算,需要传输金额
            case R.id.tv_tijiaodingdan_qujiesuan:
                if (userAddressId==0){
                    MyUntil.show(PlaceOrderActivity.this,"请选择收货地址");
                    break;
                }
                RequestParams params=new RequestParams();
                String url_tijiaodingdan= BianLiDianResponse.URL_ORDER_SUBMIT;
                mList = DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
                for (int i=mList.size()-1;i>=0;i--){
                    if (mList.get(i).getSellerId() != SPManager.getInstance().getInt("sellerId", 0)) {
                        mList.remove(i);
                    }
                }
                
                String productJson1="[";
                String productJson="";
                for (int i=0;i<mList.size();i++){
                    sellerId=mList.get(0).getSellerId();
                    if (mList.get(i).getCount()==0){
                        DaoUtils.getStudentInstance().deleteObject(mList.get(i));
                    }
//                    productJson="{\"productId\":"+mList.get(i).getSpid()+",\"num\":"+mList.get(i).getCount()+"}";
                    
                    if (type==10) {
                        if (i != mList.size() - 1) {
                            productJson = productJson + "{\"productId\":" + mList.get(i).getSpid() + ",\"num\":" + mList.get(i).getCount() + "},";
                        } else {
                            productJson = productJson + "{\"productId\":" + mList.get(i).getSpid() + ",\"num\":" + mList.get(i).getCount() + "}";
                        }
                    }else {
//                            if (mList.get(i).getSellerId() != SPManager.getInstance().getInt("sellerId", 0)) {
                                if (mList.get(i).getSpid() == products.getId()) {
//                            if (i != mList.size() - 1) {
//                                productJson = productJson + "{\"productId\":" + mList.get(i).getSpid() + ",\"num\":" + mList.get(i).getCount() + "},";
//                            } else {
                                    productJson = productJson + "{\"productId\":" + mList.get(i).getSpid() + ",\"num\":" + mList.get(i).getCount() + "}";
//                            }
                                }
                            
//                        }
                        
                        
                        

                    }
                }
                productJson=productJson1+productJson+"]";
                Log.i("YYYY","测试传递参数:"+productJson);
//                "userAddressId=1&sellerId=1&sendTime=2017-07-01 " +
//                        "12:20:00&expectSendTime=2017-07-01 12:30:00&remark=sss&produ" +
//                        "ct=[{\"productId\":1,\"num\":2},{\"productId\":2,\"num\":2}]&couponId=0";
//                        "userAddressId="+userAddressId+"&sellerId="+sellerId;
//                String pr="[{\"productId\":"+products.getId()+"}]";
//
                Log.i("YYYY",BianLiDianResponse.URL_ORDER_SUBMIT+
                        "userAddressId="+userAddressId+"&sellerId="+sellerId+
                        "&product="+productJson);

                params.put("userAddressId",String.valueOf(userAddressId));
                params.put("sellerId",String.valueOf(SPManager.getInstance().getInt("sellerId",0)));
                params.put("product",productJson);
//                params.put("sendTime","");
//                params.put("expectSendTime","");
                params.put("remark",String.valueOf(et_tijiaodingdan_beizhu.getText()));

                if (couponId!=0){
                    params.put("couponId",String.valueOf(couponId));
                }
                params.put("private-token",SPManager.getInstance().getString("loginKey",""));
                Log.i("YYYY",String.valueOf(couponId));
                RequestCenter.order_submit(url_tijiaodingdan, params,new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        TiJiaoDingDan tiJiaoDingDan = (TiJiaoDingDan) responseObj;
                        if (tiJiaoDingDan.getCode()== BianLiDianStatus.STATUS_CODE_SUCCESS){
                            resultBean=tiJiaoDingDan.getResult();

                            Intent intent=new Intent(PlaceOrderActivity.this,ZhiFuXiangQing.class);
                            intent.putExtra("type",1);
                            intent.putExtra("spId",spId);
                            Log.i("YYYY",resultBean.getAfterDiscountAmount()+"---"+
                            resultBean.getTotalAmount());
                            if (fee==0){
                                fee=resultBean.getAfterDiscountAmount();
                            }

                            intent.putExtra("fee",fee);
                            intent.putExtra("orderId",resultBean.getId());
                            startActivity(intent);
                            finish();
                        }else {
                            MyUntil.show(PlaceOrderActivity.this,tiJiaoDingDan.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {


                    }
                });
                break;
            //选择收货地址
            case R.id.ly_tijiaodingdan_xuanzeshouhuodizhi:

//                Intent intent2=new Intent(PlaceOrderActivity.this,ShouHuoDiZhiActivity.class);
//                startActivity(intent2);
//                ShouHuoDiZhiActivity.shouHuoDiZhiActivity.setOngetdata(new ShouHuoDiZhiActivity.Ongetdata() {
//
//                    @Override
//                    public void getData(DiZhiList.ResultBean data) {
//                        userAddressId=data.getId();
//                        dizhiName =  data.getCommunityName()+
//                                data.getFloorName()+
//                                data.getUnitName()+
//                                data.getDoorName();
//                        tv_dizhi_name.setText(dizhiName);
//                    }
//                });


                shouHuoDiZhiDialog=new ShouHuoDiZhiDialog(this);
                shouHuoDiZhiDialog.setOngetdata(new ShouHuoDiZhiDialog.OngetData() {
                    @Override
                    public void getDizhiId(DiZhiList.ResultBean data) {
                        if (data!=null) {
                            userAddressId = data.getId();
                            dizhiName = data.getCommunityName() +
                                    data.getFloorName() +
                                    data.getUnitName() +
                                    data.getDoorName();
                            tv_dizhi_name.setText(dizhiName);
                            SPManager.getInstance().putBoolean("isDizhi",true);
                            SPManager.getInstance().putInt("userAddressId",userAddressId);
                            SPManager.getInstance().putString("dizhiName",dizhiName);

                        }else {
                            MyUntil.show(PlaceOrderActivity.this,"请添加收货地址");
                        }
                    }
                });
                shouHuoDiZhiDialog.show();
//
//                Intent intent=new Intent(PlaceOrderActivity.this,ShouHuoDiZhiActivity.class);
//                startActivity(intent);
                break;
            //选择送货时间
            case R.id.lv_songhuoshijian:

                break;
            //选择优惠券
            case R.id.ly_tijiaodingdan_youhuiquan:
                YouHuiQuanDialog youHuiQuanDialog;
                youHuiQuanDialog=new YouHuiQuanDialog(this,sellerId,jiage);
                youHuiQuanDialog.setOngetYouhuiquan(new YouHuiQuanDialog.OngetData() {
                    @Override
                    public void getYouhuiquan(YouHuiQuan.ResultBean resultBean2) {
                        if (resultBean2!=null) {
                            resultBean1 = resultBean2;
                            couponId = resultBean1.getId();
                            youhuijian_tishi.setText("享店铺" + (double)resultBean1.getDiscount()/10 + "折");
                            fee = jiage * resultBean1.getDiscount() / 100;
                            tv_tijiaodingdan_money.setText(String.valueOf(fee / 100));
                        }else {
                            youhuijian_tishi.setText("当前无可用优惠券");
                            tv_tijiaodingdan_money.setText(String.valueOf(jiage / 100));
                        }
                    }
//                    @Override
//                    public void getDizhiId(DiZhiList.ResultBean data) {
//                        userAddressId=data.getId();
//                        dizhiName =  data.getCommunityName()+
//                                data.getFloorName()+
//                                data.getUnitName()+
//                                data.getDoorName();
//                        tv_dizhi_name.setText(dizhiName);
//                    }
                });
                youHuiQuanDialog.show();
//
                break;
        }
    }



}
