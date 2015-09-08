package com.example.io.io.patterns;

import android.app.Activity;
import android.widget.Toast;

import com.example.io.io.interfaces.TrainingState;
import com.example.io.io.models.Training;

/**
 * Created by wiktortendera on 08/09/15.
 */
public class TrainingClosed implements TrainingState {
    @Override
    public void start(Training tr, Activity act) {
        Toast.makeText(act, "Czas na jedzenie!", Toast.LENGTH_SHORT).show();
    }
}
