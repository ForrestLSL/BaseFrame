package com.lsl.base.net.callback;

import com.lsl.base.net.request.BaseRequest;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Forrest
 * on 2017/12/17 15:28
 */

public class CallbackEntity {
    private Response response;
    private Call call;
    private BaseRequest baseRequest;

    public CallbackEntity(Response response, Call call, BaseRequest baseRequest) {
        this.response = response;
        this.call = call;
        this.baseRequest = baseRequest;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    public BaseRequest getBaseRequest() {
        return baseRequest;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }
}
