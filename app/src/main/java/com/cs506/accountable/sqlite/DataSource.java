package com.cs506.accountable.sqlite;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.cs506.accountable.dto.Account;
import com.cs506.accountable.dto.Comment;
import com.cs506.accountable.dto.Income;
import com.cs506.accountable.dto.User;
import com.cs506.accountable.dto.Bill;
import com.cs506.accountable.dto.Purchase;

/**
 * Created by tkobl on 3/8/2017.
 */

public class DataSource {
    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = { SQLiteHelper.COLUMN_ID,
            SQLiteHelper.COLUMN_COMMENT };



    private String[] allColumnsAccount = {
        SQLiteHelper.COLUMN_USERID,
        SQLiteHelper.COLUMN_USERNAME,
        SQLiteHelper.COLUMN_PINHASH,
        SQLiteHelper.COLUMN_PIN,
        SQLiteHelper.COLUMN_SALT,
        SQLiteHelper.COLUMN_FIRSTTIME,
        SQLiteHelper.COLUMN_BUDGET
    };
    private String[] allColumnsBill = {
            SQLiteHelper.COLUMN_BILLID,
            SQLiteHelper.COLUMN_BILLNAME,
            SQLiteHelper.COLUMN_USERID,
            SQLiteHelper.COLUMN_ACCOUNTID,
            SQLiteHelper.COLUMN_BILLAMT,
            SQLiteHelper.COLUMN_DUEDTE,
            SQLiteHelper.COLUMN_OCCURANCERTE
    };

    private String[] allColumnsIncome = {
            SQLiteHelper.COLUMN_INCOMEID,
            SQLiteHelper.COLUMN_USERID,
            SQLiteHelper.COLUMN_ACCOUNTID,
            SQLiteHelper.COLUMN_INCOMENAME,
            SQLiteHelper.COLUMN_AMOUNT,
            SQLiteHelper.COLUMN_PAYPERIOD,
            SQLiteHelper.COLUMN_HOURS
    };
    private String[] allColumnsPurchase = {
            SQLiteHelper.COLUMN_PURCHASEID,
            SQLiteHelper.COLUMN_USERID,
            SQLiteHelper.COLUMN_ACCOUNTID,
            SQLiteHelper.COLUMN_INCOMENAME,
            SQLiteHelper.COLUMN_AMOUNT,
            SQLiteHelper.COLUMN_PAYPERIOD,
            SQLiteHelper.COLUMN_HOURS
    };
    private String[] allColumnsUser = {
            SQLiteHelper.COLUMN_USERID,
            SQLiteHelper.COLUMN_USERNAME,
            SQLiteHelper.COLUMN_PINHASH,
            SQLiteHelper.COLUMN_PIN,
            SQLiteHelper.COLUMN_SALT,
            SQLiteHelper.COLUMN_FIRSTTIME,
            SQLiteHelper.COLUMN_BUDGET
    };


    public DataSource(Context context) { dbHelper = new SQLiteHelper(context); }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Object create(String str, String[] args) {
        try {
            ContentValues values = new ContentValues();
            long returnValue = -1;
            Cursor cursor;
            User newUser;
            switch (str.toLowerCase()) {
                case "user":
                    /*User user = new User(
                            Long.parseLong(args[0]),
                            Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]),
                            args[3],
                            args[4],
                            Integer.parseInt(args[5]),
                            Integer.parseInt(args[6]),
                            Integer.parseInt(args[7]));*/

                    values.put(SQLiteHelper.COLUMN_USERID, Long.parseLong(args[0]));
                    values.put(SQLiteHelper.COLUMN_PINHASH, Integer.parseInt(args[1]));
                    values.put(SQLiteHelper.COLUMN_PIN, Integer.parseInt(args[2]));
                    values.put(SQLiteHelper.COLUMN_SALT, args[3]);
                    values.put(SQLiteHelper.COLUMN_USERNAME, args[4]);
                    //values.put(SQLiteHelper.COLUMN_ACCOUNTID, Integer.parseInt(args[5]));
                    //values.put(SQLiteHelper.COLUMN_JOBID, Integer.parseInt(args[6]));
                    values.put(SQLiteHelper.COLUMN_FIRSTTIME, Boolean.parseBoolean(args[5]));

                    returnValue = database.insert(SQLiteHelper.TABLE_USERS, null, values);

                    cursor = database.query(SQLiteHelper.TABLE_USERS,
                            allColumnsUser, SQLiteHelper.COLUMN_USERID + " = " + returnValue, null,
                            null, null, null);
                    cursor.moveToFirst();
                    newUser = cursorToUser(cursor);
                    cursor.close();

                    return newUser;

                case "income":
                    values.put(SQLiteHelper.COLUMN_INCOMEID, Integer.parseInt(args[0]));
                    values.put(SQLiteHelper.COLUMN_USERID, Integer.parseInt(args[1]));
                    values.put(SQLiteHelper.COLUMN_ACCOUNTID, Integer.parseInt(args[2]));
                    values.put(SQLiteHelper.COLUMN_INCOMENAME, args[3]);
                    values.put(SQLiteHelper.COLUMN_AMOUNT, Double.parseDouble(args[4]));
                    values.put(SQLiteHelper.COLUMN_PAYPERIOD, args[5]);
                    values.put(SQLiteHelper.COLUMN_HOURS, Double.parseDouble(args[6]));

                    returnValue = database.insert(SQLiteHelper.TABLE_INCOMES, null, values);

                    cursor = database.query(SQLiteHelper.TABLE_USERS,
                            allColumnsUser, SQLiteHelper.COLUMN_USERID + " = " + returnValue, null,
                            null, null, null);
                    cursor.moveToFirst();
                    newUser = cursorToUser(cursor);
                    cursor.close();

                    return newUser;
                    //break;
                case "account":
                    values.put(SQLiteHelper.COLUMN_ACCOUNTID, Integer.parseInt(args[0]));
                    values.put(SQLiteHelper.COLUMN_USERID, Integer.parseInt(args[1]));
                    values.put(SQLiteHelper.COLUMN_ACCOUNTNAME, args[2]);
                    values.put(SQLiteHelper.COLUMN_BALANCE, Double.parseDouble(args[3]));

                    returnValue = database.insert(SQLiteHelper.TABLE_ACCOUNTS, null, values);

                    cursor = database.query(SQLiteHelper.TABLE_USERS,
                            allColumnsUser, SQLiteHelper.COLUMN_USERID + " = " + returnValue, null,
                            null, null, null);
                    cursor.moveToFirst();
                    newUser = cursorToUser(cursor);
                    cursor.close();

                    return newUser;
                    //break;
                case "bill":
                    /*Bill bill = new Bill(Long.parseLong(args[0]), Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]), args[3], args[4], Integer.parseInt(args[5]),
                            Integer.parseInt(args[6]), Boolean.parseBoolean(args[7]));*/

                    values.put(SQLiteHelper.COLUMN_BILLID, Integer.parseInt(args[0]));
                    values.put(SQLiteHelper.COLUMN_BILLNAME, args[1]);
                    values.put(SQLiteHelper.COLUMN_USERID, Integer.parseInt(args[2]));
                    values.put(SQLiteHelper.COLUMN_ACCOUNTID, Integer.parseInt(args[3]));
                    values.put(SQLiteHelper.COLUMN_BILLAMT, Double.parseDouble(args[4]));
                    values.put(SQLiteHelper.COLUMN_DUEDTE, args[5]);
                    values.put(SQLiteHelper.COLUMN_OCCURANCERTE, Integer.parseInt(args[6]));

                    returnValue = database.insert(SQLiteHelper.TABLE_ACCOUNTS, null, values);

                    cursor = database.query(SQLiteHelper.TABLE_USERS,
                            allColumnsUser, SQLiteHelper.COLUMN_USERID + " = " + returnValue, null,
                            null, null, null);
                    cursor.moveToFirst();
                    newUser = cursorToUser(cursor);
                    cursor.close();

                    return newUser;

                    //break;
                case "purchase":
                    values.put(SQLiteHelper.COLUMN_PURCHASEID, Integer.parseInt(args[0]));
                    values.put(SQLiteHelper.COLUMN_USERID, Integer.parseInt(args[1]));
                    values.put(SQLiteHelper.COLUMN_ACCOUNTID, Integer.parseInt(args[2]));
                    values.put(SQLiteHelper.COLUMN_PRICE, Double.parseDouble(args[3]));
                    values.put(SQLiteHelper.COLUMN_DATETIME, args[4]);
                    values.put(SQLiteHelper.COLUMN_CATEGORY, args[5]);
                    values.put(SQLiteHelper.COLUMN_LOCATION, args[6]);
                    values.put(SQLiteHelper.COLUMN_DUEDTE, args[7]);

                    returnValue = database.insert(SQLiteHelper.TABLE_PURCHASES, null, values);

                    cursor = database.query(SQLiteHelper.TABLE_USERS,
                            allColumnsUser, SQLiteHelper.COLUMN_USERID + " = " + returnValue, null,
                            null, null, null);
                    cursor.moveToFirst();
                    newUser = cursorToUser(cursor);
                    cursor.close();

                    return newUser;

                //break;
                default:
                    break;
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Object deleteById(String str, Integer id) {
        Integer result = 0;
        try {
            ContentValues values;
            switch (str.toLowerCase()) {
                case "user":
                    String[] whereArgs = new String[id];
                    result = 0;
                    Cursor cursor = database.query(SQLiteHelper.TABLE_USERS, null, null, null, null, null, null);
                    if(cursor.moveToFirst()) {
                        result = database.delete(
                                SQLiteHelper.TABLE_USERS, //User Table
                                "? = " + SQLiteHelper.COLUMN_USERID, whereArgs);
                    }
                    cursor.close();
                break;
                case "bill":
                    whereArgs = new String[id];
                    result = 0;
                    cursor = database.query(SQLiteHelper.TABLE_BILLS, null, null, null, null, null, null);
                    if(cursor.moveToFirst()) {
                        result = database.delete(
                                SQLiteHelper.TABLE_BILLS, //Table
                                "? = " + SQLiteHelper.COLUMN_BILLID, //Where clause
                                whereArgs //Replaces ? with where args incrementally
                        );
                    }
                    cursor.close();
                break;
                case "account":
                    whereArgs = new String[id];
                    result = 0;
                    cursor = database.query(SQLiteHelper.TABLE_ACCOUNTS, null, null, null, null, null, null);
                    if(cursor.moveToFirst()) {
                        result = database.delete(
                                SQLiteHelper.TABLE_ACCOUNTS, //Table
                                "? = " + SQLiteHelper.COLUMN_ACCOUNTID, //Where clause
                                whereArgs //Replaces ? with where args incrementally
                        );
                    }
                    cursor.close();
                    break;
                case "comment":
                    whereArgs = new String[id];
                    result = 0;
                    cursor = database.query(SQLiteHelper.TABLE_COMMENTS, null, null, null, null, null, null);
                    if(cursor.moveToFirst()) {
                        result = database.delete(
                                SQLiteHelper.TABLE_COMMENTS, //Table
                                "? = " + SQLiteHelper.COLUMN_ID, //Where clause
                                whereArgs //Replaces ? with where args incrementally
                        );
                    }
                    cursor.close();
                    break;
                case "purchase":
                    whereArgs = new String[id];
                    result = 0;
                    cursor = database.query(SQLiteHelper.TABLE_PURCHASES, null, null, null, null, null, null);
                    if(cursor.moveToFirst()) {
                        result = database.delete(
                                SQLiteHelper.TABLE_PURCHASES, //Table
                                "? = " + SQLiteHelper.COLUMN_PURCHASEID, //Where clause
                                whereArgs //Replaces ? with where args incrementally
                        );
                    }
                    cursor.close();
                    break;
                case "income":
                    whereArgs = new String[id];
                    result = 0;
                    cursor = database.query(SQLiteHelper.TABLE_INCOMES, null, null, null, null, null, null);
                    if(cursor.moveToFirst()) {
                        result = database.delete(
                                SQLiteHelper.TABLE_INCOMES, //Table
                                "? = " + SQLiteHelper.COLUMN_INCOMEID, //Where clause
                                whereArgs //Replaces ? with where args incrementally
                        );
                    }
                    cursor.close();
                    break;
                default:
                    break;
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }


    // TODO: to be completed by Tony
    public Object retrieveById(String str, String id) {
        try {
            Cursor cursor;
            User newUser;
            Purchase newPurchase;
            Income newIncome;
            Bill newBill;
            Account newAccount;
            switch (str.toLowerCase()) {
                case "user":
                    cursor = database.query(SQLiteHelper.TABLE_USERS,
                            allColumnsUser, SQLiteHelper.COLUMN_USERID + " = " + id,
                            null, null, null, null);
                    cursor.moveToFirst();
                    newUser = cursorToUser(cursor);
                    cursor.close();

                    return newUser;
                case "purchase":
                    cursor = database.query(SQLiteHelper.TABLE_PURCHASES,
                            allColumnsPurchase, SQLiteHelper.COLUMN_PURCHASEID + " = " + id,
                            null, null, null, null);
                    cursor.moveToFirst();
                    newPurchase = cursorToPurchase(cursor);
                    cursor.close();

                    return newPurchase;
                case "income":
                    cursor = database.query(SQLiteHelper.TABLE_USERS,
                            allColumnsUser, SQLiteHelper.COLUMN_USERID + " = " + id,
                            null, null, null, null);
                    cursor.moveToFirst();
                    newUser = cursorToUser(cursor);
                    cursor.close();

                    return newUser;
                case "bill":
                    cursor = database.query(SQLiteHelper.TABLE_USERS,
                            allColumnsUser, SQLiteHelper.COLUMN_USERID + " = " + id,
                            null, null, null, null);
                    cursor.moveToFirst();
                    newUser = cursorToUser(cursor);
                    cursor.close();

                    return newUser;
                case "account":
                    cursor = database.query(SQLiteHelper.TABLE_USERS,
                            allColumnsUser, SQLiteHelper.COLUMN_USERID + " = " + id,
                            null, null, null, null);
                    cursor.moveToFirst();
                    newUser = cursorToUser(cursor);
                    cursor.close();

                    return newUser;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Object> retrieveAll(String str) {
        try {
            Cursor cursor;
            switch (str.toLowerCase()) {
                case "purchase":
                    List<Purchase> purchaseList = new ArrayList<>();
                    List<Object> purchaseObjects = new ArrayList<>();

                    cursor = database.query(SQLiteHelper.TABLE_PURCHASES,
                            allColumnsPurchase, null, null, null, null, null);
                    cursor.moveToFirst();
                    purchaseList = cursorToPurchaseList(cursor);
                    cursor.close();

                    for (int i = 0; i < purchaseList.size(); i++) {
                        purchaseObjects.add((Object) purchaseList.get(i));
                    }

                    return purchaseObjects;
                // TODO: cases for account, bill, income, user : completed by Tony
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Comment createComment(String comment) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_COMMENT, comment);
        long insertId = database.insert(SQLiteHelper.TABLE_COMMENTS, null,
                values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_COMMENTS,
                allColumns, SQLiteHelper.COLUMN_USERID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Comment newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteComment(Comment comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(SQLiteHelper.TABLE_COMMENTS, SQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }


    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_COMMENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.setId(cursor.getLong(0));
        comment.setComment(cursor.getString(1));
        return comment;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getLong(0));
        user.setPin_hash(cursor.getInt(1));
        user.setPin(cursor.getInt(2));
        user.setSalt(cursor.getString(3));
        user.setUsername(cursor.getString(4));
        user.setFirstTime(cursor.getInt(5));
        return user;
    }

    private Bill cursorToBill(Cursor cursor) {
        Bill bill = new Bill();
        bill.setBillId(cursor.getInt(0));
        bill.setUserId(cursor.getInt(1));
        bill.setAccountId(cursor.getInt(2));
        bill.setBillName(cursor.getString(3));
        bill.setBillAmount(cursor.getDouble(4));
        bill.setDueDate(cursor.getString(5));
        bill.setOccuranceRte(cursor.getInt(6));
        return bill;
    }

    private Purchase cursorToPurchase(Cursor cursor) {
        Purchase purchase = new Purchase();
        purchase.setPurchaseId(cursor.getInt(0));
        purchase.setUserId(cursor.getInt(1));
        purchase.setAccountId(cursor.getInt(2));
        purchase.setPrice(cursor.getDouble(3));
        /*purchase.setDate(cursor.getString(4));
        purchase.setTime(cursor.getString(5));*/
        purchase.setCategory(cursor.getString(6));
        purchase.setLocation(cursor.getString(7));
        purchase.setComment(cursor.getString(8));
        return purchase;
    }

    // TODO: complete other cursorTo<Object>List methods
    private List<Purchase> cursorToPurchaseList(Cursor cursor) {
        List<Purchase> purchaseList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Purchase purchase = cursorToPurchase(cursor);
            purchaseList.add(purchase);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return purchaseList;
    }

}
