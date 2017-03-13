package com.cs506.accountable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Setup_0_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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


        // Save the Entered PIN


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
}

