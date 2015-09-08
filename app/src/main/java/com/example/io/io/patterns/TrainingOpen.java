package com.example.io.io.patterns;

import android.app.Activity;
import android.content.Intent;

import com.example.io.io.activities.TrainingActivity;
import com.example.io.io.interfaces.TrainingState;
import com.example.io.io.models.Training;

/**
 * Created by wiktortendera on 08/09/15.
 */
public class TrainingOpen implements TrainingState{
    @Override
    public void start(Training tr, Activity act) {
        Intent intent = new Intent(act, TrainingActivity.class);
        intent.putExtra("trainingId", (long)tr.getId());
        System.out.println(tr.getId());
        act.startActivityForResult(intent, 1);
    }
}
