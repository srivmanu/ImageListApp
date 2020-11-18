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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.friday.catlist.Model.DataListFromJson;
import com.friday.catlist.R;
import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private final List<DataListFromJson.ImageItem> mValues;

    public FavoriteAdapter(List<DataListFromJson.ImageItem> favList) {
        this.mValues = favList;
        cleanList();
    }

    private void cleanList() {
        Iterator<DataListFromJson.ImageItem> iter = mValues.iterator();
        while (iter.hasNext()) {
            DataListFromJson.ImageItem p = iter.next();
            if (!p.getFav()) iter.remove();
        }
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cat_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.pos = position;
        holder.mIdView.setText(mValues.get(position).getTitle());
        holder.mToggleView.setVisibility(View.GONE);
        Picasso.get()
                .load(mValues.get(position).getUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.im_404)
                .into(holder.mContentView);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public List<DataListFromJson.ImageItem> getList() {
        return mValues;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final ImageView mContentView;
        public final ToggleButton mToggleView;
        public int pos;
        public DataListFromJson.ImageItem mItem;

        public ViewHolder(@NonNull View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
            mToggleView = view.findViewById(R.id.toggleButton);
        }
    }
}
