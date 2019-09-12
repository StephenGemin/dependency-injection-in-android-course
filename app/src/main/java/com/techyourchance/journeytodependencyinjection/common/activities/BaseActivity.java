package com.techyourchance.journeytodependencyinjection.common.activities;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.techyourchance.journeytodependencyinjection.MyApplication;
import com.techyourchance.journeytodependencyinjection.common.di.CompositionRoot;
import com.techyourchance.journeytodependencyinjection.common.di.Injector;
import com.techyourchance.journeytodependencyinjection.common.di.PresentationCompositionRoot;

/**
 * Created by Stephen Gemin on 9/9/2019
 */
public class BaseActivity extends AppCompatActivity {

    private boolean mIsInjectorUsed;

    /**
     * Has activity lifecycle
     */
    @UiThread
    protected Injector getInjector() {
        if (mIsInjectorUsed) {
            throw new RuntimeException("There is no need to use injector more than once");
        }
        mIsInjectorUsed = true;
        return new Injector(getCompositionRoot());
    }

    private PresentationCompositionRoot getCompositionRoot() {
        return new PresentationCompositionRoot(getAppCompositionRoot(), this);
    }

    /**
     * Has application lifecycle
     */
    private CompositionRoot getAppCompositionRoot() {
        return ((MyApplication) getApplication()).getCompositionRoot();
    }
}
