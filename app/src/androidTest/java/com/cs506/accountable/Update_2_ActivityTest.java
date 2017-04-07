package com.cs506.accountable;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.AdapterView;

import org.junit.Rule;
import org.junit.Test;

/**
 * Created by ChensongHu on 4/4/17.
 */

public class Update_2_ActivityTest {
    @Rule
    public ActivityTestRule<Update_2_Activity> mActivityRule = new ActivityTestRule<>(
            Update_2_Activity.class);

    @Test
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
    }
}
