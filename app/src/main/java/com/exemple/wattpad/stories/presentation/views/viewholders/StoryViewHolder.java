package com.exemple.wattpad.stories.presentation.views.viewholders;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.exemple.wattpad.stories.R;
import com.exemple.wattpad.stories.domain.models.Story;
import com.exemple.wattpad.stories.domain.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Alexis on 02/11/2017.
 *
 */

public class StoryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.story_cover)
    AppCompatImageView mStoryCoverAppCompatImageView;

    @BindView(R.id.story_title)
    AppCompatTextView mStoryTitleAppCompatTextView;

    @BindView(R.id.story_author_avatar)
    CircleImageView mAuthorAvatarAppCompatImageView;

    @BindView(R.id.story_author_name)
    AppCompatTextView mAuthorNameAppCompatTextView;

    public StoryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Story story) {
        if (story.isDisplayable()) {
            setImage(mStoryCoverAppCompatImageView, story.getCoverUrl());
            mStoryTitleAppCompatTextView.setText(story.getTitle());
            User author = story.getUser();
            if (author != null && author.isValid()) {
                setImage(mAuthorAvatarAppCompatImageView, author.getAvatarUrl());
                mAuthorNameAppCompatTextView.setText(author.getName());
            }
        }
    }

    private void setImage(ImageView imageView, String url) {
        Glide.with(itemView.getContext()).load(url).into(imageView);
    }
}
