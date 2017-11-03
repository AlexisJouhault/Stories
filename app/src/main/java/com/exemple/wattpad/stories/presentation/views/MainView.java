package com.exemple.wattpad.stories.presentation.views;

import com.exemple.wattpad.stories.domain.models.Story;

import java.util.List;

/**
 * Created by Alexis on 02/11/2017.
 *
 */

public interface MainView extends LoadDataView {
    void showStories(List<Story> stories);

    void noContentError();

    void defaultError();
}
