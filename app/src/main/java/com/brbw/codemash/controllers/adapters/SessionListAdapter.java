package com.brbw.codemash.controllers.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.brbw.codemash.CodeMashApplication;
import com.brbw.codemash.R;
import com.brbw.codemash.models.Session;
import com.brbw.codemash.models.UserPreferences;
import com.brbw.codemash.util.ViewHelper;

import java.util.List;

import static com.brbw.codemash.util.ViewHelper.findView;

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

        final Session session = getItem(position);

        ViewHelper viewHelper = new ViewHelper(convertView);
        viewHelper.setText(R.id.session_title, session.getTitle());
        viewHelper.loadImageFromUrlIntoImageView(R.id.speaker_image,
                String.format("%s?s=100", session.getSessionImageUrl()));

        CheckBox favorites = findView(convertView, R.id.session_favorite);
        addFavoritesChangeListener(session, favorites);
        favorites.setChecked(session.isFavorited());

        return convertView;
    }

    private void addFavoritesChangeListener(final Session session, CheckBox favorites) {
        favorites.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UserPreferences userPreferences = CodeMashApplication.userPreferencesInstance();

                if (isChecked != session.isFavorited()) {
                    if (isChecked) {
                        userPreferences.addFavorite(session);
                    } else {
                        userPreferences.removeFavorite(session);
                    }
                    session.isFavorited(isChecked);
                }
            }
        });
    }
}
