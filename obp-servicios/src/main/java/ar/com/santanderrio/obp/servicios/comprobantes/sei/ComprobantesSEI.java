/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.sei;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobantesView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobantesViewIn;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.HistorialComprobantesView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.TransaccionViewIn;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Interface ComprobantesSEI.
 *
 * @author luis.pedro.lopez
 */
@Path("/comprobantes")
public interface ComprobantesSEI {

	/**
	 * Obtiene comprobantes para la pestaña de Transacciones.
	 *
	 * @param recargaPagina
	 *            the recarga pagina
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerTransacciones")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobantesView> obtenerComprobantes(ComprobantesViewIn recargaPagina);

	/**
	 * Obtiene comprobantes para la pestaña de Transacciones.
	 *
	 * @param idComprobante
	 *            the id comprobante
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerDetalleTransacciones")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@NotNull
	Respuesta<DetalleComprobanteView> obtenerDetalleComprobantes(ComprobantesViewIn idComprobante);

	/**
	 * Descargar comprobante PDF.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobantePDF")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargarComprobantePDF();
	
	/**
	 * Consulta historial de un comprobante en particular.
	 *
	 * @param recargaPagina
	 *            the recarga pagina
	 * @return the respuesta
	 */
	@POST
	@Path("/consultaHistorial")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<HistorialComprobantesView> consultaHistorial(ComprobantesViewIn consultaHistorial);

	
	@POST
    @Path("/obtenerDetalleHistorial")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Respuesta<DetalleComprobanteView> obtenerDetalleHistorial(ComprobantesViewIn idComprobante);

	
	@POST
    @Path("/obtenerSolapaPorDefecto")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	Respuesta<TransaccionViewIn> solapaPorDefecto();
	
	
	@POST
    @Path("/descargaHistorial")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargaHistorial(ComprobantesViewIn viewIn);
	
}
