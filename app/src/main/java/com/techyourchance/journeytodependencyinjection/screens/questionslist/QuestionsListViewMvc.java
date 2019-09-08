package com.techyourchance.journeytodependencyinjection.screens.questionslist;

import com.techyourchance.journeytodependencyinjection.questions.Question;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ObservableViewMvc;

import java.util.List;

/**
 * Created by Stephen Gemin on 9/7/2019
 */
public interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener> {

    interface Listener {
        void onQuestionClicked(Question question);
    }

    void bindQuestions(List<Question> questions);
}
