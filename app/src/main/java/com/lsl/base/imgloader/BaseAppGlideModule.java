package com.lsl.base.imgloader;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

/**
 * Created by Forrest
 * on 2017/7/21 17:08
 * 使用了@GlideModule 就可以使用GlideApp类了
 * 该类是配置内存，缓存大小，也可以自定义
 */

@GlideModule
public class BaseAppGlideModule extends AppGlideModule {
    /**
     * Glide的通用配置
     * @param context
     * @param builder
     */
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        //内存配置
//        int memoryCacheSizeBytes = 1024 * 1024 * 20; // 20mb
//        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));

        //缓存配置
//        int diskCacheSizeBytes = 1024 * 1024 * 100; // 100 MB
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskCacheSizeBytes));

    }

    /**
     * If you’ve already migrated to the Glide v4 AppGlideModule and LibraryGlideModule, you can
     * disable manifest parsing entirely. Doing so can improve the initial startup time of Glide
     * and avoid some potential problems with trying to parse metadata. To disable manifest parsing,
     * override the isManifestParsingEnabled() method in your AppGlideModule implemenation:
     */
    @Override
    public boolean isManifestParsingEnabled() {
//        return super.isManifestParsingEnabled();
        return false;
    }
}
