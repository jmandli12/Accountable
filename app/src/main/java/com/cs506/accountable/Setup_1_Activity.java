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

import com.cs506.accountable.sqlite.DataSource;

public class Setup_1_Activity extends AppCompatActivity {
    String unconfirmedPIN;
    DataSource ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ds = new DataSource(this);
        ds.open();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_1_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable Setup");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle prev = getIntent().getExtras();
        unconfirmedPIN = prev.getString("unconfirmedPIN");
    }

    /*
    Confirm PIN and move on
     */
    public void confirmPin(View view) {

        EditText et = (EditText) findViewById(R.id.secondPin);
        String pinString = et.getText().toString();

        if(pinString.equals(unconfirmedPIN) && pinString.length() == 4) {
            // TODO: Save the Entered PIN

            Toast.makeText(this, "PIN confirmed", Toast.LENGTH_LONG).show();

            //Move onto next screen
            Intent intent = new Intent(this, Setup_2_Activity.class);
            //intent.putExtra("userID", "0");
            intent.putExtra("pin", pinString);
            startActivity(intent);
            finish();
        }
        else if(pinString.length() != 4) {
            Toast.makeText(this, "PIN must be 4 digits long", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Entered PIN does not equal previous PIN", Toast.LENGTH_LONG).show();
        }
    }
}
