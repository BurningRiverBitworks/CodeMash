package com.brbw.codemash.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.brbw.codemash.util.Strings.nullSafeStringFrom;

public class ViewHelper {

    private final View view;

    public ViewHelper(View view) {
        this.view = view;
    }

    public ViewHelper setText(int textViewId, String text) {
        TextView textView = findView(view, textViewId);
        textView.setText(text);
        return this;
    }

    public static View inflateForAdapter(Context context, ViewGroup parent, int layoutId) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(layoutId, parent, false);
    }

    public void loadImageFromUrlIntoImageView(int imageViewId, String url) {
        ImageView imageView = findView(view, imageViewId);
        Picasso.with(view.getContext())
                .load(properUrl(url))
                .into(imageView);
    }

    private String properUrl(String imageUrl) {
        String url = nullSafeStringFrom(imageUrl);
        if (!url.startsWith("http:") && !url.startsWith("https:")) {
            url = "http:" + url;
        }
        return url;
    }

    public static <T extends View> T findView(View view, int viewId) {
        return (T) view.findViewById(viewId);
    }
}
