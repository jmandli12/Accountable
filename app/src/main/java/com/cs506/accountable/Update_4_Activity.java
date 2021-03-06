package com.cs506.accountable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.cs506.accountable.dto.Goal;
import com.cs506.accountable.dto.Income;
import com.cs506.accountable.sqlite.DataSource;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;
import static android.view.View.GONE;
import static com.cs506.accountable.R.id.incomeAmount;
import static com.cs506.accountable.R.id.incomeName;

public class Update_4_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DataSource ds;
    List<Goal> goalList;
    Goal goal;
    Button button;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ds = new DataSource(Update_4_Activity.this);
        ds.open();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_4_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.timePeriodSpinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time_period_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.unitSavingSpinner2);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.unit_of_saving_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //Get Names of Incomes
        List<String> list = getGoalNames();

        spinner = (Spinner) findViewById(R.id.goalSpinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, list);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(this);

        deleteButton = (Button) findViewById(R.id.deleteGoal);
        deleteButton.setVisibility(View.GONE);
    }

    public List<String> getGoalNames() {

        List<Object> obj = ds.retrieveAll("goal");
        List<String> goalNames = new ArrayList<>();
        goalList = new ArrayList<>();
        goal = new Goal();
        goal.setGoalName("New Goal");
        goalList.add(goal);

        for (Object object : obj) {
            goal = (Goal) object;
            goalList.add(goal);
        }

        for (Goal g : goalList) {
            goalNames.add(g.getGoalName());
        }
        return goalNames;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Spinner timePeriod = (Spinner) findViewById(R.id.timePeriodSpinner2);
        Spinner unitSaving = (Spinner) findViewById(R.id.unitSavingSpinner2);
        EditText amountToSave = (EditText) findViewById(R.id.amountToSave2);
        EditText goalName = (EditText) findViewById(R.id.goalName2);
        button = (Button) findViewById(R.id.addUpdateGoal);
        deleteButton = (Button) findViewById(R.id.deleteGoal);

        switch (parent.getId()) {
            case R.id.unitSavingSpinner2:
                LinearLayout timePeriodLayout = (LinearLayout) findViewById(R.id.timePeriodLayout2);
                if (pos == 2) {
                    timePeriodLayout.setVisibility(View.INVISIBLE);
                } else {
                    timePeriodLayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.goalSpinner:
                goal = goalList.get(pos);

                //Get all information about bill
                if (pos == 0) {
                    goalName.setText("");
                    timePeriod.setSelection(0);
                    unitSaving.setSelection(0);
                    amountToSave.setText("");
                    button.setText("Add Goal");
                    deleteButton.setVisibility(View.GONE);
                } else {
                    deleteButton.setVisibility(View.VISIBLE);
                    goalName.setText(goal.getGoalName());
                    timePeriod.setSelection(goal.getTimePeriod());
                    unitSaving.setSelection(goal.getUnit());

                    String amount;
                    if (goal.getUnit() > 1) {
                        amount = String.valueOf((int) goal.getAmount());
                    } else {
                        amount = String.valueOf(goal.getAmount());
                    }
                    if (amount.charAt(amount.length() - 2) == '.') amount = amount.concat("0");
                    amountToSave.setText(String.valueOf(amount));

                    button.setText("Update Goal");
                }

        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void addUpdateGoal(View view) {

        String timePeriod;
        String unitSaving;
        String amount;
        String goalName;

        Spinner spinner1 = (Spinner) findViewById(R.id.timePeriodSpinner2);
        timePeriod = spinner1.getSelectedItemPosition() + "";
        Spinner spinner2 = (Spinner) findViewById(R.id.unitSavingSpinner2);
        unitSaving = spinner2.getSelectedItemPosition() + "";

        EditText text = (EditText) findViewById(R.id.amountToSave2);
        amount = text.getText().toString();
        EditText name = (EditText) findViewById(R.id.goalName2);
        goalName = name.getText().toString();

        boolean isValidAmount = false;
        if (unitSaving.equals("1")) {
            isValidAmount = amount.matches("([0-9]|([1-9][0-9]+))\\.[0-9][0-9]");
        }
        if (unitSaving.equals("2")) {
            isValidAmount = amount.matches("([0-9]|([1-9][0-9])|100)");
        }

        List<Goal> goals = (List<Goal>) (List<?>) ds.retrieveAll("goal");
        boolean duplicate = false;
        if (!goals.isEmpty()) {
            duplicate = goals.get(0).getUnit() == Integer.parseInt(unitSaving);
            if (goals.size() == 2) {
                duplicate = goals.get(0).getUnit() == Integer.parseInt(unitSaving)
                        || goals.get(1).getUnit() == Integer.parseInt(unitSaving);
            }
        }

        if (isValidAmount && goalName.length() > 0 && (!timePeriod.equals("0") || unitSaving.equals("2")) && !unitSaving.equals("0") && !duplicate) {
            if (unitSaving.equals("2") || unitSaving.equals("3")) {
                amount = String.valueOf((double) Integer.parseInt(amount));
            }
            if (button.getText().equals("Add Goal")) {
                String[] goalArgs = {null, "1", goalName, String.valueOf(timePeriod), String.valueOf(unitSaving), amount};
                ds.create("goal", goalArgs);
            } else {
                String[] goalArgs = {String.valueOf(goal.getGoalId()), "1", goalName, String.valueOf(timePeriod), String.valueOf(unitSaving), amount};
                ds.create("goal", goalArgs);
            }

            if(unitSaving.equals("1")) {
                Toast.makeText(this, "Goal Name: " + goalName + " TimePeriod Selected: " + timePeriod
                        + " UnitSaving Selected: " + unitSaving + " Amount to Save: "
                        + amount, Toast.LENGTH_LONG).show();
            }
            if(unitSaving.equals("2")) {
                Toast.makeText(this, "Goal Name: " + goalName
                        + " UnitSaving Selected: " + unitSaving + " Amount to Save: "
                        + amount, Toast.LENGTH_LONG).show();
            }

            //Get Names of Goals
            List<String> list = getGoalNames();

            Spinner spinner = (Spinner) findViewById(R.id.goalSpinner);
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter1);
            spinner.setOnItemSelectedListener(this);


        } else {
            if (duplicate) {
                Toast.makeText(this, "Only one goal per unit type supported", Toast.LENGTH_LONG).show();
            }
            if (goalName.length() == 0) {
                Toast.makeText(this, "Goal Name cannot be empty", Toast.LENGTH_LONG).show();
            }
            if (unitSaving.equals("0")) {
                Toast.makeText(this, "Unit of Saving must be selected", Toast.LENGTH_LONG).show();
            }
            if (unitSaving.equals("1")) {
                if (amount.length() < 3 || !isValidAmount) {
                    Toast.makeText(this, "Goal Amount must be in the format \"{dollars}.{cents}\"", Toast.LENGTH_LONG).show();
                }
                if (timePeriod.equals("0")) {
                    Toast.makeText(this, "Time Period must be selected", Toast.LENGTH_LONG).show();
                }
            } else if (unitSaving.equals("2")) {
                if (!isValidAmount) {
                    Toast.makeText(this, "\"% of Income\" Goal Amount must be between 0 and 100", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    public void deleteGoal(View view) {
        ds.deleteById("goal", String.valueOf(goal.getGoalId()));

        Toast.makeText(this, goal.getGoalName() + " deleted!", Toast.LENGTH_LONG).show();

        //Get Names of Goals
        List<String> list = getGoalNames();

        Spinner spinner = (Spinner) findViewById(R.id.goalSpinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, list);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(this);
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
