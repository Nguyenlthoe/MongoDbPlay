package dev.nguyenlthoe.job;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import dev.nguyenlthoe.config.Config;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bson.Document;

public class QueryDocument {
	public QueryDocument() {
	}

	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		MongoClient mongoClient = MongoClients.create(Config.URL_MONGODB);
		MongoDatabase database = mongoClient.getDatabase("linhtinh");
		MongoCollection<Document> collection = database.getCollection(args[0]);
		System.out.println(format.format(new Date(System.currentTimeMillis())));
		FindIterable<Document> findIterable = collection.find(Filters.regex("cars", args[1]));
		int numberMatch = 0;
		MongoCursor var7;
		Document document;
		if (args[2].equals("print")) {
			var7 = findIterable.iterator();

			while(var7.hasNext()) {
				document = (Document)var7.next();
				++numberMatch;
				System.out.println(document.get("cars"));
			}
		} else {
			for(var7 = findIterable.iterator(); var7.hasNext(); ++numberMatch) {
				document = (Document)var7.next();
			}
		}

		System.out.println("Number match: " + numberMatch);
		System.out.println(format.format(new Date(System.currentTimeMillis())));
	}
}
