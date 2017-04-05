package com.cs506.accountable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cs506.accountable.sqlite.DataSource;

public class Setup_7_Activity extends AppCompatActivity {

    DataSource ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_7_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable Setup");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ds = new DataSource(this);
        ds.open();
    }

    /*
    Save Goal and move to next Activity
     */
    public void moveNext(View view) {
        Intent intent = new Intent(this, Main_Activity.class);
        startActivity(intent);
    }
}
