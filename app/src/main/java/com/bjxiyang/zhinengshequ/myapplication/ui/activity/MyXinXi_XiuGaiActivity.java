package com.bjxiyang.zhinengshequ.myapplication.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.baisi.myapplication.okhttp.request.RequestParams;
import com.bjxiyang.zhinengshequ.R;
import com.bjxiyang.zhinengshequ.myapplication.app.GuardApplication;
import com.bjxiyang.zhinengshequ.myapplication.luban.LuBan;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;
import com.bjxiyang.zhinengshequ.myapplication.manager.UserManager;
import com.bjxiyang.zhinengshequ.myapplication.model.FanHui;
import com.bjxiyang.zhinengshequ.myapplication.model.Users1;
import com.bjxiyang.zhinengshequ.myapplication.response_xy.XY_Response;
import com.bjxiyang.zhinengshequ.myapplication.until.DialogUntil;
import com.bjxiyang.zhinengshequ.myapplication.until.MyUntil;
import com.bjxiyang.zhinengshequ.myapplication.update.network.RequestCenter;
import com.bjxiyang.zhinengshequ.myapplication.view.CircleImageView;
import com.bjxiyang.zhinengshequ.myapplication.view.MyDialog;
import com.testin.agent.Bugout;
import com.testin.agent.BugoutConfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class MyXinXi_XiuGaiActivity extends MySwipeBackActivity implements View.OnClickListener {
    /**
     * UI
     */
    private CircleImageView iv_xiugaixinxi_xiugai_touxiang;
    private EditText et_xiugaixinxi_xiugai_name;
    private ImageView iv_xiugaixinxi_xiugai_men;
    private ImageView iv_xiugaixinxi_xiugai_women;
    private TextView tv_xiugaixinxi_xiugai_chushengriqi;
    private EditText et_xiugaixinxi_xiugai_tel;
    private EditText et_xiugaixinxi_xiugai_email;
    private EditText et_xiugaixinxi_xiugai_qq;
    private EditText et_xiugaixinxi_xiugai_weixin;
    private EditText et_xiugaixinxi_xiugai_address;
    private ImageView iv_xiugaixinxi_xiugai_bt_xiugai;
    private RelativeLayout iv_xiugaixinxi_xiugai_fanhui;

    /**
     * Data
     * @param savedInstanceState
     */
    private Map map=new HashMap();
    private String name;
    private String birthday;
    private String phone;
    private String email;
    private String qq;
    private String weixin;
    private String address;
    private int sex=1;
    private File mFile;
    private String picturePath;
    private Uri uri;
    private Users1.Obj obj;

    //时间选择器
    int mYear, mMonth, mDay;
    final int DATE_DIALOG = 1;
    final int RESULT_LOAD_IMAGE=2;
    //拍照
    final static int CAMERA_RESULT = 0;//声明一个常量，代表结果码


    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;

    private String sdPath;//SD卡的路径
    private String picPath;//图片存储路径

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiugaixinxi_xiugai);
        initUI();
        getData();
        sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        picPath = sdPath + "/" + "temp.png";

        
    }
    private void setEditText(Users1 users){
        obj=users.getObj();
        if ((obj.getNickName())!=null){
            et_xiugaixinxi_xiugai_name.setText(obj.getRealName());
        }
        if (obj.getSex().equals("1")){
            iv_xiugaixinxi_xiugai_men.setBackgroundResource(R.drawable.b_btn_xuanze_pre);
            iv_xiugaixinxi_xiugai_women.setBackgroundResource(R.drawable.e_btn_xuanze);
        }else {
            iv_xiugaixinxi_xiugai_men.setBackgroundResource(R.drawable.e_btn_xuanze);
            iv_xiugaixinxi_xiugai_women.setBackgroundResource(R.drawable.b_btn_xuanze_pre);
        }

        if (obj.getBirthday()!=null){
            tv_xiugaixinxi_xiugai_chushengriqi.setText(obj.getBirthday());
        }
        if (obj.getMobilePhone()!=null){
            et_xiugaixinxi_xiugai_tel.setText(obj.getMobilePhone());
        }
        if (obj.getEmail()!=null){
            et_xiugaixinxi_xiugai_email.setText(obj.getEmail());
        }
        if (obj.getQq()!=null){
            et_xiugaixinxi_xiugai_qq.setText(obj.getQq());
        }
        if (obj.getWeChat()!=null){
            et_xiugaixinxi_xiugai_weixin.setText(obj.getWeChat());
        }
        if (obj.getAddress()!=null){
            et_xiugaixinxi_xiugai_address.setText(obj.getAddress());
        }
        if (obj.getHeadPhotoUrl()!=null&&!obj.getHeadPhotoUrl().equals("")){
//            Bitmap bitmap = getBitmapFromSharedPreferences();
//            mFile=compressImagefile(bitmap);
            ImageLoaderManager.getInstance(this).displayImage(
                    iv_xiugaixinxi_xiugai_touxiang,obj.getHeadPhotoUrl());
        }else {
            if (obj.getSex().equals("1")){
                iv_xiugaixinxi_xiugai_touxiang.setBackgroundResource(R.drawable.c_img_touxiang);
            }else {
                iv_xiugaixinxi_xiugai_touxiang.setBackgroundResource(R.drawable.c_img_touxiang_1);
            }

        }

    }

    private void initUI() {
        iv_xiugaixinxi_xiugai_fanhui= (RelativeLayout) findViewById(R.id.iv_xiugaixinxi_xiugai_fanhui);
        iv_xiugaixinxi_xiugai_touxiang= (CircleImageView) findViewById(R.id.iv_xiugaixinxi_xiugai_touxiang);
        et_xiugaixinxi_xiugai_name= (EditText) findViewById(R.id.et_xiugaixinxi_xiugai_name);
        iv_xiugaixinxi_xiugai_men= (ImageView) findViewById(R.id.iv_xiugaixinxi_xiugai_men);
        iv_xiugaixinxi_xiugai_women= (ImageView) findViewById(R.id.iv_xiugaixinxi_xiugai_women);
        tv_xiugaixinxi_xiugai_chushengriqi= (TextView) findViewById(R.id.tv_xiugaixinxi_xiugai_chushengriqi);
        et_xiugaixinxi_xiugai_tel= (EditText) findViewById(R.id.et_xiugaixinxi_xiugai_tel);
        et_xiugaixinxi_xiugai_email= (EditText) findViewById(R.id.et_xiugaixinxi_xiugai_email);
        et_xiugaixinxi_xiugai_qq= (EditText) findViewById(R.id.et_xiugaixinxi_xiugai_qq);
        et_xiugaixinxi_xiugai_weixin= (EditText) findViewById(R.id.et_xiugaixinxi_xiugai_weixin);
        et_xiugaixinxi_xiugai_address= (EditText) findViewById(R.id.et_xiugaixinxi_xiugai_address);
        iv_xiugaixinxi_xiugai_bt_xiugai= (ImageView) findViewById(R.id.iv_xiugaixinxi_xiugai_bt_xiugai);

        iv_xiugaixinxi_xiugai_fanhui.setOnClickListener(this);
        iv_xiugaixinxi_xiugai_touxiang.setOnClickListener(this);
        iv_xiugaixinxi_xiugai_men.setOnClickListener(this);
        iv_xiugaixinxi_xiugai_women.setOnClickListener(this);
        iv_xiugaixinxi_xiugai_bt_xiugai.setOnClickListener(this);
        tv_xiugaixinxi_xiugai_chushengriqi.setOnClickListener(this);

        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_xiugaixinxi_xiugai_chushengriqi:
                 showDialog(DATE_DIALOG);

                break;
            case R.id.iv_xiugaixinxi_xiugai_fanhui:
                finish();
                break;
            case R.id.iv_xiugaixinxi_xiugai_touxiang:
//                showPickDialog();
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
//                showDialog();
                break;
            case R.id.iv_xiugaixinxi_xiugai_men:
                sex=1;
                iv_xiugaixinxi_xiugai_men.setBackgroundResource(R.drawable.b_btn_xuanze_pre);
                iv_xiugaixinxi_xiugai_women.setBackgroundResource(R.drawable.e_btn_xuanze);
                break;
            case R.id.iv_xiugaixinxi_xiugai_women:
                sex=0;
                iv_xiugaixinxi_xiugai_men.setBackgroundResource(R.drawable.e_btn_xuanze);
                iv_xiugaixinxi_xiugai_women.setBackgroundResource(R.drawable.b_btn_xuanze_pre);
                break;
            case R.id.iv_xiugaixinxi_xiugai_bt_xiugai:
                name= String.valueOf(et_xiugaixinxi_xiugai_name.getText());
                email= String.valueOf(et_xiugaixinxi_xiugai_email.getText());
                qq= String.valueOf(et_xiugaixinxi_xiugai_qq.getText());
                weixin= String.valueOf(et_xiugaixinxi_xiugai_weixin.getText());
                address= String.valueOf(et_xiugaixinxi_xiugai_address.getText());
                birthday= String.valueOf(tv_xiugaixinxi_xiugai_chushengriqi.getText());

                quanxian();

                DialogUntil.showLoadingDialog(MyXinXi_XiuGaiActivity.this,"正在提交",true);

                

                RequestParams params=new RequestParams();
                if (mFile==null){

                    String url1= XY_Response.URL_UPDATEUSERINFOFORANDROID
                            +"mobilePhone="
                            +UserManager.getInstance().getUser().getObj().getMobilePhone()
                            +"&realName="+name+
                            "&sex="+sex+"&birthday="+birthday+"&qq="+qq
                            +"&weChat="+weixin+"&email="+email
                            +"&address="+address;
                    RequestCenter.register(url1, new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            DialogUntil.closeLoadingDialog();
                            FanHui fanHui= (FanHui) responseObj;
                            if (fanHui.getCode().equals("1000")){
                                Intent intent=new Intent(MyXinXi_XiuGaiActivity.this,MyXinXiActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                MyUntil.show(MyXinXi_XiuGaiActivity.this,fanHui.getMsg());
                            }
                        }

                        @Override
                        public void onFailure(Object reasonObj) {
                            DialogUntil.closeLoadingDialog();
                        }
                    });


//                    params.put("iconFile",null);
                    map.put("iconFile",null);
                }else {
                    String url = XY_Response.URL_UPDATEUSERINFO
                            + "mobilePhone="
                            + UserManager.getInstance().getUser().getObj().getMobilePhone()
                            + "&realName=" + name +
                            "&sex=" + sex + "&birthday=" + birthday + "&qq=" + qq
                            + "&weChat=" + weixin + "&email=" + email
                            + "&address=" + address;
//                    try {
//                        params.put("iconFile", mFile);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }

                    RequestCenter.uploadPictures(url,map, new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            DialogUntil.closeLoadingDialog();
                            FanHui fanHui= (FanHui) responseObj;
                            if (fanHui.getCode().equals("1000")){
                                Intent intent=new Intent(MyXinXi_XiuGaiActivity.this,MyXinXiActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                MyUntil.show(MyXinXi_XiuGaiActivity.this,fanHui.getMsg());
                            }
                        }
                        @Override
                        public void onFailure(Object reasonObj) {
                            DialogUntil.closeLoadingDialog();
                            MyDialog.showDialog(MyXinXi_XiuGaiActivity.this,"请检查网络");
                        }
                    });
                }
                break;
        }
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, mYear, mMonth, mDay);
        }
        return null;
    }

    /**
     * 设置日期 利用StringBuffer追加
     */
    public void display() {
        tv_xiugaixinxi_xiugai_chushengriqi.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").append(mDay).append(" "));
    }

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            display();
        }
    };

    /**
     * 用onActivityResult()接收传回的图像，当用户拍完照片，或者取消后，系统都会调用这个函数
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == RESULT_CANCELED && resultCode == RESULT_OK) {

//            FileInputStream fis = null;
//            try {
//                Log.e("sdPath2",picPath);
//                //把图片转化为字节流
//                fis = new FileInputStream(picPath);
//                //把流转化图片
//                Bitmap bitmap = compressImage(BitmapFactory.decodeStream(fis));
//                mFile=compressImagefile(bitmap);
//                iv_xiugaixinxi_xiugai_touxiang.setImageBitmap(bitmap);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }finally{
//                try {
//                    fis.close();//关闭流
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }


        Bundle extras = intent.getExtras();//从Intent中获取附加值
            Bitmap bitmap=(Bitmap) extras.get("data");
            mFile=compressImagefile(bitmap);
            LuBan.setOnGetImage(MyXinXi_XiuGaiActivity.this, mFile, new LuBan.OnGetImage() {
                @Override
                public void getImage(File file) {
                    map.put("iconFile", file);

                }
            });

//            saveBitmapToSharedPreferences((Bitmap) extras.get("data"));
//            从附加值中获取返回的图像
            iv_xiugaixinxi_xiugai_touxiang.setImageBitmap(bitmap);
            bitmap.recycle();
        }
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            Uri uri1 = intent.getData();
            Bitmap bitmap=getBitmapFromUri(uri1);
//            iv_xiugaixinxi_xiugai_touxiang.setImageBitmap(bitmap);
//            saveBitmapToSharedPreferences(bitmap);
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(uri1,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath= cursor.getString(columnIndex);
            mFile=new File(picturePath);
            LuBan.setOnGetImage(MyXinXi_XiuGaiActivity.this, mFile, new LuBan.OnGetImage() {
                @Override
                public void getImage(File file) {
                    map.put("iconFile", file);

                }
            });
            cursor.close();
            bitmap.recycle();
            ImageLoaderManager.getInstance(MyXinXi_XiuGaiActivity.this)
                    .displayImage(iv_xiugaixinxi_xiugai_touxiang,String.valueOf(uri1));

        }
    }
    private void showDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(MyXinXi_XiuGaiActivity.this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("请选择添加方式");
        builder.setNegativeButton("图库选择", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        builder.setNeutralButton("拍照", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//实例化Intent对象,使用MediaStore的ACTION_IMAGE_CAPTURE常量调用系统相机
                startActivityForResult(intent, CAMERA_RESULT);//开启相机，传入上面的Intent对象
            }
        });
        builder.show();



    }
    //向User中添加信息

    //将修改后的信息保存到SP中
    private void setSP(Users1 users){
        //会员编号
        SPManager.getInstance().putString("c_memberId", String.valueOf(users.getObj().getC_memberId()));
        SPManager.getInstance().putString("mobilePhone",users.getObj().getMobilePhone());
        SPManager.getInstance().putString("realName",users.getObj().getRealName());
        SPManager.getInstance().putString("nickName",users.getObj().getNickName());
        SPManager.getInstance().putString("sex",users.getObj().getSex());
        SPManager.getInstance().putString("headPhotoUrl",users.getObj().getHeadPhotoUrl());
        SPManager.getInstance().putString("birthday",users.getObj().getBirthday());
        SPManager.getInstance().putString("email",users.getObj().getEmail());
        SPManager.getInstance().putString("address",users.getObj().getAddress());
        SPManager.getInstance().putString("qq",users.getObj().getQq());
        SPManager.getInstance().putString("weChat",users.getObj().getWeChat());
        SPManager.getInstance().putString("age", users.getObj().getAge());
        SPManager.getInstance().putString("ownerId",users.getObj().getOwnerId());
        users.setObj(null);
    }
    //弹出选择图库还是拍照的Dialog
    private void showPickDialog() {
        new AlertDialog.Builder(this)
                .setTitle("设置头像...")
                .setNegativeButton("相册", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // 激活系统图库，选择一张图片
//                        Intent intent1 = new Intent(Intent.ACTION_PICK);
//                        intent1.setType("image/*");
//                        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
//                        startActivityForResult(intent1, PHOTO_REQUEST_GALLERY);

                        Intent intent = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, RESULT_LOAD_IMAGE);


                    }
                })
                .setPositiveButton("拍照", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
// 激活相机
//                        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        Uri uri = Uri.fromFile(new File(picPath));
//                        //为拍摄的图片指定一个存储的路径
//                        intent2.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//                        startActivityForResult(intent2, CAMERA_RESULT);
                        Intent intent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, CAMERA_RESULT);
                    }
                }).show();
    }
    //裁剪图片的方法
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;

        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    public static File compressImagefile(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 500) {  //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            long length = baos.toByteArray().length;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String filename = format.format(date);
        File file = new File(Environment.getExternalStorageDirectory(),filename+".png");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        recycleBitmap(bitmap);
        return file;
    }
    public static void recycleBitmap(Bitmap... bitmaps) {
        if (bitmaps==null) {
            return;
        }
        for (Bitmap bm : bitmaps) {
            if (null != bm && !bm.isRecycled()) {
                bm.recycle();
            }
        }
    }
    public Uri getImageContentUri(File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = MyXinXi_XiuGaiActivity.this.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Images.Media._ID },
                MediaStore.Images.Media.DATA + "=? ",
                new String[] { filePath }, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return MyXinXi_XiuGaiActivity.this.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }
    private void getData(){
        DialogUntil.showLoadingDialog(this,"正在加载",true);
        String url= XY_Response.URL_GETUSERINFO+"mobilePhone="+
                SPManager.getInstance().getString("mobilePhone",null);
        RequestCenter.getUserInfo(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                Users1 users= (Users1) responseObj;
                setEditText(users);
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
                MyDialog.showDialog(MyXinXi_XiuGaiActivity.this);

            }
        });
    }
    public  String getFilePathFromContentUri(Uri selectedVideoUri) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};

//        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn, null, null, null);
//      也可用下面的方法拿到cursor
      Cursor cursor = this.managedQuery(selectedVideoUri, filePathColumn, null, null, null);

        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }
    private File getmFile(String url){
        Uri uri = Uri.parse(url);
        Cursor actualimagecursor = MyXinXi_XiuGaiActivity.this.getContentResolver()
                .query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }
                        , null, null, null );
        int actual_image_column_index =
                actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualimagecursor.moveToFirst();
        String img_path = actualimagecursor.getString(actual_image_column_index);
        mFile = new File(img_path);
        return mFile;
    }
    private void quanxian(){
        if (Build.VERSION.SDK_INT >= 23) {
            getQuanXian(Manifest.permission.WRITE_EXTERNAL_STORAGE, 1);
            getQuanXian(Manifest.permission.READ_EXTERNAL_STORAGE, 1);
            return;
        }
    }
    private void getQuanXian(String permission,int code){
        int permissionInt= ContextCompat.checkSelfPermission(this,
                permission);
        if(permissionInt != PackageManager.PERMISSION_GRANTED){
            android.support.v4.app.ActivityCompat.requestPermissions(this,
                    new String[]{permission},code);
            return;
        }

    }

    private void saveBitmapToSharedPreferences(Bitmap bitmap) {
        // Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        //第三步:将String保持至SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("testSP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("image", imageString);
        editor.commit();
        SPManager.getInstance().putString("image", imageString);

    }
    private Bitmap getBitmapFromUri(Uri uri) {
        try
        {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            return bitmap;
        }
        catch (Exception e)
        {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }
    public static String getRealFilePath(final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = GuardApplication.getContent().getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
    private Bitmap getBitmapFromSharedPreferences() {
        Bitmap bitmap = null;
        SharedPreferences sharedPreferences;
        sharedPreferences = GuardApplication.getContent().getSharedPreferences("testSP", Context.MODE_PRIVATE);
        //第一步:取出字符串形式的Bitmap
        String imageString = sharedPreferences.getString("image", "");
        //第二步:利用Base64将字符串转换为ByteArrayInputStream
        byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
        if (byteArray.length == 0) {
        } else {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);

            //第三步:利用ByteArrayInputStream生成Bitmap
            bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
        }
        return bitmap;

    }
}
