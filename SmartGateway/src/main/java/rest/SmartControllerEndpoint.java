package rest;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import model.EnvironmentTypes;
import model.SmartControllerTypes;
import model.SmartGatewayConfig;

@Path("/controller")
public class SmartControllerEndpoint {
	
	//invoca api esposte da MainBackend
	@Path("/{idenv}/{namecontroller}")
	@PUT
	public Response addSmartController(@QueryParam("type") SmartControllerTypes type,
			@PathParam("idenv") Long idenv, @PathParam("namecontroller") String namecontroller) {
		
		CloseableHttpClient client = HttpClients.createDefault();
		
		try {
			URIBuilder uri = new URIBuilder(String.format(SmartGatewayConfig.ADD_CONTROLLER, idenv,namecontroller));
			uri = uri.setParameter("type", type.toString());
			HttpPut put = new HttpPut(uri.build());
			put.setHeader("email", SmartGatewayConfig.EMAIL_GATEWAY);
			CloseableHttpResponse response = client.execute(put);
			if (response.getStatusLine().getStatusCode()==Status.CREATED.getStatusCode())
				return Response.status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return Response.status(Status.EXPECTATION_FAILED).build();
	}
	
	//invoca api esposte da MainBackend
	@Path("/{name}")
	@PUT
	public Response addEnvController(@QueryParam("type") EnvironmentTypes type,
			@PathParam("name") String name) {
		
		
		CloseableHttpClient client = HttpClients.createDefault();
		
		URIBuilder builder = null;
		try {
			builder = new URIBuilder(String.format(SmartGatewayConfig.ADD_CONTROLLER, name));
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		builder = builder.setParameter("type", type.toString());

		HttpPut put = null;
		
		try {
			put = new HttpPut(builder.build());
			put.setHeader("email", SmartGatewayConfig.EMAIL_GATEWAY);
			CloseableHttpResponse response = client.execute(put);
			if (response.getStatusLine().getStatusCode()==Status.CREATED.getStatusCode()) {
				return Response.status(Status.ACCEPTED).build();
			}
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return null;
		//add query param... or format string ADD_CONTROLLER
	}
	
	@Path("/hostname/{oldName}/{newName}")
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public Response setHostname(@PathParam("oldName") String oldName,
			@PathParam("newName") String newName) {
		
		
		return null;
	}
	
	//invocato da MainBackend. Invoca api rest esposte dalle board
	@Path("/{name}/frequency/{value}")
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public Response setFrequency(@PathParam("name") String name,
			@PathParam("value") double value) {
		
		
		return null;
	}
	
	//invocato da MainBackend. Invoca api rest esposte dalle board
	@Path("/{name}/threshold/{value}")
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public Response setThreshold(@PathParam("name") String name,
			@PathParam("value") double value) {
		
		return null;
	}

}
