package com.cs506.accountable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cs506.accountable.sqlite.DataSource;

public class Setup_6_Activity extends AppCompatActivity {
    DataSource ds;
    String pin;
    String accountID;
    String budget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ds = new DataSource(this);
        ds.open();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_6_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable Setup");
        setSupportActionBar(toolbar);

        Spinner spinner = (Spinner) findViewById(R.id.timePeriodSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time_period_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.unitSavingSpinner);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.unit_of_saving_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Bundle prev = getIntent().getExtras();
        pin = prev.getString("pin");
        accountID = prev.getString("accountID");
        budget = prev.getString("budget");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void addGoal(View view) {

        //  0=daily, 1=weekly, 2=monthly, 3=yearly
        String timePeriod;
        String originalTimePeriod;
        // 0=$, 1=% of income, 2=% of savings
        String unitSaving;
        String originalUnitSaving;
        String amount;

        Spinner spinner = (Spinner) findViewById(R.id.timePeriodSpinner);
        originalTimePeriod = spinner.getSelectedItem().toString();
        timePeriod = originalTimePeriod;
        if (timePeriod.equals("Daily")) {
            timePeriod = "0";
        }
        else if (timePeriod.equals("Weekly")) {
            timePeriod = "1";
        }
        else if (timePeriod.equals("Monthly")) {
            timePeriod = "2";
        }
        else if (timePeriod.equals("Yearly")) {
            timePeriod = "3";
        }
        else {
            timePeriod = "";
        }
        spinner = (Spinner) findViewById(R.id.unitSavingSpinner);
        originalUnitSaving = spinner.getSelectedItem().toString();
        unitSaving = originalUnitSaving;
        if (unitSaving.equals("$")) {
            unitSaving = "0";
        }
        else if (unitSaving.equals("% of Income")) {
            unitSaving = "1";
        }
        else if (unitSaving.equals("% of Savings")) {
            unitSaving = "2";
        }
        else {
            unitSaving = "";
        }
        EditText text = (EditText) findViewById(R.id.amountToSave);
        amount = text.getText().toString();


        if (!timePeriod.equals("(Press to Select)") && !unitSaving.equals("(Select One)")) {
            String[] goalArgs = {"1", timePeriod, unitSaving, amount};
            ds.create("goal", goalArgs);
        }

        Toast.makeText(this, "TimePeriod Selected: " + originalTimePeriod + " UnitSaving Selected: "
                + originalUnitSaving + " Amount to Save: " + amount, Toast.LENGTH_LONG).show();


    }

    /*
    Save goal and move to next Activity
     */
    public void moveNext(View view) {

        //finally create user
        String hasPin;
        if (pin.equals("noPin")) {
            hasPin = "0";
            pin = "0";
        } else {
            hasPin = "1";
        }

        //userID, userName, pinHash, pin, salt, firstTime(now it is false), budget, hasPin
        String[] userArgs = {"1", "User", "0", pin, "0", "0", budget, hasPin};

        ds.create("user", userArgs);
        Intent intent = new Intent(this, Setup_7_Activity.class);

        // TODO:
        //create new user with pushed info, if fails, popup notification and go back to beginnning
        startActivity(intent);
    }

    public void savingsHelp(View view) {

        switch (view.getId()) {
            case R.id.goalNameHelp:
                Snackbar.make(view, "Name of your personal goal. \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", null).show();
                break;
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
}
