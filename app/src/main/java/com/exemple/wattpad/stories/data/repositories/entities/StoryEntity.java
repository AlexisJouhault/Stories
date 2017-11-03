package com.exemple.wattpad.stories.data.repositories.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexis on 02/11/2017.
 *
 */

public class StoryEntity {

    @SerializedName("id")
    public long id;

    @SerializedName("title")
    public String title;

    @SerializedName("user")
    public UserEntity user;

    @SerializedName("cover")
    public String coverUrl;
}
