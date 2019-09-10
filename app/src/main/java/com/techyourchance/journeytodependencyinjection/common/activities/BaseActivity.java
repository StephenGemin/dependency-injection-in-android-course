package com.techyourchance.journeytodependencyinjection.common.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.techyourchance.journeytodependencyinjection.MyApplication;
import com.techyourchance.journeytodependencyinjection.common.di.CompositionRoot;
import com.techyourchance.journeytodependencyinjection.common.di.PresentationCompositionRoot;

/**
 * Created by Stephen Gemin on 9/9/2019
 */
public class BaseActivity extends AppCompatActivity {

    private PresentationCompositionRoot mPresentationCompositionRoot;

    /**
     * Has activity lifecycle
     */
    protected PresentationCompositionRoot getCompositionRoot() {
        if (mPresentationCompositionRoot == null) {
            mPresentationCompositionRoot = new PresentationCompositionRoot(
                    getAppCompositionRoot(),
                    getSupportFragmentManager(),
                    LayoutInflater.from(this));
        }
        return mPresentationCompositionRoot;
    }

    /**
     * Has application lifecycle
     */
    private CompositionRoot getAppCompositionRoot() {
        return ((MyApplication) getApplication()).getCompositionRoot();
    }
}
