package com.example.io.io.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.io.io.models.Contestant;
import com.example.io.io.models.Training;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wiktortendera on 06/09/15.
 */
public class ContestantsDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_NAME};

    public ContestantsDataSource(Context context){
        database = Database.getDatabase(context);
    }


    public List<Contestant> getAllContestants() {
        List<Contestant> contestants = new ArrayList<Contestant>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_CONTESTANTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Contestant contestant = cursorToContestant(cursor);
            contestants.add(contestant);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return contestants;
    }

    public ContentValues getArgs(Contestant contestant) {
        ContentValues args = new ContentValues();
        args.put(MySQLiteHelper.COLUMN_ID, contestant.getId());
        args.put(MySQLiteHelper.COLUMN_NAME, contestant.getName());
        return args;
    }

    public long createOrUpdateContestant(Contestant contestant) {
        ContentValues args = getArgs(contestant);
        args.remove(MySQLiteHelper.COLUMN_ID);
        return database.insertWithOnConflict ("contestants", null, args, 5); //5 = CONFLICT REPLACE
    }

    private Contestant cursorToContestant(Cursor cursor) {
        Contestant contestant = new Contestant(cursor.getInt(0), cursor.getString(1));
        return contestant;
    }
}
