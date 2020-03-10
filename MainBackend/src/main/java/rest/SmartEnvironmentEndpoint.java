package rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;

import controller.ClientController;
import controller.SmartControllerController;
import controller.SmartEnvironmentController;
import model.device.ControllerTypes;
import model.device.EnvironmentTypes;
import model.device.SmartController;
import model.device.SmartEnvironment;

@Path("/environment")
public class SmartEnvironmentEndpoint {
	
	@Inject
	private Logger logger;
	
	@Inject
	private ClientController clientController;
	
	@Inject
	private SmartEnvironmentController environmentController;
	
	@Inject
	private SmartControllerController smartControllerController;
	
	@Path("/{name}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEnvironment(@HeaderParam("email") String email,
			@QueryParam("type") String type, @PathParam("name") String name) {
		try {
			SmartEnvironment env = environmentController.createSmartEnvironment(EnvironmentTypes.valueOf(type), name);
			clientController.addEnvironment(email, env);
			logger.info("Added new environment");
			return Response.status(Status.CREATED).entity(env).build();
		} catch (Exception e) {
			logger.info(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Path("/{idenv}/{namecont}")
	@PUT
	public Response addSmartController(@QueryParam("type") ControllerTypes type, @PathParam("idenv") Long id,
			@PathParam("namecont") String name) {
		try {
			SmartController sc = smartControllerController.createSmartController(type, name);
			environmentController.addSmartController(id, sc);
			logger.info("added new smartcontroller into environment");
			return Response.status(Status.CREATED).entity(sc).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Path("/{idenv}/smartcontroller")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllController(@PathParam("idenv") Long id) {
		try {
			List<SmartController> controllers = environmentController.getAll(id);
			logger.info("Retrived all smartcontroller within environment");
			return Response.status(Status.FOUND).entity(controllers).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
