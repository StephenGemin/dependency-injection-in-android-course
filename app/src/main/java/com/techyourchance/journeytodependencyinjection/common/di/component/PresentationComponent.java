package com.techyourchance.journeytodependencyinjection.common.di.component;

import com.techyourchance.journeytodependencyinjection.common.di.module.PresentationModule;
import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;
import com.techyourchance.journeytodependencyinjection.screens.questionslist.QuestionsListActivity;

import dagger.Component;

/**
 * Created by Stephen Gemin on 9/13/2019
 */
@Component(modules = PresentationModule.class)
public interface PresentationComponent {
    void inject(QuestionsListActivity questionsListActivity);
    void inject(QuestionDetailsActivity questionDetailsActivity);
}
