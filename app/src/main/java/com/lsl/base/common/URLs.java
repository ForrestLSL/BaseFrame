package com.lsl.base.common;

/**
 * Created by Forrest
 * on 2017/7/13 10:32
 */

public class URLs {

    public static final String HOST = "192.168.10.212:8092";

    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";

    private final static String URL_SPLITTER = "/";
    private final static String URL_UNDERLINE = "_";

    private final static String URL_API_HOST = HTTP + HOST + URL_SPLITTER;

    public final static String GET_CONTRACT = URL_API_HOST + "";
    public final static String GET_HQ_ACTIVITY_LIST = URL_API_HOST + "mobile/newsNotice/activity";

}
