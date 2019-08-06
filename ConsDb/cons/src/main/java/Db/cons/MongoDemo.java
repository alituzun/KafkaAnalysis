package Db.cons;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import static java.util.Arrays.asList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MongoDemo {

    public static void main(String[] args) {
        MongoClient mongoClient;
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("Deneme");

            database.getCollection("kafkalog").insertOne(
                    new Document("address",
                            new Document()
                            .append("street", "2 Avenue")
                            .append("zipcode", "10075")
                            .append("building", "1480")
                            .append("coord", asList(-73.9557413, 40.7720266)))
                    .append("borough", "Manhattan")
                    .append("cuisine", "Italian")
                    .append("grades", asList(
                            new Document()
                            .append("date", format.format(new Date()))
                            .append("grade", "A")
                            .append("score", 11),
                            new Document()
                            .append("date", format.format(new Date()))
                            .append("grade", "B")
                            .append("score", 17)))
                    .append("name", "Vella")
                    .append("restaurant_id", "41704620"));
        } catch (Exception e) {
            System.err.println("Bir Hata Meydana Geldi!");
            System.out.println("Hata" + e);
        }
    }
}
