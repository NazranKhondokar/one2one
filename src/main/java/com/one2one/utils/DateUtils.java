package com.one2one.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static java.util.Objects.isNull;

public class DateUtils {

    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy hh:mm a";

    public static String format(Date date, String format) {
        return isNull(date) ? null : new SimpleDateFormat(format).format(date);
    }

    public static Date asDate(String date) {
        return java.sql.Date.valueOf(date);
    }

    public static Date asDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }
}
