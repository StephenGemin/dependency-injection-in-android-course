package com.techyourchance.journeytodependencyinjection.common.di;


import com.techyourchance.journeytodependencyinjection.common.di.component.PresentationComponent;
import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;
import com.techyourchance.journeytodependencyinjection.screens.questionslist.QuestionsListActivity;

/**
 * Created by Stephen Gemin on 9/11/2019
 */
public class Injector {

    private final PresentationComponent mPresentationComponent;

    public Injector(PresentationComponent presentationComponent) {
        mPresentationComponent = presentationComponent;
    }

    public void inject(Object client) {
        if (client instanceof QuestionsListActivity) {
            injectQuestionsListActivity((QuestionsListActivity) client);
        }
        else if (client instanceof QuestionDetailsActivity) {
            injectQuestionDetailsActvity((QuestionDetailsActivity) client);
        }
        else {
            throw new RuntimeException("invalid client: " + client);
        }
    }

    private void injectQuestionsListActivity(QuestionsListActivity client) {
        client.mViewMvcFactory = mPresentationComponent.getViewMvcFactory();
        client.mDialogsManager = mPresentationComponent.getDialogsManager();
        client.mFetchQuestionsListUseCase = mPresentationComponent.getFetchQuestionsListUseCase();
    }
    private void injectQuestionDetailsActvity (QuestionDetailsActivity client) {
        client.mViewMvcFactory = mPresentationComponent.getViewMvcFactory();
        client.mDialogsManager = mPresentationComponent.getDialogsManager();
        client.mfetchQuestionDetailsUseCase = mPresentationComponent.getFetchQuestionDetailsUseCase();
    }

}
