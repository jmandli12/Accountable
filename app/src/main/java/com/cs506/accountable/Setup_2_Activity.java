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
        String accountValue;

        EditText et = (EditText) findViewById(R.id.accountName);
        accountName = et.getText().toString();
        et = (EditText) findViewById(R.id.accountValue);
        accountValue = et.getText().toString();

        Toast.makeText(this, "Account Name: " + accountName + " Account Value: " + accountValue, Toast.LENGTH_LONG).show();

        //Save Bank Info

        // Move onto next Activity
        Intent intent = new Intent(this, Setup_3_Activity.class);
        startActivity(intent);

    }
}
