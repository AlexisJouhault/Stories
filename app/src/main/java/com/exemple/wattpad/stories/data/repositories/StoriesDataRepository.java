package com.exemple.wattpad.stories.data.repositories;

import com.exemple.wattpad.stories.data.api.WattpadApiService;
import com.exemple.wattpad.stories.data.repositories.mapper.StoryEntityMapper;
import com.exemple.wattpad.stories.domain.StoriesRepository;
import com.exemple.wattpad.stories.domain.models.Story;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by Alexis on 02/11/2017.
 *
 */

public class StoriesDataRepository implements StoriesRepository {

    private final WattpadApiService mWattpadApiService;
    private final StoryEntityMapper mStoryEntityMapper;

    private final int defaultLimit = 30;
    private final int defaultOffset = 0;
    private final String defaultFields = "stories(id,title,cover,user)";

    public StoriesDataRepository() {
        mWattpadApiService = WattpadApiService.Builder.build();
        mStoryEntityMapper = new StoryEntityMapper();
    }

    public Observable<List<Story>> getStories() {
        return mWattpadApiService.getStories(defaultOffset, defaultLimit, defaultFields).map(mStoryEntityMapper::transform);
    }
}
