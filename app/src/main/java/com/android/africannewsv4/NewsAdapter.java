package com.android.africannewsv4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<NewsData> {
    public NewsAdapter(@NonNull Context context, int resource, ArrayList<NewsData> newsDataArrayList) {
        super(context, 0, newsDataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newsDataArrayList = convertView;
        if(newsDataArrayList == null) {
            newsDataArrayList = LayoutInflater.from(getContext()).inflate(R.layout.item_list_view, parent, false);

        }
        NewsData newsData = getItem(position);

        /**
         * webTitle text view
         */
        TextView webTitle = newsDataArrayList.findViewById(R.id.webTitle);
        webTitle.setText(newsData.webTitle);

        TextView sectionName = newsDataArrayList.findViewById(R.id.sectionName);
        sectionName.setText(newsData.sectionName);

        TextView articleDate = newsDataArrayList.findViewById(R.id.articleDate);
        articleDate.setText(newsData.articleDate);

        TextView authorName = newsDataArrayList.findViewById(R.id.authorName);
        authorName.setText(newsData.authorName);

        TextView webUrl = newsDataArrayList.findViewById(R.id.webUrl);
        webUrl.setText(newsData.webUrl);

        return newsDataArrayList;

    }
}
