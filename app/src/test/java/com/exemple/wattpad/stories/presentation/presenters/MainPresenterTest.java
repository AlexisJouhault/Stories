package com.exemple.wattpad.stories.presentation.presenters;

import com.exemple.wattpad.stories.domain.models.Story;
import com.exemple.wattpad.stories.presentation.views.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Alexis on 02/11/2017.
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    private static final String UNKNOWN_ERROR = "unknown";

    private MainPresenter mTested = new MainPresenter();

    @Mock
    MainView mMockedMainView;

    @Before
    public void setUp() throws Exception {
        mTested.setView(mMockedMainView);
    }

    @Test
    public void should_display_error_on_null_list() throws Exception {
        mTested.handleStories(null);

        verify(mMockedMainView, times(1)).noContentError();
    }

    @Test
    public void should_display_error_on_empty_list() throws Exception {
        mTested.handleStories(new ArrayList<>());

        verify(mMockedMainView, times(1)).noContentError();
    }

    @Test
    public void should_display_default_error_on_unknown_error_code() throws Exception {
        mTested.handleError(UNKNOWN_ERROR);

        verify(mMockedMainView, times(1)).defaultError();
    }

    @Test
    public void should_show_stories_on_valid_list() throws Exception {
        List<Story> stories = createValidStories();
        mTested.handleStories(stories);

        verify(mMockedMainView, times(1)).showStories(any());
    }

    @Test
    public void should_find_correct_stories() throws Exception {
        List<Story> stories = createValidStories();
        mTested.handleStories(stories);

        String query = "test";
        List<Story> queryResult = mTested.search(query);

        assertTrue(!queryResult.isEmpty());
    }

    @Test
    public void should_not_find_any_stories() throws Exception {
        List<Story> stories = createValidStories();
        mTested.handleStories(stories);

        String query = "Harry Potter";
        List<Story> queryResult = mTested.search(query);

        assertTrue(queryResult.isEmpty());
    }
    private List<Story> createValidStories() {
        List<Story> stories = new ArrayList<>();
        stories.add(createValidStory("test"));
        stories.add(createValidStory("Adventures of John Doe"));
        stories.add(createValidStory("No title"));
        return stories;
    }

    private Story createValidStory(String title) {
        Story story = new Story();
        story.setTitle(title);
        return story;
    }
}