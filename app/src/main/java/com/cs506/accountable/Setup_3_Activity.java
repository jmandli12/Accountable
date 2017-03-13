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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


public class Setup_3_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_3_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable Setup");
        setSupportActionBar(toolbar);

        Spinner spinner = (Spinner) findViewById(R.id.occurrenceSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bill_occurrence_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

 /*       String[] bills;

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, bills);
        ListView lv = (ListView) findViewById(R.id.billsList);
        lv.setAdapter(arrayAdapter);*/

    }

    /*
    Move to the next Activity if no fields are filled
     */
    public void moveNext(View view) {

        String billName;
        String billAmount;
        String dueDate;
        String occurrence;

        EditText et = (EditText) findViewById(R.id.billName);
        billName = et.getText().toString();
        et = (EditText) findViewById(R.id.billAmount);
        billAmount = et.getText().toString();
        et = (EditText) findViewById(R.id.dueDate);
        dueDate = et.getText().toString();
        Spinner spinner = (Spinner) findViewById(R.id.occurrenceSpinner);
        occurrence = spinner.getSelectedItem().toString();

        if (billName.equals("") && billAmount.equals("") && occurrence.equals("(Press to Select)")) {
            Intent intent = new Intent(this, Setup_4_Activity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Must complete adding bill", Toast.LENGTH_LONG).show();
        }

    }

    /*
    Add bill to list
     */
    public void addBill(View view) {

        String billName;
        String billAmount;
        String dueDate;
        String occurrence;

        EditText et = (EditText) findViewById(R.id.billName);
        billName = et.getText().toString();
        et = (EditText) findViewById(R.id.billAmount);
        billAmount = et.getText().toString();
        et = (EditText) findViewById(R.id.dueDate);
        dueDate = et.getText().toString();
        Spinner spinner = (Spinner) findViewById(R.id.occurrenceSpinner);
        occurrence = spinner.getSelectedItem().toString();

        boolean isValidAmount = billAmount.matches("([0-9]|([1-9][0-9]+))\\.[0-9][0-9]");
//      boolean isValidDate = dueDate.matches("([0][1-9]|[1][0-2])/([0][1-9]|)");

        if (isValidAmount && billName.length() > 0 && billAmount.length() > 2 && !occurrence.equals("(Press to Select)")) {
            Toast.makeText(this, "BillName: " + billName + "\nBillAmount: " + billAmount + "\nDueDate:" + dueDate + "\nOccurrence: " + occurrence, Toast.LENGTH_LONG).show();
        }

        }
        else {
            if (billName.length() == 0) {
                Toast.makeText(this, "Bill name cannot be empty", Toast.LENGTH_LONG).show();
            }
            if (billAmount.length() < 3 || !isValidAmount) {
                Toast.makeText(this, "Bill amount must be in the format \"{dollars}.{cents}\"", Toast.LENGTH_LONG).show();
                //TODO:Check for invalid leading 0 in dollar side?
            }
            if (occurrence.equals("(Press to Select)")) {
                Toast.makeText(this, "Occurrence must be selected", Toast.LENGTH_LONG).show();
            }
        }

    public void billAmountHelp(View view) {
        Snackbar.make(view, "Amount the bill is worth \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();
    }

    public void billNameHelp(View view) {
        Snackbar.make(view, "Name of the Bill ('Electric','Phone', etc) \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();
    }

    public void occurrenceHelp(View view) {
        Snackbar.make(view, "How often do you pay the Bill. \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();
    }

    public void dueDateHelp(View view) {
        Snackbar.make(view, "When the bill is due. \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();
    }
}
