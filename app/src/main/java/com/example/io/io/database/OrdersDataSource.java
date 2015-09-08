package com.example.io.io.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.io.io.interfaces.Order;
import com.example.io.io.models.Exercising;
import com.example.io.io.models.Running;
import com.example.io.io.models.Swimming;
import com.example.io.io.models.Training;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wiktortendera on 06/09/15.
 */
public class OrdersDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_TYPE,  MySQLiteHelper.COLUMN_CONTESTANT_ID,
            MySQLiteHelper.COLUMN_TRAINING_ID,  MySQLiteHelper.COLUMN_DOING};

    public OrdersDataSource(Context context){
        database = Database.getDatabase(context);
    }


    public ContentValues getArgs(Order order) {
        ContentValues args = new ContentValues();
        args.put(MySQLiteHelper.COLUMN_ID, order.getId());
        args.put(MySQLiteHelper.COLUMN_TYPE, order.getType());
        args.put(MySQLiteHelper.COLUMN_CONTESTANT_ID, order.getContestant());
        args.put(MySQLiteHelper.COLUMN_TRAINING_ID, order.getTraining());
        args.put(MySQLiteHelper.COLUMN_DOING, order.getDoing());
        return args;
    }

    public long createOrUpdateOrder(Order order) {
        ContentValues args = getArgs(order);
        args.remove(MySQLiteHelper.COLUMN_ID);
        return database.insertWithOnConflict ("orders", null, args, 5); //5 = CONFLICT REPLACE
    }

    public long updateOrder(Order order) {
        ContentValues args = getArgs(order);
        return database.insertWithOnConflict ("orders", null, args, 5); //5 = CONFLICT REPLACE
    }

    public List<Order> getOrdersForTraining(long trainingId) {
        Cursor cursor = database.query(MySQLiteHelper.TABLE_ORDERS,
                allColumns, MySQLiteHelper.COLUMN_TRAINING_ID + "=?", new String[] { String.valueOf(trainingId) }, null, null, null, null);
        List<Order> orders = new ArrayList<Order>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Order order = cursorToOrder(cursor);
            orders.add(order);
            cursor.moveToNext();
        }
        return orders;
    }

    private Order cursorToOrder(Cursor cursor) {
        switch (cursor.getInt(1)) {
            case(0) :
                return new Running(cursor.getLong(0), cursor.getInt(1), cursor.getLong(2), cursor.getLong(3), cursor.getInt(4));
            case(1) :
                return new Swimming(cursor.getLong(0), cursor.getInt(1), cursor.getLong(2), cursor.getLong(3), cursor.getInt(4));
            default :
                return new Exercising(cursor.getLong(0), cursor.getInt(1), cursor.getLong(2), cursor.getLong(3), cursor.getInt(4));
        }
    }
}
