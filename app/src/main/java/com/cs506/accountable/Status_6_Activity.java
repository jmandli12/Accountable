package com.cs506.accountable;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cs506.accountable.dto.Purchase;
import com.cs506.accountable.sqlite.DataSource;

import java.util.ArrayList;
import java.util.List;

public class Status_6_Activity extends AppCompatActivity {

    DataSource ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_6_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ds = new DataSource(Status_6_Activity.this);
        ds.open();

        String[] items = getItems();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, items);
        ListView lv = (ListView) findViewById(R.id.previousPurchasesListView);
        lv.setAdapter(adapter);
    }

    public String[] getItems() {

        String[] items;
        String purchaseString;
        List<String> list = new ArrayList<>();

        List<Object> purchases = ds.retrieveAll("purchase");

        for(Object tmp : purchases){
            Purchase purchase = (Purchase) tmp;
            String price = String.valueOf(purchase.getPrice());
            if(price.charAt(price.length()-2) == '.') price = price.concat("0");
            if (!purchase.getLocation().isEmpty()) {
                purchaseString = purchase.getLocation()+", $"+ price +"\n"+purchase.getDate()+", "+purchase.getTime()+"\n"+purchase.getCategory();
            }
            else {
                purchaseString = "(Unknown Location), $"+ price +"\n"+purchase.getDate()+", "+purchase.getTime()+"\n"+purchase.getCategory();
            }
            if (!purchase.getComment().isEmpty()){
                purchaseString += ", "+purchase.getComment();;
            }
            list.add(purchaseString);
        }

        String[] tmp = new String[list.size()];
        items = list.toArray(tmp);
        return items;
    }
}
