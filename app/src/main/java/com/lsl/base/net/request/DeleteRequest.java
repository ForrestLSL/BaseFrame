package com.lsl.base.net.request;

import com.lsl.base.common.BLog;
import com.lsl.base.net.model.HttpHeaders;
import com.lsl.base.net.utils.HttpUtils;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Forrest
 * on 2017/7/25 10:20
 */

public class DeleteRequest extends BaseBodyRequest<DeleteRequest> {


    public DeleteRequest(String url) {
        super(url);
        method = "DELETE";
    }

    @Override
    public Request generateRequest(RequestBody requestBody) {
        try {
            mHeaders.put(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, String.valueOf(requestBody.contentLength()));
        } catch (IOException e) {
            BLog.e(e);
        }
        Request.Builder builder = HttpUtils.appendHeaders(mHeaders);
        return builder.delete().url(url).tag(tag).build();
    }
}
