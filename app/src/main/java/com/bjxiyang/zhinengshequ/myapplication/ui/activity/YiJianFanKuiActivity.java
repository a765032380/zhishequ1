package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.MyDialog;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.lidroid.xutils.bitmap.core.BitmapDecoder.calculateInSampleSize;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class YiJianFanKuiActivity extends MySwipeBackActivity implements View.OnClickListener{

    private static final int RESULT_LOAD_IMAGE_ONE=1;
    private static final int RESULT_LOAD_IMAGE_TWO=2;
    private static final int RESULT_LOAD_IMAGE_THREE=3;
    private static final int RESULT_LOAD_IMAGE_FOUR=4;

    private String yijian;
    private String phone;
    private Map map;

    private File mFile;
    private String picturePath;

    private RelativeLayout iv_yijianfankui_fanhui;
    private EditText et_fankuiyijian;
    private EditText et_fankuiyijian_lianxifangshi;
    private ImageView iv_yijianfankui_tianjiatupian1,
            iv_yijianfankui_tianjiatupian2,
            iv_yijianfankui_tianjiatupian3,
            iv_yijianfankui_tianjiatupian4,
            iv_yijianfankui_tijiaofankui;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yijianfankui);
        initUI();
        initData();
    }

    private void initData() {
        map=new HashMap();
    }

    private void initUI() {
        iv_yijianfankui_fanhui= (RelativeLayout) findViewById(R.id.iv_yijianfankui_fanhui);
        et_fankuiyijian= (EditText) findViewById(R.id.et_fankuiyijian);
        et_fankuiyijian_lianxifangshi=
                (EditText) findViewById(R.id.et_fankuiyijian_lianxifangshi);

        iv_yijianfankui_tianjiatupian1=
                (ImageView) findViewById(R.id.iv_yijianfankui_tianjiatupian1);
        iv_yijianfankui_tianjiatupian2=
                (ImageView) findViewById(R.id.iv_yijianfankui_tianjiatupian2);
        iv_yijianfankui_tianjiatupian3=
                (ImageView) findViewById(R.id.iv_yijianfankui_tianjiatupian3);
        iv_yijianfankui_tianjiatupian4=
                (ImageView) findViewById(R.id.iv_yijianfankui_tianjiatupian4);
        iv_yijianfankui_tijiaofankui=
                (ImageView) findViewById(R.id.iv_yijianfankui_tijiaofankui);

        iv_yijianfankui_fanhui.setOnClickListener(this);
        iv_yijianfankui_tianjiatupian1.setOnClickListener(this);
        iv_yijianfankui_tianjiatupian2.setOnClickListener(this);
        iv_yijianfankui_tianjiatupian3.setOnClickListener(this);
        iv_yijianfankui_tianjiatupian4.setOnClickListener(this);
        iv_yijianfankui_tijiaofankui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_yijianfankui_fanhui:
                finish();
                break;
            case R.id.iv_yijianfankui_tianjiatupian1:
                selectImage(RESULT_LOAD_IMAGE_ONE);
                break;
            case R.id.iv_yijianfankui_tianjiatupian2:
                selectImage(RESULT_LOAD_IMAGE_TWO);
                break;
            case R.id.iv_yijianfankui_tianjiatupian3:
                selectImage(RESULT_LOAD_IMAGE_THREE);
                break;
            case R.id.iv_yijianfankui_tianjiatupian4:
                selectImage(RESULT_LOAD_IMAGE_FOUR);
                break;
            //提交反馈按钮
            case R.id.iv_yijianfankui_tijiaofankui:
                yijian= String.valueOf(et_fankuiyijian.getText());
                phone= String.valueOf(et_fankuiyijian_lianxifangshi.getText());
                String url= XY_Response.URL_USERSUGGEST
                        +"mobilePhone="+
                        UserManager.getInstance().getUser().getObj().getMobilePhone()+
                        "&suggestContent="+yijian+"&contactWay="+phone;
                DialogUntil.showLoadingDialog(this,"正在提交",false);
                if(mFile==null){
                    map.put("suggestPic",new File(""));
                }
                RequestCenter.register(url,new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogUntil.closeLoadingDialog();
                        FanHui fanHui= (FanHui) responseObj;
                        if (fanHui.getCode().equals("1000")){
                            Toast.makeText(YiJianFanKuiActivity.this,"提交成功",Toast.LENGTH_LONG).show();
                            finish();
                        }else {
                            Toast.makeText(YiJianFanKuiActivity.this,fanHui.getMsg(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogUntil.closeLoadingDialog();
                        MyDialog.showDialog(YiJianFanKuiActivity.this,"请检查网络");
                    }
                });
                break;

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && null != data){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath= cursor.getString(columnIndex);
            mFile=new File(picturePath);
            map.put("suggestPic",mFile);
            cursor.close();
//           String url= getRealPathFromUri(YiJianFanKuiActivity.this,data.getData());
            switch (requestCode){
                case RESULT_LOAD_IMAGE_ONE:
//                    Log.i("IIIII",String.valueOf(url));
//                    Bitmap bm=getSmallBitmap(url); //xxx根据你的情况获取
//                    BitmapDrawable bd=new BitmapDrawable(null,bm);
                    ImageLoaderManager.getInstance(YiJianFanKuiActivity.this)
                            .displayImage(iv_yijianfankui_tianjiatupian1, String.valueOf(selectedImage));
//                    iv_yijianfankui_tianjiatupian1.setImageURI(data.getData());
                    iv_yijianfankui_tianjiatupian2.setVisibility(View.VISIBLE);
                    break;
                case RESULT_LOAD_IMAGE_TWO:
                    ImageLoaderManager.getInstance(YiJianFanKuiActivity.this)
                            .displayImage(iv_yijianfankui_tianjiatupian2,String.valueOf(selectedImage));
//                    iv_yijianfankui_tianjiatupian2.setImageURI(data.getData());
                    iv_yijianfankui_tianjiatupian3.setVisibility(View.VISIBLE);
                    break;
                case RESULT_LOAD_IMAGE_THREE:
                    ImageLoaderManager.getInstance(YiJianFanKuiActivity.this)
                            .displayImage(iv_yijianfankui_tianjiatupian3,String.valueOf(selectedImage));
//                    iv_yijianfankui_tianjiatupian3.setImageURI(data.getData());
                    iv_yijianfankui_tianjiatupian4.setVisibility(View.VISIBLE);
                    break;
                case RESULT_LOAD_IMAGE_FOUR:
                    ImageLoaderManager.getInstance(YiJianFanKuiActivity.this)
                            .displayImage(iv_yijianfankui_tianjiatupian4,String.valueOf(selectedImage));
//                    iv_yijianfankui_tianjiatupian4.setImageURI(data.getData());
                    break;
            }
        }
    }

    private void selectImage(int code){
        Intent i = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, code);
    }
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, 640, 960);
        options.inJustDecodeBounds = false;
        Bitmap localBitmap1 = BitmapFactory.decodeFile(filePath, options);
        return localBitmap1;
    }
    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

}
