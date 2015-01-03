package com.brbw.codemash.controllers.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.brbw.codemash.models.Session;
import com.brbw.codemash.util.ViewHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SessionListAdapter extends ArrayAdapter<Session> {

    private static final int LAYOUT_ID = R.layout.adapter_session_list_item;

    public SessionListAdapter(Context context, List<Session> sessions) {
        super(context, LAYOUT_ID, sessions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ViewHelper.inflateForAdapter(getContext(), parent, LAYOUT_ID);
        }

        Session session = getItem(position);

        ViewHelper viewHelper = new ViewHelper(convertView);
        viewHelper.setText(R.id.session_title, session.getTitle());
        viewHelper.loadImageFromUrlIntoImageView(R.id.speaker_image,
                String.format("%s?s=100", session.getSessionImageUrl()));


        ImageView imageView = (ImageView) convertView.findViewById(R.id.speaker_image);
        String firstSpeakerUrl = session.getSpeakers().get(0).getGravatarUrl();

        String imageUrl;
        if (firstSpeakerUrl != null && !firstSpeakerUrl.startsWith("http")) {
            imageUrl = String.format("http:%s?s=100", firstSpeakerUrl);
        } else {
            imageUrl = String.format("%s?s=100", firstSpeakerUrl);
        }

        Picasso.with(getContext())
                .load(imageUrl)
                .into(imageView);

        return convertView;
    }
}
