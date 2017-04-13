package com.example.sajini.homeautomationmobileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sajini on 4/13/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    // All Static variables
    private static final int DATABASE_VERSION = 1;               // Database Version

    private static final String DATABASE_NAME = "HomeAutomation.db";      //Database name
    private static final String DEVICES_TABLE_NAME = "devices";          // table name
    private static final String DEVICES_COLUMN_ID = "id";                // columns name
    private static final String DEVICES_COLUMN_CATEGORY = "category";
    private static final String DEVICES_COLUMN_TOPIC = "topic";
    private static final String DEVICES_COLUMN_STATUS = "status";
    private HashMap hp;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //creating tables
    //These is where we need to write create table statements. This is called when database is created.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "create table devices " + "(id integer primary key, topic text,status text)"
        );

    }

    //upgrading tables
    //This method is called when database is upgraded
    //like modifying the table structure, adding constraints to database etc.,
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DEVICES_TABLE_NAME);

        // Create tables again
        onCreate(db);

    }

    public boolean addDevice (String category, String topic, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("category", category);
        contentValues.put("topic", topic);
        contentValues.put("status", status);

        db.insert(DEVICES_TABLE_NAME, null, contentValues);
        db.close(); // Closing database connection
        return true;
    }

    public void getData(String category, ArrayList topics) {

        String sql = "SELECT"+DEVICES_COLUMN_TOPIC+" from" +DEVICES_TABLE_NAME+ "WHERE category=?";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, new String[] {category});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding contact to list
                topics.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
    }


    // Updating single contact
    public int updateContact(String id, String category, String topic, String status) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("category", category);
        contentValues.put("topic", topic);
        contentValues.put("status", status);
        // updating row
        return db.update(DEVICES_TABLE_NAME, contentValues, DEVICES_COLUMN_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    // Deleting single contact
    public void deleteContact(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DEVICES_TABLE_NAME, DEVICES_COLUMN_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }
}
