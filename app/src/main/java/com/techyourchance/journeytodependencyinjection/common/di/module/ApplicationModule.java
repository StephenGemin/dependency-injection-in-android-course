package com.techyourchance.journeytodependencyinjection.common.di.module;

import android.app.Application;

import com.techyourchance.journeytodependencyinjection.networking.StackoverflowApi;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Gemin on 9/13/2019
 */
@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    FetchQuestionsListUseCase getFetchQuestionsListUseCase(StackoverflowApi stackoverflowApi) {
        return new FetchQuestionsListUseCase(stackoverflowApi);
    }
}
