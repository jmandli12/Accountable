package com.cs506.accountable;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cs506.accountable.dto.Account;
import com.cs506.accountable.dto.Bill;
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


public class Main_Activity extends AppCompatActivity {
    private final long TWO_WEEKS = 1209600000L;
    private final long HOUR = 3600000L;
    DataSource ds;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ds = new DataSource(this);
        ds.open();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable");
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.gear);
        toolbar.setOverflowIcon(drawable);
        setSupportActionBar(toolbar);
        user = (User) ds.retrieveById("user", "1");
        String[] lastSync = user.getLastSync().split("/");
        GregorianCalendar ls = new GregorianCalendar(Integer.parseInt(lastSync[2]),
                Integer.parseInt(lastSync[0])-1, Integer.parseInt(lastSync[1]));
        GregorianCalendar temp = new GregorianCalendar();
        GregorianCalendar today = new GregorianCalendar(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH),
                temp.get(Calendar.DAY_OF_MONTH));
        if(ls.before(today)) {
            syncBalance();
        }
        ds.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Update_0_Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.about_settings){
            Intent intent = new Intent(this, About_Activity.class);
            startActivity(intent);
        } else if (id == R.id.changePIN_settings){
            if(user.getHasPin() == 1){
                Intent intent = new Intent(this, Lock_Screen_Activity.class);
                intent.putExtra("changePIN", "YES");
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, Setup_0_Activity.class);
                intent.putExtra("changePIN", "YES");
                startActivity(intent);
            }

        }

        return super.onOptionsItemSelected(item);
    }

    /*
    Moves to Purchase Activity
     */
    public void moveToPurchase(View view) {
        Intent intent1 = new Intent(this, Status_0_Activity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); //Load Status Activity to calculate allowance
        intent1.putExtra("forPurchase", true);
        startActivity(intent1);
    }

    /*
    Moves to Purchase Activity
     */
    public void moveToStatus(View view) {
        Intent intent = new Intent(this, Status_0_Activity.class);
        intent.putExtra("forPurchase", false);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        return;
    }

    public void syncBalance() {
        int sum = 0;
        List<Bill> allBills = (List<Bill>)(List<?>) ds.retrieveAll("bill");
        Iterator<Bill> iterateBills = allBills.iterator();
        List<Bill> validBills = new ArrayList<Bill>();
        String[] date;
        String[] lastSync = user.getLastSync().split("/");
        //FINISH THIS
        GregorianCalendar temp = new GregorianCalendar();
        GregorianCalendar today = new GregorianCalendar(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH),
                temp.get(Calendar.DAY_OF_MONTH));
        GregorianCalendar ls = new GregorianCalendar(Integer.parseInt(lastSync[2]),
                Integer.parseInt(lastSync[0])-1, Integer.parseInt(lastSync[1]));
        GregorianCalendar billDate;
        Bill currBill;
        while(iterateBills.hasNext()) {
            temp = (GregorianCalendar)ls.clone();
            currBill = iterateBills.next();
            date = currBill.getDueDate().split("/");
            billDate = new GregorianCalendar(Integer.parseInt(date[2]),
                    Integer.parseInt(date[0])-1, Integer.parseInt(date[1]));
            GregorianCalendar leapDay = new GregorianCalendar(billDate.get(Calendar.YEAR), 02, 29);

            switch (currBill.getOccurrenceRte()) {
                case 1:
                    while(!billDate.after(temp)) {
                        billDate.add(Calendar.WEEK_OF_YEAR, 1);
                    }
                    while(temp.before(billDate) && temp.before(today)) {
                        temp.add(Calendar.DAY_OF_YEAR, 1);
                    }
                    if(temp.get(Calendar.DAY_OF_WEEK) == billDate.get(Calendar.DAY_OF_WEEK)) {
                        while(!temp.after(today)) {
                            sum += (int) (currBill.getBillAmount() * 100.0);
                            temp.add(Calendar.WEEK_OF_YEAR, 1);
                        }
                    }
                    break;
                case 2:
                    while(!billDate.after(temp)) {
                        billDate.add(Calendar.MONTH, 1);
                    }
                    while(temp.before(billDate) && temp.before(today)) {
                        temp.add(Calendar.DAY_OF_YEAR, 1);
                    }
                    if(temp.get(Calendar.DAY_OF_MONTH) == billDate.get(Calendar.DAY_OF_MONTH)) {
                        while (!temp.after(today)) {
                            sum += (int) (currBill.getBillAmount() * 100.0);
                            temp.add(Calendar.MONTH, 1);
                        }
                    }
                    break;
                case 3:
                    while(!billDate.after(temp)) {
                        billDate.add(Calendar.YEAR, 1);
                    }
                    while(temp.before(billDate) && temp.before(today)) {
                        temp.add(Calendar.DAY_OF_YEAR, 1);
                    }
                    int actualDate = billDate.get(Calendar.DAY_OF_YEAR);
                    if (billDate.isLeapYear(billDate.get(Calendar.YEAR)) && billDate.after(leapDay)){
                        actualDate--;
                    }
                    if(temp.get(Calendar.DAY_OF_YEAR) == actualDate) {
                        while (!temp.after(today)) {
                            sum += (int) (currBill.getBillAmount() * 100.0);
                            temp.roll(Calendar.YEAR, 1);
                        }
                    }
                    break;
                default: break;
            }
        }
        List<Income> allIncomes = (List<Income>)(List<?>) ds.retrieveAll("income");
        Iterator<Income> iterateIncomes = allIncomes.iterator();
        List<Income> validIncomes = new ArrayList<Income>();
        GregorianCalendar incomeDate;
        Income currIncome;
        while(iterateIncomes.hasNext()) {
            temp = (GregorianCalendar)ls.clone();
            currIncome = iterateIncomes.next();
            date = currIncome.getDate().split("/");
            incomeDate = new GregorianCalendar(Integer.parseInt(date[2]),
                    Integer.parseInt(date[0])-1, Integer.parseInt(date[1]));
            GregorianCalendar leapDay = new GregorianCalendar(incomeDate.get(Calendar.YEAR), 02, 29);

            switch (currIncome.getPayPeriod()) {
                case "Weekly":
                    while(!incomeDate.after(temp)) {
                        incomeDate.add(Calendar.WEEK_OF_YEAR, 1);
                    }
                    while(temp.before(incomeDate) && temp.before(today)) {
                        temp.add(Calendar.DAY_OF_YEAR, 1);
                    }
                    if(temp.get(Calendar.DAY_OF_WEEK) == incomeDate.get(Calendar.DAY_OF_WEEK)) {
                        while(!temp.after(today)) {
                            sum -= (int) (currIncome.getAmount() * 100.00);
                            temp.add(Calendar.WEEK_OF_YEAR, 1);
                        }
                    }
                    break;
                case "BiWeekly":
                    while(!incomeDate.after(temp)) {
                        incomeDate.add(Calendar.WEEK_OF_YEAR, 2);
                    }
                    while(temp.before(incomeDate) && temp.before(today)) {
                        temp.add(Calendar.DAY_OF_YEAR, 1);
                    }
                    int tDay = temp.get(Calendar.DAY_OF_WEEK);
                    int iDay = incomeDate.get(Calendar.DAY_OF_WEEK);
                    long tMill = temp.getTimeInMillis();
                    long iMill = incomeDate.getTimeInMillis();
                    long diff = tMill - iMill;
                    long quot = diff%TWO_WEEKS;
                    // Check for extra hour in case of daylight savings
                    if(tDay == iDay && (quot == 0 || quot == HOUR || quot == HOUR * 335)) {
                        while(!temp.after(today)) {
                            sum -= (int) (currIncome.getAmount() * 100.00);
                            temp.add(Calendar.WEEK_OF_YEAR, 2);
                        }
                    }
                    break;
                case "Monthly":
                    while(!incomeDate.after(temp)) {
                        incomeDate.add(Calendar.MONTH, 1);
                    }
                    while(temp.before(incomeDate) && temp.before(today)) {
                        temp.add(Calendar.DAY_OF_YEAR, 1);
                    }
                    if(temp.get(Calendar.DAY_OF_MONTH) == incomeDate.get(Calendar.DAY_OF_MONTH)) {
                        while (!temp.after(today)) {
                            sum -= (int) (currIncome.getAmount() * 100.00);
                            temp.add(Calendar.MONTH, 1);
                        }
                    }
                    break;
                default: break;
            }
        }
        Account account = (Account) ds.retrieveById("account", "1");
        double balance = account.getBalance();
        int intBalance = (int) (balance * 100.0);
        account.setBalance((double) (intBalance - sum) / 100);
        String[] newAccountArgs = {"1", "1", account.getAccountName(), String.valueOf(account.getBalance()), String.valueOf(account.getBalance())};
        ds.create("account", newAccountArgs);
        Date d = new Date();
        String now = (String) DateFormat.format("MM/dd/yyyy", d.getTime());
        //userID, userName, pinHash, pin, salt, firstTime(now it is false), budget, hasPin
        String[] newUserArgs = {"1", "User", "0", String.valueOf(user.getPin()), "0", "0",
                String.valueOf(user.getHasPin()), now, user.getLastCalc(), String.valueOf(user.getAllowance())};
        ds.create("user", newUserArgs);
    }
}
