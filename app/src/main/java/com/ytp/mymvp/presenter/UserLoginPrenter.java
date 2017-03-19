package com.ytp.mymvp.presenter;

import android.content.Context;

import com.ytp.mymvp.Mo.LoginBean;
import com.ytp.mymvp.Mo.LoginModel;
import com.ytp.mymvp.Mo.IUserLoginModel;
import com.ytp.mymvp.Mo.OnUserLoginListener;
import com.ytp.mymvp.view.IUserLoginView;

/**
 * Created by Administrator on 2017年03月10日:20:26.
 */

public class UserLoginPrenter implements OnUserLoginListener {
    private IUserLoginModel model;//Model层
    private IUserLoginView  view;//View层

    public UserLoginPrenter( IUserLoginView view) {
        this.model = new LoginModel() ;
        this.view = view;
    }

    public void login(Context context){
        view.showProgress();
        model.login(context,getUsername(),getPassword(),this);
    }
    private String getUsername(){
        return view.getUsername();
    }

    private String getPassword(){
        return view.getPassword();
    }

    @Override
    public void LoginSuccess(LoginBean bean) {
        view.jump2Main(bean);
        view.hideProgress();
    }

    @Override
    public void LoginFailed() {
        view.showLoginError();
        view.hideProgress();
    }
}
