package com.example.volleyimage;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

/**
 * 全局队列
 */
public class MyApplication extends Application {
    public static RequestQueue queues;

    @Override
    public void onCreate() {
        super.onCreate();
        //实例化队列
        queues=Volley.newRequestQueue(getApplicationContext());
    }

    //自定义方法，返回全局队列
    public static RequestQueue getHttpQueues(){
        return queues;
    }

}
