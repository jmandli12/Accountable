package com.cs506.accountable;

import android.support.design.widget.Snackbar;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;

/**
 * Created by ChensongHu on 4/4/17.
 */

public class Update_5_ActivityTest {
    @Rule
    public ActivityTestRule<Update_5_Activity> mActivityRule = new ActivityTestRule<>(
            Update_5_Activity.class);

    @Test
    public void updateBudget() throws Exception {
    }

    public void budgetHelp(View view) throws Exception {
        Snackbar.make(view, "NOT SURE WHAT GOES HERE YET \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();
    }
}
