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

public class Setup_1_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_1_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable Setup");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*
    Confirm PIN and move on
     */
    public void confirmPin(View view) {

        EditText et = (EditText) findViewById(R.id.secondPin);
        String pinString = et.getText().toString();


        // Save the Entered PIN

        Toast.makeText(this, "PIN = " + pinString, Toast.LENGTH_LONG).show();

        //Move onto next screen
        Intent intent = new Intent(this, Setup_2_Activity.class);
        startActivity(intent);
    }
}
