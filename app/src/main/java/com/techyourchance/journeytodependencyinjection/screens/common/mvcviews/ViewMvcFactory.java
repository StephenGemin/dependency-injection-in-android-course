package com.techyourchance.journeytodependencyinjection.screens.common.mvcviews;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsViewMvc;
import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsViewMvcImpl;
import com.techyourchance.journeytodependencyinjection.screens.questionslist.QuestionsListViewMvc;
import com.techyourchance.journeytodependencyinjection.screens.questionslist.QuestionsListViewMvcImpl;

/**
 * Created by Stephen Gemin on 9/10/2019
 */
public class ViewMvcFactory {
    private final LayoutInflater mlayoutInflater;

    public ViewMvcFactory(LayoutInflater layoutInflater) {
        mlayoutInflater = layoutInflater;
    }

    /**
     * Instantiate a new implementation of MVC view. The returned instance will be casted to MVC view
     * type inferred by java's automatic type inference.
     * @param mvcViewClass the class of the required MVC view
     * @param container this container will be used as MVC view's parent. See {@link LayoutInflater#inflate(int, ViewGroup)}
     * @param <T> the type of the required MVC view
     * @return new instance of MVC view
     */
    public <T extends ViewMvc> T newInstance(Class<T> mvcViewClass, @Nullable ViewGroup container) {

        ViewMvc viewMvc;

        if (mvcViewClass == QuestionsListViewMvc.class) {
            viewMvc = new QuestionsListViewMvcImpl(mlayoutInflater, container);
        }
        else if (mvcViewClass == QuestionDetailsViewMvc.class) {
            viewMvc = new QuestionDetailsViewMvcImpl(mlayoutInflater, container);
        }
        else {
            throw new IllegalArgumentException("unsupported MVC view class " + mvcViewClass);
        }
        // no inspection unchecked
        return (T) viewMvc;
    }
}
