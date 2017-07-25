package com.lsl.base.net.convert;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import okhttp3.Response;

/**
 * Created by Forrest
 * on 2017/7/25 08:45
 */

public class BitmapConvert implements Converter<Bitmap>{

    public static BitmapConvert create(){
        return BitmapConvert.ConvertHolder.convert;
    }

    public static class ConvertHolder{
        private static BitmapConvert convert = new BitmapConvert();
    }

    @Override
    public Bitmap convertSuccess(Response response) throws Exception {
        return BitmapFactory.decodeStream(response.body().byteStream());
    }
}
