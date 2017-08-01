package com.baisi.myapplication.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.baisi.myapplication.okhttp.https.HttpsUtils;
import com.baisi.myapplication.okhttp.listener.DisposeDataHandle;
import com.baisi.myapplication.okhttp.response.CommonFileCallback;
import com.baisi.myapplication.okhttp.response.CommonJsonCallback;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author gll
 * 请求的发送，请求参数的配置，https支持
 */
public class CommonOkHttpClient {
    private static final Handler handler = new Handler(Looper.getMainLooper());

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*");
    private static final int TIME_OUT=300;//超时参数
    private static OkHttpClient mOkHttpClient;
    private static Call call;

    //为我们的client取配置参数
    static {
        //创建我们client对象的构建者
        OkHttpClient.Builder okHttpBuilder=new OkHttpClient.Builder();
        //为构建者填充超时时间
        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        //允许重定向
        okHttpBuilder.followRedirects(true);
        //https支持
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        okHttpBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory());
        mOkHttpClient=okHttpBuilder.build();
    }



    public final static void uploadImgAndParameter(Map<String, Object> map,
                                                    String url,DisposeDataHandle handle) {

         OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        okhttp3.Request request = chain.request().newBuilder()
                                .build();
                        return chain.proceed(request);
                    }
                }).readTimeout(TIME_OUT, TimeUnit.SECONDS)// 设置读取超时时间
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)// 设置写的超时时间
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)// 设置连接超时时间
                .build();

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() != null) {
                    if (entry.getValue() instanceof File) {
                        File f = (File) entry.getValue();
                        builder.addFormDataPart(entry.getKey(), f.getName(),
                                RequestBody.create(MEDIA_TYPE_PNG, f));
                        Log.i("FILE",f.getAbsolutePath());
                    } else {
                        builder.addFormDataPart(entry.getKey(), entry
                                .getValue().toString());
                        Log.i("FILE",entry.getValue().toString());
                    }
                }

            }
        }else {
//            builder.addPart();

        }
        // 创建RequestBody
        okhttp3.Request request = null;
//        if (map.get("iconFile")!=null){
            RequestBody body = builder.build();
            request = new okhttp3.Request.Builder().url(url)// 地址
                    .post(body)// 添加请求体
                    .build();
//        }
//        else {
//            MultipartBody.Builder builder1 = new MultipartBody.Builder()
//                    .setType(MultipartBody.FORM);
//
//            builder.addPart(RequestBody.create(MEDIA_TYPE_PNG, (File) null));
//
//            RequestBody mFormBody = builder1.build();
//            request= new okhttp3.Request.Builder().url(url)// 地址
//                    .post(mFormBody)// 添加请求体
//                    .build();
//        }
        client.newCall(request).enqueue(new CommonJsonCallback(handle));

    }

//    private void uploadImg(DisposeDataHandle handle) {
//
//
//
//        // mImgUrls为存放图片的url集合
//        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//        for (int i = 0; i <mImgUrls.size() ; i++) {
//            File f=new File(mImgUrls.get(i));
//            if (f!=null) {
//                builder.addFormDataPart("img", f.getName(), RequestBody.create(MEDIA_TYPE_PNG, f));
//            }
//        }
//        MultipartBody requestBody = builder.build();
//        //构建请求
//        Request request = new Request.Builder()
//                .url(Constant.BASE_URL)//地址
//                .post(requestBody)//添加请求体
//                .build();
//
//        call=mOkHttpClient.newCall(request);
//        call.enqueue(new CommonFileCallback(handle));
//
//    }

    /**
     * 发送具体的http/https请求
     * @param request
     * @param commcallback
     * @return
     */
    public static Call sendRequest(Request request, Callback commcallback){
        call=mOkHttpClient.newCall(request);
        call.enqueue(commcallback);
        return call;
    }
    public static Call uploadPictures2(Request request, DisposeDataHandle handle){
        call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }


    public static Call get(Request request, DisposeDataHandle handle) {
        call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call post(Request request, DisposeDataHandle handle) {
        call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call downloadFile(Request request, DisposeDataHandle handle) {
        call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(handle));
        return call;
    }





    public static void breakLink(){
        call.cancel();
    }

}
