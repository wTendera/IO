package com.example.io.io.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by wiktortendera on 30/08/15.
 */
public class Database {
    public static SQLiteDatabase database;
    private static MySQLiteHelper dbHelper;

    public Database(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public static SQLiteDatabase getDatabase(Context context) {
        if(database == null) {
            dbHelper = new MySQLiteHelper(context);
            database = dbHelper.getWritableDatabase();
        }
        return database;
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
}
