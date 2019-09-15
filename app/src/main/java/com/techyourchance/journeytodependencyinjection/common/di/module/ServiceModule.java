package com.techyourchance.journeytodependencyinjection.common.di.module;

import android.app.Service;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Gemin on 9/14/2019
 */
@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    Service service() {
        return mService;
    }

    @Provides
    Context context(Service service) {
        return service;
    }
}
