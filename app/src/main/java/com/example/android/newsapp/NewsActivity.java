package com.example.android.newsapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderCallbacks<List<News>> {
    private static final int NEWS_LOADER_ID = 1;
    private static final String GUARDIAN_NEWS_REQUEST_URL =
            "https://content.guardianapis.com/search?api-key=4214e34c-f37a-40bc-833d-23b0bd9416d2";
    final String LOG_MSG = NewsActivity.class.getSimpleName() + "\t";
    private NewsAdapter adapter;
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v(LOG_MSG, "Activity created");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null &&
                networkInfo.isConnectedOrConnecting();

        LoaderManager loaderManager = getLoaderManager();
        mEmptyStateTextView = findViewById(R.id.empty_view);
        ListView listView = findViewById(R.id.list);

        if (isConnected) {

            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
            Log.v(LOG_MSG, "Call Loader");

            listView.setEmptyView(mEmptyStateTextView);
            adapter = new NewsAdapter(this, new ArrayList<News>());

            listView.setAdapter(adapter);
        } else {

            View loadingIndicator = findViewById(R.id.progress_view);
            loadingIndicator.setVisibility(View.GONE);
            Log.v(LOG_MSG, "Check Internet Connection");
            mEmptyStateTextView.setText(R.string.no_internet);
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News currentNews = adapter.getItem(position);

                assert currentNews != null;
                Uri NewsUri = Uri.parse(currentNews.getWebUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, NewsUri);
                startActivity(websiteIntent);
            }
        });
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        Log.v(LOG_MSG, "Create Loader");
        return new NewsLoader(this, GUARDIAN_NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        Log.v(LOG_MSG, "Finish Loader");
        ProgressBar progressView = findViewById(R.id.progress_view);
        progressView.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.no_news);
        adapter.clear();

        if (news != null && !news.isEmpty()) {
            adapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        Log.v(LOG_MSG, "Reset Loader");
        adapter.clear();
    }
}