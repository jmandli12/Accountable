package com.cs506.accountable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
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

        ds = new DataSource(Setup_6_Activity.this);
        ds.open();
    }

    /*
    Save goal and move to next Activity
     */
    public void moveNext(View view) {

        String timePeriod;
        String unitSaving;

        Spinner spinner = (Spinner) findViewById(R.id.timePeriodSpinner);
        timePeriod = spinner.getSelectedItem().toString();
        spinner = (Spinner) findViewById(R.id.unitSavingSpinner);
        unitSaving = spinner.getSelectedItem().toString();


        if (!timePeriod.equals("(Press to Select)") && !unitSaving.equals("(Select One)")) {

        }

        Toast.makeText(this, "TimePeriod Selected: " + timePeriod + " UnitSaving Selected: " + unitSaving, Toast.LENGTH_LONG).show();


        //finally create user
        String hasPin;
        if (pin.equals("noPin")) {
            hasPin = "0";
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
