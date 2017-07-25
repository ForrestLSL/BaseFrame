package com.lsl.base.net.callback;

import android.graphics.Bitmap;
import com.lsl.base.net.convert.BitmapConvert;
import okhttp3.Response;

/**
 * Created by Forrest
 * on 2017/7/25 09:26
 */

public abstract class BitmapCallback extends AbsCallback<Bitmap> {

    @Override
    public Bitmap convertSuccess(Response response) throws Exception {
        Bitmap bitmap = BitmapConvert.create().convertSuccess(response);
        response.close();
        return bitmap;
    }
}
