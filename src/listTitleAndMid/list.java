package listTitleAndMid;

/**
 * Created by kami on 2017/8/6.
 */
import com.mongodb.*;
import com.sun.javafx.webkit.KeyCodeMap;
import count.getMonDat;
import net.sf.json.JSONObject;
import util.getMonDate;
import  util.keyVal;

import java.util.*;

public class list {
    public  static Set<String> set = new HashSet<String>();
    public static DBCollection mongoConn(String db,String coll){
        DBCollection collection = null;
        Mongo mongoClient = null;
        try {
            mongoClient = new MongoClient( "localhost" , 27017 );
            // 连接到数据库
            DB mongoDatabase = mongoClient.getDB(db);
            System.out.println("----------------------Connect to "+mongoDatabase.getName()+" successfully");
            collection = mongoDatabase.getCollection(coll);
            System.out.println("----------------------Choose collection:"+coll);
//            mongoClient.close();
        } catch (Exception e) {
            return collection;
        }finally {
//            mongoClient.close();
        }
        return collection;
    }

    public static DBCollection mongoConn(String db,String coll,String host,int port){
        DBCollection collection = null;
        Mongo mongoClient = null;
        try {
            mongoClient = new MongoClient( host, port );
            // 连接到数据库
            DB mongoDatabase = mongoClient.getDB(db);
            System.out.println("----------------------Connect to "+mongoDatabase.getName()+" successfully");
            collection = mongoDatabase.getCollection(coll);
            System.out.println("----------------------Choose collection:"+coll);
//            mongoClient.close();
        } catch (Exception e) {
            return collection;
        }finally {
//            mongoClient.close();
        }
        return collection;
    }

    public  static  void test(){
        Map<Long,Long> list = new HashMap();
        Map<String,String> find = new IdentityHashMap<String, String>();
        DBCollection dbColl = mongoConn(keyVal.test,"album_email");
        BasicDBList condList = new BasicDBList();
        BasicDBObject cond = new BasicDBObject();
         cond.put("$gt",1497888000000L);
        cond.put("$lte",1498838400000L);
        BasicDBObject composeCod = new BasicDBObject();
        composeCod.put("ct", cond);
        condList.add(composeCod);
        DBCursor ret = dbColl.find(composeCod);
        System.out.println(composeCod);
        System.out.println(ret.hasNext());
        while (ret.hasNext()){
            JSONObject jsonObject = JSONObject.fromObject(ret.next());
            Long mid = jsonObject.getLong("mid");
            Long album_id = jsonObject.getLong("album");
            int dbFind = Integer.parseInt(mid.toString())%4;
            int colFind = Integer.parseInt(mid.toString())%4096;
            String albumid = album_id.toString();
            String dbName = "xng_album_" + dbFind;
            String colName = "album_msg_by_mid_" + colFind;
            switch (dbFind){
                case 0:
                    query(mongoConn(dbName, colName, "10.27.208.238", 50000), dbName, colName);
                    break;
                case 1:
                    query(mongoConn(dbName, colName, "10.27.208.241", 50001), dbName, colName);
                    break;
                case 2:
                    query(mongoConn(dbName, colName, "10.26.118.56", 50002), dbName, colName);
                    break;
                case 3:
                    query(mongoConn(dbName, colName, "10.27.208.233",50003), dbName, colName);
                    break;
            }
//            mongoConn(dbName,colName,"10.27.208.238",50000);
//
//            list.put(mid,album_id);
//            find.put(colName,dbName);
        }

//        for( Map.Entry<Long,Long> entry : list.entrySet()){
//            System.out.println(entry.getKey()+"\t\t"+entry.getValue());
//        }
//        for (HashMap.Entry<String,String> entry : find.entrySet()){
//            System.out.println(entry.getKey()+"\t\t"+entry.getValue());
//        }

        System.out.println(set.iterator());
        /*10.27.208.238      0-0
        10.27.208.241		 1-0
        10.26.118.56		 2-0
        10.27.208.233        3-0*/
    }
    public static  void query(DBCollection collection,String key,String value){
        BasicDBObject queryObjectMain = new BasicDBObject();
        BasicDBObject queryObjectAnt = new BasicDBObject();
        queryObjectMain.put(key,value);
        queryObjectAnt.put("title",1);
        queryObjectAnt.put("mid",1);
        DBCursor cursor = collection.find(queryObjectMain, queryObjectAnt);
        while(cursor.hasNext()){
            set.add(cursor.next().toString());
        }
    }
}
