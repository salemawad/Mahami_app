package com.example.final_projects.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "User_Task", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table User_Task(set_TaskName TEXT primary key, set_Description TEXT Not null, set_Date TEXT Not null, set_Time TEXT Not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists User_Taks");
    }

    //===========================================================================
    //Method For Insert data into SQLite Database Table (UserDetails)
    public Boolean InsertUserData(String set_TaskName, String set_Description, String set_Date,  String set_Time) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("set_TaskName", set_TaskName);
        contentValues.put("set_Description", set_Description);
        contentValues.put("set_Date", set_Date);
        contentValues.put("set_Time", set_Time);
        long result = DB.insert("User_Task", null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }
    //===========================================================================
    //Method For Get data From SQLite Database Table (UserDetails) Using Cursor
    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor;
        cursor = DB.rawQuery("Select * from User_Task ", null);
        return cursor;
    }
}
