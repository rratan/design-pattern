package org.rratan.concurrency;

import java.sql.Timestamp;

public class Utils {
    public static String getCurrTimeStamp(){
        return (new Timestamp(System.currentTimeMillis())).toString();
    }
}
