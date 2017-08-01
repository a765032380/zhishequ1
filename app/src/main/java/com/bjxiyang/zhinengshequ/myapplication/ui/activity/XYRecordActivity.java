package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.bjxiyang.zhinengshequ.R;
/**
 * Created by gll on 17-5-22.
 */

public class XYRecordActivity extends MySwipeBackActivity {
    private ListView mListView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xy_activity_record);
        initUI();
        initDate();
    }

    private void initDate() {

    }

    private void initUI() {
        mListView= (ListView) findViewById(R.id.record_lv);
    }


}
