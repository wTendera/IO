package com.example.io.io.models;

import com.example.io.io.interfaces.Order;

/**
 * Created by wiktortendera on 05/09/15.
 */
public class Trainer {
    private Order mode;
    public void setMode(Order order) {
        mode = order;
    }

    public void command() {
        mode.follow();
    }

    public void undo() {
        mode.undo();
    }
}
