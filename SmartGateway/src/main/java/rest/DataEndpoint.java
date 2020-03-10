package rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import controller.ProducerController;
import model.Sensor;
import model.SmartController;

@Path("/data")
public class DataEndpoint {
	
	@Inject
	private ProducerController producerController;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void messageFromFieldSystem(SmartController controller) {
		List<Sensor> outOfThreshold = controller.checkValidDataRead();
		System.out.println("Reviced message from field system");
		if (outOfThreshold!=null) {
			//notify user
		} else {
			try {
				producerController.send(null, controller);
			} catch (Exception e) {
				
			}
		}
	}
	
	

}
