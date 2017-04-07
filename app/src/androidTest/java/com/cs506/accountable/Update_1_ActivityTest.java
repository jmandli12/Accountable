package com.cs506.accountable;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Rule;
import org.junit.Test;

/**
 * Created by ChensongHu on 4/4/17.
 */

public class Update_1_ActivityTest extends ActivityInstrumentationTestCase2<Update_1_Activity> {
    private Update_1_Activity mActivity;


    public Update_1_ActivityTest(){
        super("com.cs506.accountable.Update_1_Activity", Update_1_Activity.class);
    }
    @Rule
    public ActivityTestRule<Update_1_Activity> mActivityRule = new ActivityTestRule<>(
            Update_1_Activity.class);

    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        mActivity = getActivity();
    }

    @Test
    public void testPreconditions(){
        assertTrue(null != mActivity.getIntent());
    }

    @Test
    public void updateAccount()throws Exception{
        mActivity.runOnUiThread(
                new Runnable() {
                    @Override
                    public void run(){
                        Activity nextBalanceActivity = getActivity();
                        assertEquals(nextBalanceActivity, Update_0_Activity.class);
                    }

                }
        );


    }

}
