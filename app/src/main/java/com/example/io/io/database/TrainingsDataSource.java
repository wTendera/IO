package com.example.io.io.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.io.io.models.Training;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wiktortendera on 30/08/15.
 */
public class TrainingsDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_NAME};

    public TrainingsDataSource(Context context){
        database = Database.getDatabase(context);
    }


    public List<Training> getAllTrainings() {
        List<Training> trainings = new ArrayList<Training>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_TRAININGS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Training training = cursorToTraining(cursor);
            trainings.add(training);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return trainings;
    }

    public ContentValues getArgs(Training training) {
        ContentValues args = new ContentValues();
        args.put(MySQLiteHelper.COLUMN_ID, training.getId());
        args.put(MySQLiteHelper.COLUMN_NAME, training.getName());
        return args;
    }

    public void createOrUpdateTraining(Training training) {
        ContentValues args = getArgs(training);
        database.insertWithOnConflict ("trainings", null, args, 5); //5 = CONFLICT REPLACE
    }

    private Training cursorToTraining(Cursor cursor) {
        Training training = new Training(cursor.getInt(0), cursor.getString(1));
        return training;
    }
}
