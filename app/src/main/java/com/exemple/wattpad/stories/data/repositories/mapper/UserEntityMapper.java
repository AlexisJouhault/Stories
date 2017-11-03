package com.exemple.wattpad.stories.data.repositories.mapper;

import com.exemple.wattpad.stories.data.repositories.entities.UserEntity;
import com.exemple.wattpad.stories.domain.models.User;

/**
 * Created by Alexis on 02/11/2017.
 *
 */

public class UserEntityMapper {

    public User transform(UserEntity userEntity) {
        User user = new User();

        if (userEntity != null) {
            user.setName(userEntity.name);
            user.setAvatarUrl(userEntity.avatarUrl);
            user.setFullName(userEntity.fullName);
            user.setValid(true);
        } else {
            user.setValid(false);
        }

        return user;
    }
}
