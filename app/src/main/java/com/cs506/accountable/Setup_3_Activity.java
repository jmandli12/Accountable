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

import com.cs506.accountable.dto.Account;
import com.cs506.accountable.dto.Bill;
import com.cs506.accountable.sqlite.DataSource;

import java.util.*;

public class Setup_3_Activity extends AppCompatActivity {
    DataSource ds;
    String pin;
    String accountID;
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
        Bundle prev = getIntent().getExtras();
        pin = prev.getString("pin");
        accountID = prev.getString("accountID");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

 /*       String[] bills;

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, bills);
        ListView lv = (ListView) findViewById(R.id.billsList);
        lv.setAdapter(arrayAdapter);*/

    }

    /*
    Move to the next Activity
     */
    public void moveNext(View view) {
        Intent intent = new Intent(this, Setup_4_Activity.class);
        intent.putExtra("accountID", accountID);
        intent.putExtra("pin", pin);
        startActivity(intent);
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
        String[] billArgs = {"0", accountID, billName, billAmount, dueDate, occurrence};
        ds.create("bill", billArgs);
        //bills.add(new Bill(null, 1, 1, ));
        Toast.makeText(this, "BillName: " + billName + "\nBillAmount: " + billAmount + "\nDueDate:" + dueDate + "\nOccurrence: " + occurrence, Toast.LENGTH_LONG).show();
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
