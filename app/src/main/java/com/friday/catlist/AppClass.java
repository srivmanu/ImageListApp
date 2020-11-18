/*
 * Copyright (c) 2020.
 * This file is developed by Manu Srivastava.
 * All intents and usage of the file are supposed to be personal. If you
 * are using this file in your deployment code, please make sure you
 * test the file before you deploy. Else, may god help your application
 *
 * Manu Srivastava can be contacted at srivmanu@gmail.com
 */

package com.friday.catlist;

import android.app.Application;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.friday.catlist.Adapter.FavoriteAdapter;
import com.friday.catlist.Fragment.CatListFragment;
import com.friday.catlist.Fragment.FavoriteFragment;
import com.friday.catlist.Model.DataListFromJson;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AppClass extends Application {
    private static final String TAG = "AppClass";
    List<DataListFromJson.ImageItem> list = new ArrayList<>();

    public void getDummyList(int size, Fragment context) {
        String title = "DEMO";
        String url = "https://static.independent.co.uk/s3fs-public/styles/article_small/public/thumbnails/image/2016/12/19/18/sush0istock-gkrphoto.jpg";
        for (int i = 0; i < size; i++) {
            list.add(new DataListFromJson.ImageItem(title + " : " + i, url));
        }
        if (context instanceof CatListFragment)
            ((CatListFragment) context).updateAdapter();
        else if (context instanceof FavoriteFragment)
            ((FavoriteFragment) context).updateAdapter();
    }

    public void getListFromNetwork(String url, Fragment context) {
        if (list.size() == 0) {
            Log.d(TAG, "getListFromNetwork: " + list.size());

            OkHttpClient client = new OkHttpClient();
            Request req = new Request.Builder()
                    .url(url)
                    .build();
            client.newCall(req).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    getDummyList(30, context);
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    parseJson(response.body().string(), context);
                }
            });
        }
    }

    private void parseJson(String json, Fragment context) {
        list.addAll(new Gson().fromJson(json, DataListFromJson.class).getData());
        if (context instanceof CatListFragment)
            ((CatListFragment) context).updateAdapter();
        else if (context instanceof FavoriteFragment)
            ((FavoriteFragment) context).updateAdapter();
    }

    public List<DataListFromJson.ImageItem> getList() {
        return list;
    }

}
