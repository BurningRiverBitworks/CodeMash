package com.brbw.codemash.controllers.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.brbw.codemash.R;
import com.brbw.codemash.controllers.fragments.PlaceholderFragment;

import java.util.Locale;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final int TOTAL_NUMBER_OF_CONFERENCE_DAYS = 4;
    private Context context;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return TOTAL_NUMBER_OF_CONFERENCE_DAYS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return context.getString(R.string.tuesday).toUpperCase(l);
            case 1:
                return context.getString(R.string.wednesday).toUpperCase(l);
            case 2:
                return context.getString(R.string.thursday).toUpperCase(l);
            case 3:
                return context.getString(R.string.friday).toUpperCase(l);
        }
        return "";
    }
}
