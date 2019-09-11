package com.techyourchance.journeytodependencyinjection.networking;

import com.google.gson.annotations.SerializedName;
import com.techyourchance.journeytodependencyinjection.questions.Question;
import com.techyourchance.journeytodependencyinjection.questions.QuestionSchema;

import java.util.List;

public class QuestionsListResponseSchema {

    @SerializedName("items")
    private final List<QuestionSchema> mQuestions;

    public QuestionsListResponseSchema(List<QuestionSchema> questions) {
        mQuestions = questions;
    }

    public List<QuestionSchema> getQuestions() {
        return mQuestions;
    }
}
