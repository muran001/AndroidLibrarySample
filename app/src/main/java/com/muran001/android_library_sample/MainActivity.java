package com.muran001.android_library_sample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String PACKAGE = MainActivity.class.getPackage().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateLibraryList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void updateLibraryList() {
        final ListView libraryListView = (ListView) findViewById(R.id.library_list);
        final String[] libraryListString = getResources().getStringArray(R.array.library_names);
        ArrayAdapter<String> libraryListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, libraryListString);


        libraryListView.setAdapter(libraryListAdapter);

        libraryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final ListView listView = (ListView) parent;
                String clickedItemName = (String) listView.getItemAtPosition(position);
                String activityName = PACKAGE + "." + clickedItemName + "Activity";
                try {
                    Intent intent = new Intent(getApplicationContext(), Class.forName(activityName));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    Log.e(TAG, "Activity Not Found :" +activityName);
                    e.printStackTrace();
                }
            }
        });
    }
}
