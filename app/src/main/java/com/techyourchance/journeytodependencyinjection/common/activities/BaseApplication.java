package com.techyourchance.journeytodependencyinjection.common.activities;

import android.app.Application;

import com.techyourchance.journeytodependencyinjection.common.di.component.ApplicationComponent;
import com.techyourchance.journeytodependencyinjection.common.di.component.DaggerApplicationComponent;

/**
 * Created by Stephen Gemin on 9/9/2019
 */
public class BaseApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }


}
