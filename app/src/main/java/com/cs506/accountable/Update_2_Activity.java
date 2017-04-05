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

public class Update_2_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_2_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Spinner spinner = (Spinner) findViewById(R.id.occurrenceSpinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bill_occurrence_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner = (Spinner) findViewById(R.id.billSpinner);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.billNames_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        EditText billName = (EditText) findViewById(R.id.billName2);
        EditText billAmount = (EditText) findViewById(R.id.billAmount2);
        EditText dueDate = (EditText) findViewById(R.id.dueDate2);
        Spinner occurrence = (Spinner) findViewById(R.id.occurrenceSpinner2);
        Button button = (Button) findViewById(R.id.addUpdateButton);

        String bill = (String) parent.getItemAtPosition(pos);

        //Get all information about bill
        if(bill.equals("New Bill")){
            billName.setText("");
            billAmount.setText("");
            dueDate.setText("");
            occurrence.setSelection(0);
            button.setText("Add Bill");
        } else{
            billName.setText("billName");
            billAmount.setText("billAmount");
            dueDate.setText("dueDate");
            occurrence.setSelection(1);
            button.setText("Update Bill");
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
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

    public void backToHome(View view) {
        Intent intent = new Intent(this, Update_0_Activity.class);
        startActivity(intent);
    }

    public void addUpdateBill(View view) {
    }
}
