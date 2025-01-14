 package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

 import android.content.Context;
 import android.content.Intent;
 import android.os.Bundle;

 import com.techyourchance.journeytodependencyinjection.common.base.BaseActivity;
 import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
 import com.techyourchance.journeytodependencyinjection.questions.QuestionDetails;
 import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
 import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.ServerErrorDialogFragment;
 import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ViewMvcFactory;

 import javax.inject.Inject;

 public class QuestionDetailsActivity extends BaseActivity implements
         QuestionDetailsViewMvc.Listener, FetchQuestionDetailsUseCase.Listener {

     public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

     public static void start(Context context, String questionId) {
         Intent intent = new Intent(context, QuestionDetailsActivity.class);
         intent.putExtra(EXTRA_QUESTION_ID, questionId);
         context.startActivity(intent);
     }

     @Inject FetchQuestionDetailsUseCase mFetchQuestionDetailsUseCase;
     @Inject DialogsManager mDialogsManager;
     @Inject ViewMvcFactory mViewMvcFactory;

     private String mQuestionId;

     private QuestionDetailsViewMvc mViewMvc;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         getPresentationComponent().inject(this);

         mViewMvc = mViewMvcFactory.newInstance(QuestionDetailsViewMvc.class, null);

         setContentView(mViewMvc.getRootView());

         //noinspection ConstantConditions
         mQuestionId = getIntent().getExtras().getString(EXTRA_QUESTION_ID);
     }

     @Override
     protected void onStart() {
         super.onStart();
         mViewMvc.registerListener(this);
         mFetchQuestionDetailsUseCase.registerListener(this);

         mFetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(mQuestionId);
     }

     @Override
     protected void onStop() {
         super.onStop();
         mViewMvc.unregisterListener(this);
         mFetchQuestionDetailsUseCase.unregisterListener(this);
     }

     @Override
     public void onFetchOfQuestionDetailsSucceeded(QuestionDetails question) {
         mViewMvc.bindQuestion(question);
     }

     @Override
     public void onFetchOfQuestionDetailsFailed() {
         mDialogsManager.showDialogWithId(ServerErrorDialogFragment.newInstance(), "");
     }
 }
