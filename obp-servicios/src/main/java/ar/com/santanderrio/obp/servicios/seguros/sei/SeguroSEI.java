/*
 * 
 */
package ar.com.santanderrio.obp.servicios.seguros.sei;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * SEI para la obtencion del token encriptado para la conexion a seguros.
 *
 */
@Path("/seguros")
public interface SeguroSEI {

	/**
	 * Obtener el token encriptado para invocar a seguros.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerTokenEncriptado")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<TokenView> obtenerTokenEncriptado();

}
