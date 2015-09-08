package com.example.io.io.models;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.io.io.interfaces.Order;
import com.example.io.io.interfaces.TrainingState;
import com.example.io.io.patterns.TrainingClosed;
import com.example.io.io.patterns.TrainingOpen;

import java.util.ArrayList;

/**
 * Created by wiktortendera on 30/08/15.
 */
public class Training {
    private int id;
    private String name;
    public TrainingState state;


    public Training(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setState(Activity act) {
        SharedPreferences prefs = act.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
        boolean eating = prefs.getBoolean("eating", false);
        if(eating)
            state = new TrainingClosed();
        else
            state = new TrainingOpen();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void start(Activity act) {
        state.start(this, act);
    }

}
