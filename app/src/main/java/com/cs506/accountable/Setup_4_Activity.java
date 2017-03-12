package com.cs506.accountable;

import android.content.Intent;
import android.icu.text.DecimalFormat;
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

public class Setup_4_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_4_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable Setup");
        setSupportActionBar(toolbar);

        Spinner spinner = (Spinner) findViewById(R.id.hoursSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.income_hours_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner = (Spinner) findViewById(R.id.payPeriodSpinner);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.pay_period_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    /*
    Add entered income
     */
    public void addIncome(View view) {

        String incomeName;
        String incomeAmount;
        double doubleAmount;
        String hours;
        String payPeriod;


        EditText et = (EditText) findViewById(R.id.incomeName);
        incomeName = et.getText().toString();
        et = (EditText) findViewById(R.id.incomeAmount);
        incomeAmount = et.getText().toString();

        Spinner spinner = (Spinner) findViewById(R.id.hoursSpinner);
        hours = spinner.getSelectedItem().toString();

        spinner = (Spinner) findViewById(R.id.payPeriodSpinner);
        payPeriod = spinner.getSelectedItem().toString();

        doubleAmount = Double.parseDouble(incomeAmount);
        //Create Object

        //Save Income
        //^This will be its own method


        Toast.makeText(this, "IncomeName: " + incomeName + "\nIncomeAmount: " + incomeAmount + "\nHours: " + hours + "\nPayPeriod: " + payPeriod, Toast.LENGTH_LONG).show();

    }

    /*
    Move to next Activity
     */
    public void moveNext(View view) {
        Intent intent = new Intent(this, Setup_5_Activity.class);
        startActivity(intent);
    }

    public void incomeNameHelp(View view) {
        Snackbar.make(view, "Name of your Source of Income ('Work', 'Company_Name', 'Shoveling Snow', etc)", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void incomeAmountHelp(View view) {
        Snackbar.make(view, "Amount of Money on Paycheck (Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();
    }
}