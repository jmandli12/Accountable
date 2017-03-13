package com.cs506.accountable;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;

        import java.util.List;
        import java.util.Random;

        import android.app.ListActivity;
        import android.widget.ArrayAdapter;

        import com.cs506.accountable.R;
        import com.cs506.accountable.dto.Comment;

        import com.cs506.accountable.sqlite.CommentsDataSource;
        import com.cs506.accountable.sqlite.TestDatabaseActivity;

public class Main_Activity extends AppCompatActivity {

    TestDatabaseActivity db;

            // Will be called via the onClick attribute
            // of the buttons in main.xml
            public void onClick (View view){
                db.onClick(view);
            }

            @Override
            protected void onResume () {
                db.datasource.open();
                super.onResume();
            }

            @Override
            protected void onPause () {
                db.datasource.close();
                super.onPause();
            }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            //getMenuInflater().inflate(R.menu.menu_settings, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            // if (id == R.id.action_settings) {
            //     return true;
            // }

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

        /*
        TEMP will be removed before end of iteration 1
         */
        public void lockScreen(View view) {
            Intent intent = new Intent(this, Lock_Screen_Activity.class);
            startActivity(intent);
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accountable");
        setSupportActionBar(toolbar);

        db = new TestDatabaseActivity();
    }
}
