package com.yayanheryanto.larismotor.model;

import android.app.Activity;

public class MenuModel {

    private int image;
    private String title;
    private Class<? extends Activity> mContext;

    public MenuModel(int image, String title, final Class<? extends Activity> ActivityToOpen) {
        this.image = image;
        this.title = title;
        this.mContext = ActivityToOpen ;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class<? extends Activity> getmContext() {
        return mContext;
    }

    public void setmContext(Class<? extends Activity> mContext) {
        this.mContext = mContext;
    }
}
