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

public class Update_3_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_3_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.hoursSpinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.income_hours_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.payPeriodSpinner2);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.pay_period_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner = (Spinner) findViewById(R.id.incomeSpinner);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.incomeNames_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        EditText incomeName = (EditText) findViewById(R.id.incomeName2);
        EditText incomeAmount = (EditText) findViewById(R.id.incomeAmount2);
        EditText receivingDate = (EditText) findViewById(R.id.receivingDate2);
        Spinner payPeriod = (Spinner) findViewById(R.id.payPeriodSpinner2);
        Spinner hours = (Spinner) findViewById(R.id.hoursSpinner2);
        Button button = (Button) findViewById(R.id.addUpdateIncome);

        String income = (String) parent.getItemAtPosition(pos);

        //Get all information about bill
        if(income.equals("New Income")){
            incomeName.setText("");
            incomeAmount.setText("");
            receivingDate.setText("");
            payPeriod.setSelection(0);
            hours.setSelection(0);
            button.setText("Add Income");
        } else{
            incomeName.setText("IncomeName");
            incomeAmount.setText("IncomeAmount");
            receivingDate.setText("Date");
            payPeriod.setSelection(1);
            hours.setSelection(1);
            button.setText("Update Income");
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void addUpdateIncome(View view) {
    }

    public void backToHome(View view) {
        Intent intent = new Intent(this, Update_0_Activity.class);
        startActivity(intent);
    }

    public void incomeNameHelp(View view) {
        Snackbar.make(view, "Name of your Source of Income ('Work', 'Company', etc)\n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();
    }

    public void incomeAmountHelp(View view) {
        Snackbar.make(view, "Amount of Money on Paycheck \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();
    }

    public void dueDateHelp(View view) {
        Snackbar.make(view, "When the paycheck is received. \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();
    }

    public void payPeriodHelp(View view) {
        Snackbar.make(view, "How often you get a Paycheck \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();
    }

    public void workKindHelp(View view) {
        Snackbar.make(view, "Do you work Hourly or Salary\n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();
    }

}
