package com.techyourchance.journeytodependencyinjection.common.activities;

import android.support.v7.app.AppCompatActivity;

import com.techyourchance.journeytodependencyinjection.MyApplication;
import com.techyourchance.journeytodependencyinjection.common.di.CompositionRoot;

/**
 * Created by Stephen Gemin on 9/9/2019
 */
public class BaseActivity extends AppCompatActivity {

    protected CompositionRoot getCompositionRoot() {
        return ((MyApplication) getApplication()).getCompositionRoot();
    }
}
