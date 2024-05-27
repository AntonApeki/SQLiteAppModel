package com.example.sqliteappmodel.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userstore.db";
    private static final int SCHEMA = 1;
    public static final String TABLE_USERS = "users";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";

    public static final String[] COLUMNS = new String[] {COLUMN_ID, COLUMN_NAME, COLUMN_PHONE};

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // String onCreateSQL = String.format("CREATE TABLE %s (%d INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT);", TABLE_USERS, COLUMN_ID, COLUMN_NAME, COLUMN_PHONE);

        // db.execSQL("CREATE TABLE " + TABLE_USERS + " (" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT," + COLUMN_PHONE + " TEXT);");
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT);", TABLE_USERS, COLUMN_ID, COLUMN_NAME, COLUMN_PHONE));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
}
