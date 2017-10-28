package course.homework;

import java.net.*;
import com.mongodb.*;
public class hw2_3 {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient("localhost",27017); DB db = client.getDB("students");
        DBCollection collection = db.getCollection("grades");
        BasicDBObject searchQuery = new BasicDBObject();
        BasicDBObject sortQuery = new BasicDBObject();
        searchQuery.put("type","homework");
        sortQuery.put("student_id", 1);
        sortQuery.put("score", 1);
        DBCursor cursor = collection.find(searchQuery).sort(sortQuery);
        System.out.println(cursor.count());
        BasicDBObject tempDocument = null;
        int i = 0;
        while (cursor.hasNext()) {
            BasicDBObject document = (BasicDBObject) cursor.next();
            System.out.println(document);
            if (tempDocument != null) {
                if (!document.get("student_id").equals( tempDocument.get("student_id"))) {
                    collection.remove(document);
                    i++; }
            } else {
                collection.remove(document);
                i++;
            }
            tempDocument = document;
        }
        System.out.println(i);
    }
}

