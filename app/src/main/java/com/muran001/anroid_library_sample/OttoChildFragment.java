package com.muran001.anroid_library_sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.muran001.anroid_library_sample.lib.otto.ChildButtonClickEvent;
import com.muran001.anroid_library_sample.lib.otto.EventBusProvider;
import com.muran001.anroid_library_sample.lib.otto.ParentButtonClickEvent;
import com.squareup.otto.Subscribe;

import android_sample.muran001.com.androidsample.R;

public class OttoChildFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.otto_child_fragment, container, false);

        final Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusProvider.getInstance().post(new ChildButtonClickEvent(button.getText() + " tapped"));
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void subscribe(ParentButtonClickEvent event) {
        Toast.makeText(this.getActivity(), "FRAGMENT says: " + event.message, Toast.LENGTH_LONG).show();
    }
}
