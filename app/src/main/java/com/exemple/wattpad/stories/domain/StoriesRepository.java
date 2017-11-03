package com.exemple.wattpad.stories.domain;

import com.exemple.wattpad.stories.domain.models.Story;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Alexis on 02/11/2017.
 *
 */

public interface StoriesRepository {
    Observable<List<Story>> getStories();
}
