package com.techyourchance.journeytodependencyinjection.common.di.component;

import com.techyourchance.journeytodependencyinjection.common.di.module.ApplicationModule;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Stephen Gemin on 9/13/2019
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    FetchQuestionsListUseCase getFetchQuestionsListUseCase();
    FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase();
}
