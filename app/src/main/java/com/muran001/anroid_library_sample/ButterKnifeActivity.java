package com.muran001.anroid_library_sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android_sample.muran001.com.androidsample.R;

public class ButterKnifeActivity extends BaseLibraryActivity {

    private static final String TAG = ButterKnifeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);
    }
}
