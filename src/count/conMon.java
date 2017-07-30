package count;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class conMon {
	//localHost

    public static  DBCollection  mongoConn(String db,String coll){
        DBCollection collection = null;
        try {
			Mongo mongoClient = new MongoClient( "localhost" , 27017 );
            // 连接到数据库
			DB mongoDatabase = mongoClient.getDB(db);
            System.out.println("----------------------Connect to "+mongoDatabase.getName()+" successfully");
           collection = mongoDatabase.getCollection(coll);
            System.out.println("----------------------Choose collection:"+coll);
        } catch (Exception e) {
            e.printStackTrace();
            return collection;
        }
        return collection;
    }
	
	//29999mongo
    public static  DBCollection  mongoConn29999(String db,String coll){
        DBCollection collection = null;
        try {
			Mongo mongoClient = new MongoClient( "10.26.222.98" , 29999 );
            // 连接到数据库
			DB mongoDatabase = mongoClient.getDB(db);
            System.out.println("----------------------Connect to "+mongoDatabase.getName()+" successfully");
           collection = mongoDatabase.getCollection(coll);
            System.out.println("----------------------Choose collection:"+coll);
        } catch (Exception e) {
            e.printStackTrace();
            return collection;
        }
        return collection;
    }
    
  //300001mongo
    public static  DBCollection  mongoConn30001(String db,String coll){
        DBCollection collection = null;
        try {
            @SuppressWarnings("resource")
			Mongo mongoClient = new MongoClient( "10.173.196.67" , 30001 );
			DB mongoDatabase = mongoClient.getDB(db);
            System.out.println("----------------------Connect to "+mongoDatabase.getName()+" successfully");
           collection = mongoDatabase.getCollection(coll);
            System.out.println("----------------------Choose collection:"+coll);
        } catch (Exception e) {
            System.out.println(e.getClass().getName()+e.getMessage());
            return collection;
        }
        return collection;
    }


}