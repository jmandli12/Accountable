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

import com.cs506.accountable.dto.Bill;
import com.cs506.accountable.dto.Income;
import com.cs506.accountable.sqlite.DataSource;

import java.util.ArrayList;
import java.util.List;

public class Update_3_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    DataSource ds;
    List<Income> incomeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_3_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ds = new DataSource(Update_3_Activity.this);
        ds.open();

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


        //Get Names of Incomes
        List<String> list = getIncomeNames();

        spinner = (Spinner) findViewById(R.id.incomeSpinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,list);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(this);

    }

    public List<String> getIncomeNames() {

        List<Object> obj = ds.retrieveAll("income");
        List<String> incomeNames = new ArrayList<>();
        Income income = new Income();
        income.setIncomeName("New Bill");
        incomeList.add(income);

        for(Object object : obj){
            income = (Income) object;
            incomeList.add(income);
        }

        for(Income i : incomeList){
            incomeNames.add(i.getIncomeName());
        }
        return incomeNames;
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

        //String income = (String) parent.getItemAtPosition(pos);
        Income income = incomeList.get(pos);

        //Get all information about bill
        if(pos==0){
            incomeName.setText("");
            incomeAmount.setText("");
            receivingDate.setText("");
            payPeriod.setSelection(0);
            hours.setSelection(0);
            button.setText("Add Income");
        } else{
            incomeName.setText(income.getIncomeName());
            incomeAmount.setText("$"+income.getAmount());
            receivingDate.setText(income.getDate());
            //TODO: Change payPeriod to int
            //payPeriod.setSelection(income.getPayPeriod());
            hours.setSelection(income.getHours());
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
