package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Singleton
public class FirestoreDb {
	
	
	private Logger logger = LoggerFactory.getLogger(FirestoreDb.class);
	
	private Firestore db;
	
	public FirestoreDb() {
		db = initDB();
	}
	
	private Firestore initDB() {
		try {
			FileInputStream in = new FileInputStream("/home/aledns/Scrivania/kg.json");
			FirebaseOptions opt = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(in))
					.setDatabaseUrl("https://smartwateringplants.firebaseio.com")
					.build();
				FirebaseApp.initializeApp(opt);
				return FirestoreClient.getFirestore();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,Object> getLastValue(String gateway, String boardName) throws Exception {
		logger.info("Db is null? "+(db==null?true:false));
		DocumentReference docRef = db.collection(gateway).document(boardName);
		logger.info("Document is null? :"+(docRef==null?true:false));
		
		Map<String,Object> map = docRef.get().get().getData();
		
		return map;
		
	}
	

}
