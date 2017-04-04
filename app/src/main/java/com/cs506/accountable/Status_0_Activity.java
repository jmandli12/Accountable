package com.cs506.accountable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cs506.accountable.dto.Account;
import com.cs506.accountable.dto.Bill;
import com.cs506.accountable.dto.Income;
import com.cs506.accountable.dto.User;
import com.cs506.accountable.sqlite.DataSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;


public class Status_0_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    DataSource ds;
    List<Bill> allBills;
    List<Income> allIncomes;
    List<Bill> validBills;
    List<Income> validIncomes;
    Account account;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ds = new DataSource(this);
        ds.open();

        setContentView(R.layout.activity_status_0_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.statusSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.status_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        TextView accountBalance = (TextView) findViewById(R.id.accountBalance);
        TextView incomeEarned = (TextView) findViewById(R.id.incomeEarned);
        TextView spendingAllowance = (TextView) findViewById(R.id.spendingAllowance);
        TextView amountSpent = (TextView) findViewById(R.id.amountSpent);
        TextView goalStatus = (TextView) findViewById(R.id.goalStatus);

        allBills = (List<Bill>)(List<?>) ds.retrieveAll("bill"); //TODO
        allIncomes = (List<Income>)(List<?>) ds.retrieveAll("income"); //TODO
        account = (Account) ds.retrieveById("account", "1");
        user = (User) ds.retrieveById("user", "1");
        String balance = String.valueOf(account.getBalance());
        if(balance.charAt(balance.length()-2) == '.') balance = balance.concat("0");
        accountBalance.setText(balance);
        incomeEarned.setText(todayIncome());
        spendingAllowance.setText(todayAllowance());
        amountSpent.setText(todaySpent());
        goalStatus.setText("0.00");

        ds.close();
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        ds = new DataSource(this);
        ds.open();

        TextView accountBalance = (TextView) findViewById(R.id.accountBalance);
        TextView incomeEarned = (TextView) findViewById(R.id.incomeEarned);
        TextView spendingAllowance = (TextView) findViewById(R.id.spendingAllowance);
        TextView amountSpent = (TextView) findViewById(R.id.amountSpent);
        TextView goalStatus = (TextView) findViewById(R.id.goalStatus);
        LinearLayout dates = (LinearLayout) findViewById(R.id.customDateLayout);

        String statusRange = (String) parent.getItemAtPosition(pos);
        if(statusRange.equals("Custom Date")){
            dates.setVisibility(View.VISIBLE);
        } else{
            dates.setVisibility(View.INVISIBLE);
        }
        //TODO: Use current selection to get values from database
        //Account account = (Account) ds.retrieveById("account", "1");
        String balance = String.valueOf(account.getBalance());
        if(balance.charAt(balance.length()-2) == '.') balance = balance.concat("0");
        accountBalance.setText(balance);
        switch (pos) {
            case 0: incomeEarned.setText(todayIncome());
                    spendingAllowance.setText(todayAllowance());
                    amountSpent.setText(todaySpent());
                    break;
            case 1: incomeEarned.setText(weeklyIncome());
                    spendingAllowance.setText(weeklyAllowance());
                    amountSpent.setText(weeklySpent());
                    break;
            case 2: incomeEarned.setText(monthlyIncome());
                    spendingAllowance.setText(monthlyAllowance());
                    amountSpent.setText(monthlySpent());
                    break;
            case 3: incomeEarned.setText(yearlyIncome());
                    spendingAllowance.setText(yearlyAllowance());
                    amountSpent.setText(yearlySpent());
                    break;
            default: break;
        }
        goalStatus.setText("0.00");

        ds.close();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    /*
    Move to Home View onClick of button
     */
    public void backToHome(View view) {

        Intent intent = new Intent(this, Status_6_Activity.class);
        startActivity(intent);
    }

    public String todayIncome() {
        Iterator<Income> iterateIncomes = allIncomes.iterator();
        validIncomes = new ArrayList<Income>();
        double sum = 0.0;
        String[] date;
        GregorianCalendar d = new GregorianCalendar();
        GregorianCalendar incomeDate;
        Income currIncome;
        while(iterateIncomes.hasNext()) {
            currIncome = iterateIncomes.next();
            date = currIncome.getDate().split("/");
            incomeDate = new GregorianCalendar(Integer.parseInt(date[2]),
                    Integer.parseInt(date[0]), Integer.parseInt(date[1]));
            switch (currIncome.getPayPeriod()) {
                case "Weekly": if(d.DAY_OF_WEEK == incomeDate.DAY_OF_WEEK) {
                                    sum += currIncome.getAmount();
                                }
                                break;
                case "BiWeekly": if(d.WEEK_OF_YEAR%2 == incomeDate.WEEK_OF_YEAR%2
                                    && d.DAY_OF_WEEK == incomeDate.DAY_OF_WEEK) {
                                    sum += currIncome.getAmount();
                                }
                                break;
                case "Monthly": if(d.DAY_OF_MONTH == incomeDate.DAY_OF_MONTH) {
                                    sum += currIncome.getAmount();
                                }
                                break;
                //TODO: What to do in case of Other???
                default: break;
            }
        }
        return String.valueOf(sum);
    }

    public String weeklyIncome() {
        return "void";
    }

    public String monthlyIncome() {
        return "void";
    }

    public String yearlyIncome() {
        return "void";
    }

    public String todayAllowance() {
        //Set the Daily Allowance Value
        //TextView dailyAllowance = (TextView) findViewById(R.id.dailyAllowance);
        //TODO: call method to get this value
        //Double amount = 100.45;
        //ds = new DataSource(this);
        //Account account = (Account) ds.retrieveById("account", "1"); //TODO
        Double currAmount = account.getBalance();
        Iterator<Bill> iterateBills = allBills.iterator();
        Iterator<Income> iterateIncomes = allIncomes.iterator();
        validBills = new ArrayList<Bill>();
        validIncomes = new ArrayList<Income>();

//        if(user.getBudget().equals("Weekly")) {
//            String[] date;
//            GregorianCalendar billDate;
//            GregorianCalendar d = new GregorianCalendar();
//            Bill currBill;
//            while (iterateBills.hasNext()) {
//                currBill = iterateBills.next();
//                date = currBill.getDueDate().split("/");
//                billDate = new GregorianCalendar(Integer.parseInt(date[2]),
//                        Integer.parseInt(date[0]), Integer.parseInt(date[1]));
//                billDate.add(GregorianCalendar.WEEK_OF_MONTH, 1);
//                if (d.getDay() < billDate.getDay()) {
//                    validBills.add(currBill);
//                }
//            }
//
//            Date incomeDate;
//            Income currIncome;
//            while (iterateIncomes.hasNext()) {
//                currIncome = iterateIncomes.next();
//                date = currIncome.getDate();
//                incomeDate = new Date(date);
//                if (d.getDay() < incomeDate.getDay()) {
//                    validIncomes.add(currIncome);
//                }
//            }
//
//            double billSum = 0.0;
//            double incomeSum = 0.0;
//            for (int i = 0; i < validBills.size(); i++) {
//                billSum += validBills.get(i).getBillAmount();
//            }
//            for (int j = 0; j < validIncomes.size(); j++) {
//                incomeSum += validIncomes.get(j).getAmount();
//            }
//
//            currAmount -= billSum;
//            currAmount += incomeSum;
//
//            currAmount = currAmount / (7 - d.getDay());
//
//            return currAmount.toString();
//            //dailyAllowance.setText("$"+amount);
//        }
//        else {
//            String[] date;
//            GregorianCalendar billDate;
//            GregorianCalendar d = new GregorianCalendar();
//            Bill currBill;
//            while(iterateBills.hasNext()) {
//                currBill = iterateBills.next();
//                date = currBill.getDueDate().split("/");
//                billDate = new GregorianCalendar(Integer.parseInt(date[2]),
//                        Integer.parseInt(date[0]), Integer.parseInt(date[1]));
//                billDate.add(GregorianCalendar.WEEK_OF_MONTH, 1);
//                if(d.getDay() < billDate.getDay()) {
//                    validBills.add(currBill);
//                }
//            }
//
//            Date incomeDate;
//            Income currIncome;
//            while(iterateIncomes.hasNext()) {
//                currIncome = iterateIncomes.next();
//                date = currIncome.getDate();
//                incomeDate = new Date(date);
//                if(d.getDay() < incomeDate.getDay()) {
//                    validIncomes.add(currIncome);
//                }
//            }
//
//            double billSum = 0.0;
//            double incomeSum = 0.0;
//            for (int i = 0; i < validBills.size(); i++) {
//                billSum += validBills.get(i).getBillAmount();
//            }
//            for (int j = 0; j < validIncomes.size(); j++) {
//                incomeSum += validIncomes.get(j).getAmount();
//            }
//
//            currAmount -= billSum;
//            currAmount += incomeSum;
//
//            currAmount = currAmount/(7-d.getDay());
//
//            return currAmount.toString();
//            //dailyAllowance.setText("$"+amount);
//        }
        return "void";
    }

    public String weeklyAllowance() {
        return "void";
    }

    public String monthlyAllowance() {
        return "void";
    }

    public String yearlyAllowance() {
        return "void";
    }

    public String todaySpent() {
        return "void";
    }

    public String weeklySpent() {
        return "void";
    }

    public String monthlySpent() {
        return "void";
    }

    public String yearlySpent() {
        return "void";
    }
}
