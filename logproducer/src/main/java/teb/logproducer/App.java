package teb.logproducer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.Scanner;
 
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
 
public class App {
	//private static Scanner in;
 
	public static void main(String[] args) throws Exception {
 
		String topicName = "buyukveri";
		//in = new Scanner(System.in);
		System.out.println("Mesaj giriniz(Çıkmak için -1)");
 
		// Configure the Producer
		Properties configProperties = new Properties();
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"localhost:9092");
		configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.ByteArraySerializer");
		configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");
 
		Producer producer = new KafkaProducer<String, String>(configProperties);
 
		/*String line = in.nextLine();
		while (!line.equals("-1")) {
			ProducerRecord<String, String> rec = new ProducerRecord<String, String>(
					topicName, line);
			producer.send(rec);
			line = in.nextLine();
		}
		in.close();*/
		
			File file = new File("C:\\Users\\Hz.Melkor\\Desktop\\Teb-proje\\logfile.log");
	        BufferedReader reader = null;
	        reader = new BufferedReader(new FileReader(file));
	        String satir = reader.readLine();
 
            while (satir!=null) {
                //System.out.println(satir);
                ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, satir);
            	producer.send(rec);
                satir = reader.readLine();
           }
            reader.close();
		//in.close();
		producer.close();
	}
}
