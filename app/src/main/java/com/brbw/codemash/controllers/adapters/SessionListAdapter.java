package com.brbw.codemash.controllers.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.brbw.codemash.R;
import com.brbw.codemash.models.Session;
import com.brbw.codemash.util.ViewHelper;

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

        return convertView;
    }
}
