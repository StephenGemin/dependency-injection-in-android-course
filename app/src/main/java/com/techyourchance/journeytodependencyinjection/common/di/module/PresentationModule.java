package com.techyourchance.journeytodependencyinjection.common.di.module;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.techyourchance.journeytodependencyinjection.common.di.component.ApplicationComponent;
import com.techyourchance.journeytodependencyinjection.networking.StackoverflowApi;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;
import com.techyourchance.journeytodependencyinjection.screens.common.ImageLoader;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ViewMvcFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Gemin on 9/13/2019
 */
@Module
public class PresentationModule {

    private final FragmentActivity mActivity;

    public PresentationModule (FragmentActivity fragmentActivity) {
        mActivity = fragmentActivity;
    }

    @Provides
    DialogsManager getDialogsManager(FragmentManager fragmentManager) {
        return new DialogsManager(fragmentManager);
    }

    @Provides
    FragmentManager getFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }

    @Provides
    LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mActivity);
    }

    @Provides
    Activity getActivity() {
        return mActivity;
    }

    @Provides
    Context context(Activity activity) {
        return activity;
    }

    @Provides
    FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase(StackoverflowApi stackoverflowApi) {
        return new FetchQuestionDetailsUseCase(stackoverflowApi);
    }

    @Provides
    ImageLoader getImageLoader(Activity activity) {
        return new ImageLoader(activity);
    }

    @Provides
    ViewMvcFactory getViewMvcFactory(LayoutInflater layoutInflater, ImageLoader imageLoader) {
        return new ViewMvcFactory(layoutInflater, imageLoader);
    }

}
