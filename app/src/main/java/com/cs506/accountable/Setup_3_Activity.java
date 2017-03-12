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

public class Setup_3_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_3_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable Setup");
        setSupportActionBar(toolbar);

        Spinner spinner = (Spinner) findViewById(R.id.occurrenceSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bill_occurrence_array, android.R.layout.simple_spinner_dropdown_item );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*
    Move to the next Activity
     */
    public void moveNext(View view) {
        Intent intent = new Intent(this, Setup_4_Activity.class);
        startActivity(intent);
    }

    /*
    Add bill to list
     */
    public void addBill(View view) {

        EditText et = (EditText) findViewById(R.id.billName);
        String billName = et.getText().toString();
        et = (EditText) findViewById(R.id.billAmount);
        String billAmount = et.getText().toString();
        et = (EditText) findViewById(R.id.dueDate);
        String dueDate = et.getText().toString();
        Spinner spinner = (Spinner) findViewById(R.id.occurrenceSpinner);
        String occurrence = spinner.getSelectedItem().toString();

        //TODO: Convert inputs to correct types

        Toast.makeText(this,"BillName: "+billName+" BillAmount: "+billAmount+" DueDate:" +dueDate+" Occurrence: "+occurrence,Toast.LENGTH_LONG).show();




        Snackbar.make(view, "Added Bill", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
