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

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import com.friday.catlist.Model.DataListFromJson;
import com.friday.catlist.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CatListItemAdapter extends RecyclerView.Adapter<CatListItemAdapter.ViewHolder> {
    private static final String TAG = "CatListItemAdapter";
    private final List<DataListFromJson.ImageItem> mValues;

    public CatListItemAdapter(List<DataListFromJson.ImageItem> items) {
        mValues = items;
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
        holder.pos = position;
        holder.setToggle(mValues.get(position).getFav());
        holder.mIdView.setText(mValues.get(position).getTitle());
        //CallBack Added because some images were 404, I had to confirm.
        Picasso.get()
                .load(mValues.get(position).getUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.im_404)
                .into(holder.mContentView);
        holder.mToggleView.setOnCheckedChangeListener((buttonView, isChecked) ->
                mValues.get(position).setFav(isChecked)
        );
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final ImageView mContentView;
        public final ToggleButton mToggleView;
        public int pos;
        public DataListFromJson.ImageItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
            mToggleView = view.findViewById(R.id.toggleButton);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mIdView.getText() + "'";
        }

        public void setToggle(boolean val) {
            mToggleView.setChecked(val);
        }
    }
}