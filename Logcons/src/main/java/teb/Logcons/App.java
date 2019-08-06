package teb.Logcons;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
 
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
 
public class App {
	private static Properties createConsumerConfig() {
        Properties configProperties = new Properties();
        configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "buyukveriGroup");
        configProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "client_id");
        return configProperties;
    }
	private static Scanner in;
 
	public static void main(String[] args) throws Exception {
 
		String topicName = "buyukveri";
		
		ConsumerThread consumerRunnable = new ConsumerThread(topicName);
        consumerRunnable.run();
        String line = "";
        while (!line.equals("exit")) {
            line = in.next();
        }
        consumerRunnable.getKafkaConsumer().wakeup();
        System.out.println("Stopping consumer .....");
		
	}
	
	
	private static class ConsumerThread implements Runnable{
        private String topicName;
        private KafkaConsumer<String,String> kafkaConsumer;
 
        public ConsumerThread(String topicName){
            this.topicName = topicName;
        }
        public void run() {
           
        	String dosyaYolu = "C:\\Users\\Hz.Melkor\\Desktop\\Teb-proje\\ConsumerData.txt";
        	
            kafkaConsumer = new KafkaConsumer<String, String>(createConsumerConfig());
            kafkaConsumer.subscribe(Arrays.asList(topicName));
            
            try {
            	FileOutputStream fos = new FileOutputStream(dosyaYolu);
        		OutputStreamWriter osw=new OutputStreamWriter(fos,"UTF-8");
                while (true) {
                    @SuppressWarnings("deprecation")
					ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println(record.value());
                    osw.write(record.value()+"\n");
                    }         
                    }
            }catch(WakeupException ex){
                System.out.println("Exception caught " + ex.getMessage());
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
                kafkaConsumer.close();
                System.out.println("Close KafkaConsumer");
            }
        }
        public KafkaConsumer<String,String> getKafkaConsumer(){
           return this.kafkaConsumer;
        }
    }
	
}
