package com.example.sqliteappmodel.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {
    DatabaseHelper databaseHelper;
    SQLiteDatabase database;

    public DatabaseAdapter(Context context) {
        databaseHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public DatabaseAdapter open(){
        database = databaseHelper.getReadableDatabase();
        return DatabaseAdapter.this;
    }
    public void close() {
        databaseHelper.close();
    }
    private Cursor getAllEntries(){
        //String[] columns = new String[]{DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_PHONE};
        return database.query(DatabaseHelper.TABLE_USERS, DatabaseHelper.COLUMNS, null, null, null, null, null);
    }

    public long addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, user.name);
        values.put(DatabaseHelper.COLUMN_PHONE, user.number);
        return database.insert(DatabaseHelper.TABLE_USERS, null, values);
    }
    public long delUser(long id) {
        String condition = String.format("%s = ?", DatabaseHelper.COLUMN_ID);
        String[] conditionArgs = new String[]{String.valueOf(id)};
        return database.delete(DatabaseHelper.TABLE_USERS, condition, conditionArgs);
    }
    public long updUser(User user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, user.name);
        values.put(DatabaseHelper.COLUMN_PHONE, user.number);
        String whereClause = String.format("%s = ?", DatabaseHelper.COLUMN_ID);
        String[] whereArgs = new String[]{String.valueOf(user.id)};
        return database.update(DatabaseHelper.TABLE_USERS, values, whereClause, whereArgs);
    }
    public long getCount(){
        return getUsers().size();
    }
    public User getUser(long id) {
        User userReturn = new User();
        String query = String.format("SELECT * FROM %s WHERE %s = ?", DatabaseHelper.TABLE_USERS, DatabaseHelper.COLUMN_ID);

        Cursor userCursor = database.rawQuery(query, new String[]{String.valueOf(id)});

        while(userCursor.moveToFirst()) {
            String name = userCursor.getString(userCursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
            String phone = userCursor.getString(userCursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PHONE));
            userReturn.name = name;
            userReturn.number = phone;
        }
        userCursor.close();
        return userReturn;

    }
    public ArrayList<User> getUsers(){
        ArrayList<User> userArrayList = new ArrayList<>();
        Cursor userCursor = getAllEntries();
        while(userCursor.moveToNext()) {
            int id = userCursor.getInt(userCursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
            String name = userCursor.getString(userCursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
            String phone = userCursor.getString(userCursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PHONE));
            User user = new User(id, name, phone);
            userArrayList.add(user);
        }
        return userArrayList;
    }
}
