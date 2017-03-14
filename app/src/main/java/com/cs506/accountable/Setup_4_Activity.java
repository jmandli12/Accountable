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
        String hoursOrSalary;
        String payPeriod;


        EditText et = (EditText) findViewById(R.id.incomeName);
        incomeName = et.getText().toString();
        et = (EditText) findViewById(R.id.incomeAmount);
        incomeAmount = et.getText().toString();

        Spinner spinner = (Spinner) findViewById(R.id.hoursSpinner);
        hoursOrSalary = spinner.getSelectedItem().toString();

        spinner = (Spinner) findViewById(R.id.payPeriodSpinner);
        payPeriod = spinner.getSelectedItem().toString();

        if(!incomeAmount.isEmpty()) doubleAmount = Double.parseDouble(incomeAmount);
        //Create Object

        //Save Income
        //^This will be its own method

        boolean isValidAmount = incomeAmount.matches("([0-9]|([1-9][0-9]+))\\.[0-9][0-9]");

        if (isValidAmount && incomeName.length() > 0 && incomeAmount.length() > 2 && !hoursOrSalary.equals("Hourly or Salary? (Select One)") && !payPeriod.equals("Pay Period (Select One)")) {
            Toast.makeText(this, "IncomeName: " + incomeName + "\nIncomeAmount: " + incomeAmount + "\nHours: " + hoursOrSalary + "\nPayPeriod: " + payPeriod, Toast.LENGTH_LONG).show();
        }
        else {
            if (incomeName.length() == 0) {
                Toast.makeText(this, "Income Name cannot be empty", Toast.LENGTH_LONG).show();
            }
            //TODO: ALLOW USER TO START WITH NEGATIVE BALANCE
            if (incomeAmount.length() < 3 || !isValidAmount) {
                Toast.makeText(this, "Income amount must be in the format \"{dollars}.{cents}\"", Toast.LENGTH_LONG).show();
                //TODO:Check for invalid leading 0 in dollar side?
            }
            if (payPeriod.equals("Pay Period (Select One)")) {
                Toast.makeText(this, "Pay period must be selected", Toast.LENGTH_LONG).show();
            }
            if (hoursOrSalary.equals("Hourly or Salary? (Select One)")) {
                Toast.makeText(this, "Method of pay must be selected", Toast.LENGTH_LONG).show();
            }
        }

    }

    /*
    Move to next Activity
     */
    public void moveNext(View view) {

        String incomeName;
        String incomeAmount;
        double doubleAmount;
        String hoursOrSalary;
        String payPeriod;


        EditText et = (EditText) findViewById(R.id.incomeName);
        incomeName = et.getText().toString();
        et = (EditText) findViewById(R.id.incomeAmount);
        incomeAmount = et.getText().toString();

        Spinner spinner = (Spinner) findViewById(R.id.hoursSpinner);
        hoursOrSalary = spinner.getSelectedItem().toString();

        spinner = (Spinner) findViewById(R.id.payPeriodSpinner);
        payPeriod = spinner.getSelectedItem().toString();

        if (incomeName.equals("") && incomeAmount.equals("") && hoursOrSalary.equals("Hourly or Salary? (Select One)") && payPeriod.equals("Pay Period (Select One)")) {
            Intent intent = new Intent(this, Setup_5_Activity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Must complete adding income information", Toast.LENGTH_LONG).show();
        }
    }

    public void incomeNameHelp(View view) {
        Snackbar.make(view, "Name of your Source of Income ('Work', 'Company', etc)\n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();
    }

    public void incomeAmountHelp(View view) {
        Snackbar.make(view, "Amount of Money on Paycheck \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
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
