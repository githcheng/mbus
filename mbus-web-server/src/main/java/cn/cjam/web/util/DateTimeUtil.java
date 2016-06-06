package cn.cjam.web.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by cheng on 2015/10/24.
 */
public class DateTimeUtil {

    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static DateFormat getDateFormat(){
        return dateFormat;
    }
}
