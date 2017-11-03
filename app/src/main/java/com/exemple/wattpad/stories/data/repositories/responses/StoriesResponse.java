package com.exemple.wattpad.stories.data.repositories.responses;

import com.exemple.wattpad.stories.data.repositories.entities.StoryEntity;
import com.exemple.wattpad.stories.domain.models.Story;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alexis on 02/11/2017.
 *
 */

public class StoriesResponse {

    @SerializedName("stories")
    private List<StoryEntity> storyEntities;

    public List<StoryEntity> getStoryEntities() {
        return storyEntities;
    }

    public void setStoryEntities(List<StoryEntity> storyEntities) {
        this.storyEntities = storyEntities;
    }
}
