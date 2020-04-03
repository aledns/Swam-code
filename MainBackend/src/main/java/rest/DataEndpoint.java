package rest;

import java.util.Map;

import javax.inject.Inject;
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
	
//	private FirestoreDb firestore = new FirestoreDb();
	@Inject
	private FirestoreDb firestore;
	
	@Path("/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDataPlant(@HeaderParam("hostname") String hostname, @PathParam("name") String name) {
		try {
			Map<String,Object> obj = firestore.getLastValue(hostname, name);
			return Response.status(Status.OK).entity(obj).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	

}
