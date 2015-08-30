package com.example.io.io.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "io_android.db";
    private static final int DATABASE_VERSION = 1;

    public static final String COLUMN_ID = "_id";

    /*********** CARS CREATE TABLE ************/
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_ZIP = "zip";

    private static final String USERS_CREATE = "create table "
            + TABLE_USERS + "(" + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_FIRST_NAME + " text, "
            + COLUMN_LAST_NAME + " text, "
            + COLUMN_STREET + " text, "
            + COLUMN_CITY + " text, "
            + COLUMN_ZIP + " text);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(USERS_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        onCreate(db);
    }

}
