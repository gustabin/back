/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TenenciasPMCView;
import ar.com.santanderrio.obp.servicios.tenencias.view.DesafioPresentarView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ReporteFinancieroView;
import ar.com.santanderrio.obp.servicios.tenencias.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasInView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasView;


// TODO: Auto-generated Javadoc
/**
 * The Interface TenenciasSEI.
 *
 * @author
 */
@Path("/tenencias")
public interface TenenciasSEI {

	/**
	 * realiza consultar en Tenencias.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/consultar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TenenciasView> consultarTenencias(TenenciasInView viewRequest);

	/**
	 * realiza consultar en Tenencias.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/reporteExcel")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> reporteTenenciasExcel(TenenciasInView viewRequest);

	/**
	 * realiza desafio al Presentar.
	 *
	 * @param desafio
	 *            the desafio
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/desafioPresentar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<DesafioPresentarView> challenge(DesafioPresentarView desafio);
	
	/**
	 * realiza desafio al Presentar.
	 *
	 * @param tenencias
	 *            the tenencias
	 * @param mc
	 *            the mc
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/saltoPmc")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<TenenciasPMCView> generarVep(TenenciasPMCView tenencias,
			@Context org.apache.cxf.jaxrs.ext.MessageContext mc);
	
	/**
	 * Habilitado.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/operaPmc")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Boolean> habilitado();
	
	/**
	 * Resumen financiero.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/exportarResumenFinanciero")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteFinancieroView> exportarResumenFinanciero(TenenciasInView viewRequest);
}
