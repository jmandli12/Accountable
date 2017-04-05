package com.cs506.accountable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cs506.accountable.dto.Account;
import com.cs506.accountable.dto.Bill;
import com.cs506.accountable.sqlite.DataSource;

import java.util.List;

public class Update_1_Activity extends AppCompatActivity {

    DataSource ds;
    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ds = new DataSource(Update_1_Activity.this);
        ds.open();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_1_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<Object> obj = ds.retrieveAll("account");
        account = (Account) obj.get(0);

        EditText account1Name = (EditText) findViewById(R.id.accountName1);

        account1Name.setText(account.getAccountName());
        EditText account1 = (EditText) findViewById(R.id.accountBalance1);

        String balance = String.valueOf(account.getBalance());
        if(balance.charAt(balance.length()-2) == '.') balance = balance.concat("0");
        account1.setText(String.valueOf(balance));
    }

    public void updateAccount(View view) {
        String accountName;
        String accountBalance;

        EditText et = (EditText) findViewById(R.id.accountName1);
        accountName = et.getText().toString();
        et = (EditText) findViewById(R.id.accountBalance1);
        accountBalance = et.getText().toString();




        boolean isValidAmount = accountBalance.matches("([0-9]|([1-9][0-9]+))\\.[0-9][0-9]");
        if (isValidAmount && accountName.length() > 0 && accountBalance.length() > 2) {
            //if(isValidAmount && accountBalance.length() > 2){
            //Toast.makeText(this, "Account Name: " + accountName + " Account Balance: " + accountBalance, Toast.LENGTH_LONG).show();

            //Save Bank Info
            String[] accountArgs = {"1", "1", accountName, accountBalance};
            //accountID, userID, accountName, accountBalance
            Account account = (Account) ds.create("account", accountArgs);

            // Move onto next Activity
            Intent intent = new Intent(this, Update_0_Activity.class);
            startActivity(intent);

        } else {
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
}
