package com.cs506.accountable;

import android.support.design.widget.Snackbar;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.AdapterView;

import org.junit.Rule;

/**
 * Created by ChensongHu on 4/4/17.
 */

public class Update_4_ActivityTest {
    @Rule
    public ActivityTestRule<Update_4_Activity> mActivityRule = new ActivityTestRule<>(
            Update_4_Activity.class);

    public void onItemSelected() throws Exception{

    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void savingsHelp(View view) {

        switch (view.getId()) {
            case R.id.timePeriodHelp:
                Snackbar.make(view, "Time period of this goal. \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", null).show();
                break;
            case R.id.unitOfSavingHelp:
                Snackbar.make(view, "Unit in which you would like to save. \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", null).show();
                break;
            case R.id.amountToSaveHelp:
                Snackbar.make(view, "Amount or Percentage you wish to save. \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", null).show();
                break;
        }
    }

    public void addUpdateGoal(View view) {
    }

    public void backToHome(View view) {
//        Intent intent = new Intent(this, Update_0_Activity.class);
//        startActivity(intent);
    }
}
