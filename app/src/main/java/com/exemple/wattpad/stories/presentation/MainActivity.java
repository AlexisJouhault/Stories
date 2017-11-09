package com.exemple.wattpad.stories.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;

import com.exemple.wattpad.stories.R;
import com.exemple.wattpad.stories.domain.models.Story;
import com.exemple.wattpad.stories.presentation.adapters.StoriesListAdapter;
import com.exemple.wattpad.stories.presentation.presenters.MainPresenter;
import com.exemple.wattpad.stories.presentation.views.MainView;
import com.exemple.wattpad.stories.presentation.views.itemdecorations.VerticalDividerDecoration;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import com.jakewharton.rxbinding2.support.v7.widget.SearchViewQueryTextEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final int LIST_SPAN = 1;
    private static final int GRID_SPAN = 3;
    private final long DEBOUNCE = 800;
    private MainPresenter mMainPresenter = new MainPresenter();
    private StoriesListAdapter mStoriesListAdapter;

    @BindView(R.id.stories_recyclerview)
    RecyclerView mStoriesRecyclerView;

    @BindView(R.id.progress)
    View mSpinner;

    private SearchView mSearchView;
    private boolean gridEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mStoriesListAdapter = new StoriesListAdapter(this);
        mStoriesRecyclerView.setAdapter(mStoriesListAdapter);
        setUpLayoutManager(getStoriesSpan());
    }

    private void setUpLayoutManager(int span) {
        mStoriesRecyclerView.setLayoutManager(new GridLayoutManager(this, span, LinearLayoutManager.VERTICAL, false));
    }

    private int getStoriesSpan() {
        return gridEnabled ? GRID_SPAN : LIST_SPAN;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        RxSearchView.queryTextChangeEvents(mSearchView)
                .debounce(DEBOUNCE, TimeUnit.MILLISECONDS)
                .map(event -> mMainPresenter.handleQueryEvent(event))
                .observeOn(AndroidSchedulers.mainThread()) // switching to main thread for ui changes
                .doOnNext(this::showStories)
                .subscribe();
        mSearchView.setOnCloseListener(() -> mMainPresenter.restoreResults());
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMainPresenter.setView(this);
        mMainPresenter.initialize();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMainPresenter.destroy();
    }

    @Override
    public void showLoading() {
        mSpinner.setVisibility(VISIBLE);
    }

    @Override
    public void hideLoading() {
        mSpinner.setVisibility(GONE);
    }

    @Override
    public void showStories(List<Story> stories) {
        mStoriesListAdapter.setItems(stories);
    }

    @Override
    public void noContentError() {
        displayError("No content to display");
    }

    @Override
    public void defaultError() {
        displayError("Something went wrong");
    }

    @OnClick(R.id.switch_layout_button)
    public void onLayoutSwitch() {
        gridEnabled = !gridEnabled;
        setUpLayoutManager(getStoriesSpan());
    }

    private void displayError(String message) {
        // TODO: 02/11/2017 add error view
    }
}
