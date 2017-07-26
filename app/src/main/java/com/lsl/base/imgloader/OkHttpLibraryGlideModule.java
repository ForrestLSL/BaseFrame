package com.lsl.base.imgloader;

import android.content.Context;

import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.LibraryGlideModule;

import java.io.InputStream;

/**
 * A {@link com.bumptech.glide.module.GlideModule} implementation to replace Glide's default
 * {@link java.net.HttpURLConnection} based {@link com.bumptech.glide.load.model.ModelLoader}
 * with an OkHttp based {@link com.bumptech.glide.load.model.ModelLoader}.
 *
 * <p> If you're using gradle, you can include this module simply by depending on the aar, the
 * module will be merged in by manifest merger. For other build systems or for more more
 * information, see {@link com.bumptech.glide.module.GlideModule}. </p>
 *
 * @deprecated Replaced by {@link } for Applications that use Glide's
 * annotations.
 */
@GlideModule
public class OkHttpLibraryGlideModule extends LibraryGlideModule {

    @Override
    public void registerComponents(Context context, Registry registry) {
//        super.registerComponents(context, registry);
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }
}
