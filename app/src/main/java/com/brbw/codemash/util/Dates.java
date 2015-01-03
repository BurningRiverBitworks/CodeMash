package com.brbw.codemash.util;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.brbw.codemash.util.Strings.isNullOrEmpty;
import static com.brbw.codemash.util.Strings.nullSafeStringFrom;

public class Dates {

    public static Date dateFrom(JSONObject json, String key, String format) {

        String time = nullSafeStringFrom(json, key);

        if (isNullOrEmpty(time)) {
            return new Date(0);
        }

        return dateOrDefault(format, time, 0);
    }

    public static String formatedDate(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static Date dateOrDefault(String format, String time, int milliseconds) {
        try {
            return new SimpleDateFormat(format).parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date(milliseconds);
        }
    }
}
