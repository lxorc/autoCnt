package count;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import autoSav.savMongoData;
import net.sf.json.JSONObject;
import util.cbnString;
import util.conMon;
import util.getMonDate;
import util.keyVal;

public class getMonDat {
	public static Map<String, String> map = new HashMap<String, String>();
	public static HashSet<String> set = new HashSet<String>();

	//获取集合
	public static DBCursor queryOne(String key, String value, String db, String coll) {

		//集群测试
		DBCollection collection = null;
		BasicDBObject queryObject = new BasicDBObject();
		if (db.equals("xng_stat")) {
			collection = conMon.mongoConn29999(db, coll);
			queryObject.put(key, value);
		} else if (db.equals("xngStat")) {
			collection = conMon.mongoConn30001(db, coll);
			queryObject.put(key, Long.parseLong(value));
		} else {
			//本地测试
			collection = conMon.mongoConn(db, coll);
			queryObject.put(key, Long.parseLong(value));
		}
		DBCursor cursor = collection.find(queryObject);
		return cursor;
	}

	//json普通解析 获取coll + date + uv
	public static String toJson(String... str) {
//		String dataName,String coll,String bson,String uv,String pv
		JSONObject jsonObject = JSONObject.fromObject(str[2]);
		String date = getMonDate.getDay(1, keyVal.ymdNoSepar);
		Long uview = jsonObject.getLong(str[3]);
		String line = cbnString.cbnStr(str[0], str[1], date, uview);
		if (str.length == 4) {
			savMongoData.sav(str[0], date, uview);
		} else if (str.length == 5) {
			Long pview = jsonObject.getLong(str[4]);
			line = cbnString.cbnStr(str[0], str[1], date, uview, pview);
			savMongoData.sav(str[0], date, uview, pview);
		}
		return line;
	}

	//		String dataName,String coll,String bson,String uv,String pv
	//(dataName, collOne, collTwo, collStrs[i], str[0], str[1]))
	public static String toJsonSum(String dataName, String bson[], String uv, String pv) {
		Long uview = 0L;
		Long pview = 0L;
		for (String strs:bson){
			JSONObject jsonObject = JSONObject.fromObject(strs);
			uview += jsonObject.getLong(uv);
			pview += jsonObject.getLong(pv);
		}
		String date = getMonDate.getDay(1, keyVal.ymdNoSepar);
		String line = cbnString.cbnStr(dataName, dataName, date, uview, pview);
		savMongoData.sav(dataName, date, uview, pview);
		return line;
	}

	//获取前一天的数据 并存入 map
	public static void mongoFind(String coll, String db) {

		DBCursor cursor = null;
		String date = "";
		try {
			if (db.equals(keyVal.mongo30001)) {
				//在mongo上是20170701
				date = getMonDate.getDay(1, keyVal.ymdNoSepar);
				cursor = queryOne("date", date, db, coll);
			} else if (db.equals(keyVal.mongo29999)) {
				//在mongo2999是2017-07-01
				//一天前的数据
				date = (getMonDate.getDay(1, keyVal.ymd));
				cursor = queryOne("_id", date, db, coll);
			}
			while (cursor.hasNext()) {
				String bson = cursor.next().toString();
//				System.out.println(bson);
				map.put(coll, bson);
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	//返回需要的数据
	public static String getResult(String dataName, String coll, String... str) {
		String toJson = new String();
		String bson = map.get(coll);
		if (bson != null) {
			try {
				if (str.length == 2) {
					toJson = toJson.concat(toJson(dataName, coll, bson, str[0], str[1]));
				} else if (str.length == 1) {
					toJson = toJson.concat(toJson(dataName, coll, bson, str[0]));
				}
				set.add(toJson);
				return toJson;
			} catch (NullPointerException e) {
				// TODO: handle exception
				System.out.println(e.getMessage() + e.getClass().getName());
				System.out.println("没有在" + coll + "找到数据");
			}
		}
		return bson;
	}

	//返回需要的数据
	//("分享回流"，"coll1","coll2","uv","pv")
	public static String getResultSum(String dataName, String collOne, String collTwo, String... str) {
		String toJson = new String();
		String[] collStrs = {map.get(collOne), map.get(collTwo)};
		try {
			toJson = toJson.concat(toJsonSum(dataName, collStrs, str[0], str[1]));
			set.add(toJson);
			return toJson;
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println(e.getMessage() + e.getClass().getName());
		}
		return toJson;
	}
}
