package com.cs506.accountable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
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
import com.cs506.accountable.dto.Goal;
import com.cs506.accountable.dto.Income;
import com.cs506.accountable.dto.Purchase;
import com.cs506.accountable.dto.User;
import com.cs506.accountable.sqlite.DataSource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;


public class Status_0_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private final long TWO_WEEKS = 1209600000L;
    private final long HOUR = 3600000L;
    DataSource ds;
    List<Bill> allBills;
    List<Income> allIncomes;
    List<Purchase> allPurchases;
    List<Bill> validBills;
    List<Income> validIncomes;
    List<Purchase> validPurchases;
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
        allPurchases = (List<Purchase>)(List<?>) ds.retrieveAll("purchase");
        account = (Account) ds.retrieveById("account", "1");
        user = (User) ds.retrieveById("user", "1");
        accountBalance.setText("$" + formatDoubleMoney(account.getBalance()));
        incomeEarned.setText("$" + todayIncome());
        if(!user.getLastCalc().isEmpty()) {
            String[] lastCalc = user.getLastCalc().split("/");
            GregorianCalendar lc = new GregorianCalendar(Integer.parseInt(lastCalc[2]),
                    Integer.parseInt(lastCalc[0])-1, Integer.parseInt(lastCalc[1]));
            GregorianCalendar temp = new GregorianCalendar();
            GregorianCalendar today = new GregorianCalendar(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH),
                    temp.get(Calendar.DAY_OF_MONTH));
            if(lc.before(today)) {
                spendingAllowance.setText("$" + todayAllowance());
            }
            else {
                spendingAllowance.setText("$" + formatDoubleMoney(user.getAllowance()));
            }
        }
        else {
            spendingAllowance.setText("$" + todayAllowance());
        }
        amountSpent.setText("$" + todaySpent());
        goalStatus.setText("$" + "0.00");

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

        accountBalance.setText("$" + formatDoubleMoney(account.getBalance()));
        switch (pos) {
            case 0: incomeEarned.setText("$" + todayIncome());
                    spendingAllowance.setText("$" + todayAllowance());
                    amountSpent.setText("$" + todaySpent());
                    break;
            case 1: incomeEarned.setText("$" + weeklyIncome());
                    spendingAllowance.setText("$" + weeklyAllowance());
                    amountSpent.setText("$" + weeklySpent());
                    break;
            case 2: incomeEarned.setText("$" + monthlyIncome());
                    spendingAllowance.setText("$" + monthlyAllowance());
                    amountSpent.setText("$" + monthlySpent());
                    break;
            case 3: incomeEarned.setText("$" + yearlyIncome());
                    spendingAllowance.setText("$" + yearlyAllowance());
                    amountSpent.setText("$" + yearlySpent());
                    break;
            default: break;
        }
        goalStatus.setText("$" + goalStatus());

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
        return todayIncome(new GregorianCalendar());
    }

    public String todayIncome(GregorianCalendar temp) {
        Iterator<Income> iterateIncomes = allIncomes.iterator();
        validIncomes = new ArrayList<Income>();
        int sum = 0;
        String[] date;
        GregorianCalendar d = new GregorianCalendar(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH),
                    temp.get(Calendar.DAY_OF_MONTH));
        GregorianCalendar incomeDate;
        Income currIncome;
        while(iterateIncomes.hasNext()) {
            currIncome = iterateIncomes.next();
            date = currIncome.getDate().split("/");
            incomeDate = new GregorianCalendar(Integer.parseInt(date[2]),
                    Integer.parseInt(date[0])-1, Integer.parseInt(date[1]));
            switch (currIncome.getPayPeriod()) {
                case "Weekly":
                                if(d.get(Calendar.DAY_OF_WEEK) == incomeDate.get(Calendar.DAY_OF_WEEK)) {
                                    sum += (int) (currIncome.getAmount() * 100.0);
                                }
                                break;
                case "BiWeekly": int dDay = d.get(Calendar.DAY_OF_WEEK);
                                int iDay = incomeDate.get(Calendar.DAY_OF_WEEK);
                                long dMill = d.getTimeInMillis();
                                long iMill = incomeDate.getTimeInMillis();
                                long diff = dMill - iMill;
                                long quot = diff%TWO_WEEKS;
                                // Check for extra hour in case of daylight savings
                                if(dDay == iDay && (quot == 0 || quot == HOUR || quot == HOUR * 335)) {
                                    sum += (int) (currIncome.getAmount() * 100.0);
                                }
                                break;
                case "Monthly": if(d.get(Calendar.DAY_OF_MONTH) == incomeDate.get(Calendar.DAY_OF_MONTH)) {
                                    sum += (int) (currIncome.getAmount() * 100.0);
                                }
                                break;
                //TODO: What to do in case of Other???
                default: break;
            }
        }

        return formatDoubleMoney((double) sum / 100);
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

        int accountBalance = (int) (account.getBalance() * 100.0);
        int allowance = accountBalance;
        Goal dollar = null;
        Goal income = null;
        Goal savings = null;
        boolean monthly = false;
        boolean yearly = false;
        double incomePercent = 1.00;

        List<Goal> allGoals = (List<Goal>) (List<?>) ds.retrieveAll("goal");
        Iterator<Goal> iterator = allGoals.iterator();
        while(iterator.hasNext()) {
            Goal currGoal = iterator.next();
            switch (currGoal.getUnit()) {
                case 1: dollar = currGoal;
                    break;
                case 2: income = currGoal;
                    break;
                case 3: savings = currGoal;
                    break;
                default: break;
            }
            if(currGoal.getTimePeriod() == 4) yearly = true;
            else if(currGoal.getTimePeriod() == 3) monthly = true;

        }

        GregorianCalendar temp = new GregorianCalendar();
        GregorianCalendar d = new GregorianCalendar(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH),
                temp.get(Calendar.DAY_OF_MONTH));
        GregorianCalendar end = (GregorianCalendar) d.clone();
        if(yearly) {
            end.set(end.get(Calendar.YEAR), Calendar.JANUARY, 1);
            end.roll(Calendar.YEAR, 1);
        }
        else if(monthly) {
            end.set(end.get(Calendar.YEAR), end.get(Calendar.MONTH), 1);
            end.roll(Calendar.MONTH, 1);
        }
        else {
            end.add(GregorianCalendar.WEEK_OF_YEAR, 2);
            end.add(GregorianCalendar.DAY_OF_WEEK, -end.get(Calendar.DAY_OF_WEEK));
        }

        if(income != null) {
            if(income.getAmount() != 0.0) {
                incomePercent = ((100.0 - income.getAmount()) / 100.0);
            }
        }

        d.add(GregorianCalendar.DAY_OF_YEAR, 1);
        while(d.before(end)) {
            if(dollar != null) {
                switch (dollar.getTimePeriod()) {
                    case 1: allowance -= (int) (dollar.getAmount() * 100.0);
                            break;
                    case 2: if(d.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                                allowance -= (int) (dollar.getAmount() * 100.0);
                            }
                            break;
                    case 3: int monthDay = d.get(Calendar.DAY_OF_MONTH);
                            if(monthDay == 1) {
                                allowance -= (int) (dollar.getAmount() * 100.0);
                            }
                            break;
                    case 4: int yearDay = d.get(Calendar.DAY_OF_YEAR);
                            if(yearDay == 1) {
                                allowance -= (int) (dollar.getAmount() * 100.0);
                            }
                            break;
                    default: break;
                }
            }
            allowance -= (int) (Double.parseDouble(todaySpent(d)) * 100.0);
            allowance += (int) (Math.floor(Double.parseDouble(todayIncome(d))*incomePercent*100.0));
            d.add(GregorianCalendar.DAY_OF_YEAR, 1);
        }

        d = new GregorianCalendar(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH),
                temp.get(Calendar.DAY_OF_MONTH));
        long span = end.getTimeInMillis() - d.getTimeInMillis();
        int daySpan = (int) (span / (long)(1000*60*60*24));
        allowance = allowance / daySpan; //TODO: POSSIBLY OPTIMIZE THIS
        String now = (String) DateFormat.format("MM/dd/yyyy", d.getTime());
        user.setLastCalc(now);
        user.setAllowance((double) allowance / 100);

        String[] newUserArgs = {"1", "User", "0", String.valueOf(user.getPin()), "0", "0",
                user.getBudget(), String.valueOf(user.getHasPin()), user.getLastSync(),
                user.getLastCalc(), String.valueOf(user.getAllowance())};
        ds.create("user", newUserArgs);

        return formatDoubleMoney((double) allowance / 100);
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
        return todaySpent(new GregorianCalendar());
    }

    public String todaySpent(GregorianCalendar temp) {
        Iterator<Bill> iterateBills = allBills.iterator();
        validBills = new ArrayList<Bill>();
        int sum = 0;
        String[] date;

        GregorianCalendar d = new GregorianCalendar(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH),
                temp.get(Calendar.DAY_OF_MONTH));
        GregorianCalendar billDate;
        Bill currBill;
        while(iterateBills.hasNext()) {
            currBill = iterateBills.next();
            date = currBill.getDueDate().split("/");
            billDate = new GregorianCalendar(Integer.parseInt(date[2]),
                    Integer.parseInt(date[0])-1, Integer.parseInt(date[1]));
            GregorianCalendar leapDay = new GregorianCalendar(billDate.get(Calendar.YEAR), 02, 29);
            switch (currBill.getOccurrenceRte()) {
                case 1:
                    if(d.get(Calendar.DAY_OF_WEEK) == billDate.get(Calendar.DAY_OF_WEEK)) {
                        sum += (int) (currBill.getBillAmount() * 100.0);
                    }
                    break;
                case 2:
                    if(d.get(Calendar.DAY_OF_MONTH) == billDate.get(Calendar.DAY_OF_MONTH)) {
                        sum += (int) (currBill.getBillAmount() * 100.0);
                    }
                    break;
                case 3:
                    int actualDate = billDate.get(Calendar.DAY_OF_YEAR);
                    if (billDate.isLeapYear(billDate.get(Calendar.YEAR)) && billDate.after(leapDay)){
                        actualDate--;
                    }
                    if(d.get(Calendar.DAY_OF_YEAR) == actualDate) {
                        sum += (int) (currBill.getBillAmount() * 100.0);
                    }
                    break;
                default: break;
            }
        }

        Iterator<Purchase> iteratePurchases = allPurchases.iterator();
        validPurchases = new ArrayList<Purchase>();

        GregorianCalendar purchaseDate;
        Purchase currPurchase;
        while(iteratePurchases.hasNext()) {
            currPurchase = iteratePurchases.next();
            date = currPurchase.getDate().split("/");
            purchaseDate = new GregorianCalendar(Integer.parseInt(date[2]),
                    Integer.parseInt(date[0])-1, Integer.parseInt(date[1]));
            if(d.equals(purchaseDate)) {
                sum += (int) (currPurchase.getPrice() * 100.0);
            }
        }

        return formatDoubleMoney((double) sum / 100);
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

    public String goalStatus() {


        return formatDoubleMoney(0.0);
    }

    private String formatDoubleMoney(double i) {
        String s = String.valueOf(i);
        if(s.charAt(s.length()-2) == '.') s = s.concat("0");
        return s;
    }
}
