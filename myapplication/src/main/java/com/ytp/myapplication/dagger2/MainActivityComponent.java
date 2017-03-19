package com.ytp.myapplication.dagger2;

import com.ytp.myapplication.view.MainActivity;

import dagger.Component;

//@componet 类似于等号的关系 把注入的目标和 提供的的对象关联起来提供一个注入方法 进行关联
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void in(MainActivity activity);
}