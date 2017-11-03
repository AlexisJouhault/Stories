package com.exemple.wattpad.stories.presentation.views.itemdecorations;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.exemple.wattpad.stories.R;

/**
 * Created by Alexis on 02/11/2017.
 *
 */

public class VerticalDividerDecoration extends DividerItemDecoration {

    public VerticalDividerDecoration(Context context) {
        super(context, VERTICAL);
        setDrawable(ContextCompat.getDrawable(context, R.drawable.divider_stories_list));
    }
}
