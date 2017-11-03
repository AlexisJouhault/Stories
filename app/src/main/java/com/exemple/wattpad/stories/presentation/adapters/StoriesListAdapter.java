package com.exemple.wattpad.stories.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.exemple.wattpad.stories.R;
import com.exemple.wattpad.stories.domain.models.Story;
import com.exemple.wattpad.stories.presentation.views.viewholders.StoryViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexis on 02/11/2017.
 *
 */

public class StoriesListAdapter extends RecyclerView.Adapter<StoryViewHolder> {

    private final Context mContext;
    private List<Story> mItems = new ArrayList<>();

    public StoriesListAdapter(Context context) {
        mContext = context;
    }

    public void setItems(List<Story> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StoryViewHolder(LayoutInflater.from(mContext).inflate(R.layout.listelement_story, parent, false));
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
