package com.example.io.io.models;

/**
 * Created by wiktortendera on 05/09/15.
 */
public class Contestant {
    private long id;
    private String name;

    public Contestant(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() { return id; }

    public String getName() { return name; }

    public String startRun() {
        return "zawodnik " + name + " biega";
    }

    public String stopRun() {
        return "zawodnik " + name + " przestał biegać";
    }

    public String startSwim() {
        return "zawodnik " + name + " pływa";
    }

    public String stopSwimming() {
        return "zawodnik " + name + " przestał pływać";
    }

    public String startExercise() {
        return "zawodnik " + name + " ćwiczy";
    }

    public String stopExercising() {
        return "zawodnik " + name + " przestał ćwiczyć";
    }

}
