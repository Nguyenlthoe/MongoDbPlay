package dev.nguyenlthoe.job;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.nguyenlthoe.config.Config;
import dev.nguyenlthoe.utils.MongoDbUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

public class InsertDocument {
	public InsertDocument() {
	}

	public static void main(String[] args) {
		String[] cars = new String[]{"Volvo", "Abarth", "Zenos", "Alfa Romeo", "Volkswagen", "Aston Martin", "Vauxhall", "Audi", "Toyota", "Bentley", "Tesla", "BMW", "Suzuki", "Bugatti", "Subaru", "Cadillac", "SsangYong", "Caterham", "Smart", "Chevrolet", "Skoda", "Chrysler", "Seat", "Citroen", "Saab", "Dacia", "Rolls-Royce", "Ferrari", "Renault", "Fiat", "Radical", "Ford", "Porsche", "Honda", "Peugeot", "Hyundai", "Pagani", "Infiniti", "Noble", "Jaguar", "Nissan", "Jeep", "Morgan", "Kia", "Mitsubishi", "Lamborghini", "Mini", "Land Rover", "MG", "Lexus", "Mercedes-Benz", "Lotus", "Mclaren", "Maserati", "Mazda", "VinFast"};
		MongoClient mongoClient = MongoClients.create(Config.URL_MONGODB);
		Random random = new Random();

		for(int k = 0; k < 200; ++k) {
			List<Document> documents = new ArrayList();
			List<Document> documents2 = new ArrayList();

			for(int i = 0; i < 5000; ++i) {
				Document document = new Document();
				Document document2 = new Document();
				Set<String> newCars = new HashSet();
				int num = getRandom(random, 3, 30);

				while(newCars.size() < num) {
					int next = getRandom(random, 0, cars.length - 1);
					newCars.add(cars[next]);
				}

				document.append("cars", newCars);
				document2.append("cars", StringUtils.join(newCars, ","));
				documents.add(document);
				documents2.add(document2);
			}

			MongoDbUtils.InsertManySsp(documents, mongoClient, "linhtinh", "carArray");
			MongoDbUtils.InsertManySsp(documents2, mongoClient, "linhtinh", "carString");
		}

	}

	public static int getRandom(Random random, int min, int max) {
		return random.nextInt(max - min) + min;
	}
}
