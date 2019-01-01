package com.android.africannewsv4;


import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import com.android.africannewsv4.NetworkUtils;
import com.android.africannewsv4.NewsData;

import org.json.JSONException;

import java.util.ArrayList;

public class NewsLoader extends AsyncTaskLoader<ArrayList<NewsData>> {

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Override
    public ArrayList<NewsData> loadInBackground() {
        if(mUrl == null) {
            return null;
        }
        ArrayList<NewsData> newsDataArrayList = null;
        try {
            newsDataArrayList = NetworkUtils.fetchNewsData(mUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsDataArrayList;
    }
}
