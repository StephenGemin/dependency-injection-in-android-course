package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techyourchance.journeytodependencyinjection.R;
import com.techyourchance.journeytodependencyinjection.questions.QuestionWithBody;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.BaseViewMvc;

/**
 * Created by Stephen Gemin on 9/7/2019
 */
public class QuestionDetailsViewMvcImpl extends BaseViewMvc<QuestionDetailsViewMvc.Listener>
        implements QuestionDetailsViewMvc {

    private final TextView mTxtQuestionBody;

    public QuestionDetailsViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        setRootView(inflater.inflate(R.layout.layout_question_details, container, false));
        mTxtQuestionBody = findViewById(R.id.txt_question_body);
    }

    @Override
    public void bindQuestion(QuestionWithBody question) {
        String questionBody = question.getBody();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mTxtQuestionBody.setText(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY));
        }
        mTxtQuestionBody.setText(Html.fromHtml(questionBody));
    }
}
