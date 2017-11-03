package com.exemple.wattpad.stories.data.repositories.mapper;

import com.exemple.wattpad.stories.data.repositories.entities.StoryEntity;
import com.exemple.wattpad.stories.data.repositories.responses.StoriesResponse;
import com.exemple.wattpad.stories.domain.models.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexis on 02/11/2017.
 *
 */

public class StoryEntityMapper {

    private final UserEntityMapper mUserEntityMapper;

    public StoryEntityMapper() {
        mUserEntityMapper = new UserEntityMapper();
    }

    public List<Story> transform(StoriesResponse storiesResponse) {
        if (storiesResponse != null && storiesResponse.getStoryEntities() != null) {
            List<StoryEntity> storyEntities = storiesResponse.getStoryEntities();
            return transform(storyEntities);
        }
        return new ArrayList<>();
    }

    private List<Story> transform(List<StoryEntity> storyEntities) {
        List<Story> stories = new ArrayList<>();

        for (StoryEntity storyEntity : storyEntities) {
            stories.add(transform(storyEntity));
        }

        return stories;
    }

    private Story transform(StoryEntity storyEntity) {
        Story story = new Story();

        if (storyEntity != null) {
            story.setId(storyEntity.id);
            story.setCoverUrl(storyEntity.coverUrl);
            story.setTitle(storyEntity.title);
            story.setUser(mUserEntityMapper.transform(storyEntity.user));
            story.setDisplayable(true);
        } else {
            story.setDisplayable(false);
        }

        return story;
    }
}
