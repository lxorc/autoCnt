package util;

/**
 * Created by kami on 2017/8/6.
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class getMonDate {
    //获取几天前的年月日
    public static String getDay(int day,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Long time = 86400000L*day;
        return sdf.format(new Date().getTime()-time);
    }

    //获取几天前的年月日
    public static String getMont(int month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Long time = 86400000L*30*month;
        return sdf.format(new Date().getTime()-time);
    }
}