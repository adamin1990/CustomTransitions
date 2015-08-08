package com.adamin90.adamlee.customtransitions;

import android.graphics.Bitmap;

/**
 * Created by adamlee on 2015/8/8.
 */
public class BitmapBean {
    int resourceId;
    String description;
    Bitmap thumbnail;

    public BitmapBean(int resourceId, String description, Bitmap thumbnail) {
        this.resourceId = resourceId;
        this.description = description;
        this.thumbnail = thumbnail;
    }
}
