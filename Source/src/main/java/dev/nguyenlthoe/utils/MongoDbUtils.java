package dev.nguyenlthoe.utils;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertManyResult;
import java.util.List;
import org.bson.Document;

public class MongoDbUtils {
	public MongoDbUtils() {
	}

	public static void InsertManySsp(List<Document> sspLogs, MongoClient mongoClient, String dbName, String collectionName) {
		MongoDatabase database = mongoClient.getDatabase(dbName);
		MongoCollection<Document> collection = database.getCollection(collectionName);

		try {
			InsertManyResult result = collection.insertMany(sspLogs);
			System.out.println("Inserted document ids: " + result.getInsertedIds());
		} catch (MongoException var7) {
			MongoException me = var7;
			System.err.println("Unable to insert due to an error: " + me);
		}

	}
}
