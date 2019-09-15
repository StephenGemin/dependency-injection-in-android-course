package com.techyourchance.journeytodependencyinjection.common;

import android.app.Service;
import android.support.annotation.UiThread;

import com.techyourchance.journeytodependencyinjection.common.activities.BaseApplication;
import com.techyourchance.journeytodependencyinjection.common.di.component.ApplicationComponent;
import com.techyourchance.journeytodependencyinjection.common.di.component.PresentationComponent;
import com.techyourchance.journeytodependencyinjection.common.di.component.ServiceComponent;
import com.techyourchance.journeytodependencyinjection.common.di.module.PresentationModule;
import com.techyourchance.journeytodependencyinjection.common.di.module.ServiceModule;

/**
 * Created by Stephen Gemin on 9/14/2019
 */
public abstract class BaseService extends Service {
    private boolean mIsServiceComponentUsed;

    /**
     * Has activity lifecycle
     */
    @UiThread
    protected ServiceComponent getServiceComponent() {
        if (mIsServiceComponentUsed) {
            throw new RuntimeException("There is no need to use injector more than once");
        }
        mIsServiceComponentUsed = true;
        return getApplicationComponent()
                .newServiceComponent(new ServiceModule(this));
    }

    /**
     * Has application lifecycle
     */
    private ApplicationComponent getApplicationComponent() {
        return ((BaseApplication) getApplication()).getApplicationComponent();
    }
}
