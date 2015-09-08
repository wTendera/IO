package com.example.io.io.models;

import android.app.Activity;

import com.example.io.io.database.ContestantsDataSource;
import com.example.io.io.database.OrdersDataSource;
import com.example.io.io.interfaces.Order;

/**
 * Created by wiktortendera on 05/09/15.
 */
public class Exercising implements Order {
    private long id;
    private int type;
    private long contestant_id;
    private long training_id;
    private int doing;

    public Exercising(long id, int type, long contestant_id, long training_id, int doing) {
        this.id = id;
        this.type = type;
        this.contestant_id = contestant_id;
        this.training_id = training_id;
        this.doing = doing;
    }

    public String follow(Activity act){
        return contestant(act).startExercise();
    }

    public String undo(Activity act){
        return contestant(act).stopExercising();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public long getContestant() {
        return contestant_id;
    }

    @Override
    public long getTraining() {
        return training_id;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public boolean getDoing() {
        return doing == 1;
    }

    public Contestant contestant(Activity act) {
        ContestantsDataSource dataSource = new ContestantsDataSource(act);
        return dataSource.getContestant(getContestant());
    }

    @Override
    public String getStatus() {
        return doing == 1? "Working" : "Resting";
    }

    @Override
    public String getCommandString() {
        return doing == 1? "Stop" : "Start";
    }

    @Override
    public String command(Activity act) {
        OrdersDataSource dataSource = new OrdersDataSource(act);
        String res = null;
        if(doing == 0) {
            res = follow(act);
            doing = 1;
        } else {
            res = undo(act);
            doing = 0;
        }
        dataSource.updateOrder(this);
        return res;
    }

    public String getName() {
        return "Exercising";
    }
}
