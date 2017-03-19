package com.ytp.myapplication.view;

import com.ytp.myapplication.dagger2.DaggerMainActivityComponent;
import com.ytp.myapplication.dagger2.MainActivityModule;

public class MainActivity extends BaseActivity {
    @Override
    public void initPresenter() {
        DaggerMainActivityComponent.builder().mainActivityModule(new MainActivityModule()).build().in(this);
       // mPresenter = new LoginPresenter();
        mPresenter.initView(this);
        setContentView(mPresenter.getView());
    }
}
