package com.brbw.codemash.models;

import java.util.Calendar;
import java.util.Date;

public enum Day {

    TUESDAY(Calendar.TUESDAY),
    WEDNESDAY(Calendar.WEDNESDAY),
    THURSDAY(Calendar.THURSDAY),
    FRIDAY(Calendar.FRIDAY);

    private final int calendarDayOfWeek;

    private Day(int calendarDayOfWeek) {
        this.calendarDayOfWeek = calendarDayOfWeek;
    }

    public boolean isOn(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendarDayOfWeek == calendar.get(Calendar.DAY_OF_WEEK);
    }
}


