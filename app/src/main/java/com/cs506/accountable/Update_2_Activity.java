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
import com.cs506.accountable.sqlite.DataSource;

import java.util.ArrayList;
import java.util.List;

public class Update_2_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DataSource ds;
    List<Bill> billList;
    Bill bill;
    Button button;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ds = new DataSource(Update_2_Activity.this);
        ds.open();

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

        //Get Names of Bills
        List<String> list = getBillNames();

        spinner = (Spinner) findViewById(R.id.billSpinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, list);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(this);

        deleteButton = (Button) findViewById(R.id.deleteBill);
        deleteButton.setVisibility(View.GONE);
    }

    public List<String> getBillNames() {
        List<Object> obj = ds.retrieveAll("bill");
        List<String> billNames = new ArrayList<>();
        billList = new ArrayList<>();
        Bill bill = new Bill();
        bill.setBillName("New Bill");
        billList.add(bill);

        for (Object object : obj) {
            bill = (Bill) object;
            billList.add(bill);
        }

        for (Bill b : billList) {
            billNames.add(b.getBillName());
        }
        return billNames;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        EditText billName = (EditText) findViewById(R.id.billName2);
        EditText billAmount = (EditText) findViewById(R.id.billAmount2);
        EditText dueDate = (EditText) findViewById(R.id.dueDate2);
        Spinner occurrence = (Spinner) findViewById(R.id.occurrenceSpinner2);
        button = (Button) findViewById(R.id.addUpdateButton);

        //String bill = (String) parent.getItemAtPosition(pos);

        bill = billList.get(pos);

        //Get all information about bill
        if (pos == 0) {
            billName.setText("");
            billAmount.setText("");
            dueDate.setText("");
            occurrence.setSelection(0);
            button.setText("Add Bill");
            deleteButton.setVisibility(View.INVISIBLE);
        } else {
            billName.setText(bill.getBillName());

            String amount = String.valueOf(bill.getBillAmount());
            if(amount.charAt(amount.length()-2) == '.') amount = amount.concat("0");
            billAmount.setText(String.valueOf(amount));

            dueDate.setText(bill.getDueDate());

            occurrence.setSelection(bill.getOccurrenceRte());

            button.setText("Update Bill");
            deleteButton.setVisibility(View.VISIBLE);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void addUpdateBill(View view) {

        String billName;
        String billAmount;
        String dueDate;
        String occurrence;


        EditText bill1 = (EditText) findViewById(R.id.billName2);
        billName = bill1.getText().toString();
        EditText amount = (EditText) findViewById(R.id.billAmount2);
        billAmount = amount.getText().toString();
        EditText date = (EditText) findViewById(R.id.dueDate2);
        dueDate = date.getText().toString();
        Spinner spinner = (Spinner) findViewById(R.id.occurrenceSpinner2);
        occurrence = spinner.getSelectedItemPosition() + "";

        boolean isValidAmount = billAmount.matches("([0-9]|([1-9][0-9]+))\\.[0-9][0-9]");
        boolean isValidDate = dueDate.matches("([0][1-9]|[1][0-2])/([0][1-9]|[1-2][0-9]|[3][0-1])/([2][0][0-9][0-9])");

        if (isValidDate && isValidAmount && billName.length() > 0 && billAmount.length() > 2 && !occurrence.equals("0")) {


            if (button.getText().equals("Add Bill")) {
                String[] billArgs = {null, "1", "1", billName, billAmount, dueDate, "" + occurrence + ""};
                Bill rbill = (Bill) ds.create("bill", billArgs);
            } else {
                String[] billArgs = {String.valueOf(bill.getBillId()), "1", "1", billName, billAmount, dueDate, "" + occurrence + ""};
                Bill rbill = (Bill) ds.create("bill", billArgs);
            }

            //Get Names of Bills
            List<String> list = getBillNames();

            spinner = (Spinner) findViewById(R.id.billSpinner);
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter1);
            spinner.setOnItemSelectedListener(this);

        } else {
            if (billName.length() == 0) {
                Toast.makeText(this, "Bill name cannot be empty", Toast.LENGTH_LONG).show();
            }
            if (billAmount.length() <= 3 || !isValidAmount) {
                Toast.makeText(this, "Bill amount must be in the format \"{dollars}.{cents}\"", Toast.LENGTH_LONG).show();
                //TODO:Check for invalid leading 0 in dollar side?
            }
            if (!isValidDate) {
                Toast.makeText(this, "Due date must be in the format mm/dd/year from this current date or onwards", Toast.LENGTH_LONG).show();
            }
            if (occurrence.equals("0")) {
                Toast.makeText(this, "Occurrence must be selected", Toast.LENGTH_LONG).show();
            }
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

    public void deleteBill(View view) {
        ds.deleteById("bill", String.valueOf(bill.getBillId()));

        Toast.makeText(this, bill.getBillName() + " deleted!", Toast.LENGTH_LONG).show();

        //Get Names of Goals
        List<String> list = getBillNames();

        Spinner spinner = (Spinner) findViewById(R.id.billSpinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,list);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(this);
    }

}
