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

        String timePeriod;
        String unitSaving;
        String amount;
        String goalName;

        Spinner spinner1 = (Spinner) findViewById(R.id.timePeriodSpinner);
        timePeriod = spinner1.getSelectedItemPosition() + "";
        Spinner spinner2 = (Spinner) findViewById(R.id.unitSavingSpinner);
        unitSaving = spinner2.getSelectedItemPosition() + "";

        EditText text = (EditText) findViewById(R.id.amountToSave);
        amount = text.getText().toString();
        EditText name = (EditText) findViewById(R.id.goalName);
        goalName = name.getText().toString();

        boolean isValidAmount = amount.matches("([0-9]|([1-9][0-9]+))\\.[0-9][0-9]");

        if (isValidAmount && goalName.length() > 0 && !timePeriod.equals("0") && !unitSaving.equals("0")) {

            String[] goalArgs = { null, "1", goalName, timePeriod, unitSaving, amount};
            ds.create("goal", goalArgs);

            Toast.makeText(this, "Goal Name: " + goalName + " TimePeriod Selected: " + timePeriod
                    + " UnitSaving Selected: " + unitSaving + " Amount to Save: "
                    + amount, Toast.LENGTH_LONG).show();

            name.setText("");
            text.setText("");
            spinner1.setSelection(0);
            spinner2.setSelection(0);


        } else {
            if (goalName.length() == 0) {
                Toast.makeText(this, "Goal Name cannot be empty", Toast.LENGTH_LONG).show();
            }
            if (timePeriod.equals(timePeriod.equals("0"))) {
                Toast.makeText(this, "Time Period must be selected", Toast.LENGTH_LONG).show();
            }
            if (timePeriod.equals(unitSaving.equals("0"))) {
                Toast.makeText(this, "Unit of Saving must be selected", Toast.LENGTH_LONG).show();
            }
            if (amount.length() < 3 || !isValidAmount) {
                Toast.makeText(this, "Goal Amount must be in the format \"{dollars}.{cents}\"", Toast.LENGTH_LONG).show();
            }
        }
    }

    /*
    Save goal and move to next Activity
     */
    public void moveNext(View view) {

        String timePeriod;
        String unitSaving;
        String amount;
        String goalName;

        Spinner spinner1 = (Spinner) findViewById(R.id.timePeriodSpinner);
        timePeriod = spinner1.getSelectedItemPosition() + "";
        Spinner spinner2 = (Spinner) findViewById(R.id.unitSavingSpinner);
        unitSaving = spinner2.getSelectedItemPosition() + "";

        EditText text = (EditText) findViewById(R.id.amountToSave);
        amount = text.getText().toString();
        EditText name = (EditText) findViewById(R.id.goalName);
        goalName = name.getText().toString();

        if (goalName.equals("") && timePeriod.equals("0") && unitSaving.equals("0") && amount.equals("")) {

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

        } else {
            Toast.makeText(this, "Must complete adding goal information", Toast.LENGTH_LONG).show();
        }
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
