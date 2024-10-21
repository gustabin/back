/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

/**
 * The Interface RsaSEI.
 */
@Path("/rsa")
public interface RsaSEI {

	/**
	 * Es una prueba
	 *
	 * @return string prueba
	 */
	@POST
	@Path("/getRsaDeviceTokenCookie")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<String> getRsaDeviceTokenCookie();
}
