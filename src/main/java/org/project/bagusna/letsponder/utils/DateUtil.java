package org.project.bagusna.letsponder.utils;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    public static String toHumanDate(Date date) {
        return new SimpleDateFormat("d MMM yyyy").format(date);
    }

    public static String toHumanDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, formatter);
        return zonedDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }
}
