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

import com.example.io.io.activities.ContestantActivity;
import com.example.io.io.adapters.ContestantsAdapter;
import com.example.io.io.database.ContestantsDataSource;
import com.example.io.io.models.Contestant;

import java.util.ArrayList;


public class ContestantFragment extends Fragment implements AbsListView.OnItemClickListener {


    private ArrayList<Contestant> content;

    private OnFragmentInteractionListener mListener;
    private ContestantsDataSource contestantsDataSource;

    private AbsListView mListView;
    SharedPreferences prefs;

    private ContestantsAdapter mAdapter;
    public ContestantFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contestantsDataSource = new ContestantsDataSource(getActivity());
        prefs = getActivity().getSharedPreferences("com.example.app", Context.MODE_PRIVATE);


        content = new ArrayList<Contestant>();
        content.addAll(contestantsDataSource.getAllContestants());

        mAdapter = new ContestantsAdapter(getActivity(),
                R.layout.contestant_item_row, content);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contestant, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
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
        long contestantId = content.get((int)id).getId();
        Intent intent = new Intent(getActivity(), ContestantActivity.class);
        intent.putExtra("contestantId", contestantId);
        startActivityForResult(intent, 1);
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
