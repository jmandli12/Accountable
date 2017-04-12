package com.cs506.accountable;

import android.app.Activity;
import android.support.test.espresso.core.deps.guava.base.Strings;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
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

/**
 * Created by Jonah on 4/3/2017.
 */
public class Setup_2_ActivityTest {
    @Rule
    public ActivityTestRule<Setup_2_Activity> mActivityRule = new ActivityTestRule<>(
            Setup_2_Activity.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addAccount() throws Exception {
        Activity currentActivity = CurrentActivityUtil.getCurrentActivity();
        //Test a variety of input values for Account Balance
        //First we add a valid accuont name to isolate the account balance
        onView(withId(R.id.accountName))
                .perform(clearText(), typeText("Checking Account"));
        closeSoftKeyboard();
        checkBalanceField("1234");
        checkIfSameActivity(currentActivity);
        checkBalanceField("1234.1321");
        checkIfSameActivity(currentActivity);
        checkBalanceField("0");
        checkIfSameActivity(currentActivity);
        checkBalanceField(".12");
        checkIfSameActivity(currentActivity);
        //Lets clear account name and enter a valid account balance
        onView(withId(R.id.accountName))
                .perform(clearText());
        closeSoftKeyboard();
        checkBalanceField("50.45");
        checkIfSameActivity(currentActivity);
        //Finally Lets Check to see if two valid inputs takes us to the next activity
        onView(withId(R.id.accountName))
                .perform(typeText("Checking Account"));
        closeSoftKeyboard();
        onView(withId(R.id.button3))
                .perform(click());
        //Check that we Activity Transition
        Activity expectedActivity = CurrentActivityUtil.getCurrentActivity();
        assertNotEquals(currentActivity, expectedActivity);


    }

    @Test
    public void accountHelp() throws Exception {
        //We can get back to this later, doesn't seem that important to test at the moment
    }

    public void checkBalanceField(String input){
        onView(withId(R.id.accountBalance))
                .perform(clearText(), typeText(input));
        closeSoftKeyboard();
        onView(withId(R.id.button3))
                .perform(click());
    }

    public void checkIfSameActivity(Activity currentActivity){
        //Check that we Activity Transition
        Activity expectedActivity = CurrentActivityUtil.getCurrentActivity();
        assertEquals(currentActivity, expectedActivity);
    }

}