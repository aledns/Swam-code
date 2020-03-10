package rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;

import controller.ClientController;
import controller.SmartEnvironmentController;
import model.device.SmartEnvironment;

@Path("/client")
public class ClientEndpoint {
	
	@Inject
	private Logger logger;
	
	@Inject
	private ClientController clientController;
	
	@Inject
	private SmartEnvironmentController envController;
	
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addClient(@FormParam("email") String email,
			@FormParam("password") String password,
			@FormParam("hostname") String hostname) {
		try {
			logger.info("Recived"+email);
			logger.info(password);
			logger.info(hostname);
			clientController.createClient(email, password,hostname);
			logger.info("Client registered correctly");
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.OK).entity("Client creato").build();
	}
	
	
	@Path("/environment")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@HeaderParam("email") String email) {
		try {
			List<SmartEnvironment> envs = clientController.getEnvironments(email);
			logger.info("Obtained all environment");
			return Response.status(Status.ACCEPTED).entity(envs).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Path("/environment/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEnvironment(@PathParam("id") Long id) {
		try {
			SmartEnvironment env = envController.get(id);
			logger.info("Obtained environment");
			return Response.status(Status.FOUND).entity(env).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	
}
