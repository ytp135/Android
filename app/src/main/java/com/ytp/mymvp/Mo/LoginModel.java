package com.ytp.mymvp.Mo;

import android.content.Context;

import com.ytp.mymvp.HttpTask;

/**
 * Created by Administrator on 2017年03月10日:19:53.
 */

public class LoginModel implements IUserLoginModel {


    @Override
    public void login(Context mContext, final String username, final String password, final OnUserLoginListener listener) {
        HttpTask.getInstance().getPostURL(mContext, new HttpTask.OnBeanRequestListener() {
            @Override
            public void onErrorListener() {

            }

            @Override
            public void onResponseListener(LoginBean response) {
                switch (response.code) {
                    case 0:
                        listener.LoginFailed();
                        break;
                    case 1:
                        listener.LoginSuccess(response);
                        break;
                }
            }
        }, username, password);
    }
}
