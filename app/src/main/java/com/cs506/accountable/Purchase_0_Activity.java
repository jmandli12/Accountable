package com.cs506.accountable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Date;

public class Purchase_0_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_0_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable");
        setSupportActionBar(toolbar);

        Spinner spinner = (Spinner) findViewById(R.id.categorySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_dropdown_item );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set the date and time so the user does not have to
        EditText date = (EditText)findViewById(R.id.purchaseDate);
        EditText time = (EditText)findViewById(R.id.purchaseTime);
        Date d = new Date();
        String pdate = (String) DateFormat.format("MM/dd/yyyy", d.getTime());
        String ptime = (String) DateFormat.format("hh:mm a", d.getTime());
        date.setText(pdate);
        time.setText(ptime);


    }

    /*
    Add a purchase and return to home screen for now.
     */
    public void addPurchase(View view) {

        String category;
        String price;
        double doubleAmount;
        String date;
        String time;
        String location;
        String comments;


        EditText et = (EditText) findViewById(R.id.purchasePrice);
        price = et.getText().toString();

        et = (EditText) findViewById(R.id.purchaseDate);
        date = et.getText().toString();

        et = (EditText) findViewById(R.id.purchaseTime);
        time = et.getText().toString();

        et = (EditText) findViewById(R.id.purchaseLocation);
        location = et.getText().toString();

        et = (EditText) findViewById(R.id.purchaseComments);
        comments = et.getText().toString();

        Spinner spinner = (Spinner) findViewById(R.id.categorySpinner);
        category = spinner.getSelectedItem().toString();

        boolean isValidAmount = price.matches("([0-9]|([1-9][0-9]+))\\.[0-9][0-9]");

        /*

        doubleAmount = Double.parseDouble(purchasePrice);
        //Create Object
        */
        //Save Income
        //^This will be its own method

        if (isValidAmount && price.length() > 2 && !category.equals("Category (Select One)")) {

            Toast.makeText(this,"Purchase Price: "+price+"\nDate/Time: "+date+" "+time+"\nCategory: "+category+"\nLocation: "+location+"\nComments: "+comments,Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, Main_Activity.class);
            startActivity(intent);

        } else {
            if (price.length() < 3 || !isValidAmount) {
                Toast.makeText(this, "Price amount must be in the format \"{dollars}.{cents}\"", Toast.LENGTH_LONG).show();
                //TODO:Check for invalid leading 0 in dollar side?
            }
            if (category.equals("Category (Select One)")) {
                Toast.makeText(this, "Category must be selected", Toast.LENGTH_LONG).show();
            }
        }

    }
}
