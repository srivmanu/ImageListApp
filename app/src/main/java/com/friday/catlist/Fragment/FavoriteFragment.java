/*
 * Copyright (c) 2020.
 * This file is developed by Manu Srivastava.
 * All intents and usage of the file are supposed to be personal. If you
 * are using this file in your deployment code, please make sure you
 * test the file before you deploy. Else, may god help your application
 *
 * Manu Srivastava can be contacted at srivmanu@gmail.com
 */

package com.friday.catlist.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.friday.catlist.Activity.BottomTabbedActivity;
import com.friday.catlist.Adapter.FavoriteAdapter;
import com.friday.catlist.AppClass;
import com.friday.catlist.R;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    AppClass application;
    private int mColumnCount = 2;
    private AppCompatActivity mActivity;
    private FavoriteAdapter adapter;

    public FavoriteFragment() {
    }

    public static FavoriteFragment newInstance(int columnCount) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        application = (AppClass) mActivity.getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), mColumnCount));
            }
            adapter = new FavoriteAdapter(new ArrayList<>(application.getList()));
            recyclerView.setAdapter(adapter);
        }

        return view;
    }


    public void updateAdapter() {
        mActivity.runOnUiThread(() -> adapter.notifyDataSetChanged());
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof BottomTabbedActivity) {
            mActivity = (BottomTabbedActivity) context;
        }
    }
}