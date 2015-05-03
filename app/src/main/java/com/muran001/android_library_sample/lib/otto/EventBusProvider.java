package com.muran001.android_library_sample.lib.otto;

import com.squareup.otto.Bus;

public class EventBusProvider {

    private static final Bus sBus = new Bus();

    private EventBusProvider() {

    }

    public static Bus getInstance() {
        return sBus;
    }
}
