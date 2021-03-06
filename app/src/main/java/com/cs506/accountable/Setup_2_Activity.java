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

import com.cs506.accountable.dto.Account;
import com.cs506.accountable.sqlite.DataSource;
import com.cs506.accountable.dto.User;

public class Setup_2_Activity extends AppCompatActivity {
    DataSource ds;
    User user;
    String pin = "noPin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ds = new DataSource(this);
        ds.open();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_2_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable Setup");
        setSupportActionBar(toolbar);
        Bundle prev = getIntent().getExtras();
        if (prev != null) pin = prev.getString("pin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //String[] userArgs = {"1", "user", "0", pin, "", "1", "", "1"};
        //user = (User) ds.create("user", userArgs);
    }

    /*
    In charge of saving bank info and for now moving to next screen.
     */
    public void addAccount(View view) {

        String accountName;
        String accountBalance;

        EditText et = (EditText) findViewById(R.id.accountName);
        accountName = et.getText().toString();
        et = (EditText) findViewById(R.id.accountBalance);
        accountBalance = et.getText().toString();
        boolean isValidAmount = accountBalance.matches("([0-9]|([1-9][0-9]+))\\.[0-9][0-9]");
        if (isValidAmount && accountName.length() > 0 && accountBalance.length() > 2) {
            Toast.makeText(this, "Account Name: " + accountName + " Account Balance: " + accountBalance, Toast.LENGTH_LONG).show();

            //Save Bank Info
            String[] accountArgs = {"1", "1", accountName, accountBalance, accountBalance};
            //accountID, userID, accountName, accountBalance
            Account account = (Account) ds.create("account", accountArgs);
            //Snackbar.make(view,account.getAccountName(), Snackbar.LENGTH_INDEFINITE)
              //      .setAction("Action", null).show();

            // Move onto next Activity
            Intent intent = new Intent(this, Setup_3_Activity.class);
            intent.putExtra("accountID", "1");
            intent.putExtra("pin", pin);
            startActivity(intent);

        }
        else {
            if (accountName.length() == 0) {
                Toast.makeText(this, "Account Name cannot be empty", Toast.LENGTH_LONG).show();
            }
            //TODO: ALLOW USER TO START WITH NEGATIVE BALANCE
            if (accountBalance.length() < 3 || !isValidAmount) {
                Toast.makeText(this, "Account Balance must be in the format \"{dollars}.{cents}\"", Toast.LENGTH_LONG).show();
                //TODO:Check for invalid leading 0 in dollar side?
            }
        }
    }

    public void accountHelp(View view) {
        switch (view.getId()) {
            case R.id.accountNameHelp:
                Snackbar.make(view, "Name of your account.(Savings,Checking etc.) \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", null).show();
                break;
            case R.id.accountBalanceHelp:
                Snackbar.make(view, "How much money is in your account \n(Swipe to Dismiss)", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", null).show();
                break;
        }
    }
}
