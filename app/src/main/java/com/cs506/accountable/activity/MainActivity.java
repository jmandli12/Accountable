package com.cs506.accountable.activity;

import android.os.Bundle;

import com.cs506.accountable.R;
import com.cs506.accountable.sqlite.TestDatabaseActivity;

public class MainActivity extends TestDatabaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
