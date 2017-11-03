package com.exemple.wattpad.stories.domain.usecases;

import com.exemple.wattpad.stories.data.repositories.StoriesDataRepository;
import com.exemple.wattpad.stories.domain.StoriesRepository;
import com.exemple.wattpad.stories.domain.models.Story;
import com.exemple.wattpad.stories.presentation.presenters.MainPresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alexis on 02/11/2017.
 *
 */

public class GetMainStoriesList {

    private final StoriesRepository mStoriesRepository;

    public GetMainStoriesList() {
        mStoriesRepository = new StoriesDataRepository();
    }

    public void execute(DefaultObserver<List<Story>> observer) {
        mStoriesRepository.getStories().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
}
