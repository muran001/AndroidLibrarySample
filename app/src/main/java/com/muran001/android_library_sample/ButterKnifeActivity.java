package com.muran001.android_library_sample;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class ButterKnifeActivity extends BaseLibraryActivity {

    private static final String TAG = ButterKnifeActivity.class.getSimpleName();

    @InjectView(R.id.input_id_text)
    EditText inputIdText;

    @InjectView(R.id.input_pass_text)
    EditText inputPassText;

    @InjectView(R.id.display_button)
    Button displayButton;

    @InjectView(R.id.clear_button)
    Button clearButton;

    @InjectView(R.id.result_id_text)
    TextView resultIdText;

    @InjectView(R.id.result_pass_text)
    TextView resultPassText;

    @InjectView(R.id.list_view)
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);
        ButterKnife.inject(this);

        final String[] libraryListString = getResources().getStringArray(R.array.library_names);
        ArrayAdapter<String> libraryListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, libraryListString);

        listView.setAdapter(libraryListAdapter);
    }


    @OnClick(R.id.display_button)
    public void display(Button displayButton) {
        resultIdText.setText(inputIdText.getText());
        resultPassText.setText(inputPassText.getText());
    }

    @OnClick(R.id.clear_button)
    public void clear() {
        resultIdText.setText("");
        resultPassText.setText("");
    }

    @OnClick({ R.id.left_button, R.id.center_button, R.id.right_button})
    public void displayToast(Button anyButton) {
        Toast.makeText(this, anyButton.getText() + " Button is clicked!", Toast.LENGTH_SHORT).show();
    }

    @OnItemClick(R.id.list_view)
    public void listViewClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "no." + position + " item is clicked", Toast.LENGTH_SHORT).show();
    }
}
