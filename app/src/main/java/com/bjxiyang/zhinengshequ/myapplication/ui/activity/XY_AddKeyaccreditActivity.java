package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.model.SelectPlot;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.UserType;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.MyDialog;
import com.bjxiyang.zhinengshequ.myapplication.widgets.CommonActionSheetDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gll on 17-5-23.
 */

public class XY_AddKeyaccreditActivity extends MySwipeBackActivity implements View.OnClickListener {

    private LinearLayout add_select_yezhu,add_select_zuke;
    private List<SelectPlot.Obj> mList;
    private RelativeLayout ib_menjinjilu_fanghui;
    private EditText et_name;
    private EditText et_lianxidianhua;
    private ImageButton ib_quedingtianjia;
    private String name;
    private String phone;
    private ImageButton ib_yezhujiashu;
    private ImageButton ib_zuke;
    public CommonActionSheetDialog commonActionSheetDialog;
    private LinearLayout select_xiaoqu;
    private TextView select_xiaoqu_tianjiashouquan;

    private int communityId;
    private int nperId;
    private int floorId;
    private int unitId;
    private int doorId;
    private int roleType=UserType.USER_FOLK;


    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            if (msg.what==1000){

                mList= (List<SelectPlot.Obj>) msg.obj;
                showActionSheet();
            }
            return false;
        }
    });
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xy_activity_tianjiashouquan);
        initUI();
    }

    private void initUI() {
        add_select_yezhu= (LinearLayout) findViewById(R.id.add_select_yezhu);
        add_select_yezhu.setOnClickListener(this);
        add_select_zuke= (LinearLayout) findViewById(R.id.add_select_zuke);
        add_select_zuke.setOnClickListener(this);
        select_xiaoqu_tianjiashouquan= (TextView) findViewById(R.id.select_xiaoqu_tianjiashouquan);
        ib_menjinjilu_fanghui= (RelativeLayout) findViewById(R.id.ib_menjinjilu_fanghui);
        ib_menjinjilu_fanghui.setOnClickListener(this);
        et_name= (EditText) findViewById(R.id.et_name);
        et_lianxidianhua= (EditText) findViewById(R.id.et_lianxidianhua);
        ib_quedingtianjia= (ImageButton) findViewById(R.id.ib_quedingtianjia);
        ib_quedingtianjia.setOnClickListener(this);
        ib_yezhujiashu= (ImageButton) findViewById(R.id.ib_yezhujiashu);
        ib_zuke= (ImageButton) findViewById(R.id.ib_zuke);
        select_xiaoqu= (LinearLayout) findViewById(R.id.select_xiaoqu);
        select_xiaoqu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回按键
            case R.id.ib_menjinjilu_fanghui:
                finish();
                break;
            //确定添加按键
            case R.id.ib_quedingtianjia:
                name= String.valueOf(et_name.getText());
                phone= String.valueOf(et_lianxidianhua.getText());
                if (!isMobilephone(phone)){
                    Toast.makeText(XY_AddKeyaccreditActivity.this,"请输入正确的手机号",Toast.LENGTH_LONG).show();
                    return;
                }
                if (roleType==UserType.USER_OWNER){
                    roleType= UserType.USER_FOLK;
                }else if (roleType==UserType.USER_LESSEE){
                    roleType= UserType.USER_LESSEE_HOME;
                }

                String url=XY_Response.URL_ADDPERMISSION+"mobilePhone="
                        +UserManager.getInstance().getUser().getObj().getMobilePhone()+
                        "&communityId="+communityId+"&nperId="+nperId+
                        "&floorId="+floorId+"&unitId="+unitId+"&doorId="+
                        doorId+"&roleType="+roleType+"&customerName="+name+"&customerTel="
                        +phone;

                DialogUntil.showLoadingDialog(XY_AddKeyaccreditActivity.this,"正在提交",true);
                RequestCenter.addPermission(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogUntil.closeLoadingDialog();
                        FanHui fanHui= (FanHui) responseObj;
                        if (fanHui.getCode().equals("1000")){
                            finish();
                        }else {
                            MyUntil.show(XY_AddKeyaccreditActivity.this,fanHui.getMsg());
                        }

                    }
                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogUntil.closeLoadingDialog();
                        MyDialog.showDialog(XY_AddKeyaccreditActivity.this);
                    }
                });
                break;
            //选择
            case R.id.add_select_yezhu:
                ib_yezhujiashu.setBackgroundResource(R.drawable.k_btn_zhong_pre);
                ib_zuke.setBackgroundResource(R.drawable.k_btn_zhong_n);
                break;
            case R.id.add_select_zuke:
                ib_zuke.setBackgroundResource(R.drawable.k_btn_zhong_pre);
                ib_yezhujiashu.setBackgroundResource(R.drawable.k_btn_zhong_n);
                break;
            case R.id.select_xiaoqu:
                getData();
                break;

        }
    }
    //得到当前用户所拥有的小区
    private void getXiaoqu(){

    }
    public void showActionSheet()
    {
        if (mList.size()>0) {
            if (commonActionSheetDialog == null) {
                commonActionSheetDialog = new CommonActionSheetDialog(this);

                for (SelectPlot.Obj item : mList) {
                    commonActionSheetDialog.addMenuItem(item.getCommunityName()
                            +item.getNperName()+item.getFloorName()
                            + item.getUnitName()
                            + item.getDoorName());
                }

                commonActionSheetDialog.setMenuListener(new CommonActionSheetDialog.MenuListener() {
                    @Override
                    public void onItemSelected(int position, String item) {

                        communityId = mList.get(position).getCommunityId();
                        nperId = mList.get(position).getNperId();
                        floorId = mList.get(position).getFloorId();
                        unitId = mList.get(position).getUnitId();
                        doorId = mList.get(position).getDoorId();
                        select_xiaoqu_tianjiashouquan.setText(mList.get(position).getCommunityName()
                                + mList.get(position).getNperName() +
                                 mList.get(position).getUnitName() +
                                 mList.get(position).getDoorName());
                        roleType=mList.get(position).getRoleType();
                    }

                    @Override
                    public void onCancel() {

                    }
                });

            }
            commonActionSheetDialog.show();
        }else {
            select_xiaoqu_tianjiashouquan.setText("请添加小区");
        }

    }
    private List<SelectPlot.Obj> getData(){
        mList=new ArrayList<>();
        DialogUntil.showLoadingDialog(XY_AddKeyaccreditActivity.this,"正在加载",true);
        String url= XY_Response.URL_FINDCOMMUNITYBYPER+"mobilePhone="+
                UserManager.getInstance().getUser().getObj().getMobilePhone();
        RequestCenter.findCommunityByPer(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                SelectPlot selectPlot= (SelectPlot) responseObj;
                DialogUntil.closeLoadingDialog();
                if (selectPlot.getCode().equals("1000")){
                    Message message=new Message();
                    message.what=1000;
                    message.obj=selectPlot.getObj();
                    handler.sendMessage(message);
                }else {
                    Toast.makeText(XY_AddKeyaccreditActivity.this,selectPlot.getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
                MyDialog.showDialog(XY_AddKeyaccreditActivity.this,"请检查网络连接");
            }
        });
        return mList;
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
