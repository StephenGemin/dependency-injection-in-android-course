package com.techyourchance.journeytodependencyinjection.questions;

import android.support.annotation.Nullable;

import com.techyourchance.journeytodependencyinjection.networking.QuestionsListResponseSchema;
import com.techyourchance.journeytodependencyinjection.networking.StackoverflowApi;
import com.techyourchance.journeytodependencyinjection.screens.common.BaseObservable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Stephen Gemin on 9/7/2019
 */
public class FetchQuestionsListUseCase extends BaseObservable<FetchQuestionsListUseCase.Listener> {

    public interface Listener {
        void onFetchOfQuestionsSucceeded(List<Question> questions);
        void onFetchOfQuestionsFailed();
    }

    private StackoverflowApi mStackoverflowApi;
    @Nullable Call<QuestionsListResponseSchema> mCall;

    public FetchQuestionsListUseCase(StackoverflowApi stackoverflowApi) {
        mStackoverflowApi = stackoverflowApi;
    }

    public void fetchLastActiveQuestionsAndNotify(int numOfQuestions) {
        cancelCurrentFetchIfActive();

        mCall = mStackoverflowApi.lastActiveQuestions(numOfQuestions);
        mCall.enqueue(new Callback<QuestionsListResponseSchema>() {
            @Override
            public void onResponse(Call<QuestionsListResponseSchema> call, Response<QuestionsListResponseSchema> response) {
                if (response.isSuccessful()) {
                    notifySucceeded(questionsFromQuestionsSchema(response.body().getQuestions()));
                } else {
                    notifyFailed();
                }
            }

            @Override
            public void onFailure(Call<QuestionsListResponseSchema> call, Throwable t) {
                notifyFailed();
            }
        });
    }

    private List<Question> questionsFromQuestionsSchema(List<QuestionSchema> questionSchemas) {
        List<Question> questions = new ArrayList<>(questionSchemas.size());
        for (QuestionSchema questionSchema : questionSchemas) {
            questions.add(new Question(questionSchema.getId(), questionSchema.getTitle()));
        }
        return questions;
    }

    private void notifyFailed() {
        for (Listener listener: getListeners()) {
            listener.onFetchOfQuestionsFailed();
        }
    }

    private void notifySucceeded(List<Question> questions) {
        List<Question> unmodifiableQuestions = Collections.unmodifiableList(questions);
        for (Listener listener: getListeners()) {
            listener.onFetchOfQuestionsSucceeded(unmodifiableQuestions);
        }
    }

    private void cancelCurrentFetchIfActive() {
        if (mCall != null && !mCall.isCanceled() && !mCall.isExecuted()) {
            mCall.cancel();
        }
    }
}
