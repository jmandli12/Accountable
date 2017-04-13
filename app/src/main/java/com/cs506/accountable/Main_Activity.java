package com.cs506.accountable;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cs506.accountable.dto.Bill;
import com.cs506.accountable.dto.Purchase;
import com.cs506.accountable.sqlite.DataSource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;


public class Main_Activity extends AppCompatActivity {
    DataSource ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable");
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.gear);
        toolbar.setOverflowIcon(drawable);
        setSupportActionBar(toolbar);

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
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    Moves to Purchase Activity
     */
    public void moveToPurchase(View view) {
        Intent intent = new Intent(this, Purchase_0_Activity.class);
        startActivity(intent);
    }

    /*
    Moves to Purchase Activity
     */
    public void moveToStatus(View view) {
        Intent intent = new Intent(this, Status_0_Activity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        return;
    }

//    public String syncBalance() {
//        ds = new DataSource(this);
//        ds.open();
//        List<Bill> allBills = (List<Bill>)(List<?>) ds.retrieveAll("bill");
//        List<Purchase> allPurchases = (List<Purchase>)(List<?>) ds.retrieveAll("purchase");
//        Iterator<Bill> iterateBills = allBills.iterator();
//        List<Bill> validBills = new ArrayList<Bill>();
//        double sum = 0.0;
//        String[] date;
//        String[] lastSync;
//        //FINISH THIS
//        GregorianCalendar temp = new GregorianCalendar();
//        GregorianCalendar d = new GregorianCalendar(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH),
//                temp.get(Calendar.DAY_OF_MONTH));
//        GregorianCalendar billDate;
//        Bill currBill;
//        while(iterateBills.hasNext()) {
//            currBill = iterateBills.next();
//            date = currBill.getDueDate().split("/");
//            billDate = new GregorianCalendar(Integer.parseInt(date[2]),
//                    Integer.parseInt(date[0])-1, Integer.parseInt(date[1]));
//            GregorianCalendar leapDay = new GregorianCalendar(billDate.get(Calendar.YEAR), 02, 29);
//            switch (currBill.getOccurrenceRte()) {
//                case 1:
//                    if(d.get(Calendar.DAY_OF_WEEK) == billDate.get(Calendar.DAY_OF_WEEK)) {
//                        sum += currBill.getBillAmount();
//                    }
//                    break;
//                case 2:
//                    if(d.get(Calendar.DAY_OF_MONTH) == billDate.get(Calendar.DAY_OF_MONTH)) {
//                        sum += currBill.getBillAmount();
//                    }
//                    break;
//                case 3:
//                    int actualDate = billDate.get(Calendar.DAY_OF_YEAR);
//                    if (billDate.isLeapYear(billDate.get(Calendar.YEAR)) && billDate.after(leapDay)){
//                        actualDate--;
//                    }
//                    if(d.get(Calendar.DAY_OF_YEAR) == actualDate) {
//                        sum += currBill.getBillAmount();
//                    }
//                    break;
//                default: break;
//            }
//        }
//
//        Iterator<Purchase> iteratePurchases = allPurchases.iterator();
//        List<Purchase> validPurchases = new ArrayList<Purchase>();
//
//        GregorianCalendar purchaseDate;
//        Purchase currPurchase;
//        while(iteratePurchases.hasNext()) {
//            currPurchase = iteratePurchases.next();
//            date = currPurchase.getDate().split("/");
//            purchaseDate = new GregorianCalendar(Integer.parseInt(date[2]),
//                    Integer.parseInt(date[0])-1, Integer.parseInt(date[1]));
//            if(d.equals(purchaseDate)) {
//                sum += currPurchase.getPrice();
//            }
//        }
//
//        ds.close();
//    }
}
