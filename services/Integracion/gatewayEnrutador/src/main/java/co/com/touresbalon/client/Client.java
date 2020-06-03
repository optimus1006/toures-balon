package co.com.touresbalon.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.camel.Body;

import co.com.touresbalon.model.RequestRest;
/**
 * 
 * @author Andrea Gómez
 *
 */
@Path("/")
public interface Client {

	@POST
	@Path("/gatewayEnrutador")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void gatewayEnrutador(@Body RequestRest body);

	@GET
	@Path("/nombreProyecto")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void nombreProyecto();
}