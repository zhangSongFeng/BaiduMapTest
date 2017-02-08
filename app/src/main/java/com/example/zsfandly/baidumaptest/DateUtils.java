package com.example.zsfandly.baidumaptest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zsfandly on 2017/2/7.
 */

public class DateUtils {
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
    public static String toDate(Date date){
        return sdf.format(date);
    }
}
