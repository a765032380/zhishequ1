package com.bjxiyang.zhinengshequ.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.adapter.GouWuCheAdapter;
import com.bjxiyang.zhinengshequ.myapplication.until.ShangPing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gll on 17-5-25.
 */

public class MyDialog extends Dialog {

    private List<ShangPing> list;
    //清空全部
    private RelativeLayout ly_dialog_qingkong;
    //ListView
    private ListView lv_dialog;
    private Context mContext;

    public MyDialog(Context context) {
        super(context);
    }

    public MyDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        initUI();
    }

    private void initUI() {
        ly_dialog_qingkong= (RelativeLayout) findViewById(R.id.ly_dialog_qingkong);
        //清空按钮的监听事件
        ly_dialog_qingkong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击清空按钮后，清除购物车所有产品
            }
        });
        lv_dialog= (ListView) findViewById(R.id.lv_dialog);
        GouWuCheAdapter adapter=new GouWuCheAdapter(mContext,getData());
        adapter.setData(getData());
        lv_dialog.setAdapter(adapter);


    }

    private List<ShangPing> getData(){
        list=new ArrayList<>();

        for (int i=0;i<10;i++){
            ShangPing sp=new ShangPing();
            sp.setName("零食"+i);
            sp.setGuige("290g");
            sp.setJiage("$15");
            sp.setShuliang("999");
            list.add(sp);
        }
        return list;
    }
    public MyDialog listDialog(View view) {
        final MyDialog dialog = new MyDialog(mContext,
                R.style.Dialog);

        dialog.addContentView(view, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        Window window = dialog.getWindow();
        int width = (int) (getOwnerActivity().getWindowManager().getDefaultDisplay().getWidth() * 0.9);
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setLayout(width, height);
        window.setGravity(Gravity.CENTER);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        return dialog;
    }
}
