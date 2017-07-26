package com.lsl.base.imgloader;

import com.bumptech.glide.load.model.Headers;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Forrest
 * on 2017/7/25 21:48
 */

public class GlideHeaders implements Headers {

    public LinkedHashMap<String, String> glideHeaders;

    public GlideHeaders(){
        init();
    }

    private void init() {
        glideHeaders = new LinkedHashMap<>();
    }

    public void put(String key, String value){
        if (key != null && value != null) {
            glideHeaders.put(key, value);
        }
    }

    @Override
    public Map<String, String> getHeaders() {
        return glideHeaders;
    }
}
