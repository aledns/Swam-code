package model;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class SmartControllerSerializer  implements Serializer<SmartController> {
	private ObjectMapper mapper = null;
	
	public SmartControllerSerializer() {
		if (mapper==null) {
			mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		}
			
	}
	

	@Override
	public byte[] serialize(String topic, SmartController data) {
		byte obj[] = null;
		
		try {
			obj = mapper.writeValueAsBytes(data);
		} catch (Exception e) {
			System.err.println("Exception on serialize: "+e.getMessage());
		}
		
		return obj;
	}

}
