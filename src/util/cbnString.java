package util;

public class cbnString {
	/**
	 * @kami
	 * 合并字符串
	 * a+b+c
	 * abc
	 * */
	//DN:ColN
	//Dat:00000	uv:10
	public static String cbnStr(String dataName, String coll, String dat, Long uview) {
		// TODO Auto-generated method stub
		String str = dataName+":"+coll+ "\ndate:"+dat+"\tuv:"+uview+"\n";
		return str;
	}
	
	//DN:ColN
	//Dat:00000	uv:10	pv:132
	public static String cbnStr(String dataName, String coll, String dat, Long uview	, Long pview) {
		// TODO Auto-generated method stub
		String str = dataName+":"+coll+ "\ndate:"+dat+"\tuv:"+uview+"\tpv:"+pview+"\n";
		return str;
	}

}
