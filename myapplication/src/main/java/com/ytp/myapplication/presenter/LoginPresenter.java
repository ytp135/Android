package com.ytp.myapplication.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ytp.myapplication.R;
import com.ytp.myapplication.mode.manager.Login;
import com.ytp.myapplication.mode.bean.LoginBean;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.ytp.myapplication.R.id.login;

/**
 * Created by Administrator on 2017年03月18日:15:39.
 */

public class LoginPresenter extends BasePresenter {
    private Button         mLogin;
    private EditText       etUsername;
    private EditText       etPassword;
    private ProgressDialog mProgressDialog;
    private View           mView;
    private Context        mContext;
    private String         mName;
    private String         mPwd;
    private Login          mLogin1;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LoginBean bean) {
        if (bean != null) {
            bindView(bean);
        }
    }

    @Override
    public void initView(Context context) {
        mContext = context;
        mView = LayoutInflater.from(context).inflate(R.layout.activity_main, null);
        mLogin = (Button) mView.findViewById(login);
        etUsername = (EditText) mView.findViewById(R.id.et_username);
        etPassword = (EditText) mView.findViewById(R.id.et_password);
        mProgressDialog = new ProgressDialog(context);

    }

    // 初始化数据
    @Override
    public void initData() {
        mName = etUsername.getText().toString().trim();
        mPwd = etPassword.getText().toString().trim();
        mLogin1 = new Login();
    }

    // 初始化事件
    @Override
    public void initEvent() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLogin1.login(mName, mPwd, 1 + "");
            }
        });
    }

    private void bindView(LoginBean bean) {
        switch (bean.code) {
            case 0:
                Toast.makeText(mContext, bean.message, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(mContext, "！", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public View getView() {
        return mView;
    }
}
