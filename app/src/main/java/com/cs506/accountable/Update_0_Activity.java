package com.cs506.accountable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Update_0_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_0_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClick(View view) {

        Intent intent;// = new Intent(this, Waiting_2_Activity.class);

        switch (view.getId()) {
            case R.id.updateBalance:
                intent = new Intent(this, Update_1_Activity.class);
                startActivity(intent);
                break;
            case R.id.updateBills:
                intent = new Intent(this, Update_2_Activity.class);
                startActivity(intent);
                break;
            case R.id.updateIncome:
                intent = new Intent(this, Update_3_Activity.class);
                startActivity(intent);
                break;
            case R.id.updateGoals:
                intent = new Intent(this, Update_4_Activity.class);;
                startActivity(intent);
                break;
            case R.id.backToHome:
                intent = new Intent(this, Main_Activity.class);;
                startActivity(intent);
                break;
        }
    }
}
