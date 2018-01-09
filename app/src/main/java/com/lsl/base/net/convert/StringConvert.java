package com.lsl.base.net.convert;

import com.lsl.base.net.request.BaseRequest;

import okhttp3.Response;

/**
 * Created by Forrest
 * on 2017/7/13 11:28
 */

public class StringConvert implements Converter<String> {

    public static StringConvert create() {
        return ConvertHolder.convert;
    }

    private static class ConvertHolder {
        private static StringConvert convert = new StringConvert();
    }

    @Override
    public String convertSuccess(Response value, BaseRequest request) throws Exception {
        return value.body().string();
    }
}