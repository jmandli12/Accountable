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

import com.cs506.accountable.dto.User;
import com.cs506.accountable.sqlite.DataSource;

import java.util.List;


public class Main_Activity extends AppCompatActivity {

    DataSource ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ds = new DataSource(Main_Activity.this);
        ds.open();

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
        } else if (id == R.id.about_settings){
            Intent intent = new Intent(this, About_Activity.class);
            startActivity(intent);
        } else if (id == R.id.changePIN_settings){
            List<Object> obj = ds.retrieveAll("user");
            User user = (User) obj.get(0);
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

}
