package com.example.b88;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Cl on 2016/10/10.
 */
public class Welcome extends Activity {

    private boolean isFirstIn = false;

    private final static int GO_HOME = 0;
    private final static int GO_GUIDE = 1;

    private final static int TIME = 2000;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        init();
    }

    private void init() {
        SharedPreferences sp = getSharedPreferences("my", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        isFirstIn = sp.getBoolean("isFirstIn", false);

        if (!isFirstIn) {
            // �ǵ�һ�ν��� isFirstIn == false
            handler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
            // ��״̬���浽�ļ���
            edit.putBoolean("isFirstIn", true);
           
            edit.commit();
        } else {
            handler.sendEmptyMessageDelayed(GO_HOME, TIME);

        }
    }

    private void goHome() {
        startActivity(new Intent(Welcome.this, MainActivity.class));
        finish();
    }

    private void goGuide() {
        startActivity(new Intent(Welcome.this, Guide.class));
        finish();
    }
}
