package com.techyourchance.journeytodependencyinjection.screens.common.fragments;

import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;

import com.techyourchance.journeytodependencyinjection.common.base.BaseApplication;
import com.techyourchance.journeytodependencyinjection.common.di.component.ApplicationComponent;
import com.techyourchance.journeytodependencyinjection.common.di.component.PresentationComponent;
import com.techyourchance.journeytodependencyinjection.common.di.module.PresentationModule;

/**
 * Created by Stephen Gemin on 9/14/2019
 */
public class BaseFragment extends Fragment {
    private boolean mIsInjectorUsed;

    /**
     * Has fragment lifecycle
     */
    @UiThread
    protected PresentationComponent getPresentationComponent() {
        if (mIsInjectorUsed) {
            throw new RuntimeException("There is no need to use injector more than once");
        }
        mIsInjectorUsed = true;
        return getApplicationComponent()
                .newPresentationComponent(new PresentationModule(getActivity()));
    }

    /**
     * Has application lifecycle
     */
    private ApplicationComponent getApplicationComponent() {
        return ((BaseApplication) getActivity().getApplication()).getApplicationComponent();
    }
}
