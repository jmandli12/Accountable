package com.cs506.accountable;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cs506.accountable.dto.User;
import com.cs506.accountable.sqlite.DataSource;

import java.io.File;
import java.util.List;

public class Lock_Screen_Activity extends AppCompatActivity {

    DataSource ds;
    int pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Check to see if it is users first time running application
        boolean firstTime = false; //TODO: get this value from the database
        boolean hasPin = false; //TODO: get this value from the database

        try {
            SQLiteDatabase checkDB = SQLiteDatabase.openDatabase("/data/data/com.cs506.accountable/databases/accountable.db", null, SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (Exception e) {
            firstTime = true;
        }
        ds = new DataSource(Lock_Screen_Activity.this);
        ds.open();
        if(ds.retrieveAll("user").size()==0){
            firstTime=true;
        }

        if (firstTime) {
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        } else {
            List<Object> obj = ds.retrieveAll("user");
            User user = (User) obj.get(0);
            if (user.getHasPin() == 1) {
                hasPin = true;
                pin = user.getPin();
            }

            if (!hasPin) {
                Intent intent = new Intent(this, Main_Activity.class);
                startActivity(intent);
            }
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
        int pin1 = Integer.parseInt(et.getText().toString());


        if(pin == pin1){
            //If the pins match then go to main view
            Intent intent = new Intent(this, Main_Activity.class);
            startActivity(intent);
        }
        else{
            et.setText("");
            Toast.makeText(this, "Pins do not Match.  Try Again", Toast.LENGTH_LONG).show();
        }

    }
}
