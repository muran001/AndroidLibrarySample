package com.muran001.anroid_library_sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.muran001.anroid_library_sample.lib.otto.ChildButtonClickEvent;
import com.muran001.anroid_library_sample.lib.otto.EventBusProvider;
import com.muran001.anroid_library_sample.lib.otto.ParentButtonClickEvent;
import com.squareup.otto.Subscribe;

import android_sample.muran001.com.androidsample.R;

public class OttoActivity extends BaseLibraryActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otto);
        getSupportFragmentManager().beginTransaction().add(R.id.container, new OttoChildFragment()).commit();

        final Button button = (Button) findViewById(R.id.parent_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusProvider.getInstance().post(new ParentButtonClickEvent(button.getText() + " tapped"));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void subscribe(ChildButtonClickEvent event) {
        Toast.makeText(this, "ACTIVITY says: " + event.message, Toast.LENGTH_LONG).show();
    }
}

