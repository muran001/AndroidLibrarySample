package com.muran001.android_library_sample.lib.dagger;

import java.util.List;

public class Feed {

    private List<Article> mArticles;


    public List<Article> getArticles() {
        return mArticles;
    }

    public void setArticles(List<Article> mArticles) {
        this.mArticles = mArticles;
    }
}
