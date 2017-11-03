package com.exemple.wattpad.stories.data.repositories.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexis on 02/11/2017.
 *
 */

public class UserEntity {

    @SerializedName("name")
    public String name;

    @SerializedName("avatar")
    public String avatarUrl;

    @SerializedName("fullname")
    public String fullName;

}
