package com.example.android.newsapp;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private final String LOG_MSG = NewsLoader.class.getSimpleName() + "\t";

    private String mWebUrl;


    NewsLoader(Context context, String url) {
        super(context);
        mWebUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.v(LOG_MSG, "Start Loader");

        forceLoad();
    }


    @Override
    public List<News> loadInBackground() {
        Log.v(LOG_MSG, "Background Loader");

        if (mWebUrl == null) {
            return null;
        }

        Log.v(LOG_MSG, "Fetching Data List");

        List news = Utilties.fetchNewsData(mWebUrl);
        return news;
    }
}
