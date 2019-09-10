package com.techyourchance.journeytodependencyinjection;

import android.app.Application;
import android.support.annotation.UiThread;

import com.techyourchance.journeytodependencyinjection.networking.StackoverflowApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Stephen Gemin on 9/9/2019
 */
public class MyApplication extends Application {

    private Retrofit mRetrofit;
    private StackoverflowApi mStackOverflowApi;

    @UiThread
    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
             mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return mRetrofit;
    }

    @UiThread
    public StackoverflowApi getStackOverflowApi() {
        if (mStackOverflowApi == null) {
            mStackOverflowApi = getRetrofit().create(StackoverflowApi.class);
        }
        return mStackOverflowApi;
    }
}
