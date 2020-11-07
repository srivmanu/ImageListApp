/*
 * Copyright (c) 2020.
 * This file is developed by Manu Srivastava.
 * All intents and usage of the file are supposed to be personal. If you
 * are using this file in your deployment code, please make sure you
 * test the file before you deploy. Else, may god help your application
 *
 * Manu Srivastava can be contacted at srivmanu@gmail.com
 */

package com.friday.catlist.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.friday.catlist.Fragment.CatListFragment;
import com.friday.catlist.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CatListFragment fragment = CatListFragment.newInstance(2);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_holder, fragment).commit();
    }
}