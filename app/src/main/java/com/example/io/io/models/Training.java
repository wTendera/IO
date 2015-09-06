package com.example.io.io.models;

import com.example.io.io.interfaces.Order;

import java.util.ArrayList;

/**
 * Created by wiktortendera on 30/08/15.
 */
public class Training implements Order{
    private int id;
    private String name;
    private Order orders[];

    public Training(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String follow() {
        ArrayList<String> res = new ArrayList<>();
        for(Order o : orders)
            res.add(o.follow());
        return res.toString();
    }

    @Override
    public String undo() {
        ArrayList<String> res = new ArrayList<>();
        for(Order o : orders)
            res.add(o.undo());
        return res.toString();
    }
}
