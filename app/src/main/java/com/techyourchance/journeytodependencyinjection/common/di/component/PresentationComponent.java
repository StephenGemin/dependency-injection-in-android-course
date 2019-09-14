package com.techyourchance.journeytodependencyinjection.common.di.component;

import com.techyourchance.journeytodependencyinjection.common.di.module.PresentationModule;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ViewMvcFactory;

import dagger.Component;

/**
 * Created by Stephen Gemin on 9/13/2019
 */
@Component(modules = PresentationModule.class)
public interface PresentationComponent {
    FetchQuestionsListUseCase getFetchQuestionsListUseCase();
    FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase();
    ViewMvcFactory getViewMvcFactory();
    DialogsManager getDialogsManager();
}
