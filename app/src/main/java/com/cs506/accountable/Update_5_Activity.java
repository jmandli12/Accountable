package com.cs506.accountable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cs506.accountable.dto.User;
import com.cs506.accountable.sqlite.DataSource;

import java.util.List;

public class Update_5_Activity extends AppCompatActivity {

    DataSource ds;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_5_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ds = new DataSource(Update_5_Activity.this);
        ds.open();

        Spinner spinner = (Spinner) findViewById(R.id.budgetSpinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.budget_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        List<Object> obj = ds.retrieveAll("user");
        user = (User) obj.get(0);

        int spinnerPosition = adapter.getPosition(user.getBudget());
        spinner.setSelection(spinnerPosition);
    }

    public void updateBudget(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.budgetSpinner2);
        String budget = spinner.getSelectedItem().toString();

        String[] userArgs = {"1", "User", "0", String.valueOf(user.getPin()), "0", "0", budget, String.valueOf(user.getHasPin())};
        ds.create("user", userArgs);

        Intent intent = new Intent(this, Update_0_Activity.class);
        startActivity(intent);
    }

    public void budgetHelp(View view) {
        Snackbar.make(view, "NOT SURE WHAT GOES HERE YET \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();
    }

}
