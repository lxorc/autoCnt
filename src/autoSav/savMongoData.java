package autoSav;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import count.conMon;
import count.keyVal;

public class savMongoData {
	/*
	 * @kami
	 * 保存find结果数据到Mongo30001的AllCntSav集合
	 * db.AllCntSav.find()
	 * ColName:[] Uv:[] Pv:[]
	 * ColName:[] Uv:[]
	 * */
	public static void sav(String DbName,String Dat,Long...Val){
//		集群测试
//		DBCollection dbCol =
//				conMongo.mongoConn(keyVal.mongo30001, keyVal.AllCntSav);
//		本地测试
		DBCollection dbCol =
				conMon.mongoConn(keyVal.test, keyVal.AllCntSav);
        BasicDBObject obj = new BasicDBObject();
        obj.put("Col",DbName);
        obj.put("Dat",Dat);
        obj.put("Uv", Val[0]);
        if (Val.length>1) {
            obj.put("Pv", Val[1]);
		}
        dbCol.insert(obj);
        System.out.println(obj.toString());
	}
}
