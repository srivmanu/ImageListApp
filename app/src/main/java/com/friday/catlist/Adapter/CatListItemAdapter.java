/*
 * Copyright (c) 2020.
 * This file is developed by Manu Srivastava.
 * All intents and usage of the file are supposed to be personal. If you
 * are using this file in your deployment code, please make sure you
 * test the file before you deploy. Else, may god help your application
 *
 * Manu Srivastava can be contacted at srivmanu@gmail.com
 */

package com.friday.catlist.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.friday.catlist.BuildConfig;
import com.friday.catlist.Model.DataListFromJson;
import com.friday.catlist.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CatListItemAdapter extends RecyclerView.Adapter<CatListItemAdapter.ViewHolder> {
    private static final String TAG = "CatListItemAdapter";
    private final List<DataListFromJson.ImageItem> mValues;
    private final Context mContext;

    public CatListItemAdapter(List<DataListFromJson.ImageItem> items, Context context) {
        mValues = items;
        mContext = context;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cat_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getTitle());
        //CallBack Added because some images were 404, I had to confirm.
        Picasso.get()
                .load(mValues.get(position).getUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.im_404)
                .into(holder.mContentView, new Callback() {

                    @Override
                    public void onSuccess() {
                        if (BuildConfig.DEBUG)
                            Log.d(TAG, "onSuccess: Loaded " + mValues.get(position).getTitle());
                    }

                    @Override
                    public void onError(Exception e) {
                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, "onError: " + e.getMessage());
                            Log.d(TAG, "onError: " + mValues.get(position).getUrl());
                        }
                    }
                });
        holder.mView.setOnClickListener(v -> {
            if (BuildConfig.DEBUG)
                Log.d(TAG, "onClick: " + holder.mItem.getTitle());
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final ImageView mContentView;
        public DataListFromJson.ImageItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mIdView.getText() + "'";
        }
    }
}