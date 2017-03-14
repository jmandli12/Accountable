package com.cs506.accountable.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.cs506.accountable.dto.Comment;
import com.cs506.accountable.dto.User;
import com.cs506.accountable.dto.Bill;

/**
 * Created by tkobl on 3/8/2017.
 */

public class DataSource {
    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = { SQLiteHelper.COLUMN_ID,
            SQLiteHelper.COLUMN_COMMENT };
    private String[] allColumnsUser = { SQLiteHelper.COLUMN_USERID,
            SQLiteHelper.COLUMN_USERNAME, SQLiteHelper.COLUMN_ACCOUNTID, SQLiteHelper.COLUMN_JOBID,
            SQLiteHelper.COLUMN_PINHASH, SQLiteHelper.COLUMN_PIN, SQLiteHelper.COLUMN_SALT};

    public DataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Object create(String str, String[] args) {
        try {
            ContentValues values = new ContentValues();
            switch (str.toLowerCase()) {
                case "user":
                    User user = new User(Long.parseLong(args[0]), Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]), args[3], args[4], Integer.parseInt(args[5]),
                            Integer.parseInt(args[6]), Integer.parseInt(args[7]));

                    values.put(SQLiteHelper.COLUMN_USERID, Long.parseLong(args[0]));
                    values.put(SQLiteHelper.COLUMN_PINHASH, Integer.parseInt(args[1]));
                    values.put(SQLiteHelper.COLUMN_PIN, Integer.parseInt(args[2]));
                    values.put(SQLiteHelper.COLUMN_SALT, args[3]);
                    values.put(SQLiteHelper.COLUMN_USERNAME, args[4]);
                    values.put(SQLiteHelper.COLUMN_ACCOUNTID, Integer.parseInt(args[5]));
                    values.put(SQLiteHelper.COLUMN_JOBID, Integer.parseInt(args[6]));
                    values.put(SQLiteHelper.COLUMN_FIRSTTIME, Boolean.parseBoolean(args[7]));
                    long insertUser = database.insert(SQLiteHelper.TABLE_USERS, null,
                            values);
                    Cursor cursor = database.query(SQLiteHelper.TABLE_USERS,
                            allColumnsUser, SQLiteHelper.COLUMN_USERID + " = " + insertUser, null,
                            null, null, null);
                    cursor.moveToFirst();
                    User newUser = cursorToUser(cursor);
                    cursor.close();
                    return newUser;
                case "income":
                    break;
                case "account":
                    break;
                case "bill":
                    Bill bill = new Bill(Long.parseLong(args[0]), Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]), args[3], args[4], Integer.parseInt(args[5]),
                            Integer.parseInt(args[6]), Boolean.parseBoolean(args[7]));

                    values.put(SQLiteHelper.COLUMN_BILLID, Integer.parseInt(args[0]));
                    values.put(SQLiteHelper.COLUMN_USERID, Integer.parseInt(args[1]));
                    values.put(SQLiteHelper.COLUMN_ACCOUNTID, Integer.parseInt(args[2]));
                    values.put(SQLiteHelper.COLUMN_BILLNAME, args[3]);
                    values.put(SQLiteHelper.COLUMN_BILLAMT, Double.parseDouble(args[4]));
                    values.put(SQLiteHelper.COLUMN_DUEDTE, args[5]);
                    values.put(SQLiteHelper.COLUMN_OCCURANCERTE, Integer.parseInt(args[6]));
                    break;
                case "purchase":
                    break;
                default:
                    break;
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Object retrieve(String str, String[] args) {
        try {
            ContentValues values = new ContentValues();
            switch (str.toLowerCase()) {
                case "user":
                    User user = new User(Long.parseLong(args[0]), Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]), args[3], args[4], Integer.parseInt(args[5]),
                            Integer.parseInt(args[6]), Integer.parseInt(args[7]));

                    values = new ContentValues();
                    values.put(SQLiteHelper.COLUMN_USERID, Long.parseLong(args[0]));
                    values.put(SQLiteHelper.COLUMN_PINHASH, Integer.parseInt(args[1]));
                    values.put(SQLiteHelper.COLUMN_PIN, Integer.parseInt(args[2]));
                    values.put(SQLiteHelper.COLUMN_SALT, args[3]);
                    values.put(SQLiteHelper.COLUMN_USERNAME, args[4]);
                    values.put(SQLiteHelper.COLUMN_ACCOUNTID, Integer.parseInt(args[5]));
                    values.put(SQLiteHelper.COLUMN_JOBID, Integer.parseInt(args[6]));
                    values.put(SQLiteHelper.COLUMN_FIRSTTIME, Boolean.parseBoolean(args[7]));
                    Cursor findUser = database.query("TABLE_USER", allColumnsUser,
                            "user_id = " + Long.parseLong(args[0]), new String[] {}, null, null,
                            null);
                    findUser.moveToFirst();
                    User newUser = cursorToUser(findUser);
                    findUser.close();
                    return newUser;
                case "bill":
                    /*Bill bill = new Bill(Long.parseLong(args[0]), Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]), args[3], args[4], Integer.parseInt(args[5]),
                            Integer.parseInt(args[6]), Boolean.parseBoolean(args[7]));*/

                    values = new ContentValues();
                    values.put(SQLiteHelper.COLUMN_BILLID, Integer.parseInt(args[0]));
                    values.put(SQLiteHelper.COLUMN_USERID, Integer.parseInt(args[1]));
                    values.put(SQLiteHelper.COLUMN_ACCOUNTID, Integer.parseInt(args[2]));
                    values.put(SQLiteHelper.COLUMN_BILLNAME, args[3]);
                    values.put(SQLiteHelper.COLUMN_BILLAMT, Double.parseDouble(args[4]));
                    values.put(SQLiteHelper.COLUMN_DUEDTE, args[5]);
                    values.put(SQLiteHelper.COLUMN_OCCURANCERTE, Integer.parseInt(args[6]));
                    // returns cursor with bill that matches bill id in args
                    Cursor findBill = database.query("TABLE_BILL", allColumnsUser,
                            "bill_id = " + Integer.parseInt(args[0]), new String[] {}, null, null,
                            null);
                    findBill.moveToFirst();
                    Bill newBill = cursorToBill(findBill);
                    findBill.close();
                    return newBill;
                default:
                    break;
            }
        }
        catch (Exception e) {
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
        user.setAccountID(cursor.getInt(5));
        user.setJobID(cursor.getInt(6));
        user.setFirstTime(cursor.getInt(7));
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
}
