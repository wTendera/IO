package com.example.io.io.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.io.io.R;

import com.example.io.io.activities.TrainingActivity;
import com.example.io.io.adapters.TrainingsAdapter;
import com.example.io.io.database.TrainingsDataSource;
import com.example.io.io.models.Training;

import java.util.ArrayList;

public class TrainingFragment extends Fragment implements AbsListView.OnItemClickListener {


    private long user_id;

    private ArrayList<Training> content;

    private OnFragmentInteractionListener mListener;
    private TrainingsDataSource trainingsDataSource;

    private AbsListView mListView;
    SharedPreferences prefs;

    private TrainingsAdapter mAdapter;

    public TrainingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        trainingsDataSource = new TrainingsDataSource(getActivity());
        prefs = getActivity().getSharedPreferences("com.example.app", Context.MODE_PRIVATE);


        content = new ArrayList<Training>();
        content.addAll(trainingsDataSource.getAllTrainings());
        for(Training t : content)
            t.setState(getActivity());

        mAdapter = new TrainingsAdapter(getActivity(),
                R.layout.training_item_row, content);

        user_id = prefs.getLong("user_id", 0);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        mListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Training training = content.get((int) id);
        training.start(getActivity());
    }

    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String id);
    }


}
