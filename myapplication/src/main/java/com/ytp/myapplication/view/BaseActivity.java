package com.ytp.myapplication.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ytp.myapplication.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017年03月18日:17:30.
 */

public abstract class BaseActivity extends AppCompatActivity {
    //指定的注入目标 在执行注入后 即可直接获取对象
    @Inject
    protected BasePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        // 初始化数据
        initData();
        // 初始化事件
        initEvent();
    }

    // 初始化数据
    private void initData() {
        mPresenter.initData();
    }

    // 初始化事件
    private void initEvent() {
        mPresenter.initEvent();
    }


    public abstract void initPresenter();
    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.removeEventbus();
    }
}
