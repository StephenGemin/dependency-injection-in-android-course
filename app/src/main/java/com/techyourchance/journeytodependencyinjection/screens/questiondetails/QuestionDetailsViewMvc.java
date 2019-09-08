package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

import com.techyourchance.journeytodependencyinjection.questions.QuestionWithBody;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ObservableViewMvc;

/**
 * Created by Stephen Gemin on 9/7/2019
 */
public interface QuestionDetailsViewMvc extends ObservableViewMvc<QuestionDetailsViewMvc.Listener> {

    interface Listener {
        // currently no user actions
    }

    void bindQuestion(QuestionWithBody question);
}
