package com.techyourchance.journeytodependencyinjection.questions;

import android.support.annotation.Nullable;

import com.techyourchance.journeytodependencyinjection.networking.SingleQuestionResponseSchema;
import com.techyourchance.journeytodependencyinjection.networking.StackoverflowApi;
import com.techyourchance.journeytodependencyinjection.screens.common.BaseObservable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Stephen Gemin on 9/7/2019
 */
public class FetchQuestionDetailsUseCase extends BaseObservable<FetchQuestionDetailsUseCase.Listener> {

    public interface Listener {
        void onFetchQuestionDetailsSucceeded(QuestionWithBody question);
        void onFetchOfQuestionDetailsFailed();
    }

    private StackoverflowApi mstackoverflowApi;
    @Nullable Call<SingleQuestionResponseSchema> mCall;

    public FetchQuestionDetailsUseCase(StackoverflowApi stackoverflowApi) {
        mstackoverflowApi = stackoverflowApi;
    }

    public void fetchQuestionDetailsAndNotify(String questionId) {

        cancelCurrentFetchIfActive();
        mCall = mstackoverflowApi.questionDetails(questionId);
        mCall.enqueue(new Callback<SingleQuestionResponseSchema>() {
            @Override
            public void onResponse(Call<SingleQuestionResponseSchema> call, Response<SingleQuestionResponseSchema> response) {
                if (response.isSuccessful()) {
                    notifySucceeded(response.body().getQuestion());
                } else {
                    notifyFailed();
                }
            }

            @Override
            public void onFailure(Call<SingleQuestionResponseSchema> call, Throwable t) {
                notifyFailed();
            }
        });
    }

    private void notifySucceeded(QuestionWithBody question) {
        for (Listener listener: getListeners()) {
            listener.onFetchQuestionDetailsSucceeded(question);
        }
    }

    private void notifyFailed() {
        for (Listener listener: getListeners()) {
            listener.onFetchOfQuestionDetailsFailed();
        }

    }

    private void cancelCurrentFetchIfActive() {
        if (mCall != null && !mCall.isCanceled() && !mCall.isExecuted()) {
            mCall.cancel();
        }
    }
}
