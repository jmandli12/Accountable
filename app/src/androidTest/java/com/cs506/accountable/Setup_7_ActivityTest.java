package com.cs506.accountable;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by Jonah on 4/4/2017.
 */
public class Setup_7_ActivityTest {
    @Rule
    public ActivityTestRule<Setup_7_Activity> mActivityRule = new ActivityTestRule<>(
            Setup_7_Activity.class);

    //This test crashes our app, but when you attempt to do the same activity
    //on your own it does not.
    @Test
    public void moveNext() throws Exception {
        Activity currentActivity = CurrentActivityUtil.getCurrentActivity();
        onView(withId(R.id.button10)).perform(click());
        Activity expectedActivity = CurrentActivityUtil.getCurrentActivity();
        assertNotEquals(currentActivity, expectedActivity);
    }

    @Test
    public void savingsHelp() throws Exception {

    }

}