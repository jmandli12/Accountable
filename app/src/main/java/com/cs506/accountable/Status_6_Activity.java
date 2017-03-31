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

        //Sample data that should be removed when database is up and running
        list.add("Subway, $6.00\n03/02/2018 09:22 PM\nFood: Footlong");
        list.add("Grocery Store, $60.00\n03/02/2018 09:22 PM\nGroceries: Lots of stuff including beer and stuff so this makes it a very long comment for the sake of testing to make sure the list works the way it is supposed to");
        list.add("Subway, $10.00\n03/02/2018 09:22 PM\nFood: Footlong and Drink");
        list.add("State Street, $6000.00\n03/02/2018 09:22 PM\nSocial: Fun Night");
        list.add("Union South, $6.00\n03/02/2018 09:22 PM\nFood: lunch");
        list.add("Memorial Union Terrace, $60.00\n03/02/2018 09:22 PM\nSocial: A few pitchers of beer");

        List<Object> purchases = ds.retrieveAll("purchase");

        for(Object tmp : purchases){
            Purchase purchase = (Purchase) tmp;
            purchaseString = "";
            purchaseString = purchase.getLocation()+", $"+purchase.getPrice()+"\n"+purchase.getDate()+", "+purchase.getTime()+"\n"+purchase.getCategory()+", "+purchase.getComment();
            list.add(purchaseString);
        }

        String[] tmp = new String[list.size()];
        items = list.toArray(tmp);//getResources().getStringArray(R.array.category_array);
        return items;
    }
}
