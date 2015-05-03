package com.muran001.android_library_sample.lib.dagger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ArticleModule {

    @Singleton
    @Provides
    public Article providesArticle() {
        return new Article(1, "title1", 300);
    }

    @Singleton
    @Provides
    public List<Article> providesArticles() {
        List<Article> articles = new ArrayList<Article>();
        articles.add(new Article(1, "title1", 300));
        articles.add(new Article(2, "title2", 300));
        articles.add(new Article(3, "title3", 300));
        articles.add(new Article(4, "title4", 300));
        return articles;
    }

    @Singleton
    @Provides
    public Feed providesFeed(List<Article> articles) {
        Feed feed = new Feed();
        feed.setArticles(articles);
        return feed;
    }
}
