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
import android.widget.Spinner;
import android.widget.Toast;

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
    }

    /*
    Add a purchase and return to home screen for now.
     */
    public void addPurchase(View view) {

        String category;
        String purchasePrice;
        double doubleAmount;
        String date;
        String time;
        String location;
        String comments;


        EditText et = (EditText) findViewById(R.id.purchasePrice);
        purchasePrice = et.getText().toString();

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
/*

        doubleAmount = Double.parseDouble(purchasePrice);
        //Create Object
*/
        //Save Income
        //^This will be its own method


        Toast.makeText(this,"Purchase Price: "+purchasePrice+"\nDate/Time: "+date+" "+time+"\nCategory: "+category+"\nLocation: "+location+"\nComments: "+comments,Toast.LENGTH_LONG).show();


        Intent intent = new Intent(this, Main_Activity.class);
        startActivity(intent);

    }
}
