package com.cs506.accountable;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by Jonah on 4/3/2017.
 */
public class Setup_1_ActivityTest {
    @Rule
    public ActivityTestRule<Setup_1_Activity> mActivityRule = new ActivityTestRule<>(
            Setup_1_Activity.class);

    @Test
    public void confirmPin() throws Exception {

        Setup_1_Activity activity = mActivityRule.getActivity();

        //Enter a pin that is the wrong length
        onView(withId(R.id.secondPin))
                .perform(typeText("123"));
        closeSoftKeyboard();
        onView(withId(R.id.button5))
                .perform(click());

        //Check Toast Text
        onView(withText("PIN must be 4 digits long")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));

        sleep(5000); //Let Toast dissappear

        //Enter a pin that is the wrong pin
        onView(withId(R.id.secondPin))
                .perform(clearText(), typeText("9999"));
        closeSoftKeyboard();
        onView(withId(R.id.button5))
                .perform(click());

        //Check Toast Text
        onView(withText("Entered PIN does not equal previous PIN")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));

        Activity expectedActivity = CurrentActivityUtil.getCurrentActivity();

        //Enter a pin that is the wrong length
        onView(withId(R.id.secondPin))
                .perform(clearText(), typeText("1234"));
        closeSoftKeyboard();
        onView(withId(R.id.button5))
                .perform(click());

        //Check Activity Transition
        Activity currentActivity = CurrentActivityUtil.getCurrentActivity();
        assertNotEquals(currentActivity, expectedActivity);






    }

}