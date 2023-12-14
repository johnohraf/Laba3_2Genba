package com.example.laba3_2genba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "OneGroupDB";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "OneGroup";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_LAST_NAME = "Last_Name";
    public static final String COLUMN_FIRST_NAME = "First_Name";
    public static final String COLUMN_MIDDLE_NAME = "Middle_Name";
    public static final String COLUMN_TIMESTAMP = "Timestamp";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_LAST_NAME + " TEXT, " +
                    COLUMN_FIRST_NAME + " TEXT, " +
                    COLUMN_MIDDLE_NAME + " TEXT, " +
                    COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}