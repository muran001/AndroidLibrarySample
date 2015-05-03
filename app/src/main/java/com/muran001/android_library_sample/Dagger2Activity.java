package com.muran001.android_library_sample;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.muran001.android_library_sample.lib.dagger.Article;
import com.muran001.android_library_sample.lib.dagger.ArticleComponent;
import com.muran001.android_library_sample.lib.dagger.ArticleModule;
import com.muran001.android_library_sample.lib.dagger.DaggerArticleComponent;
import com.muran001.android_library_sample.lib.dagger.Feed;

import java.util.List;

import javax.inject.Inject;


public class Dagger2Activity extends BaseLibraryActivity {

    @Inject
    Feed mFeed;

    @Inject
    Article mArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);



        ArticleComponent aComponent = DaggerArticleComponent.builder().articleModule(new ArticleModule()).build();
        aComponent.inject(this);
        Toast.makeText(this, mArticle.toString(), Toast.LENGTH_LONG).show();

        updateListView();
    }

    private void updateListView() {
        ListView articleList = (ListView) findViewById(R.id.feed_article_list);

        ContributorAdapter adapter = new ContributorAdapter(this, mFeed.getArticles());
        articleList.setAdapter(adapter);
    }


    // for ListView

    private class ContributorAdapter extends ArrayAdapter<Article> {
        public ContributorAdapter(Context context, List<Article> articles) {
            super(context, 0, articles);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Article article = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.dagger2_item, parent, false);
            }
            TextView titleTextView = (TextView) convertView.findViewById(R.id.title);
            TextView priceTextView = (TextView) convertView.findViewById(R.id.price);
            titleTextView.setText(article.getTitle());
            priceTextView.setText("@ " + String.valueOf(article.getPrice()));
            return convertView;
        }
    }
}
