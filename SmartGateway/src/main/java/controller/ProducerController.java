package controller;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Producer;
import model.SmartController;

public class ProducerController {
	
	@Inject
	private Producer producer;
	
	
	public void send(String topic, SmartController controller) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonObject = mapper.writeValueAsString(controller);
		producer.send(topic, jsonObject);
	}

}
