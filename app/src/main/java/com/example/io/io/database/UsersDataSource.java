package com.example.io.io.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.io.io.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wiktortendera on 30/08/15.
 */
public class UsersDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_FIRST_NAME, MySQLiteHelper.COLUMN_LAST_NAME ,
            MySQLiteHelper.COLUMN_STREET, MySQLiteHelper.COLUMN_CITY, MySQLiteHelper.COLUMN_ZIP};

    public UsersDataSource(Context context) {
        database = Database.getDatabase(context);
    }


    public ContentValues getArgs(User user) {
        ContentValues args = new ContentValues();
        args.put(MySQLiteHelper.COLUMN_ID, user.getId());
        args.put(MySQLiteHelper.COLUMN_FIRST_NAME, user.getFirstName());
        args.put(MySQLiteHelper.COLUMN_LAST_NAME, user.getLastName());
        args.put(MySQLiteHelper.COLUMN_STREET, user.getStreet());
        args.put(MySQLiteHelper.COLUMN_CITY, user.getCity());
        args.put(MySQLiteHelper.COLUMN_ZIP, user.getZip());
        return args;
    }

    public long createOrUpdateUser(User user) {
        ContentValues args = getArgs(user);
        args.remove(MySQLiteHelper.COLUMN_ID);
        return database.insertWithOnConflict("users", null, args, 5); //5 = CONFLICT REPLACE
    }



    private User cursorToUser(Cursor cursor) {

        User user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5));
        return user;
    }

    public User getUser(long id) {
        Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS,
                allColumns, MySQLiteHelper.COLUMN_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        cursor.moveToFirst();
        User user = cursorToUser(cursor);
        cursor.close();
        return user;
    }
}
