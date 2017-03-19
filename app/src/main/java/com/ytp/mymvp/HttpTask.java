package com.ytp.mymvp;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.ytp.mymvp.Mo.LoginBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017年03月10日:20:41.
 */

public class HttpTask<T> {
    private static HttpTask instance;

    private HttpTask() {
    }

    public static HttpTask getInstance() {
        if (instance == null) {
            synchronized (HttpTask.class) {
                if (instance == null) {
                    instance = new HttpTask();
                }
            }
        }
        return instance;
    }
    public void getPostURL(Context context, final OnBeanRequestListener listener, final String username, final String password){
        String url = "http://www.oschina.net/action/apiv2/login_validate";
        postUrl(url,context,listener,username,password);
    }
    private void postUrl(String url, Context context, final OnBeanRequestListener listener, final String username, final String password){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onErrorListener();
            }
        };

        Response.Listener<LoginBean> listenerr = new Response.Listener<LoginBean>() {
            @Override
            public void onResponse(LoginBean response) {
                listener.onResponseListener(response);
            }
        };
        BeanRequest request = new BeanRequest(Request.Method.POST, url, LoginBean.class, listenerr, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("username", username);
                map.put("pwd", password);
                map.put("keep_login", "1");
                return map;
            }
        };
        requestQueue.add(request);
    }
    public interface OnBeanRequestListener{
        void onErrorListener();
        void onResponseListener(LoginBean response);
    }



}
