package com.muran001.anroid_library_sample;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import android_sample.muran001.com.androidsample.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

public class RetrofitActivity extends BaseLibraryActivity  {

    private static final String API_URL = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        final GitHub client = buildRestAdapter().create(GitHub.class);
        final ContributorCallBack callback = new ContributorCallBack(new Handler());

        Button loadContributorsButton = (Button) findViewById(R.id.load_contributors_button);
        loadContributorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    client.contributors("square", "retrofit", callback);
                } catch (RetrofitError e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void updateContributorListView(List<Contributor> contributors) {
        ListView contributorListView = (ListView) findViewById(R.id.contributor_list_view);

        ContributorAdapter adapter = new ContributorAdapter(this, contributors);
        contributorListView.setAdapter(adapter);
    }


    // retrofit implementation

    private RestAdapter buildRestAdapter() {
        return new RestAdapter.Builder().setEndpoint(API_URL).build();
    }

    public class Contributor {
        String login;
        int contributions;
    }

    interface GitHub {
        // set callbacks and return void for async task
        @GET("/repos/{owner}/{repo}/contributors")
        void contributors(@Path("owner") String owner, @Path("repo") String repo, Callback<List<Contributor>> callback);
    }

    class ContributorCallBack implements Callback<List<Contributor>> {
        private Handler mHandler;
        public ContributorCallBack(Handler handler) {
            mHandler = handler;
        }
        @Override
        public void failure(RetrofitError error) {
        }

        @Override
        public void success(final List<Contributor> contributors, Response response) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    updateContributorListView(contributors);
                }
            });
        }
    }


    // for listview

    private class ContributorAdapter extends ArrayAdapter<Contributor> {
        public ContributorAdapter(Context context, List<Contributor> contributors) {
            super(context, 0, contributors);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Contributor contributor = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.retrofit_item, parent, false);
            }
            TextView loginTextView = (TextView) convertView.findViewById(R.id.login);
            TextView contributionsTextView = (TextView) convertView.findViewById(R.id.contributions);
            loginTextView.setText(contributor.login);
            contributionsTextView.setText("@ " + String.valueOf(contributor.contributions));
            return convertView;
        }
    }
}
