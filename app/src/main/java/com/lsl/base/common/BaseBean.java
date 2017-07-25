package com.lsl.base.common;

import java.io.Serializable;

/**
 * Created by Forrest
 * on 2017/7/13 11:59
 */

public class BaseBean<T> implements Serializable{
    private int code;
    private boolean success;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
