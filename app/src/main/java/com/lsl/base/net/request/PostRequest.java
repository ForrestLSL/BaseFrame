package com.lsl.base.net.request;

import com.lsl.base.common.BLog;
import com.lsl.base.net.model.HttpHeaders;
import com.lsl.base.net.utils.HttpUtils;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Forrest
 * on 2017/7/25 09:35
 */

public class PostRequest extends BaseBodyRequest<PostRequest> {
    public PostRequest(String url) {
        super(url);
        method = "POST";
    }

    @Override
    public Request generateRequest(RequestBody requestBody) {
        try {
            mHeaders.put(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, String.valueOf(requestBody.contentLength()));
        }catch (Exception e){
            BLog.e(e);
        }
        Request.Builder requestBuilder = HttpUtils.appendHeaders(mHeaders);
        return requestBuilder.patch(requestBody).url(url).tag(tag).build();
    }
}
