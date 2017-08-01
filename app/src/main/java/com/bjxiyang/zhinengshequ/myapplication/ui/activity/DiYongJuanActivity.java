package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.model.TestDiYongJuan;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.DiYongJuanAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class DiYongJuanActivity extends MySwipeBackActivity implements View.OnClickListener{
    private RelativeLayout rl_diyongquan_fanhui;
    private ListView lv_diyongquan;
    private List<TestDiYongJuan> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyongquan);
        initUi();
        initDate();
    }

    private void initDate() {
        DiYongJuanAdapter adapter=new DiYongJuanAdapter(this,getData());
        lv_diyongquan.setAdapter(adapter);

    }

    private void initUi() {
        rl_diyongquan_fanhui= (RelativeLayout) findViewById(R.id.rl_diyongquan_fanhui);
        rl_diyongquan_fanhui.setOnClickListener(this);
        lv_diyongquan= (ListView) findViewById(R.id.lv_diyongquan);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_diyongquan_fanhui:
                finish();
                break;
        }
    }
    private List<TestDiYongJuan> getData(){
        mList=new ArrayList<>();
        TestDiYongJuan diyongjuan=new TestDiYongJuan();
        for (int i=0;i<10;i++){

            diyongjuan.setDate("有效期至2017-9-9");
            diyongjuan.setDuijianghao("256899");
            diyongjuan.setName("食用大豆油");
            diyongjuan.setShouji("仅限18813045215使用");
            diyongjuan.setXianshi("限20:00-23:00、夜宵频道使用。");

            mList.add(diyongjuan);
        }
        return mList;
    }
}
