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

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PINHASH = "pin_hash";
    public static final String COLUMN_PIN = "pin";
    public static final String COLUMN_SALT = "salt";
    public static final String COLUMN_FIRSTTIME = "first_time";
    public static final String COLUMN_BUDGET = "budget";
    public static final String COLUMN_HASPIN = "has_pin";

    public static final String TABLE_BILLS = "bills";
    public static final String COLUMN_BILLID = "bill_id";
    public static final String COLUMN_BILLNAME = "bill_name";
    //public static final String COLUMN_USERID = "user_id";
    //public static final String COLUMN_ACCOUNTID = "account_id";
    public static final String COLUMN_BILLAMT = "bill_amt";
    public static final String COLUMN_DUEDTE = "due_dte";
    public static final String COLUMN_OCCURANCERTE = "occurance_rte";

    public static final String TABLE_ACCOUNTS = "accounts";
    //public static final String COLUMN_ACCOUNTID = "account_id";
    //public static final String COLUMN_USERID = "user_id";
    public static final String COLUMN_ACCOUNTNAME = "account_name";
    public static final String COLUMN_BALANCE = "balance";

    public static final String TABLE_INCOMES = "incomes";
    public static final String COLUMN_INCOMEID = "income_id";
    //public static final String COLUMN_USERID = "user_id";
    public static final String COLUMN_ACCOUNTID = "account_id";
    public static final String COLUMN_INCOMENAME = "income_name";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_PAYPERIOD = "pay_period";
    public static final String COLUMN_HOURS = "hours";

    public static final String TABLE_PURCHASES = "purchases";
    public static final String COLUMN_PURCHASEID = "purchase_id";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_DATETIME = "datetime";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_COMMENT = "comment";

    private static final String DATABASE_NAME = "accountable.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE_COMMENTS = "create table "
            + TABLE_COMMENTS + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_COMMENT + " text not null)";

    private static final String DATABASE_CREATE_USERS = " create table "
            + TABLE_USERS + "( "
            + COLUMN_USERID + " integer" /*+ " primary key autoincrement, "*/
            + COLUMN_USERNAME + " text, "
            + COLUMN_PINHASH + " integer, "
            + COLUMN_PIN + " integer, "
            + COLUMN_SALT +" integer, "
            + COLUMN_FIRSTTIME + " boolean, "
            + COLUMN_BUDGET + " text, "
            + COLUMN_HASPIN + " boolean)";

    private static final String DATABASE_CREATE_BILLS =  " create table "
           + TABLE_BILLS + "( "
            + COLUMN_BILLID + "text, "
            + COLUMN_BILLNAME + " text, "
            + COLUMN_USERID + " integer references user_id,"
            + COLUMN_ACCOUNTID + " integer references account_id,"
            + COLUMN_BILLAMT + " integer, "
            + COLUMN_DUEDTE + " date, "
            + COLUMN_OCCURANCERTE + " integer" + ")";

    private static final String DATABASE_CREATE_ACCOUNTS =  " create table "
            + TABLE_ACCOUNTS + "("
            + COLUMN_ACCOUNTID + "integer, "
            + COLUMN_USERID + " integer references user_id,"
            + COLUMN_ACCOUNTNAME + " text,"
            + COLUMN_BALANCE + " double)";

    private static final String DATABASE_CREATE_INCOMES =  " create table "
            + TABLE_INCOMES + "("
            + COLUMN_INCOMEID + /*"integer primary key autoincrement, "*/"text ,"
            + COLUMN_USERID + " integer references user_id,"
            + COLUMN_ACCOUNTID + " integer references account_id, "
            + COLUMN_INCOMENAME + " text, "
            + COLUMN_PAYPERIOD + " text, "
            + COLUMN_HOURS + " double)";

    private static final String DATABASE_CREATE_PURCHASES =  " create table "
            + TABLE_PURCHASES + "("
            + COLUMN_PURCHASEID + "text , "
            + COLUMN_USERID + " integer references user_id,"
            + COLUMN_ACCOUNTID + " integer references account_id, "
            + COLUMN_PRICE + " double, "
            + COLUMN_DATETIME + " text, "
            + COLUMN_CATEGORY + " text, "
            + COLUMN_LOCATION + " text, "
            + COLUMN_COMMENT + " text)";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_COMMENTS);
        database.execSQL(DATABASE_CREATE_USERS);
        database.execSQL(DATABASE_CREATE_BILLS);
        database.execSQL(DATABASE_CREATE_ACCOUNTS);
        database.execSQL(DATABASE_CREATE_INCOMES);
        database.execSQL(DATABASE_CREATE_PURCHASES);
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
