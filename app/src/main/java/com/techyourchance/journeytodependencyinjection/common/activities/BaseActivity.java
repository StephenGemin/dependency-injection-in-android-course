package com.techyourchance.journeytodependencyinjection.common.activities;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;

import com.techyourchance.journeytodependencyinjection.common.di.component.ApplicationComponent;
import com.techyourchance.journeytodependencyinjection.common.di.component.DaggerApplicationComponent;
import com.techyourchance.journeytodependencyinjection.common.di.component.DaggerPresentationComponent;
import com.techyourchance.journeytodependencyinjection.common.di.component.PresentationComponent;
import com.techyourchance.journeytodependencyinjection.common.di.module.ApplicationModule;
import com.techyourchance.journeytodependencyinjection.common.di.module.PresentationModule;

/**
 * Created by Stephen Gemin on 9/9/2019
 */
public class BaseActivity extends AppCompatActivity {

    private boolean mIsInjectorUsed;

    /**
     * Has activity lifecycle
     */
    @UiThread
    protected PresentationComponent getPresentationComponent() {
        if (mIsInjectorUsed) {
            throw new RuntimeException("There is no need to use injector more than once");
        }
        mIsInjectorUsed = true;
        return DaggerPresentationComponent.builder()
                .presentationModule(new PresentationModule(this))
                .applicationComponent(getApplicationComponent())
                .build();
    }

    /**
     * Has application lifecycle
     */
    private ApplicationComponent getApplicationComponent() {
        return ((BaseApplication) getApplication()).getApplicationComponent();
    }
}
