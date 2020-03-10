package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import model.SmartGatewayConfig;

@Path("/client")
public class ClientEndpoint {
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response registerUser(@FormParam("email") String email,
			@FormParam("password") String password, @FormParam("hostname") String hostname) {
		
		CloseableHttpResponse response;
		
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost post = new HttpPost(SmartGatewayConfig.PATH_BACKEND+"/client");
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("email", email));
			params.add(new BasicNameValuePair("password", password));
			params.add(new BasicNameValuePair("hostname",hostname));
			post.setEntity(new UrlEncodedFormEntity(params));
			
			response = client.execute(post);
			
			if (response.getStatusLine().getStatusCode()==Response.Status.OK.getStatusCode()) {
				SmartGatewayConfig.EMAIL_GATEWAY = email;
				SmartGatewayConfig.HOSTNAME_GATEWAY = hostname;
			}
				return Response.status(Status.OK).entity("Utente registrato").build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}

}
