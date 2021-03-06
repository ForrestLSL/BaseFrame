package com.lsl.base.net.request;

import com.lsl.base.net.utils.HttpUtils;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Forrest
 * on 2017/7/13 10:28
 */

public class GetRequest extends BaseRequest<GetRequest> {
    public GetRequest(String url) {
        super(url);
        method = "GET";
    }

    @Override
    public RequestBody generateRequestBody() {
        return null;
    }

    @Override
    public Request generateRequest(RequestBody requestBody) {
        Request.Builder requestBuilder = HttpUtils.appendHeaders(mHeaders);
        url = HttpUtils.createUrlFromParams(baseUrl, mParams.urlParamsMap);
        return requestBuilder.get().url(url).tag(tag).build();
    }
}
