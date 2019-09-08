 package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

 import android.content.Context;
 import android.content.Intent;
 import android.os.Bundle;
 import android.support.v4.app.FragmentManager;
 import android.support.v7.app.AppCompatActivity;
 import android.text.Html;
 import android.view.LayoutInflater;
 import android.widget.TextView;

 import com.techyourchance.journeytodependencyinjection.Constants;
 import com.techyourchance.journeytodependencyinjection.R;
 import com.techyourchance.journeytodependencyinjection.screens.common.ServerErrorDialogFragment;
 import com.techyourchance.journeytodependencyinjection.networking.SingleQuestionResponseSchema;
 import com.techyourchance.journeytodependencyinjection.networking.StackoverflowApi;

 import retrofit2.Call;
 import retrofit2.Callback;
 import retrofit2.Response;
 import retrofit2.Retrofit;
 import retrofit2.converter.gson.GsonConverterFactory;

 public class QuestionDetailsActivity extends AppCompatActivity implements
         Callback<SingleQuestionResponseSchema>, QuestionDetailsViewMvc.Listener {

     public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

     public static void start(Context context, String questionId) {
         Intent intent = new Intent(context, QuestionDetailsActivity.class);
         intent.putExtra(EXTRA_QUESTION_ID, questionId);
         context.startActivity(intent);
     }

     private StackoverflowApi mStackoverflowApi;

     private Call<SingleQuestionResponseSchema> mCall;

     private String mQuestionId;

     private QuestionDetailsViewMvc mViewMvc;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         mViewMvc = new QuestionDetailsViewMvcImpl(LayoutInflater.from(this), null);
         setContentView(mViewMvc.getRootView());

         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl(Constants.BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();

         mStackoverflowApi = retrofit.create(StackoverflowApi.class);

         //noinspection ConstantConditions
         mQuestionId = getIntent().getExtras().getString(EXTRA_QUESTION_ID);

     }

     @Override
     protected void onStart() {
         super.onStart();
         mViewMvc.registerListener(this);
         mCall = mStackoverflowApi.questionDetails(mQuestionId);
         mCall.enqueue(this);
     }

     @Override
     protected void onStop() {
         super.onStop();
         mViewMvc.unregisterListener(this);
         if (mCall != null) {
             mCall.cancel();
         }
     }

     @Override
     public void onResponse(Call<SingleQuestionResponseSchema> call, Response<SingleQuestionResponseSchema> response) {
         SingleQuestionResponseSchema questionResponseSchema;
         if (response.isSuccessful() && (questionResponseSchema = response.body()) != null) {
             mViewMvc.bindQuestion(questionResponseSchema.getQuestion());
         }else {
             onFailure(call, null);
         }
     }

     @Override
     public void onFailure(Call<SingleQuestionResponseSchema> call, Throwable t) {
         FragmentManager fragmentManager = getSupportFragmentManager();
         fragmentManager.beginTransaction()
                 .add(ServerErrorDialogFragment.newInstance(), null)
                 .commitAllowingStateLoss();
     }

 }
