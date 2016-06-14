package cn.cjam.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jam on 2016/6/14.
 */
public class TimeUtil {


    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public static String addDay(String s, int n) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(s));
            calendar.add(Calendar.DATE, n);//增加一天
            return sdf.format(calendar.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public static String today(){
        return sdf.format(new Date());
    }


    public static void main(String[] args){
        String today = today();
        String tommorow = addDay(today, 1);
        System.out.println(today+","+tommorow);
    }
}
