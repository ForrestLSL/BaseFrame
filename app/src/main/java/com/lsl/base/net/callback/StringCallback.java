package com.lsl.base.net.callback;

import com.lsl.base.net.convert.StringConvert;
import com.lsl.base.net.request.BaseRequest;

import okhttp3.Response;

/**
 * Created by Forrest
 * on 2017/7/13 10:53
 */

public abstract class StringCallback extends AbsCallback<String> {

    @Override
    public String convertSuccess(Response response, BaseRequest request) throws Exception {
        String s = StringConvert.create().convertSuccess(response, request);
        response.close();
        return s;
    }

}