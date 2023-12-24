package com.nmm.banking.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public static final String TIME_ZONE = "GMT+05:30";
    public static DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");

    public static String getCurrentTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date currentTime = new Date();
        String dateString = simpleDateFormat.format(currentTime);
        return dateString;
    }

    public static LocalDateTime getFormattedDateTime(String dateTime) {
        LocalDateTime localDateTime = null;
        try {
            localDateTime = LocalDateTime.parse(dateTime,FORMAT);
        }catch (Exception e){

        }
        return localDateTime;
    }
}
