package com.cs506.accountable;

import android.app.Activity;
import android.support.test.espresso.DataInteraction;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Predicates.instanceOf;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Created by Jonah on 4/4/2017.
 */
public class Setup_5_ActivityTest {
    @Rule
    public ActivityTestRule<Setup_5_Activity> mActivityRule = new ActivityTestRule<>(
            Setup_5_Activity.class);

    @Test
    public void moveNext() throws Exception {
        Activity currentActivity = CurrentActivityUtil.getCurrentActivity();
        onView(withId(R.id.button9))
                .perform(click());
        assertEquals(currentActivity, CurrentActivityUtil.getCurrentActivity());

        selectSpinner(R.id.budgetSpinner, 1);
        onView(withId(R.id.button9))
                .perform(click());
        assertNotEquals(currentActivity, CurrentActivityUtil.getCurrentActivity());
    }

    @Test
    public void budgetHelp() throws Exception {

    }

    public void selectSpinner(int spinnerID, int position){
        onView(withId(R.id.budgetSpinner)).perform(click());
        onData(anything()).atPosition(1).perform(click());
    }



}