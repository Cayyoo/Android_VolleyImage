package com.example.volleyimage;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * LruCache
 *
 * ImageLoader
 *
 * ImageRequest
 *
 * NetworkImageView
 *
 * ImageListener
 */
public class MainActivity extends Activity {
    private ImageView iv_img;
    private NetworkImageView netImageView;
    //百度logo
    String url="https://www.baidu.com/img/bdlogo.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化ImageView
        initView();

        /**
         * 使用ImageRequest加载图片
         */
//		//参数0,0表示显示默认尺寸图片
//		ImageRequest request=new ImageRequest(url, new Listener<Bitmap>(){
//
//			//图片请求成功的回调方法
//			@Override
//			public void onResponse(Bitmap arg0) {
//				//请求成功后将图片显示到ImageView
//				iv_img.setImageBitmap(arg0);
//			}
//			
//		}, 0, 0, Config.RGB_565, new Response.ErrorListener() {
//
//			//图片请求失败的回调方法
//			@Override
//			public void onErrorResponse(VolleyError arg0) {
//				//请求失败后给一个默认图片
//				iv_img.setBackgroundResource(R.drawable.ic_launcher);
//			}
//		});	
//		//添加队列
//		MyApplication.getHttpQueues().add(request);

        /**
         * 使用ImageLoader加载图片
         */
        //图片加载对象
        ImageLoader loader=new ImageLoader(MyApplication.getHttpQueues(), new BitmapCache());
        //监听图片加载
        ImageListener listener=ImageLoader.getImageListener(iv_img, R.drawable.ic_launcher, R.drawable.ic_launcher);
        //加载，获取图片
        loader.get(url, listener);

        /**
         * 使用NetworkImageView加载图片
         */
        //图片加载对象
        ImageLoader netLoader=new ImageLoader(MyApplication.getHttpQueues(), new BitmapCache());
        //设置默认显示图片
        netImageView.setDefaultImageResId(R.drawable.ic_launcher);
        //设置加载失败显示的图片
        netImageView.setErrorImageResId(R.drawable.ic_launcher);
        //加载图片
        netImageView.setImageUrl(url, netLoader);
    }

    //获取ImageView、NetworkImageView对象
    private void initView() {
        iv_img=(ImageView) this.findViewById(R.id.iv_img);
        netImageView=(NetworkImageView) this.findViewById(R.id.networkImageView);
    }

}
