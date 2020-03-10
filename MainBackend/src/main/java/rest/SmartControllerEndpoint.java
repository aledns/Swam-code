package rest;

import javax.inject.Inject;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import controller.SmartControllerController;

@Path("/smartcontroller")
public class SmartControllerEndpoint {
	
	@Inject
	private SmartControllerController smartControllerController;
	
	@Path("/{id}/frequency/{value}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeFrequency(@PathParam("id") Long id,@PathParam("value") double value,
			@HeaderParam("email") String email) {
		try {
			smartControllerController.changeFrequency(id, value);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.status(Status.EXPECTATION_FAILED).build();
		}
	}
	

}
