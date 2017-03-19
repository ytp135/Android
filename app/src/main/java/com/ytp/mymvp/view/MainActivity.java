package com.ytp.mymvp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ytp.mymvp.Mo.LoginBean;
import com.ytp.mymvp.R;
import com.ytp.mymvp.presenter.UserLoginPrenter;

public class MainActivity extends AppCompatActivity implements com.ytp.mymvp.view.IUserLoginView {

    private Button           mLogin;
    private EditText         etUsername;
    private EditText         etPassword;
    private ProgressDialog   mProgressDialog;
    private UserLoginPrenter mPrenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLogin = (Button) findViewById(R.id.login);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        mProgressDialog = new ProgressDialog(this);
        mPrenter = new UserLoginPrenter(this);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPrenter.login(MainActivity.this);
            }
        });
    }

    @Override
    public String getUsername() {
        return etUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString().trim();
    }

    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.hide();
    }

    @Override
    public void jump2Main(LoginBean user) {
        Toast.makeText(this, "登录成功，跳转主页", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginError() {
        Toast.makeText(this, "用户名或者密码错误！", Toast.LENGTH_SHORT).show();
    }
}
