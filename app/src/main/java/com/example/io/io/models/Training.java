package com.example.io.io.models;

/**
 * Created by wiktortendera on 30/08/15.
 */
public class Training {
    private int id;
    private String name;

    public Training(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
}
