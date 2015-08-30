package com.example.io.io.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.io.io.R;
import com.example.io.io.models.Training;

import java.util.ArrayList;

/**
 * Created by wiktortendera on 30/08/15.
 */
public class TrainingsAdapter extends ArrayAdapter<Training> {

    Context context;
    int layoutResourceId;
    ArrayList<Training> data = null;
    SharedPreferences prefs;
    int lastCarId;

    public TrainingsAdapter(Context context, int layoutResourceId, ArrayList<Training> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        prefs = context.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TrainingHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new TrainingHolder();
            holder.name = (TextView)row.findViewById(R.id.name);

            row.setTag(holder);
        }
        else
        {
            holder = (TrainingHolder)row.getTag();
        }

        Training training = data.get(position);
        holder.name.setText(training.getName());

        return row;
    }

    static class TrainingHolder
    {
        TextView name;
    }
}
