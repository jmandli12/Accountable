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
import android.widget.Spinner;

import com.cs506.accountable.dto.Goal;
import com.cs506.accountable.dto.Income;
import com.cs506.accountable.sqlite.DataSource;

import java.util.ArrayList;
import java.util.List;

import static com.cs506.accountable.R.id.incomeAmount;
import static com.cs506.accountable.R.id.incomeName;

public class Update_4_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    DataSource ds;
    List<Goal> goalList;
    Goal goal;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_4_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ds = new DataSource(Update_4_Activity.this);
        ds.open();

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

        //Get Names of Incomes
        List<String> list = getGoalNames();

        spinner = (Spinner) findViewById(R.id.goalSpinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,list);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(this);
    }

    public List<String> getGoalNames() {

        List<Object> obj = ds.retrieveAll("goal");
        List<String> goalNames = new ArrayList<>();
        goalList = new ArrayList<>();
        goal = new Goal();
        goal.setGoalName("New Goal");
        goalList.add(goal);

        for(Object object : obj){
            goal = (Goal) object;
            goalList.add(goal);
        }

        for(Goal g : goalList){
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
        Button button = (Button) findViewById(R.id.addUpdateGoal);

        String goal = (String) parent.getItemAtPosition(pos);

        //Get all information about bill
        if(goal.equals("New Goal")){
            timePeriod.setSelection(0);
            unitSaving.setSelection(0);
            amountToSave.setText("");
            button.setText("Add Goal");
        } else{
            timePeriod.setSelection(1);
            unitSaving.setSelection(1);
            amountToSave.setText("Goal info here");
            button.setText("Update Goal");
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void addUpdateGoal(View view) {

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



    public void backToHome(View view) {
        Intent intent = new Intent(this, Update_0_Activity.class);
        startActivity(intent);
    }
}
