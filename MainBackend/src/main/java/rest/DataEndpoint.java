package rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import utils.FirestoreDb;

@Path("/data")
public class DataEndpoint {
	
//	@Inject
//	private FirestoreDb firestore;
	
	@Path("/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDataPlant(@HeaderParam("hostname") String hostname, @PathParam("name") String name) {
		
		return Response.status(Status.OK).build();
	}
	

}
