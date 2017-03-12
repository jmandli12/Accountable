package com.cs506.accountable;

        import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Setup_0_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_0_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable Setup");
        setSupportActionBar(toolbar);

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void buttonClickHandler(View view) {

        EditText et = (EditText) findViewById(R.id.firstPin);

        //TODO: change pin to int and save PIN
        String pinString = et.getText().toString();
        // Save the Entered PIN
        Toast.makeText(this, "PIN: " + pinString, Toast.LENGTH_LONG).show();

        // Move to next Screen
        Intent intent = new Intent(this, Setup_1_Activity.class);
        startActivity(intent);
    }
}

