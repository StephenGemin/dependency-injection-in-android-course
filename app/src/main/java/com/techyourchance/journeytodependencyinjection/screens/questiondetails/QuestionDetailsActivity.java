 package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

 import android.content.Context;
 import android.content.Intent;
 import android.os.Bundle;
 import android.view.LayoutInflater;

 import com.techyourchance.journeytodependencyinjection.common.activities.BaseActivity;
 import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
 import com.techyourchance.journeytodependencyinjection.questions.QuestionWithBody;
 import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
 import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.ServerErrorDialogFragment;

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
     private FetchQuestionDetailsUseCase mfetchQuestionDetailsUseCase;
     private DialogsManager mDialogsManager;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         mViewMvc = new QuestionDetailsViewMvcImpl(LayoutInflater.from(this), null);
         setContentView(mViewMvc.getRootView());
         mfetchQuestionDetailsUseCase = getCompositionRoot().getFetchQuestionDetailsUseCase();

         //noinspection ConstantConditions
         mQuestionId = getIntent().getExtras().getString(EXTRA_QUESTION_ID);
         mDialogsManager = getCompositionRoot().getDialogsManager();
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
     public void onFetchQuestionDetailsSucceeded(QuestionWithBody question) {
        mViewMvc.bindQuestion(question);
     }

     @Override
     public void onFetchOfQuestionDetailsFailed() {
         mDialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(),"");
     }
 }
