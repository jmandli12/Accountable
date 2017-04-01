package com.cs506.accountable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import com.cs506.accountable.dto.User;
import com.cs506.accountable.sqlite.DataSource;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DataSource ds = new DataSource(this);

        boolean firstTime = true;
        //firstTime = ds.isFirstTime();


        //Check to see if it is users first time running application




        boolean noPin = false;
        //noPin = !(ds.hasPin("0"));
        if(firstTime){
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        }
        else if(noPin){
            Intent intent = new Intent(this, Main_Activity.class);
            startActivity(intent);
        }
    }

    /*
    This method is in charge of saving the entered PIN and moving to next Activity
     */
    public void buttonClickHandler(View view) {

        // Move to next Screen
        Intent intent = new Intent(this, Setup_0_Activity.class);
        startActivity(intent);
    }
}
