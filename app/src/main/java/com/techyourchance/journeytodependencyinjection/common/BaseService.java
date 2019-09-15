package com.techyourchance.journeytodependencyinjection.common;

import android.app.Service;
import android.support.annotation.UiThread;

import com.techyourchance.journeytodependencyinjection.common.base.BaseApplication;
import com.techyourchance.journeytodependencyinjection.common.di.component.ApplicationComponent;
import com.techyourchance.journeytodependencyinjection.common.di.component.ServiceComponent;
import com.techyourchance.journeytodependencyinjection.common.di.module.ServiceModule;

public abstract class BaseService extends Service {

    private boolean mIsServiceComponentUsed;

    @UiThread
    protected ServiceComponent getServiceComponent() {
        if (mIsServiceComponentUsed) {
            throw new RuntimeException("there is no reason to perform injection more than once");
        }

        mIsServiceComponentUsed = true;

        return getApplicationComponent().newServiceComponent(new ServiceModule(this));
    }

    private ApplicationComponent getApplicationComponent() {
        return ((BaseApplication) getApplication()).getApplicationComponent();
    }
}
