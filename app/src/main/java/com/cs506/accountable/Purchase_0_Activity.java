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


        EditText purchasePrice = (EditText) findViewById(R.id.purchasePrice);
        price = purchasePrice.getText().toString();

        EditText purchaseDate = (EditText) findViewById(R.id.purchaseDate);
        date = purchaseDate.getText().toString();

        EditText purchaseTime = (EditText) findViewById(R.id.purchaseTime);
        time = purchaseTime.getText().toString();

        EditText purchaseLocation = (EditText) findViewById(R.id.purchaseLocation);
        location = purchaseLocation.getText().toString();

        EditText purchaseComments = (EditText) findViewById(R.id.purchaseComments);
        comments = purchaseComments.getText().toString();

        Spinner categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        category = categorySpinner.getSelectedItem().toString();

        boolean isValidAmount = price.matches("([0-9]|([1-9][0-9]+))\\.[0-9][0-9]");
        boolean isValidDate = date.matches("([0][1-9]|[1][0-2])/([0][1-9]|[1-2][0-9]|[3][0-1])/([2][0][1][7-9]|[2][0][2-9][0-9])");
        boolean isValidTime = time.matches("((0[1-9])|(1[0-2])):([0-5][0-9]) ((AM)|(PM))");
        /*

        doubleAmount = Double.parseDouble(purchasePrice);
        //Create Object
        */
        //Save Income
        //^This will be its own method

        if (isValidTime && isValidDate && isValidAmount && price.length() > 2 && !category.equals("Category (Select One)")) {

            if(!price.isEmpty() && !date.isEmpty() && !time.isEmpty() && !category.isEmpty() && !location.isEmpty() && !comments.isEmpty()){
                Toast.makeText(this,"Purchase Price: "+price+"\nDate/Time: "+date+" "+time+"\nCategory: "+category+"\nLocation: "+location+"\nComments: "+comments,Toast.LENGTH_LONG).show();
            } else if (!price.isEmpty() && !date.isEmpty() && !time.isEmpty() && !category.isEmpty() && !location.isEmpty()){
                Toast.makeText(this,"Purchase Price: "+price+"\nDate/Time: "+date+" "+time+"\nCategory: "+category+"\nLocation: "+location,Toast.LENGTH_LONG).show();
            } else if (!price.isEmpty() && !date.isEmpty() && !time.isEmpty() && !category.isEmpty() && !comments.isEmpty()){
                Toast.makeText(this,"Purchase Price: "+price+"\nDate/Time: "+date+" "+time+"\nCategory: "+category+"\nComments: "+comments,Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"Purchase Price: "+price+"\nDate/Time: "+date+" "+time+"\nCategory: "+category,Toast.LENGTH_LONG).show();
            }

            purchasePrice.setText("");
            purchaseDate.setText("");
            purchaseTime.setText("");
            purchaseLocation.setText("");
            purchaseComments.setText("");
            categorySpinner.setSelection(0);

            //Set the date and time so the user does not have to
            EditText date2 = (EditText)findViewById(R.id.purchaseDate);
            EditText time2 = (EditText)findViewById(R.id.purchaseTime);
            Date d = new Date();
            String pdate = (String) DateFormat.format("MM/dd/yyyy", d.getTime());
            String ptime = (String) DateFormat.format("hh:mm a", d.getTime());
            date2.setText(pdate);
            time2.setText(ptime);



        } else {
            if (price.length() < 3 || !isValidAmount) {
                Toast.makeText(this, "Price amount must be in the format \"{dollars}.{cents}\"", Toast.LENGTH_LONG).show();
                //TODO:Check for invalid leading 0 in dollar side?
            }
            if (!isValidDate) {
                Toast.makeText(this, "Purchase date must be in the format of a valid mm/dd/year", Toast.LENGTH_LONG).show();
            }
            if (!isValidTime){
                Toast.makeText(this, "Purchase time must be in the format of a valid {hr}:{min}", Toast.LENGTH_LONG).show();
            }
            if (category.equals("Category (Select One)")) {
                Toast.makeText(this, "Category must be selected", Toast.LENGTH_LONG).show();
            }
        }

    }

    /*
    Move to the next Activity if no fields are filled
     */
    public void backToHome(View view) {

        Intent intent = new Intent(this, Main_Activity.class);
        startActivity(intent);
    }
}
