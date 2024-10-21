/*
 * 
 */
package ar.com.santanderrio.obp.servicios.obe.sei;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

/**
 * The Interface ObeSEI.
 */
@Path("/obe")
public interface ObeSEI {

	/**
	 * Obtener token encriptado.
	 *
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
	@SuppressWarnings("rawtypes")
	@POST
	@Path("/obtenerTokenEncriptado")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta obtenerTokenEncriptado(@Context org.apache.cxf.jaxrs.ext.MessageContext mc);

}
