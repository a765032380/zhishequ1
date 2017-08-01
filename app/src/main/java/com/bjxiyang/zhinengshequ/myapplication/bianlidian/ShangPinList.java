package com.bjxiyang.zhinengshequ.myapplication.bianlidian;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/7/4 0004.
 */

public class ShangPinList {


    /**
     * code : 0
     * time : 1499170847958
     * msg :
     * result : [{"id":1,"sort":1,"sellerId":1,"name":"5","products":[{"id":1,"sellerId":1,"logo":"logoURL","name":"商品一号","des":"","productTypeId":1,"price":1000,"ifDiscount":0,"discountPrice":0,"stockNum":100,"status":0},{"id":3,"sellerId":1,"logo":"ss","name":"aa","des":"aldfa","productTypeId":1,"price":2000,"ifDiscount":1,"discountPrice":1800,"stockNum":100,"status":0},{"id":10,"sellerId":1,"logo":"ss","name":"aa","des":"aldfa","productTypeId":1,"price":200000,"ifDiscount":1,"discountPrice":180000,"stockNum":100,"status":0},{"id":16,"sellerId":1,"logo":"ss","name":"aa","des":"aldfa","productTypeId":1,"price":200000,"ifDiscount":1,"discountPrice":180000,"stockNum":100,"status":0}]},{"id":2,"sort":2,"sellerId":1,"name":"推荐","products":[{"id":2,"sellerId":1,"logo":"logoURL","name":"商品2号","des":"","productTypeId":2,"price":1000,"ifDiscount":1,"discountPrice":100,"stockNum":100,"status":0}]},{"id":3,"sort":3,"sellerId":1,"name":"1234567","products":""}]
     */

    private int code;
    private long time;
    private String msg;
    private List<Result> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public static class Result {
        /**
         * id : 1
         * sort : 1
         * sellerId : 1
         * name : 5
         * products : [{"id":1,"sellerId":1,"logo":"logoURL","name":"商品一号","des":"","productTypeId":1,"price":1000,"ifDiscount":0,"discountPrice":0,"stockNum":100,"status":0},{"id":3,"sellerId":1,"logo":"ss","name":"aa","des":"aldfa","productTypeId":1,"price":2000,"ifDiscount":1,"discountPrice":1800,"stockNum":100,"status":0},{"id":10,"sellerId":1,"logo":"ss","name":"aa","des":"aldfa","productTypeId":1,"price":200000,"ifDiscount":1,"discountPrice":180000,"stockNum":100,"status":0},{"id":16,"sellerId":1,"logo":"ss","name":"aa","des":"aldfa","productTypeId":1,"price":200000,"ifDiscount":1,"discountPrice":180000,"stockNum":100,"status":0}]
         */

        private int id;
        private int sort;
        private int sellerId;
        private String name;
        private List<Products> products;

        private int count;
        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Products> getProducts() {
            return products;
        }

        public void setProducts(List<Products> products) {
            this.products = products;
        }

        public static class Products implements Serializable {
            /**
             * id : 1
             * sellerId : 1
             * logo : logoURL
             * name : 商品一号
             * des :
             * productTypeId : 1
             * price : 1000
             * ifDiscount : 0
             * discountPrice : 0
             * stockNum : 100
             * status : 0
             */


            private int id;
            private int sellerId;
            private String logo;
            private String name;
            private String des;
            private int productTypeId;
            private int price;
            private int ifDiscount;
            private int discountPrice;
            private int stockNum;
            private int status;

            private int count;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }


            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSellerId() {
                return sellerId;
            }

            public void setSellerId(int sellerId) {
                this.sellerId = sellerId;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public int getProductTypeId() {
                return productTypeId;
            }

            public void setProductTypeId(int productTypeId) {
                this.productTypeId = productTypeId;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getIfDiscount() {
                return ifDiscount;
            }

            public void setIfDiscount(int ifDiscount) {
                this.ifDiscount = ifDiscount;
            }

            public int getDiscountPrice() {
                return discountPrice;
            }

            public void setDiscountPrice(int discountPrice) {
                this.discountPrice = discountPrice;
            }

            public int getStockNum() {
                return stockNum;
            }

            public void setStockNum(int stockNum) {
                this.stockNum = stockNum;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

        }
    }
}
