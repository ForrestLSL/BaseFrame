package com.lsl.base.imgloader;

import android.content.Context;

/**
 * Created by Forrest
 * on 2017/7/25 21:45
 */

public abstract class ImgLoadContext<R> {

    protected Context context;         //上下文

    protected String url = null;       //请求地址

    protected GlideHeaders headers;    //请求Header




    protected ImgLoadContext<R> header(String key, String value){
        if (headers == null){
            headers =new GlideHeaders();
        }
        headers.put(key, value);
        return this;
    }

    public Context getContext(){
        return context;
    }

    public ImgLoadContext<R> context(Context context){
        this.context = context;
        return this;
    }

    public ImgLoadContext<R> url(String url){
        this.url = url;
        return this;
    }

    public String getUrl(){
        return url;
    }

    public void load() {

    }
}
