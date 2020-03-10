package model;

import java.util.Properties;

import javax.inject.Inject;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;

public class Producer {
	
	@Inject 
	private Logger logger;
//	private static Logger logger = LoggerFactory.getLogger(Producer.class);
	private static KafkaProducer<String,String> kafkaProducer = null;
	
	public Producer() {
		if (kafkaProducer==null) {
			Properties p = new Properties();
			String ser = SmartControllerSerializer.class.getName();
			p.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"alednsmac:9092");
			p.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ser);
			p.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ser);
			kafkaProducer = new KafkaProducer<String,String>(p);
			logger.info("KafkaProducer created");
		}
		
	}
	
	public void send(String topic, String msg) throws Exception {
		ProducerRecord<String,String> producerRecord = new ProducerRecord<>(topic,msg);
		try {
			kafkaProducer.send(producerRecord);
			logger.info("Message sended correctly");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	

}
