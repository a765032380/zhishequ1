package com.bjxiyang.zhinengshequ.myapplication.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.DianMing;
import com.bjxiyang.zhinengshequ.myapplication.ui.view.ListViewAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public class ListViewDialog extends AlertDialog implements AdapterView.OnItemClickListener{
    private ListView dialog_listView;
    private List<DianMing.Result> mList;
    private Context mContext;
    private Onsetselect onsetselect;

    protected ListViewDialog(@NonNull Context context) {
        super(context);
    }
    public ListViewDialog(@NonNull Context context,List<DianMing.Result> mList) {
        super(context);
        this.mList=mList;
        this.mContext=context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_list_view);
        initUI();
    }
    public void setOnsetselect(Onsetselect onsetselect){
        this.onsetselect=onsetselect;
    }
    private void initUI() {
        dialog_listView= (ListView) findViewById(R.id.dialog_listView);
        ListViewAdapter adapter=new ListViewAdapter(mList,mContext);
        dialog_listView.setAdapter(adapter);
        dialog_listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        onsetselect.getDianMingResult(mList.get(position));
        cancel();
    }



    public interface Onsetselect{
        public void getDianMingResult(DianMing.Result result);
    }

}
