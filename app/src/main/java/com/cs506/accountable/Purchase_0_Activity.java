package com.cs506.accountable;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.cs506.accountable.dto.Account;
import com.cs506.accountable.dto.User;
import com.cs506.accountable.sqlite.DataSource;

public class Purchase_0_Activity extends AppCompatActivity {
    DataSource ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ds = new DataSource(this);
        ds.open();

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

        Spinner spinner1 = (Spinner) findViewById(R.id.amPMSpinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.amPM_array, android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        //Set the date and time so the user does not have to
        EditText date = (EditText)findViewById(R.id.purchaseDate);
        EditText time = (EditText)findViewById(R.id.purchaseTime);
        Date d = new Date();
        String pdate = (String) DateFormat.format("MM/dd/yyyy", d.getTime());
        String ptime = (String) DateFormat.format("hh:mm", d.getTime());
        String amPM = (String) DateFormat.format("a", d.getTime());

        if(amPM.equals("AM")){
            spinner1.setSelection(0);
        } else{
            spinner1.setSelection(1);
        }
        date.setText(pdate);
        time.setText(ptime);

        User user = (User) ds.retrieveById("user", "1");
        Toast.makeText(this, "Allowance Remaining: $" + String.format("%.2f", user.getAllowance()), Toast.LENGTH_LONG).show();

    }

    /*
    Add a purchase and return to home screen for now.
     */
    public void addPurchase(View view) {

        String category;
        String price;
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

        Spinner amPMSpinner = (Spinner) findViewById(R.id.amPMSpinner);
        time = time + " " + amPMSpinner.getSelectedItem();

        boolean isValidAmount = price.matches("([0-9]|([1-9][0-9]+))\\.[0-9][0-9]");
        boolean isValidDate = date.matches("([0][1-9]|[1][0-2])/([0][1-9]|[1-2][0-9]|[3][0-1])/([2][0][1][7-9]|[2][0][2-9][0-9])");
        boolean isValidTime = time.matches("((0[1-9])|(1[0-2])):([0-5][0-9]) ((AM)|(PM))");
        /*

        doubleAmount = Double.parseDouble(purchasePrice);
        //Create Object
        */
        //Save Income
        //^This will be its own method

        if(isValidDate) {
            String[] dateArray = date.split("/");
            GregorianCalendar pd = new GregorianCalendar(Integer.parseInt(dateArray[2]),
                    Integer.parseInt(dateArray[0])-1, Integer.parseInt(dateArray[1]));
            GregorianCalendar temp = new GregorianCalendar();
            GregorianCalendar today = new GregorianCalendar(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH),
                    temp.get(Calendar.DAY_OF_MONTH));
            isValidDate = !temp.equals(today);
        }

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
            String ptime = (String) DateFormat.format("hh:mm", d.getTime());
            date2.setText(pdate);
            time2.setText(ptime);

            String[] purchaseArgs = {null, "1", "1", price, date, time,
                    category, location, comments};
            ds.create("purchase", purchaseArgs);

            Account account = (Account) ds.retrieveById("account", "1");
            double balance = account.getBalance();
            int intBalance = (int) (balance * 100.0);
            int intPrice = (int) (Double.parseDouble(price) * 100.0);
            account.setBalance((double) (intBalance - intPrice) / 100);
            String[] newAccountArgs = {"1", "1", account.getAccountName(), String.valueOf(account.getBalance()), String.valueOf(account.getStartBalance())};
            ds.create("account", newAccountArgs);

            User user = (User) ds.retrieveById("user", "1");
//            if(user.getLastCalc().isEmpty()) {
//                user.setAllowance(0.0);
//            }
            double allowance = user.getAllowance();
            int intAllowance = (int) (allowance * 100.0);
            user.setAllowance((double) (intAllowance - intPrice) / 100);
            String[] newUserArgs = {"1", "User", "0", String.valueOf(user.getPin()), "0", "0",
                    user.getBudget(), String.valueOf(user.getHasPin()), user.getLastSync(),
                    user.getLastCalc(), String.valueOf(user.getAllowance())};
            ds.create("user", newUserArgs);

            if(user.getAllowance() < 0.0) {
                Toast.makeText(this, "ALERT: You have exceeded today's spending allowance! " + user.getAllowance(), Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "Allowance Remaining: $" + String.format("%.2f", user.getAllowance()), Toast.LENGTH_LONG).show();
            }
        } else {
            if (price.length() < 3 || !isValidAmount) {
                Toast.makeText(this, "Price amount must be in the format \"{dollars}.{cents}\"", Toast.LENGTH_LONG).show();
                //TODO:Check for invalid leading 0 in dollar side?
            }
            if (!isValidDate) {
                Toast.makeText(this, "Purchase date must be in the format of a valid mm/dd/year \nFuture and Past dates are not yet supported.", Toast.LENGTH_LONG).show();
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
    Move to Home View onClick of button
     */
    public void backToHome(View view) {

        Intent intent = new Intent(this, Main_Activity.class);
        startActivity(intent);
    }
}
