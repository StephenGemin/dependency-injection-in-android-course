 package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

 import android.content.Context;
 import android.content.Intent;
 import android.os.Bundle;
 import android.support.v4.app.FragmentManager;
 import android.support.v7.app.AppCompatActivity;
 import android.view.LayoutInflater;

 import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
 import com.techyourchance.journeytodependencyinjection.questions.QuestionWithBody;
 import com.techyourchance.journeytodependencyinjection.screens.common.ServerErrorDialogFragment;

 public class QuestionDetailsActivity extends AppCompatActivity implements
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



     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         mViewMvc = new QuestionDetailsViewMvcImpl(LayoutInflater.from(this), null);
         setContentView(mViewMvc.getRootView());

        mfetchQuestionDetailsUseCase = new FetchQuestionDetailsUseCase();

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
     public void onFetchQuestionDetailsSucceeded(QuestionWithBody question) {
        mViewMvc.bindQuestion(question);
     }

     @Override
     public void onFetchOfQuestionDetailsFailed() {
         FragmentManager fragmentManager = getSupportFragmentManager();
         fragmentManager.beginTransaction()
                 .add(ServerErrorDialogFragment.newInstance(), null)
                 .commitAllowingStateLoss();
     }
 }
