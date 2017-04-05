package com.cs506.accountable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Update_1_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_1_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView account1Name = (TextView) findViewById(R.id.account1Name);
        account1Name.setText("Account 1 Name Here");
        EditText account1 = (EditText) findViewById(R.id.account1);
        String accountBalance = "100.33";
        account1.setText(accountBalance);

    }

    public void updateAccount(View view) {
        //Save updated data

        //Move back to Update Screen
        Intent intent = new Intent(this, Update_0_Activity.class);
        startActivity(intent);
    }
}
