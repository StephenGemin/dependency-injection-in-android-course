package com.techyourchance.journeytodependencyinjection.screens.common;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Stephen Gemin on 9/10/2019
 */
public class ImageLoader {

    private final Activity mActivity;
    private final RequestOptions mDefaultRequestOptions;

    public ImageLoader(Activity activity) {
        mActivity = activity;
        mDefaultRequestOptions = new RequestOptions()
                .centerCrop();
    }

    public void loadImage(String url, ImageView target) {
        Glide.with(mActivity).load(url)
                .apply(mDefaultRequestOptions).into(target);
    }
}
