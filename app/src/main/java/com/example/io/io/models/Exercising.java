package com.example.io.io.models;

import com.example.io.io.interfaces.Order;

/**
 * Created by wiktortendera on 05/09/15.
 */
public class Exercising implements Order {
    private Contestant contestant;  //obiekt wykonujacy
    public Exercising (Contestant contestant){
        this.contestant = contestant;
    }

    public String follow(){
        return contestant.startExercise();
    }

    public String undo(){
        return contestant.stopExercising();
    }
}
