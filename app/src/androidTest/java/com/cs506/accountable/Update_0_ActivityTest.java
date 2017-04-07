package com.cs506.accountable;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by ChensongHu on 4/4/17.
 */

public class Update_0_ActivityTest extends ActivityInstrumentationTestCase2<Update_0_Activity> {
    private Update_0_Activity mActivity;


    public Update_0_ActivityTest(){
        super("com.cs506.accountable.Update_0_Activity", Update_0_Activity.class);
    }

    @Rule
    public ActivityTestRule<Update_0_Activity> mActivityRule = new ActivityTestRule<>(
            Update_0_Activity.class);

    protected void setUp() throws Exception{
        super.setUp();
        setActivityInitialTouchMode(false);
        mActivity = getActivity();
        Button updateBalanceButton = (Button)mActivity.findViewById(R.id.updateBalance);
        Button updateBills = (Button)mActivity.findViewById(R.id.updateBills);
        Button updateIncome = (Button)mActivity.findViewById(R.id.updateIncome);
        Button updateGoals = (Button)mActivity.findViewById(R.id.updateGoals);
        Button updateBudget = (Button)mActivity.findViewById(R.id.updateBudget);
        Button backToHome = (Button)mActivity.findViewById(R.id.backToHome);
    }

    @Test
    public void testPreconditions(){
        assertTrue((Button)mActivity.findViewById(R.id.updateBalance) != null);
        assertTrue((Button)mActivity.findViewById(R.id.updateBills) != null);
        assertTrue((Button)mActivity.findViewById(R.id.updateIncome) != null);
        assertTrue((Button)mActivity.findViewById(R.id.updateGoals) != null);
        assertTrue((Button)mActivity.findViewById(R.id.updateBudget) != null);
        assertTrue((Button)mActivity.findViewById(R.id.backToHome) != null);
    }

    @Test
    public void onClick() throws Exception{
        mActivity.runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        onView(withId(R.id.updateBalance)).perform(click());
                        Activity nextBalanceActivity = getActivity();
                        assertEquals(nextBalanceActivity, Update_1_Activity.class);

                        onView(withId(R.id.updateBills)).perform(click());
                        Activity nextBillActivity = getActivity();
                        assertEquals(nextBillActivity, Update_2_Activity.class);

                        onView(withId(R.id.updateIncome)).perform(click());
                        Activity nextIncomeActivity = getActivity();
                        assertEquals(nextIncomeActivity, Update_3_Activity.class);

                        onView(withId(R.id.updateGoals)).perform(click());
                        Activity nextGoalsActivity = getActivity();
                        assertEquals(nextGoalsActivity, Update_4_Activity.class);

                        onView(withId(R.id.updateBudget)).perform(click());
                        Activity nextBudgetActivity = getActivity();
                        assertEquals(nextBudgetActivity, Update_5_Activity.class);

                        onView(withId(R.id.backToHome)).perform(click());
                        Activity nextActivity = getActivity();
                        assertEquals(nextActivity, Main_Activity.class);
                    }

                }
        );
    }


}
