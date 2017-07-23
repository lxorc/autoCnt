package count;

import java.text.SimpleDateFormat;
import java.util.Date;

public class getDate {
	//获取几天前的年月日
	public static String getDay(int day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Long time = 86400000L*day;
		return sdf.format(new Date().getTime()-time);
	}
	
	//获取几天前的年月日
	public static String getMonth(int month) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Long time = 86400000L*30*month;
		return sdf.format(new Date().getTime()-time);
	}
	
}
