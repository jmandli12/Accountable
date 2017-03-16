package com.cs506.accountable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TextView;

import com.cs506.accountable.dto.Account;
import com.cs506.accountable.dto.Bill;
import com.cs506.accountable.dto.Income;
import com.cs506.accountable.dto.Purchase;
import com.cs506.accountable.sqlite.DataSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Status_0_Activity extends AppCompatActivity {
    DataSource ds;
    List<Bill> allBills;
    List<Income> allIncomes;
    List<Bill> validBills;
    List<Income> validIncomes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_0_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set the Daily Allowance Value
        TextView dailyAllowance = (TextView) findViewById(R.id.dailyAllowance);
        //TODO: call method to get this value
        //Double amount = 100.45;
        ds = new DataSource(this);
        Account account = ds.retrieveByID("account", "1");
        Double currAmount = account.getBalance();
        allBills = ds.retrieveAll("bill");
        allIncomes = ds.retrieveAll("income");
        Iterator<Bill> iterateBills = allBills.iterator();
        Iterator<Income> iterateIncomes = allIncomes.iterator();
        validBills = new ArrayList<Bill>();
        validIncomes = new ArrayList<Income>();

        String date;
        Date billDate;
        Date d = new Date();
        Bill currBill;
        while(iterateBills.hasNext()) {
            currBill = iterateBills.next();
            date = currBill.getDueDate();
            billDate = new Date(date);
            if(d.getDay() < billDate.getDay()) {
                validBills.add(currBill);
            }
        }

        Date incomeDate;
        Income currIncome;
        while(iterateIncomes.hasNext()) {
            currIncome = iterateIncomes.next();
            date = currIncome.getDate();
            incomeDate = new Date(date);
            if(d.getDay() < incomeDate.getDay()) {
                validIncomes.add(currIncome);
            }
        }

        double billSum = 0.0;
        double incomeSum = 0.0;
        for (int i = 0; i < validBills.size(); i++) {
            billSum += validBills.get(i).getBillAmount();
        }
        for (int j = 0; j < validIncomes.size(); j++) {
            incomeSum += validIncomes.get(j).getAmount();
        }

        currAmount -= billSum;
        currAmount += incomeSum;

        currAmount = currAmount/(7-d.getDay());

        String amount = currAmount.toString();
        dailyAllowance.setText("$"+amount);
    }

}
