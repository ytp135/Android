package com.ytp.myapplication.presenter;

import android.content.Context;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017年03月18日:17:22.
 */

public abstract class BasePresenter {
    public BasePresenter() {
        EventBus.getDefault().register(this);
    }
    public void removeEventbus() {
    	EventBus.getDefault().unregister(this);
    }

    public abstract void initView(Context context);

    public abstract View getView();

    public void initData() {
    }

    public void initEvent() {

    }
}
