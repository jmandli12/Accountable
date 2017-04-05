package com.cs506.accountable;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Predicates.instanceOf;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Created by Jonah on 4/3/2017.
 */
public class Setup_4_ActivityTest {
    int incomeNameID = R.id.incomeName;
    int incomeAmountID = R.id.incomeAmount;
    int dateID = R.id.dueDate;
    int payPeriodSpinnerID = R.id.payPeriodSpinner;
    int hourSpinnerID = R.id.hoursSpinner;
    int addIncomeButtonID = R.id.button7;

    @Rule
    public ActivityTestRule<Setup_4_Activity> mActivityRule = new ActivityTestRule<>(
            Setup_4_Activity.class);


    @Test
    public void addIncome() throws Exception {
        String[] nameTestValues = {""};
        String[] incomeTestValues = {"1234", "0", "9.23123", "1234.1", "0.1"};
        String[] dateTestValues = {"2/2/2014", "4135423543", "19/1/2018", "1234.1", "../12/2045"}; //TODO: Add more
        initializeTestValues("name");
        testField(incomeNameID, nameTestValues);
        initializeTestValues("amount");
        testField(incomeAmountID, incomeTestValues);
        initializeTestValues("date");
        testField(dateID, dateTestValues);
    }

    @Test
    public void moveNext() throws Exception {

    }

    @Test
    public void incomeNameHelp() throws Exception {

    }

    @Test
    public void incomeAmountHelp() throws Exception {

    }

    @Test
    public void dueDateHelp() throws Exception {

    }

    @Test
    public void payPeriodHelp() throws Exception {

    }

    @Test
    public void workKindHelp() throws Exception {

    }

    public void selectSpinner(int spinnerID, int position){
        onView(withId(spinnerID)).perform(click());
        onData(anything()).atPosition(position).perform(click());
    }

    public void testField(int id, String[] testValues){
        Activity currentActivity = CurrentActivityUtil.getCurrentActivity();
        for (int i = 0; i < testValues.length; i++) {
            onView(withId(id))
                    .perform(clearText(), typeText(testValues[i]));
            closeSoftKeyboard();
            onView(withId(addIncomeButtonID))
                    .perform(click());
        }
        Activity expectedActivity = CurrentActivityUtil.getCurrentActivity();
        assertEquals(currentActivity, expectedActivity);
    }

    //Basically this will reset all the appropriate fields so we can isolate one field
    public void initializeTestValues(String leaveOut){
        //After further inspection this is a better way to write this function. The code in setup 3 is garbage
        onView(withId(incomeAmountID))
                .perform(clearText(), typeText("100.50"));
        onView(withId(dateID))
                .perform(clearText(), typeText("04/20/2018"));
        onView(withId(incomeNameID))
                .perform(clearText(), typeText("Chicken Tendies Sales Commission"));
        closeSoftKeyboard();
        selectSpinner(hourSpinnerID, 1); //TODO: Worth it to test the spinners this iteration?
        selectSpinner(payPeriodSpinnerID, 1);
        switch(leaveOut){
            case "name":
                onView(withId(incomeNameID))
                        .perform(clearText());
                break;
            case "amount":
                onView(withId(incomeAmountID))
                        .perform(clearText());
                break;
            case "date":
                onView(withId(dateID))
                        .perform(clearText());
                break;
        }
    }

}