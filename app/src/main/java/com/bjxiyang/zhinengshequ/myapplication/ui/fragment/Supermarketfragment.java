package com.bjxiyang.zhinengshequ.myapplication.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.app.GuardApplication;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DianMing;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.GouWuChe;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.GouWuCheDao;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.ShangPinList;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.ShangPingDetail;
import com.bjxiyang.zhinengshequ.myapplication.bianlidianstatus.BianLiDianStatus;
import com.bjxiyang.zhinengshequ.myapplication.greendao.DaoUtils;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.SelectPlot;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.BianLiDianResponse;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.supermarket.adapter.CatograyAdapter;
import com.bjxiyang.zhinengshequ.myapplication.supermarket.adapter.GoodsAdapter;
import com.bjxiyang.zhinengshequ.myapplication.supermarket.adapter.GoodsDetailAdapter;
import com.bjxiyang.zhinengshequ.myapplication.supermarket.adapter.ProductAdapter;
import com.bjxiyang.zhinengshequ.myapplication.supermarket.bean.CatograyBean;
import com.bjxiyang.zhinengshequ.myapplication.supermarket.bean.GoodsBean;
import com.bjxiyang.zhinengshequ.myapplication.supermarket.bean.ItemBean;
import com.bjxiyang.zhinengshequ.myapplication.supermarket.view.MyListView;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.MainActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.PlaceOrderActivity;
import com.bjxiyang.zhinengshequ.myapplication.ui.activity.XYXuanZeXiaoQuActivity;
import com.bjxiyang.zhinengshequ.myapplication.until.CollectionUtil;
import com.bjxiyang.zhinengshequ.myapplication.until.DensityUtil;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.LogOutUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.SPToGouWuChe;
import com.bjxiyang.zhinengshequ.myapplication.until.UnitGetGouWuChe;
import com.bjxiyang.zhinengshequ.myapplication.view.MyDialog;
import com.bjxiyang.zhinengshequ.myapplication.widgets.CommonActionSheetDialog;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.bjxiyang.zhinengshequ.myapplication.ui.dialog.ListViewDialog;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.flipboard.bottomsheet.OnSheetDismissedListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.os.Looper.getMainLooper;

/**
 * Created by gll on 17-5-20.
 */

public class Supermarketfragment extends Fragment implements
        MainActivity.FragmentBackListener,View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {
    /**
     * 返回按键
     */
    private BackHandledFragment mBackHandedFragment;
    private boolean hadIntercept;

    //控件

    private SwipeRefreshLayout swipeRefreshLayout1;
    private SwipeRefreshLayout swipeRefreshLayout2;
    private SwipeRefreshLayout swipeRefreshLayout3;
    private LinearLayout ll_shopname;
    private ListView lv_catogary, lv_good;
    private ImageView iv_logo;
    private TextView tv_car;
    private TextView tv_count,tv_totle_money,tv_totle_money2;
    Double totleMoney = 0.00;
    private TextView bv_unm;
    private RelativeLayout rl_bottom;
    //分类和商品
    private List<CatograyBean> list = new ArrayList<CatograyBean>();
    private List<GoodsBean> list2 = new ArrayList<GoodsBean>();
    private GuardApplication myApp;
    private CatograyAdapter catograyAdapter;//分类的adapter
    private GoodsAdapter goodsAdapter;//分类下商品adapter
    ProductAdapter productAdapter;//底部购物车的adapter
    GoodsDetailAdapter goodsDetailAdapter;//套餐详情的adapter
    private static DecimalFormat df;
    private LinearLayout ll_shopcar;
    //底部数据
    private BottomSheetLayout bottomSheetLayout;
    private View bottomSheet;
    private SparseArray<GoodsBean> selectedList;
    //套餐
    private View bottomDetailSheet;
    private List<GoodsBean> list3 = new ArrayList<GoodsBean>();
    private List<GoodsBean> list4 = new ArrayList<GoodsBean>();
    private List<GoodsBean> list5 = new ArrayList<GoodsBean>();

    private Handler mHanlder;
    private ViewGroup anim_mask_layout;//动画层
    private View view;
    private Context mContext;

    private ImageView fanhui;
    private TextView tv_spName;
    private ImageView iv_shangminxiangqing_img;
    private TextView tv_biaoqian1;
    private TextView tv_biaoqian2;
    private TextView tv_danjia;
    private ImageView iv_jian;
    private ImageView iv_jia;
    private TextView tv_shuliang;
    private TextView tv_dianming;
    private TextView tv_spjieshao;
    private TextView tv_shuliang1;
    private TextView tv_xuanhaole;
    private TextView tv_zongjia;
    private LinearLayout ll_first;
    private boolean isShow=false;
    private TextView tv_shopname;
    private List<DianMing.Result> resultList;

    private DianMing.Result result;
    private int sellerId=-1;

    private List<SelectPlot.Obj> mList_plot;
    private List<ShangPinList.Result> list_fenlei;
    private List<ShangPinList.Result.Products> list_shangpin;


    private GouWuChe gouWuChe;
    List<GouWuChe> mList;

    public CommonActionSheetDialog commonActionSheetDialog;
    public int communityId=-1;


    private ShangPingDetail.ResultBean resultBean1;
    private List<ShangPinList.Result.Products> list_shangpin2;
    @Override
    public void onResume() {

        communityId = SPManager.getInstance().getInt("communityId",-1);
        Log.i("YYY","测试显示无数据"+communityId);
        if (communityId==-1){

            showSelectSJ();
            MyUntil.show(getContext(),"请选择商家");
        } else if (communityId==-2){
            showWuShuJu();
        } else{
            getDianMing(communityId);
        }

//        getShangPingList(sellerId);
        if(productAdapter!=null){
            productAdapter.notifyDataSetChanged();
        }

        if(goodsAdapter!=null){
            goodsAdapter.notifyDataSetChanged();
        }

        if(catograyAdapter!=null){
            catograyAdapter.notifyDataSetChanged();
        }
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){

            communityId = SPManager.getInstance().getInt("communityId",-1);
            Log.i("YYY","测试显示无数据"+communityId);
            if (communityId==-1){
                showSelectSJ();
                MyUntil.show(getContext(),"请选择商家");
            } else if (communityId==-2){
                showWuShuJu();
            } else{
                getDianMing(communityId);
            }
            if(productAdapter!=null){
                productAdapter.notifyDataSetChanged();
            }

            if(goodsAdapter!=null){
                goodsAdapter.notifyDataSetChanged();
            }

            if(catograyAdapter!=null){
                catograyAdapter.notifyDataSetChanged();
            }
            if (bottomSheetLayout!=null){
                bottomSheetLayout.dismissSheet();
            }
        }
        super.onHiddenChanged(hidden);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext=getContext();
        view=inflater.inflate(R.layout.fragment_supermarket,container,false);
        myApp = GuardApplication.getContent();
        mHanlder = new Handler(getMainLooper());
        initView();
        addListener();

        ll_shopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("YYYY","点击了购物车");
                showBottomSheet();
            }
        });
        if (SPManager.getInstance().getInt("sellerId",-1)!=-1){
            tv_shopname.setText(SPManager.getInstance().getString("shopName",""));
            getShangPingList(SPManager.getInstance().getInt("sellerId",-1));
        }
//        tv_totle_money.setText("￥"+ String.valueOf(df.format(UnitGetGouWuChe.getZongJia())));
        update(false);
        return view;
    }



    public void initView() {
        ll_first= (LinearLayout) view.findViewById(R.id.ll_first);
        swipeRefreshLayout1= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout1);
        swipeRefreshLayout2= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout2);
        swipeRefreshLayout3= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout3);
        swipeRefreshLayout3.setOnRefreshListener(this);
        swipeRefreshLayout1.setOnRefreshListener(this);
        swipeRefreshLayout2.setOnRefreshListener(this);
        tv_shopname= (TextView) view.findViewById(R.id.tv_shopname);
        bottomSheetLayout = (BottomSheetLayout) view.findViewById(R.id.bottomSheetLayout);
        ll_shopname= (LinearLayout) view.findViewById(R.id.ll_shopname);
        ll_shopname.setOnClickListener(this);
        lv_catogary = (ListView) view.findViewById(R.id.lv_catogary);
        lv_good = (ListView) view.findViewById(R.id.lv_good);
        tv_car = (TextView) view.findViewById(R.id.tv_car);
        //底部控件
        rl_bottom = (RelativeLayout) view.findViewById(R.id.rl_bottom);
        bv_unm = (TextView) view.findViewById(R.id.bv_unm);
        tv_totle_money= (TextView) view.findViewById(R.id.tv_totle_money);
        ll_shopcar= (LinearLayout) view.findViewById(R.id.ll_shopcar);

        selectedList = new SparseArray<>();
        df = new DecimalFormat("0.00");

    }

    //填充数据
    private void initData() {
//        list_shangpin=new ArrayList<>();
        String url_shangpin=BianLiDianResponse.URL_PRODUCT_LIST+"sellerId=";
        RequestCenter.order_product_list(url_shangpin, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                ShangPinList shangPinList= (ShangPinList) responseObj;
                Log.i("CATXXX",String.valueOf("25151515151515"));
                if (shangPinList.getCode()==BianLiDianStatus.STATUS_CODE_SUCCESS){
                    //分类
                    Log.i("CATXXX",String.valueOf(list.size()));
                    list_fenlei=shangPinList.getResult();
                    catograyAdapter = new CatograyAdapter(mContext, list_fenlei);
                    lv_catogary.setAdapter(catograyAdapter);
                    catograyAdapter.notifyDataSetChanged();
                    //商品
//                    list_shangpin=list_fenlei.get(0).getProducts();
//                    goodsAdapter = new GoodsAdapter(mContext,Supermarketfragment.this,list_shangpin, catograyAdapter);
                    lv_good.setAdapter(goodsAdapter);
                    goodsAdapter.notifyDataSetChanged();

                }
            }
            @Override
            public void onFailure(Object reasonObj) {

            }
        });
//        //商品
//        for (int j=30;j<45;j++){
//            GoodsBean goodsBean = new GoodsBean();
//            goodsBean.setTitle("胡辣汤"+j);
//            goodsBean.setProduct_id(j);
//            goodsBean.setCategory_id(j);
//            goodsBean.setIcon("http://c.hiphotos.baidu.com/image/h%3D200/sign=5992ce78530fd9f9bf175269152cd42b/4ec2d5628535e5dd557b44db74c6a7efce1b625b.jpg");
//            goodsBean.setOriginal_price("200");
//            goodsBean.setPrice("100");
//            list3.add(goodsBean);
//        }
//
//        //商品
//        for (int j=5;j<10;j++){
//            GoodsBean goodsBean = new GoodsBean();
//            goodsBean.setTitle("胡辣汤"+j);
//            goodsBean.setProduct_id(j);
//            goodsBean.setCategory_id(j);
//            goodsBean.setIcon("http://e.hiphotos.baidu.com/image/h%3D200/sign=c898bddf19950a7b6a3549c43ad0625c/14ce36d3d539b600be63e95eed50352ac75cb7ae.jpg");
//            goodsBean.setOriginal_price("80");
//            goodsBean.setPrice("60");
//            list4.add(goodsBean);
//        }
//
//        //商品
//        for (int j=10;j<15;j++){
//            GoodsBean goodsBean = new GoodsBean();
//            goodsBean.setTitle("胡辣汤"+j);
//            goodsBean.setProduct_id(j);
//            goodsBean.setCategory_id(j);
//            goodsBean.setIcon("http://g.hiphotos.baidu.com/image/pic/item/03087bf40ad162d9ec74553b14dfa9ec8a13cd7a.jpg");
//            goodsBean.setOriginal_price("40");
//            goodsBean.setPrice("20");
//            list5.add(goodsBean);
//        }
//
//        CatograyBean catograyBean3 = new CatograyBean();
//        catograyBean3.setCount(3);
//        catograyBean3.setKind("江湖餐品"+3);
//        catograyBean3.setList(list3);
//        list.add(catograyBean3);
//
//        CatograyBean catograyBean4 = new CatograyBean();
//        catograyBean4.setCount(4);
//        catograyBean4.setKind("江湖餐品"+4);
//        catograyBean4.setList(list4);
//        list.add(catograyBean4);
//
//        CatograyBean catograyBean5 = new CatograyBean();
//        catograyBean5.setCount(5);
//        catograyBean5.setKind("江湖餐品"+5);
//        catograyBean5.setList(list5);
//        list.add(catograyBean5);
//

//        //默认值
//        list2.clear();
//        list2.addAll(list.get(0).getList());

//        //分类
//        catograyAdapter = new CatograyAdapter(mContext, list);
//        lv_catogary.setAdapter(catograyAdapter);
//        catograyAdapter.notifyDataSetChanged();
//        //商品
//        goodsAdapter = new GoodsAdapter(mContext,this,list2, catograyAdapter);
//        lv_good.setAdapter(goodsAdapter);
//        goodsAdapter.notifyDataSetChanged();
    }
    //添加监听
    private void addListener() {
        lv_catogary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

//                if (position==0){
//                    list_shangpin.clear();
//                    final int position1=position;
//                    String url_list=BianLiDianResponse.URL_PRODUCT_LIST+"sellerId="
//                            +SPManager.getInstance().getInt("sellerId",-1);
//                    RequestCenter.order_product_list(url_list, new DisposeDataListener() {
//                        @Override
//                        public void onSuccess(Object responseObj) {
//                            ShangPinList shangPinList= (ShangPinList) responseObj;
//                            if (shangPinList.getCode()==BianLiDianStatus.STATUS_CODE_SUCCESS){
//                                //分类
//                                //商品
//                                list_shangpin=shangPinList.getResult().get(0).getProducts();
//                            }
//                        }
//                        @Override
//                        public void onFailure(Object reasonObj) {
//                        }
//                    });
//                    catograyAdapter.setSelection(position);
//                    catograyAdapter.notifyDataSetChanged();
//                    goodsAdapter.notifyDataSetChanged();
//                }else {
//                    list_shangpin.clear();
//                    list_shangpin.addAll(list_fenlei.get(position).getProducts());
                    catograyAdapter.setSelection(position);
                    catograyAdapter.notifyDataSetChanged();
//                    goodsAdapter.notifyDataSetChanged();
                goodsAdapter=new GoodsAdapter(mContext,Supermarketfragment.this, list_fenlei.get(position).getProducts(),catograyAdapter);
                lv_good.setAdapter(goodsAdapter);
//                }

//                if (position==0){
////                    Log.i("YYY",list_shangpin2.get(0).getName()+"");
//                    list_shangpin=list_shangpin2;
//                }else {
//
//                }


            }
        });
    }
    //创建套餐详情view
    public void showDetailSheet(List<ItemBean> listItem, int position, ShangPinList.Result.Products products){
        bottomDetailSheet = createMealDetailView(listItem,position, products);

        bottomSheetLayout.addOnSheetStateChangeListener(
                new BottomSheetLayout.OnSheetStateChangeListener() {
                    @Override
                    public void onSheetStateChanged(BottomSheetLayout.State state) {
                        if (state==BottomSheetLayout.State.HIDDEN){
                            catograyAdapter.notifyDataSetChanged();
                            goodsAdapter.notifyDataSetChanged();
                            update(false);
                            bottomSheetLayout.dismissSheet();
                        }
                    }
                });

        if(bottomSheetLayout.isSheetShowing()){
            bottomSheetLayout.dismissSheet();
        }else {
            if(listItem.size()!=0){
                bottomSheetLayout.showWithSheetView(bottomDetailSheet);
            }
        }
        bottomSheetLayout.setPeekSheetTranslation(1700);
        bottomSheetLayout.setMinimumHeight(1500);
    }
    //查看套餐详情
    private View createMealDetailView(List<ItemBean> listItem, final int position, final ShangPinList.Result.Products products)
    {
        isShow=true;
        onAttach(getActivity());
        gouWuChe=null;
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_super_goods_detail,(ViewGroup) getActivity().getWindow().getDecorView(),false);
        iv_shangminxiangqing_img= (ImageView) view.findViewById(R.id.iv_shangminxiangqing_img);
        fanhui= (ImageView) view.findViewById(R.id.iv_shangminxiangqing_fanhui);
        tv_spName= (TextView) view.findViewById(R.id.tv_shangpinxiangqing_shangpinming);
        tv_biaoqian1= (TextView) view.findViewById(R.id.tv_shangpinxiangqing_xinpin);
        tv_biaoqian2= (TextView) view.findViewById(R.id.tv_shangpinxiangqing_xinpinguige);
        tv_danjia= (TextView) view.findViewById(R.id.tv_shangpinxiangqing_jiage);
        iv_jian= (ImageView) view.findViewById(R.id.iv_shangpinxiangqing_jian);
        iv_jia= (ImageView) view.findViewById(R.id.iv_shangpinxiangqing_jia);
        tv_shuliang= (TextView) view.findViewById(R.id.tv_shangpinxiangqing_count);
        tv_dianming= (TextView) view.findViewById(R.id.tv_dianming);
        tv_spjieshao= (TextView) view.findViewById(R.id.tv_shangpinxiangqing_shangpinjieshao);
        tv_shuliang1= (TextView) view.findViewById(R.id.tv_shangpinxiangqing_yuanjiaocount);
        tv_xuanhaole= (TextView) view.findViewById(R.id.tv_shangpinxiangqing_xuanhaole);
        tv_zongjia= (TextView) view.findViewById(R.id.tv_shangpinxiangqing_money);


        final int position1=position;
        tv_xuanhaole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count_cha=0;
                List<GouWuChe> list=DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
                for (int i=0;i<list.size();i++){
                    if (list.get(i).getSpid()==products.getId()){
                        count_cha=list.get(i).getCount();
                    }
                }
            if (count_cha==0){
                MyUntil.show(getContext(),"请添加商品");
            }else {
                Intent intent = new Intent(mContext, PlaceOrderActivity.class);
                intent.putExtra("spId", products.getId());
                intent.putExtra("sellerId", products.getSellerId());
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", products);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            }
        });
        String url_xiangqing = BianLiDianResponse.URL_PRODUCT_DETAIL
                +"productId="+products.getId();
        gouWuChe=null;
        mList = DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
        if (mList.size() > 0) {
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i).getSpid() == products.getId()) {
                    gouWuChe = mList.get(i);
                }
            }
        }
        if (gouWuChe!=null){
            if (gouWuChe.getIfDiscount()==0){
                tv_zongjia.setText(String.valueOf(df.format((double)gouWuChe.getPrice()*gouWuChe.getCount()/100)));
            }else {
                tv_zongjia.setText(String.valueOf(df.format((double)gouWuChe.getDiscountPrice()*gouWuChe.getCount()/100)));
            }
//            tv_zongjia.setText(String.valueOf(gouWuChe.getPrice()*gouWuChe.getCount()));
            tv_shuliang.setText(String.valueOf(gouWuChe.getCount()));
            tv_shuliang1.setText(String.valueOf(gouWuChe.getCount()));
        }else {
            tv_zongjia.setText(0+"");
            tv_shuliang.setText(0+"");
            tv_shuliang1.setText(0+"");
            gouWuChe=SPToGouWuChe.splistToGouWuChe(products);
            DaoUtils.getStudentInstance().insertObject(gouWuChe);
        }
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetLayout.dismissSheet();
            }
        });

        iv_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                            GouWuCheDao gouWuCheDao = GuardApplication.getDaoSession().getGouWuCheDao();
//                            GouWuChe gouWuChe=new GouWuChe();
//                            gouWuChe=SPToGouWuChe.spToGouWuChe(list_shangpin.get(position));
//                            gouWuCheDao.insert(gouWuChe);
//
//                            GouWuCheDao configDao = GuardApplication.getDaoSession().getGouWuCheDao();
//                            List<GouWuChe> configs = configDao.queryBuilder()
//                                    .build().forCurrentThread().list();//线程安全
//
//                            if (configs != null && configs.size() != 0) {
//                                configs.get(0).getName(); //取第一个
//
                if (gouWuChe!=null) {
                    gouWuChe.setCount(gouWuChe.getCount()+1);
                    DaoUtils.getStudentInstance().updateObject(gouWuChe);
                    tv_shuliang.setText(String.valueOf(gouWuChe.getCount()));
                    tv_shuliang1.setText(String.valueOf(gouWuChe.getCount()));
                    if (gouWuChe.getIfDiscount()==0){
                        tv_zongjia.setText(String.valueOf(df.format((double)gouWuChe.getPrice()*gouWuChe.getCount()/100)));
                    }else {
                        tv_zongjia.setText(String.valueOf(df.format((double)gouWuChe.getDiscountPrice()*gouWuChe.getCount()/100)));
                    }
                }
//                            handlerCarNum(1,gouWuChe,true);
            }
        });
        iv_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gouWuChe.getCount()>=1){
                    gouWuChe.setCount(gouWuChe.getCount()-1);
                    DaoUtils.getStudentInstance().updateObject(gouWuChe);
                    tv_shuliang.setText(String.valueOf(gouWuChe.getCount()));
                    tv_shuliang1.setText(String.valueOf(gouWuChe.getCount()));
                    if (gouWuChe.getIfDiscount()==0){
                        tv_zongjia.setText(String.valueOf(df.format((double)gouWuChe.getPrice()*gouWuChe.getCount()/100)));
                    }else {
                        tv_zongjia.setText(String.valueOf(df.format((double)gouWuChe.getDiscountPrice()*gouWuChe.getCount()/100)));
                    }

                }else {
                    tv_shuliang.setText(String.valueOf(0));
                    tv_shuliang1.setText(String.valueOf(0));
                    tv_zongjia.setText(String.valueOf(0));
                }
//                            handlerCarNum(0,gouWuChe,true);
            }
        });


        RequestCenter.order_product_detail(url_xiangqing, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                ShangPingDetail shangPingDetail= (ShangPingDetail) responseObj;
                if (shangPingDetail.getCode()==BianLiDianStatus.STATUS_CODE_SUCCESS){
                    ShangPingDetail.ResultBean resultBean = shangPingDetail.getResult();

                    tv_spjieshao.setText(resultBean.getDes());
                    tv_spName.setText(resultBean.getName());
                    ImageLoaderManager.getInstance(getContext())
                            .displayImage(iv_shangminxiangqing_img,resultBean.getLogo());
                    if (gouWuChe.getIfDiscount()==0){
                        tv_danjia.setText(String.valueOf(df.format((double)gouWuChe.getPrice()/100)));
                    }else {
                        tv_danjia.setText(String.valueOf(df.format((double)gouWuChe.getDiscountPrice()/100)));

                    }

                    tv_dianming.setText( SPManager.getInstance().getString("shopName",""));

                    resultBean1=resultBean;


                }else {
                    MyUntil.show(mContext,shangPingDetail.getMsg());
                }
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });


//        tv_spjieshao.setText(R.string.jieshao);
//        fanhui.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomSheetLayout.dismissSheet();
//            }
//        });
//        tv_shuliang.setText(String.valueOf(list_shangpin.get(0).getStockNum()));
////        tv_shuliang1.setText("sdasd");
//        tv_shuliang1.setText(String.valueOf(list_shangpin.get(0).getCount()));
//
//        iv_jia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                handlerCarNum(1,list_shangpin.get(0),true);
//                tv_shuliang.setText(String.valueOf(list_shangpin.get(0).getCount()));
//                tv_shuliang1.setText(String.valueOf(list_shangpin.get(0).getCount()));
//            }
//        });
//        iv_jian.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                handlerCarNum(0,list_shangpin.get(0),true);
//                if (list3.get(0).getNum()>0){
//                    tv_shuliang.setText(String.valueOf(list_shangpin.get(0).getCount()));
//                    tv_shuliang1.setText(String.valueOf(list_shangpin.get(0).getCount()));
//                }
//
//            }
//        });







//        ListView lv_product = (MyListView) view.findViewById(R.id.lv_product);
//        TextView tv_meal = (TextView) view.findViewById(R.id.tv_meal);
//        TextView tv_num = (TextView) view.findViewById(R.id.tv_num);
//        int count=0;
//        for(int i=0;i<listItem.size();i++){
//            count = count+ Integer.parseInt(listItem.get(i).getNote2());
//        }
//        tv_meal.setText(mealName);
//        tv_num.setText("(共"+count+"件)");
//        goodsDetailAdapter = new GoodsDetailAdapter(mContext,listItem);
//        lv_product.setAdapter(goodsDetailAdapter);
//        goodsDetailAdapter.notifyDataSetChanged();ImageLoaderManager



        return view;
    }
    //创建购物车view
    private void showBottomSheet(){
        isShow=true;
        onAttach(getActivity());
        bottomSheet = createBottomSheetView();
        if(bottomSheetLayout.isSheetShowing()){
            bottomSheetLayout.dismissSheet();
        }else {
            if (SPManager.getInstance().getString("shopName",null)!=null) {
//                if (list_shangpin.size() != 0) {
                    bottomSheetLayout.showWithSheetView(bottomSheet);
//                }
            }else {
                MyUntil.show(getContext(),"请先选择商家");
            }
        }
    }
    //查看购物车布局
    private View createBottomSheetView(){
//        (ViewGroup) getActivity().getWindow().getDecorView(),false
        View view = LayoutInflater.from(mContext).inflate(R.layout.super_layout_bottom_sheet,null);
        MyListView lv_product = (MyListView) view.findViewById(R.id.lv_product);
        tv_totle_money2= (TextView) view.findViewById(R.id.tv_totle_money2);

        mList=DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
        for (int i=0;i<mList.size();i++){
            if (mList.get(i).getCount()==0){
                DaoUtils.getStudentInstance().deleteObject(mList.get(i));
            }
        }


        tv_count= (TextView) view.findViewById(R.id.tv_count);
        tv_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (UnitGetGouWuChe.getConuntAll()==0){
                    MyUntil.show(getContext(),"请添加商品");
                }else {
                    Intent intent = new Intent(mContext, PlaceOrderActivity.class);
                    intent.putExtra("type", 10);
                    startActivity(intent);
                }
            }
        });
        TextView clear = (TextView) view.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCart();
            }
        });

        mList=DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
        for (int i=mList.size()-1;i>=0;i--){
            if (mList.get(i).getSellerId()!=SPManager.getInstance().getInt("sellerId",0)){
                mList.remove(i);
            }
        }
        productAdapter = new ProductAdapter(getContext(),
                Supermarketfragment.this,goodsAdapter, mList);

        int size = mList.size();
        for(int i=0;i<size;i++){
            int price=mList.get(i).getCount()*mList.get(i).getPrice();
            totleMoney += price;
        }
        tv_totle_money2.setText("￥"+ String.valueOf(df.format(UnitGetGouWuChe.getZongJia())));
        tv_totle_money.setText("￥"+ String.valueOf(df.format(UnitGetGouWuChe.getZongJia())));
        totleMoney = 0.00;
        lv_product.setAdapter(productAdapter);
        return view;
    }
    //清空购物车
    public void clearCart(){
        DaoUtils.getStudentInstance().deleteAll(GouWuChe.class);
//        list_shangpin.clear();
//        list2.clear();
//        if (list.size() > 0) {
//            for (int j=0;j<list.size();j++){
//                list.get(j).setCount(0);
//                for(int i=0;i<list.get(j).getList().size();i++){
//                    list.get(j).getList().get(i).setNum(0);
//                }
//            }
//            list2.addAll(list.get(0).getList());
        if (catograyAdapter!=null) {
            catograyAdapter.setSelection(0);
            //刷新不能删
            catograyAdapter.notifyDataSetChanged();
        }
        if (goodsAdapter!=null){
            goodsAdapter.notifyDataSetChanged();
        }
//        }
        tv_totle_money.setText("￥"+ String.valueOf(0.00));
        totleMoney = 0.00;
        update(true);
    }
    //根据商品id获取当前商品的采购数量
    public int getSelectedItemCountById(int id){
        GoodsBean temp = selectedList.get(id);
        if(temp==null){
            return 0;
        }
        return temp.getNum();
    }

    public void handlerCarNum(int type,GouWuChe gouWuChe1, boolean refreshGoodList){
//        if (gouWuChe1!=null){
//            gouWuChe=gouWuChe1;
//        }
        if (type==1) {
//            mList = DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
//            if (mList.size() > 0) {
//                for (int i = 0; i < mList.size(); i++) {
//                    if (mList.get(i).getSpid() == resultBean.getId()) {
//                        gouWuChe = mList.get(i);
            gouWuChe1.setCount(gouWuChe1.getCount() + 1);
            DaoUtils.getStudentInstance().updateObject(gouWuChe1);
//                    }
//                }
//            } else {
//                    gouWuChe = SPToGouWuChe.spToGouWuChe(resultBean);
//                    gouWuChe.setCount(1);
//                    DaoUtils.getStudentInstance().insertObject(gouWuChe);
//            }
        }else if (type==0){
            if (gouWuChe1!=null){


                if (gouWuChe1.getCount()>1){
                    gouWuChe1.setCount(gouWuChe1.getCount()-1);
                    DaoUtils.getStudentInstance().updateObject(gouWuChe1);
//                    tv_shuliang.setText(String.valueOf(gouWuChe.getCount()));
//                    tv_shuliang1.setText(String.valueOf(gouWuChe.getCount()));
//                    if (resultBean1!=null){
//                        tv_zongjia.setText(String.valueOf(resultBean1.getPrice()*gouWuChe.getCount()));
//                    }
                }else {
//                    tv_shuliang.setText(String.valueOf(0));
//                    tv_shuliang1.setText(String.valueOf(0));
//                    tv_zongjia.setText(String.valueOf(0));
                    gouWuChe1.setCount(0);
                    DaoUtils.getStudentInstance().updateObject(gouWuChe1);
//                    DaoUtils.getStudentInstance().deleteObject(gouWuChe1);
                }
            }
        }
//        if (type == 0) {
//            ShangPinList.Result.Products temp = products;
////            list_shangpin.get(products.getId());
//            if(temp!=null){
//                if(temp.getCount()<1){
//                    int i =  products.getCount();
//                    products.setCount(--i);
////                    list_shangpin.remove(products);
//                }else{
//                    int i =  products.getCount();
//                    products.setCount(--i);
//                }
//            }
//
//
//
//        } else if (type == 1) {
//            ShangPinList.Result.Products temp = products;
////            list_shangpin.get(products.getId());
//            if(temp==null){
//                products.setCount(1);
////                list_shangpin.addAll(products.getId(), products);
//
////                    list_shangpin    .append(products.getId(), products);
//            }else{
//                int i= products.getCount();
//                products.setCount(++i);
//            }
//        }
        update(refreshGoodList);

    }
    //刷新布局 总价、购买数量等
    private void update(boolean refreshGoodList){

        int count =UnitGetGouWuChe.getConuntAll();



//        for(int i=0;i<size;i++){
//            GoodsBean item = selectedList.valueAt(i);
//            count += item.getNum();
//            totleMoney += item.getNum()* Double.parseDouble(item.getPrice());
//
        tv_totle_money.setText("￥"+ String.valueOf(df.format(UnitGetGouWuChe.getZongJia())));
        if (tv_totle_money2!=null){
            tv_totle_money2.setText("￥"+ String.valueOf(df.format(UnitGetGouWuChe.getZongJia())));
        }
        totleMoney = 0.00;
        if(count<1){
            bv_unm.setVisibility(View.GONE);
        }else{
            bv_unm.setVisibility(View.VISIBLE);
        }

        bv_unm.setText(String.valueOf(UnitGetGouWuChe.getConuntAll()));


        if(productAdapter!=null){
            productAdapter.notifyDataSetChanged();
        }

        if(goodsAdapter!=null){
            goodsAdapter.notifyDataSetChanged();
        }

        if(catograyAdapter!=null){
            catograyAdapter.notifyDataSetChanged();
        }

        if(bottomSheetLayout.isSheetShowing() && count<1){
            bottomSheetLayout.dismissSheet();
        }
    }
    /**
     * @Description: 创建动画层
     * @param
     * @return void
     * @throws
     */
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) getActivity().getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE-1);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private View addViewToAnimLayout(final ViewGroup parent, final View view,
                                     int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }

    public void setAnim(final View v, int[] startLocation) {
        anim_mask_layout = null;
        anim_mask_layout = createAnimLayout();
        anim_mask_layout.addView(v);//把动画小球添加到动画层
        final View view = addViewToAnimLayout(anim_mask_layout, v, startLocation);
        int[] endLocation = new int[2];// 存储动画结束位置的X、Y坐标
        tv_car.getLocationInWindow(endLocation);
        // 计算位移
        int endX = 0 - startLocation[0] + 40;// 动画位移的X坐标
        int endY = endLocation[1] - startLocation[1];// 动画位移的y坐标

        TranslateAnimation translateAnimationX = new TranslateAnimation(0,0, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator());
        translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);

        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0, 0, endY);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
        translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationY.setFillAfter(true);

        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        set.setDuration(800);// 动画的执行时间
        view.startAnimation(set);
        // 动画监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            // 动画的开始
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            // 动画的结束
            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.GONE);
            }
        });

    }

    public List<ItemBean> getListAll(){
        List<ItemBean> list_all=new ArrayList<ItemBean>();
        ItemBean itemBean=new ItemBean();
        itemBean.setKey("1");
        itemBean.setNote1("2");
        itemBean.setNote2("3");
        itemBean.setValue("4");
        list_all.add(itemBean);
        return list_all;
    }
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        if(activity instanceof MainActivity){
                ((MainActivity)activity).setBackListener(this);
                ((MainActivity)activity).setInterception(true);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(getActivity() instanceof MainActivity){
                ((MainActivity)getActivity()).setBackListener(null);
                ((MainActivity)getActivity()).setInterception(false);

        }
    }

    @Override
    public void onbackForward() {
        // 处理fragment的返回事件
        if (isShow){
            bottomSheetLayout.dismissSheet();
            onDetach();
            isShow=false;
        }
    }

    private void hide(){
        MainActivity.linearLayout.setVisibility(View.GONE);
    }
    private void display(){
        MainActivity.linearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_shopname:
                getData();
//                    getDianMing(1);
                break;
        }
    }
    private void getDianMing(int communityId){
        resultList=new ArrayList<>();
        String url= BianLiDianResponse.URL_SELLER_LIST+"communityId="+communityId;

        RequestCenter.order_seller_list(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DianMing dianMing= (DianMing) responseObj;
                if (dianMing.getCode() == BianLiDianStatus.STATUS_CODE_SUCCESS) {

                    resultList = dianMing.getResult();
                    result = resultList.get(0);
//                    mList=DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
//                    for (int i=0;i<mList.size();i++){
////                        if (mList.get(i).getSellerId()==result.getId()){
////                            if (UnitGetGouWuChe.getZongJia()!=0){
////                                tv_totle_money.setText("￥"+ String.valueOf(df.format(UnitGetGouWuChe.getZongJia())));
////                            }
////                            int count =UnitGetGouWuChe.getConuntAll();
////                            bv_unm.setText(String.valueOf(count));
////                        }
//
//                    }
                    //18706451620
                    //120688jj
//                    List<Integer> list=new ArrayList();
//                    for (int i=0;i<list_fenlei.size();i++){
//                        for (int j=0;j<list_fenlei.get(i).getProducts().size();j++){
//                            list.add(list_fenlei.get(i).getProducts().get(j).getId());
//                        }
//                    }
//
//                    for (int i=0;i<mList.size();i++){
//                        for (int j=list.size()-1;j<0;j--){
//                            if (mList.get(i).getSpid()!=list.get(j)){
//                                DaoUtils.getStudentInstance().deleteObject(mList.get(i));
//                            }
//                        }
//                    }

                    SPManager.getInstance().putInt("sellerId", result.getId());
                    SPManager.getInstance().putString("shopName", result.getShopName());
                    sellerId = result.getId();

                    tv_shopname.setText(result.getShopName());
                    update(false);
                    getShangPingList(result.getId());
//                    }
//                    ListViewDialog listViewDialog=new ListViewDialog(getActivity(),resultList);
//
//                    listViewDia                            log.setOnsetselect(new ListViewDialog.Onsetselect() {
//                        @Override
//                        public void getDianMingResult(DianMing.Result result1) {
//                            result=result1;
//                            mList=DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
//                            for (int i=0;i<mList.size();i++){
//                                if (mList.get(i).getSellerId()==result.getId()){
//
//                                    if (UnitGetGouWuChe.getZongJia()!=0){
//                                        tv_totle_money.setText("￥"+ String.valueOf(df.format(UnitGetGouWuChe.getZongJia())));
//                                    }
//                                    int count =UnitGetGouWuChe.getConuntAll();
//
//                                    bv_unm.setText(String.valueOf(count));
//                                }
//                            }
//                            SPManager.getInstance().putInt("sellerId",result.getId());
//                            SPManager.getInstance().putString("shopName",result1.getShopName());
//                            getShangPingList(result.getId());
//                            sellerId=result.getId();
//                            tv_shopname.setText(result1.getShopName());
//                        }
//                    });
//                    listViewDialog.show();
                }else if (dianMing.getCode()==BianLiDianStatus.STATUS_CODE_ERROR_USER_NOTLOGIN){
                    LogOutUntil.logout();
                }
                else {
                    showWuShuJu();
//                    MyUntil.show(getContext(),dianMing.getMsg());
                }

            }

            @Override
            public void onFailure(Object reasonObj) {
                showWuShuJu();
            }
        });

    }
    private void getShangPingList(int sellerId){
        Log.i("YYY","测试显示无数据sellerId="+sellerId);
        DialogUntil.showLoadingDialog(getContext(),"正在加载",true);
        String url_list=BianLiDianResponse.URL_PRODUCT_LIST+"sellerId="+sellerId;
        RequestCenter.order_product_list(url_list, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                ShangPinList shangPinList= (ShangPinList) responseObj;

                if (shangPinList.getCode()==BianLiDianStatus.STATUS_CODE_SUCCESS){
                    //分类
                    list_fenlei=shangPinList.getResult();
                    //商品
                    if (list_fenlei.size()>0) {
                        catograyAdapter = new CatograyAdapter(mContext, list_fenlei);
                        lv_catogary.setAdapter(catograyAdapter);
                        catograyAdapter.notifyDataSetChanged();
                        list_shangpin = list_fenlei.get(0).getProducts();
                        goodsAdapter = new GoodsAdapter(
                                mContext, Supermarketfragment.this, list_shangpin, catograyAdapter);
                        lv_good.setAdapter(goodsAdapter);
                        goodsAdapter.notifyDataSetChanged();
                        showList();
                    }else {
                        showWuShuJu();
                    }
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();

            }
        });

    }
    private void getData(){
        mList_plot=new ArrayList<>();
        DialogUntil.showLoadingDialog(getActivity(),"正在加载",true);
        String url= XY_Response.URL_FINDCOMMUNITYBYPER+"mobilePhone="+
                UserManager.getInstance().getUser().getObj().getMobilePhone();

        RequestCenter.findCommunityByPer(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                SelectPlot selectPlot= (SelectPlot) responseObj;
                DialogUntil.closeLoadingDialog();
                if (selectPlot.getCode().equals("1000")){
                    mList_plot = selectPlot.getObj();
                    if (mList_plot.size()>0){
                        showActionSheet(mList_plot);
                    }else {
                        MyUntil.show(getContext(),"请选择小区");
                        SPManager.getInstance().remove("sellerId");
                    }
//                    if (mList_plot==null){
//                        MyUntil.show(getContext(),"请选择小区");
////                        Intent intent = new Intent(getContext(),XYXuanZeXiaoQuActivity.class);
////                        startActivity(intent);
//                    }else {
//                        showActionSheet(mList_plot);
//                    }
                }else {
                    SPManager.getInstance().remove("sellerId");
//                    Toast.makeText(getActivity(),selectPlot.getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
            }
        });
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
                    communityId=mList.get(position).getCommunityId();
                    SPManager.getInstance().putInt("communityId",communityId);
                    getDianMing(communityId);
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

    @Override
    public void onRefresh() {
        communityId = SPManager.getInstance().getInt("communityId",-1);

        if (communityId==-1){
            showSelectSJ();
            MyUntil.show(getContext(),"请选择商家");
        }else if (communityId==-2){
            showWuShuJu();
        } else{
            getDianMing(communityId);

        }
        swipeRefreshLayout1.setRefreshing(false);
        swipeRefreshLayout2.setRefreshing(false);
        swipeRefreshLayout3.setRefreshing(false);
    }
    private void showList(){
        ll_first.setVisibility(View.VISIBLE);
        swipeRefreshLayout2.setVisibility(View.GONE);
        swipeRefreshLayout3.setVisibility(View.GONE);
    }
    private void showWuShuJu(){
        SPManager.getInstance().putInt("sellerId",-1);
        SPManager.getInstance().putInt("communityId",-2);
        tv_shopname.setText("店名");
        bv_unm.setText("0");
        tv_totle_money.setText("￥0.00");
        ll_first.setVisibility(View.GONE);
        swipeRefreshLayout2.setVisibility(View.VISIBLE);
        swipeRefreshLayout3.setVisibility(View.GONE);
    }
    private void showSelectSJ(){
        SPManager.getInstance().putInt("sellerId",-1);
        SPManager.getInstance().putInt("communityId",-1);
        tv_shopname.setText("店名");
        bv_unm.setText("0");
        tv_totle_money.setText("￥0.00");
        ll_first.setVisibility(View.GONE);
        swipeRefreshLayout2.setVisibility(View.GONE);
        swipeRefreshLayout3.setVisibility(View.VISIBLE);
    }
}
