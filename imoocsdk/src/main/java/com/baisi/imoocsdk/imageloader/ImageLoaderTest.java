package com.baisi.imoocsdk.imageloader;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by gll on 17-3-8.
 */
public class ImageLoaderTest {
    private void testApi(){
        /**
         * 我们为ImageLoader配置参数
         */
//        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(content).build();

        /**
         * 我们先来获取到ImageLoader的一个实例
         */
        ImageLoader imageLoader=ImageLoader.getInstance();

        /**
         * 为我们显示图片的时候取进行一些配置
         */
        DisplayImageOptions options=new DisplayImageOptions.Builder().build();
        /**
         * 使用displayImage去加载图片
         */
//        imageLoader.displayImage("url",imageView,options,new SimpleImageLoadingListener() {
//
//
//        });
//        ImageLoaderManager.getInstance(context).displayImage();

    }
}
