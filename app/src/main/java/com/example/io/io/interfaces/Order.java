package com.example.io.io.interfaces;

import android.app.Activity;

import com.example.io.io.models.Contestant;

/**
 * Created by wiktortendera on 05/09/15.
 */
public interface Order {
    String follow(Activity act);
    String undo(Activity act);
    long getId();
    long getContestant();
    long getTraining();
    int getType();
    boolean getDoing();
    Contestant contestant(Activity act);
    String getStatus();
    String getCommandString();
    String command(Activity act);
    String getName();
}
