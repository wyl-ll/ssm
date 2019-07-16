package cn.itcast.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String DateToString(Date date, String str){
        SimpleDateFormat sdf=new SimpleDateFormat(str);
        String format = sdf.format(date);
        return format;
    }

    public static Date StringToDate(String s, String str) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(str);

        Date parse = sdf.parse(s);

        return parse;
    }
}
