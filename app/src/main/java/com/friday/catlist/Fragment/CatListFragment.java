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
import com.friday.catlist.Adapter.CatListItemAdapter;
import com.friday.catlist.AppClass;
import com.friday.catlist.R;

public class CatListFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    AppClass application;
    private int mColumnCount = 2;
    private AppCompatActivity mActivity;
    private CatListItemAdapter adapter;

    public CatListFragment() {
    }

    public static CatListFragment newInstance(int columnCount) {
        CatListFragment fragment = new CatListFragment();
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
        View view = inflater.inflate(R.layout.fragment_cat_list_list, container, false);
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new CatListItemAdapter(application.getList());
            application.getListFromNetwork("https://s3-us-west-1.amazonaws.com/net.raquo.images/ContentStub.json", this);
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