package org.rratan.ratelimiter.impl.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    private static long truncate(Long time, ChronoUnit unit) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = new Date(time);
        System.out.println(date);
        String dateStr = sdf.format(date);
        System.out.println(dateStr);
        Instant instant = Instant.parse(dateStr);
        Instant returnValue = instant.truncatedTo(unit);

        return returnValue.toEpochMilli();
    }

    public static void main(String[] args){
        System.out.println(ChronoUnit.MINUTES);
        System.out.println(truncate(System.currentTimeMillis(),ChronoUnit.HOURS));
    }
}
