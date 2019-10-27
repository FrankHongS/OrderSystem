package com.qinyuan.ordersystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

        public static Date parseDateString(String dateString) throws ParseException {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateString);
        }

        public static String parseDateToString(Date date){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(date);
        }
}
