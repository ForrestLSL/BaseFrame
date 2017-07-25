package com.lsl.base.net.request;

import com.lsl.base.common.BLog;
import com.lsl.base.net.model.HttpHeaders;
import com.lsl.base.net.utils.HttpUtils;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Forrest
 * on 2017/7/25 10:25
 */

public class OptionsRequest extends BaseBodyRequest<OptionsRequest> {

    public OptionsRequest(String url) {
        super(url);
        method = "OPTIONS";
    }

    @Override
    public Request generateRequest(RequestBody requestBody) {
        try {
            mHeaders.put(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, String.valueOf(requestBody.contentLength()));
        } catch (IOException e) {
            BLog.e(e);
        }
        Request.Builder requestBuilder = HttpUtils.appendHeaders(mHeaders);
        return requestBuilder.method("OPTIONS", requestBody).url(url).tag(tag).build();
    }
}
