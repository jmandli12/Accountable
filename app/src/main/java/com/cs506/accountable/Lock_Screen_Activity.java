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
import android.widget.TextView;
import android.widget.Toast;

import com.cs506.accountable.dto.User;
import com.cs506.accountable.sqlite.DataSource;

import java.io.File;
import java.util.List;

public class Lock_Screen_Activity extends AppCompatActivity {

    DataSource ds;
    int pin;
    boolean changePIN = false;
    boolean keepGoing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ds = new DataSource(Lock_Screen_Activity.this);
        ds.open();
        Bundle prev = getIntent().getExtras();

        if (prev != null) {
            if(prev.getString("changePIN") != null){
                if(prev.getString("changePIN").equals("YES")){
                    keepGoing=false;
                    User user = (User) ds.retrieveById("user", "1");
                    if (user.getHasPin() == 1) {
                        pin = user.getPin();
                    }
                    changePIN = true;
                }
            }
        }

        if(keepGoing) {
            //Check to see if it is users first time running application
            boolean firstTime = false;
            boolean hasPin = false;

            try {
                SQLiteDatabase checkDB = SQLiteDatabase.openDatabase("/data/data/com.cs506.accountable/databases/accountable.db", null, SQLiteDatabase.OPEN_READONLY);
                checkDB.close();
            } catch (Exception e) {
                firstTime = true;
            }

            if (!firstTime && ds.retrieveAll("user").size() == 0) {
                firstTime = true;
            }

            if (firstTime) {
                Intent intent = new Intent(this, WelcomeActivity.class);
                ds.wipe();
                startActivity(intent);
            } else {
                User user1 = (User) ds.retrieveById("user", "1");
                if (user1.getHasPin() == 1) {
                    hasPin = true;
                    pin = user1.getPin();
                }

                if (!hasPin) {
                    Intent intent = new Intent(this, Main_Activity.class);
                    startActivity(intent);
                }
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
        String pinString = et.getText().toString();


        if(pinString.length() == 4) {
            // Move to next Screen
            int pin1 = Integer.parseInt(pinString);
            if (pin == pin1) {
                //If the pins match then go to main view
                if(changePIN){
                    Intent intent = new Intent(this, Setup_0_Activity.class);
                    intent.putExtra("changePIN", "YES");
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, Main_Activity.class);
                    startActivity(intent);
                }

            } else {
                et.setText("");
                Toast.makeText(this, "Pins do not Match.  Try Again", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, "PIN must be 4 digits long", Toast.LENGTH_LONG).show();
        }




    }
}
