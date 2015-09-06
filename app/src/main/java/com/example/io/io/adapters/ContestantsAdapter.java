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
import com.example.io.io.models.Contestant;
import com.example.io.io.models.Training;

import java.util.ArrayList;

/**
 * Created by wiktortendera on 06/09/15.
 */
public class ContestantsAdapter extends ArrayAdapter<Contestant> {
    Context context;
    int layoutResourceId;
    ArrayList<Contestant> data = null;
    SharedPreferences prefs;
    int lastCarId;

    public ContestantsAdapter(Context context, int layoutResourceId, ArrayList<Contestant> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        prefs = context.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ContestantHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ContestantHolder();
            holder.name = (TextView)row.findViewById(R.id.name);

            row.setTag(holder);
        }
        else
        {
            holder = (ContestantHolder)row.getTag();
        }

        Contestant contestant = data.get(position);
        holder.name.setText(contestant.getName());

        return row;
    }

    static class ContestantHolder
    {
        TextView name;
    }
}
