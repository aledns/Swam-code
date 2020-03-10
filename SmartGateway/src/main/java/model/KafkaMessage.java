package model;

public class KafkaMessage {
	private String topic;
	private SmartController controller;
	
	public KafkaMessage() {
		
	}
	
	public KafkaMessage(String topic, SmartController controller) {
		this.topic = topic;
		this.controller = controller;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public SmartController getController() {
		return controller;
	}

	public void setController(SmartController controller) {
		this.controller = controller;
	}
	
}