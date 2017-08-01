package com.bjxiyang.zhinengshequ.myapplication.bianlidian;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 *
 * Created by Administrator on 2017/7/7 0007.
 */
@Entity
public class GouWuChe {
    /**
     * id: 数据ID
     * spid : 商品ID
     * userId 用户ID
     * count: 购物车中的数量
     * sellerId : 商家ID
     * logo : 商品图片
     * name : 商品名字
     * des : 商品介绍
     * productTypeId : 商品所属分类ID
     * price : 商品价格
     * ifDiscount : 是否优惠
     * discountPrice : 优惠的价格
     * stockNum : 库存数量
     * status : 是否上架
     */
    @Id
    private Long id;

    private int spid;
    private int userId;
    private int sellerId;
    private int count;
    private String logo;
    private String name;
    private String des;
    private int productTypeId;
    private int price;
    private int ifDiscount;
    private int discountPrice;
    private int stockNum;
    private int status;
    @Generated(hash = 80540742)
    public GouWuChe(Long id, int spid, int userId, int sellerId, int count,
            String logo, String name, String des, int productTypeId, int price,
            int ifDiscount, int discountPrice, int stockNum, int status) {
        this.id = id;
        this.spid = spid;
        this.userId = userId;
        this.sellerId = sellerId;
        this.count = count;
        this.logo = logo;
        this.name = name;
        this.des = des;
        this.productTypeId = productTypeId;
        this.price = price;
        this.ifDiscount = ifDiscount;
        this.discountPrice = discountPrice;
        this.stockNum = stockNum;
        this.status = status;
    }
    @Generated(hash = 495316655)
    public GouWuChe() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getSpid() {
        return this.spid;
    }
    public void setSpid(int spid) {
        this.spid = spid;
    }
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getSellerId() {
        return this.sellerId;
    }
    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getLogo() {
        return this.logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDes() {
        return this.des;
    }
    public void setDes(String des) {
        this.des = des;
    }
    public int getProductTypeId() {
        return this.productTypeId;
    }
    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }
    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getIfDiscount() {
        return this.ifDiscount;
    }
    public void setIfDiscount(int ifDiscount) {
        this.ifDiscount = ifDiscount;
    }
    public int getDiscountPrice() {
        return this.discountPrice;
    }
    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }
    public int getStockNum() {
        return this.stockNum;
    }
    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }


}
