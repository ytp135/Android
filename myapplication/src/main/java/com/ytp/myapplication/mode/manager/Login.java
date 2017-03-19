package com.ytp.myapplication.mode.manager;

import android.util.Log;

import com.ytp.myapplication.BuildConfig;
import com.ytp.myapplication.mode.bean.LoginBean;
import com.ytp.myapplication.mode.bean.User;
import com.ytp.myapplication.mode.network.LoginRetrofit;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017年03月18日:15:23.
 */

public class Login {
    public void login(String username, String pwd, String keep_login) {
        Observable<LoginBean> observable = LoginRetrofit
                .getInstance()
                .getApi()
                .login("login_validate", new User(username, pwd, keep_login));

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<LoginBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                if (BuildConfig.DEBUG) Log.d("Login", "d:" + d);
            }

            @Override
            public void onNext(LoginBean value) {
                EventBus.getDefault().post(value);
            }

            @Override
            public void onError(Throwable e) {
                if (BuildConfig.DEBUG) Log.d("Login", "e:" + e);
            }

            @Override
            public void onComplete() {
                if (BuildConfig.DEBUG) Log.d("Login", "-----------");
            }
        });


//        call.enqueue(new Callback<LoginBean>() {
//            @Override
//            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
//                LoginBean bean = response.body();
//                EventBus.getDefault().post(bean);
//            }
//            @Override
//            public void onFailure(Call<LoginBean> call, Throwable t) {
//                if (BuildConfig.DEBUG) Log.d("Login", t.getLocalizedMessage());
//            }
//        });
    }
}
