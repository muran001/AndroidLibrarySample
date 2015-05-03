package com.muran001.android_library_sample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import timber.log.Timber;

public class TimberActivity extends BaseLibraryActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timber);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }

        setClickListener((Button) findViewById(R.id.load_contributors_button));
        setClickListener((Button) findViewById(R.id.secondButton));
    }

    private void setClickListener(final Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "button " + button.getText() + " click event is logged", Toast.LENGTH_SHORT).show();
                Timber.tag(button.getText().toString());
                Timber.d("button " + button.getText() + " is clicked");
            }
        });
    }

    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }


}

class FakeCrashLibrary {
    private FakeCrashLibrary() {
        throw new AssertionError("No instances.");
    }

    public static void log(int priority, String tag, String message) {
        Log.d(tag, message);
    }

    public static void logWarning(Throwable t) {
        t.printStackTrace();
    }

    public static void logError(Throwable t) {
        t.printStackTrace();
    }
}