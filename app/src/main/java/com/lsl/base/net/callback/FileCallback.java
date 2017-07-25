package com.lsl.base.net.callback;

import com.lsl.base.net.convert.FileConvert;

import java.io.File;

import okhttp3.Response;

/**
 * Created by Forrest
 * on 2017/7/25 09:28
 */

public abstract class FileCallback extends AbsCallback<File> {

    private FileConvert convert;

    public FileCallback() {
        this(null);
    }

    public FileCallback(String destFileName) {
        this(null, destFileName);
    }

    public FileCallback(String destFileDir, String destFileName) {
        convert = new FileConvert(destFileDir, destFileName);
        convert.setCallback(this);
    }

    @Override
    public File convertSuccess(Response response) throws Exception {
        File file = convert.convertSuccess(response);
        response.close();
        return file;

    }
}
