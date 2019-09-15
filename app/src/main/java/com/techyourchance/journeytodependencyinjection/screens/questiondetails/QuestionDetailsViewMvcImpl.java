package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.techyourchance.journeytodependencyinjection.R;
import com.techyourchance.journeytodependencyinjection.questions.QuestionDetails;
import com.techyourchance.journeytodependencyinjection.screens.common.ImageLoader;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.BaseViewMvc;

/**
 * Created by Stephen Gemin on 9/7/2019
 */
public class QuestionDetailsViewMvcImpl extends BaseViewMvc<QuestionDetailsViewMvc.Listener>
        implements QuestionDetailsViewMvc {

    private final TextView mTxtQuestionBody;
    private final ImageLoader mImageLoader;
    private final TextView mTxtUserDisplayName;
    private final ImageView mImgUserAvatar;

    public QuestionDetailsViewMvcImpl(LayoutInflater inflater, ViewGroup container, ImageLoader imageLoader) {
        setRootView(inflater.inflate(R.layout.layout_question_details, container, false));
        mTxtQuestionBody = findViewById(R.id.txt_question_body);
        mImageLoader = imageLoader;
        mTxtUserDisplayName = findViewById(R.id.txt_user_display_name);
        mImgUserAvatar = findViewById(R.id.img_user_avatar);
    }

    @Override
    public void bindQuestion(QuestionDetails question) {
        String questionBody = question.getBody();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mTxtQuestionBody.setText(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY));
        } else {
            mTxtQuestionBody.setText(Html.fromHtml(questionBody));
        }
        mTxtUserDisplayName.setText(question.getmUserDisplayName());
        mImageLoader.loadImage(question.getmUserAvatarUrl(), mImgUserAvatar);
    }
}
