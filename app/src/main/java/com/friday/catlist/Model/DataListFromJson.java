/*
 * Copyright (c) 2020.
 * This file is developed by Manu Srivastava.
 * All intents and usage of the file are supposed to be personal. If you
 * are using this file in your deployment code, please make sure you
 * test the file before you deploy. Else, may god help your application
 *
 * Manu Srivastava can be contacted at srivmanu@gmail.com
 */

package com.friday.catlist.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataListFromJson {

    @SerializedName("data")
    @Expose
    private List<ImageItem> data = null;

    public List<ImageItem> getData() {
        return data;
    }

    public void setData(List<ImageItem> data) {
        this.data = data;
    }

    public static class ImageItem {
        String title;
        String url;
        boolean fav = false;

        public ImageItem(String title, String url) {
            this.title = title;
            this.url = url;
        }

        public Boolean getFav() {
            return fav;
        }

        public void setFav(boolean fav) {
            this.fav = fav;
        }

        public String getTitle() {
            return title;
        }

        public String getUrl() {
            return url;
        }
    }
}
