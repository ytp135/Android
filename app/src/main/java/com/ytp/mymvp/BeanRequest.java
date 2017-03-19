package com.ytp.mymvp;

import com.alibaba.fastjson.JSON;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017年03月10日:19:05.
 */

public class BeanRequest<T> extends Request<T> {

    private final Class<T>             mClazz;
    private final Response.Listener<T> mListener;

    public BeanRequest(int method, String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mClazz = clazz;
        mListener = listener;
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        T t ;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            t = JSON.parseObject(parsed, mClazz);
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
        return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
