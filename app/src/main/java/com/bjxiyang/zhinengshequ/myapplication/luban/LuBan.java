package com.bjxiyang.zhinengshequ.myapplication.luban;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.io.File;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by Administrator on 2017/7/17 0017.
 */

public class LuBan {
    private static OnGetImage onGetImage;
    private static File file;
    private static Context context;

    public static void setOnGetImage(Context context,File file,OnGetImage onGetImage){
        LuBan.onGetImage=onGetImage;
        LuBan.context=context;
        LuBan.file=file;
        yasuoImage();
    }

    static Handler mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            onGetImage.getImage((File) msg.obj);
            return false;
        }
    });

    public static void yasuoImage(){
        Luban.with(context)
                .load(file)                     //传人要压缩的图片
                .setCompressListener(new OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {

                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }
                    @Override
                    public void onSuccess(File file) {
                        Message message=new Message();
                        message.obj=file;
                        mHandler.sendMessage(message);

                        // TODO 压缩成功后调用，返回压缩后的图片文件
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();    //启动压缩
    }

   public interface OnGetImage{
        public void getImage(File file);
    }
}
