package com.cs506.accountable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class Lock_Screen_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Check to see if it is users first time running application
        boolean firstTime = true; //TODO: get this value from the database
        boolean noPin = false; //TODO: get this value from the database
        if(firstTime){
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        }
        else if(noPin){
            Intent intent = new Intent(this, Main_Activity.class);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock__screen_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable");
        setSupportActionBar(toolbar);

    }

    /*
    Check to see if pin is correct and go to main view
     */
    public void buttonClickHandler(View view) {

        EditText et = (EditText) findViewById(R.id.passwordPin);
        String pin = et.getText().toString();


        //If the pins match then go to main view
        Intent intent = new Intent(this, Main_Activity.class);
        startActivity(intent);
    }
}
