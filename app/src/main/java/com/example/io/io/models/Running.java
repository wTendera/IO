package com.example.io.io.models;

import com.example.io.io.interfaces.Order;

/**
 * Created by wiktortendera on 05/09/15.
 */
public class Running implements Order {
    private Contestant contestant;  //obiekt wykonujacy
    public Running (Contestant contestant){
        this.contestant = contestant;
    }

    public String follow(){
        return contestant.startRun();
    }

    public String undo(){
        return contestant.stopRun();
    }
}
