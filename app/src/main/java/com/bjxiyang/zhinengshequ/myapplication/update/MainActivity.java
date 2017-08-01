package com.bjxiyang.zhinengshequ.myapplication.update;//package com.sander.guardmanagement.update;
//
//import android.Manifest;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Build;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
//
//
//public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    private Button mButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.);
//        initUI();
//        quanxian();
//    }
//
//    private void initUI() {
//        mButton= (Button) findViewById(R.id.startService);
//        mButton.setOnClickListener(this);
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        Tell.callPhone(MainActivity.this);
////        checkVersion();
//    }
//    private void checkVersion() {
//        RequestCenter.checkVersion(new DisposeDataListener() {
//            @Override
//            public void onSuccess(Object responseObj) {
//                final UpdateModel updateModel = (UpdateModel) responseObj;
//                if (Util.getVersionCode(MainActivity.this) < updateModel.data.currentVersion) {
//                    //说明有新版本,开始下载
//                    CommonDialog dialog = new CommonDialog(MainActivity.this,
//                            getString(R.string.update_new_version),
//                            getString(R.string.update_title),
//                            getString(R.string.cancel),
//                            getString(R.string.update_install),
//                             new CommonDialog.DialogClickListener() {
//                        @Override
//                        public void onDialogClick() {
//                            Intent intent = new Intent(MainActivity.this,UpdateService.class);
//                            startService(intent);
//                        }
//                    });
//                    dialog.setCancelable(false);
//                    dialog.show();
//                } else {
//                    AlertDialog.Builder dialog= new AlertDialog.Builder(MainActivity.this);
//                    dialog.setTitle("提示");
//                    dialog.setCancelable(false);
//                    dialog.setMessage("该版本已是最新版本");
//                    dialog.setIcon(R.mipmap.ic_launcher_round);
//                    dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            // TODO Auto-generated method stub
//                        }
//                    });
//                    dialog.show();
//                    //弹出一个toast提示当前已经是最新版本等处理
//                }
//            }
//
//            @Override
//            public void onFailure(Object reasonObj) {
//
//            }
//        });
//    }
//    private void quanxian(){
//        if (Build.VERSION.SDK_INT >= 23) {
//            int checkCallPhonePermission = ContextCompat.checkSelfPermission(MainActivity.this,
//                    Manifest.permission.CALL_PHONE);
//            if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
//                ActivityCompat.requestPermissions(MainActivity.this,
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
//                return;
//            }else{
//                //上面已经写好的拨号方法
//            }
//        } else {
//            //上面已经写好的拨号方法
//        }
//    }
//}
