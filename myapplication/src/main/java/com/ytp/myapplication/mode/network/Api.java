package com.ytp.myapplication.mode.network;


import com.ytp.myapplication.mode.bean.LoginBean;
import com.ytp.myapplication.mode.bean.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Leon on 2017/2/22.
 */

public interface Api {
/*    @GET("top250")
    Call<MovieBean> listTop250(@Query("start") int start, @Query("count") int count);*/

    @POST("{login_validate}")
    Observable<LoginBean> login(@Path("login_validate") String login_validate, @Body User user);
}
