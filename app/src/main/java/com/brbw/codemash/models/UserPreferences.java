package com.brbw.codemash.models;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserPreferences {

    private static final String USER_PREFERENCES_KEY = "CodeMashPreferences2015";
    private static final String FAVORITES = "FAVORITES";

    private final Context context;
    private static boolean favoritesOnly;

    public UserPreferences(Context context) {
        this.context = context;
    }


    public void addFavorite(Session currentSession) {
        Set<String> favorites = getFavorites();
        favorites.add(String.valueOf(currentSession.getId()));
        getEditor().putStringSet(FAVORITES, favorites).apply();
    }

    public void removeFavorite(Session currentSession) {
        Set<String> favorites = getFavorites();
        favorites.remove(String.valueOf(currentSession.getId()));
        getEditor().putStringSet(FAVORITES, favorites).apply();
    }

    private SharedPreferences.Editor getEditor() {
        return context.getSharedPreferences(USER_PREFERENCES_KEY, Context.MODE_PRIVATE).edit();
    }

    private Set<String> getFavorites() {
        SharedPreferences preferences = context.getSharedPreferences(USER_PREFERENCES_KEY, Context.MODE_PRIVATE);
        return preferences.getStringSet(FAVORITES, new HashSet<String>());
    }

    public boolean isFavoritesOnly() {
        return UserPreferences.favoritesOnly;
    }

    public void isFavoritesOnly(boolean favoritesOnly) {
        UserPreferences.favoritesOnly = favoritesOnly;
    }

    public List<Integer> getFavoriteSessionIds() {
        List<Integer> favoriteIds = new ArrayList<>();
        for (String favorite : getFavorites()) {
            favoriteIds.add(Integer.parseInt(favorite));
        }
        return favoriteIds;
    }
}
