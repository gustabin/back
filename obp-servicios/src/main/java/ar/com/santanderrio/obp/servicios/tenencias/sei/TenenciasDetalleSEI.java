/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasDetalleInView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasDetalleView;

/**
 * The Interface TenenciasDetalleSEI.
 *
 * @author desa
 */
@Path("/tenenciasDetalle")
public interface TenenciasDetalleSEI {

	/**
	 * realiza consultar detalles en Tenencias.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/consultarDetalle")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TenenciasDetalleView> consultarDetalleTenencias(TenenciasDetalleInView viewRequest);

}
