/*
 * 
 */
package ar.com.santanderrio.obp.servicios.fecp.sei;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.OfertaComercialView;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * SEI para la obtencion del los parametros para conexion para FECP.
 */
@Path("/fecp")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public interface FecpSEI {

	/**
	 * Obtener los parametros para invocar a FECP.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerTokenEncriptado")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<TokenView> obtenerTokenEncriptado(OfertaComercialView oferta);

}
