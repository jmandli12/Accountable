package com.cs506.accountable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cs506.accountable.sqlite.DataSource;

public class Setup_5_Activity extends AppCompatActivity {
    String pin;
    String accountID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_5_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable Setup");
        setSupportActionBar(toolbar);

        Spinner spinner = (Spinner) findViewById(R.id.budgetSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.budget_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Bundle prev = getIntent().getExtras();
        pin = prev.getString("pin");
        accountID = prev.getString("accountID");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*
    Save budget plan and move to next Activity
     */
    public void moveNext(View view) {

        Spinner spinner = (Spinner) findViewById(R.id.budgetSpinner);
        String budget = spinner.getSelectedItem().toString();
        //TODO: Retrieve user and add budget?
        //TODO: Perhaps move this screen to earlier spot? Closer to creation of user?
        //TODO: Perhaps pass userID, pin, accountID, and this budget to the end of the Setup?


        if (!budget.equals("(Select One)")) {
            Toast.makeText(this, "Budget Selected: " + budget, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, Setup_6_Activity.class);
            intent.putExtra("accountID", accountID);
            intent.putExtra("pin", pin);
            intent.putExtra("budget", budget);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Must select a budget", Toast.LENGTH_LONG).show();
        }
    }
}
