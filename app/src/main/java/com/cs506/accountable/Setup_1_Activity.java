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

import com.cs506.accountable.dto.User;
import com.cs506.accountable.sqlite.DataSource;

import java.util.List;

public class Setup_1_Activity extends AppCompatActivity {
    String unconfirmedPIN;
    DataSource ds;
    boolean changePIN = false;
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
        if(prev != null){
            unconfirmedPIN = prev.getString("unconfirmedPIN");
            if(prev.getString("changePIN") != null){
                changePIN = true;
            }
        }else{
            unconfirmedPIN = "1234";
        }

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

            if(changePIN){
                List<Object> obj = ds.retrieveAll("user");
                User user = (User) obj.get(0);
                String[] userArgs = {"1", "User", "0", pinString, "0", "0", user.getBudget(), "1"};
                ds.create("user", userArgs);
                Toast.makeText(this, "Updated PIN", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, Main_Activity.class);
                startActivity(intent);
            } else {
                //Move onto next screen
                Intent intent = new Intent(this, Setup_2_Activity.class);
                //intent.putExtra("userID", "0");
                intent.putExtra("pin", pinString);
                startActivity(intent);
                finish();
            }
        }
        else if(pinString.length() != 4) {
            Toast.makeText(this, "PIN must be 4 digits long", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Entered PIN does not equal previous PIN", Toast.LENGTH_LONG).show();
        }
    }
}
