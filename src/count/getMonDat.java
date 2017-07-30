package count;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import autoSav.savMongoData;
import net.sf.json.JSONObject;

public class getMonDat {
	public static String str =  new String();
    public static Map<String, String> map = new HashMap<String, String>();
    public static HashSet<String> set = new HashSet<String>();

    //获取集合
    public  static  DBCursor queryOne(String key,Long value,String db,String coll){

    	//集群测试
    	DBCollection collection = null;
    	if (db.equals("xng_statt")) {
        	collection= conMon.mongoConn29999(db, coll);
		}else if (db.equals("xngStat")) {
	    	collection= conMon.mongoConn30001(db, coll);
		}else {
	    	//本地测试
	        collection= conMon.mongoConn(db, coll);
		}
        BasicDBObject queryObject=new BasicDBObject();
        queryObject.put(key, value);
        DBCursor cursor = collection.find(queryObject);
        return cursor;
    }

	//json 解析 获取coll + date + uv
	public  static  String toJson(String...str){
//		String dataName,String coll,String bson,String uv,String pv
		JSONObject jsonObject = JSONObject.fromObject(str[2]);
//		String dataName = str[0];
//		String coll = str[1];
//		String bson = str[2];
//		String uv = ;
		String date = jsonObject.getString(keyVal.Dat);
		Long uview = jsonObject.getLong(str [3]);
		String line = cbnString.cbnStr(str[0], str[1], date, uview);
		if (str.length<3) {
			savMongoData.sav(str[0], date, uview);
		}
		else if (str.length>3){
			Long pview = jsonObject.getLong(str[4]);
			line = cbnString.cbnStr(str[0], str[1], date, uview,pview);
			savMongoData.sav(str[0],date,uview,pview);
		}
		return line;
	}

    //获取前一天的数据 并存入 map
    public  static void  mongoFind(String coll,String db){
    		DBCursor cursor;
            try{
            	//一天前的数据
    			Long date = Long.parseLong(getDate.getDay(1));
	                cursor = queryOne("date", date,db,coll);
                while (cursor.hasNext()){
                    String bson = cursor.next().toString();
                    map.put(coll, bson);            
                }
            }catch(Exception e){
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }
    }

    //返回需要的数据
    public static String getResult(String dataName,String coll,String...str) {
		String toJson = new String();
        String bson = map.get(coll);
    	try {
			if (str.length==2){
				toJson=toJson.concat(toJson(dataName,coll,bson,str[0],str[1]));
			}else if (str.length==1){
				toJson=toJson.concat(toJson(dataName,coll,bson,str[0]));
			}
            set.add(toJson);
        	return toJson;
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println(e.getMessage()+e.getClass().getName());
			System.out.println("没有在"+coll+"找到数据");
		}
		return bson;
	}
    
    public static String find() {

		//29999 不区分wa 和 ma
		mongoFind(keyVal.DSta,keyVal.mongo29999);
		getResult("总用户制作数\t", keyVal.DSta, keyVal.AlbPer);

		//3000miniAPP 日统计
		mongoFind(keyVal.MaUpd, keyVal.mongo30001);
		getResult("ma用户首次上传照片\t",keyVal.MaUpd,keyVal.Uview);
		
		mongoFind(keyVal.MaCom,keyVal.mongo30001);
		getResult("ma用户首次提交影集\t",keyVal.MaCom,keyVal.Uview);

		mongoFind(keyVal.MaReadSharAl, keyVal.mongo30001);
		getResult("ma读者分享\t",keyVal.MaReadSharAl,keyVal.Uview,keyVal.Pview);
		
    	mongoFind(keyVal.MaAuthSharAl,keyVal.mongo30001);
    	getResult("ma作者分享\t",keyVal.MaAuthSharAl,keyVal.Uview,keyVal.Pview);
		
		mongoFind(keyVal.MaDfqsAuthAl,keyVal.mongo30001);
		getResult("ma作者通过二维码社交唤醒回流\t", keyVal.MaDfqsAuthAl, keyVal.Uview, keyVal.Pview);
		
		mongoFind(keyVal.MaDfqsReadAl, keyVal.mongo30001);
		getResult("ma读者通过二维码社交唤醒回流\t", keyVal.MaDfqsReadAl, keyVal.Uview, keyVal.Pview);
		
		mongoFind(keyVal.MaDfsReadAl, keyVal.mongo30001);
		getResult("ma读者通过链接社交唤醒回流\t", keyVal.MaDfsReadAl, keyVal.Uview, keyVal.Pview);
		
		mongoFind(keyVal.MaDfsAuthAl, keyVal.mongo30001);
		getResult("ma作者通过链接社交唤醒回流\t", keyVal.MaDfsAuthAl, keyVal.Uview, keyVal.Pview);

		mongoFind(keyVal.MaPerD, keyVal.mongo30001);
		getResult("ma总UV\t",keyVal.MaPerD,keyVal.MapgEnSuc);
		
//		3000webAPP 日统计
    	mongoFind(keyVal.OutAuthSharAl, keyVal.mongo30001);
    	getResult("wa作者分享\t", keyVal.OutAuthSharAl, keyVal.Uview, keyVal.Pview);
    	
    	mongoFind(keyVal.OutAuthSharBacAl, keyVal.mongo30001);
    	getResult("wa作者社交唤醒回流\t", keyVal.OutAuthSharBacAl, keyVal.Uview, keyVal.Pview);
    	
    	mongoFind(keyVal.OutReadSharAl, keyVal.mongo30001);
    	getResult("wa读者分享\t", keyVal.OutReadSharAl, keyVal.Uview, keyVal.Pview);
    	
    	mongoFind(keyVal.OutReadSharBacAl, keyVal.mongo30001);
    	getResult("wa读者社交唤醒回流\n", keyVal.OutReadSharBacAl, keyVal.Uview, keyVal.Pview);
    	for (String string : set) {
			str = str + string;
		}
    	return str;
	}
    
    public static String findLocal() {
    	//本地测试
    	mongoFind(keyVal.MaUpd,keyVal.test);
    	getResult("wa读者社22唤醒回流\t",keyVal.MaUpd,keyVal.Uview,keyVal.Pview);

		mongoFind(keyVal.OutReadSharBacAl, keyVal.test);

		System.out.println(getResult("wa读者社交唤醒回流\n", keyVal.OutReadSharBacAl, keyVal.Uview, keyVal.Pview));
		for (String string : set) {
			str = str + string;
		}
    	return str;
    }
}
