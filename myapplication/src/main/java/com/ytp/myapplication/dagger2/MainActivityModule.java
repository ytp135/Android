package com.ytp.myapplication.dagger2;

import com.ytp.myapplication.presenter.BasePresenter;
import com.ytp.myapplication.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;
//一般@module 会和@provides配合使用 作为提供对象的方式
@Module
public class MainActivityModule {
    @Provides
    public BasePresenter BasePresenterPrivider() {
        return new LoginPresenter();
    }
}