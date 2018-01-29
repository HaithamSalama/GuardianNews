package com.example.android.newsapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class NewsAdapter extends ArrayAdapter<News> {

    NewsAdapter(Context context, List<News> places) {
        super(context, 0, places);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_news, parent, false);
        }

        News currentPlace = getItem(position);

        TextView categoryTextView = listItemView.findViewById(R.id.sectionName_txt);
        TextView titleTextView = listItemView.findViewById(R.id.title_txt);
        TextView dateTextView = listItemView.findViewById(R.id.date_txt);
        TextView timeTextView = listItemView.findViewById(R.id.time_txt);

        categoryTextView.setText(currentPlace.getSectionId());
        titleTextView.setText(currentPlace.getWebTitle());
        dateTextView.setText(currentPlace.getWebPublicationDate());
        timeTextView.setText(currentPlace.getWebPublicationTime());


        GradientDrawable categoryCircle = (GradientDrawable) categoryTextView.getBackground();
        int categoryColor = getCategoryColor(currentPlace.getPillarName());

        categoryCircle.setColor(categoryColor);
        return listItemView;
    }

    private int getCategoryColor(String category) {
        int categoryColorResourceId;

        switch (category) {
            case "Sport":
                categoryColorResourceId = R.color.sports;
                break;
            case "News":
                categoryColorResourceId = R.color.news;
                break;
            case "Arts":
                categoryColorResourceId = R.color.arts;
                break;
            case "Opinion":
                categoryColorResourceId = R.color.opinion;
                break;
            case "Lifestyle":
                categoryColorResourceId = R.color.lifestyle;
                break;
            default:
                categoryColorResourceId = R.color.news;
                break;
        }

        return ContextCompat.getColor(getContext(), categoryColorResourceId);
    }
}
