package ingestion;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class IngestionSystem {

	public static void main(String[] args) throws Exception {
		Logger logger = LoggerFactory.getLogger(IngestionSystem.class);
		Firestore db = getDb();
		KafkaConsumer<String,String> consumer = getConsumer();

		String patternString = "[a-zA-Z0-9]+@gmail\\.com";
		Pattern pattern = Pattern.compile(patternString);

		consumer.subscribe(pattern);

		
		long timePoll = 2000;
		
		
		while (true) {
			ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(timePoll));
			for (ConsumerRecord<String,String> record : records) {
				logger.info("Recived message: "+record.value());
				JSONObject obj = new JSONObject(record.value());
				DocumentReference document = db.collection(record.topic()).document(obj.getString("id"));
				document.set(obj.toMap());
			}
		}

	}
	
	private static Firestore getDb() throws Exception  {
		FileInputStream in = new FileInputStream("./src/main/resources/smartwateringplants-firebase-adminsdk-qd6fe-6e52578486.json");
		
		FirebaseOptions opt = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(in))
				.setDatabaseUrl("https://smartwateringplants.firebaseio.com")
				.build();
		FirebaseApp.initializeApp(opt);
			
		return FirestoreClient.getFirestore();
	}
	
	private static KafkaConsumer<String,String> getConsumer() {
		String des = StringDeserializer.class.getName();
		Properties p = new Properties();
		p.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		p.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, des);
		p.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, des);
		p.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "test_id");
		
		//set offset = 0 for new topics
		p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		//used by consumer to check if there are new topics
		p.setProperty(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "1000");
		return new KafkaConsumer<String,String>(p);
	}

}
