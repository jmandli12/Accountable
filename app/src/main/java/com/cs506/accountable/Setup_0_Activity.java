package com.cs506.accountable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cs506.accountable.dto.Account;
import com.cs506.accountable.dto.Bill;
import com.cs506.accountable.dto.Goal;
import com.cs506.accountable.dto.Income;
import com.cs506.accountable.dto.Purchase;
import com.cs506.accountable.dto.User;
import com.cs506.accountable.sqlite.DataSource;

public class Setup_0_Activity extends AppCompatActivity {

    DataSource ds;
    User user;
    Account account;
    Bill bill;
    Income income;
    Purchase purchase;
    Goal goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ds = new DataSource(this);
        ds.open();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_0_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable Setup");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void buttonClickHandler(View view) {

        EditText et = (EditText) findViewById(R.id.firstPin);
        String pinString = et.getText().toString();

        //Toast.makeText(this, "PIN: " + pinString, Toast.LENGTH_LONG).show();
        if(pinString.length() == 4) {
            // Move to next Screen
            Intent intent = new Intent(this, Setup_1_Activity.class);
            intent.putExtra("unconfirmedPIN", pinString);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "PIN must be 4 digits long", Toast.LENGTH_LONG).show();
        }
    }

    public void skipButton(View view) {
        Intent intent = new Intent(this, Setup_2_Activity.class);
        startActivity(intent);
    }
}

