package ar.com.santanderrio.obp.servicios.cosmos.sei;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cosmos.web.view.CosmosInView;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * SEI para la obtencion del los parametros para conexion para Cosmos.
 */
@Path("/cosmos")
public interface CosmosSEI {

	/**
	 * Obtener los parametros para invocar a Cosmos.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerTokenEncriptado")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<TokenView> obtenerTokenEncriptado(CosmosInView in);

}
