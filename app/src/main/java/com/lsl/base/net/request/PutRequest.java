package com.lsl.base.net.request;

import com.lsl.base.common.BLog;
import com.lsl.base.net.model.HttpHeaders;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Forrest
 * on 2017/7/25 09:58
 */

public abstract class PutRequest extends BaseRequest<PutRequest> {
    public PutRequest(String url) {
        super(url);
        method = "PUT";
    }

    @Override
    public Request generateRequest(RequestBody requestBody) {
        try {
            mHeaders.put(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, String.valueOf(requestBody.contentLength()));
        } catch (IOException e) {
            BLog.e(e);
        }
        return null;
    }
}
