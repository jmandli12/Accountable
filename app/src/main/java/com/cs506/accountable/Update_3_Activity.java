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
import android.widget.Toast;

import com.cs506.accountable.dto.Bill;
import com.cs506.accountable.dto.Income;
import com.cs506.accountable.sqlite.DataSource;

import java.util.ArrayList;
import java.util.List;

public class Update_3_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    DataSource ds;
    List<Income> incomeList;
    Income income;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ds = new DataSource(Update_3_Activity.this);
        ds.open();

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
        incomeList = new ArrayList<>();
        income = new Income();
        income.setIncomeName("New Income");
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

    public void addUpdateIncome(View view) {

       String incomeName;
        String incomeAmount;
        String dueDate;
        String hoursOrSalary;
        String payPeriod;


        EditText name = (EditText) findViewById(R.id.incomeName2);
        incomeName = name.getText().toString();
        EditText amount = (EditText) findViewById(R.id.incomeAmount2);
        incomeAmount = amount.getText().toString();
        EditText date = (EditText) findViewById(R.id.receivingDate2);
        dueDate = date.getText().toString();

        Spinner hOrS = (Spinner) findViewById(R.id.hoursSpinner2);
        hoursOrSalary = hOrS.getSelectedItemPosition() + "";
        Spinner payPer = (Spinner) findViewById(R.id.payPeriodSpinner2);
        payPeriod = payPer.getSelectedItem().toString();


        boolean isValidAmount = incomeAmount.matches("([0-9]|([1-9][0-9]+))\\.[0-9][0-9]");
        boolean isValidDate = dueDate.matches("([0][1-9]|[1][0-2])/([0][1-9]|[1-2][0-9]|[3][0-1])/([2][0][0-9][0-9])"); //TODO:

        if (isValidAmount && isValidDate && incomeName.length() > 0 && incomeAmount.length() > 2 && !hoursOrSalary.equals("0") && !payPeriod.equals("Pay Period (Select One)")) {

            if(button.getText().equals("Add Income")){
                String[] incomeArgs = {null, "1", String.valueOf(income.getAccountId()), incomeName, incomeAmount, dueDate, payPeriod, hoursOrSalary};
                ds.create("income", incomeArgs);
            } else {
                String[] incomeArgs = {String.valueOf(income.getIncomeId()), "1", String.valueOf(income.getAccountId()), incomeName, incomeAmount, dueDate, payPeriod, hoursOrSalary};
                ds.create("income", incomeArgs);
            }

            //Get Names of Incomes
            List<String> list = getIncomeNames();

            Spinner spinner = (Spinner) findViewById(R.id.incomeSpinner);
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item,list);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter1);
            spinner.setOnItemSelectedListener(this);

        }
        else {
            if (incomeName.length() == 0) {
                Toast.makeText(this, "Income Name cannot be empty", Toast.LENGTH_LONG).show();
            }
            //TODO: ALLOW USER TO START WITH NEGATIVE BALANCE
            if (incomeAmount.length() < 3 || !isValidAmount) {
                Toast.makeText(this, "Income Amount must be in the format \"{dollars}.{cents}\"", Toast.LENGTH_LONG).show();
                //TODO:Check for invalid leading 0 in dollar side?
            }
            if (!isValidDate) {
                Toast.makeText(this, "Receiving Date must be in the format mm/dd/year from this current date or onwards", Toast.LENGTH_LONG).show();
            }
            if (payPeriod.equals("Pay Period (Select One)")) {
                Toast.makeText(this, "Pay Period must be selected", Toast.LENGTH_LONG).show();
            }
            if (hoursOrSalary.equals("Hourly or Salary? (Select One)")) {
                Toast.makeText(this, "Method of pay must be selected", Toast.LENGTH_LONG).show();
            }
        }
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
        button = (Button) findViewById(R.id.addUpdateIncome);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pay_period_array, android.R.layout.simple_spinner_dropdown_item);

        //String income = (String) parent.getItemAtPosition(pos);
        income = incomeList.get(pos);

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

            String amount = String.valueOf(income.getAmount());
            if(amount.charAt(amount.length()-2) == '.') amount = amount.concat("0");
            incomeAmount.setText(String.valueOf(amount));

            receivingDate.setText(income.getDate());
            int spinnerPosition = adapter.getPosition(income.getPayPeriod());
            payPeriod.setSelection(spinnerPosition);
            hours.setSelection(income.getHours());
            button.setText("Update Income");
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
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
