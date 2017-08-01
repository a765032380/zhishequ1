package com.bjxiyang.zhinengshequ.myapplication.ui.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.model.ProPayOrder;
import com.bjxiyang.zhinengshequ.myapplication.model.WuYeJiaoFei;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.ZhiFuXiangQing;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class WuYeJiaoFeiAdapter extends BaseAdapter {



    public static String propertyId="";
    public static String payOrder="";

    private Context mContext;
    private List<WuYeJiaoFei.Obj> mList;

    public WuYeJiaoFeiAdapter(Context mContext, List<WuYeJiaoFei.Obj> mList) {
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
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_wuyejiaofei, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_item_wuyejiaofei_date = (TextView) view.findViewById(R.id.tv_item_wuyejiaofei_date);
//            viewHolder.tv_item_wuyejiaofei_time = (TextView) view.findViewById(R.id.tv_item_wuyejiaofei_time);
            viewHolder.iv_item_wuyejiaofei_biaoji = (ImageView) view.findViewById(R.id.iv_item_wuyejiaofei_biaoji);
            viewHolder.tv_item_wuyejiaofei_dizhi = (TextView) view.findViewById(R.id.tv_item_wuyejiaofei_dizhi);
            viewHolder.tv_item_wuyejiaofei_money = (TextView) view.findViewById(R.id.tv_item_wuyejiaofei_money);
            viewHolder.iv_item_wuyejiaofei_jiaofei = (ImageView) view.findViewById(R.id.iv_item_wuyejiaofei_jiaofei);
            viewHolder.tv_item_wuyejiaofei_wuyegongsiname = (TextView) view.findViewById(R.id.tv_item_wuyejiaofei_wuyegongsiname);
            viewHolder.jiaofeizhuangtai = (TextView) view.findViewById(R.id.jiaofeizhuangtai);
            viewHolder.ll_item_gone_text = (LinearLayout) view.findViewById(R.id.ll_item_gone_text);
            viewHolder.ll_item_gone_jiaofei = (LinearLayout) view.findViewById(R.id.ll_item_gone_jiaofei);
            viewHolder.tv_item_wuyejiaofei_dingdanhao = (TextView) view.findViewById(R.id.tv_item_wuyejiaofei_dingdanhao);
            viewHolder.tv_item_wuyejiaofei_jiaofeishijian = (TextView) view.findViewById(R.id.tv_item_wuyejiaofei_jiaofeishijian);
            viewHolder.tv_item_wuyejiaofei_zhifufangshi = (TextView) view.findViewById(R.id.tv_item_wuyejiaofei_zhifufangshi);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_item_wuyejiaofei_date.setText(mList.get(position).getAddTime());
//        viewHolder.tv_item_wuyejiaofei_time.setText(mList.get(position).getTime());
        viewHolder.tv_item_wuyejiaofei_dizhi.setText(
                mList.get(position).getCommunityName()+
                mList.get(position).getNperName()+
                mList.get(position).getFloorName()+
                mList.get(position).getUnitName()+
                mList.get(position).getDoorName()
        );
        viewHolder.tv_item_wuyejiaofei_money.setText(mList.get(position).getFee());

        viewHolder.tv_item_wuyejiaofei_wuyegongsiname.setText(mList.get(position).getPropertyName());
        viewHolder.tv_item_wuyejiaofei_dingdanhao.setText(mList.get(position).getOrderNo());
        viewHolder.tv_item_wuyejiaofei_jiaofeishijian.setText(mList.get(position).getPaymentTime());
//        viewHolder.tv_item_wuyejiaofei_zhifufangshi.setText(mList.get(position).getPaymentType());
        if (mList.get(position).getHavePayment().equals("0")){
            viewHolder.jiaofeizhuangtai.setText("应缴金额");
            viewHolder.iv_item_wuyejiaofei_biaoji.setBackgroundResource(R.drawable.a_l_icon_dizhi);
            viewHolder.ll_item_gone_jiaofei.setVisibility(View.VISIBLE);
            viewHolder.ll_item_gone_text.setVisibility(View.GONE);
        }else {
            viewHolder.jiaofeizhuangtai.setText("已缴金额");
            viewHolder.iv_item_wuyejiaofei_biaoji.setBackgroundResource(R.drawable.a_l_icon_yijiaofeidizhi);
            viewHolder.ll_item_gone_jiaofei.setVisibility(View.GONE);
            viewHolder.ll_item_gone_text.setVisibility(View.VISIBLE);
            viewHolder.tv_item_wuyejiaofei_dingdanhao.setText(mList.get(position).getOrderNo());
            viewHolder.tv_item_wuyejiaofei_jiaofeishijian.setText(mList.get(position).getPaymentTime());
            if (mList.get(position).getPaymentType()==0){
                viewHolder.tv_item_wuyejiaofei_zhifufangshi.setText("微信");
            }else if (mList.get(position).getPaymentType()==1){
                viewHolder.tv_item_wuyejiaofei_zhifufangshi.setText("支付宝");
            }

        }
        final int position1=position;

        viewHolder.iv_item_wuyejiaofei_jiaofei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("propertyId",mList.get(position1).getPropertyId()+"");
                Intent intent=new Intent(mContext, ZhiFuXiangQing.class);
                intent.putExtra("propertyName",mList.get(position1).getPropertyName());
                intent.putExtra("propertyId",mList.get(position1).getPropertyId());
                intent.putExtra("fee",(mList.get(position1).getFee()));
                mContext.startActivity(intent);
            }
        });

        return view;
    }

//    private void sendReqToWeiXi(ProPayOrder proPayOrder) {
//        ProPayOrder.Obj obj=proPayOrder.getObj();
//
//        IWXAPI msgApi = WXAPIFactory.createWXAPI(GuardApplication.getContent(),null);
//        msgApi.registerApp(obj.getApp_id());
//
////        if (obj!=null){
//        // 将该app注册到微信
//        PayReq request = new PayReq();
//        propertyId=obj.getPrepay_id();
//        payOrder=obj.getOrder_no();
//
//        request.appId = obj.getApp_id();
//        request.partnerId =obj.getPartnerid();
//        request.prepayId= obj.getPrepay_id();
//        request.packageValue = obj.getReturn_package();
//        request.nonceStr= obj.getNonce_str();
//        request.timeStamp= String.valueOf(obj.getTime_stamp());
//        request.sign= obj.getPay_info_sign();
//
////        Toast.makeText(mContext,
////                obj.getApp_id()+"--"
////                +obj.getPrepay_id()+"--"
////                +obj.getNonce_str()+"--"
////                +String.valueOf(obj.getTime_stamp())+"--"
////                +obj.getSign(),Toast.LENGTH_LONG).show();
//
//        msgApi.registerApp(obj.getApp_id());
//        msgApi.sendReq(request);
//
////        }
//
//
//    }
    public IWXAPI api;
    private void sendreq(ProPayOrder proPayOrder){
        ProPayOrder.Obj obj=proPayOrder.getObj();
        api = WXAPIFactory.createWXAPI(mContext, obj.getPrepay_id());
        api.registerApp(obj.getPrepay_id());

        propertyId=obj.getPrepay_id();
        payOrder=obj.getOrder_no();
        PayReq request = new PayReq();

        request.appId = obj.getApp_id();
        request.partnerId =obj.getPartnerid();
        request.prepayId= obj.getPrepay_id();
        request.packageValue ="Sign=WXPay";
        request.nonceStr= obj.getNonce_str();
        request.timeStamp= String.valueOf(obj.getTime_stamp());
        request.sign= obj.getPay_info_sign();
        request.extData = "app data";

//							req.appId			="wx8d46f83d49930740";
//							req.partnerId		= "11971742";
//							req.prepayId		= "wx20160506162457ea051ff9a90856416486";
//							req.nonceStr		="5qhwTqdC2p6fIFgV";
//							req.timeStamp		="1462523097";
//							req.packageValue	="Sign=WXPay";
//							req.sign			= "D3E6EC74AA1A7445B3AF811C5F7A25B8";
//							req.extData			= "app data";
                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                    api.sendReq(request);


    }


    class ViewHolder {
        TextView jiaofeizhuangtai;
        TextView tv_item_wuyejiaofei_date;
        ImageView iv_item_wuyejiaofei_biaoji;
        TextView tv_item_wuyejiaofei_dizhi;
        TextView tv_item_wuyejiaofei_money;
        ImageView iv_item_wuyejiaofei_jiaofei;

        TextView tv_item_wuyejiaofei_wuyegongsiname;
        TextView tv_item_wuyejiaofei_dingdanhao;
        TextView tv_item_wuyejiaofei_jiaofeishijian;
        TextView tv_item_wuyejiaofei_zhifufangshi;

        LinearLayout ll_item_gone_text;
        LinearLayout ll_item_gone_jiaofei;
    }
}