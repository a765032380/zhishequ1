package com.bjxiyang.zhinengshequ.myapplication.until;

import com.bjxiyang.zhinengshequ.myapplication.bianlidian.GouWuChe;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.ShangPinList;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.ShangPingDetail;

/**
 * Created by Administrator on 2017/7/7 0007.
 */

public class SPToGouWuChe {

    public static GouWuChe spToGouWuChe(ShangPingDetail.ResultBean resultBean){
        GouWuChe gouWuChe=new GouWuChe();
        gouWuChe.setName(resultBean.getName());
        gouWuChe.setDes(resultBean.getDes());
        gouWuChe.setDiscountPrice(resultBean.getDiscountPrice());
        gouWuChe.setIfDiscount(resultBean.getIfDiscount());
        gouWuChe.setLogo(resultBean.getLogo());
        gouWuChe.setSellerId(resultBean.getSellerId());
        gouWuChe.setSpid(resultBean.getId());
        gouWuChe.setStockNum(resultBean.getStockNum());
        gouWuChe.setPrice(resultBean.getPrice());
        gouWuChe.setProductTypeId(resultBean.getProductTypeId());
        gouWuChe.setStatus(resultBean.getStatus());
        return gouWuChe;
    }
    public static GouWuChe splistToGouWuChe(ShangPinList.Result.Products resultBean){
        GouWuChe gouWuChe=new GouWuChe();
        gouWuChe.setName(resultBean.getName());
        gouWuChe.setDes(resultBean.getDes());
        gouWuChe.setDiscountPrice(resultBean.getDiscountPrice());
        gouWuChe.setIfDiscount(resultBean.getIfDiscount());
        gouWuChe.setLogo(resultBean.getLogo());
        gouWuChe.setSellerId(resultBean.getSellerId());
        gouWuChe.setSpid(resultBean.getId());
        gouWuChe.setStockNum(resultBean.getStockNum());
        gouWuChe.setPrice(resultBean.getPrice());
        gouWuChe.setProductTypeId(resultBean.getProductTypeId());
        gouWuChe.setStatus(resultBean.getStatus());
        return gouWuChe;


    }


}
