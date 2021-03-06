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

import com.cs506.accountable.sqlite.DataSource;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Setup_4_Activity extends AppCompatActivity {
    DataSource ds;
    String pin;
    String accountID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ds = new DataSource(this);
        ds.open();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_4_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable Setup");
        setSupportActionBar(toolbar);

        Spinner spinner = (Spinner) findViewById(R.id.payPeriodSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pay_period_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Bundle prev = getIntent().getExtras();
        if(prev != null){
            pin = prev.getString("pin");
            accountID = prev.getString("accountID");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*
    Add entered income
     */
    public void addIncome(View view) {

        String incomeName;
        String incomeAmount;
        String dueDate;
        String payPeriod;


        EditText name = (EditText) findViewById(R.id.incomeName);
        incomeName = name.getText().toString();
        EditText amount = (EditText) findViewById(R.id.incomeAmount);
        incomeAmount = amount.getText().toString();
        EditText date = (EditText) findViewById(R.id.dueDate);
        dueDate = date.getText().toString();

        Spinner payPer = (Spinner) findViewById(R.id.payPeriodSpinner);
        payPeriod = payPer.getSelectedItem().toString();

        boolean isValidAmount = incomeAmount.matches("([0-9]|([1-9][0-9]+))\\.[0-9][0-9]");
        boolean isValidDate = dueDate.matches("([0][1-9]|[1][0-2])/([0][1-9]|[1-2][0-9]|[3][0-1])/([2][0][1][7-9]|[2][0][2-9][0-9])"); //TODO:

        if(isValidDate) {
            String[] dateArray = dueDate.split("/");
            GregorianCalendar pd = new GregorianCalendar(Integer.parseInt(dateArray[2]),
                    Integer.parseInt(dateArray[0])-1, Integer.parseInt(dateArray[1]));
            GregorianCalendar temp = new GregorianCalendar();
            GregorianCalendar today = new GregorianCalendar(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH),
                    temp.get(Calendar.DAY_OF_MONTH));
            isValidDate = !pd.before(today);
        }

        if (isValidAmount && isValidDate && incomeName.length() > 0 && incomeAmount.length() > 2 && !payPeriod.equals("Pay Period (Select One)")) {

            String[] incomeArgs = {null, "1", accountID, incomeName, incomeAmount, dueDate, payPeriod};
            ds.create("income", incomeArgs);

            Toast.makeText(this, "(Added Income Type)" + "\nIncomeName: " + incomeName + "\nIncomeAmount: " + incomeAmount + "\nRecievingDate: " + dueDate + "\nPayPeriod: " + payPeriod, Toast.LENGTH_LONG).show();

            name.setText("");
            amount.setText("");
            payPer.setSelection(0);
            date.setText("");

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
        }

    }

    /*
    Move to next Activity
     */
    public void moveNext(View view) {

        String incomeName;
        String incomeAmount;
        String payPeriod;
        String dueDate;


        EditText et = (EditText) findViewById(R.id.incomeName);
        incomeName = et.getText().toString();
        et = (EditText) findViewById(R.id.incomeAmount);
        incomeAmount = et.getText().toString();
        et = (EditText) findViewById(R.id.dueDate);
        dueDate = et.getText().toString();

        Spinner spinner = (Spinner) findViewById(R.id.payPeriodSpinner);
        payPeriod = spinner.getSelectedItem().toString();

        if (incomeName.equals("") && incomeAmount.equals("") && dueDate.equals("") && payPeriod.equals("Pay Period (Select One)")) {
            Intent intent = new Intent(this, Setup_6_Activity.class);
            intent.putExtra("accountID", accountID);
            intent.putExtra("pin", pin);
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

    public void dueDateHelp(View view) {
        Snackbar.make(view, "When the paycheck is received. \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();
    }

    public void payPeriodHelp(View view) {
        Snackbar.make(view, "How often you get a Paycheck \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();
    }
}
