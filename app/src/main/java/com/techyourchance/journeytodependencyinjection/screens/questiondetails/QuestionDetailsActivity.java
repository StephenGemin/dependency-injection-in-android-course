 package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

 import android.content.Context;
 import android.content.Intent;
 import android.os.Bundle;

 import com.techyourchance.journeytodependencyinjection.common.activities.BaseActivity;
 import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
 import com.techyourchance.journeytodependencyinjection.questions.QuestionDetails;
 import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
 import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.ServerErrorDialogFragment;
 import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ViewMvcFactory;

 public class QuestionDetailsActivity extends BaseActivity implements
         QuestionDetailsViewMvc.Listener, FetchQuestionDetailsUseCase.Listener {

     public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

     public static void start(Context context, String questionId) {
         Intent intent = new Intent(context, QuestionDetailsActivity.class);
         intent.putExtra(EXTRA_QUESTION_ID, questionId);
         context.startActivity(intent);
     }

     private String mQuestionId;
     private QuestionDetailsViewMvc mViewMvc;
     public FetchQuestionDetailsUseCase mfetchQuestionDetailsUseCase;
     public DialogsManager mDialogsManager;
     public ViewMvcFactory mViewMvcFactory;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         getInjector().inject(this);

         mViewMvc = mViewMvcFactory.newInstance(
                 QuestionDetailsViewMvc.class,null);

         setContentView(mViewMvc.getRootView());

         //noinspection ConstantConditions
         mQuestionId = getIntent().getExtras().getString(EXTRA_QUESTION_ID);
     }

     @Override
     protected void onStart() {
         super.onStart();
         mViewMvc.registerListener(this);
         mfetchQuestionDetailsUseCase.registerListener(this);
         mfetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(mQuestionId);
     }

     @Override
     protected void onStop() {
         super.onStop();
         mViewMvc.unregisterListener(this);
         mfetchQuestionDetailsUseCase.unregisterListener(this);
     }

     @Override
     public void onFetchQuestionDetailsSucceeded(QuestionDetails question) {
        mViewMvc.bindQuestion(question);
     }

     @Override
     public void onFetchOfQuestionDetailsFailed() {
         mDialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(),"");
     }
 }
