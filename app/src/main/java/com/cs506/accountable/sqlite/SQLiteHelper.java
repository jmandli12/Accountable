package com.cs506.accountable.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by tkobl on 3/8/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COMMENTS = "comments";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COMMENT = "comment";

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_JOBID = "job_id";
    public static final String COLUMN_ACCOUNTID = "account_id";
    public static final String COLUMN_PINHASH = "pin_hash";
    public static final String COLUMN_PIN = "pin";
    public static final String COLUMN_SALT = "salt";
    public static final String COLUMN_FIRSTTIME = "first_time";

    private static final String DATABASE_NAME = "accountable.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_COMMENT
            + " text not null); create table " + TABLE_USERS + "( " + COLUMN_USERID + " integer"
            + " primary key autoincrement, " + COLUMN_USERNAME + " text, " + COLUMN_JOBID
            + " integer key autoincrement, " + COLUMN_ACCOUNTID + " integer key autoincrement, "
            + COLUMN_PINHASH + " integer, " + COLUMN_PIN + " integer, " + COLUMN_SALT +" integer, "
            + COLUMN_FIRSTTIME + " boolean);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

}
