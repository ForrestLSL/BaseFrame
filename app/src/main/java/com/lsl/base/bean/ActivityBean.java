package com.lsl.base.bean;

/**
 * Created by Forrest
 * on 2017/7/25 10:50
 */

public class ActivityBean {
    private String id;//活动ID
    private String title;//活动标题
    private String date;//活动日期
    private String url;//活动URL
    private String imagePath;//活动图片地址
    private String iconImagePath;//列表图标
    private ShareModel shareModel;//分享模型
    private boolean needLogin;//是否需要登陆
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public ShareModel getShareModel() {
        return shareModel;
    }
    public void setShareModel(ShareModel shareModel) {
        this.shareModel = shareModel;
    }
    public boolean isNeedLogin() {
        return needLogin;
    }
    public void setNeedLogin(boolean needLogin) {
        this.needLogin = needLogin;
    }
    public String getIconImagePath() {
        return iconImagePath;
    }
    public void setIconImagePath(String iconImagePath) {
        this.iconImagePath = iconImagePath;
    }
}
