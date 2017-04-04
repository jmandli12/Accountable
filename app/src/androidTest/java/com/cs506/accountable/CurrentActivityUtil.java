package com.cs506.accountable;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import java.util.Collection;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.runner.lifecycle.Stage.RESUMED;

/**
 * Created by Jonah on 4/3/2017.
 * pulled from a tutorial online to get the current activity
 *
 */

public final class CurrentActivityUtil {

    @Nullable
    public static Activity getCurrentActivity() {
        final Activity[] currentActivity = {null};
        getInstrumentation().runOnMainSync(() -> {
            Collection<Activity> resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
            if (resumedActivities.iterator().hasNext()) {
                currentActivity[0] = resumedActivities.iterator().next();
            }
        });

        return currentActivity[0];
    }
}
