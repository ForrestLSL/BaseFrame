package com.lsl.base.net.request;

import com.lsl.base.net.utils.HttpUtils;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Forrest
 * on 2017/7/25 10:16
 */

public class HeadRequest extends BaseRequest<HeadRequest>{

    public HeadRequest(String url) {
        super(url);
        method = "HEAD";
    }

    @Override
    public RequestBody generateRequestBody() {
        return null;
    }

    @Override
    public Request generateRequest(RequestBody requestBody) {
        Request.Builder builder = HttpUtils.appendHeaders(mHeaders);
        url = HttpUtils.createUrlFromParams(baseUrl, mParams.urlParamsMap);
        return builder.head().url(url).tag(tag).build();
    }
}
