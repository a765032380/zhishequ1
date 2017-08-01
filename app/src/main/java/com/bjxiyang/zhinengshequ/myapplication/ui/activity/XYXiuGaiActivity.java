package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.model.Plot;
import com.bjxiyang.zhinengshequ.myapplication.widgets.CommonActionSheetDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gll on 17-5-23.
 */

public class XYXiuGaiActivity extends MySwipeBackActivity implements View.OnClickListener{

    private List<Plot> mList;

    private RelativeLayout ib_querenxiugai_fanghui;
    private EditText et_name_xiugai;
    private EditText et_lianxidianhua_xiugai;
    private ImageButton ib_quedingtianjia;
    private LinearLayout select_xiaoqu_xiugai;
    private String name;
    private String phone;
    private TextView select_xiaoqu_tv;
    public CommonActionSheetDialog commonActionSheetDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xy_activity_xiugaixinxi);
        initUI();
    }

    private void initUI() {
        select_xiaoqu_tv= (TextView) findViewById(R.id.select_xiaoqu_tv);
        ib_querenxiugai_fanghui= (RelativeLayout) findViewById(R.id.ib_querenxiugai_fanghui);
        ib_querenxiugai_fanghui.setOnClickListener(this);
        ib_quedingtianjia= (ImageButton) findViewById(R.id.ib_quedingtianjia);
        ib_quedingtianjia.setOnClickListener(this);
        et_name_xiugai= (EditText) findViewById(R.id.et_name_xiugai);
        et_lianxidianhua_xiugai= (EditText) findViewById(R.id.et_lianxidianhua_xiugai);
        select_xiaoqu_xiugai= (LinearLayout) findViewById(R.id.select_xiaoqu_xiugai);
        select_xiaoqu_xiugai.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回的按键
            case R.id.ib_querenxiugai_fanghui:
                finish();
                break;
            //提交的按键提交数据并关闭当前页面
            case R.id.ib_quedingtianjia:
                name= String.valueOf(et_name_xiugai.getText());
                phone= String.valueOf(et_lianxidianhua_xiugai.getText());
                finish();
                break;
            case R.id.select_xiaoqu_xiugai:
                showActionSheet();
                break;
        }
    }
    public void showActionSheet()
    {
        if (commonActionSheetDialog == null){
            commonActionSheetDialog = new CommonActionSheetDialog(this);
            List<Plot> list= getData();
            for (Plot item:list){
                commonActionSheetDialog.addMenuItem(item.getPlot()
                        +"--"+item.getBuildingNo()+"号楼--"+item.getUnitNumber()+
                        "单元");
            }

            commonActionSheetDialog.setMenuListener(new CommonActionSheetDialog.MenuListener() {
                @Override
                public void onItemSelected(int position, String item) {
                   select_xiaoqu_tv.setText(mList.get(position).getPlot()
                            +"--"+mList.get(position).getBuildingNo()+"号楼--"
                            +mList.get(position).getUnitNumber()+
                            "单元");
                }

                @Override
                public void onCancel() {

                }
            });

        }
        commonActionSheetDialog.show();

    }
    private List<Plot> getData(){
        mList=new ArrayList<>();
        for (int i=0;i<5;i++){
            Plot plot=new Plot();
            plot.setPlot("鹏景阁大厦"+i);
            plot.setBuildingNo(i);
            plot.setUnitNumber(i);
            mList.add(plot);
        }
        return mList;


    }


}
