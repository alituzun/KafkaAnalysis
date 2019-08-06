package Db.cons;

import static java.util.Arrays.asList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	MongoClient mongoClient;
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("Deneme");
    	File file = new File("C:\\Users\\Hz.Melkor\\Desktop\\Teb-proje\\ConsumerData.txt");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        String satir = reader.readLine(); 
        String date;
        String clck;
        int istcount = 0,becount= 0,mscount= 0,tkcount= 0,lncount=0;
        while (satir!=null) {
        	
        	String city = null;
            //System.out.println(satir);
        	date=satir.substring(0, 10);
        	clck=satir.substring(11, 19);
        	if(satir.contains("Istanbul")) {
        		istcount++;
        		database.getCollection("kafkalog").insertOne( new Document("address",
                         new Document()
                         .append("tarih", date)
                         .append("saat", clck)
                         .append("city", "Istanbul")));
        		city="Istanbul";
        	}
        	if(satir.contains("Moskow")) {
        		mscount++;
        		database.getCollection("kafkalog").insertOne( new Document("address",
                         new Document()
                         .append("tarih", date)
                         .append("saat", clck)
                         .append("city", "Moskow")));
        		city="Moskow";
        	}
        	if(satir.contains("London")) {
        		tkcount++;
        		database.getCollection("kafkalog").insertOne( new Document("address",
                         new Document()
                         .append("tarih", date)
                         .append("saat", clck)
                         .append("city", "London")));
        		city="London";
        	}
        	if(satir.contains("Tokyo")) {
        		lncount++;
        		database.getCollection("kafkalog").insertOne(new Document("address",
                         new Document()
                         .append("tarih", date)
                         .append("saat", clck)
                         .append("city", "Tokyo")));
        		city="Tokyo";
        	}
        	if(satir.contains("Beijing")) {
        		becount++;
        		database.getCollection("kafkalog").insertOne(new Document("address",
                         new Document()
                         .append("tarih", date)
                         .append("saat", clck)
                         .append("city", "Beijing")));
        		city="Beijing";
        	}
        	//System.out.println(clck+" "+city);
            satir = reader.readLine();
       }
        reader.close();
    }
}
