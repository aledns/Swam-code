package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class FirestoreDb {
	
	@Inject
	private Logger logger;
	
	private Firestore db;
	
	public FirestoreDb() {
		try {
			File f = new File("./src/main/resources/smartwateringplants-firebase-adminsdk-x2z1l-ad878bd177.json");
//			logger.info("File exis);
			FileInputStream sa = new FileInputStream("./src/main/resources/smartwateringplants-firebase-adminsdk-x2z1l-ad878bd177.json");
			logger.info("Obtained file. Is null ? "+(sa==null?true:false));
			FirebaseOptions opt = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(sa))
				.setDatabaseUrl("https://smartwateringplants.firebaseio.com")
				.build();
			FirebaseApp.initializeApp(opt);
			db = FirestoreClient.getFirestore();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public Map<String,Object> getLastValue(String gateway, String boardName) throws Exception {
		logger.info("Db is null? "+(db==null?true:false));
		DocumentReference docRef = db.collection(gateway).document(boardName);
		logger.info("Document is null? :"+(docRef==null?true:false));
		
		Map<String,Object> map = docRef.get().get().getData();
		
		return map;
	}
	
	public static void listF() {
		File f = new File(".src/main/resources/smartwateringplants-firebase-adminsdk-x2z1l-ad878bd177.json");
//		File s = new File(getClass().getClassLoader().getResource("smartwateringplants-firebase-adminsdk-x2z1l-ad878bd177.json").getFile());
		System.out.println("File exists?: "+f.exists());
		
		try (Stream<java.nio.file.Path> walk = Files.walk(Paths.get("."))) {
			List<String> result = walk.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());
			
			result.forEach(System.out::println);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
