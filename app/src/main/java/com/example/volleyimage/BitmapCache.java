package com.example.volleyimage;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * 图片缓存类
 */
public class BitmapCache implements ImageCache {
    public LruCache<String,Bitmap> cache;

    //定义缓存内存的大小,超过这个缓存会启动自动回收
    public int max=10*1024*1024;

    //给出构造
    public BitmapCache(){
        cache=new LruCache<String, Bitmap>(max){

            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }

        };
    }

    @Override
    public Bitmap getBitmap(String arg0) {
        return cache.get(arg0);
    }

    @Override
    public void putBitmap(String arg0, Bitmap arg1) {
        //放入到LruCache中
        cache.put(arg0, arg1);
    }

}
