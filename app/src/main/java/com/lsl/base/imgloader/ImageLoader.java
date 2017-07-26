package com.lsl.base.imgloader;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.lsl.base.common.BLog;

/**
 * Created by Forrest
 * on 2017/7/20 15:01
 * 封装Glide的入口
 */

public class ImageLoader extends ImgLoadContext<Bitmap> {


    private int defaultImage;
    private Drawable errorImage;
    private ImageView imageView;
    private BitmapTransformation transformation;
    private boolean asGif;


    public ImageLoader (Context context){
        this.context = context;
    }

    public int getDefaultImage() {
        return defaultImage;
    }

    public ImageLoader defaultImage(int defaultImage) {
        this.defaultImage = defaultImage;
        return this;
    }

    public Drawable getErrorImage() {
        return errorImage;
    }

    public ImageLoader errorImage(Drawable errorImage) {
        this.errorImage = errorImage;
        return this;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public ImageLoader imageView(ImageView imageView) {
        this.imageView = imageView;
        return this;
    }

    public BitmapTransformation getTransformation() {
        return transformation;
    }

    public ImageLoader transformation(BitmapTransformation transformation) {
        this.transformation = transformation;
        return this;
    }

    public boolean isAsGif() {
        return asGif;
    }

    public ImageLoader asGif(boolean asGif) {
        this.asGif = asGif;
        return this;
    }

    @Override
    public ImageLoader url(String url) {
        super.url(url);
        return this;
    }

    @Override
    protected ImageLoader header(String key, String value) {
        super.header(key, value);
        return this;
    }

    @Override
    public void load() {
        super.load();
        if (TextUtils.isEmpty(url)){
            BLog.e("传入的url为空");
            return;
        }

        if (imageView == null){
            BLog.e("请设置ImageView");
            return;
        }

        GlideRequests request = GlideApp.with(context);
        //是否是GIF图
        if (asGif){
            request.asGif();
        }
        GlideRequest<Drawable> glideRequest = null;
        //添加Header
        if (headers != null && headers.getHeaders().size() > 0){
            glideRequest = request.load(new GlideUrl(url,headers));
        }else {
            glideRequest = request.load(url);
        }
        //默认显示图片
        if (defaultImage != -1) {
            glideRequest.placeholder(defaultImage);
        }
        //显示错误图片
        if (errorImage != null){
            glideRequest.error(errorImage);
        }
        //添加变换
        if (transformation != null){
            glideRequest.transform(transformation);
        }

        //加载View
        glideRequest.into(imageView);


    }
}
