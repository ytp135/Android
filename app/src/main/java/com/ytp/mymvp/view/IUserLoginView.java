package com.ytp.mymvp.view;

import com.ytp.mymvp.Mo.LoginBean;

/**
 * Created by Administrator on 2017年03月10日:20:13.
 */

public interface IUserLoginView {
    /**获取用户名*/
    String getUsername();
    /**获取密码*/
    String getPassword();

    /**显示加载进度*/
    void showProgress();
    /**隐藏加载进度*/
    void hideProgress();

    /**登录成功跳转主页*/
    void jump2Main(LoginBean user);
    /**登录失败提示用户*/
    void showLoginError();
}
