package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.Door;
import com.bjxiyang.zhinengshequ.myapplication.model.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.model.Floor;
import com.bjxiyang.zhinengshequ.myapplication.model.Plots;
import com.bjxiyang.zhinengshequ.myapplication.model.Unit;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.XYXuanZeXiaoQuAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bjxiyang.zhinengshequ.R;

import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.SelectType;
import com.bjxiyang.zhinengshequ.myapplication.until.UserType;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.MyDialog;

import okhttp3.MediaType;

/**
 * Created by gll on 17-5-23.
 */

public class XYXuanZeXiaoQuActivity extends MySwipeBackActivity implements AdapterView.OnItemClickListener,View.OnClickListener {
    public static final MediaType FORM_CONTENT_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");


    private RelativeLayout ib_menjinjilu_fanghui1;
    private List<String> mList;
    private ListView lv_xuanzexiaoqu;
    private TextView select_xiaoqu_title;
    private int type;
    private TextView add_xiaoqu_dizhi;
    private String xiaoqu_dizhi;
    private RelativeLayout ib_menjinjilu_fanghui;
    private ImageButton ib_quedingtianjia;
    private ImageButton ib_yezhujiashu,ib_zuhu,ib_jiaren;
    private XYXuanZeXiaoQuAdapter adapter;
    private String xiaoqu;
    private String louhao;
    private String danyuan;
    private String men;
    private Intent intent;
    private EditText et_name;
    private TextView tv_lianxidianhua;
    private LinearLayout queren;
    private LinearLayout xuanze;
    private LinearLayout select_yezhu,select_zuhu,select_jiaren;

    private Plots plots;
    private Floor floor;
    private Unit unit;
    private Door door;


    private String name;
    private String uphone;

    private String phone;
    private int communityId;
    private int nperId;
    private int floorId;
    private int unitId;
    private int doorId;
    private int roleType=UserType.USER_OWNER;

    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1000:
                    DialogUntil.closeLoadingDialog();
                    mList= (List<String>) msg.obj;
                    adapter=new XYXuanZeXiaoQuAdapter(XYXuanZeXiaoQuActivity.this,mList);
                    lv_xuanzexiaoqu.setAdapter(adapter);
                    lv_xuanzexiaoqu.setOnItemClickListener(XYXuanZeXiaoQuActivity.this);
                    break;
            }
            return false;
        }
    });
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xy_activity_xuanzexiaoqu);
        initUI();
    }
    private void initUI() {
        et_name= (EditText) findViewById(R.id.et_name);
        tv_lianxidianhua= (TextView) findViewById(R.id.tv_lianxidianhua);
        tv_lianxidianhua.setText(UserManager.getInstance().getUser().getObj().getMobilePhone());
        select_yezhu= (LinearLayout) findViewById(R.id.select_yezhu);
        select_zuhu= (LinearLayout) findViewById(R.id.select_zuhu);
        select_jiaren= (LinearLayout) findViewById(R.id.select_jiaren);
        select_yezhu.setOnClickListener(this);
        select_zuhu.setOnClickListener(this);
        select_jiaren.setOnClickListener(this);
        ib_jiaren= (ImageButton) findViewById(R.id.ib_jiaren);
        ib_yezhujiashu= (ImageButton) findViewById(R.id.ib_yezhujiashu);
        ib_yezhujiashu.setOnClickListener(this);
        ib_zuhu= (ImageButton) findViewById(R.id.ib_zuhu);
        ib_zuhu.setOnClickListener(this);
        add_xiaoqu_dizhi= (TextView) findViewById(R.id.add_xiaoqu_dizhi);
        ib_menjinjilu_fanghui1= (RelativeLayout) findViewById(R.id.ib_menjinjilu_fanghui1);
        ib_menjinjilu_fanghui1.setOnClickListener(this);
        ib_quedingtianjia= (ImageButton) findViewById(R.id.ib_quedingtianjia);
        ib_quedingtianjia.setOnClickListener(this);
        queren= (LinearLayout) findViewById(R.id.queren);
        xuanze= (LinearLayout) findViewById(R.id.xuanze);
        ib_menjinjilu_fanghui= (RelativeLayout) findViewById(R.id.ib_menjinjilu_fanghui);
        ib_menjinjilu_fanghui.setOnClickListener(this);
        lv_xuanzexiaoqu= (ListView) findViewById(R.id.lv_xuanzexiaoqu);
        select_xiaoqu_title= (TextView) findViewById(R.id.select_xiaoqu_title);
        getData(type);

    }
    //得到模拟数据
    private List<String> getData(int type1){
        DialogUntil.showLoadingDialog(XYXuanZeXiaoQuActivity.this,"正在加载数据",true);
        final int type2=type1;
        mList=new ArrayList<>();
        switch (type1){
            case 0:
                phone= UserManager.getInstance().getUser().getObj().getMobilePhone();
                String url= XY_Response.URL_FINDCOMMUNITY+"mobilePhone="+phone;
                RequestCenter.findCommunity(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        lv_xuanzexiaoqu.setEnabled(true);
                        plots= (Plots) responseObj;
                        if (plots.getCode().equals("1000")){
                            List<Plots.Obj> list= plots.getObj();
                            for (int i = 0; i< list.size(); i++){
                                mList.add(list.get(i).getNperName());
                                if (list.get(i).getNperName().equals("")){
                                    mList.remove(i);
                                }
                            }
                        }else {
                            mList.add("当前列表为空");
                            lv_xuanzexiaoqu.setEnabled(false);
                           }
                        Message message=new Message();
                        message.obj=mList;
                        message.what=1000;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(Object reasonObj){
                        qinqiushibai();
                    }
                });
                break;
            case 1:
                String url1=XY_Response.URL_FINDFLOOR+"mobilePhone="
                                +phone+"&communityId="+communityId+"&nperId="+nperId;
                RequestCenter.findFloor(url1, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        lv_xuanzexiaoqu.setEnabled(true);
                        mList=new ArrayList<String>();
                        floor = (Floor) responseObj;
                        if (floor.getCode().equals("1000")||floor.getCode()=="1000"){
                            List<Floor.Obj> list= floor.getObj();
                            for (int i = 0; i< list.size(); i++){
                                mList.add(list.get(i).getFloorName());
                                if (list.get(i).getFloorName().equals("")) {
                                    mList.remove(i);
                                }
                                
                            }
                        }else {
                            mList.add("当前列表为空");
                            lv_xuanzexiaoqu.setEnabled(false);
                        }
                        Message message=new Message();
                        message.obj=mList;
                        message.what=1000;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        qinqiushibai();
                    }
                });
                break;
            case 2:
                String url2=XY_Response.URL_FINDUNIT+"mobilePhone="
                        +phone+"&communityId="+communityId+"&nperId="+nperId+"&floorId="+floorId;
                RequestCenter.findUnit(url2, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        lv_xuanzexiaoqu.setEnabled(true);
                        mList=new ArrayList<String>();
                        unit = (Unit) responseObj;
                        if (unit.getCode().equals("1000")){
                            List<Unit.Obj> list= unit.getObj();
                            for (int i = 0; i< list.size(); i++){
                                mList.add(list.get(i).getUnitName());
                                if (list.get(i).getUnitName().equals("")) {
                                    mList.remove(i);
                                }
                            }
                        }else {
                            mList.add("当前列表为空");
                            lv_xuanzexiaoqu.setEnabled(false);
                        }
                        Message message=new Message();
                        message.obj=mList;
                        message.what=1000;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        qinqiushibai();
                    }
                });
                break;
            case 3:
                String url3=XY_Response.URL_FINDDOOR+"mobilePhone="
                        +phone+"&communityId="+communityId+"&nperId="+
                        nperId+"&floorId="+floorId+"&unitId="+unitId;
                RequestCenter.findDoor(url3, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {

                        lv_xuanzexiaoqu.setEnabled(true);
                        mList=new ArrayList<String>();
                        door = (Door) responseObj;
                        if (door.getCode().equals("1000")){
                            List<Door.Obj> list= door.getObj();
                            for (int i = 0; i< list.size(); i++){
                                mList.add(list.get(i).getDoorName());
                                if (list.get(i).getDoorName().equals("")) {
                                    mList.remove(i);
                                }
                            }
                        }else {
                            mList.add("当前列表为空");
                            lv_xuanzexiaoqu.setEnabled(false);
                        }
                        Message message=new Message();
                        message.obj=mList;
                        message.what=1000;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        qinqiushibai();
                    }
                });

                break;

        }
        return mList;
    }
    //重写返回键的处理事件
    @Override
    public void onBackPressed() {
       onBack();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (type<4){
            if (type== SelectType.XIAOQU){
                communityId=plots.getObj().get(position).getCommunityId();
                nperId=plots.getObj().get(position).getNperId();

                type=SelectType.LOUHAO;
                xiaoqu=mList.get(position);
                update();
            }else if (type==SelectType.LOUHAO){
                floorId=floor.getObj().get(position).getFloorId();
                type=SelectType.DANYUAN;
                louhao=mList.get(position);
                update();
            }else if (type==SelectType.DANYUAN){
                unitId=unit.getObj().get(position).getUnitId();
                type=SelectType.MEN;
                danyuan=mList.get(position);
                update();

            }else if (type==SelectType.MEN){
                doorId=door.getObj().get(position).getDoorId();
                type++;
                showQueren();
                //拼接地址
                men=mList.get(position);
                xiaoqu_dizhi=xiaoqu+"-"+louhao+"-"+danyuan+"-"+men;
                add_xiaoqu_dizhi.setText(xiaoqu_dizhi);
                return;
            }
            setTitle();
        }

    }
    private void showQueren(){
        queren.setVisibility(View.VISIBLE);
        xuanze.setVisibility(View.GONE);

    }
    private void showXuanze(){
        xuanze.setVisibility(View.VISIBLE);
        queren.setVisibility(View.GONE);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_yezhujiashu:
                roleType= UserType.USER_OWNER;
                ib_jiaren.setBackgroundResource(R.drawable.k_btn_zhong_n);
                ib_yezhujiashu.setBackgroundResource(R.drawable.k_btn_zhong_pre);
                ib_zuhu.setBackgroundResource(R.drawable.k_btn_zhong_n);
                break;
            case R.id.select_yezhu:
                roleType= UserType.USER_OWNER;
                ib_jiaren.setBackgroundResource(R.drawable.k_btn_zhong_n);
                ib_yezhujiashu.setBackgroundResource(R.drawable.k_btn_zhong_pre);
                ib_zuhu.setBackgroundResource(R.drawable.k_btn_zhong_n);
                break;
            case R.id.select_zuhu:
                ib_jiaren.setBackgroundResource(R.drawable.k_btn_zhong_n);
                roleType= UserType.USER_LESSEE;
                ib_zuhu.setBackgroundResource(R.drawable.k_btn_zhong_pre);
                ib_yezhujiashu.setBackgroundResource(R.drawable.k_btn_zhong_n);
                break;
            case R.id.ib_zuhu:
                ib_jiaren.setBackgroundResource(R.drawable.k_btn_zhong_n);
                roleType= UserType.USER_LESSEE;
                ib_zuhu.setBackgroundResource(R.drawable.k_btn_zhong_pre);
                ib_yezhujiashu.setBackgroundResource(R.drawable.k_btn_zhong_n);
                break;
            case R.id.select_jiaren:
                ib_jiaren.setBackgroundResource(R.drawable.k_btn_zhong_pre);
                roleType= UserType.USER_FOLK;
                ib_zuhu.setBackgroundResource(R.drawable.k_btn_zhong_n);
                ib_yezhujiashu.setBackgroundResource(R.drawable.k_btn_zhong_n);
                break;
            case R.id.ib_menjinjilu_fanghui:
                onBack();
                break;
            case R.id.ib_menjinjilu_fanghui1:
                onBack();
                break;
            case R.id.ib_quedingtianjia:
                name= String.valueOf(et_name.getText());
                uphone= String.valueOf(tv_lianxidianhua.getText());
                if (!isMobilephone(uphone)){
                    Toast.makeText(XYXuanZeXiaoQuActivity.this,"请输入正确的手机号",Toast.LENGTH_LONG).show();
                    return;
                }
                DialogUntil.showLoadingDialog(XYXuanZeXiaoQuActivity.this,"正在提交",true);
                //提交的按钮，提交数据到服务器。并跳转到小区列表页面
                String addUrl=XY_Response.URL_ADDCOMMUNITY+"mobilePhone="
                        +phone+"&communityId="+communityId+"&nperId="+
                        nperId+"&floorId="+floorId+"&unitId="+unitId+"&doorId="+doorId+
                        "&roleType="+roleType+"&customerName="+name+"&customerTel="+uphone;
                RequestCenter.addCommunity(addUrl, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogUntil.closeLoadingDialog();
                        FanHui fanHui= (FanHui) responseObj;
                        if (fanHui.getCode().equals("1000")){
                            finish();
                            Intent intent=new Intent(XYXuanZeXiaoQuActivity.this,XYKeyAccredit.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(XYXuanZeXiaoQuActivity.this,fanHui.getMsg(),Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogUntil.closeLoadingDialog();
                        MyDialog.showDialog(XYXuanZeXiaoQuActivity.this);
                    }
                });
                break;

        }
    }
    private void setTitle(){
        switch (type){
            case SelectType.XIAOQU:
                select_xiaoqu_title.setText("选择小区");
                break;
            case SelectType.LOUHAO:
                select_xiaoqu_title.setText("选择楼号");
                break;
            case SelectType.DANYUAN:
                select_xiaoqu_title.setText("选择单元");
                break;
            case SelectType.MEN:
                select_xiaoqu_title.setText("选择门号");
                break;
        }
    }
    
    private void onBack(){
        showXuanze();
        type--;
        if (type<0){
            super.onBackPressed();
        }else {
            getData(type);
            adapter=new XYXuanZeXiaoQuAdapter(this,mList);
            lv_xuanzexiaoqu.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            setTitle();
        }
    }
    private void update(){
        getData(type);
        adapter=new XYXuanZeXiaoQuAdapter(this,mList);
        lv_xuanzexiaoqu.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void qinqiushibai(){
        mList=new ArrayList<String>();
        mList.add("当前列表为空");
        lv_xuanzexiaoqu.setEnabled(false);
        Message message=new Message();
        message.obj=mList;
        message.what=1000;
        handler.sendMessage(message);
    }
    public static boolean isMobilephone(String phone) {
        if (phone.startsWith("86") || phone.startsWith("+86")) {
            phone = phone.substring(phone.indexOf("86") + 2);
        }
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}
