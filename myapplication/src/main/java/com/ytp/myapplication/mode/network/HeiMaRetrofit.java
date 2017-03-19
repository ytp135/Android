package com.ytp.myapplication.mode.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Leon on 2017/2/15.
 */

public class HeiMaRetrofit {

    private static HeiMaRetrofit sHeiMaRetrofit;

    private Api mApi;

    private Gson mGson = new GsonBuilder().setLenient().create();//设置宽大处理畸形的json

    private static final int CACHE_MAX_SIZE = 5 * 1024 * 1024;

    private HeiMaRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.oschina.net/action/apiv2/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(mGson))//添加转换器
                .build();
        mApi = retrofit.create(Api.class);
    }

    public void init(Context context) {
        //缓存的路径
        String cacheDir = context.getCacheDir().getAbsolutePath() + "/response";
        File file = new File(cacheDir);
        if (!file.exists()) {
            file.mkdirs();//创建缓存目录
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(new Cache(file, CACHE_MAX_SIZE))
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.oschina.net/action/apiv2/login_validate")
             //   .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(mGson))//添加转换器
                .build();
        mApi = retrofit.create(Api.class);
    }

    public static HeiMaRetrofit getInstance() {
        if (sHeiMaRetrofit == null) {
            synchronized (HeiMaRetrofit.class) {
                if (sHeiMaRetrofit == null) {
                    sHeiMaRetrofit = new HeiMaRetrofit();
                }
            }
        }
        return sHeiMaRetrofit;
    }

    public Api getApi() {
        return mApi;
    }

    //在okhttp在回调网络之前我们可以拦截这个响应，做处理在返回回去
    private static Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());
            CacheControl control = new CacheControl.Builder().maxAge(5, TimeUnit.MINUTES).build();
            Response responseAfterModify = response.newBuilder().header("Cache-Control", control.toString()).build();
            return responseAfterModify;
        }
    };

}
