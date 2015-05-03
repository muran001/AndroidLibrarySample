package com.muran001.android_library_sample.lib.dagger;

public class Article {

    private int mId;

    private String mTitle;

    private int mPrice;

    public Article() {

    }

    public Article(int id, String title, int price) {
        mId = id;
        mTitle = title;
        mPrice = price;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String toString() {
        return mTitle + " \\" + mPrice;
    }
}
