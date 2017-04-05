package com.cs506.accountable;

import android.app.Activity;
import android.support.test.espresso.core.deps.guava.base.Strings;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
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
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Created by Jonah on 4/3/2017.
 */
public class Setup_3_ActivityTest {
    int addBillButton = R.id.button4;
    int nextButton = R.id.button6;
    int billNameID = R.id.billName;
    int billAmountID= R.id.billAmount;
    int dueDateID = R.id.dueDate;
    int occurenceSpinnerID = R.id.occurrenceSpinner;


    @Rule
    public ActivityTestRule<Setup_3_Activity> mActivityRule = new ActivityTestRule<>(
            Setup_3_Activity.class);

    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void moveNext() throws Exception {

    }

    @Test
    public void addBill() throws Exception {
        String[] nameTestValues = {""};
        String[] billTestValues = {"1234", "0", "9.23123", "1234.1", "0.1"};
        String[] dateTestValues = {"2/2/2014", "4135423543", "19/1/2018", "1234.1", "../12/2045"}; //TODO: Add more
        initializeTestValues("name");
        testField(billNameID, nameTestValues);
        initializeTestValues("amount");
        testField(billAmountID, billTestValues);
        initializeTestValues("date");
        testField(dueDateID, dateTestValues);
        //TODO: Occurences?


    }

    @Test
    public void billAmountHelp() throws Exception {

    }

    @Test
    public void billNameHelp() throws Exception {

    }

    @Test
    public void occurrenceHelp() throws Exception {

    }

    @Test
    public void dueDateHelp() throws Exception {

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
            onView(withId(addBillButton))
                    .perform(click());
        }
        Activity expectedActivity = CurrentActivityUtil.getCurrentActivity();
        assertEquals(currentActivity, expectedActivity);
    }
    //Basically this will reset all the appropriate fields so we can isolate one field
    public void initializeTestValues(String leaveOut){
        switch(leaveOut){
            case "name":
                onView(withId(billAmountID))
                        .perform(clearText(), typeText("100.50"));
                closeSoftKeyboard();
                onView(withId(dueDateID))
                        .perform(clearText(), typeText("04/20/2018"));
                closeSoftKeyboard();
                selectSpinner(occurenceSpinnerID, 2);
                onView(withId(billNameID))
                        .perform(clearText());
                closeSoftKeyboard();
                break;
            case "amount":
                onView(withId(billAmountID))
                        .perform(clearText());
                closeSoftKeyboard();
                onView(withId(dueDateID))
                        .perform(clearText(), typeText("04/20/2018"));
                closeSoftKeyboard();
                selectSpinner(occurenceSpinnerID, 2);
                onView(withId(billNameID))
                        .perform(clearText(), typeText("Ham Sandwiches"));
                closeSoftKeyboard();
                break;
            case "date":
                onView(withId(billAmountID))
                        .perform(clearText(), typeText("100.50"));
                closeSoftKeyboard();
                onView(withId(dueDateID))
                        .perform(clearText());
                closeSoftKeyboard();
                selectSpinner(occurenceSpinnerID, 2);
                onView(withId(billNameID))
                        .perform(clearText(), typeText("Ham Sandwiches"));
                closeSoftKeyboard();
                break;
            case "occurence":
                onView(withId(billAmountID))
                        .perform(clearText(), typeText("100.50"));
                closeSoftKeyboard();
                onView(withId(dueDateID))
                        .perform(clearText());
                closeSoftKeyboard();
                selectSpinner(occurenceSpinnerID, 1);
                onView(withId(billNameID))
                        .perform(clearText(), typeText("Ham Sandwiches"));
                closeSoftKeyboard();
                break;
        }
    }

}