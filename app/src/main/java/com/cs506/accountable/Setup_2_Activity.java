package com.cs506.accountable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Setup_2_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_2_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable Setup");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*
    In charge of saving bank info and for now moving to next screen.
     */
    public void addAccount(View view) {

        String accountName;
        String accountBalance;

        EditText et = (EditText) findViewById(R.id.accountName);
        accountName = et.getText().toString();
        et = (EditText) findViewById(R.id.accountBalance);
        accountBalance = et.getText().toString();
        boolean isValidAmount = accountBalance.matches("([0-9]|([1-9][0-9]+))\\.[0-9][0-9]");
        if (isValidAmount && accountName.length() > 0 && accountBalance.length() > 2) {
            Toast.makeText(this, "Account Name: " + accountName + " Account Balance: " + accountBalance, Toast.LENGTH_LONG).show();

            //Save Bank Info

            // Move onto next Activity
            Intent intent = new Intent(this, Setup_3_Activity.class);
            startActivity(intent);
        }
        else {
            if (accountName.length() == 0) {
                Toast.makeText(this, "Account Name cannot be empty", Toast.LENGTH_LONG).show();
            }
            if (accountBalance.length() < 3 || !isValidAmount) {
                Toast.makeText(this, "Account Balance must be in the format \"{dollars}.{cents}\"", Toast.LENGTH_LONG).show();
                //TODO:Check for invalid leading 0 in dollar side?
            }
        }
    }
}
