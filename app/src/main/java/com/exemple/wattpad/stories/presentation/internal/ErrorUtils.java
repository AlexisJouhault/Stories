package com.exemple.wattpad.stories.presentation.internal;

import android.util.Log;

/**
 * Created by Alexis on 02/11/2017.
 *
 */

public final class ErrorUtils {

    public static final String NO_CONTENT = "noContent";

    private ErrorUtils() {
    }

    public static void log(String message) {
        Log.d(ErrorUtils.class.getSimpleName(), message);
    }
}
