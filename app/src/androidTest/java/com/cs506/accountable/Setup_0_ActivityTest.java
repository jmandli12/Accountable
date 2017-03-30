package com.cs506.accountable;

import android.support.test.espresso.ViewAssertion;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;


/**
 * Created by Jonah on 3/15/2017.
 */
public class Setup_0_ActivityTest {


    private String mStringToBetyped;

    @Rule
    public ActivityTestRule<Setup_0_Activity> mActivityRule = new ActivityTestRule<>(
            Setup_0_Activity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        mStringToBetyped = "111111";
    }

    @Test
    public void necessaryViewsExist() {

        // Check that both buttons exist was changed.
        onView(withId(R.id.button2))
                .check(matches(isDisplayed()));
        onView(withId(R.id.skipButton))
                .check(matches(isDisplayed()));
        onView(withId(R.id.firstPin))
                .check(matches(isDisplayed()));

    }

    @Test
    public void skipWorks() {
        onView(withId(R.id.skipButton))
                .perform(click());
        onView(withId(R.id.content_setup_2_))
                .check(matches(isDisplayed()));
    }

//    @Test
//    public void changeText_shouldFail() {
//        // Type text and then press the button.
//        onView(withId(R.id.firstPin))
//                .perform(typeText(mStringToBetyped));
//
//        // Check that the text was changed.
//        onView(withId(R.id.firstPin))
//                .check(matches(withText("222222")));
//    }

}