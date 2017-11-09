package com.exemple.wattpad.stories.presentation.presenters;

import android.text.TextUtils;

import com.exemple.wattpad.stories.domain.models.Story;
import com.exemple.wattpad.stories.domain.usecases.GetMainStoriesList;
import com.exemple.wattpad.stories.presentation.internal.ErrorUtils;
import com.exemple.wattpad.stories.presentation.views.MainView;
import com.jakewharton.rxbinding2.support.v7.widget.SearchViewQueryTextEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DefaultObserver;

/**
 * Created by Alexis on 02/11/2017.
 *
 */

public class MainPresenter {

    private final GetMainStoriesList mGetMainStoriesList;
    private MainView mMainView;
    private List<Story> mResults;

    public MainPresenter() {
        mGetMainStoriesList = new GetMainStoriesList();
        mResults = new ArrayList<>();
    }

    public void setView(MainView view) {
        this.mMainView = view;
    }

    public void initialize() {
        if (mMainView != null) {
            mMainView.showLoading();
            mGetMainStoriesList.execute(new GetStoriesListObserver());
        } else {
            ErrorUtils.log("Missing View in MainPresenter");
        }
    }

    void handleStories(List<Story> stories) {
        if (stories == null || stories.isEmpty()) {
            handleError(ErrorUtils.NO_CONTENT);
        } else {
            mResults = stories;
            mMainView.showStories(stories);
        }
    }

    void handleError(String message) {
        if (ErrorUtils.NO_CONTENT.equals(message)) {
            mMainView.noContentError();
        } else {
            mMainView.defaultError();
        }
    }

    public void destroy() {
        mMainView = null;
    }

    List<Story> search(String query) {
        List<Story> queryResults = new ArrayList<>();

        for (Story story : mResults) {
            if (story.getTitle() != null && story.getTitle().toLowerCase().contains(query.toLowerCase())) {
                queryResults.add(story);
            }
        }

        return queryResults;
    }

    public List<Story> handleQueryEvent(SearchViewQueryTextEvent event) {
        if (event.queryText().length() > 0) {
            return search(String.valueOf(event.queryText()));
        } else {
            return mResults == null ? new ArrayList<>() : mResults;
        }
    }

    public boolean restoreResults() {
        mMainView.showStories(mResults);
        return true;
    }

    class GetStoriesListObserver extends DefaultObserver<List<Story>> {

        @Override
        public void onNext(List<Story> stories) {
            handleStories(stories);
        }

        @Override
        public void onError(Throwable e) {
            handleError(e.getMessage());
        }

        @Override
        public void onComplete() {
            mMainView.hideLoading();
        }

    }
}
