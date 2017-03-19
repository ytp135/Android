package com.ytp.mymvp.Mo;

import android.content.Context;

/**
 * Created by Administrator on 2017年03月10日:19:56.
 */

public interface IUserLoginModel {
    void login(Context context, String username, String password, OnUserLoginListener listener);
}
